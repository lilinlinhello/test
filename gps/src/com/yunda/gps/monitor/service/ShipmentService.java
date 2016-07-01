package com.yunda.gps.monitor.service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.pojo.ShipmentPo;

public interface ShipmentService {

	Page pageQuery(ShipmentPo shipment);

}
