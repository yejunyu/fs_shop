package com.fullstack.user.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.user.entity.User;

/**
 * 用户
 * @author chay
 * @version 2017-5-12
 */
@Service
public interface UserService<T> extends BaseService<User> {
	/**
	 * 创建用户
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public boolean createUser(User user,String[] roleIds) throws BusinessException;
	/**
	 * 用户登录
	 * @param loginName
	 * @param password
	 * @return
	 * @throws BusinessException
	 */
	public User login(String loginName,String password) throws BusinessException;
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public User updateInfo(User user) throws BusinessException;
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public User updatePassword(String loginName,String password,String newPassword) throws BusinessException;
	/**
	 * 根据用户名重置密码
	 * @param loginName
	 * @return
	 * @throws BusinessException
	 */
	public User resetPassword(String loginName) throws BusinessException;
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public boolean delUser(Integer id) throws BusinessException;
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */
	public boolean batchDel(String[] ids) throws BusinessException;
	/**
	 * 编辑状态
	 * @param id
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	public boolean editStatus(Integer id,int status) throws BusinessException;
	/**
	 * 修改用户数据
	 * @param user
	 * @param roleIds
	 * @return
	 * @throws BusinessException
	 */
	public boolean editById(User user,String[] roleIds) throws BusinessException;
	/**
	 * 
	 */
	public Page<User> findPage(Page<User> page, Wrapper<User> wrapper) throws BusinessException;
	/**
	 * 根据角色获取用户列表
	 * @param roleName
	 * @return
	 * @throws BusinessException
	 */
	public List<User> getUsersByRoleEName(String eName) throws BusinessException;
}