package com.fullstack.user.user.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.common.utils.UserUtils;

/**
 * 用户
 * @Useror chay
 * @version 2017-5-12
 */
@TableName("user")
public class User extends DataEntity<User> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 名称
	private String code;				// 编码（工号）
	@TableField("login_name")
	private String loginName;			// 登录名称
	private String password;			// 密码
	@TableField("contact_way")
	private String contactWay;			// 联系方式
	private String email;				// 邮箱
	private Integer status;				// 状态
	@TableField("last_login_time")
	private Date lastLoginTime;			// 最后登录时间
	
	
	@TableField(exist=false)
	private Integer createBy;	// 创建者
	@TableField(exist=false)
	private Date createDate;	// 创建日期
	
	@TableField(exist=false)
	private List<UserRole> listUserRole;	//用户对应的角色关联关系
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("id："+this.id);
		str.append(" name："+this.name);
		str.append(" code："+this.code);
		return str.toString();
	}
	
	public User() {
		super();
	}

	public User(String loginName,String password) throws BusinessException{
		this.loginName=loginName;
		this.password=UserUtils.passwordEncode(loginName, password);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserRole> getListUserRole() {
		return listUserRole;
	}

	public void setListUserRole(List<UserRole> listUserRole) {
		this.listUserRole = listUserRole;
	}

	/**
	 * 0默认 1冻结
	 */
	public static final Integer STATUS_FLAG_NORMAL = 0;	
	public static final Integer STATUS_FLAG_FREEZE = 1;
	
}