package com.fullstack.shop.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.sys.entity.Feedback;

@RestController  
@RequestMapping("/feedback")  
public class FeedbackController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Feedback feedback) throws BusinessException {
		Page<Feedback> page = RequestUtils.getPage(request);
		EntityWrapper<Feedback> e = this.entityInit(feedback);
		page = feedbackService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 根据类型加载数据
	 * @param request
	 * @param Feedback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("loadByType")
    public JSONObject loadByType(HttpServletRequest request,Feedback feedback) throws BusinessException {
		EntityWrapper<Feedback> e = this.entityInit(feedback);
		List<Feedback> list = feedbackService.selectList(e);
        return this.retResult(list);
    }
	/**
	 * 新增
	 * @param request
	 * @param Feedback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createFeedback(HttpServletRequest request,Feedback feedback) throws BusinessException {
		feedbackService.create(feedback);
        return this.retResult(success_create);
    }
	/**
	 * 修改
	 * @param request
	 * @param Feedback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateFeedback(HttpServletRequest request,Feedback feedback) throws BusinessException {
		feedbackService.editById(feedback);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Feedback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delFeedback(HttpServletRequest request,Feedback feedback) throws BusinessException {
		feedbackService.delById(feedback);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Feedback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Feedback feedback) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			feedbackService.batchDel(feedback,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	
}
