package com.fullstack.common.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fullstack.common.exceptions.BusinessException;

/**
 * @author chay
 * @version 2017-7-10
 * @param <T>
 */
public interface BaseService<T> extends IService<T> {

	/**
	 * 重写二次封装简单的增删改查
	 * @return
	 * @throws BusinessException
	 */
	public boolean create(T t) throws BusinessException;
	public boolean delById(T t) throws BusinessException;
	public T editById(T t) throws BusinessException;
	public T getInfoById(int id) throws BusinessException;
	public boolean batchDel(T t,String[] ids) throws BusinessException;
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * 当分页参数等于-1的时候返回全部数据，不进行分页操作
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, Wrapper<T> wrapper) throws BusinessException;
	
}