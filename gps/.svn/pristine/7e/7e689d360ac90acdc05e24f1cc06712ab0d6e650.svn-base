package com.yunda.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.pojo.JcdfLog;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.service.JcdfLogService;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.gps.util.PropertiesManager;

/**
 * 日志管理
 * 
 * @author JXQ
 * @date	2013-11-12
 * 
 */
@Controller
@RequestMapping(value = "/sys/syslog.do")
public class SysLogController extends BaseController{

	/**系统日志管理业务类*/
	@Resource(name="jcdfLogService")
	private JcdfLogService jcdfLogService;
	
	/**
	 * 转发到日志列表页面
	 * 
	 * @param response
	 * 
	 * @return	用户管理页面视图
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String forwardListJsp(HttpServletResponse response){
		return "public/syslog_list";
	}
	
	
	/**
	 * 分页查询用户数据
	 * 
	 * @param response
	 * @param request
	 * @param sysUser
	 */
	@RequestMapping(params="method=pageQuery")
	public void pageQuery(HttpServletResponse response, HttpServletRequest request, JcdfLog log) {
		Page page = jcdfLogService.pageQuery(log);
		this.total = page.getTotal();
		sendJsonDataToClient(page, response);
	}
	
	/**
	 * 导出日志excel
	 * @param response
	 * @param request
	 * @param log
	 */
	@RequestMapping(params="method=exportExcel")
	public void exportLog(HttpServletResponse response, HttpServletRequest request, JcdfLog log){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String fileName = PropertiesManager.getProperty("export.log.fileName").replaceAll(".xls", format.format(new Date())+".xls");
		String gridName = PropertiesManager.getProperty("export.log.columnNames");
		String fieldName = PropertiesManager.getProperty("export.log.fieldNames");
		String maxsheet = PropertiesManager.getProperty("export.excel.book.maxSheet");
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
			long total = this.getTotal();
			int count = (int) (total/Integer.parseInt(maxsheet)) + 1;
			log.setPageSize((int) total);
			
			for(int i = 1 ; i <= count ;i++){
				log.setPageNo(i);
				Page page = jcdfLogService.pageQuery(log);
				@SuppressWarnings("unchecked")
				List<JcdfLog> listLog = (List<JcdfLog>) page.getRows();
				
				int rownum = 1;//sheet 单元行记录索引
				for(JcdfLog jcdfLog : listLog){
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
						
						Field field = JcdfLog.class.getDeclaredField(fieldele);
						field.setAccessible(true);
						Object fobj = field.get(jcdfLog);
						this.setCellValue(cell, fobj);
					}
				}
			}
			wb.write(out);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JCDFWebUtil.Log(request, "导出日志信息报表");
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