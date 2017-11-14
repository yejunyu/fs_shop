package com.fullstack.shop.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 用户地址
 * @Useror chay
 * @version 2017-11-7
 */
@TableName("shop_member_address")
public class MemberAddress extends DataEntity<MemberAddress> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 名称
	private String sex;					// 性别
	private String phone;				// 手机号码	
	private String address;				// 地址
	@TableField("detail_address")
	private String detailAddress;		// 详细地址
	@TableField("create_by")
	private Integer createBy;			// 创建人
	@TableField("create_date")
	private Date createDate;			// 创建时间
	@TableField("default_flag")
	private Integer defaultFlag;		// 是否默认
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("id："+this.id);
		str.append(" name："+this.name);
		return str.toString();
	}
	
	public MemberAddress() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

}