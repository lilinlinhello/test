package com.yunda.gps.monitor.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.AlarmLogging;

public interface AlarmLoggingDao {

	Page pageQuery(AlarmLogging alarmLogging);

}
