package com.yunda.gps.alarmParam.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.alarmParam.dao.AlarmParamDao;
import com.yunda.gps.alarmParam.data.AlarmParam;
import com.yunda.gps.alarmParam.mapper.AlarmParamMapper;

@Repository(value="alarmParamDaoImpl")
public class AlarmParamDaoImpl extends JcdfDaoSupport implements AlarmParamDao {

	@Autowired
	private AlarmParamMapper alarmParamMapper;

	@Override
	public Page pageQuery(AlarmParam alarmParam) {
		Page page = null;
		try {
			page = this.pageQuery(alarmParamMapper, "pageQuery", alarmParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	
}
