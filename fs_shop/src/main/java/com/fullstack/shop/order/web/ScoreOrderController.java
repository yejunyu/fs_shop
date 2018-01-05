package com.fullstack.shop.order.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.order.entity.ScoreOrder;

@RestController  
@RequestMapping("/scoreOrder")  
public class ScoreOrderController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		Page<ScoreOrder> page = RequestUtils.getPage(request);
		EntityWrapper<ScoreOrder> e = this.entityInit(order);
		page = scoreOrderService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createOrder(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		String wxId = RequestUtils.getParameter(request, "wxId");
		scoreOrderService.createScoreOrder(wxId,order);
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
    public JSONObject updateOrder(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		scoreOrderService.editById(order);
		
        return this.retResult(success_update);
    }
	/**
	 * 修改状态
	 * @param request
	 * @param order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("editStatusById")
    public JSONObject editStatusById(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		scoreOrderService.editById(order);
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
    public JSONObject delOrder(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		scoreOrderService.delById(order);
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
    public JSONObject getInfoById(HttpServletRequest request,ScoreOrder order) throws BusinessException {
		order = scoreOrderService.getInfoById(order.getId());
        return this.retResult(order);
    }
	
}
