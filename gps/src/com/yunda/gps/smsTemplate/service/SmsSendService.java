package com.yunda.gps.smsTemplate.service;

import java.util.Map;


public interface SmsSendService {
	
	/**
	 * 定时查询临时表进行短信发送
	 */
	public void autoSmsTime();
	
	/**
	 *  向短信临时表中插入短信
	 * @param map
	 * @return
	 */
	public Object insertSmsSend(Map<String,Object> map);
	
	/**
	 * 短信回复
	 * @return
	 */
	public Object smsSendResponse(String xml);
	
}
