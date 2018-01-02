package com.fullstack.user.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fullstack.user.user.entity.User;

/**
 * 用户
 * @author chay
 * @version 2017-5-12
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

	User getByLoginNameAndPassword(User user);	//根据用户名和密码获取用户
	User getByLoginName(String loginName);	//根据用户名获取数据
	int updateLastLoginTime(User user);		//修改最后登录时间
	int updatePassword(User user);		//修改密码
//    /**
//     * 自定义注入方法
//     */
//    int deleteAll();
//
//    @Select("select test_id as id, name, age, test_type from user")
//    public List<User> selectListBySQL();

}