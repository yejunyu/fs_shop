package com.fullstack.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.shop.agency.entity.Agency;
import com.fullstack.shop.agency.service.AgencyService;
import com.fullstack.shop.dict.entity.ShopDict;
import com.fullstack.shop.dict.service.ShopDictService;
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
import com.fullstack.shop.sys.entity.Attach;
import com.fullstack.shop.sys.entity.Feedback;
import com.fullstack.shop.sys.service.AttachService;
import com.fullstack.shop.sys.service.FeedbackService;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.entity.MemberAddress;
import com.fullstack.shop.user.service.MemberAddressService;
import com.fullstack.shop.user.service.MemberService;
import com.fullstack.user.auth.entity.Auth;
import com.fullstack.user.auth.service.AuthService;
import com.fullstack.user.dict.entity.Dict;
import com.fullstack.user.dict.service.DictService;
import com.fullstack.user.office.entity.Office;
import com.fullstack.user.office.entity.OfficeUser;
import com.fullstack.user.office.service.OfficeService;
import com.fullstack.user.office.service.OfficeUserService;
import com.fullstack.user.role.entity.Role;
import com.fullstack.user.role.entity.RoleAuth;
import com.fullstack.user.role.service.RoleAuthService;
import com.fullstack.user.role.service.RoleService;
import com.fullstack.user.user.entity.User;
import com.fullstack.user.user.entity.UserRole;
import com.fullstack.user.user.service.UserRoleService;
import com.fullstack.user.user.service.UserService;

/**
 * 控制器支持类
 * @author chay
 * @version 2017-6-7
 */
public abstract class ServiceController extends BaseController {

	
	/************* user *************************/
	@Autowired  
	protected UserService<User> userService;
	@Autowired  
	protected RoleService<Role> roleService;
	@Autowired  
	protected RoleAuthService<RoleAuth> roleAuthService;
	@Autowired  
	protected AuthService<Auth> authService;
	@Autowired  
	protected UserRoleService<UserRole> userRoleService;
	@Autowired  
	protected OfficeService<Office> officeService;
	@Autowired  
	protected OfficeUserService<OfficeUser> officeUserService;
	@Autowired  
	protected DictService<Dict> dictService;
	
	
	
	/************* shop *************************/
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
	
	@Autowired  
	protected ShopDictService<ShopDict> shopDictService;
}
