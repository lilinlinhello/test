package com.yunda.gps.common.location.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.gps.common.location.dao.LocationDao;
import com.yunda.gps.common.location.mapper.LocationMapper;
import com.yunda.gps.common.location.service.LocationService;
/**
 * 
 * @author luogengxian
 * @date 2015-07-30
 * */
@Service(value="locationService")
@Transactional
public class LocationServiceImpl implements LocationService{
	@Resource(name="locationDao")
	private LocationDao locationDao;
	@Autowired
	private LocationMapper locationMapper;
	
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
	public List<Map<String, Object>> loadLocation(String inputValue, String country,String type,String provinceId,String cityId){
		return locationDao.loadLocation(inputValue,country,type,provinceId,cityId);
	}

	/**
	 * 通过id加载名称
	 * @param getNameById
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public Map<String,String> getNameById(String id,String type) {
		return locationDao.getNameById(id,type);
	}
	/***
	 * 地点编码转换为名称
	 * @param code 地点编码
	 * @param type 转换类型(1:省,2市,3县区)
	 * @return
	 */
	public String codeToName(String code,String type){
		return locationDao.codeToName(code, type);
	}
	/**
	 * 加载全部省名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllProvinceName(){
		return locationDao.getAllProvinceName();
	}
	/**
	 * 加载全部市名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllCityName(){
		return locationDao.getAllCityName();
	}
	/**
	 * 加载全部县区名称
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getAllCountyName(){
		return locationDao.getAllCountyName();
	}
	
	/**
	 * 获取市--省编码map
	 * */
	public List<Map<String,String>> getAllCityProvince(){
		return locationMapper.getAllCityProvince();
	}
	
	/***
	 * 地点名称转换为对应的code
	 * @param name 地点名称
	 * @param type 转换类型(1:省,2市,3县区)
	 * @return
	 */
	public Map<String,Object> nameToCode(Map<String,String> map){
	
		return locationMapper.nameToCode(map);
	}
	
}
