package com.fullstack.user.role.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 角色
 * @Roleor chay
 * @version 2017-5-12
 */
@TableName("role")
public class Role extends DataEntity<Role> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 名称
	private String eName;				// 英文名称 用来做唯一标识，逻辑使用
	
	@TableField(exist=false)
	private Integer createBy;	// 创建者
	@TableField(exist=false)
	private Date createDate;	// 创建日期
	
	@TableField(exist=false)
	private List<RoleAuth> listRoleAuth;	//角色关联的权限列表
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name："+this.name);
		return str.toString();
	}
	
	public Role() {
		super();
	}

	public Role(Integer id){
		super(id);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleAuth> getListRoleAuth() {
		return listRoleAuth;
	}

	public void setListRoleAuth(List<RoleAuth> listRoleAuth) {
		this.listRoleAuth = listRoleAuth;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

}