package com.fullstack.shop.dict.web;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fullstack.common.exceptions.BusinessException;
import com.fullstack.common.utils.DateUtils;
import com.fullstack.common.utils.ImgUtils;
import com.fullstack.common.utils.Log;
import com.fullstack.common.utils.PropertiesUtil;
import com.fullstack.common.utils.UploadUtils;
import com.fullstack.common.web.RequestUtils;
import com.fullstack.common.web.ServiceController;
import com.fullstack.shop.dict.entity.Attach;

@RestController  
@RequestMapping("/attach")  
public class AttachController extends ServiceController {
	
	/**
	 * 列表
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("list")
    public JSONObject list(HttpServletRequest request,Attach Attach) throws BusinessException {
		Page<Attach> page = RequestUtils.getPage(request);
		EntityWrapper<Attach> e = this.entityInit(Attach);
		page = attachService.findPage(page,e);
        return this.retResult(page);
    }
	/**
	 * 新增
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("create")
    public JSONObject createAttach(@RequestParam(value = "file") MultipartFile multipart,
    		HttpServletRequest request,Attach attach) throws BusinessException {
		
		// 判断路径是否存在，不存在则创建目录  
        String basePath = PropertiesUtil.getAttachBasePath();
        String filePath = "/"+DateUtils.getDateYM();
        String path = basePath + filePath;
        File pathFile = new File(path);  
        if (!pathFile.exists() && !pathFile.isDirectory()) {  
            if (!pathFile.mkdirs()){
            	throw new BusinessException(9003001);
            }
        }  
        //文件获取处理
		byte[] fileByte = null;
		String fileName = null;
		String msg = UploadUtils.validateFile(request,multipart);
		if(StringUtils.isEmpty(msg)){
			try {
				String suffixName = multipart.getOriginalFilename();
				if(!StringUtils.isEmpty(suffixName)){
					suffixName = suffixName.substring(suffixName.lastIndexOf("."),suffixName.length());
				}
				
				InputStream inputStream = multipart.getInputStream();
				if(inputStream==null){
					Log.error("文件不存在");
					throw new BusinessException(9003002);
				}
				//将得到的文件流转二进制，用于存储到数据库（避免数据转移的时候转移漏）
				fileByte = UploadUtils.getFileBuffer(inputStream);  
				
				//文件输出到硬盘，前端读取时候直接读取硬盘上面的，发现硬盘没有数据再去数据库获取
				fileName = UploadUtils.getFileRename()+suffixName;
				UploadUtils.fileOutputDisk(fileByte, path, fileName);
			} catch (Exception e) {
				Log.error(e.getMessage());
				throw new BusinessException(9003002);
			}
		}else{
			Log.error("文件校验不通过");
			throw new BusinessException(9003002);
		}
		
		attach.setBytes(fileByte);
		attach.setName(fileName);
		attach.setPath(filePath);
		attachService.create(attach);
		attach.getExtraData().put(ImgUtils.IMG_KEY, 
				ImgUtils.commonPathUtils(PropertiesUtil.getAttachLoadPath(),attach.getPath(),attach.getName()));
        return this.retResult(attach);
    }
	/**
	 * 修改
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("update")
    public JSONObject updateAttach(HttpServletRequest request,Attach attach) throws BusinessException {
		attachService.editById(attach);
        return this.retResult(success_update);
    }
	/**
	 * 删除
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("del")
    public JSONObject delAttach(HttpServletRequest request,Attach attach) throws BusinessException {
		attachService.delById(attach);
        return this.retResult(success_del);
    }
	
	/**
	 * 批量删除
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("batchDel")
    public JSONObject batchDel(HttpServletRequest request,Attach attach) throws BusinessException {
		String[] ids = RequestUtils.getStrings(request,"ids[]");
		if(ids!=null && ids.length>0){
			attachService.batchDel(attach,ids);
	        return this.retResult(success_del);
		}else{
			return this.retResult("删除数据不存在");
		}
    }
	/**
	 * 
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getInfoById")
    public JSONObject getInfoById(HttpServletRequest request,Attach attach) throws BusinessException {
		attach = attachService.getInfoById(attach.getId());
        return this.retResult(attach);
    }
	
	/**
	 * 
	 * @param request
	 * @param Attach
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("bindParentId")
    public JSONObject bindParentId(HttpServletRequest request,Attach attach) throws BusinessException {
		attachService.bindParentId(attach.getId(),attach.getParentId());
        return this.retResult("绑定成功");
    }
}
