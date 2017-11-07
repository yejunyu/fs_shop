package com.fullstack.dict;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.dict.entity.Dict;
import com.fullstack.shop.dict.service.DictService;


public class DictTest extends AppTest{
	
	@Autowired  
    private DictService<Dict> service;  
      
    @Test  
    public void testInsert() throws BusinessException{  
    	for(int i=1;i<=1;i++){
    		Dict entity = new Dict();  
        	entity.setType("junit");
        	entity.setName("junit"+i);
        	entity.setSort(i);
        	entity.preInsert();
            service.insert(entity);  
            System.out.println("插入信息 name-->"+entity.getName());
            System.out.println("插入信息 id-->"+entity.getId());
    	}
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Dict> page = new Page<Dict>();
    	page.setCurrent(-1);
//    	page.setSize();
    	
    	EntityWrapper<Dict> ew=new EntityWrapper<Dict>();
    	ew.setEntity(new Dict());
    	ew.where("name like {0}","%"+"junit"+"%");
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Dict entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
