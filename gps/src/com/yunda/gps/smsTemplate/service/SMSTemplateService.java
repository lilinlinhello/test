package com.yunda.gps.smsTemplate.service;

import java.util.List;

import com.yunda.app.base.BaseService;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;

public interface SMSTemplateService extends BaseService<SMSTemplateVo>{
	
	/**
	 * 分页查询短信信息模板列表(不分页)
	 * @param smsTemplateVo
	 * @return
	 */
	public List<SMSTemplateVo> getSMSList(SMSTemplateVo smsTemplateVo);

}
