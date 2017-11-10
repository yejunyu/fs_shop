package com.fullstack.shop.goods.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.goods.entity.GoodsImg;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface GoodsImgDao extends BaseMapper<GoodsImg> {
	/**
	 * 
	 * 查询goodsId为空的数据
	 * @return
	 */
	List<GoodsImg> selectGoodsIdForNull(); 
}