package com.fullstack.shop.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.user.dao.MemberDao;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.service.MemberService;

/**
 * 用户
 * @Memberor chay
 * @version 2017-5-12
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberDao, Member> implements MemberService<Member>{

//	@Autowired
//	private MemberDao memberDao;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean createMember(Member member) throws BusinessException {
		return super.create(member);
	}
	
	
}