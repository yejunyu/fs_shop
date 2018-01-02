package com.fullstack.shop.dict.service;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.dict.entity.ShopDict;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Service
public interface ShopDictService<T> extends BaseService<ShopDict> {
	
//	public Dict getById(Integer id) throws BusinessException;
//	
	public ShopDict editById2(ShopDict dict) throws BusinessException;
	
}