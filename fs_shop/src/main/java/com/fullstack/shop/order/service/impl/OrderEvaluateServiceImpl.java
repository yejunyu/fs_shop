package com.fullstack.shop.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.order.dao.OrderEvaluateDao;
import com.fullstack.shop.order.entity.OrderEvaluate;
import com.fullstack.shop.order.service.OrderEvaluateService;

/**
 * 
 * @author chay
 * @version 2018-1-4
 */
@Service
public class OrderEvaluateServiceImpl extends BaseServiceImpl<OrderEvaluateDao, OrderEvaluate> implements OrderEvaluateService<OrderEvaluate>{
	
	@Override
	public Page<OrderEvaluate> findPage(Page<OrderEvaluate> page, Wrapper<OrderEvaluate> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}

	
}