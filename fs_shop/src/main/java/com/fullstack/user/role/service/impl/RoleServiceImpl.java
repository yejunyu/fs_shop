package com.fullstack.user.role.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.user.role.dao.RoleDao;
import com.fullstack.user.role.entity.Role;
import com.fullstack.user.role.entity.RoleAuth;
import com.fullstack.user.role.service.RoleAuthService;
import com.fullstack.user.role.service.RoleService;

/**
 * 角色
 * @Roleor chay
 * @version 2017-5-12
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService<Role>{

//	@Autowired
//	private RoleDao roleDao;
	@Autowired
	private RoleAuthService<RoleAuth> roleAuthService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean create(Role role, String[] authIds) throws BusinessException {
		if(StringUtils.isBlank(role.geteName())){
			throw new BusinessException(9001008);
		}else if(this.getRoleByEName(role.geteName())!=null){
			throw new BusinessException(9001007,role.geteName());
		}
		super.create(role);
		return roleAuthService.editByRoleId(role.getId(), authIds);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editById(Role role, String[] authIds) throws BusinessException {
		super.editById(role);
		return roleAuthService.editByRoleId(role.getId(), authIds);
	}

	@Override
	public Page<Role> findPage(Page<Role> page, Wrapper<Role> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		List<RoleAuth> list = null;
		for(Role role : page.getRecords()){
			list = roleAuthService.selectByRoleId(role.getId());
			role.setListRoleAuth(list);
			role.getExtraData().put("authStr", listRoleAuthToAuthStr(list));
		}
		return page;
	}
	
	/**
	 * 根据英文名获取角色信息
	 * @param eName
	 * @return
	 */
	@Override
	public Role getRoleByEName(String eName){
		EntityWrapper<Role> ew = new EntityWrapper<Role>();
		Role role = new Role();
		role.setDelFlag(null);
		role.seteName(eName);
    	ew.setEntity(role);
    	return super.selectOne(ew);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	private String[] listRoleAuthToAuthStr(List<RoleAuth> list){
		String[] str = new String[list.size()];
		for(int i=0;i<list.size();i++){
			str[i] = list.get(i).getAuth().getName();
		}
		return str;
	}

	
}