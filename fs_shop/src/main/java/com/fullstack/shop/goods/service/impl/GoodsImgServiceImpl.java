package com.fullstack.shop.goods.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.PropertiesUtil;
import com.fullstack.shop.goods.dao.GoodsImgDao;
import com.fullstack.shop.goods.entity.Goods;
import com.fullstack.shop.goods.entity.GoodsImg;
import com.fullstack.shop.goods.service.GoodsImgService;
import com.fullstack.shop.goods.service.GoodsService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class GoodsImgServiceImpl extends BaseServiceImpl<GoodsImgDao, GoodsImg> implements GoodsImgService<GoodsImg>{

	@Autowired  
	private GoodsService<Goods> goodsService;
	@Autowired  
	private GoodsImgDao goodsImgDao;
	
	@Override
	public boolean bindGoodsId(Integer id,Integer goodsId) throws BusinessException {
		//保证商品信息存在
		if(goodsId==null){
			throw new BusinessException(9003003);
		}else if(goodsService.getInfoById(goodsId)==null){
			throw new BusinessException(9003003);
		}
		GoodsImg orginGoodsImg = super.getInfoById(id);
		if(orginGoodsImg==null){
			throw new BusinessException(9003004);
		}
		orginGoodsImg.setGoodsId(goodsId);
		super.editById(orginGoodsImg);
		return true;
	}

	@Override
	public GoodsImg getLastGoodsImgByGoodsId(Integer goodsId) throws BusinessException {
		GoodsImg goodsImg = new GoodsImg();
		goodsImg.setGoodsId(goodsId);
		EntityWrapper<GoodsImg> wrapper = this.entityInit(goodsImg);
		wrapper.orderBy("id", false);
		return super.selectOne(wrapper);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public void delNullGoodsImg() throws BusinessException {
		List<GoodsImg> list = goodsImgDao.selectGoodsIdForNull();
		for(GoodsImg img:list){
			String filePath = PropertiesUtil.getGoodsImgBasePath()+img.getPath()+"/"+img.getName();
			File file=new File(filePath);
			if(file.exists()&&file.isFile()){
				file.delete();
				System.out.println("文件删除成功");
			}else{
				System.out.println("需要删除的文件不存在");
			}
			//反正都是后台定时执行，所以懒得专门写接口去删除，直接用公用的就好
			super.delById(img);
		}
	}
	
	
}