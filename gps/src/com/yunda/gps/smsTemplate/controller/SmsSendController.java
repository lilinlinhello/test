package com.yunda.gps.smsTemplate.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.smsTemplate.service.SmsSendService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.vehicleMaintain.service.VehicleMaintainService;

@Controller
@RequestMapping(value="/smsSend")
public class SmsSendController extends BaseController{
	
	@Resource(name="smsSendService")
	private SmsSendService smsSendService;
	
	@Resource(name="vehicleMaintainService")
	private VehicleMaintainService vehicleMaintainService;
	
	/**
	 * 跳转到选择模板页面
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectSmsSendTemplate.do")
	public String selectSmsSendTemplate(HttpServletResponse response){
		return "smsTemplate/selectTemplate";
	}
	
	
	@RequestMapping("/smsSendInsert.do")
	public void smsSendInsert(HttpServletResponse response,HttpServletRequest request,@RequestParam("id")String id,
			@RequestParam("smsType")String smsType,@RequestParam("smsTemplateId") String smsTemplateId,@RequestParam("isOwn")String isOwn){
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Map<String,Object> map = new HashMap<String,Object>();
		//暂存id,用户查找车辆信息中的sim卡号
		map.put("SMS_ID", id);
		map.put("SMS_TYPE", smsType);
		map.put("smsTemplateId", smsTemplateId);//用户查找短信模板ID  自定义时的短信内容
		map.put("isOwn", isOwn);//用于判断是否是自定义  0 为自定义   1为模板
		map.put("CREATE_BY", loginUser.getUserId());
		map.put("CREATE_TIME", DateUtil.getDateYMDHMs(new Date()));
		Message msg = (Message) smsSendService.insertSmsSend(map);
		sendJsonDataToClient(msg, response);
	}
	
	
	@RequestMapping(value="/smsSendResponse.do",method=RequestMethod.POST,consumes="text/xml")
	public void smsSendResponse(@RequestBody String smsResponse,HttpServletResponse response){
		String xml = "<response><id code=\"1\">成功</response>";
		Message msg = (Message) smsSendService.smsSendResponse(smsResponse);
		if(msg.isResult()){
			sendJsonDataToClient(xml, response);
		}else{
			sendJsonDataToClient("参数格式有误", response);
		}
	}
	

}
