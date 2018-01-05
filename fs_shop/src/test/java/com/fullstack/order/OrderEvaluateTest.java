package com.fullstack.order;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.order.entity.OrderEvaluate;
import com.fullstack.shop.order.service.OrderEvaluateService;


public class OrderEvaluateTest extends AppTest{
	
	@Autowired  
    private OrderEvaluateService<OrderEvaluate> service;  
      
    /**
     * 
     * @throws BusinessException 	
	 */
	@Test  
    public void insert() throws BusinessException{  
		OrderEvaluate t = new OrderEvaluate();
		t.setContent("skdljfkdsjdsl");
		t.setGoodsId(1);
		t.setOrderId(1);
		t.setServiceAttitude(1);
		t.setGoodsScore(2);
		service.create(t);
    } 
	
}
