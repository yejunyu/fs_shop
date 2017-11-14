package com.fullstack.common.utils;

import java.math.BigDecimal;

/**
 * 
 * @author chay
 * @version 
 */
public class MathUtils {

	

	/**
	 * 获取1-num范围内的随机数
	 */
	public static int getRandom(int num) {
		return (int) (1+Math.random()*num);
	}
	/**
	 * 获取双精度
	 * @param num
	 * @return
	 */
	public static Double getDouble2(String num) {
		BigDecimal b = new BigDecimal(Double.parseDouble(num)); 
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println();
	}
}
