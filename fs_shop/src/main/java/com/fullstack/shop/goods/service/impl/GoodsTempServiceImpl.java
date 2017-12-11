package com.fullstack.shop.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.ImgUtils;
import com.fullstack.common.utils.PropertiesUtil;
import com.fullstack.shop.dict.entity.Attach;
import com.fullstack.shop.dict.service.AttachService;
import com.fullstack.shop.goods.dao.GoodsTempDao;
import com.fullstack.shop.goods.entity.GoodsTemp;
import com.fullstack.shop.goods.service.GoodsTempService;

/**
 * 商品类型Service
 * @author chay
 * @version 2017-04-17
 */
@Service
public class GoodsTempServiceImpl extends BaseServiceImpl<GoodsTempDao, GoodsTemp> implements GoodsTempService<GoodsTemp> {
	
	@Autowired  
	private AttachService<Attach> attachService;
	
	@Override
	public Page<GoodsTemp> findPage(Page<GoodsTemp> page, Wrapper<GoodsTemp> wrapper) throws BusinessException {
		page = super.findPage(page, wrapper);
		super.fieldsetUtils(page.getRecords());
		for(GoodsTemp entity : page.getRecords()){
			Attach attach = attachService.getLastAttachByParentId(entity.getId());
			if(attach!=null){
				entity.getExtraData().put(ImgUtils.IMG_KEY, 
						ImgUtils.commonPathUtils(PropertiesUtil.getAttachLoadPath(),attach.getPath(),attach.getName()));
			}
		}
		
		
		
		return page;
	}
	
	@Override
	public GoodsTemp getInfoById(int id) throws BusinessException {
		GoodsTemp GoodsTemp = super.getInfoById(id);
		super.fieldsetUtils(GoodsTemp);
		return GoodsTemp;
	}
}