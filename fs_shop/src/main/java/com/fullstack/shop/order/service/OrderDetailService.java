package com.fullstack.shop.order.service;

import java.util.List;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDetail;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface OrderDetailService<T> extends BaseService<OrderDetail> {
	/**
	 * 批量保存订单信息
	 * @param listOrderDetail
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public boolean batchInsert(List<OrderDetail> listOrderDetail,int orderId) throws BusinessException;
	/**
	 * 根据订单id获取下单详情
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public List<OrderDetail> getListByOrderId(int orderId) throws BusinessException;
	/**
	 * 根据订单条件查询的对应的详情数据
	 * @param order
	 * @return
	 * @throws BusinessException
	 */
	public List<OrderDetail> getByOrderCondition(Order order) throws BusinessException;
}