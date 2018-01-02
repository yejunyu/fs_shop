package com.fullstack.user.auth.service.impl;

import org.springframework.stereotype.Service;

import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.user.auth.dao.AuthDao;
import com.fullstack.user.auth.entity.Auth;
import com.fullstack.user.auth.service.AuthService;

/**
 * 权限
 * @author chay
 * @version 2017-5-12
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl<AuthDao, Auth> implements AuthService<Auth>{
	
	
	
}