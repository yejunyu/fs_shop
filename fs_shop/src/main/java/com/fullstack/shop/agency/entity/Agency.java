package com.fullstack.shop.agency.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.persistence.DataEntity;

/**
 * 跑腿代办
 * @author chay
 * @version 2017-12-6
 */
@TableName("shop_agency")
public class Agency extends DataEntity<Agency> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private String address;		// 地址
	private String demand;		// 其他要求
	private Integer type;		// 类型
	@TableField("basic_cost")
	private String basicCost;	// 基础费用
	@TableField("service_cost")
	private String serviceCost;	// 成本（服务费）
	
	
	@TableField("create_by")
	protected Integer createBy;	// 创建者
	@TableField("create_date")
	protected Date createDate;	// 创建日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(" type："+this.type);
		return str.toString();
	}
	
	public Agency() throws BusinessException {
		super();
	}

	public Agency(Integer id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBasicCost() {
		return basicCost;
	}

	public void setBasicCost(String basicCost) {
		this.basicCost = basicCost;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	
}