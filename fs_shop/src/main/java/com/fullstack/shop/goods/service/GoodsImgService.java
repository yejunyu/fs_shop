package com.fullstack.shop.goods.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.goods.entity.GoodsImg;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface GoodsImgService<T> extends BaseService<GoodsImg> {
	/**
	 * 图片绑定到对应的商品下面
	 * @param goodsImg
	 * @return
	 * @throws BusinessException
	 */
	public boolean bindGoodsId(Integer id,Integer goodsId) throws BusinessException;
	/**
	 * 根据商品id获取最后上传的那张图片
	 * @return
	 */
	public GoodsImg getLastGoodsImgByGoodsId(Integer goodsId) throws BusinessException;
	
	/**
	 * 删除没有绑定商品的图片
	 * @return
	 */
	public GoodsImg delNullGoodsImg();
}