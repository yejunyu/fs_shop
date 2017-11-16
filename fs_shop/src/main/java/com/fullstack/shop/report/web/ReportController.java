package com.fullstack.shop.report.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.report.entity.Report;

@RestController  
@RequestMapping("/report")  
public class ReportController extends ServiceController {
	
	/**
	 * 根据年月日分组获取对应的数据
	 * 用于筛选报表时的数据维度的选择
	 * @param request
	 * @param report
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("groupByYearMonthDay")
    public JSONObject groupByYearMonthDay(HttpServletRequest request,Report report) throws BusinessException {
        return this.retResult(reportService.groupByYearMonthDay());
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getDataGroupByCycle")
    public JSONObject getDataGroupByCycle(HttpServletRequest request,Report report) throws BusinessException {
		String timeCycle = RequestUtils.getParameter(request, "timeCycle");
		String year = RequestUtils.getParameter(request, "year");
		String month = RequestUtils.getParameter(request, "month");
        return this.retResult(reportService.getDataGroupByCycle(timeCycle, year, month));
    }
	
	/**
	 * 
	 * @param request
	 * @param report
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getDataGroupByGoodsId")
    public JSONObject getDataGroupByGoodsId(HttpServletRequest request,Report report) throws BusinessException {
		String timeCycle = RequestUtils.getParameter(request, "timeCycle");
		String year = RequestUtils.getParameter(request, "year");
		String month = RequestUtils.getParameter(request, "month");
        return this.retResult(reportService.getDataGroupByGoodsId(timeCycle, year, month));
    }
	
}
