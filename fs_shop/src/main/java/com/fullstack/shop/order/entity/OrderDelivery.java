package com.fullstack.shop.order.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_order_delivery")
public class OrderDelivery extends DataEntity<OrderDelivery> {
	
	private static final long serialVersionUID = 1L;
	@TableField("order_id")
	private Integer orderId;		// 订单id
	@TableField("delivery_time")
	private String deliveryTime;		// 配送时间
	@TableField("delivery_address")
	private String deliveryAddress;		// 配送地址
	private String customer;	// 客户
	@TableField("contact_way")
	private String contactWay;		// 联系方式
	
	@TableField(exist=false)
	private Integer createBy;	// 创建者
	@TableField(exist=false)
	private Date createDate;	// 创建日期
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public OrderDelivery() {
		super();
	}

	public OrderDelivery(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	
}