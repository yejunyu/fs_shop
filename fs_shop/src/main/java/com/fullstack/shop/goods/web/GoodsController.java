package com.fullstack.shop.goods.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.goods.entity.Goods;

@RestController  
@RequestMapping("/goods")  
public class GoodsController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Goods goods) throws BusinessException {
		Page<Goods> page = RequestUtils.getPage(request);
		EntityWrapper<Goods> e = this.entityInit(goods);
		page = goodsService.findPage(page,e);
        return this.retResult(page);
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
