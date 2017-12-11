package com.fullstack.shop.dict.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.dict.entity.Attach;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface AttachDao extends BaseMapper<Attach> {
	/**
	 * 
	 * 查询goodsId为空的数据
	 * @return
	 */
	List<Attach> selectParentIdForNull(); 
}