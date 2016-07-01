package com.yunda.gps.common.fileUD.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.common.fileUD.bean.Documents;
import com.yunda.gps.common.fileUD.dao.FileUDDao;
import com.yunda.gps.common.fileUD.mapper.FileUPMapper;
/**
 * file upload daoImpl
 *
 */
@Repository(value="fileUDDao")
public class FileUDDaoImpl extends JcdfDaoSupport implements FileUDDao{
	@Autowired
	private FileUPMapper fileUPMapper ;
	public String getNme(){
		return null;
	}
	/**
	 * 保存上传文档信息
	 * @param doc
	 */
	public void saveUpInfos(Documents doc) {
		fileUPMapper.saveUpInfos(doc);
	}
	/**
	 * 通过id加载文档名称
	 * @param ids
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<String> getFileNameByIds(String[] docIds){
		return fileUPMapper.getFileNameByIds(docIds);
	}
	/**
	 * 通过ID加载文档FTP存放地址
	 * @param docIds
	 * @return
	 */
	public List<String> getFilePathByIds(String[] docIds){
		return fileUPMapper.getNameByIds(docIds);
	}
}
