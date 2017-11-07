package com.fullstack.shop.goods.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 商品类型Entity
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_goods_temp")
public class GoodsTemp extends DataEntity<GoodsTemp> {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 名称
	private String fieldset;	// 自定义字段
	
	@TableField("update_by")
	protected Integer updateBy;	// 更新者
	@TableField("update_date")
	protected Date updateDate;	// 更新日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name:"+this.name);
		str.append("->fieldset:"+this.fieldset);
		str.append("->listFieldset:"+this.listFieldset);
		return str.toString();
	}
	
	public GoodsTemp() {
		super();
	}

	public GoodsTemp(Integer id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}