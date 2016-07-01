package com.yunda.gps.smsTemplate.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.gps.smsTemplate.dao.SmsSendDao;
import com.yunda.gps.smsTemplate.mapper.SmsSendMapper;

@Repository(value="smsSendDao")
public class SmsSendDaoImpl implements SmsSendDao {
	
	@Autowired
	private SmsSendMapper smsSendMapper;
	
	/**
	 * 从临时表中查询短信
	 */
	@Override
	public List<Map<String, Object>> queryList(String smsType) {
		return smsSendMapper.queryList(smsType);
	}

	/**
	 * 向临时表中插入短信
	 */
	@Override
	public Integer insertSmsSend(Map<String, Object> map) {
		return smsSendMapper.insertSmsSend(map);
	}
	/**
	 * 删除临时表中的短信记录
	 */
	@Override
	public Integer deleteSmsSend(Map<String, Object> map) {
		return smsSendMapper.deleteSmsSend(map);
	}

	@Override
	public Integer maxSendNo() {
		return smsSendMapper.maxSendNo();
	}

}
