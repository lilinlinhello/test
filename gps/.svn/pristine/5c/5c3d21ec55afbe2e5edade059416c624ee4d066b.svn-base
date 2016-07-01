package com.yunda.gps.alarmParam.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.alarmParam.data.AlarmParam;
import com.yunda.gps.alarmParam.data.AlarmParamVo;
import com.yunda.gps.alarmParam.servcie.AlarmParamService;
import com.yunda.gps.constant.Constant;

@Controller
@RequestMapping("/alarmParam")
public class AlarmParamController extends BaseController {

	@Resource(name="alarmParamServiceImpl")
	private AlarmParamService alarmParamService;
	
	@RequestMapping("/listPage.do")
	public String forwardListPage(HttpServletRequest request){
		
		return "alarmParam/alarmParam_list";
	}
	
	@RequestMapping("/addPage.do")
	public String forwardAddPage(){
		return "alarmParam/alarmParam_add";
	}
	
	@RequestMapping("/editPage.do")
	public String forwardEditPage(HttpServletRequest request,String id){	
		AlarmParamVo alarmParam = alarmParamService.selectById(id);
		request.setAttribute("alarmParam", JSONObject.fromObject(alarmParam));
		return "alarmParam/alarmParam_edit";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/pageQuery.do")
	public void pageQuery(HttpServletRequest request,HttpServletResponse response,AlarmParam alarmParam){
		Page page = alarmParamService.pageQuery(alarmParam);
		if(page != null){
			this.total = page.getTotal();
		}
		sendJsonDataToClient(page, response);
	}
	
	@RequestMapping("/insert.do")
	public void insert(HttpServletRequest request,HttpServletResponse response,AlarmParam alarmParam){
		if( alarmParam.getDistance() == null){
			alarmParam.setDistance(Constant.DISTANCE);
		}
		if( alarmParam.getCollection() == null){
			alarmParam.setCollection(Constant.COLLECTION);
		}
		if( alarmParam.getSpeed() == null){
			alarmParam.setSpeed(Constant.SPEED);
		}
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		alarmParam.setCreateBy(user.getUserId());
		alarmParam.setCreateTime(new Date());
		Message msg = alarmParamService.insert(alarmParam);
		if(msg.isResult()){
			JCDFWebUtil.Log(request, "新增报警参数信息："+",类型名称="+alarmParam.getApType());
		}
		sendJsonDataToClient(msg, response);
	}
	
	
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request,HttpServletResponse response,AlarmParam alarmParam){
		if( alarmParam.getDistance() == null){
			alarmParam.setDistance(Constant.DISTANCE);
		}
		if( alarmParam.getCollection() == null){
			alarmParam.setCollection(Constant.COLLECTION);
		}
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		alarmParam.setUpdateBy(user.getUserId());
		alarmParam.setUpdateTime(new Date());
		Message msg = alarmParamService.update(alarmParam);
		if(msg.isResult()){
			JCDFWebUtil.Log(request, "更新报警参数信息："+",类型名称="+alarmParam.getApType());
		}
		sendJsonDataToClient(msg, response);
	}
}
