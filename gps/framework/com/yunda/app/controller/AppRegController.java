package com.yunda.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.service.AppRegService;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.PropertiesManager;
@Controller
@RequestMapping(value="/appReg/appReg.do")
public class AppRegController extends BaseController{
	
	/**接口管理业务类*/
	@Autowired
	private AppRegService appRegService;
	Log log = LogFactory.getLog(AppRegController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request,HttpServletResponse response){
 		return "appReg/appReg_list";
	}

	/**
	 * 通过ak查询应用注册信息
	 * 
	 * @param response
	 * @param ak
	 */
	@RequestMapping(params="method=get")
	public void get(HttpServletResponse response, String ak) {
		sendJsonDataToClient(appRegService.get(ak), response);
	}
	
	@RequestMapping(params="method=forwardEditJsp")
	public String forwardEditJsp(HttpServletResponse response,HttpServletRequest request){
		return "appReg/appReg_edit";
	} 
	
	
	/**
	 * 根据应用注册appReg进行应用注册信息修改
	 * 
	 * @param response
	 * @param appReg
	 */
	@RequestMapping(params="method=update")
	public void update(HttpServletResponse response, HttpServletRequest request, AppReg appReg) {
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
        appReg.setUpdateBy(user.getUserId());
        appReg.setUpdateTime(new Date());
		Message msg = (Message)appRegService.update(appReg);
		sendJsonDataToClient(msg, response);
	}
	
	@RequestMapping(params="method=forwardAddJsp")
	public String forwardAddJsp(HttpServletResponse response,HttpServletRequest request){
		return "appReg/appReg_add";
	} 
	
	/**
	 * 分页查询数据
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, AppReg appReg) {
		Page page = appRegService.pageQuery(appReg);
		this.total = page.getTotal();
		sendJsonDataToClient(page, response);
	}
	

    /**
     * 应用新增
     * 
     * @param response
     * @param AppReg
     */
    @RequestMapping(params = "method=insert")
    public void insert(HttpServletResponse response, HttpServletRequest request, AppReg appReg) {
        LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
        appReg.setCreateBy(user.getUserId());
        appReg.setUpdateBy(user.getUserId());
        appReg.setCreateTime(new Date());
        appReg.setUpdateTime(new Date());
        appReg.setDelFlag(Constant.NO_DELETE_FLAG); // 未删除状态
        Message msg = (Message) appRegService.insert(appReg);
		sendJsonDataToClient(msg, response);
    }
	
	/**
	 * 根据应用注册编号(regId)停用应用
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping(params="method=deleteById")
	public void deleteById(HttpServletResponse response, HttpServletRequest request, String regIds) {
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = (Message)appRegService.deleteById(regIds,user.getUserId());
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 根据应用注册编号(regId)启用应用
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping(params="method=useById")
	public void useById(HttpServletResponse response, HttpServletRequest request, String regIds) {
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		Message msg = (Message)appRegService.useById(regIds,user.getUserId());
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 导出excel
	 * @param response
	 * @param request
	 * @param appReg
	 */
	@RequestMapping(params="method=exportExcel")
	public void exportLog(HttpServletResponse response, HttpServletRequest request, AppReg appReg){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String fileName = PropertiesManager.getProperty("export.appReg.fileName").replaceAll(".xls", format.format(new Date())+".xls");
		String gridName = PropertiesManager.getProperty("export.appReg.columnNames");
		String fieldName = PropertiesManager.getProperty("export.appReg.fieldNames");
		String maxrow = PropertiesManager.getProperty("export.excel.sheet.maxRow");

		OutputStream out = null;
		response.setContentType("application/vnd.ms-excel");
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
			out =  response.getOutputStream();
			
			//生成sheet
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = this.createSheet(wb, fileName, gridName);
			
			//计算所需查询次数
			appReg.setPageSize((int)this.getTotal());
			
			appReg.setPageNo(1);
			Page page = appRegService.pageQuery(appReg);
			@SuppressWarnings("unchecked")
			List<AppReg> listLog = (List<AppReg>) page.getRows();
			
			int rownum = 1;//sheet 单元行记录索引
			for(AppReg reg : listLog){
				if(rownum > Integer.parseInt(maxrow)){
					sheet = this.createSheet(wb, fileName+1, gridName);
					rownum = 1;						
				}
				
				HSSFRow row = sheet.createRow(rownum++);
				int cellnum = 0;
				for(String fieldele : fieldName.split(",")){
					HSSFCell cell = row.createCell(cellnum++);
					CellStyle style = cell.getCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					
					Field field = AppReg.class.getDeclaredField(fieldele);
					field.setAccessible(true);
					Object fobj = field.get(reg);
					this.setCellValue(cell, fobj);
				}
			}
			wb.write(out);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JCDFWebUtil.Log(request, "导出应用注册信息报表");
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void setCellValue(HSSFCell cell, Object value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		if(value instanceof java.lang.String){
			cell.setCellValue(new HSSFRichTextString(value.toString()));
		}else if(value instanceof java.lang.Number){
			cell.setCellValue(Double.parseDouble(value.toString())) ;
		}else if(value instanceof java.lang.Boolean){
			cell.setCellValue(Boolean.parseBoolean(value.toString())) ;
		}else if(value instanceof java.util.Date){			
			cell.setCellValue(sdf.format((Date)value));
		}else{
			cell.setCellValue("");
		}
	}

	private HSSFSheet createSheet(HSSFWorkbook wb, String fileName, String gridName) {
		HSSFSheet sheet = wb.createSheet(fileName);
		sheet.setDefaultColumnWidth(20);
		HSSFFont headFont = wb.createFont();
		headFont.setColor(HSSFFont.COLOR_RED);
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setFont(headFont);
		
		
		HSSFRow headRow = sheet.createRow(0);
		int column = 0;
		for(String gridlist : gridName.split(",")){
			HSSFCell cell = headRow.createCell(column++);
			cell.setCellValue(gridlist);
			cell.setCellStyle(headStyle);
		}
		
		return sheet;
	}
	
}

