package com.fullstack.shop.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.user.entity.MemberAddress;

@RestController  
@RequestMapping("/memberAddress")  
public class MemberAddressController extends ServiceController {
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException	
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,MemberAddress memberAddress) throws BusinessException {
		Page<MemberAddress> page = RequestUtils.getPage(request);
		EntityWrapper<MemberAddress> e = this.entityInit(memberAddress);
		page = memberAddressService.findPage(page, e);
		return this.retResult(page);
    }
	
}
