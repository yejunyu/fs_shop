package com.fullstack.user.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.UserUtils;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.user.user.entity.User;

@RestController  
@RequestMapping("/user")  
public class UserController extends ServiceController {
	
	/************************ 用户个人管理 ******************************/
	/**
	 * 个人信息
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("info")
    public JSONObject info(HttpServletRequest request) throws BusinessException {
        return this.retResult(UserUtils.getCurrentUser(request));
    } 
	/**
	 * 修改个人信息
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject update(User user) throws BusinessException {
		user = userService.updateInfo(user);
        return this.retResult("修改成功",user);
    }
	
	/**
	 * 修改个人密码
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updatePassword")
    public JSONObject updatePassword(HttpServletRequest request) throws BusinessException {
		String loginName = RequestUtils.getParameter(request, "loginName");
		String password = RequestUtils.getParameter(request, "password");
		String newPassword = RequestUtils.getParameter(request, "newPassword");
		userService.updatePassword(loginName, password, newPassword);
        return this.retResult("修改成功");
    }
	
	
	/************************用户管理*************************/
	/**
	 * 用户列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,User user) throws BusinessException {
		Page<User> page = RequestUtils.getPage(request);
		EntityWrapper<User> e = this.entityInit(user);
		page = userService.findPage(page, e);
        return this.retResult(page);
    }
	/**
	 * 新增用户
	 * @param request
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("createUser")
    public JSONObject createUser(HttpServletRequest request,User user) throws BusinessException {
		user.getExtraData().put("officeId", RequestUtils.getInteger(request, "officeId"));
		userService.createUser(user,RequestUtils.getStrings(request,"roleIds[]"));
        return this.retResult("新增成功",user);
    }
	/**
	 * 修改用户
	 * @param request
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateUser")
    public JSONObject updateUser(HttpServletRequest request) throws BusinessException {
		User user = new User();
		user.setId(RequestUtils.getInteger(request, "id"));
		user.setName(RequestUtils.getParameter(request, "name"));
		user.setCode(RequestUtils.getParameter(request, "code"));
		user.setContactWay(RequestUtils.getParameter(request, "contactWay"));
		userService.editById(user, RequestUtils.getStrings(request,"roleIds[]"));
        return this.retResult("修改成功");
    }
	/**
	 * 重置密码
	 * @param request
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("resetPassword")
    public JSONObject resetPassword(HttpServletRequest request) throws BusinessException {
		String loginName = request.getParameter("loginName");
		userService.resetPassword(loginName);
        return this.retResult("重置成功");
    }
	/**
	 * 删除用户
	 * @param request
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("delUser")
    public JSONObject delUser(HttpServletRequest request) throws BusinessException {
		userService.delUser(RequestUtils.getInteger(request, "id"));
        return this.retResult("删除成功");
    }
	/**
	 * 批量删除数据
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			userService.batchDel(ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据失败");
		}
    }
	/**
	 * 编辑用户状态
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("editStatus")
    public JSONObject editStatus(HttpServletRequest request) throws BusinessException {
		userService.editStatus(RequestUtils.getInteger(request, "id"),RequestUtils.getInt(request, "status"));
        return this.retResult("修改成功");
    }
}
