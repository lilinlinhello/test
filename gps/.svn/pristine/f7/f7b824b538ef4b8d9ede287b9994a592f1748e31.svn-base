package com.yunda.gps.driver.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.driver.bean.DriverManager;
import com.yunda.gps.driver.service.GpsDriverManagerService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.KeyUtil;
import com.yunda.gps.util.PropertiesManager;
@Controller
@RequestMapping(value="/gpsDriverManager")
public class GpsDriverManagerController extends BaseController{
	@Autowired
	private GpsDriverManagerService gpsDriverManagerService;
	Log log = LogFactory.getLog(GpsDriverManagerController.class);
	
	@RequestMapping(value="DriverManager.do",method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request,HttpServletResponse response){
 		return "gpsDriverManager/gpsDriverManager_list";
	}
	@RequestMapping("/editPage.do")
	public String forwardEditPage(HttpServletRequest request,HttpServletResponse response){
		return "gpsDriverManager/gpsDriverManager_edit";
	}
	@RequestMapping(value="driverManagerQuery.do")
	public void driverManagerPageQuery(HttpServletRequest request,HttpServletResponse response,DriverManager driverManager){
		Page page=null;
		try {
			page = gpsDriverManagerService.driverManagerPageQuery(driverManager);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		sendJsonDataToClient(page,response);
	}
	
	@RequestMapping(value="retriveCorpName.do")
	public void retriveCorpName(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, String>>  corpList = gpsDriverManagerService.retriveCorpName();
		sendJsonDataToClient(corpList,response);
	}
	
	@RequestMapping(value="addGpsDriverManager.do")
	public String forwardAddJsp(HttpServletRequest request,HttpServletResponse response){
		return "gpsDriverManager/gpsDriverManager_add";
	}
	
	//添加驾驶员信息
	@RequestMapping(value="insertGpsDriver.do")
	public void insertGpsDriver(HttpServletRequest request,HttpServletResponse response, DriverManager driverManager){
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		driverManager.setCreateUser(user.getUserId());
		driverManager.setCreateTime(DateUtil.format(new Date()));
		driverManager.setId(KeyUtil.getIdByTime(3));
		driverManager.setDeleteFlag(Constant.NO_DELETE_FLAG);
		Message msg = gpsDriverManagerService.insertGpsDriver(driverManager);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "新增驾驶员记录：驾驶员名称为："+driverManager.getUserName());
		}
		sendJsonDataToClient(msg,response);
	}
	
	@RequestMapping(value="deleteGpsDriver.do")
	public void deleteGpsDriver(HttpServletRequest request,HttpServletResponse response,String ids){
		Message msg = gpsDriverManagerService.deleteGpsDriver(ids);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除驾驶员记录：驾驶员编码为："+ids);
		}
		sendJsonDataToClient(msg,response);
	}
	
	@RequestMapping(value="findGpsDriverById.do")
	public void findInfoById(HttpServletRequest request,HttpServletResponse response,String id){
		DriverManager dm = gpsDriverManagerService.findInfoById(id);
		sendJsonDataToClient(dm,response);
	}
	
	@RequestMapping(value="updateGpsDriver.do")
	public void updateGpsDriver(HttpServletRequest request,HttpServletResponse response,DriverManager driverManager){
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		driverManager.setUpdateUser(user.getUserId());
		driverManager.setUpdateTime(DateUtil.format(new Date()));
		Message msg = gpsDriverManagerService.updateGpsDriver(driverManager);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改驾驶员信息：驾驶员编码为："+driverManager.getId());
		}
		sendJsonDataToClient(msg,response);
	}
	
	@RequestMapping(value="gpsDriverExport.do")
	public void gpsDriverExport(HttpServletRequest request,HttpServletResponse response,DriverManager driverManager){
		HSSFWorkbook wb=null;
		try{
			Future<HSSFWorkbook> exportResult = gpsDriverManagerService.gpsDriverExport(driverManager);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
			String gridName = PropertiesManager.getProperty("export.gpsEquipRegister.fileName");
			String displayName = gridName + date + ".xls";
			response.addHeader("Content-Disposition","attachment;filename="+ new String(displayName.getBytes("UTF-8"),"ISO-8859-1"));
			while(true){
				if(exportResult.isDone()){
					 wb= exportResult.get();
					 wb.write(response.getOutputStream());
					 break;
				}
			}
			if (response.getOutputStream() != null){
				response.getOutputStream().flush();
			}
			response.flushBuffer();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
