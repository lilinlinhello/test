package com.yunda.gps.common.fileUD.mapper;

import java.util.List;

import com.yunda.gps.common.fileUD.bean.Documents;

/**
 * 文件上传操作 mybatis 映射接口
 * @author luogengxian
 *
 */
public interface FileUPMapper {
	String getName();
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
	/***
	 * 根据图片id获取对应的名称
	 * */
	List<String> getNameByIds(String[] docIds);
	
}
