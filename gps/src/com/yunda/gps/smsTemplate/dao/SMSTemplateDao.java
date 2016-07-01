package com.yunda.gps.smsTemplate.dao;

import java.util.List;
import java.util.Map;

import com.yunda.app.base.BaseDao;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;

public interface SMSTemplateDao extends BaseDao<SMSTemplateVo>{
	
	
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
