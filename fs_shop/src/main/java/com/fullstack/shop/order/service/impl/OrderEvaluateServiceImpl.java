package com.fullstack.shop.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.order.dao.OrderEvaluateDao;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderEvaluate;
import com.fullstack.shop.order.service.OrderEvaluateService;
import com.fullstack.shop.order.service.OrderService;

/**
 * 
 * @author chay
 * @version 2018-1-4
 */
@Service
public class OrderEvaluateServiceImpl extends BaseServiceImpl<OrderEvaluateDao, OrderEvaluate> implements OrderEvaluateService<OrderEvaluate>{
	
	@Autowired  
	private OrderService<Order> orderService;
	@Autowired  
	private GoodsService<Goods> goodsService;
	
	
	@Override
	public Page<OrderEvaluate> findPage(Page<OrderEvaluate> page, Wrapper<OrderEvaluate> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		for(OrderEvaluate entity:page.getRecords()){
			entity.setOrder(orderService.getInfoById(entity.getOrderId()));
			entity.setGoods(goodsService.getInfoById(entity.getGoodsId()));
		}
		return page;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public void batchCreate(List<OrderEvaluate> list) throws BusinessException {
		
		for(OrderEvaluate entity:list){
			super.create(entity);
		}
		//评价完成修改订单状态
		if(list.size()>0){
			Integer orderId = list.get(0).getOrderId();
			orderService.editStatusById(orderId, Order.STATUS_EVALUATE);
		}
	}

	
}