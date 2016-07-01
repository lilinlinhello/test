package com.yunda.gps.vehicleStat.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.util.CustomerContextHolder;
import com.yunda.gps.vehicleStat.mapper.VehicleStatMapper;

@Repository(value="vehicleStatDao")
public class VehicleStatDaoImp extends JcdfDaoSupport implements VehicleStatDao
{
	@Autowired
	private VehicleStatMapper vehicleStatMapper = null;
	

	@Override
    public String queryStatusByVehicleId(String vehicleId)
    {
		String status=null;
		try {
			//切换数据源
	    	CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_B);
			status = vehicleStatMapper.queryStatusByVehicleId(vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
    	return status;
    }


	@Override
	public Date queryOnLineByVehicleId(String vehicleId) {
		Date date=null;
		try {
			//切换数据源
	    	CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_B);
			date= vehicleStatMapper.queryOnLineByVehicleId(vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
    	return date;
	}
}
