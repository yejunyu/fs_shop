package com.fullstack.shop.sys.service;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.sys.entity.Attach;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
public interface AttachService<T> extends BaseService<Attach> {
	/**
	 * 图片绑定到对应的商品下面
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	public boolean bindParentId(Integer id,Integer parentId) throws BusinessException;
	/**
	 * 根据商品id获取最后上传的那张图片
	 * @return
	 */
	public Attach getLastAttachByParentId(Integer parentId) throws BusinessException;
	
	/**
	 * 删除没有绑定商品的图片
	 * @return
	 */
	public void delNullAttach() throws BusinessException;
}