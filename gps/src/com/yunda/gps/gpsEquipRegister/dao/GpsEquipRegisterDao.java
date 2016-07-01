package com.yunda.gps.gpsEquipRegister.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;

/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
public interface GpsEquipRegisterDao {

	/**
	 * 分页查询GPS设备维护登记表
	 * @param gpsEquipRegister
	 * @return
	 * @throws Exception 
	 */
	public Page pageQuery(GpsEquipRegister gpsEquipRegister) throws Exception;

	/**
	 * 新增GPS设备登记记录
	 * @param gpsEquipRegister
	 */
	public void insertGpsER(GpsEquipRegister gpsEquipRegister);

	/**
	 * 查询OaId对应的记录
	 * @param oaId
	 * @return GpsEquipRegister
	 */
	public GpsEquipRegister selectByOaId(String oaId);
	
	/**
	 *  修改GPS设备登记记录
	 * @param GpsEquipRegister
	 */
	public void updateGpsER(GpsEquipRegister gpsEquipRegister);

	/**
	 * 批量删除GPS设备维护信息
	 * @param idStr
	 * @return
	 */
	public void deleteByOaId(String[] idStr);
}
