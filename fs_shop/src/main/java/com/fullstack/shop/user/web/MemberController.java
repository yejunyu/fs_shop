package com.fullstack.shop.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.user.entity.Member;

@RestController  
@RequestMapping("/member")  
public class MemberController extends ServiceController {
	
	
	/**
	 * 用户列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Member member) throws BusinessException {
		Page<Member> page = RequestUtils.getPage(request);
//		EntityWrapper<User> e = this.entityInit(user);
//		page = userService.findPage(page, e);
        return this.retResult(page);
    }
	
}
