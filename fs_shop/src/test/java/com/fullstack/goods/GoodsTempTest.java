package com.fullstack.goods;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsTempService;


public class GoodsTempTest extends AppTest {
	
	@Autowired  
    private GoodsTempService<GoodsTemp> service;  
	
	/**
	 * 增
	 * @throws BusinessException 
	 */
	@Test  
    public void insert() throws BusinessException{
		String fieldsets = "[{\"name\":\"长\",\"type\":\"11\"},{\"name\":\"宽\",\"type\":\"12\"},{\"name\":\"高\",\"type\":\"12\"}]";
		GoodsTemp entity = new GoodsTemp();
    	entity.setName("水果");
    	entity.setFieldset(JSONArray.parseArray(fieldsets).toJSONString());
    	service.create(entity);
    	System.out.println(entity.getId());
    }
	
	/**
	 * 列表数据
	 * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<GoodsTemp> page = new Page<GoodsTemp>();
    	page.setCurrent(-1);
    	EntityWrapper<GoodsTemp> ew=new EntityWrapper<GoodsTemp>();
    	ew.setEntity(new GoodsTemp());
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(GoodsTemp entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
