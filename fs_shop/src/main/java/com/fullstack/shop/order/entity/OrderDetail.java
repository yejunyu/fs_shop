package com.fullstack.shop.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_order_detail")
public class OrderDetail extends DataEntity<OrderDetail> {
	
	private static final long serialVersionUID = 1L;
	@TableField("order_id")
	private Integer orderId;		// 订单
	@TableField("goods_id")
	private Integer goodsId;		// 商品
	private Integer count;		// 数量
	private String price;		// 价格 
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(Integer id){
		super(id);
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
}