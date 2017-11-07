package com.fullstack.common.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.persistence.DataEntity;
import com.fullstack.common.service.BaseService;
import com.fullstack.shop.dict.entity.Dict;
import com.fullstack.shop.dict.service.DictService;

/**
 * Service基类
 * @author chay
 * @version 2017-7-10
 * @param <T>
 */
public abstract class BaseServiceImpl<D extends BaseMapper<T>, T extends DataEntity<T>> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {
	
	@Autowired  
	protected DictService<Dict> dictService;
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	@Override
	public Page<T> findPage(Page<T> page, Wrapper<T> wrapper) throws BusinessException {
		if(page.getCurrent() == -1){
			List<T> list = baseMapper.selectList(wrapper);
			page = new Page<T>();
			page.setCurrent(1);
			page.setRecords(list);
			page.setSize(list.size());
			page.setTotal(list.size());
			return page;
		}else{
			return this.selectPage(page, wrapper);
		}
	}
	/**
	 * 新增数据
	 */
	@Override
	public boolean create(T t) throws BusinessException {
		t.preInsert();
		int res = baseMapper.insert(t);
		return res>0;
	}

	/**
	 * 根据id删除数据
	 */
	@Override
	public boolean delById(T t) throws BusinessException {
		t.setDelFlag(T.DEL_FLAG_DELETE);
		t.preUpdate();
		int res = baseMapper.updateById(t);
		return res>0;
	}
	/**
	 * 获取详情
	 */
	@Override
	public T getInfoById(int id) throws BusinessException {
		return baseMapper.selectById(id);
	}
	/**
	 * 根据id修改数据
	 */
	@Override
	public T editById(T t) throws BusinessException {
		t.preUpdate();
		baseMapper.updateById(t);
		return baseMapper.selectById(t.getId());
	}
	/**
	 * 批量删除数据
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public boolean batchDel(T t,String[] ids) throws BusinessException {
		t.setDelFlag(T.DEL_FLAG_DELETE);
		int res = 0;
		for(String id : ids){
			t.setId(Integer.parseInt(id));
			res = baseMapper.updateById(t);
		}
		return res>0;
	}

	
	
	
	
	/*    数据处理     */
	/**
	 * Fieldset字段转为list集合
	 * @param list
	 */
	protected void fieldsetUtils(List<T> list){
		for(T t:list){
			if(StringUtils.isBlank(t.getFieldset())){
				continue;
			}
			JSONArray jsonArr = JSONArray.parseArray(t.getFieldset());
			for(Object obj : jsonArr){
				t.getListFieldset().add(obj.toString());
			}
		}
	}
	
	/**
	 * 根据父标准处理子数据格式
	 * @param parentFieldset
	 * @param t
	 * @return
	 */
	protected T fieldsetUtils(String parentFieldset,T t){
		if(StringUtils.isBlank(parentFieldset) && (t!=null && StringUtils.isNotBlank(t.getFieldset()))){
			JSONArray jsonArr = this.strToJSONArray(t.getFieldset());
			for(Object obj : jsonArr){
				t.getListFieldset().add(obj.toString());
			}
		}else if((t!=null && StringUtils.isNotBlank(t.getFieldset()))){
			//组装返回数据
			JSONArray jsonArr = this.strToJSONArray(parentFieldset);
			for(Object obj : jsonArr){
				JSONObject jsonObj = JSONObject.parseObject(obj.toString());
				String key = jsonObj.getString("name");
				JSONObject json = JSONObject.parseObject(t.getFieldset());
				t.getExtraData().put(key, json.containsKey(key)?json.get(key):"");
			}
			//处理字段列表
			jsonArr = this.strToJSONArray(parentFieldset);
			for(Object obj : jsonArr){
				t.getListFieldset().add(obj.toString());
			}
		}
		return t;
	}
	/**
	 * 转换自定义字段为列表，便于展示
	 * @param t
	 * @return
	 */
	protected T fieldsetUtils(T t){
		this.fieldsetUtils(null, t);
		return t;
	}
	/**
	 * String转JSONArray
	 * @param fieldset
	 * @return
	 */
	protected JSONArray strToJSONArray(String str){
		if(StringUtils.isNotBlank(str)){
			return JSONArray.parseArray(str);
		}
		return new JSONArray();
	}
	
}
