package com.fullstack.user.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.role.entity.RoleAuth;

/**
 * 角色-权限
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface RoleAuthDao extends BaseMapper<RoleAuth> {
	/**
	 * 根据角色id删除对应的关联关系
	 * @param roleId
	 * @return
	 */
	int delByRoleId(int roleId);
	/**
	 * 根据
	 * @param authId
	 * @return
	 */
	int delByAuthId(int authId);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	List<RoleAuth> selectByRoleId(int roleId);
}