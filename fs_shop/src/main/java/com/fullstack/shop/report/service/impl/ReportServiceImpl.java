package com.fullstack.shop.report.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.shop.report.dao.ReportDao;
import com.fullstack.shop.report.entity.Report;
import com.fullstack.shop.report.service.ReportService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class ReportServiceImpl extends BaseServiceImpl<ReportDao, Report> implements ReportService<Report>{
	
	@Override
	public Page<Report> findPage(Page<Report> page, Wrapper<Report> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}

	@Override
	public void generateReportByDate(String date) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
}