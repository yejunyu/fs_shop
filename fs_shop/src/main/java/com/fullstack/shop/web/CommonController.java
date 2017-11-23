package com.fullstack.shop.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.common.web.ServiceController;

@RestController  
@RequestMapping("/common")  
public class CommonController extends ServiceController {
	
	
	/**
	 * 根据当前时间返回可选择的派送时间列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getSendTimeList")
    public JSONObject getSendTimeList(HttpServletRequest request) throws BusinessException {
        return this.retResult(DateUtils.getSendTimeList());
    }
	
	
}
