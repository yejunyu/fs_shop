package com.fullstack.shop.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.order.dao.ScoreOrderDao;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.ScoreOrder;
import com.fullstack.shop.order.service.OrderService;
import com.fullstack.shop.order.service.ScoreOrderService;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.service.MemberService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class ScoreOrderServiceImpl extends BaseServiceImpl<ScoreOrderDao, ScoreOrder> implements ScoreOrderService<ScoreOrder>{
	
	@Autowired  
    private MemberService<Member> memberService; 
	@Autowired  
	protected OrderService<Order> orderService;
	@Autowired  
	protected GoodsService<Goods> goodsService;
	
	@Override
	public Page<ScoreOrder> findPage(Page<ScoreOrder> page, Wrapper<ScoreOrder> wrapper) throws BusinessException {
		wrapper.orderBy("create_date", false);
		page = super.findPage(page, wrapper);
		for(ScoreOrder entity:page.getRecords()){
			entity.setGoods(goodsService.getInfoById(entity.getGoodsId()));
			entity.setOrder(orderService.getInfoById(entity.getOrderId()));
			entity.getExtraData().put("createBy", memberService.getInfoById(entity.getCreateBy()));
		}
		return page;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createScoreOrder(String wxId,ScoreOrder scoreOrder) throws BusinessException {
		//1.创建订单
		scoreOrder.setCreateBy(memberService.getByWxId(wxId).getId());
		super.create(scoreOrder);
		return true;
	}
	
}