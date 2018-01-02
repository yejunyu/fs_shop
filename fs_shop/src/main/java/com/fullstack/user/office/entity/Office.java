package com.fullstack.user.office.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 机构
 * @author chay
 * @version 2017-5-12
 */
@TableName("u_office")
public class Office extends DataEntity<Office> {
	
	private static final long serialVersionUID = 1L;
	@TableField("parent_id")
	private Integer parentId;		// 父级id
	private String name;			// 名称
	@TableField("master_id")
	private Integer masterId;		// 负责人

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
	
	public Office() {
		super();
	}

	public Office(Integer id){
		super(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	
}