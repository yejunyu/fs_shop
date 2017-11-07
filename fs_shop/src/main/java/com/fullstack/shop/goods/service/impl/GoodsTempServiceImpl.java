package com.fullstack.shop.goods.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.goods.dao.GoodsTempDao;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsTempService;

/**
 * 商品类型Service
 * @author chay
 * @version 2017-04-17
 */
@Service
public class GoodsTempServiceImpl extends BaseServiceImpl<GoodsTempDao, GoodsTemp> implements GoodsTempService<GoodsTemp> {
	
	@Override
	public Page<GoodsTemp> findPage(Page<GoodsTemp> page, Wrapper<GoodsTemp> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		super.fieldsetUtils(page.getRecords());
		return page;
	}
	
	@Override
	public GoodsTemp getInfoById(int id) throws BusinessException {
		GoodsTemp GoodsTemp = super.getInfoById(id);
		super.fieldsetUtils(GoodsTemp);
		return GoodsTemp;
	}
}