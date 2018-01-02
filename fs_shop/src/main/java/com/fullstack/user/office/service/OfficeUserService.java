package com.fullstack.user.office.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.user.office.entity.OfficeUser;

/**
 * 机构-用户
 * @author chay
 * @version 2017-8-14
 */
@Service
public interface OfficeUserService<T> extends BaseService<OfficeUser> {
	/**
	 * 
	 * @param officeId
	 * @return
	 */
	public List<OfficeUser> getUsersByOfficeId(Integer officeId) throws BusinessException;
}