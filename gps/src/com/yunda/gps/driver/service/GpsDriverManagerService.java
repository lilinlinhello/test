package com.yunda.gps.driver.service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.driver.bean.DriverManager;
import com.yunda.gps.driver.dao.GpsDriverManagerDao;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.PropertiesManager;
@Service
public class GpsDriverManagerService{
	@Autowired
	public GpsDriverManagerDao gpsDriverManagerDao;
	
	public Page driverManagerPageQuery(DriverManager driverManager) throws Exception{
		Page page = gpsDriverManagerDao.driverManagerPageQuery(driverManager);
		return page;
	}
	//将返回的单位名称重新封装，从而在转成json之后可以直接供调用
	public List<Map<String, String>> retriveCorpName(){
		List<String> corpList = gpsDriverManagerDao.retriveCorpName();
		Map<String, String> corpMap = null;
		List<Map<String, String>> resultList = new LinkedList<Map<String, String>>();
		for(int i=0;i<corpList.size();i++){
			corpMap = new HashMap<String, String>();
			corpMap.put("id", corpList.get(i));
			corpMap.put("name", corpList.get(i));
			resultList.add(corpMap);
		}
		return resultList;
	}
	
	public Message insertGpsDriver(DriverManager driverManager){
		Message msg = null;
		List<String> list = gpsDriverManagerDao.findByLicenseCard(driverManager.getCarLicense());
		if(list.size() > 0){
			msg = new Message(false,"车牌号"+driverManager.getCarLicense()+"已存在");
		}else{
			gpsDriverManagerDao.insertGpsDriver(driverManager);
			msg = new Message(true,"数据添加成功！");
		}
		return msg;
	}
	
	public Message deleteGpsDriver(String ids){
		Message msg = null;
		try{
			if(null!=ids){
				String[] idsArr = ids.split(",");
				for(int i=0;i<idsArr.length;i++){
					gpsDriverManagerDao.deleteGpsDriver(idsArr[i]);
				}
			}
			msg = new Message(true,"删除数据成功！");
		}catch(Exception e){
			msg = new Message(false,"删除数据失败！");
			e.printStackTrace();
		}
		return msg;
	}
	
	public DriverManager findInfoById(String id){
		return gpsDriverManagerDao.findInfoById(id);
	}
	
	public Message updateGpsDriver(DriverManager driverManager){
		Message msg = null;
		DriverManager dm = this.findInfoById(driverManager.getId());
		if(dm!=null&&dm.getCarLicense()!=null&&dm.getCarLicense().equals(driverManager.getCarLicense())&&
				dm.getUserName()!=null&&dm.getUserName().equals(driverManager.getUserName())&&
				dm.getCorpId()!=null&&dm.getCorpId().equals(driverManager.getCorpId())&&
				dm.getTelephone()!=null&&dm.getTelephone().equals(driverManager.getTelephone())){
			msg = new Message(false,"该条数据未改变,请进行修改");
		}else{
			gpsDriverManagerDao.updateGpsDriver(driverManager);
			msg = new Message(true,"数据更新成功！");
		}
		return msg;
	}
	
	@Async
	public Future<HSSFWorkbook> gpsDriverExport(DriverManager driverManager) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("驾驶员信息表");
		List<DriverManager> costAnalysisList=gpsDriverManagerDao.gpsDriverExport(driverManager);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Row row = sheet.createRow((short) 0);
		String gridHead = PropertiesManager.getProperty("export.gpsEquipRegister.columnNames");
		String fieldNames = PropertiesManager.getProperty("export.gpsEquipRegister.fieldNames");
		String[] tableHeader = gridHead.split(",");
		String[] methodName=fieldNames.split(",");
		for(int i=0;i<tableHeader.length;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(tableHeader[i]);
			cell.setCellStyle(cellStyle);
		}
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 5000);
		int i = 1;
		for(DriverManager dm:costAnalysisList){
			row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(i);
			cell.setCellStyle(cellStyle);
			i++;
			for(int j=1;j<tableHeader.length;j++){
				String methodGet="get"+methodName[j-1];
				Method method=dm.getClass().getMethod(methodGet);
				Object result = method.invoke(dm);
				cell = row.createCell(j);
				cell.setCellValue(result==null?"":result.toString());
				cell.setCellStyle(cellStyle);
			}
		}
		return new AsyncResult<HSSFWorkbook>(wb);
	}
}
