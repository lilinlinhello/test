package com.yunda.gps.common.fileUD.dao;

import java.util.List;

import com.yunda.gps.common.fileUD.bean.Documents;

/**
 * file upload dao
 * @author luogengxian
 *
 */
public interface FileUDDao {
	String getNme();
	/**
	 * 保存上传文档信息
	 * @param doc
	 */
	void saveUpInfos(Documents doc);
	/**
	 * 通过id加载文档名称
	 * @param ids
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<String> getFileNameByIds(String[] docIds);
	/**
	 * 通过ID加载文档FTP存放地址
	 * @param docIds
	 * @return
	 */
	List<String> getFilePathByIds(String[] docIds);
		
}
