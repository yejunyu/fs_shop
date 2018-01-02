package com.fullstack.user.dict.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CacheUtils;
import com.fullstack.user.dict.dao.DictDao;
import com.fullstack.user.dict.entity.Dict;
import com.fullstack.user.dict.service.DictService;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictDao, Dict> implements DictService<Dict>{

	
	@Override
	@Cacheable(value = CacheUtils.CACHE_NAME,key = "'dict'+#id")
	public Dict getInfoById(int id) throws BusinessException {
		return super.getInfoById(id);
	}
	
	@CachePut(value = CacheUtils.CACHE_NAME,key = "'dict'+#dict.getId()")
	@Override
	public Dict editById2(Dict dict) throws BusinessException {
		return super.editById(dict);
	}

}