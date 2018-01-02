package com.fullstack.user.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.user.entity.UserRole;

@RestController  
@RequestMapping("/userRole")  
public class UserRoleController extends ServiceController {
	
	/**
	 * 根据用户id获取对应的关联角色
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("selectByUserId")
    public JSONObject selectByUserId(HttpServletRequest request,UserRole userRole) throws BusinessException {
        return this.retResult(userRoleService.selectByUserId(userRole.getUserId()));
    }
	/**
	 * 根据角色获取对应的关联用户
	 * @param request
	 * @param userRole
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("selectByRoleId")
    public JSONObject selectByRoleId(HttpServletRequest request,UserRole userRole) throws BusinessException {
        return this.retResult(userRoleService.selectByRoleId(userRole.getRoleId()));
    }
	
	@RequestMapping("del")
    public JSONObject del(HttpServletRequest request,UserRole userRole) throws BusinessException {
		userRoleService.del(userRole.getUserId(),userRole.getRoleId());
        return this.retResult();
    }
	
	@RequestMapping("batchDelByRoleId")
    public JSONObject batchDelByRoleId(HttpServletRequest request,UserRole userRole) throws BusinessException {
		userRoleService.batchDelByRoleId(userRole.getRoleId(),RequestUtils.getStrings(request,"userIds[]"));
        return this.retResult();
    }
	
}
