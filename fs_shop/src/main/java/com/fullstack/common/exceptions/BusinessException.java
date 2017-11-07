package com.fullstack.common.exceptions;

import java.text.MessageFormat;

/**
 * 自定义系统业务异常处理
 * @author chay
 * @version 2017-5-15
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = -1L;

	private int code;
	private String[] params;
	private String msg;

	public BusinessException() {
		super();
	}

	public BusinessException(int code) {
		this(code, "");
	}

	public BusinessException(int code, String... params) {
		this.params = params;
		this.code = code;
		this.params = params;

		// 获取资源代码对应描述
		this.msg = getMsgFromCfg(code, params);
	}

	/**
	 * 从配置文件中获取错误信息
	 * 
	 * @param errcode
	 * @param params
	 * @return
	 */
	public static String getMsgFromCfg(int errcode, Object[] params) {
		String message = ErrorResources.getInstance().getMessage(errcode+"");
		return params == null ? message : MessageFormat.format(message,params);
	}

	public int getCode() {
		return this.code;
	}

	public String[] getParams() {
		return params;
	}

	public String getMessage() {
		return msg;
	}

}
