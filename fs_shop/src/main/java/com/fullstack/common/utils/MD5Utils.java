package com.fullstack.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fullstack.common.exceptions.BusinessException;

/**
 * MD5 加密
 * @author chay
 * @version 2017-5-15
 */
public class MD5Utils {
	

	/**
	 * @throws BusinessException 
	 * 
	 */
	public static String MD5ByMessageDigest(String str) throws BusinessException {
		String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
        	throw new BusinessException(9000001);
        }
        return result;
	}
	
	/**
	 * 32位md5加密
	 * @param str
	 * @return
	 * @throws BusinessException 
	 */
	public static String MD5To32(String str) throws BusinessException{
		return MD5ByMessageDigest(str);
	}
	/**
	 * 16位md5加密
	 * @param str
	 * @return
	 * @throws BusinessException 
	 */
	public static String MD5To16(String str) throws BusinessException{
		return MD5ByMessageDigest(str).toString().substring(8, 24);
	}
	
}
