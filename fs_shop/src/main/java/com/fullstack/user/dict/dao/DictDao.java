package com.fullstack.user.dict.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.dict.entity.Dict;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Mapper
public interface DictDao extends BaseMapper<Dict> {
	
}