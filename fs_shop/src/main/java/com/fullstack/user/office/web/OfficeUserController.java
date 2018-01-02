package com.fullstack.user.office.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.office.entity.OfficeUser;

@RestController  
@RequestMapping("/officeUser")  
public class OfficeUserController extends ServiceController {
	
	/**
	 * 根据机构id获取用户列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getUsersByOfficeId")
    public JSONObject list(HttpServletRequest request,OfficeUser officeUser) throws BusinessException {
		List<OfficeUser> list = officeUserService.getUsersByOfficeId(officeUser.getOfficeId());
        return this.retResult(list);
    }
	
}
