package com.yunda.gps.common.fileUD.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yunda.app.base.BaseController;
import com.yunda.gps.common.fileUD.service.FileUDService;
/**
 * 文件上传/下载 管理控制器 * 
 * 
 */
@Controller
@RequestMapping(value = "/fileUD")
public class FileUDController extends BaseController{
	Log log = LogFactory.getLog(FileUDController.class);
	@Resource(name="fileUDService")
	private FileUDService fileUDService ;
	
	
	/**
	 * 文件上传
	 * @param response
	 * @return	void
	 */
	@ResponseBody
	@RequestMapping("/fileUpload.do")
	public void upload(MultipartHttpServletRequest request,HttpServletResponse response,HttpSession session) { 
		log.info("file upload start ....... ");
		try {
		List<MultipartFile> files = request.getFiles("upNames") ;
		//List<String> ids = fileUDService.upload(files,session) ;
		Map<String,Object> map = fileUDService.uploadOfPictures(files,session) ;		
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script type='text/javascript'>parent.uploadCallback('"+JSONArray.fromObject(map.get("ids")).toString()+"','"+map.get("errMsg").toString()+"')</script>" );
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("file upload end ....... ");
	}
	
}
