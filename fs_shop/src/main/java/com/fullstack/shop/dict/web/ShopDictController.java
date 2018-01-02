package com.fullstack.shop.dict.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.dict.entity.ShopDict;

@RestController  
@RequestMapping("/shopDict")  
public class ShopDictController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,ShopDict ShopDict) throws BusinessException {
		Page<ShopDict> page = RequestUtils.getPage(request);
		EntityWrapper<ShopDict> e = this.entityInit(ShopDict);
		page = shopDictService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 根据类型加载数据
	 * @param request
	 * @param ShopDict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("loadByType")
    public JSONObject loadByType(HttpServletRequest request,ShopDict ShopDict) throws BusinessException {
		EntityWrapper<ShopDict> e = this.entityInit(ShopDict);
		List<ShopDict> list = shopDictService.selectList(e);
        return this.retResult(list);
    }
	/**
	 * 新增
	 * @param request
	 * @param ShopDict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createShopDict(HttpServletRequest request,ShopDict ShopDict) throws BusinessException {
		shopDictService.create(ShopDict);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param ShopDict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateShopDict(HttpServletRequest request,ShopDict ShopDict) throws BusinessException {
		shopDictService.editById2(ShopDict);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param ShopDict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delShopDict(HttpServletRequest request,ShopDict ShopDict) throws BusinessException {
		shopDictService.delById(ShopDict);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param ShopDict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,ShopDict shopDict) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			shopDictService.batchDel(shopDict,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	
}
