package com.fullstack.user.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.user.role.entity.Role;

/**
 * 用户-角色
 * @Roleor chay
 * @version 2017-5-12
 */
@TableName("u_user_role")
public class UserRole extends DataEntity<UserRole> {
	
	private static final long serialVersionUID = 1L;
	
	@TableField("user_id")
	private Integer userId;				// 用户id
	@TableField("role_id")
	private Integer roleId;				// 角色id
	
	@TableField(exist=false)
	private Integer id;		//
	@TableField(exist=false)
	private String remarks;	// 
	@TableField(exist=false)
	private Date delFlag;	// 
	
	@TableField(exist=false)
	private User user;	// 
	@TableField(exist=false)
	private Role role;	// 
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("roleId："+this.roleId);
		str.append("userId："+this.userId);
		return str.toString();
	}
	
	public UserRole() {
		super();
	}

	public UserRole(Integer id){
		super(id);
	}
	
	public UserRole(Integer userId,Integer roleId){
		this.userId = userId;
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}