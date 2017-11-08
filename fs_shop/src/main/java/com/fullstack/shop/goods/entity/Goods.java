package com.fullstack.shop.goods.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 商品Entity
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_goods")
public class Goods extends DataEntity<Goods> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String description;		// 描述
	private Integer type;		// 类型
	@TableField("temp_id")
	protected Integer tempId;	// 模板id
	private Integer score;		// 积分
	private String price;		// 价格
	private Integer min;		// 最小值
	private Integer max;		// 最大值
	private String fieldset;		// 自定义字段
	
	@TableField("update_by")
	protected Integer updateBy;	// 更新者
	@TableField("update_date")
	protected Date updateDate;	// 更新日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("name："+this.name);
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public Goods() {
		super();
	}

	public Goods(Integer id){
		super(id);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getTempId() {
		return tempId;
	}

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	
}