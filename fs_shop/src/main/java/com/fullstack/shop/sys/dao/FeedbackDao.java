package com.fullstack.shop.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.sys.entity.Feedback;

/**
 * 
 * @author chay
 * @version 2017-5-9
 */
@Mapper
public interface FeedbackDao extends BaseMapper<Feedback> {
	
}