package com.fullstack.common.exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.fullstack.common.utils.Log;

/**
 * 系统资源文件读取器
 * @author chay
 * @version 2017-5-15
 */
public class ErrorResources {

	private final static String MESSAGE_BUNDLE_NAME = "errors";

	private ResourceBundle res;

	// singleton
	protected static ErrorResources instance = new ErrorResources();

	public static ErrorResources getInstance() {
		return instance;
	}

	// 无参构造方法
	private ErrorResources() {
		loadResourceBundle();
	}

	public void loadResourceBundle() {
		res = ResourceBundle.getBundle(MESSAGE_BUNDLE_NAME);
		if (res == null) {
			Log.error("No resource bundle is found: " + MESSAGE_BUNDLE_NAME);
		}
	}

	public String getMessage(String key, String defVal) {
		if (StringUtils.isNotBlank(res.getString(key))) {
			return res.getString(key);
		} else {
			return defVal;
		}
	}

	public String getMessage(String key) {
		if (StringUtils.isNotBlank(res.getString(key))) {
			return res.getString(key);
		} else {
			return String.format("请在%s中定义异常信息%s", MESSAGE_BUNDLE_NAME, key);
		}
	}
	
	public String getFormateMessage(String key, String param){
		return this.getFormateMessage(key, new String[]{param});
	}

	public String getFormateMessage(String key, String[] params){
		String msg = getMessage(key);
		if(StringUtils.isNotBlank(msg)){
			MessageFormat messageFormat = new MessageFormat(msg);
			msg =  messageFormat.format(params);
		}
		return msg;
	}
}
