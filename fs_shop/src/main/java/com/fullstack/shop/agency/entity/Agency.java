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
	private Integer status;		// 状态
	private String cause;		// 代办驳回原因
	
	
	@TableField("create_by")
	protected Integer createBy;	// 创建者
	@TableField("create_date")
	protected Date createDate;	// 创建日期
	


	
	@TableField(exist=false)
	private String statusDis;
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getStatusDis() {
		if(this.status==Agency.STATUS_DEFAULT){
			this.statusDis = "待处理";
		}else if(this.status==Agency.STATUS_WAIT){
			this.statusDis = "处理中";
		}else if(this.status==Agency.STATUS_SEND){
			this.statusDis = "待验收";
		}else if(this.status==Agency.STATUS_END){
			this.statusDis = "验收完成";
		}else if(this.status==Agency.STATUS_REBUT){
			this.statusDis = "审核不通过";
		}else if(this.status==Agency.STATUS_CANCEL){
			this.statusDis = "已取消";
		}
		return statusDis;
	}

	
	
	
	public static int STATUS_DEFAULT = 0;	// 待处理
	public static int STATUS_WAIT = 1;		// 处理中
	public static int STATUS_SEND = 2;		// 待验收
	public static int STATUS_END = 3;		// 验收完成
	public static int STATUS_REBUT = 5;		// 审核不通过
	public static int STATUS_CANCEL = 6;	// 已取消
	
	
}