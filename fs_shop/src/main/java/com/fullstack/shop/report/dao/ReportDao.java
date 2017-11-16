package com.fullstack.shop.report.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.report.entity.Report;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface ReportDao extends BaseMapper<Report> {
	/**
	 * 删除该天的全部报表
	 * @param countDate
	 */
	void delByCountDate(String countDate);
	
	List<Map<String,String>> groupByYearMonth();
	
	List<Map<String, Object>> getDataGroupByYear(Report report);
	
	List<Map<String, Object>> getDataGroupByMonth(Report report);
	
	List<Map<String, Object>> getDataGroupByDay(Report report);
}