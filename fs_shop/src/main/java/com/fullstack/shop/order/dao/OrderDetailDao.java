package com.fullstack.shop.order.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDetail;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface OrderDetailDao extends BaseMapper<OrderDetail> {
	/**
	 * 
	 * @return
	 */
	List<OrderDetail> getByOrderCondition(Order order);
}