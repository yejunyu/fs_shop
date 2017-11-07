package com.fullstack.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.Log;

/**
 * 控制器支持类
 * @author chay
 * @version 2017-4-17
 */
public abstract class BaseController {

	protected int success_code = 9000;
	
	protected String success_msg = "请求成功.";
	
	protected String success_create = "新增成功.";
	protected String success_update = "修改成功.";
	protected String success_del = "删除成功.";
	

	
	/**
	 * 返回统一格式数据，自定义状态和提示语
	 * @param code
	 * @param msg
	 * @param Object
	 * @return
	 */
	protected JSONObject retResult(String code,String msg,Object obj){
		JSONObject resJson = new JSONObject();
		resJson.put("code", code);
		resJson.put("msg", msg);
		resJson.put("result", obj);
		return resJson;
	}
	/**
	 * 返回统一格式数据，使用默认提示
	 * @param msg
	 * @param obj
	 * @return
	 */
	protected JSONObject retResult(String msg,Object obj){
		JSONObject resJson = new JSONObject();
		resJson.put("code", success_code);
		resJson.put("msg", msg);
		resJson.put("result", obj);
		return resJson;
	}
	/**
	 * 返回统一格式数据，使用默认状态和默认提示
	 * @param obj
	 * @return
	 */
	protected JSONObject retResult(Object obj){
		JSONObject resJson = new JSONObject();
		resJson.put("code", success_code);
		resJson.put("msg", success_msg);
		resJson.put("result", obj);
		return resJson;
	}
	protected JSONObject retResult(){
		JSONObject resJson = new JSONObject();
		resJson.put("code", success_code);
		resJson.put("msg", success_msg);
		resJson.put("result", null);
		return resJson;
	}
	/**
	 * 返回自定义提示语
	 * @param msg提示语
	 * @return
	 */
	protected JSONObject retResult(String msg){
		JSONObject resJson = new JSONObject();
		resJson.put("code", success_code);
		if(StringUtils.isNotBlank(msg)){
			resJson.put("msg", msg);
		}else{
			resJson.put("msg", success_msg);
		}
		resJson.put("result", msg);
		return resJson;
	}
	/**
	 * SpringMvc 统一的异常信息处理方法
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public JSONObject handExcpetion(
			HttpServletRequest request, Exception ex) {
		Log.error("系统业务异常：" + ex.getCause());
		JSONObject resJson = new JSONObject();
		resJson.put("result", null);
		if (ex instanceof BusinessException) {
			int code = ((BusinessException) ex).getCode();
			String message = ((BusinessException) ex).getMessage();
			resJson.put("code", code);
			resJson.put("msg", message);
			Log.error(String.format("#####%s 发生业务异常：%s-%s",
					request.getRequestURL(), code, message));
		} else {
			// 非业务异常，则为未知异常，以统一系统未知异常代码来做处理！！
			BusinessException be = new BusinessException(9000000);
			resJson.put("code", be.getCode());
			resJson.put("msg", be.getMessage());
			Log.error("##### 未知业务异常：" + ex.getMessage());
			ex.printStackTrace();
		}
		return resJson;
	}
	
	protected <T> EntityWrapper<T> entityInit(T t){
		EntityWrapper<T> e=new EntityWrapper<T>();
    	e.setEntity(t);
    	return e;
	}
	
}
