package com.fullstack.shop.order.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.shop.goods.entity.Goods;

/**
 * 订单
 * @author chay
 * @version 2017-11-7
 */
@TableName("shop_order_evaluate")
public class OrderEvaluate extends DataEntity<OrderEvaluate> {
	
	private static final long serialVersionUID = 1L;
	private String content;			// 内容
	@TableField("order_id")
	private Integer orderId;		// 订单id
	@TableField("goods_id")
	private Integer goodsId;		// 商品id
	@TableField("service_attitude")
	private Integer serviceAttitude;// 服务态度
	@TableField("goods_score")
	private Integer goodsScore;		// 商品评分（满意度）
	
	@TableField("create_by")
	private Integer createBy;	// 创建者
	@TableField("create_date")
	private Date createDate;	// 创建日期
	
	@TableField(exist=false)
	private Order order;
	@TableField(exist=false)
	private Goods goods;
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public OrderEvaluate() {
		super();
	}

	public OrderEvaluate(Integer id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getServiceAttitude() {
		return serviceAttitude;
	}

	public void setServiceAttitude(Integer serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}

	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	
	
}