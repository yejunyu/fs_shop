package com.fullstack.agency;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.agency.entity.Agency;
import com.fullstack.shop.agency.service.AgencyService;


public class AgencyTest extends AppTest{
	
	@Autowired  
    private AgencyService<Agency> service;  
      
    @Test  
    public void insert() throws BusinessException{  
		Agency entity = new Agency();  
		entity.setContent("daiban002");
		entity.setAddress("地址002");
    	
    	entity.preInsert();
        service.insert(entity);  
        System.out.println("插入信息 id-->"+entity.getId());
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Agency> page = new Page<Agency>();
    	page.setCurrent(-1);
//    	page.setSize();
    	
    	EntityWrapper<Agency> ew=new EntityWrapper<Agency>();
    	ew.setEntity(new Agency());
    	ew.where("name like {0}","%"+"junit"+"%");
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Agency entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
