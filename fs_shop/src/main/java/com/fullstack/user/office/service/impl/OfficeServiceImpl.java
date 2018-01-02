package com.fullstack.user.office.service.impl;

import org.springframework.stereotype.Service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.user.office.dao.OfficeDao;
import com.fullstack.user.office.entity.Office;
import com.fullstack.user.office.service.OfficeService;

/**
 * 机构
 * @Officeor chay
 * @version 2017-5-12
 */
@Service
public class OfficeServiceImpl extends BaseServiceImpl<OfficeDao, Office> implements OfficeService<Office>{
	
	@Override
	public boolean create(Office office) throws BusinessException {
		if(office.getParentId()==null){
			office.setParentId(-1);
		}
		return super.create(office);
	}
	
}