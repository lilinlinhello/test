package com.yunda.gps.vehicleStat.dao;

import java.util.Date;

public interface VehicleStatDao {
	/**
	 * 获取车辆GPS状态
	 * 
	 * @return
	 */
	public String queryStatusByVehicleId(String vehicleId);

	/**
	 * 获取车辆GPS时间
	 * 
	 * @return
	 */
	public Date queryOnLineByVehicleId(String vehicleId);
}
