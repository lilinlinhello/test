package com.yunda.gps.monitor.service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.AlarmLogging;


public interface AlarmLoggingService {

	Page pageQuery(AlarmLogging alarmLogging);

}
