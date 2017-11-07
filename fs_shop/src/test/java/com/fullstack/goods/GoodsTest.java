package com.fullstack.goods;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.goods.service.GoodsTempService;


public class GoodsTest extends AppTest {
	
	@Autowired  
    private GoodsService<Goods> service;  
	
	@Autowired  
    private GoodsTempService<GoodsTemp> tempService;  
	
	/**
	 * 增
	 * @throws BusinessException 
	 */
	@Test  
    public void insert() throws BusinessException{
		GoodsTemp temp = tempService.selectList(null).get(0);
		
		String fieldsets = "{\"规格\":\"4x5x6\",\"长\":\"4\",\"宽\":\"5\",\"高\":\"6\"}";
		Goods entity = new Goods();
		entity.setTempId(temp.getId());
    	entity.setName("商品001");
    	entity.setType(1);
    	entity.setPrice("10.0");
    	entity.setMin(100);
    	entity.setMax(1000);
    	entity.setFieldset(JSONObject.parseObject(fieldsets).toJSONString());
    	service.create(entity);
    	System.out.println(entity.getId());
    }
	
	/**
	 * 列表数据
	 * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Goods> page = new Page<Goods>();
    	page.setCurrent(-1);
    	
    	EntityWrapper<Goods> ew=new EntityWrapper<Goods>();
    	ew.setEntity(new Goods());
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Goods gt:page.getRecords()){
    		System.out.println(gt);
    	}
    } 
}
