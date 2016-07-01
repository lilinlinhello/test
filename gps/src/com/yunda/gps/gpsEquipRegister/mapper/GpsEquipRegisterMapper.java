package com.yunda.gps.gpsEquipRegister.mapper;

import java.util.List;

import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;


/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
public interface GpsEquipRegisterMapper {
	
	/**
	 *  分页查询GPS设备维护登记信息 
	 * @param GpsEquipRegister	数据筛选参数,包括分页参数
	 * @return	分页数据的封装对象
	 */
	public List<GpsEquipRegister> pageQuery(GpsEquipRegister gpsEquipRegister);
   
	/**
	 *  查询OaId对应的记录是否存在
	 * @return GpsEquipRegister
	 */
	public GpsEquipRegister selectByOaId(String OaId);
	
	/**
	 *  新增GPS设备登记记录
	 * @param GpsEquipRegister 
	 */
	public int insertGpsER(GpsEquipRegister gpsEquipRegister);
	 
	/**
	 *  修改GPS设备登记记录
	 * @param GpsEquipRegister
	 */
	public int updateGpsER(GpsEquipRegister gpsEquipRegister);
	
	/**
	 *  删除GPS设备登记记录
	 * @param ids   
	 */
	public void deleteByOaId(String[] ids);
   
   
    
}