package com.fullstack.shop.sys.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.persistence.DataEntity;

/**
 * 意见反馈
 * @author chay
 * @version 2017-5-9
 */
@TableName("shop_feedback")
public class Feedback extends DataEntity<Feedback> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private String reply;		// 回复
	
	@TableField("create_by")
	protected Integer createBy;	
	@TableField("create_date")
	protected Date createDate;
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("content："+this.content);
		return str.toString();
	}
	
	public Feedback() throws BusinessException {
		super();
	}

	public Feedback(Integer id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	
	
}