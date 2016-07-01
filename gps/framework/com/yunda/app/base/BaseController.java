package com.yunda.app.base;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunda.app.entity.vo.PageQueryParams;
import com.yunda.app.service.DicService;
import com.yunda.app.util.JCDFJsonUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.common.location.service.LocationService;

/**
 * 控制器基类，负责处理一些通用的业务，同事做一些通用的简单业务实现
 * 
 * @author JiangShui
 * @date	2013-10-13
 * 
 */
public abstract class BaseController{

	/**自动注入http请求的request对象*/
	/**
	 * @modified by jxq
	 * @date 2013-10-15
	 * 此处注入无效，删除
	 */
	/*@Autowired
	protected  HttpServletRequest request; */
	
	/**
	 * 引入常用公共service类
	 * @modified by lgx
	 */
	@Resource(name="locationService")
	protected LocationService locationService; 
	@Resource(name="dicService")
	protected DicService dicService ;	
	protected long total;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * 获取页面传递过来的分页参数并包转
	 * @modified by jxq
	 * @date 2013-10-15
	 * 此处修改为工具方法，由继承类调用
	 * 
	 * @return
	 * 
	 */
	public static PageQueryParams getPageQueryParams(HttpServletRequest request) {
		int pageNo = 0;
		int pageSize = 0;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
			pageSize = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
		}
		pageNo = pageNo <=0 ? StaticVar.DEFAULT_PAGE_NO : pageNo;
		pageSize = pageSize <=0 ? StaticVar.DEFAULT_PAGE_SIZE : pageSize;
		return PageQueryParams.getInstance(pageNo, pageSize);
	}
	
	/**
	 * 将指定的数据写到客户端
	 * 非特殊情况或者特殊需求，请默认采用对应的重载方法：sendJsonDataToClient(Object obj, HttpServletResponse response)
	 * 只有该重载方法不能满足需求时，再采用该方法
	 * 
	 * @param jsonData
	 * @param response
	 */
	public static void sendJsonDataToClient(String jsonData, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		try {
			response.getWriter().write(jsonData);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定的数据写到客户端
	 * 格式化日期字段值时默认采用模式：yyyy-MM-dd，如果需要其他模式，
	 * 请采用重载方法：sendJsonDataToClient(Object obj, String dateFmt, HttpServletResponse response)以便自行指定模式
	 * 
	 * @param obj
	 * @param response
	 */
	public static void sendJsonDataToClient(Object obj, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		try {
			response.getWriter().write(JCDFJsonUtil.fromObjctToJson(obj, null));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定的数据写到客户端
	 * 
	 * @param obj
	 * @param dateFmt	格式化日期字段时采用的“模式”
	 * @param response
	 */
	public void sendJsonDataToClient(Object obj, String dateFmt, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		try {
			response.getWriter().write(JCDFJsonUtil.fromObjctToJson(obj, dateFmt));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}