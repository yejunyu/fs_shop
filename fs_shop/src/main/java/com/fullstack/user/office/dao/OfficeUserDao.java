package com.fullstack.user.office.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.office.entity.OfficeUser;

/**
 * 机构-用户
 * @author chay
 * @version 2017-8-14
 */
public interface OfficeUserDao extends BaseMapper<OfficeUser> {
	/**
	 * 
	 * @param officeUser
	 * @return
	 */
	List<OfficeUser> selectByCondition(OfficeUser officeUser);
}