package com.fullstack.user.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.auth.entity.Auth;

/**
 * 权限
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface AuthDao extends BaseMapper<Auth> {
	
}