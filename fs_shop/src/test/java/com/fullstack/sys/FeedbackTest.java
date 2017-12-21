package com.fullstack.sys;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.sys.entity.Feedback;
import com.fullstack.shop.sys.service.FeedbackService;


public class FeedbackTest extends AppTest{
	
	@Autowired  
    private FeedbackService<Feedback> service;  
      
    @Test  
    public void insert() throws BusinessException{  
    	Feedback entity = new Feedback();
    	entity.setContent("junit-test");
    	service.create(entity);
    }  
    
	
}
