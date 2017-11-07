package com.fullstack.shop.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.goods.dao.GoodsDao;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.goods.service.GoodsTempService;

/**
 * 商品Service
 * @author chay
 * @version 2017-04-17
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<GoodsDao, Goods> implements GoodsService<Goods>{
	
	@Autowired  
    private GoodsTempService<GoodsTemp> goodsTempService;  
	
	@Override
	public Page<Goods> findPage(Page<Goods> page, Wrapper<Goods> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		for(Goods goods : page.getRecords()){
			if(goods.getTempId()!=null){
				GoodsTemp GoodsTemp = goodsTempService.getInfoById(goods.getTempId());
				super.fieldsetUtils(GoodsTemp.getFieldset(), goods);
			}
		}
		return page;
	}
	
	@Override
	public Goods getInfoById(int id) throws BusinessException {
		Goods goods = super.getInfoById(id);
		GoodsTemp goodsTemp = goodsTempService.getInfoById(goods.getTempId());
		super.fieldsetUtils(goodsTemp.getFieldset(), goods);
		return goods;
	}
}