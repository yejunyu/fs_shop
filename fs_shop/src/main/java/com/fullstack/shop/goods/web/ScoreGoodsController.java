package com.fullstack.shop.goods.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.goods.entity.Goods;

@RestController  
@RequestMapping("/scoreGoods")  
public class ScoreGoodsController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Goods goods) throws BusinessException {
		goods.setType(Goods.TYPE_SCORE);
		List<Goods> list = goodsService.selByCondition(goods);
        return this.retResult(list);
    }
	/**
	 * 新增
	 * @param request
	 * @param Goods
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createGoods(HttpServletRequest request,Goods goods) throws BusinessException {
		goods.setType(Goods.TYPE_SCORE);
		goodsService.create(goods);
		
		if(RequestUtils.getInteger(request, "imgId")!=null){
			goodsImgService.bindGoodsId(RequestUtils.getInteger(request, "imgId"),goods.getId());
		}
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param Goods
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateGoods(HttpServletRequest request,Goods goods) throws BusinessException {
		goodsService.editById(goods);
		
		if(RequestUtils.getInteger(request, "imgId")!=null){
			goodsImgService.bindGoodsId(RequestUtils.getInteger(request, "imgId"),goods.getId());
		}
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Goods
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delGoods(HttpServletRequest request,Goods goods) throws BusinessException {
		goodsService.delById(goods);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Goods
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Goods goods) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			goodsService.batchDel(goods,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	/**
	 * 
	 * @param request
	 * @param Goods
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,Goods goods) throws BusinessException {
		goods = goodsService.getInfoById(goods.getId());
        return this.retResult(goods);
    }
}
