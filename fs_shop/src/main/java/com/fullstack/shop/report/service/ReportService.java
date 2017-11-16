package com.fullstack.shop.report.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 
	 * @param countDate
	 * @throws BusinessException
	 */
	public void delByCountDate(String countDate) throws BusinessException;
	/**
	 * 
	 * @return	key-year val-months
	 * @throws BusinessException
	 */
	public Map<String,Object> groupByYearMonthDay() throws BusinessException;
	
	public Map<String, Object> getDataGroupByCycle(String timeCycle,String year,String month) throws BusinessException;
	
	public List<Report> getDataGroupByGoodsId(String timeCycle,String year,String month) throws BusinessException;
}