package com.fullstack.shop.dict.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.service.impl.BaseServiceImpl;
import com.fullstack.common.utils.PropertiesUtil;
import com.fullstack.shop.dict.dao.AttachDao;
import com.fullstack.shop.dict.entity.Attach;
import com.fullstack.shop.dict.service.AttachService;

/**
 * 
 * @author chay
 * @version 2017-04-17
 */
@Service
public class AttachServiceImpl extends BaseServiceImpl<AttachDao, Attach> implements AttachService<Attach>{

	@Autowired  
	private AttachDao attachDao;
	
	@Override
	public boolean bindParentId(Integer id,Integer parentId) throws BusinessException {
		Attach orginAttach = super.getInfoById(id);
		if(orginAttach==null){
			throw new BusinessException(9003004);
		}
		orginAttach.setParentId(parentId);
		super.editById(orginAttach);
		return true;
	}

	@Override
	public Attach getLastAttachByParentId(Integer parentId) throws BusinessException {
		Attach Attach = new Attach();
		Attach.setParentId(parentId);
		EntityWrapper<Attach> wrapper = this.entityInit(Attach);
		wrapper.orderBy("id", false);
		return super.selectOne(wrapper);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={BusinessException.class})
	public void delNullAttach() throws BusinessException {
		List<Attach> list = attachDao.selectParentIdForNull();
		for(Attach img:list){
			String filePath = PropertiesUtil.getAttachBasePath()+img.getPath()+"/"+img.getName();
			File file=new File(filePath);
			if(file.exists()&&file.isFile()){
				file.delete();
				System.out.println("文件删除成功");
			}else{
				System.out.println("需要删除的文件不存在");
			}
			//反正都是后台定时执行，所以懒得专门写接口去删除，直接用公用的就好
			super.delById(img);
		}
	}
	
	
}