package com.fullstack.shop.agency.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.agency.entity.Agency;

@RestController  
@RequestMapping("/agency")  
public class AgencyController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Agency agency) throws BusinessException {
		Page<Agency> page = RequestUtils.getPage(request);
		EntityWrapper<Agency> e = this.entityInit(agency);
		page = agencyService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param Agency
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createAgency(HttpServletRequest request,Agency agency) throws BusinessException {
		agencyService.create(agency);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param Agency
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateAgency(HttpServletRequest request,Agency agency) throws BusinessException {
		agencyService.editById(agency);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Agency
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delAgency(HttpServletRequest request,Agency agency) throws BusinessException {
		agencyService.delById(agency);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Agency
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Agency agency) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			agencyService.batchDel(agency,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	
}
