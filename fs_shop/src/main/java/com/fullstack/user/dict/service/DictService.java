package com.fullstack.user.dict.service;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.dict.entity.Dict;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Service
public interface DictService<T> extends BaseService<Dict> {
	
//	public Dict getById(Integer id) throws BusinessException;
//	
	public Dict editById2(Dict dict) throws BusinessException;
	
}