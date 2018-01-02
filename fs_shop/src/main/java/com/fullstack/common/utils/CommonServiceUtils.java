package com.fullstack.common.utils;

import java.util.List;

import com.fullstack.user.user.entity.UserRole;

/**
 * 简单service共用处理
 * @author chay
 * @version 2017-8-14
 */
public class CommonServiceUtils {

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String[] listUserRoleToRoleStr(List<UserRole> list){
		String[] str = new String[list.size()];
		for(int i=0;i<list.size();i++){
			str[i] = list.get(i).getRole().getName();
		}
		return str;
	}
}
