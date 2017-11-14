package com.fullstack.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.user.entity.MemberAddress;
import com.fullstack.shop.user.service.MemberAddressService;


public class MemberAddressTest extends AppTest{
	
	@Autowired  
    private MemberAddressService<MemberAddress> service;  
      
    @Test  
    public void insert() throws BusinessException{  
    	MemberAddress entity = new MemberAddress();  
    	entity.setName("junit01");
    	entity.setSex("先生");
    	entity.setPhone("1234567");
    	entity.setAddress("广东广州");
    	entity.setDetailAddress("珠江新城");
        service.create(entity);
        System.out.println(entity);
        entity = new MemberAddress();  
    	entity.setName("junit02");
    	entity.setSex("女士");
    	entity.setPhone("7654321");
    	entity.setAddress("广东广州");
    	entity.setDetailAddress("花城广场");
        service.create(entity);
        System.out.println(entity);
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<MemberAddress> page = new Page<MemberAddress>();
    	page.setCurrent(-1);
    	
    	EntityWrapper<MemberAddress> ew=new EntityWrapper<MemberAddress>();
    	ew.setEntity(new MemberAddress());
    	//ew.where("name like {0}","%"+"junit"+"%");
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(MemberAddress entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
