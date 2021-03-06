package com.fullstack.shop.user.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.user.dao.MemberAddressDao;
import com.fullstack.shop.user.entity.MemberAddress;
import com.fullstack.shop.user.service.MemberAddressService;

/**
 * 
 *  chay
 * @version 2017-5-12
 */
@Service
public class MemberAddressServiceImpl extends BaseServiceImpl<MemberAddressDao, MemberAddress> implements MemberAddressService<MemberAddress>{

//	@Autowired
//	private MemberAddressDao memberAddressDao;
	
	
	@Override
	public Page<MemberAddress> findPage(Page<MemberAddress> page, Wrapper<MemberAddress> wrapper) throws BusinessException {
		wrapper.orderBy("update_date", false);
		page = super.findPage(page, wrapper);
		return page;
	}
	
}