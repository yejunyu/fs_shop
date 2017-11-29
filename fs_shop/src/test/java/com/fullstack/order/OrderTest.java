package com.fullstack.order;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.service.GoodsService;
import com.fullstack.shop.order.entity.Order;
import com.fullstack.shop.order.entity.OrderDelivery;
import com.fullstack.shop.order.entity.OrderDetail;
import com.fullstack.shop.order.service.OrderService;


public class OrderTest extends AppTest{
	
	@Autowired  
    private OrderService<Order> service;  
	@Autowired  
    private GoodsService<Goods> goodsService;  
      
	/**
	 * 
	 * @throws BusinessException
	 */
    @Test  
    public void insert01() throws BusinessException{  
    	Goods goods = goodsService.selectList(null).get(0);
    	
    	Order entity = new Order();  
    	entity.setNumber(service.getOrderNumber());
    	entity.setTableNo("001");
    	entity.setPersons(10);
    	entity.setPayMethod(1+"");
    	entity.setOrderTime(DateUtils.getDate());
    	entity.setDeliveryDate(DateUtils.getDate());
    	entity.setTotal(1000+"");
    	
    	
    	OrderDelivery orderDelivery = new OrderDelivery();
    	orderDelivery.setDeliveryTime(DateUtils.getDate());
    	orderDelivery.setDeliveryAddress("广州图书馆");
    	orderDelivery.setCustomer("管理员");
    	orderDelivery.setContactWay("1234567");

    	List<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
    	for(int i=1;i<=2;i++){
    		OrderDetail detail = new OrderDetail();
    		detail.setCount(i);
    		detail.setGoodsId(goods.getId());
    		detail.setPrice(goods.getPrice());
    		
    		listOrderDetail.add(detail);
    	}
    	
    	entity.setOrderDelivery(orderDelivery);
    	entity.setListOrderDetail(listOrderDetail);
    	
        service.createOrder("s",1,entity);
        System.out.println("插入信息 id-->"+entity.getId());
    }  
    
    /**
	 * 列表数据
     * @throws BusinessException 
	 */
	@Test  
    public void list() throws BusinessException{  
    	Page<Order> page = new Page<Order>();
    	page.setCurrent(-1);
//    	page.setSize();
    	
    	EntityWrapper<Order> ew=new EntityWrapper<Order>();
    	ew.setEntity(new Order());
    	ew.where("name like {0}","%"+"junit"+"%");
    	
    	page = service.findPage(page,ew);
    	System.out.println(page);
    	System.out.println("------------------");
    	for(Order entity:page.getRecords()){
    		System.out.println(entity);
    	}
    } 
	
}
