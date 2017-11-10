package com.fullstack.common.utils;

/**
 * 
 * @author chay
 * @version 
 */
public class ImgUtils {

	public static final String  IMG_KEY = "imgPath";
	

	/**
	 * 
	 */
	public static String commonPathUtils(String basePath,String entityPath,String fileName) {
		return basePath+entityPath+"/"+fileName;
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println();
	}
}
