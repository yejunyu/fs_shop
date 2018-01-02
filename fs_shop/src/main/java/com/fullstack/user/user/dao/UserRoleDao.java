package com.fullstack.user.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.user.entity.UserRole;

/**
 * 用户-角色
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	int delByUserId(int userId);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	int delByRoleId(int roleId);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	List<UserRole> selectByCondition(UserRole userRole);
	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int del(UserRole userRole);
}