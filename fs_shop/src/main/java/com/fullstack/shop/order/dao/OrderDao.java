package com.fullstack.shop.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.order.entity.Order;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface OrderDao extends BaseMapper<Order> {
	/**
	 * 根据日期统计订单总额
	 * @param deliveryDate
	 * @return
	 */
	Double getSumTotalByCondition(Order order);
}