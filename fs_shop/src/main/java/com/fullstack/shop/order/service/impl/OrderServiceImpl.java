package com.fullstack.shop.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CommonUtils;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.shop.order.dao.OrderDao;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDeliveryService;
import com.fullstack.shop.order.service.OrderDetailService;
import com.fullstack.shop.order.service.OrderService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDao, Order> implements OrderService<Order>{
	
	@Autowired  
    private OrderDetailService<OrderDetail> orderDetailService;  
	@Autowired  
    private OrderDeliveryService<OrderDelivery> orderDeliveryService;  
	
	@Override
	public Page<Order> findPage(Page<Order> page, Wrapper<Order> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}

	@Override
	public String getOrderNumber() throws BusinessException {
		EntityWrapper<Order> ew=new EntityWrapper<Order>();
		Order order = new Order();
		order.setDelFlag(null);
    	ew.setEntity(order);
    	ew.where("create_date > '"+DateUtils.getDate(DateUtils.parsePatterns[1])+"'");
    	int count = super.selectCount(ew)+1;
		return DateUtils.getDate(DateUtils.parsePatterns[2])+"-"+CommonUtils.numberPrefixZeroToString1(count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createOrder(Order order) throws BusinessException {
		super.create(order);
		
		OrderDelivery orderDelivery = order.getOrderDelivery();
		orderDelivery.setOrderId(order.getId());
		orderDeliveryService.create(orderDelivery);
		
		orderDetailService.batchInsert(order.getListOrderDetail(), order.getId());
		
		return true;
	}
	
}