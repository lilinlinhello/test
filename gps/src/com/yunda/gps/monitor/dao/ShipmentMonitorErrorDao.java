package com.yunda.gps.monitor.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.ShipmentMonitorError;

public interface ShipmentMonitorErrorDao {

	Page pageQuery(ShipmentMonitorError ship);

}
