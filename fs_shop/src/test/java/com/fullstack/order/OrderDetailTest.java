package com.fullstack.order;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderDetailService;


public class OrderDetailTest extends AppTest{
	
	@Autowired  
    private OrderDetailService<OrderDetail> service;  
//	@Autowired  
//    private GoodsService<Goods> goodsService;  
      
    /**
     * 
     * @throws BusinessException 
	 */
	@Test  
    public void getByOrderCondition() throws BusinessException{  
		Order order = new Order();
		order.setDeliveryDate(DateUtils.getYesterdayDate(DateUtils.parsePatterns[1]));
		order.setStatus(Order.STATUS_END);
		List<OrderDetail> list = service.getByOrderCondition(order);
		for(OrderDetail detail:list){
			System.out.println(detail);
		}
    } 
	
}
