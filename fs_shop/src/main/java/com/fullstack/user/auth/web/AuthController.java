package com.fullstack.user.auth.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.auth.entity.Auth;

@RestController  
@RequestMapping("/auth")  
public class AuthController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Auth auth) throws BusinessException {
		Page<Auth> page = RequestUtils.getPage(request);
		EntityWrapper<Auth> e = this.entityInit(auth);
		page = authService.findPage(page, e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param Auth
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("createAuth")
    public JSONObject createAuth(HttpServletRequest request,Auth auth) throws BusinessException {
		authService.create(auth);
        return this.retResult();
    }
	/**
	 * 修改
	 * @param request
	 * @param Auth
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateAuth")
    public JSONObject updateAuth(HttpServletRequest request,Auth auth) throws BusinessException {
		authService.updateById(auth);
        return this.retResult();
    }
	/**
	 * 删除
	 * @param request
	 * @param Auth
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("delAuth")
    public JSONObject delAuth(HttpServletRequest request,Auth auth) throws BusinessException {
		auth.setDelFlag(Auth.DEL_FLAG_DELETE);
		authService.updateById(auth);
        return this.retResult();
    }
	/**
	 * 批量删除数据
	 * @param request
	 * @param auth
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Auth auth) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			authService.batchDel(auth,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据失败");
		}
    }
	
}
