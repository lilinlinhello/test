package com.yunda.app.preload.contextinit;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.yunda.app.service.DicService;
import com.yunda.app.util.StaticVar;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.common.location.service.LocationService;

/**
 * 启动预加载信息类
 *@author Administrator
 */
public class ContextLoaderSpringListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private static Log logger = LogFactory.getLog(ContextLoaderSpringListener.class);
	@Resource(name="locationService")
	private LocationService locationService;
	@Resource(name="dicService")
	private   DicService dicService ; 
	//当spring容器初始化完成后就会执行该方法。
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.debug("ConfigLoadListener init......");
//			//***地点信息 预加载
			List<Map<String,String>> provinceMaps = locationService.getAllProvinceName();
			List<Map<String,String>> cityMaps = locationService.getAllCityName();
			List<Map<String,String>> countyMaps = locationService.getAllCountyName();
			//加载市省编码关系
			List<Map<String,String>> cityProvinceMaps = locationService.getAllCityProvince();
			JedisOpExecuter.putSingleObject(StaticVar.GLOBAL_PROVINCE_MAP, provinceMaps) ;
			JedisOpExecuter.putSingleObject(StaticVar.GLOBAL_CITY_MAP, cityMaps) ;
			JedisOpExecuter.putSingleObject(StaticVar.GLOBAL_COUNTY_MAP, countyMaps) ;
			for(Map<String,String> m : cityProvinceMaps){
				String ID = String.valueOf(m.get("ID")) ;
				String PID = String.valueOf(m.get("PID"));
				//单个CITY编码-省编码存放
				JedisOpExecuter.putSingleObject("cityProvince"+ID,PID);
			}
			for(Map<String,String> m : provinceMaps){
				String key = String.valueOf(m.get("ID")) ;
				String value = m.get("NAME")==null?"":m.get("NAME");
				//单个省编码-省名称存放
				JedisOpExecuter.putSingleObject("baseAreaLocal"+key,value);
				//查出各个省下面的市，按照省id+s分类存放
				List<Map<String,Object>> cis = locationService.loadLocation(null,"CN","2",key, null);
				JedisOpExecuter.putSingleObject("baseAreaLocal"+key+"s",cis);
			}
			for(Map<String,String> m : cityMaps){
				String key = String.valueOf(m.get("ID")) ;
				String value = m.get("NAME")==null?"":m.get("NAME");
				//单个存放
				JedisOpExecuter.putSingleObject("baseAreaLocal"+key,value);
				//查出各个市下面的县区 ,按照市id+s分类存放
				List<Map<String,Object>> cous = locationService.loadLocation(null,"CN","3",null, key);
				JedisOpExecuter.putSingleObject("baseAreaLocal"+key+"s",cous);
			}
			for(Map<String,String> m : countyMaps){
				String key = String.valueOf(m.get("ID")) ;
				String value = m.get("NAME")==null?"":m.get("NAME");
				JedisOpExecuter.putSingleObject("baseAreaLocal"+key,value);
			}
			
			//字典信息预加载(字典信息key暂时不加前缀)
			List<Map<String,String>> dics = dicService.getAllDics();
			Set<String> dicTypes = new HashSet<String>();
			for(Map<String,String> m :dics){
				String key = String.valueOf(m.get("ID")) ;
				String value = m.get("NAME")==null?"":m.get("NAME");
				String type = m.get("TYPE")==null?"":m.get("TYPE");
				dicTypes.add(type);
				JedisOpExecuter.putSingleObject(key,value);
			}
			for(String type:dicTypes){
				List<Map<String,String>> dic = dicService.getNameByType(type);
				JedisOpExecuter.putSingleObject(type,dic);
			}	
	}
	

}
