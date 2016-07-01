package com.yunda.gps.vehicleMaintain.service;

import java.util.List;
import java.util.Map;

import com.yunda.app.base.BaseService;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;

public interface VehicleMaintainService extends BaseService<VehicleMaintainVo>{
	
	/**
	 * 分页查询车辆安装维护
	 * @param vehicleMaintainVo
	 * @return
	 */
	public Page pageQuery(VehicleMaintainVo vehicleMaintainVo);

	/**
	 *  所有车牌号码下拉列表查询 
	 * @param inputValue   车牌号码
	 * @author lilinlin
	 * @date  2016-1-22
	 * @return  List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllCarNum(Map<String, Object> params);

        public Message addVehicleMaintain(VehicleMaintain vehicleMaintain);

        public VehicleMaintain selectById(String id);

        /**
        * 修改车辆安装
        * @param vehicleMaintain
        * @return
        */
        public Message updateVehicleMaintain(VehicleMaintain vehicleMaintain);

        public List<VehicleMaintain> validateVehicleMaintain(VehicleMaintain vehicleMaintain);
        
        public String findVehiclePart(String vehicleId);
        public List<Map<String, Object>> findRecentGpsData(String tableName,String vehicleId);
}
