package com.fullstack.user.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CommonServiceUtils;
import com.fullstack.user.office.dao.OfficeUserDao;
import com.fullstack.user.office.entity.OfficeUser;
import com.fullstack.user.office.service.OfficeUserService;
import com.fullstack.user.user.entity.UserRole;
import com.fullstack.user.user.service.UserRoleService;

/**
 * 机构-用户
 * @Officeor chay
 * @version 2017-8-14
 */
@Service
public class OfficeUserServiceImpl extends BaseServiceImpl<OfficeUserDao, OfficeUser> implements OfficeUserService<OfficeUser>{

	@Autowired
	private OfficeUserDao officeUserDao;
	@Autowired
	private UserRoleService<UserRole> userRoleService;
	
	/**
	 * @throws BusinessException 
	 * 
	 */
	@Override
	public List<OfficeUser> getUsersByOfficeId(Integer officeId) throws BusinessException {
		List<OfficeUser> listRes = officeUserDao.selectByCondition(new OfficeUser(officeId,null));
		List<UserRole> list = null;
		for(OfficeUser o : listRes){
			list = userRoleService.selectByUserId(o.getUserId());
			o.getUser().setListUserRole(list);
			o.getUser().getExtraData().put("roleStr", CommonServiceUtils.listUserRoleToRoleStr(list));
		}
		return listRes;
	}
	
	
	
}