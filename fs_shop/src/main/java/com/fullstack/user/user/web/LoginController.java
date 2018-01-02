package com.fullstack.user.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.CacheUtils;
import com.fullstack.common.utils.CookieUtils;
import com.fullstack.common.utils.MD5Utils;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.user.entity.User;

@RestController  
@RequestMapping("/login")  
public class LoginController extends ServiceController {

	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("")
    public JSONObject login(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
		String loginName = RequestUtils.getParameter(request, "loginName");
		String password = RequestUtils.getParameter(request, "password");
		User user = userService.login(loginName, password);
		user.getExtraData().put(CookieUtils.TOKEN_COOKIE, this.noteLogin(user, response));
        return this.retResult("登录成功", user);
    }  
	
	/**
	 * 记录登录信息
	 * @param user
	 * @param response
	 * @throws BusinessException
	 */
	public String noteLogin(User user,HttpServletResponse response) throws BusinessException{
		long s = System.currentTimeMillis();
		String cookieVal = MD5Utils.MD5To32(user.getLoginName()+s);
		CookieUtils.setCookie(response, CookieUtils.TOKEN_COOKIE, cookieVal);
		CacheUtils.set(cookieVal, user);
		return cookieVal;
	}
}
