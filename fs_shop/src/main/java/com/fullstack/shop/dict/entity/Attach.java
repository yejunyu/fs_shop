package com.fullstack.shop.dict.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstack.common.persistence.DataEntity;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_attach")
public class Attach extends DataEntity<Attach> {
	
	private static final long serialVersionUID = 1L;
	@TableField("parent_id")
	private Integer parentId;	// 来源id
	private String name;		// 名称
	private String path;		// 描述
	private byte[] bytes;		// 图片转二进制存储
	@TableField("first_show")
	private String firstShow; 	// 优先展示（0：正常；1：优先）
	
	@TableField(exist=false)
	private String remarks;	// 备注
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name："+this.name);
		return str.toString();
	}
	
	public Attach() {
		super();
	}

	public Attach(Integer id){
		super(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonIgnore
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getFirstShow() {
		return firstShow;
	}

	public void setFirstShow(String firstShow) {
		this.firstShow = firstShow;
	}
	
	
	
}