package com.fullstack.shop.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.order.dao.OrderDeliveryDao;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.service.OrderDeliveryService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class OrderDeliveryServiceImpl extends BaseServiceImpl<OrderDeliveryDao, OrderDelivery> implements OrderDeliveryService<OrderDelivery>{
	
	@Override
	public Page<OrderDelivery> findPage(Page<OrderDelivery> page, Wrapper<OrderDelivery> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}
	
}