package com.fullstack.user.role.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.role.entity.Role;

@RestController  
@RequestMapping("/role")  
public class RoleController extends ServiceController {
	
	/**
	 * 角色列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Role role) throws BusinessException {
		Page<Role> page = RequestUtils.getPage(request);
		page = roleService.findPage(page, this.entityInit(role));
        return this.retResult(page);
    }
	/**
	 * 新增角色
	 * @param request
	 * @param Role
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("createRole")
    public JSONObject createRole(HttpServletRequest request,Role role) throws BusinessException {
		roleService.create(role,RequestUtils.getStrings(request,"authIds[]"));
        return this.retResult();
    }
	/**
	 * 修改角色
	 * @param request
	 * @param Role
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateRole")
    public JSONObject updateRole(HttpServletRequest request,Role role) throws BusinessException {
		roleService.editById(role,RequestUtils.getStrings(request,"authIds[]"));
        return this.retResult();
    }
	/**
	 * 删除角色
	 * @param request
	 * @param Role
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("delRole")
    public JSONObject delRole(HttpServletRequest request,Role role) throws BusinessException {
		roleService.delById(role);
        return this.retResult("删除成功");
    }
	/**
	 * 批量删除数据
	 * @param request
	 * @param role
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Role role) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			roleService.batchDel(role,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据失败");
		}
    }
	
}
