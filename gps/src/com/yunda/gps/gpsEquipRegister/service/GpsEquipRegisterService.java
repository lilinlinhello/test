package com.yunda.gps.gpsEquipRegister.service;


import com.yunda.app.entity.vo.Page;
import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;

/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
public interface GpsEquipRegisterService {

	/**
	 * 分页查询GPS设备维护登记表
	 * @param gpsEquipRegister
	 * @return  page
	 */
	public Page pageQuery(GpsEquipRegister gpsEquipRegister);

	/**
	 * 新增GPS设备登记记录
	 * @param gpsEquipRegister
	 * @return   message
	 */
	public Object insertGpsER(GpsEquipRegister gpsEquipRegister);
	
	/**
	 *  修改GPS设备登记记录
	 * @param GpsEquipRegister
	 */
	public Object updateGpsER(GpsEquipRegister gpsEquipRegister);

	/**
	 * 获取一条GPS设备登记记录
	 * @param oaId
	 * @return
	 */
	public GpsEquipRegister selectByOaId(String oaId);

	/**
	 * 批量删除GPS设备维护信息
	 * @param idStr
	 * @return
	 */
	public Object deleteByOaId(String idStr);
}
