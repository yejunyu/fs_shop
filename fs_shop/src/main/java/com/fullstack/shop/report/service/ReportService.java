package com.fullstack.shop.report.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.report.entity.Report;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface ReportService<T> extends BaseService<Report> {
	/**
	 * 调用触发生成报表
	 * @throws BusinessException
	 */
	public void generateReportByDate(String date) throws BusinessException;
}