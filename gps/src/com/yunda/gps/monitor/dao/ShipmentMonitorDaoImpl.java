package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.ShipmentMonitorMapper;
import com.yunda.gps.monitor.pojo.ShipmentMonitorVo;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="shipmentMonitorDaoImpl")
public class ShipmentMonitorDaoImpl extends JcdfDaoSupport implements ShipmentMonitorDao {

	@Autowired
	private ShipmentMonitorMapper shipmentMonitorMapper;
	
	@Override
	public Page pageQuery(ShipmentMonitorVo shipment) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(shipmentMonitorMapper, "pageQuery", shipment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
