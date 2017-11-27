package com.fullstack.shop.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.user.entity.Member;

@RestController  
@RequestMapping("/member")  
public class MemberController extends ServiceController {
	
	
	/**
	 * 获取用户一个简单的唯一标识号
	 * @param request
	 * @param member
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getMemberUUID")
    public JSONObject getMemberUUID(HttpServletRequest request) throws BusinessException {
		String uuid = RequestUtils.getRemoteAddr(request)+"_"+DateUtils.getDateYMDHMS();
		Member member = new Member();
		member.setWxId(uuid);
		member.setName("游客"+(memberService.getCount(new Member())+1));
		memberService.createMember(member);
        return this.retResult(member);
    }
	/**
	 * 根据用户的微信开发id生成用户信息
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("createMemberByWxId")
    public JSONObject createMemberByWxId(HttpServletRequest request) throws BusinessException {
		String wxId = RequestUtils.getParameter(request, "wxId");
		Member member = new Member();
		member.setWxId(wxId);
		Member m = memberService.getByWxId(wxId);
		if(m==null){
			member.setName("微信用户"+(memberService.getCount(new Member())+1));
			memberService.createMember(member);
		}
        return this.retResult(member);
    }
	/**
	 * 用户列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Member member) throws BusinessException {
		Page<Member> page = RequestUtils.getPage(request);
		EntityWrapper<Member> e = this.entityInit(member);
		page = memberService.findPage(page, e);
        return this.retResult(page);
    }
	
}
