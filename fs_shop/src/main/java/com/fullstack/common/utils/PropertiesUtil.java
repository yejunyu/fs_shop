package com.fullstack.common.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author chay
 * @version 2017-5-19
 */
public class PropertiesUtil {
	
	private static String redis_keyPrefix = null;
	private static String redis_host = null;
	private static String redis_port = null;
	private static String redis_password = null;
	
	static {
		InputStream inputStream = PropertiesUtil.class
				.getResourceAsStream("/business.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			redis_host = properties.getProperty("redis.host");
			redis_port = properties.getProperty("redis.port");
			redis_password = properties.getProperty("redis.password");
		} catch (Exception e) {
			
		}
	}

	public static String getRedisHost() {
		return redis_host;
	}

	public static String getRedisPort() {
		return redis_port;
	}

	public static String getRedisPassword() {
		return redis_password;
	}

	public static String getRedisKeyPrefix() {
		return redis_keyPrefix;
	}

	

	
}
