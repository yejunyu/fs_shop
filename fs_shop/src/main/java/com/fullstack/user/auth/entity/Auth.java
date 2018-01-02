package com.fullstack.user.auth.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 权限
 * @author chay
 * @version 2017-5-12
 */
@TableName("u_auth")
public class Auth extends DataEntity<Auth> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 名称
	
	
	@TableField("update_by")
	protected Integer updateBy;	// 更新者
	@TableField("update_date")
	protected Date updateDate;	// 更新日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name："+this.name);
		return str.toString();
	}
	
	public Auth() {
		super();
	}

	public Auth(Integer id){
		super(id);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}