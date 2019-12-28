package com.xieli.framework.common.security;

import javax.servlet.http.HttpServletRequest;

import com.xieli.framework.common.constant.CommonErrorCodes;
import com.xieli.framework.common.constant.Global;
import com.xieli.framework.common.constant.StatusCode;
import com.xieli.framework.common.entity.Role;
import com.xieli.framework.common.entity.Menu;
import com.xieli.framework.common.entity.ResponseEntity;
import com.xieli.framework.common.util.RedisUtils;
import com.xieli.framework.common.util.StringUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 切面规则 处理权限
 * @author 李文龙
 *
 */
@Aspect
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

	@Autowired
	RedisUtils redisUtils;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IMenuService menuService;

	@Value("${server.name}")
	private String serverName;

	//定义切面
	@Pointcut("@annotation(com.xieli.framework.common.security.Authorization)")
	public void annotationPointCut() {}

	//定义方法拦截的规则
	@Around("annotationPointCut()")
	public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorization action = method.getAnnotation(Authorization.class);
        String menuFlag = action.value();
        String[] roles = action.role();
        if(roles!=null&&roles.length>0&& StringUtils.isEmpty(roles[0])){
        	roles = null;
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(Global.AUTHORIZE_TOKEN);
        if(StringUtils.isEmpty(token)){
        	token = request.getParameter(Global.AUTHORIZE_TOKEN);
        }
        logger.debug("切面中获取token："+token);
        if(StringUtils.isEmpty(token)){
        	return ResponseEntity.buildCodes(StatusCode.UNAUTHORIZED, CommonErrorCodes.UNAUTHORIZED);
        }

        String userId = null;
        if(redisUtils.hasKey(token)){
        	Object obj = redisUtils.get(token);
        	if(obj!=null){
        		userId = (String) ((Map)obj).get("id");
        	}else{   //key值信息被删 说明被踢出
        		return ResponseEntity.buildCodes(StatusCode.GONE, CommonErrorCodes.INVALID);
        	}
        }else{
        	return ResponseEntity.buildCodes(StatusCode.INVALID, CommonErrorCodes.INVALID);
        }


    	//判断用户是否据有该系统权限
    	/*if(!hasServer(userId)){
    		return ResponseEntity.build(StatusCode.FORBIDDEN, "无权限操作", null);
    	}*/

    	//当只有菜单无角色控制时
    	if(StringUtils.isNotEmpty(menuFlag)&&(roles==null||roles.length==0)){
    		if(!hasMenu(userId,menuFlag)){
    			return ResponseEntity.buildCodes(StatusCode.FORBIDDEN, CommonErrorCodes.FORBIDDEN);
    		}
    	}

    	//当只有角色无菜单控制时
    	if(StringUtils.isEmpty(menuFlag)&&roles!=null&&roles.length>0){
    		if(!hasRole(userId,roles)){
    			return ResponseEntity.buildCodes(StatusCode.FORBIDDEN, CommonErrorCodes.FORBIDDEN);
    		}
    	}

    	//当菜单角色都有时  满足一个则通过
    	if(StringUtils.isNotEmpty(menuFlag)&&roles!=null&&roles.length>0){
    		if(!hasMenu(userId, menuFlag)&&!hasRole(userId,roles)){
    			return ResponseEntity.buildCodes(StatusCode.FORBIDDEN, CommonErrorCodes.FORBIDDEN);
    		}
    	}
		logger.debug("AuthorizationInterceptor passed!");
        return joinPoint.proceed();
	}

	private boolean hasMenu(String userId,String menuFlag){
		//当传入的角色信息为空，默认角色验证不通过
		if(StringUtils.isEmpty(menuFlag)){
			return false;
		}
		boolean hasMenu = false;
		String serverNameUp = this.serverName.toUpperCase();
		String key = serverNameUp+"_MENU_"+userId;
		Set<String> menusSet = redisUtils.sStringGet(key);

		//如果从redis获取的角色信息为空 那么从安全服务中获取角色信息 并保存到redis中
		if(menusSet==null||menusSet.isEmpty()){
			menusSet = new HashSet<String>();
			ResponseEntity<List<Menu>> result = menuService.getUserMenus(userId, serverNameUp);
			if(result.getStatus()==StatusCode.OK){
				List<Menu> menusList = result.getData();
				for(Menu m:menusList){
					menusSet.add(m.getFlag().trim());
				}
				String[] menusArray = new String[menusSet.size()];
				menusSet.toArray(menusArray);
				redisUtils.sStringSetAndTime(key, Global.TOKEN_EXPIRE,menusArray);
			}
		}else{
			redisUtils.expire(key, Global.TOKEN_EXPIRE);
		}
		if(menusSet.contains(menuFlag)){
			hasMenu = true;
		}
		return hasMenu;
	}

	private boolean hasRole(String userId,String[] roles){
		//当传入的角色信息为空，默认角色验证不通过
		if(roles.length<=0){
			return false;
		}
		boolean hasRole = false;
		String serverNameUp = this.serverName.toUpperCase();
		String key = serverNameUp+"_ROLE_"+userId;
		Set<String> rolesSet = redisUtils.sStringGet(key);

		//如果从redis获取的角色信息为空 那么从安全服务中获取角色信息 并保存到redis中
		if(rolesSet==null||rolesSet.isEmpty()){
			rolesSet = new HashSet<String>();
			ResponseEntity<List<Role>> result = roleService.getUserRoles(userId, serverNameUp);
			if(result.getStatus()==StatusCode.OK){
				List<Role> rolesList = result.getData();
				for(Role r:rolesList){
					rolesSet.add(r.getFlag().trim());
				}
				String[] rolesArray = new String[rolesSet.size()];
				rolesSet.toArray(rolesArray);
				redisUtils.sStringSetAndTime(key, Global.TOKEN_EXPIRE, rolesArray);
			}
		}else{
			redisUtils.expire(key, Global.TOKEN_EXPIRE);
		}


		for(String roleFlag:roles){
			if(rolesSet.contains(roleFlag)){
				hasRole = true;
				break;
			}
		}

		return hasRole;
	}

	private boolean hasServer(String userId){

		boolean hasServer = false;
		String serverNameUp = this.serverName.toUpperCase();
		String key = serverNameUp+"_ROLE_"+userId;
		Set<String> rolesSet = redisUtils.sStringGet(key);

		//如果从redis获取的角色信息为空 那么从安全服务中获取角色信息 并保存到redis中
		if(rolesSet==null||rolesSet.isEmpty()){
			rolesSet = new HashSet<String>();
			ResponseEntity<List<Role>> result = roleService.getUserRoles(userId, serverNameUp);
			if(result.getStatus()==StatusCode.OK){
				List<Role> rolesList = result.getData();
				for(Role r:rolesList){
					rolesSet.add(r.getFlag().trim());
				}
				String[] rolesArray = new String[rolesSet.size()];
				rolesSet.toArray(rolesArray);
				redisUtils.sStringSetAndTime(key, Global.TOKEN_EXPIRE, rolesArray);
			}
		}else{
			redisUtils.expire(key, Global.TOKEN_EXPIRE);
		}

		//如果存在该系统的角色，那么判定具有该系统权限
		if(rolesSet!=null&&rolesSet.size()>0){
			hasServer = true;
		}

		return hasServer;
	}


	//@After("execution(* com.cenobitor.aop.service.DemoMethodService.*(..))")
    public void after(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
		logger.info("方法规则式拦截,"+method.getName());
    }

}
