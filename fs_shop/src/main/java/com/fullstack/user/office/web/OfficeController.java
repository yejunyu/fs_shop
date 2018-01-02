package com.fullstack.user.office.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.office.entity.Office;

@RestController  
@RequestMapping("/office")  
public class OfficeController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Office office) throws BusinessException {
		EntityWrapper<Office> e = this.entityInit(office);
		List<Office> list = officeService.selectList(e);;
        return this.retResult(list);
    }
	/**
	 * 新增
	 * @param request
	 * @param Office
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("createOffice")
    public JSONObject createOffice(HttpServletRequest request,Office office) throws BusinessException {
		officeService.create(office);
        return this.retResult();
    }
	/**
	 * 修改
	 * @param request
	 * @param Office
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateOffice")
    public JSONObject updateOffice(HttpServletRequest request,Office office) throws BusinessException {
		officeService.editById(office);
        return this.retResult();
    }
	/**
	 * 删除
	 * @param request
	 * @param Office
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("delOffice")
    public JSONObject delOffice(HttpServletRequest request,Office office) throws BusinessException {
		officeService.delById(office);
        return this.retResult();
    }
	/**
	 * 根据id获取详情
	 * @param request
	 * @param office
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,Office office) throws BusinessException {
		office = officeService.getInfoById(office.getId());
        return this.retResult(office);
    }
	
}
