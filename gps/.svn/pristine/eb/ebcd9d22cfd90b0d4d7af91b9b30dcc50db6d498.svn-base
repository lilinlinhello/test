package com.yunda.gps.common.location.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.common.location.dao.LocationDao;
import com.yunda.gps.common.location.mapper.LocationMapper;
/**
 * 地点信息数据库访问接口实现
 * 
 * @author luogengxian
 * @date	2015-07-30
 *
 */
@Repository(value="locationDao")
public class LocationDaoImpl implements LocationDao{
	@Autowired
	private LocationMapper locationMapper ;
	
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
	public List<Map<String, Object>> loadLocation(String inputValue,String country,String type,String provinceId,String cityId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("inputValue",inputValue);
		params.put("country",country);
		params.put("type",type);
		params.put("provinceId",provinceId);
		params.put("cityId",cityId);
		return locationMapper.loadLocation(params);
	}
	/**
	 * 通过id加载名称
	 * @param String id
	 * @param Strin type
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public Map<String,String> getNameById(String id,String type){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("type", type);
		paramMap.put("id", id);
		return locationMapper.getNameById(paramMap);
	}
	/**
	 * 加载全部省名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllProvinceName(){
		return locationMapper.getAllProvinceName();
	}
	/**
	 * 加载全部市名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllCityName(){
		return locationMapper.getAllCityName();
	}
	/**
	 * 加载全部县区名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllCountyName(){
		return locationMapper.getAllCountyName();
	}
	/***
	 * 地点编码转换为名称
	 * @param code 地点编码
	 * @param type 转换类型(1:省,2市,3县区)
	 * @return
	 */
	public String codeToName(String code,String type){
		if(null==code || type==null)
			return "";
		String name = null==JedisOpExecuter.getSingleObject("baseAreaLocal"+code)?"":(String)JedisOpExecuter.getSingleObject("baseAreaLocal"+code);
		if("".equals(name)){
			Map<String,String> m = this.getNameById(code,type);
			if(null!=m){
				name = m.get("NAME")==null?"":m.get("NAME");
				JedisOpExecuter.putSingleObject("baseAreaLocal"+code,name);
			}
		}
		return name ;
	}
}
