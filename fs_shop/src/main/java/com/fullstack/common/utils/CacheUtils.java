package com.fullstack.common.utils;

/**
 * Cache工具类
 * @author chay
 * @version 2017-5-19
 */
public class CacheUtils {
	
	/**
	 * 缓存名称
	 */
	public static final String CACHE_NAME = "cache";
	
	/*************************************使用EhCache缓存****************************************/
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return EhCacheUtils.get(key);
	}
	
	/**
	 * 写入缓存
	 * @param key
	 * @return
	 */
	public static void set(String key, Object value) {
		EhCacheUtils.put(key, value);
	}
	
	/**
	 * 从缓存中移除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		EhCacheUtils.remove(key);
	}
	
	
//	/*************************************使用redis缓存****************************************/
//	/**
//	 * 获取缓存
//	 * @param key
//	 * @return
//	 */
//	public static Object get(String key) {
//		return JedisUtils.getObject(key);
//	}
//	
//	/**
//	 * 写入缓存
//	 * @param key
//	 * @return
//	 */
//	public static void set(String key, Object value) {
//		JedisUtils.setObject(key, value);
//	}
//	
//	/**
//	 * 从缓存中移除
//	 * @param key
//	 * @return
//	 */
//	public static void remove(String key) {
//		JedisUtils.del(key);
//	}
	
	
}
