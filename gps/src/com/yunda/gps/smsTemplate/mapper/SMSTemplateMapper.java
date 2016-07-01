package com.yunda.gps.smsTemplate.mapper;

import java.util.List;
import java.util.Map;

import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;

public interface SMSTemplateMapper {
	
	/**
	 * 查询短信信息模板列表
	 * @param smsTemplateVo
	 * @return
	 */
	public List<SMSTemplateVo> getSMSList(SMSTemplateVo smsTemplateVo);
	
	/**
	 * 插入短信信息模板
	 * @param smsTemplateVo
	 * @return
	 */
	public Integer smsTemplateInsert(SMSTemplateVo smsTemplateVo);
	
	public SMSTemplateVo getSMSTemplate(String smsId);
	/**
	 * 更新短信模板
	 * @param smsTemplateVo
	 * @return
	 */
	public Integer smsTemplateUpdate(SMSTemplateVo smsTemplateVo);
	/**
	 * 逻辑删除短信模板信息
	 * @param smsId
	 * @return
	 */
	public Integer smsTemplateDelete(String smsId);
	
	/**
	 * 查询所有的短信模板信息
	 * @return
	 */
	public List<Map<String,Object>> queryList();
	
	/**
	 * 根据设备品牌和设备型号判断该模板是否存在
	 * @param map
	 * @return
	 */
	public Integer queryBrandAndType(Map<String,Object>map);
}
