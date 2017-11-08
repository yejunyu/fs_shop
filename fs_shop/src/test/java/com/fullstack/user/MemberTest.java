package com.fullstack.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.user.entity.Member;
import com.fullstack.shop.user.service.MemberService;


public class MemberTest extends AppTest{
	
	@Autowired  
    private MemberService<Member> service;  
      
    @Test  
    public void insert() throws BusinessException{  
    	
    	Member entity = new Member();  
    	entity.setName("junit01");
    	entity.setContactWay("110");
    	entity.preInsert();
        service.insert(entity);  
        System.out.println("插入信息 name-->"+entity.getName());
        System.out.println("插入信息 id-->"+entity.getId());
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Member> page = new Page<Member>();
    	page.setCurrent(-1);
//    	page.setSize();
    	
    	EntityWrapper<Member> ew=new EntityWrapper<Member>();
    	ew.setEntity(new Member());
    	ew.where("name like {0}","%"+"junit"+"%");
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Member entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
