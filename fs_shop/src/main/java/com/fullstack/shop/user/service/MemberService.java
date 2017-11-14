package com.fullstack.shop.user.service;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.user.entity.Member;

/**
 * 用户
 * @author chay
 * @version 2017-11-7
 */
@Service
public interface MemberService<T> extends BaseService<Member> {
	/**
	 * 创建用户
	 * @param member
	 * @return
	 * @throws BusinessException
	 */
	public boolean createMember(Member member) throws BusinessException;
	/**
	 * 统计数量
	 * @param member
	 * @return
	 * @throws BusinessException
	 */
	public int getCount(Member member) throws BusinessException;
	/**
	 * 根据唯一标识数据获取用户信息
	 * @return
	 * @throws BusinessException
	 */
	public Member getByWxId(String wxId) throws BusinessException;
}