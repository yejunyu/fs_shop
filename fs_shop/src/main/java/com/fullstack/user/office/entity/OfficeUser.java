package com.fullstack.user.office.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.user.user.entity.User;

/**
 * 机构-用户
 * @Roleor chay
 * @version 2017-8-14
 */
@TableName("u_office_user")
public class OfficeUser extends DataEntity<OfficeUser> {
	
	private static final long serialVersionUID = 1L;
	@TableField("office_id")
	private Integer officeId;				// 机构id
	@TableField("user_id")
	private Integer userId;				// 权限id
	
	@TableField(exist=false)
	private Integer id;		//
	@TableField(exist=false)
	private String remarks;	// 
	@TableField(exist=false)
	private Date delFlag;	// 
	
	@TableField(exist=false)
	private Office office;		//
	@TableField(exist=false)
	private User user;		//
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("officeId："+this.officeId);
		str.append(" userId："+this.userId);
		return str.toString();
	}
	
	public OfficeUser() {
		super();
	}

	public OfficeUser(Integer id){
		super(id);
	}
	
	public OfficeUser(Integer officeId,Integer userId){
		this.officeId=officeId;
		this.userId=userId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}