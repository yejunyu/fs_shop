package com.fullstack.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一归集日志打印
 * @author 
 *
 */
public class Log {

	private static Logger logger = LoggerFactory.getLogger(Log.class);

	public static void info(String logStr) {
		logger.info(logStr);
	}

	public static void debug(String logStr) {
		logger.debug(logStr);
	}

	public static void error(String logStr) {
		logger.error(logStr);
	}

	public static void main(String[] args) {
		System.out.println("111");
		Log.info("14654");
	}

}
