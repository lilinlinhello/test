package com.yunda.gps.smsTemplate.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.smsTemplate.dao.SMSTemplateDao;
import com.yunda.gps.smsTemplate.mapper.SMSTemplateMapper;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;

@Repository(value="smsTemplateDao")
public class SMSTemplateDaoImpl extends JcdfDaoSupport implements SMSTemplateDao {

	@Autowired
	private SMSTemplateMapper smsTemplateMapper;
	
	@Override
	public List<SMSTemplateVo> list() {
		return null;
	}

	@Override
	public SMSTemplateVo get(String id) {
		return smsTemplateMapper.getSMSTemplate(id);
	}

	@Override
	public void insert(SMSTemplateVo t) {
		smsTemplateMapper.smsTemplateInsert(t);
	}

	@Override
	public void update(SMSTemplateVo t) {
		smsTemplateMapper.smsTemplateUpdate(t);
	}

	@Override
	public void deleteById(String id) {
		smsTemplateMapper.smsTemplateDelete(id);
	}

	@Override
	public List<Map<String, Object>> queryList() {
		return smsTemplateMapper.queryList();
	}

	@Override
	public Integer queryBrandAndType(Map<String, Object> map) {
		return smsTemplateMapper.queryBrandAndType(map);
	}

}
