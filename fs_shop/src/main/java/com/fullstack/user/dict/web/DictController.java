package com.fullstack.user.dict.web;

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
import com.fullstack.user.dict.entity.Dict;

@RestController  
@RequestMapping("/dict")  
public class DictController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Dict dict) throws BusinessException {
		Page<Dict> page = RequestUtils.getPage(request);
		EntityWrapper<Dict> e = this.entityInit(dict);
		page = dictService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 根据类型加载数据
	 * @param request
	 * @param dict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("loadByType")
    public JSONObject loadByType(HttpServletRequest request,Dict dict) throws BusinessException {
		EntityWrapper<Dict> e = this.entityInit(dict);
		List<Dict> list = dictService.selectList(e);
        return this.retResult(list);
    }
	/**
	 * 新增
	 * @param request
	 * @param Dict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createDict(HttpServletRequest request,Dict dict) throws BusinessException {
		dictService.create(dict);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param Dict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateDict(HttpServletRequest request,Dict dict) throws BusinessException {
		dictService.editById2(dict);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Dict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delDict(HttpServletRequest request,Dict dict) throws BusinessException {
		dictService.delById(dict);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Dict
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Dict dict) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			dictService.batchDel(dict,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	
}
