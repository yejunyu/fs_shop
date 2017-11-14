package com.fullstack.shop.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CommonUtils;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.common.utils.MathUtils;
import com.fullstack.shop.order.dao.OrderDao;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDeliveryService;
import com.fullstack.shop.order.service.OrderDetailService;
import com.fullstack.shop.order.service.OrderService;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.entity.MemberAddress;
import com.fullstack.shop.user.service.MemberAddressService;
import com.fullstack.shop.user.service.MemberService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDao, Order> implements OrderService<Order>{
	
	@Autowired  
    private OrderDetailService<OrderDetail> orderDetailService;  
	@Autowired  
    private OrderDeliveryService<OrderDelivery> orderDeliveryService;  
	@Autowired  
    private MemberService<Member> memberService;  
	@Autowired  
    private MemberAddressService<MemberAddress> memberAddressService;
	
	@Override
	public Page<Order> findPage(Page<Order> page, Wrapper<Order> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		return page;
	}

	@Override
	public String getOrderNumber() throws BusinessException {
		EntityWrapper<Order> ew=new EntityWrapper<Order>();
		Order order = new Order();
		order.setDelFlag(null);
    	ew.setEntity(order);
    	ew.where("create_date > '"+DateUtils.getDate(DateUtils.parsePatterns[1])+"'");
    	int count = super.selectCount(ew)+1;
		return DateUtils.getDate(DateUtils.parsePatterns[2])+"-"+CommonUtils.numberPrefixZeroToString1(count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createOrder(String wxId,Integer memberAddresId,Order order) throws BusinessException {
		//1.创建订单
		order.setNumber(this.getOrderNumber());
		order.setOrderTime(DateUtils.getDate());
		order.setTotal(this.getTotal(order.getListOrderDetail()));
		order.setCreateBy(memberService.getByWxId(wxId).getId());
		super.create(order);
		//2.创建订单配送信息
		MemberAddress memberAddress = memberAddressService.getInfoById(memberAddresId);
		OrderDelivery orderDelivery = order.getOrderDelivery();
		orderDelivery.setDeliveryAddress(memberAddress.getAddress()+"  "+memberAddress.getDetailAddress());
		orderDelivery.setCustomer(memberAddress.getName()+"  "+memberAddress.getSex());
		orderDelivery.setContactWay(memberAddress.getPhone());
		orderDelivery.setOrderId(order.getId());
		orderDeliveryService.create(orderDelivery);
		//3.创建订单详情
		orderDetailService.batchInsert(order.getListOrderDetail(), order.getId());
		return true;
	}
	/**
	 * 统计订单总额
	 * @param list
	 * @return
	 */
	public String getTotal(List<OrderDetail> list){
		double t = 0;
		for(OrderDetail detail:list){
			t+=(detail.getCount()*MathUtils.getDouble2(detail.getPrice()));
		}
		return t+"";
	}
	
}