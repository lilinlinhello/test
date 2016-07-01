package com.yunda.gps.fixUsers.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.fixUsers.data.FixUsers;
import com.yunda.gps.fixUsers.service.FixUserService;
import com.yunda.gps.util.DateUtil;

@Controller
@RequestMapping(value="/fixUsers")
public class FixUsersController extends BaseController {

	@Resource(name="fixUsersServiceImpl")
	private FixUserService fixUserService;
	
	@RequestMapping("/listPage.do")
	public String forwardListPage(HttpServletRequest request){
		
		return "fixUsers/fixUsers_list";
	}
	
	@RequestMapping("/addPage.do")
	public String forwardAddPage(){
		return "fixUsers/fixUsers_add";
	}
	
	@RequestMapping("/editPage.do")
	public String forwardEditPage(HttpServletRequest request,HttpServletResponse response){
		return "fixUsers/fixUsers_edit";
	}
	
	@RequestMapping("/pageQuery.do")
	public void pageQuery(HttpServletRequest request,HttpServletResponse response,FixUsers vo){
		Page page = fixUserService.pageQuery(vo);
		this.total = page.getTotal();
		sendJsonDataToClient(page, response);
	}
	
	@RequestMapping("/insert.do")
	public void insert(HttpServletRequest request,HttpServletResponse response,FixUsers vo){
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		vo.setCreateBy(loginUser.getUserId());
		vo.setCreateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
		vo.setDeleteFlag((byte) 0);
		Message msg = fixUserService.insert(vo);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, loginUser.getUserId()+"新增安装人="+vo.getName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request,HttpServletResponse response,FixUsers vo){
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		vo.setUpdateBy(loginUser.getUserId());
		vo.setUpdateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//		vo.setDeleteFlag((byte) 0);
		Message msg = fixUserService.update(vo);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, loginUser.getUserId()+"修改安装人="+vo.getName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	@RequestMapping("/getFixUserById.do")
	public void getFixUserById(HttpServletRequest request,HttpServletResponse response,String id){
		FixUsers fixUser = fixUserService.getFixUserById(id);
		sendJsonDataToClient(fixUser, response);
	}
	@RequestMapping("/userMap.do")
	public void getUserMap(HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> list = fixUserService.getUserMap();
		sendJsonDataToClient(list, response);
	}
	
	@RequestMapping("/deleteByIds.do")
	public void deleteByIds(HttpServletRequest request,HttpServletResponse response,String ids){
		Message msg = fixUserService.deleteByID(ids);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除安装人信息：ID为："+ids);
		}
		sendJsonDataToClient(msg, response);
	}
}
