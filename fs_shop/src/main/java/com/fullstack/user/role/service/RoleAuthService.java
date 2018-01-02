package com.fullstack.user.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.role.entity.RoleAuth;

/**
 * 角色-权限
 * @author chay
 * @version 2017-5-12
 */
@Service
public interface RoleAuthService<T> extends BaseService<RoleAuth> {
	/**
	 * 根据角色id修改关联关系
	 * 当authIds为空的时候则删除该角色的全部权限
	 * @param roleId
	 * @param authIds
	 * @return
	 * @throws BusinessException
	 */
	public boolean editByRoleId(Integer roleId,String[] authIds) throws BusinessException;
	/**
	 * 根据角色id查询对应的关联列表数据
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	public List<RoleAuth> selectByRoleId(Integer roleId) throws BusinessException;
}