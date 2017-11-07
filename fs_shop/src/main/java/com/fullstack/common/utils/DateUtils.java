package com.fullstack.common.utils;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期处理
 * @author chay
 * @version 2017-9-1
 */
public class DateUtils {

	
	public static String[] parsePatterns = {
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMdd"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDate() {
		return getDate(parsePatterns[0]);
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(getDate("yyyyMMdd"));
	}
}
