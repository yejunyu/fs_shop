package com.fullstack.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.shop.dict.entity.Dict;
import com.fullstack.shop.dict.service.DictService;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.goods.service.GoodsTempService;

/**
 * 控制器支持类
 * @author chay
 * @version 2017-6-7
 */
public abstract class ServiceController extends BaseController {

	/************* user *************************/
	@Autowired  
	protected DictService<Dict> dictService;
	
	@Autowired  
	protected GoodsTempService<GoodsTemp> goodsTempService;
	@Autowired  
	protected GoodsService<Goods> goodsService;
	
}
