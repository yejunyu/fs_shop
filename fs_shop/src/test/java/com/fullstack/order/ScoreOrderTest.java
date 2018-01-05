package com.fullstack.order;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.order.entity.ScoreOrder;
import com.fullstack.shop.order.service.ScoreOrderService;


public class ScoreOrderTest extends AppTest{
	
	@Autowired  
    private ScoreOrderService<ScoreOrder> service;  
      
	/**
	 * 
	 * @throws BusinessException
	 */
    @Test  
    public void insert() throws BusinessException{  
    	
    	ScoreOrder entity = new ScoreOrder();  
    	entity.setCount(1);
    	entity.setGoodsId(30);
    	entity.setOrderId(30);
    	
        service.createScoreOrder("oajP00JUxH3wKrgjiYt6s7nLT3S4",entity);
        System.out.println("插入信息 id-->"+entity.getId());
    }  
    
	
}
