package com.fullstack.shop.order.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.order.entity.OrderEvaluate;

@RestController  
@RequestMapping("/orderEvaluate")  
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
		JSONArray jsonArray = RequestUtils.getJSONArray(request, "list");
		List<OrderEvaluate> list = this.orderEvaluateJSONArrayToList(jsonArray);
//		orderEvaluateService.create(orderEvaluate);
		orderEvaluateService.batchCreate(list);
        return this.retResult("评价成功");
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
	
	
	public List<OrderEvaluate> orderEvaluateJSONArrayToList(JSONArray jsonArray){
		List<OrderEvaluate> list = new ArrayList<OrderEvaluate>();
		OrderEvaluate orderEvaluate = null;
		for(Object obj : jsonArray){
			JSONObject jsonObj = (JSONObject) obj;
			orderEvaluate = new OrderEvaluate();
			orderEvaluate.setOrderId(jsonObj.getInteger("orderId"));
			orderEvaluate.setGoodsId(jsonObj.getInteger("goodsId"));
			orderEvaluate.setContent(jsonObj.getString("content"));
			orderEvaluate.setGoodsScore(jsonObj.getInteger("goodsScore"));
			
			list.add(orderEvaluate);
		}
		return list;
	}
}
