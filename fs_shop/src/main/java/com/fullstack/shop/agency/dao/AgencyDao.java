package com.fullstack.shop.agency.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.agency.entity.Agency;

/**
 * 
 * @author chay
 * @version 2017-5-9
 */
@Mapper
public interface AgencyDao extends BaseMapper<Agency> {
	
}