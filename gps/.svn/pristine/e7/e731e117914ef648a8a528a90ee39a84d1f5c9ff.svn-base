package com.yunda.gps.common.fileUD.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传操作 接口
 */
public interface FileUDService {
	/**
	 * 上传文件至FTP,记录文档信息(不限制上传文件类型)
	 * @param fileName
	 */
	List<String> upload(List<MultipartFile> files, HttpSession session);
	/**
	 * 上传文件至FTP,记录文档信息(限制上传文件类型为常用基本图片:JEPG,PNG,GIF,TIFF,BMP)
	 * @param fileName
	 */
	Map<String,Object> uploadOfPictures(List<MultipartFile> files, HttpSession session) ;
	/**
	 * 通过id加载文档名称
	 * @param id
	 * @return Map<String,String>
	 */
	List<String> getFileNameByIds(String docIds);
	/**
	 * 通过ID加载文档FTP存放地址
	 * @param docIds
	 * @return
	 */
	List<String> getFilePathByIds(String docIds);
	
}
