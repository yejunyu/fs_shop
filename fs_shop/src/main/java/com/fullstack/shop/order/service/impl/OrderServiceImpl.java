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
import com.fullstack.shop.report.entity.Report;
import com.fullstack.shop.report.service.ReportService;
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
    private OrderDao orderDao;  
	
	@Autowired  
    private OrderDetailService<OrderDetail> orderDetailService;  
	@Autowired  
    private OrderDeliveryService<OrderDelivery> orderDeliveryService;  
	@Autowired  
    private MemberService<Member> memberService;  
	@Autowired  
    private MemberAddressService<MemberAddress> memberAddressService;
	@Autowired  
    private ReportService<Report> reportService;
	
	@Override
	public Page<Order> findPage(Page<Order> page, Wrapper<Order> wrapper) throws BusinessException {
		wrapper.orderBy("create_date", false);
		page = super.findPage(page, wrapper);
		for(Order order:page.getRecords()){
			order.setOrderDelivery(orderDeliveryService.getInfoByOrderId(order.getId()));
			List<OrderDetail> list = orderDetailService.getListByOrderId(order.getId());
			order.setListOrderDetail(list);
			if(list.size()>0){
				order.getExtraData().put("details", this.getDetails(list));
			}
		}
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
		return DateUtils.getDate(DateUtils.parsePatterns[2])+CommonUtils.numberPrefixZeroToString(3,MathUtils.getRandom(1000))+CommonUtils.numberPrefixZeroToString1(count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createOrder(String wxId,Integer memberAddresId,Order order) throws BusinessException {
		if(order.getListOrderDetail().size()==0){
			throw new BusinessException(9002002);
		}
		//1.创建订单
		order.setNumber(this.getOrderNumber());
		order.setOrderTime(DateUtils.getDate());
		order.setPersons(this.getCounts(order.getListOrderDetail()));
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
	private String getTotal(List<OrderDetail> list){
		double t = 0;
		for(OrderDetail detail:list){
			t+=(detail.getCount()*MathUtils.getDouble2(detail.getPrice()));
		}
		return t+"";
	}
	
	private int getCounts(List<OrderDetail> list){
		int c = 0;
		for(OrderDetail detail:list){
			c+=detail.getCount();
		}
		return c;
	}
	
	/**
	 * 获取详情的集合名称
	 * @param list
	 * @return
	 */
	private String getDetails(List<OrderDetail> list){
		String names = "";
		for(OrderDetail orderDetail : list){
			names+=(orderDetail.getGoods().getName()+"x"+orderDetail.getCount()+"、");
		}
		if((names.lastIndexOf("、")+1)==names.length()){
			names = names.substring(0, names.length()-1);
		}
		return names;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editStatusById(int id, int status) throws BusinessException {
		Order order = super.getInfoById(id);
		order.setStatus(status);
		super.updateById(order);
		//TODO 订单执行完成 ，自动生成报表（后续正式上线考虑调整为每天凌晨生成前一天，现在为了方便调试暂时放在这里触发）
		if(status == Order.STATUS_END){
			reportService.generateReportByDate(order.getDeliveryDate());
		}
		return true;
	}

	@Override
	public Double getSumTotalByCondition(Order order) throws BusinessException {
		return orderDao.getSumTotalByCondition(order);
	}
	
}