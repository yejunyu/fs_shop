package com.fullstack.common.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Entity支持类
 * @author chay
 * @version 2017-4-17
 */
public abstract class DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	@TableId(value = "id",type = IdType.AUTO)
	protected Integer id;
	protected String remarks;	// 备注
	
	@TableField(exist=false)
	protected Integer createBy;	// 创建者
	@TableField(exist=false)
	protected Date createDate;	// 创建日期
	@TableField(exist=false)
	protected Integer updateBy;	// 更新者
	@TableField(exist=false)
	protected Date updateDate;	// 更新日期
	@TableField("del_flag")
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

	@TableField(exist=false)
	protected String fieldset;		// 自定义字段
	@TableField(exist=false)
	protected List<String> listFieldset = new ArrayList<String>(); 	// 自定义列表字段
	@TableField(exist=false)
	protected Map<String,Object> extraData = new HashMap<String,Object>(); 	// 额外追加属性
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(Integer id) {
		this();
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<String> getListFieldset() {
		return listFieldset;
	}

	public void setListFieldset(List<String> listFieldset) {
		this.listFieldset = listFieldset;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void setExtraData(Map<String, Object> extraData) {
		this.extraData = extraData;
	}

	public String getFieldset() {
		return fieldset;
	}

	public void setFieldset(String fieldset) {
		this.fieldset = fieldset;
	}

	public void preInsert(){
		if(this.createBy==null){
			this.createBy=-1;
		}
		if(this.updateBy==null){
			this.updateBy=-1;
		}
		this.createDate = new Date();
		this.updateDate = this.createDate;
	}

	public void preUpdate(){
		this.updateDate = new Date();
		if(this.updateBy==null){
			this.updateBy=-1;
		}
	}
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
