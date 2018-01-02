package com.fullstack.common.utils;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.user.user.entity.User;

/**
 * 用户
 * @author chay
 * @version 2017-5-15
 */
public class UserUtils {
	
	
//	static HttpServletRequest currReq ;
//	static {
//		if(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())!=null){
//			currReq = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		}
//	}
	/**
	 * 获取当前用户
	 * @return
	 * @throws BusinessException 
	 */
	public static User getCurrentUser(HttpServletRequest request) throws BusinessException{
		String token = UserUtils.getCurrentToken(request);
		User user = (User) CacheUtils.get(token);
		if(user == null){
			throw new BusinessException(9001006);
		}
		return user;
	}
	/**
	 * 获取当前用户id
	 * @return
	 * @throws BusinessException 
	 */
	public static Integer getCurrentUserId(HttpServletRequest request) throws BusinessException{
		return UserUtils.getCurrentUser(request).getId();
	}
	/**
	 * 获取当前用户的token
	 * @return
	 * @throws BusinessException 
	 */
	public static String getCurrentToken(HttpServletRequest request) throws BusinessException{
		String token = CookieUtils.getCookie(request, CookieUtils.TOKEN_COOKIE);
		if(StringUtils.isBlank(token)){
			token = request.getParameter(CookieUtils.TOKEN_COOKIE);
		}
		if(StringUtils.isBlank(token)){
			throw new BusinessException(9001006);
		}
		return token;
	}
	
	/**
	 * 用户密码加密格式
	 * @throws BusinessException 
	 */
	public static String passwordEncode(String loginName,String password) throws BusinessException {
		return MD5Utils.MD5To32(loginName+password);
	}
	/**
	 * 随机获取一个密码
	 * @param length 密码长度
	 * @return
	 */
	public static String randomPassword(int length){
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
                'x', 'y', 'z', 'A','B','C','D','E','F','G','H','I','J','K',  
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
                'X', 'Y' ,'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
		StringBuffer sb = new StringBuffer();  
		Random random=new Random();
		for(int i=0;i<length;i++){
			sb.append(str[random.nextInt(62)]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(UserUtils.randomPassword(8));
	}
}
