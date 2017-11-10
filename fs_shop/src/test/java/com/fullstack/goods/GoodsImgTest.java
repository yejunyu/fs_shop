package com.fullstack.goods;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstack.AppTest;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.shop.goods.entity.GoodsImg;
import com.fullstack.shop.goods.service.GoodsImgService;


public class GoodsImgTest extends AppTest {
	
	@Autowired  
    private GoodsImgService<GoodsImg> service;  
	
	/**
	 * 
	 * @throws BusinessException 
	 */
	@Test  
    public void getLastGoodsImgByGoodsId() throws BusinessException{
		GoodsImg goodsImg = service.getLastGoodsImgByGoodsId(2);
		System.out.println("id->"+goodsImg.getId());
    }
	
}
