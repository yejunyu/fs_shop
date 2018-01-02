package com.fullstack.user.role.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.user.auth.entity.Auth;

/**
 * 角色-权限
 * @Roleor chay
 * @version 2017-5-12
 */
@TableName("u_role_auth")
public class RoleAuth extends DataEntity<RoleAuth> {
	
	private static final long serialVersionUID = 1L;
	@TableField("role_id")
	private Integer roleId;				// 角色id
	@TableField("auth_id")
	private Integer authId;				// 权限id
	
	@TableField(exist=false)
	private Integer id;		//
	@TableField(exist=false)
	private String remarks;	// 
	@TableField(exist=false)
	private Date delFlag;	// 
	
	@TableField(exist=false)
	private Role role;		//
	@TableField(exist=false)
	private Auth auth;		//
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("roleId："+this.roleId);
		str.append(" authId："+this.authId);
		return str.toString();
	}
	
	public RoleAuth() {
		super();
	}

	public RoleAuth(Integer id){
		super(id);
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}


}