package com.fullstack.shop.order.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.order.entity.Order;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface OrderService<T> extends BaseService<Order> {
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	public boolean createOrder(Order order) throws BusinessException;
	/**
	 * 获取最新的订单号
	 * @return
	 * @throws BusinessException
	 */
	public String getOrderNumber() throws BusinessException;
}