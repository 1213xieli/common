package com.xieli.framework.common.security;

import java.util.List;

import com.xieli.framework.common.entity.ResponseEntity;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xieli.framework.common.entity.Menu;

//@FeignClient(value = "auth",path = "/menu")
public interface IMenuService {

	@RequestMapping(path="/getUserMenus",method=RequestMethod.GET)
    ResponseEntity<List<Menu>> getUserMenus(@RequestParam("userId")String userId, @RequestParam("serverFlag")String serverFlag);

}
