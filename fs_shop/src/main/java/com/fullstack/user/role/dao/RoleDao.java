package com.fullstack.user.role.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.role.entity.Role;

/**
 * 角色
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
	
}