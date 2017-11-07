package com.fullstack.report;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.report.entity.Report;
import com.fullstack.shop.report.service.ReportService;


public class ReportTest extends AppTest{
	
	@Autowired  
    private ReportService<Report> service;  
	@Autowired  
    private GoodsService<Goods> goodsService;  
      
    @Test  
    public void insert() throws BusinessException{  
    	Goods goods = goodsService.selectList(null).get(0);
    	
    	Report entity = new Report();  
    	entity.setCountDate(DateUtils.getDate());
    	entity.setGoodsId(goods.getId());
    	entity.setType(Report.TYPE_GOODS);
    	entity.setTotal("100");
        service.create(entity);  
        System.out.println("插入信息 id-->"+entity.getId());
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Report> page = new Page<Report>();
    	page.setCurrent(-1);
//    	page.setSize();
    	
    	EntityWrapper<Report> ew=new EntityWrapper<Report>();
    	ew.setEntity(new Report());
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Report entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
