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
	public boolean createOrder(String wxId,Integer memberAddresId,Order order) throws BusinessException;
	/**
	 * 获取最新的订单号
	 * @return
	 * @throws BusinessException
	 */
	public String getOrderNumber() throws BusinessException;
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	public boolean editStatusById(int id,int status) throws BusinessException;
	/**
	 *  根据日期统计订单总额
	 * @param deliveryDate
	 * @return
	 * @throws BusinessException
	 */
	public Double getSumTotalByCondition(Order order) throws BusinessException;
	
}