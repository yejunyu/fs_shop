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
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMdd", "yyyyMM" ,
			"yyyyMMddHHmmss","yyyyMMddHHmmssSSS"};

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
	
	/**
	 * 获取当前时间的年月
	 * @return
	 */
	public static String getDateYM() {
		return getDate(parsePatterns[3]);
	}
	/**
	 * 获取当前时间的年月日
	 * @return
	 */
	public static String getDateYMD() {
		return getDate(parsePatterns[2]);
	}
	/**
	 * 获取当前时间的年月日、时分秒
	 * @return
	 */
	public static String getDateYMDHMS() {
		return getDate(parsePatterns[4]);
	}
	/**
	 * 获取当前时间的时分秒以及毫秒
	 * @return
	 */
	public static String getDateYMDHMSsss() {
		return getDate(parsePatterns[5]);
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		for(int i=0;i<=10;i++){
			System.out.println(getDateYMDHMSsss());
		}
	}
}
