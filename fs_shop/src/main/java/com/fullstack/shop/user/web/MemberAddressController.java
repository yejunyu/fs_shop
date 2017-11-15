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
	/**
	 * 
	 * @param request
	 * @param memberAddress
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createOrder(HttpServletRequest request,MemberAddress memberAddress) throws BusinessException {
		memberAddressService.create(memberAddress);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateOrder(HttpServletRequest request,MemberAddress memberAddress) throws BusinessException {
		memberAddressService.editById(memberAddress);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delOrder(HttpServletRequest request,MemberAddress memberAddress) throws BusinessException {
		memberAddressService.delById(memberAddress);
        return this.retResult(success_del);
    }
	/**
	 * 
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,MemberAddress memberAddress) throws BusinessException {
		memberAddress = memberAddressService.getInfoById(memberAddress.getId());
        return this.retResult(memberAddress);
    }
}
