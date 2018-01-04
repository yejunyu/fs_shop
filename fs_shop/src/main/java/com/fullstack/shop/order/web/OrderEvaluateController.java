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
import com.fullstack.shop.order.entity.OrderEvaluate;

@RestController  
@RequestMapping("/orderEvaluateEvaluate")  
public class OrderEvaluateController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		Page<OrderEvaluate> page = RequestUtils.getPage(request);
//		orderEvaluate.setCreateBy(null);
		EntityWrapper<OrderEvaluate> e = this.entityInit(orderEvaluate);
		page = orderEvaluateService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param OrderEvaluate
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createOrderEvaluate(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		orderEvaluateService.create(orderEvaluate);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param OrderEvaluate
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateOrderEvaluate(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		orderEvaluateService.editById(orderEvaluate);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param OrderEvaluate
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delOrderEvaluate(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		orderEvaluateService.delById(orderEvaluate);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param OrderEvaluate
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			orderEvaluateService.batchDel(orderEvaluate,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	/**
	 * 
	 * @param request
	 * @param OrderEvaluate
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,OrderEvaluate orderEvaluate) throws BusinessException {
		orderEvaluate = orderEvaluateService.getInfoById(orderEvaluate.getId());
        return this.retResult(orderEvaluate);
    }
	
}
