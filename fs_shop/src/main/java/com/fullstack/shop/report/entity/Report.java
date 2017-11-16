package com.fullstack.shop.report.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.persistence.DataEntity;

/**
 *  
 * @author chay
 * @version 2017-04-17
 */
@TableName("shop_report")
public class Report extends DataEntity<Report> {
	
	private static final long serialVersionUID = 1L;
	@TableField("count_date")
	private String countDate;		// 统计日期
	@TableField("goods_id")
	private Integer goodsId;		// 商品id
	private Integer type;		// 类型
	protected String total;	// 总计
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("  fieldset:"+this.fieldset);
		str.append("  listFieldset:"+this.listFieldset);
		str.append("  extraData:"+this.extraData);
		return str.toString();
	}
	
	public Report() {
		super();
	}

	public Report(Integer id){
		super(id);
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	
	
	
	
	
	/**
	 * 日销售总额报表
	 */
	public static final int TYPE_ALL_OF_DAY = 1;		//	销售总额
	/**
	 * 日销售单个商品总额报表
	 */
	public static final int TYPE_GOODS_OF_DAY = 2;		//	商品销售额
	
	
	public static final String CYCLE_YEAR = "year";
	public static final String CYCLE_MONTH = "month";
	public static final String CYCLE_DAY = "day";
	
}