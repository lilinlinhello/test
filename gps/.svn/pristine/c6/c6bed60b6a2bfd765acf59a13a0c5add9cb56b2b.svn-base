package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.ShipmentMonitorErrorMapper;
import com.yunda.gps.monitor.pojo.ShipmentMonitorError;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="shipmentMonitorErrorDaoImpl")
public class ShipmentMonitorErrorDaoImpl extends JcdfDaoSupport implements
		ShipmentMonitorErrorDao {

	@Autowired
	private ShipmentMonitorErrorMapper shipmentMonitorErrorMapper;

	@Override
	public Page pageQuery(ShipmentMonitorError ship) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(shipmentMonitorErrorMapper, "pageQuery", ship);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
