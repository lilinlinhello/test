package com.yunda.gps.monitor.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.ShipmentMonitorVo;

public interface ShipmentMonitorDao {

	Page pageQuery(ShipmentMonitorVo shipment);

}
