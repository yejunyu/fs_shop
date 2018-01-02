package com.fullstack.user.dict.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.persistence.DataEntity;

/**
 * 数据字典
 * @author chay
 * @version 2017-5-9
 */
@TableName("dict")
public class Dict extends DataEntity<Dict> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String type;		// 类型
	private Integer sort;		// 最小值
	
	
	@TableField(exist=false)
	private Integer createBy;	// 创建者
	@TableField(exist=false)
	private Date createDate;	// 创建日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name："+this.name);
		str.append(" type："+this.type);
		str.append(" sort："+this.sort);
		return str.toString();
	}
	
	public Dict() throws BusinessException {
		super();
	}

	public Dict(Integer id){
		super(id);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}