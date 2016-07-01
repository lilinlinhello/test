package com.yunda.gps.monitor.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.gps.monitor.pojo.RunMileage;
import com.yunda.gps.monitor.service.RunMileageService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.PropertiesManager;
import com.yunda.gps.util.StringUtil;

@Controller
@RequestMapping("/runMileage")
public class RunMileageController extends BaseController {
	
	@Resource(name="runMileageServiceImpl")
	private RunMileageService runMileageService;

	@RequestMapping("/listPage.do")
	public String forwardListPage(HttpServletRequest request){

		return "monitor/runMileage_list";
	}
	
	/**
	 * 分页
	 * @param request
	 * @param response
	 * @param lineMileage
	 */
	@RequestMapping("/pageQuery.do")
	public void pageQuery(HttpServletRequest request,HttpServletResponse response,RunMileage runMileage){
		Page page = runMileageService.pageQuery(runMileage);
		if(page != null){
			this.total = page.getTotal();
		}
		sendJsonDataToClient(page, response);
	}
	
	/**
	 * 导出报表
	 * @param response
	 * @param request
	 * @param log
	 */
	@RequestMapping("/export.do")
	public void export(HttpServletResponse response, HttpServletRequest request){
		
		String fileName = PropertiesManager.getProperty("export.runMileage.fileName").replaceAll(".xls", DateUtil.format(new Date(), DateUtil.YYYYMMDD)+".xls");
		String gridName = PropertiesManager.getProperty("export.runMileage.columnNames");
		String fieldName = PropertiesManager.getProperty("export.runMileage.fieldNames");
		String maxsheet = PropertiesManager.getProperty("export.excel.book.maxSheet");
		String maxrow = PropertiesManager.getProperty("export.excel.sheet.maxRow");

		String param = request.getParameter("param");
		JSONObject jsonObject = null;
		if (StringUtil.isNullStr(param)) {
			jsonObject = new JSONObject();
		} else {
			jsonObject = JSONObject.fromObject(param);
		}		
		RunMileage vo = (RunMileage) JSONObject.toBean(jsonObject, RunMileage.class);
		
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
			vo.setPageSize((int) total);
			
			for(int i = 1 ; i <= count ;i++){
				vo.setPageNo(i);
				Page page = runMileageService.pageQuery(vo);
				@SuppressWarnings("unchecked")
				List<RunMileage> list = (List<RunMileage>) page.getRows();
				
				int rownum = 1;//sheet 单元行记录索引
				for(RunMileage value : list){
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
						
						Field field = RunMileage.class.getDeclaredField(fieldele);
						field.setAccessible(true);
						Object fobj = field.get(value);
						this.setCellValue(cell, fobj);
					}
				}
			}
			wb.write(out);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JCDFWebUtil.Log(request, "导出运行里程信息报表");
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 获取sheet
	 * 
	 * @param wb
	 * @param sheetName 表格工作薄的名字
	 * @param gridHead 表格的表头字段名称
	 * @return
	 */
	private HSSFSheet createSheet(HSSFWorkbook wb, String sheetName,String gridHead) {
		// 判断表头信息是否为空
		if (null == gridHead) {
			throw new RuntimeException("表头信息为空,无法导出!");
		}
		HSSFSheet mainSheet = wb.createSheet(sheetName);
		// 设置标题sheet信息的样式
		HSSFFont headFont = wb.createFont();
		headFont.setFontHeightInPoints((short) 20);
		headFont.setColor(HSSFFont.COLOR_RED);
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		// 设置头信息的样式
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 8);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setColor(HSSFFont.COLOR_NORMAL);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(titleFont);
		String[] gridList = gridHead.split(",");
		// 创建第一行
		HSSFRow headRow = mainSheet.createRow(0);
		// 在第一行中添加头信息数据
		int num = 0;
		for (int i = 0; i < gridList.length; i++) {
			String column = gridList[i];
			// 创建单元格
			HSSFCell headCell = headRow.createCell(num);
			HSSFRichTextString h = new HSSFRichTextString(column);
			headCell.setCellValue(h);
			headCell.setCellStyle(headerStyle);
			int columnWidth = 100;
			mainSheet.setColumnWidth(num, columnWidth * 30);
			num++;
		}
		return mainSheet;
	}
	
	private void setCellValue(HSSFCell cell, Object value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (value instanceof java.lang.String) {
			cell.setCellValue(new HSSFRichTextString(value.toString()));
		} else if (value instanceof java.lang.Number) {
			cell.setCellValue(Double.parseDouble(value.toString()));
		} else if (value instanceof java.lang.Boolean) {
			cell.setCellValue(Boolean.parseBoolean(value.toString()));
		} else if (value instanceof java.util.Date) {
			try {
				cell.setCellValue(sdf.parse(value.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			cell.setCellValue("");
		}
	}
}
