package com.fullstack.report;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.shop.report.entity.Report;
import com.fullstack.shop.report.service.ReportService;


public class ReportTest extends AppTest{
	
	@Autowired  
    private ReportService<Report> service;  
      
    @Test  
    public void generateReportByDate() throws BusinessException{  
    	service.generateReportByDate(DateUtils.getYesterdayDate(DateUtils.parsePatterns[1]));
    }  
    
    
    @Test
    public void delByCountDate() throws BusinessException{  
    	service.delByCountDate(DateUtils.getYesterdayDate(DateUtils.parsePatterns[1]));
    }  
    
    @Test
    public void groupByYearMonth() throws BusinessException{  
    	Map<String,List<String>> res = service.groupByYearMonth();
    	for(String key:res.keySet()){
    		System.out.println(key+"->"+res.get(key));
    	}
    }  
    
//    /**
//	 * 列表数据
//     * @throws BusinessException 
//	 */
//	@Test  
//    public void list() throws BusinessException{  
//    	Page<Report> page = new Page<Report>();
//    	page.setCurrent(-1);
////    	page.setSize();
//    	
//    	EntityWrapper<Report> ew=new EntityWrapper<Report>();
//    	ew.setEntity(new Report());
//    	
//    	page = service.findPage(page,ew);
//    	System.out.println(page);
//    	System.out.println("------------------");
//    	for(Report entity:page.getRecords()){
//    		System.out.println(entity);
//    	}
//    } 
	
}
