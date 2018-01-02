package com.fullstack.user.auth.service;

import org.springframework.stereotype.Service;

import com.fullstack.common.service.BaseService;
import com.fullstack.user.auth.entity.Auth;

/**
 * 权限
 * @author chay
 * @version 2017-5-12
 */
@Service
public interface AuthService<T> extends BaseService<Auth> {
	
}