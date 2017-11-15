package com.fullstack.shop.order.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 订单
 * @author chay
 * @version 2017-11-7
 */
@TableName("shop_order")
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 订单号
	@TableField("table_no")
	private String tableNo;		// 台号
	private Integer persons;		// 人数
	@TableField("pay_method")
	protected Integer payMethod;	// 支付方式
	@TableField("order_time")
	private String orderTime;		// 下单时间
	@TableField("delivery_date")
	private String deliveryDate;		// 配送时间
	private Integer status;		// 状态
	private String total;		// 总计
	

	@TableField("create_by")
	private Integer createBy;	// 创建者
	@TableField("create_date")
	private Date createDate;	// 创建日期
	@TableField("update_by")
	private Integer updateBy;	// 更新者
	@TableField("update_date")
	private Date updateDate;	// 更新日期
	
	@TableField(exist=false)
	private String statusDis;
	@TableField(exist=false)
	private OrderDelivery orderDelivery;
	@TableField(exist=false)
	private List<OrderDetail> listOrderDetail;
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public Order() {
		super();
	}

	public Order(Integer id){
		super(id);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 如果没有填写配送时间，则下单时间为配送时间，即尽快配送
	 * @return
	 */
	public String getDeliveryDate() {
		if(StringUtils.isBlank(this.deliveryDate)){
			this.deliveryDate = this.orderTime;
		}
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public OrderDelivery getOrderDelivery() {
		return orderDelivery;
	}

	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}

	public List<OrderDetail> getListOrderDetail() {
		return listOrderDetail;
	}

	public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
		this.listOrderDetail = listOrderDetail;
	}

	public String getStatusDis() {
		if(this.status==Order.STATUS_DEFAULT){
			this.statusDis = "待处理";
		}else if(this.status==Order.STATUS_WAIT){
			this.statusDis = "处理中";
		}else if(this.status==Order.STATUS_SEND){
			this.statusDis = "配送中";
		}else if(this.status==Order.STATUS_END){
			this.statusDis = "已完成";
		}else if(this.status==Order.STATUS_RETURN){
			this.statusDis = "退款中";
		}else if(this.status==Order.STATUS_REBUT){
			this.statusDis = "退款中";
		}else if(this.status==Order.STATUS_CANCEL){
			this.statusDis = "已取消";
		}
		return statusDis;
	}

//	public void setStatusDis(String statusDis) {
//		this.statusDis = statusDis;
//	}










	public static int STATUS_DEFAULT = 0;	// 待处理
	public static int STATUS_WAIT = 1;		// 处理中
	public static int STATUS_SEND = 2;		// 配送中
	public static int STATUS_END = 3;		// 已完成
	public static int STATUS_RETURN = 4;	// 退款中
	public static int STATUS_REBUT = 5;		// 已退款
	public static int STATUS_CANCEL = 6;	// 已取消
	
}