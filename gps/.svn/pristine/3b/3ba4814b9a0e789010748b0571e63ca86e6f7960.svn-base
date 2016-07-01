package com.yunda.gps.vehicleMaintain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.util.CustomerContextHolder;
import com.yunda.gps.vehicleMaintain.mapper.GpsRawDataMapper;

@Repository
public class GpsRawDataDao extends JcdfDaoSupport{
	@Autowired
	private GpsRawDataMapper gpsRawDataMapper;
    public List<Map<String, Object>> findRecentGpsData(String tableName,String vehicleId){
        List<Map<String, Object>> list = null;
    	try {
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_B);
			System.out.println(CustomerContextHolder.getCustomerType());
			list = gpsRawDataMapper.findRecentGpsData(tableName,vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
        return list;
    }
}
