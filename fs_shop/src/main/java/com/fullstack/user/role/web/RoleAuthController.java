package com.fullstack.user.role.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.role.entity.RoleAuth;

@RestController  
@RequestMapping("/roleAuth")  
public class RoleAuthController extends ServiceController {
	
	/**
	 * 根据角色id查询对应的关联列表数据
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("selectByRoleId")
    public JSONObject selectByRoleId(HttpServletRequest request,RoleAuth roleAuth) throws BusinessException {
		List<RoleAuth> list = roleAuthService.selectByRoleId(roleAuth.getRoleId());
        return this.retResult(list);
    }
	
}
