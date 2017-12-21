package com.fullstack.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.shop.agency.entity.Agency;
import com.fullstack.shop.agency.service.AgencyService;
import com.fullstack.shop.dict.entity.Attach;
import com.fullstack.shop.dict.entity.Dict;
import com.fullstack.shop.dict.service.AttachService;
import com.fullstack.shop.dict.service.DictService;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsImg;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsImgService;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.goods.service.GoodsTempService;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDeliveryService;
import com.fullstack.shop.order.service.OrderDetailService;
import com.fullstack.shop.order.service.OrderService;
import com.fullstack.shop.report.entity.Report;
import com.fullstack.shop.report.service.ReportService;
import com.fullstack.shop.sys.entity.Feedback;
import com.fullstack.shop.sys.service.FeedbackService;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.entity.MemberAddress;
import com.fullstack.shop.user.service.MemberAddressService;
import com.fullstack.shop.user.service.MemberService;

/**
 * 控制器支持类
 * @author chay
 * @version 2017-6-7
 */
public abstract class ServiceController extends BaseController {

	@Autowired  
	protected DictService<Dict> dictService;
	
	@Autowired  
	protected MemberService<Member> memberService;
	@Autowired  
	protected MemberAddressService<MemberAddress> memberAddressService;
	
	@Autowired  
	protected GoodsTempService<GoodsTemp> goodsTempService;
	@Autowired  
	protected GoodsService<Goods> goodsService;
	@Autowired  
	protected GoodsImgService<GoodsImg> goodsImgService;
	
	@Autowired  
	protected OrderService<Order> orderService;
	@Autowired  
	protected OrderDetailService<OrderDetail> orderDetailService;
	@Autowired  
	protected OrderDeliveryService<OrderDelivery> orderDeliveryService;
	
	@Autowired  
	protected ReportService<Report> reportService;
	
	@Autowired  
	protected AgencyService<Agency> agencyService;
	
	@Autowired  
	protected AttachService<Attach> attachService;
	
	@Autowired  
	protected FeedbackService<Feedback> feedbackService;
	
}
