package com.fullstack.shop.dict.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CacheUtils;
import com.fullstack.shop.dict.dao.ShopDictDao;
import com.fullstack.shop.dict.entity.ShopDict;
import com.fullstack.shop.dict.service.ShopDictService;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Service
public class ShopDictServiceImpl extends BaseServiceImpl<ShopDictDao, ShopDict> implements ShopDictService<ShopDict>{

	
	@Override
	@Cacheable(value = CacheUtils.CACHE_NAME,key = "'ShopDict'+#id")
	public ShopDict getInfoById(int id) throws BusinessException {
		return super.getInfoById(id);
	}
	
	@CachePut(value = CacheUtils.CACHE_NAME,key = "'ShopDict'+#ShopDict.getId()")
	@Override
	public ShopDict editById2(ShopDict shopDict) throws BusinessException {
		return super.editById(shopDict);
	}

}