package com.fullstack.shop.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.order.dao.OrderDetailDao;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDetailService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService<OrderDetail>{
	
	@Autowired  
    private OrderDetailDao orderDetailDao;  
	
	@Autowired  
    private GoodsService<Goods> goodsService;  
	
	@Override
	public Page<OrderDetail> findPage(Page<OrderDetail> page, Wrapper<OrderDetail> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}

	@Override
	public boolean batchInsert(List<OrderDetail> listOrderDetail, int orderId) throws BusinessException {
		for(OrderDetail detail:listOrderDetail){
			detail.setOrderId(orderId);
			super.create(detail);
		}
		return true;
	}

	@Override
	public List<OrderDetail> getListByOrderId(int orderId) throws BusinessException {
		OrderDetail detail = new OrderDetail();
		detail.setOrderId(orderId);
		EntityWrapper<OrderDetail> wrapper = this.entityInit(detail);
		List<OrderDetail> list = super.selectList(wrapper);
		for(OrderDetail orderDetail : list){
			orderDetail.setGoods(goodsService.getInfoById(orderDetail.getGoodsId()));
		}
		return list;
	}

	@Override
	public List<OrderDetail> getByOrderCondition(Order order) throws BusinessException {
		return orderDetailDao.getByOrderCondition(order);
	}
	
}