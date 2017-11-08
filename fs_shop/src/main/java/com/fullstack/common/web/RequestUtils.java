package com.fullstack.common.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.CommonUtils;


/**
 * 客户端向服务器发出请求
 * 参数工具类
 * @author chay
 * @version 2017-5-19
 */
public class RequestUtils {

	/**
	 * 将字符类型转成数组类型
	 */
	public static JSONArray getJSONArray(HttpServletRequest request,String paramName) {
		String str = request.getParameter(paramName);
		if(StringUtils.isNotBlank(str)){
			return JSONArray.parseArray(str);
		}
		return null;
	}
	
	public static Integer getInteger(HttpServletRequest request,String paramName) {
		String str = request.getParameter(paramName);
		if(StringUtils.isNotBlank(str)){
			return Integer.parseInt(str);
		}
		return null;
	}
	
	public static <T> Page<T> getPage(HttpServletRequest request) throws BusinessException {
		Page<T> page = new Page<T>();
		if(RequestUtils.getInteger(request, "pageNum")!=null &&
				RequestUtils.getInteger(request, "pageSize")!=null ){
			page.setCurrent(RequestUtils.getInt(request, "pageNum"));
			page.setSize(RequestUtils.getInt(request, "pageSize"));
		}
		return page;
	}
	/**
	 * 防止中文乱码
	 * @param request
	 * @param name
	 * @return
	 * @throws BusinessException 
	 */
	public static String getParameter(HttpServletRequest request,String paramName) throws BusinessException {
		return CommonUtils.garbledTranslation(request.getParameter(paramName));
	}
	/**
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 * @throws BusinessException
	 */
	public static String[] getStrings(HttpServletRequest request,String paramName) throws BusinessException {
		return request.getParameterValues(paramName);
	}
	/**
	 * 将界面传过来的数据转成Int类型
	 * @param request
	 * @param name
	 * @return
	 * @throws BusinessException 
	 */
	public static int getInt(HttpServletRequest request,String name) throws BusinessException {
		String val = request.getParameter(name);
		if(StringUtils.isNotBlank(val)){
			try {
				return Integer.parseInt(val);
			} catch (Exception e) {
				throw new BusinessException(9000003,val);
			}
		}
		return 0;
	}
	
	@SuppressWarnings("deprecation")
	public static Date getDate(HttpServletRequest request,String name) throws BusinessException {
		String val = request.getParameter(name);
		if(StringUtils.isNotBlank(val)){
			try {
				return new Date(val);
			} catch (Exception e) {
				throw new BusinessException(9000004,val);
			}
		}
		return null;
	}
	
}
