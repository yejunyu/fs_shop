package com.fullstack.shop.order.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.order.entity.OrderDelivery;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface OrderDeliveryService<T> extends BaseService<OrderDelivery> {
	/**
	 * 获取对应订单的派送信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public OrderDelivery getInfoByOrderId(Integer orderId) throws BusinessException;
}