package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.AlarmLoggingMapper;
import com.yunda.gps.monitor.pojo.AlarmLogging;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="alarmLoggingDaoImpl")
public class AlarmLoggingDaoImpl extends JcdfDaoSupport implements AlarmLoggingDao {

	@Autowired
	private AlarmLoggingMapper alarmLoggingMapper;
	
	@Override
	public Page pageQuery(AlarmLogging alarmLogging) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(alarmLoggingMapper, "pageQuery", alarmLogging);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
