package com.yunda.gps.alarmParam.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.alarmParam.data.AlarmParam;

public interface AlarmParamDao {

	Page pageQuery(AlarmParam alarmParam);

}
