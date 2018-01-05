package com.fullstack.shop.order.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.shop.goods.entity.Goods;

/**
 * 积分订单
 * @author chay
 * @version 2018-1-4
 */
@TableName("shop_score_order")
public class ScoreOrder extends DataEntity<ScoreOrder> {
	
	private static final long serialVersionUID = 1L;
	@TableField("order_id")
	private Integer orderId;		// 订单
	@TableField("goods_id")
	private Integer goodsId;		// 商品
	private Integer count;		// 数量
	private Integer status;		// 状态
	

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
	
	public ScoreOrder() {
		super();
	}

	public ScoreOrder(Integer id){
		super(id);
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getStatusDis() {
		if(this.status==ScoreOrder.STATUS_DEFAULT){
			this.statusDis = "待处理";
		}else if(this.status==ScoreOrder.STATUS_WAIT){
			this.statusDis = "处理中";
		}else if(this.status==ScoreOrder.STATUS_SEND){
			this.statusDis = "配送中";
		}else if(this.status==ScoreOrder.STATUS_END){
			this.statusDis = "已完成";
		}else if(this.status==ScoreOrder.STATUS_RETURN){
			this.statusDis = "退款中";
		}else if(this.status==ScoreOrder.STATUS_REBUT){
			this.statusDis = "已退款";
		}else if(this.status==ScoreOrder.STATUS_CANCEL){
			this.statusDis = "已取消";
		}
		return statusDis;
	}











	public static int STATUS_DEFAULT = 0;	// 待处理
	public static int STATUS_WAIT = 1;		// 处理中
	public static int STATUS_SEND = 2;		// 配送中
	public static int STATUS_END = 3;		// 已完成
	public static int STATUS_RETURN = 4;	// 退款中
	public static int STATUS_REBUT = 5;		// 已退款
	public static int STATUS_CANCEL = 6;	// 已取消
	
}