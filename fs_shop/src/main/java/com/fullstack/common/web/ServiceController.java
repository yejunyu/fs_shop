package com.fullstack.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.shop.dict.entity.Dict;
import com.fullstack.shop.dict.service.DictService;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsImg;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsImgService;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.goods.service.GoodsTempService;
import com.fullstack.shop.user.entity.Member;
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
	protected GoodsTempService<GoodsTemp> goodsTempService;
	@Autowired  
	protected GoodsService<Goods> goodsService;
	@Autowired  
	protected GoodsImgService<GoodsImg> goodsImgService;
	
}
