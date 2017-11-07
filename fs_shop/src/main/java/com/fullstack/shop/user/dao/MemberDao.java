package com.fullstack.shop.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.shop.user.entity.Member;

/**
 * 
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface MemberDao extends BaseMapper<Member> {


}