package com.yunda.gps.vehicleStat.mapper;

import java.util.Date;


public interface VehicleStatMapper
{
	 //查询普通状态
    public String queryStatusByVehicleId(String s);
    //查询gps产生时间
    public Date queryOnLineByVehicleId(String s);
}
