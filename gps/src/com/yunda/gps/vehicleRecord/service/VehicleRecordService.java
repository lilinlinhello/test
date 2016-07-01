package com.yunda.gps.vehicleRecord.service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;

public interface VehicleRecordService {
   
	/**
	 * 车辆数据插入记录表
	 * @param record
	 * @return
	 */
	void insert(VehicleRecord record);
	
	/**
	 * 记录获取
	 * @param record
	 * @return
	 */
	VehicleRecord getById(String vehicleId);
	
	/**
	 * 记录表数据修改
	 * @param record
	 * @return
	 */
	int updateById(VehicleRecord record);
	/**
	 * 物理删除数据
	 * @param id
	 * @return
	 */
	int deleteById(String id);
	
	/**
         * 分页查询车辆监控管理
         * @param vehicleRecord
         * @return
         */
        public Page pageQuery(VehicleRecord vehicleRecord);
	
}
