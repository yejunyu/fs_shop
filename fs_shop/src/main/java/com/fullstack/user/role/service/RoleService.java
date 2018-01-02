package com.fullstack.user.role.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.role.entity.Role;

/**
 * 角色
 * @author chay
 * @version 2017-5-12
 */
@Service
public interface RoleService<T> extends BaseService<Role> {
	/**
	 * 创建角色+权限
	 * @param role
	 * @param authIds
	 * @return
	 * @throws BusinessException
	 */
	public boolean create(Role role,String[] authIds) throws BusinessException;
	/**
	 * 修改角色+权限
	 * @param role
	 * @param authIds
	 * @return
	 * @throws BusinessException
	 */
	public boolean editById(Role role,String[] authIds) throws BusinessException;
	
	/**
	 * 重写分页查询逻辑
	 */
	public Page<Role> findPage(Page<Role> page, Wrapper<Role> wrapper) throws BusinessException;
	
	public Role getRoleByEName(String eName) throws BusinessException;
}