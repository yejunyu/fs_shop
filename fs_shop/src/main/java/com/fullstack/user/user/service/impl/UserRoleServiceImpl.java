package com.fullstack.user.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.user.user.dao.UserRoleDao;
import com.fullstack.user.user.entity.UserRole;
import com.fullstack.user.user.service.UserRoleService;

/**
 * 用户-角色
 * @UserRoleor chay
 * @version 2017-5-12
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, UserRole> implements UserRoleService<UserRole>{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editByUserId(Integer userId, String[] roleIds) throws BusinessException {
		if(userId == null){
			throw new BusinessException(9000006,"用户");
		}
		int res = 0;
		userRoleDao.delByUserId(userId);
		UserRole userRole = null;
		if(roleIds!=null && roleIds.length > 0){
			for(String roleId : roleIds){
				userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(Integer.parseInt(roleId));
				res = userRoleDao.insert(userRole);
			}
		}
		return res>0;
	}
	
	@Override
	public List<UserRole> selectByUserId(Integer userId) throws BusinessException {
		if(userId==null){
			throw new BusinessException(9000006,"用户");
		}
		return userRoleDao.selectByCondition(new UserRole(userId,null));
	}
	
	@Override
	public List<UserRole> selectByRoleId(Integer roleId) throws BusinessException {
		if(roleId==null){
			throw new BusinessException(9000006,"角色");
		}
		return userRoleDao.selectByCondition(new UserRole(null,roleId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean del(Integer userId, Integer roleId) throws BusinessException {
		return userRoleDao.del(new UserRole(userId, roleId))>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean batchDelByRoleId(Integer roleId, String[] userIds) throws BusinessException {
		if(roleId==null){
			throw new BusinessException(9000006,"角色");
		}
		if(userIds==null){
			throw new BusinessException(9000006,"用户");
		}
		int res = 0;
		for(String userId : userIds){
			res = userRoleDao.del(new UserRole(Integer.parseInt(userId), roleId));
		}
		return res>0;
	}
	
}