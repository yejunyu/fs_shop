package com.fullstack.common.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.fullstack.common.exceptions.BusinessException;

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
	/**
	 * 两数相乘
	 * @param num1
	 * @param num2
	 * @return
	 * @throws BusinessException 
	 */
	public static Double multip2(String num1,String num2) throws BusinessException{
		if(StringUtils.isBlank(num1) || StringUtils.isBlank(num2)){
			throw new BusinessException(9000006);
		}
		BigDecimal b1 = new BigDecimal(Double.parseDouble(num1)); 
		double d1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		BigDecimal b2 = new BigDecimal(Double.parseDouble(num2)); 
		double d2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		double res = d1*d2;
		BigDecimal b3 = new BigDecimal(res); 
		return b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 两数相加
	 * @param num1
	 * @param num2
	 * @return
	 * @throws BusinessException
	 */
	public static Double plus2(String num1,String num2) throws BusinessException{
		if(StringUtils.isBlank(num1) || StringUtils.isBlank(num2)){
			throw new BusinessException(9000006);
		}
		BigDecimal b1 = new BigDecimal(Double.parseDouble(num1)); 
		double d1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		BigDecimal b2 = new BigDecimal(Double.parseDouble(num2)); 
		double d2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		double res = d1+d2;
		BigDecimal b3 = new BigDecimal(res); 
		return b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 
	 * @param num
	 * @return
	 */
	public static double parseDouble(String num){
		if(StringUtils.isBlank(num)){
			return 0;
		}
		return MathUtils.getDouble2(num);
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println();
	}
}
