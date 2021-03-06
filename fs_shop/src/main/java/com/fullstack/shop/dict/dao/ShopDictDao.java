package com.fullstack.shop.dict.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.dict.entity.ShopDict;

/**
 * 字典
 * @author chay
 * @version 2017-5-9
 */
@Mapper
public interface ShopDictDao extends BaseMapper<ShopDict> {
	
}