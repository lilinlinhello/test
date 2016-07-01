package com.yunda.gps.driver.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.driver.bean.DriverManager;
import com.yunda.gps.driver.mapper.GpsDriverManagerMapper;
@Repository
public class GpsDriverManagerDao extends JcdfDaoSupport{
	@Autowired
	private GpsDriverManagerMapper gpsDriverManagerMapper;
	
	public Page driverManagerPageQuery(DriverManager driver) throws Exception{
		return this.pageQuery(gpsDriverManagerMapper,"driverPageQuery",driver);
	}
	
	public List<String> retriveCorpName(){
		return gpsDriverManagerMapper.retriveCorpName();
	}
	
	public void insertGpsDriver(DriverManager driverManager){
		gpsDriverManagerMapper.insertGpsDriver(driverManager);
	}
	
	public List<String> findByLicenseCard(String param){
		return gpsDriverManagerMapper.findByLicenseCard(param);
	}
	
	public void deleteGpsDriver(String id){
		gpsDriverManagerMapper.deleteGpsDriver(id);
	}
	
	public DriverManager findInfoById(String id){
		return gpsDriverManagerMapper.findInfoById(id);
	}
	
	public void updateGpsDriver(DriverManager driverManager){
		gpsDriverManagerMapper.updateGpsDriver(driverManager);
	}
	
	public List<DriverManager> gpsDriverExport(DriverManager driverManager){
		return gpsDriverManagerMapper.gpsDriverExport(driverManager);
	}
}
