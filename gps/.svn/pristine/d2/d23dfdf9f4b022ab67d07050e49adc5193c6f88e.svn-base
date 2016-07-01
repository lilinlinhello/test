package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.ShipmentMapper;
import com.yunda.gps.monitor.pojo.ShipmentPo;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="shipmentDaoImpl")
public class ShipmentDaoImpl extends JcdfDaoSupport implements ShipmentDao {

	@Autowired
	private ShipmentMapper shipmentMapper;
	
	@Override
	public Page pageQuery(ShipmentPo shipment) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(shipmentMapper, "pageQuery", shipment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
