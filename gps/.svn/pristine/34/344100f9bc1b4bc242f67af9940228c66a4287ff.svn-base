package com.yunda.gps.common.location.dao;

import java.util.List;
import java.util.Map;
/**
 * 地点信息数据库访问接口，负责定义接口操作协议
 * luogengxian
 * @date	2015-07-30
 * 
 */
public interface LocationDao {
	/**
	 * 加载地点信息
	 * @param inputValue
	 * @param country
	 * @param type
	 * @param provinceId
	 * @param cityId
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> loadLocation(String inputValue, String country,String type,String provinceId,String cityId);
	/**
	 * 通过id加载名称
	 * @param String id
	 * @param Strin type
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	Map<String,String> getNameById(String id,String type);
	/**
	 * 加载全部省名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<Map<String,String>> getAllProvinceName();
	/**
	 * 加载全部市名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<Map<String,String>> getAllCityName();
	/**
	 * 加载全部县区名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<Map<String,String>> getAllCountyName();
	/***
	 * 地点编码转换为名称
	 * @param code 地点编码
	 * @param type 转换类型(1:省,2市,3县区)
	 * @return
	 */
	public String codeToName(String code,String type);
}
