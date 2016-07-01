package com.yunda.gps.vehicleMaintain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;

public interface VehicleMaintainMapper {
	
	/**
	 * 分页查询车辆安装维护
	 * @param vehicleMaintainVo
	 * @return
	 */
	public List<VehicleMaintain> pageQuery(VehicleMaintainVo vehicleMaintainVo);
	/**
	 * 根据ID 删除车辆安装维护信息
	 * @param id
	 * @return
	 */
    void deleteByPrimaryKey(String id);
    /**
     * 插入车辆安装维护信息
     * @param record
     * @return
     */
    int insert(VehicleMaintain vehicleMaintain);
    
    int insertSelective(VehicleMaintain record);
    /**
     * 根据id 查询车辆安装维护信息
     * @param id
     * @return
     */
    VehicleMaintainVo selectByPrimaryKey(String id);
    /**
     * 根据id 查询车辆安装维护信息
     * @param id
     * @return
     */
    VehicleMaintain selectById(String id);
    
    /**
     *根据id更新车辆安装维护信息 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(VehicleMaintain record);
    /**
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(VehicleMaintain record);
	/**
	 * 所有车牌号码下拉列表查询 
	 * @param params   
	 * @author lilinlin
	 * @date  2016-1-22
	 * @return  List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllCarNum(Map<String,Object> params);
	
	 /**
	  * @param vehicleMaintain
	  * @return
	  */
    public List<VehicleMaintain> validateVehicleMaintain(VehicleMaintain vehicleMaintain);
    
    /**
     * 根据车牌号查询该车辆安装维护信息
     * @param licensecard
     * @return
     */
    VehicleMaintain selectByCard(String licensecard);
    
    String findVehiclePart(String vehicleId);

    
}