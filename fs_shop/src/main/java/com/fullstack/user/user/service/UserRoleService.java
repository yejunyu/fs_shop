package com.fullstack.user.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.user.entity.UserRole;

/**
 * 用户-角色
 * @author chay
 * @version 2017-5-12
 */
@Service
public interface UserRoleService<T> extends BaseService<UserRole> {
	/**
	 * 根据用户id修改对应的角色关联
	 * @param userId
	 * @param roleIds
	 * @return
	 * @throws BusinessException
	 */
	public boolean editByUserId(Integer userId,String[] roleIds) throws BusinessException;
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public List<UserRole> selectByUserId(Integer userId) throws BusinessException;
	/**
	 * 
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	public List<UserRole> selectByRoleId(Integer roleId) throws BusinessException;
	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	public boolean del(Integer userId,Integer roleId) throws BusinessException;
	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	public boolean batchDelByRoleId(Integer roleId,String[] userIds) throws BusinessException;
}