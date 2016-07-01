package com.yunda.gps.monitor.service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.ShipmentMonitorVo;

public interface ShipmentMonitorService {

	Page pageQuery(ShipmentMonitorVo shipment);

}
