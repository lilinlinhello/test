package com.yunda.gps.smsTemplate.mapper;

import java.util.List;
import java.util.Map;

/**
 * 短信发送临时表
 * @author Admin
 *
 */
public interface SmsSendMapper {
	
	/**
	 * 从短信临时表中查询需要发送的短信
	 * @return
	 */
	public List<Map<String,Object>> queryList(String smsType);
	
	/**
	 *  向短信临时表中插入短信
	 * @param map
	 * @return
	 */
	public Integer insertSmsSend(Map<String,Object> map);
	
	/**
	 * 删除短信临时表中的短信
	 * @param map
	 * @return
	 */
	public Integer deleteSmsSend(Map<String,Object> map);
	
	/**
	 * 查询最大的发送序号
	 * @return
	 */
	public Integer maxSendNo();

}
