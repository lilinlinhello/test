package com.yunda.gps.smsTemplate.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;
import com.yunda.gps.smsTemplate.service.SMSTemplateService;
import com.yunda.gps.util.DateUtil;

@Controller
@RequestMapping(value="/smsTemplate")
public class SMSTemplateController extends BaseController{
	
	@Resource(name="smsTemplateService")
	private SMSTemplateService smsTemplateService;
	
	
	@RequestMapping(value="listPage.do")
	public String forwardToPage(HttpServletResponse response,HttpServletRequest request){
		return "smsTemplate/smsTemplate_list";
	}
	
	@RequestMapping(value="addSmsTemplate.do")
	public String forwardToAddJsp(HttpServletResponse response,HttpServletRequest request){
		return "smsTemplate/smsTemplate_add";
	}
	
	@RequestMapping(value="smsTemplateEdit.do")
	public String forwardToEditJsp(HttpServletResponse response,HttpServletRequest request){
		return "smsTemplate/smsTemplate_edit";
	}
	/**
	 * 查询短信模板(不分页)
	 * @param response
	 * @param request
	 * @param smsTemplateVo
	 * @throws Exception
	 */
	@RequestMapping(value="smsTemplateQuery.do")
	public void smsTemplateList(HttpServletResponse response,HttpServletRequest request,SMSTemplateVo smsTemplateVo){
		List<SMSTemplateVo> smsList = smsTemplateService.getSMSList(smsTemplateVo);
		sendJsonDataToClient(smsList, response);
	}
	/**
	 * 插入短信模板
	 * @param response
	 * @param request
	 * @param smsTemplateVo
	 */
	@RequestMapping(value="smsTemplateInsert.do")
	public void smsTemplateInsert(HttpServletResponse response,HttpServletRequest request,SMSTemplateVo smsTemplateVo){
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		smsTemplateVo.setCreateBy(loginUser.getUserId());
		smsTemplateVo.setCreateTime(DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss));
		Message msg = (Message) smsTemplateService.insert(smsTemplateVo);
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 查看短信记录
	 * @param response
	 * @param request
	 * @param smsId
	 */
	@RequestMapping(value="smsTemplateGet.do")
	public void smsTemplateGet(HttpServletResponse response,HttpServletRequest request,@RequestParam("smsId")String smsId){
		sendJsonDataToClient(smsTemplateService.get(smsId), response);
	}
	
	/**
	 * 查看短信记录的信息
	 * @param response
	 * @param request
	 * @param smsId
	 */
	@RequestMapping(value="smsTemplateGetMsg.do")
	public void smsTemplateGetMsg(HttpServletResponse response,HttpServletRequest request,@RequestParam("smsId")String smsId){
		String smsContent = smsTemplateService.get(smsId).getSmsContent();
		String smsNewContent = null;
		if(smsContent.contains("<") && smsContent.contains(">")){//转译
			smsNewContent = smsContent.replace("<", "&lt;");
			smsNewContent = smsNewContent.replace(">", "&gt;");
		}
		sendJsonDataToClient(smsNewContent==null?smsContent:smsNewContent, response);
	}
	/**
	 * 更新短信信息模板
	 * @param response
	 * @param request
	 * @param smsTemplateVo
	 */
	@RequestMapping(value="smsTemplateUpdate.do")
	public void smsTemplateEdit(HttpServletResponse response,HttpServletRequest request,SMSTemplateVo smsTemplateVo){
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		smsTemplateVo.setUpdateBy(loginUser.getUserId());
		smsTemplateVo.setUpdateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
		Message msg = (Message) smsTemplateService.update(smsTemplateVo);
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 根据smsId 删除短信模板
	 * @param response
	 * @param request
	 * @param smsId
	 */
	@RequestMapping(value="smsTemplateDelete.do")
	public void smsTemplateDelete(HttpServletResponse response,HttpServletRequest request,@RequestParam("smsId")String smsId){
		sendJsonDataToClient((Message)smsTemplateService.deleteById(smsId), response);
	}
	
	

}
