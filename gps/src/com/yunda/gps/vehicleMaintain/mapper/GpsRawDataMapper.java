package com.yunda.gps.vehicleMaintain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GpsRawDataMapper {
    List<Map<String, Object>> findRecentGpsData(@Param("tableName")String tableName,@Param("vehicleId")String vehicleId);
}
