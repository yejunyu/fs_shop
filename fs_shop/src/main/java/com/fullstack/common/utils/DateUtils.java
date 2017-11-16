package com.fullstack.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	 * 根据日期格式获取昨天时间
	 * @param pattern
	 * @return
	 */
	public static String getYesterdayDate(String pattern){
		Date date = new Date(new Date().getTime()-24*60*60*1000);
		return DateFormatUtils.format(date, pattern);
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
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getDays(String date){
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); 
		try {
			rightNow.setTime(simpleDate.parse(date)); //要计算你想要的月份，改变这里即可
		} catch (ParseException e) {
			Log.error(e.getMessage());
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	
	
	
	
	public static void main(String[] args) {
		for(int i=1;i<=12;i++){
			System.out.println(getDays("2017-"+i));
		}
		
//		System.out.println(getYesterdayDate(parsePatterns[1]));
//		for(int i=0;i<=10;i++){
//			System.out.println(getDateYMDHMSsss());
//		}
	}
}
