package com.fullstack.shop.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 会员
 * @Useror chay
 * @version 2017-11-7
 */
@TableName("shop_member")
public class Member extends DataEntity<Member> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 名称
	@TableField("wx_id")
	private String wxId;				// 微信id
	@TableField("login_name")
	private String loginName;			// 登录名称
	private String password;			// 密码
	@TableField("contact_way")
	private String contactWay;			// 联系方式
	private Integer score;				// 积分
	@TableField("credit_score")
	private String creditScore;			// 信用积分
	@TableField("last_login_time")
	private Date lastLoginTime;			// 最后登录时间
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("id："+this.id);
		str.append(" name："+this.name);
		str.append(" score："+this.score);
		return str.toString();
	}
	
	public Member() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	
	

}