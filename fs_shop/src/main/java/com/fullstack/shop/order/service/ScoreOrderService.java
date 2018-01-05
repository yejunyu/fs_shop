package com.fullstack.shop.order.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.order.entity.ScoreOrder;

/**
 * 
 * @author chay
 * @version 2018-1-5
 */
public interface ScoreOrderService<T> extends BaseService<ScoreOrder> {
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	public boolean createScoreOrder(String wxId,ScoreOrder scoreOrder) throws BusinessException;
	
}