package com.fullstack.user.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.user.role.dao.RoleAuthDao;
import com.fullstack.user.role.entity.RoleAuth;
import com.fullstack.user.role.service.RoleAuthService;

/**
 * 角色-权限
 * @RoleAuthor chay
 * @version 2017-5-12
 */
@Service
public class RoleAuthServiceImpl extends BaseServiceImpl<RoleAuthDao, RoleAuth> implements RoleAuthService<RoleAuth>{

	@Autowired
	private RoleAuthDao roleAuthDao;
	
	/**
	 * 根据角色id修改关联关系
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editByRoleId(Integer roleId, String[] authIds) throws BusinessException {
		if(roleId == null){
			throw new BusinessException(9000006,"角色");
		}
		int res = 0;
		roleAuthDao.delByRoleId(roleId);
		RoleAuth roleAuth = null;
		if(authIds!=null && authIds.length > 0){
			for(String authId : authIds){
				roleAuth = new RoleAuth();
				roleAuth.setRoleId(roleId);
				roleAuth.setAuthId(Integer.parseInt(authId));
				res = roleAuthDao.insert(roleAuth);
			}
		}
		return res>0;
	}

	@Override
	public List<RoleAuth> selectByRoleId(Integer roleId) throws BusinessException {
		if(roleId==null){
			throw new BusinessException(9000006,"角色");
		}
		return roleAuthDao.selectByRoleId(roleId);
	}
	
	
}