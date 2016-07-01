package com.yunda.gps.driver.mapper;

import java.util.List;
import java.util.Map;

import com.yunda.gps.driver.bean.DriverManager;

public interface GpsDriverManagerMapper {
	/**
	 *  分页查询GPS设备维护登记信息 
	 * @param GpsDriverManager数据筛选参数,包括分页参数
	 * @return	分页数据的封装对象
	 */
	List<DriverManager> driverPageQuery(DriverManager driver);
	/**
	 * 查询单位名称
	 */
	List<String> retriveCorpName();
	/**
	 * 插入驾驶员信息
	 */
	void insertGpsDriver(DriverManager driverManager);
	/**
	 * 根据车牌号查询
	 */
	List<String> findByLicenseCard(String param);
	/**
	 * 根据id删除
	 */
	void deleteGpsDriver(String id);
	/**
	 * 根据id查找
	 */
	DriverManager findInfoById(String id);
	/**
	 * 根据id更新
	 */
	void updateGpsDriver(DriverManager driverManager);
	
	/**
	 * 导出
	 */
	List<DriverManager> gpsDriverExport(DriverManager driverManager);
 }