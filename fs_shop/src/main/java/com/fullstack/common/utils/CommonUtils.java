package com.fullstack.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.fullstack.common.exceptions.BusinessException;

/**
 * 基础工具
 * @author chay
 * @version 2017-5-19
 */
public class CommonUtils {

	private static final String CHARSET_NAME = "UTF-8";
    
    /**
	 * 判断送进来的字符串是否是乱码
	 * @param strName
	 * @return
	 * @throws BusinessException 
	 */
	public static boolean isGarbled(String strName) throws BusinessException {
        if(StringUtils.isBlank(strName)){
        	return false;
        }
		try {
            Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();

            int length = (ch != null) ? ch.length : 0;
            for (int i = 0; i < length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    String str = "" + ch[i];
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        	throw new BusinessException(9000002);
        }
        return false;
    }
	/**
	 * 字符串防止乱码转译
	 * @param strName
	 * @return
	 * @throws BusinessException
	 */
	public static String garbledTranslation(String str) throws BusinessException {
		if(StringUtils.isNotBlank(str)&&CommonUtils.isGarbled(str)){
			try {
				str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new BusinessException(9000002);
			}
		}
        return str;
    }
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}finally{
			try {
				if(baos!=null){
					baos.close();
				}
				if(oos!=null){
					oos.close();
				}
			} catch (IOException e) {
				Log.error(e.getMessage());
			}
		}
		
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois =null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}finally{
			try {
				if(ois!=null){
					ois.close();
				}
				if(bais!=null){
					bais.close();
				}
			} catch (IOException e) {
				Log.error(e.getMessage());
			}
		}
		return null;
	}
	
	/**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
    }
	/**
	 * 
	 * @param count		前缀加多少个0
	 * @param number	需要加的数字
	 * @return
	 */
	public static String numberPrefixZeroToString(int count,Integer num){
		if(num==null || num <= 0){
			return num.toString();
		}
		if(count<=0){	//不需要添加
			return num.toString();
		}else if(count==1){	//前缀添加1个0
			if(num<10){
				return "0"+num;
			}else{
				return num.toString();
			}
		}else if(count==2){	//前缀添加2个0
			if(num<10){
				return "00"+num;
			}else if(10 <= num && num < 100){
				return "0"+num;
			}else{
				return num.toString();
			}
		}else if(count==3){	//前缀添加3个0
			if(num<10){
				return "000"+num;
			}else if(10 <= num && num < 100){
				return "00"+num;
			}else if(100 <= num && num < 1000){
				return "0"+num;
			}else{
				return num.toString();
			}
		}
		return num.toString();
	}
	/**
	 * 前缀加1个0
	 * @param count
	 * @param num
	 * @return
	 */
	public static String numberPrefixZeroToString1(Integer num){
		return numberPrefixZeroToString(1,num);
	}
	/**
	 * 前缀加2个0
	 * @param count
	 * @param num
	 * @return
	 */
	public static String numberPrefixZeroToString2(Integer num){
		return numberPrefixZeroToString(2,num);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(numberPrefixZeroToString(1,10));
		System.out.println(numberPrefixZeroToString(2,10));
		System.out.println(numberPrefixZeroToString(3,10));
		System.out.println("---------------");
		System.out.println(numberPrefixZeroToString(1,1));
		System.out.println(numberPrefixZeroToString(2,1));
		System.out.println(numberPrefixZeroToString(3,1));
	}
}
