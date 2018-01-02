package com.fullstack.user.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CommonServiceUtils;
import com.fullstack.common.utils.UserUtils;
import com.fullstack.user.office.entity.OfficeUser;
import com.fullstack.user.office.service.OfficeUserService;
import com.fullstack.user.role.entity.Role;
import com.fullstack.user.role.service.RoleService;
import com.fullstack.user.user.dao.UserDao;
import com.fullstack.user.user.entity.User;
import com.fullstack.user.user.entity.UserRole;
import com.fullstack.user.user.service.UserRoleService;
import com.fullstack.user.user.service.UserService;

/**
 * 用户
 * @Useror chay
 * @version 2017-5-12
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService<User>{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleService<UserRole> userRoleService;
	@Autowired
	private OfficeUserService<OfficeUser> officeUserService;
	@Autowired  
	protected RoleService<Role> roleService;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createUser(User user,String[] roleIds) throws BusinessException {
		User oldUser = this.getByLoginName(user.getLoginName());
		if(oldUser != null){
			throw new BusinessException(9001001, user.getLoginName());
		}
		user.preInsert();
		user.setStatus(User.STATUS_FLAG_NORMAL);
		userDao.insert(user);
		if(user.getExtraData().get("officeId")==null){
			throw new BusinessException(9000006,"组织机构");
		}
		//填充机构信息
		if(StringUtils.isNotBlank(user.getExtraData().get("officeId").toString())){
			officeUserService.create(new OfficeUser(Integer.parseInt(user.getExtraData().get("officeId").toString()),user.getId()));
		}
//		if(StringUtils.isNotBlank(user.getExtraData().get("officeId")==null?"":user.getExtraData().get("officeId").toString())){
//			officeUserService.create(new OfficeUser(Integer.parseInt(user.getExtraData().get("officeId").toString()),user.getId()));
//		}
		return userRoleService.editByUserId(user.getId(), roleIds);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public User login(String loginName, String password) throws BusinessException {
		User user = this.getByLoginNameAndPassword(loginName,password);
		//用户冻结
		if(user==null){
			throw new BusinessException(9001003);
		}else if(User.STATUS_FLAG_FREEZE == user.getStatus()){
			throw new BusinessException(9001002, user.getName());
		}
		//修改最后登录时间
		this.updateLastLoginTime(user);
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public User updateInfo(User user) throws BusinessException {
		User currentUser = super.getInfoById(user.getId());//UserUtils.getCurrentUser();
		if(StringUtils.isNotBlank(user.getContactWay())){
			currentUser.setContactWay(user.getContactWay());
		}
		this.updateById(currentUser);
		return currentUser;
	}

	/**
	 * 修改密码
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public User updatePassword(String loginName, String password, String newPassword) throws BusinessException {
		User user = this.getByLoginNameAndPassword(loginName, password);
		if(user == null){
			throw new BusinessException(9001004);
		}
		if(UserUtils.passwordEncode(loginName, password).equals(UserUtils.passwordEncode(loginName, newPassword))){
			throw new BusinessException(9001005);
		}
		user.setPassword(UserUtils.passwordEncode(loginName, newPassword));
		userDao.updatePassword(user);
		return user;
	}
	
	
	/**
	 * 修改最后登录时间
	 * @param user
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public int updateLastLoginTime(User user){
		user.setLastLoginTime(new Date());
		return userDao.updateLastLoginTime(user);
	}
	/**
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws BusinessException
	 */
	public User getByLoginNameAndPassword(String loginName, String password) throws BusinessException{
		User user = new User(loginName,password);
		return userDao.getByLoginNameAndPassword(user);
	}
	/**
	 * 
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(String loginName){
		return userDao.getByLoginName(loginName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public User resetPassword(String loginName) throws BusinessException {
		User currentUser = this.getByLoginName(loginName);
		//随机生成8位数的密码
		String password = UserUtils.randomPassword(8);
		currentUser.setPassword(UserUtils.passwordEncode(loginName, password));
		userDao.updatePassword(currentUser);
		//TODO 将重置之后的密码发送至用户邮箱password
//		currentUser.getEmail();
		
		return currentUser;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean delUser(Integer id) throws BusinessException {
		if(id==null){
			throw new BusinessException(9001007);
		}
		User user = new User();
		user.setId(id);
		user.setDelFlag(User.DEL_FLAG_DELETE);
		return userDao.updateById(user)>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editStatus(Integer id, int status) throws BusinessException {
		if(id==null){
			throw new BusinessException(9001007);
		}
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		return userDao.updateById(user)>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean batchDel(String[] ids) throws BusinessException {
		User user = new User();
		user.setDelFlag(User.DEL_FLAG_DELETE);
		int res = 0;
		for(String id : ids){
			user.setId(Integer.parseInt(id));
			res = userDao.updateById(user);
		}
		return res>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean editById(User user, String[] roleIds) throws BusinessException {
		super.editById(user);
		return userRoleService.editByUserId(user.getId(), roleIds);
	}
	
	@Override
	public Page<User> findPage(Page<User> page, Wrapper<User> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		List<UserRole> list = null;
		for(User u : page.getRecords()){
			list = userRoleService.selectByUserId(u.getId());
			u.setListUserRole(list);
			u.getExtraData().put("roleStr", CommonServiceUtils.listUserRoleToRoleStr(list));
		}
		return page;
	}

	@Override
	public List<User> getUsersByRoleEName(String eName) throws BusinessException {
		if(StringUtils.isBlank(eName)){
			throw new BusinessException(9000006,"角色英文名");
		}
		Role role = roleService.getRoleByEName(eName);
		if(role==null){
			throw new BusinessException(9001009,"角色不存在");
		}
		List<UserRole> list = userRoleService.selectByRoleId(role.getId());
		List<User> res = new ArrayList<User>();
		for(UserRole ur :list){
			res.add(ur.getUser());
		}
		return res;
	}
	
}