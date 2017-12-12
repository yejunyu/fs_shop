package com.fullstack.shop.goods.service;

import java.util.List;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.goods.entity.Goods;

/**
 * 商品接口
 * @author chay
 * @version 2017-04-17
 */
public interface GoodsService<T> extends BaseService<Goods> {
	
	public List<Goods> selByCondition(Goods goods) throws BusinessException;
}