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
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.entity.OrderDetail;

@RestController  
@RequestMapping("/order")  
public class OrderController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Order order) throws BusinessException {
		Page<Order> page = RequestUtils.getPage(request);
		order.setCreateBy(null);
		EntityWrapper<Order> e = this.entityInit(order);
		page = orderService.findPage(page,e);
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
    public JSONObject createOrder(HttpServletRequest request,Order order) throws BusinessException {
		String wxId = RequestUtils.getParameter(request, "wxId");
		Integer memberAddressId = RequestUtils.getInteger(request, "memberAddressId");
		JSONArray details = RequestUtils.getJSONArray(request, "listDetail");
		order.setListOrderDetail(this.orderDetailJSONArrayToList(details));
		order.setOrderDelivery(new OrderDelivery(memberAddressId));
		orderService.createOrder(wxId,memberAddressId,order);
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
    public JSONObject updateOrder(HttpServletRequest request,Order order) throws BusinessException {
		orderService.editById(order);
		
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
    public JSONObject editStatusById(HttpServletRequest request,Order order) throws BusinessException {
		orderService.editStatusById(order.getId(),order.getStatus());
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
    public JSONObject delOrder(HttpServletRequest request,Order order) throws BusinessException {
		orderService.delById(order);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Order order) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			orderService.batchDel(order,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	/**
	 * 
	 * @param request
	 * @param Order
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,Order order) throws BusinessException {
		order = orderService.getInfoById(order.getId());
        return this.retResult(order);
    }
	
	/*******************************/
	/**
	 * 
	 * @param jsonArray
	 * @return
	 */
	public List<OrderDetail> orderDetailJSONArrayToList(JSONArray jsonArray){
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail detail = null;
		for(Object obj : jsonArray){
			JSONObject jsonObj = (JSONObject) obj;
			detail = new OrderDetail();
			detail.setGoodsId(jsonObj.getInteger("id"));
			detail.setPrice(jsonObj.getString("price"));
			detail.setCount(jsonObj.getInteger("count"));
			list.add(detail);
		}
		return list;
	}
}
