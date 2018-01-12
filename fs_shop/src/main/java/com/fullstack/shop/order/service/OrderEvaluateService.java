package com.fullstack.shop.order.service;

import java.util.List;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.order.entity.OrderEvaluate;

/**
 * 
 * @author chay
 * @version 2018-1-4
 */
public interface OrderEvaluateService<T> extends BaseService<OrderEvaluate> {
	
	public void batchCreate(List<OrderEvaluate> list) throws BusinessException;
}