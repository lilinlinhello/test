package com.yunda.gps.vehicleMaintain.dao;

import java.util.List;
import java.util.Map;

import com.yunda.app.base.BaseDao;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;

public interface VehicleMaintainDao extends BaseDao<VehicleMaintainVo>{
	
	/**
	 * 分页查询车辆安装维护
	 * @param vehicleMaintainVo
	 * @return
	 */
	public Page pageQuery(VehicleMaintainVo vehicleMaintainVo) throws Exception;

	/**
	 *  所有车牌号码下拉列表查询 
	 * @param inputValue   车牌号码
	 * @author lilinlin
	 * @date  2016-1-22
	 * @return  List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllCarNum(Map<String, Object> params);
	
	/**
	 * 根据Id查询该对象
	 * @param id
	 * @author lilinlin
	 * @date 2016-1-25
	 */
	public VehicleMaintainVo get(String id);

        public void addVehicleMaintain(VehicleMaintain vehicleMaintain); 

}
