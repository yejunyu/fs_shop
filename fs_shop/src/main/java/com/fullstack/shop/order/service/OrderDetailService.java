package com.fullstack.shop.order.service;

import java.util.List;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
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
}