package com.fullstack.shop.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.sys.dao.FeedbackDao;
import com.fullstack.shop.sys.entity.Feedback;
import com.fullstack.shop.sys.service.FeedbackService;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.service.MemberService;

/**
 * 
 * @author chay
 * @version 2017-5-9
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackDao, Feedback> implements FeedbackService<Feedback>{

	@Autowired  
    private MemberService<Member> memberService; 
	
	@Override
	public Page<Feedback> findPage(Page<Feedback> page, Wrapper<Feedback> wrapper) throws BusinessException {
		wrapper.orderBy("create_date", false);
		page = super.findPage(page, wrapper);
		for(Feedback entity:page.getRecords()){
			entity.getExtraData().put("createBy", memberService.getInfoById(entity.getCreateBy()));
		}
		return page;
	}
	

}