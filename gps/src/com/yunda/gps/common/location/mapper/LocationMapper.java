package com.yunda.gps.common.location.mapper;

import java.util.List;
import java.util.Map;
/**
 * 地点信息的Mybatis映射工具类
 * 
 * @author luogengxian
 * @date	2015-07-30
 *
 */
public interface LocationMapper {
	
	/**
	 * 加载地点信息
	 * @param params
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> loadLocation(Map<String,Object> params);
	
	/**
	 * 通过id加载名称
	 * @param Map<String,String> paramMap
	 * @author luogengxian
	 * @date :2015-07-30
	 * @returnSrring
	 */
	Map<String,String> getNameById(Map<String,String> paramMap);
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
	/**
	 * 加载city---province编码关系
	 * @author 郭端端
	 * **/
	List<Map<String,String>> getAllCityProvince();
	/**
	 * 导入excel对省市区名称进行转换code
	 * @author gdd
	 * **/
	Map<String,Object> nameToCode(Map<String,String> paramMap);
}
