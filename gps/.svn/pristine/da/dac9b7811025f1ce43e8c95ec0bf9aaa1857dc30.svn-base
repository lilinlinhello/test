package com.yunda.gps.smsTemplate.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.gps.smsTemplate.dao.SmsRecordDao;
import com.yunda.gps.smsTemplate.mapper.SmsRecordMapper;

@Repository(value="smsRecordDao")
public class SmsRecordDaoImpl implements SmsRecordDao{
	
	@Autowired
	private SmsRecordMapper smsRecordMapper;
	

	@Override
	public void insertSmsRecord(Map<String, Object> map) {
		smsRecordMapper.insertSmsRecord(map);
	}
	
	

}
