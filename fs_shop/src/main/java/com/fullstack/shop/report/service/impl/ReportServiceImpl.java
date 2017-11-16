package com.fullstack.shop.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.CommonUtils;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.common.utils.MathUtils;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDetailService;
import com.fullstack.shop.order.service.OrderService;
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
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired  
    private OrderService<Order> orderService;
	@Autowired  
    private OrderDetailService<OrderDetail> orderDetailService;
	
	
	@Override
	public Page<Report> findPage(Page<Report> page, Wrapper<Report> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public void generateReportByDate(String date) throws BusinessException {
		Order order = new Order();
		order.setDeliveryDate(date);
		order.setStatus(Order.STATUS_END);
		//0.删除该天的全部报表
		this.delByCountDate(date);
		//1.日销售总额报表
		this.generateReportAllOfDay(date,order);
		//2.日销售单个商品总额报表
		this.generateReportGoodsOfDay(date,order);
	}
	
	/**
	 * 根据日期生成 日销售总额报表
	 * @param date
	 * @throws BusinessException
	 */
	private void generateReportAllOfDay(String date,Order order) throws BusinessException{
		double total = orderService.getSumTotalByCondition(order);
		Report report = new Report();
		report.setType(Report.TYPE_ALL_OF_DAY);
		report.setCountDate(date);
		report.setTotal(total+"");
		super.create(report);
	}
	/**
	 * 日销售单个商品总额报表
	 * @param date
	 * @throws BusinessException
	 */
	private void generateReportGoodsOfDay(String date,Order order) throws BusinessException{
		List<OrderDetail> list = orderDetailService.getByOrderCondition(order);
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(OrderDetail detail : list){
			Integer key = detail.getGoodsId();
			if(map.containsKey(key)){
				String resOld = map.get(key);
				String resNew = MathUtils.multip2(detail.getCount()+"", detail.getPrice()).toString();
				map.put(key, MathUtils.plus2(resOld, resNew).toString());
			}else{
				map.put(key, MathUtils.multip2(detail.getCount()+"", detail.getPrice()).toString());
			}
		}
		Report report;
		for(Integer key : map.keySet()){
			report = new Report();
			report.setCountDate(date);
			report.setType(Report.TYPE_GOODS_OF_DAY);
			report.setGoodsId(key);
			report.setTotal(map.get(key));
			
			super.create(report);
		}
	}

	@Override
	public void delByCountDate(String countDate) throws BusinessException {
		reportDao.delByCountDate(countDate);
	}

	@Override
	public Map<String,List<String>> groupByYearMonth() throws BusinessException {
		List<Map<String, String>> list = reportDao.groupByYearMonth();
		Map<String,List<String>> res = new HashMap<String,List<String>>();
		List<String> resVal;
		for(Map<String, String> map : list){
			String resKey = map.get("year");
			String month = map.get("month");
			if(res.containsKey(resKey)){
				resVal = res.get(resKey);
				resVal.add(month);
				res.put(resKey, resVal);
			}else{
				resVal = new ArrayList<String>();
				resVal.add(month);
				res.put(resKey, resVal);
			}
		}
		return res;
	}

	@Override
	public Map<String, Object> getDataGroupByCycle(String timeCycle, String year, String month) throws BusinessException {
		Report report = new Report();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int defaultX = 0;	//x轴默认数据
		if(Report.CYCLE_YEAR.equals(timeCycle)){
			report.setCountDate(null);
			list = reportDao.getDataGroupByYear(report);
		}else if(Report.CYCLE_MONTH.equals(timeCycle)){
			defaultX=12;
			report.setCountDate(year);
			list = reportDao.getDataGroupByMonth(report);
		}else if(Report.CYCLE_DAY.equals(timeCycle)){
			report.setCountDate(year+"-"+month);
			defaultX=DateUtils.getDays(year+"-"+month);
			list = reportDao.getDataGroupByDay(report);
		}
		Map<String, String> res = new HashMap<String,String>();
		List<String> listX = new ArrayList<String>();
		List<Double> listY = new ArrayList<Double>();
		for(Map<String, Object> map:list){
			res.put(map.get("key").toString(),	map.get("val").toString());
		}
		
		//处理返回前端报表数据，尽量避免前端做过多的处理
		if(Report.CYCLE_YEAR.equals(timeCycle)){
			for(String k:res.keySet()){
				listX.add(k);
				listY.add(MathUtils.parseDouble(res.get(k)));
			}
		}else{
			for(int i=1;i<=defaultX;i++){
				listX.add(i+"");
				listY.add(MathUtils.parseDouble(res.get(CommonUtils.numberPrefixZeroToString(1, i)+"")));
			}
		}
		
		Map<String, Object> resMap = new HashMap<String,Object>();
		resMap.put("x", listX);
		resMap.put("y", listY);
		return resMap;
	}
	
}