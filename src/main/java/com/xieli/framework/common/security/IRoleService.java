package com.xieli.framework.common.security;

import java.util.List;

import com.xieli.framework.common.entity.Role;
import com.xieli.framework.common.entity.ResponseEntity;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "auth",path = "/role")
public interface IRoleService {

	@RequestMapping(path="/getUserRoles",method=RequestMethod.GET)
	public ResponseEntity<List<Role>> getUserRoles(@RequestParam("userId")String userId, @RequestParam("serverFlag")String serverFlag);

}
