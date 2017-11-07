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
import com.fullstack.shop.goods.entity.GoodsTemp;

@RestController  
@RequestMapping("/goodsTemp")  
public class GoodsTempController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		Page<GoodsTemp> page = RequestUtils.getPage(request);
		EntityWrapper<GoodsTemp> e = this.entityInit(goodsTemp);
		page = goodsTempService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param GoodsTemp
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createGoodsTemp(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		goodsTempService.create(goodsTemp);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param GoodsTemp
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateGoodsTemp(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		goodsTempService.editById(goodsTemp);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param GoodsTemp
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delGoodsTemp(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		goodsTempService.delById(goodsTemp);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param GoodsTemp
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			goodsTempService.batchDel(goodsTemp,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	/**
	 * 
	 * @param request
	 * @param GoodsTemp
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,GoodsTemp goodsTemp) throws BusinessException {
		goodsTemp = goodsTempService.getInfoById(goodsTemp.getId());
        return this.retResult(goodsTemp);
    }
}
