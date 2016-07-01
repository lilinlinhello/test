package com.yunda.gps.monitor.mapper;

import java.util.List;

import com.yunda.gps.monitor.pojo.ShipmentMonitorPo;
import com.yunda.gps.monitor.pojo.ShipmentMonitorVo;


public interface ShipmentMonitorMapper {
	/**
	 * 分页查询
	 * @param shipment
	 * @return
	 */
	List<ShipmentMonitorPo> pageQuery(ShipmentMonitorVo shipment);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ShipmentMonitorPo record);

    int insertSelective(ShipmentMonitorPo record);

    ShipmentMonitorPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShipmentMonitorPo record);

    int updateByPrimaryKey(ShipmentMonitorPo record);
}