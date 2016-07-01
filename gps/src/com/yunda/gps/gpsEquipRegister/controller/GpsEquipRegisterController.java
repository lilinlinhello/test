package com.yunda.gps.gpsEquipRegister.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;
import com.yunda.gps.gpsEquipRegister.service.GpsEquipRegisterService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.PropertiesManager;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleMaintain.service.VehicleMaintainService;

/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
@Controller
@RequestMapping(value = "/gpsEquipRegister")
public class GpsEquipRegisterController extends BaseController {

	Log log = LogFactory.getLog(GpsEquipRegisterController.class);

	@Resource(name = "gpsEquipRegisterService")
	private GpsEquipRegisterService gpsEquipRegisterService;

	@Resource(name="vehicleMaintainService")
	private VehicleMaintainService vehicleMaintainService;
	
	/**
	 * 跳转到GPS设备维护登记列表页面
	 */
	@RequestMapping("/equipRegister.do")
	public String toEquipRegJsp(HttpServletRequest request,HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("equipStatus","dic.equipStatusType");//设备当前状态
		params.put("repairReason","dic.repairReasonType");//维修原因
		List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
		request.setAttribute("dictionarys", JSONArray.fromObject(dics));
		return "gpsEquipRegister/gpsEquipRegister_list";
	}
	
	/**
	 * 分页查询GPS设备维护登记表
	 */
	@RequestMapping("/queryEquipRegister.do")
	public void queryEquipRegister(HttpServletRequest request,HttpServletResponse response,GpsEquipRegister gpsEquipRegister){
		Page page = null;
		try {
			String time=gpsEquipRegister.getQuickTime();
			 Calendar currentDate1 = new GregorianCalendar();
			 Calendar currentDate2 = new GregorianCalendar(); 
			 currentDate1.set(Calendar.HOUR_OF_DAY, 0);  
			 currentDate1.set(Calendar.MINUTE, 0);  
			 currentDate1.set(Calendar.SECOND, 0);
			 currentDate2.set(Calendar.HOUR_OF_DAY, 23);  
			 currentDate2.set(Calendar.MINUTE, 59);  
			 currentDate2.set(Calendar.SECOND, 59); 
			if(StringUtil.isNotNullStr(time)){
				if(time.equals("0")){//快捷设置 0 ：当天
				    gpsEquipRegister.setMinInputTime(DateUtil.format((Date)currentDate1.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss));  //最小录入时间
				    gpsEquipRegister.setMaxInputTime(DateUtil.format((Date)currentDate2.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss)); //最大录入时间
				}
				if(time.equals("1")){//快捷设置 1 ：本周
					 currentDate1.setFirstDayOfWeek(Calendar.MONDAY);  
					 currentDate1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
					 gpsEquipRegister.setMinInputTime(DateUtil.format((Date)currentDate1.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss));	
					 currentDate2.setFirstDayOfWeek(Calendar.MONDAY);  
					 currentDate2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					 gpsEquipRegister.setMaxInputTime(DateUtil.format((Date)currentDate2.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss)); //最大录入时间
				}
				if(time.equals("2")){//快捷设置 2 ：上周
					 currentDate1.add(Calendar.WEEK_OF_YEAR,-1);  
					 currentDate2.add(Calendar.WEEK_OF_YEAR,-1);  
					 currentDate1.setFirstDayOfWeek(Calendar.MONDAY);  
					 currentDate2.setFirstDayOfWeek(Calendar.MONDAY);  
					 currentDate1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
					 gpsEquipRegister.setMinInputTime(DateUtil.format((Date)currentDate1.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss));	
					 currentDate2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					 gpsEquipRegister.setMaxInputTime(DateUtil.format((Date)currentDate2.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss)); //最大录入时间
				}
				
				if(time.equals("3")){//快捷设置 3 ：本月
					 currentDate1.set(Calendar.DAY_OF_MONTH,1);  
					 gpsEquipRegister.setMinInputTime(DateUtil.format((Date)currentDate1.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss));	
					 currentDate2.set(currentDate2.get(Calendar.YEAR),currentDate2.get(Calendar.MONTH),currentDate2.getActualMaximum(Calendar.DAY_OF_MONTH));
					 gpsEquipRegister.setMaxInputTime(DateUtil.format((Date)currentDate2.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss)); //最大录入时间
				}
				if(time.equals("4")){//快捷设置 4：上月
					 currentDate1.add(Calendar.MONTH, -1);  
					 currentDate2.add(Calendar.MONTH, -1);
					 currentDate1.set(Calendar.DAY_OF_MONTH,1);  
					 gpsEquipRegister.setMinInputTime(DateUtil.format((Date)currentDate1.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss));	
					 currentDate2.set(currentDate2.get(Calendar.YEAR),currentDate2.get(Calendar.MONTH),currentDate2.getActualMaximum(Calendar.DAY_OF_MONTH));
					 gpsEquipRegister.setMaxInputTime(DateUtil.format((Date)currentDate2.getTime().clone(),DateUtil.YYYY_MM_DD_HH_mm_ss)); //最大录入时间
				}
			}
			page = gpsEquipRegisterService.pageQuery(gpsEquipRegister);
		} catch (Exception e) {
			log.fatal(e.getMessage());
		}
		this.setTotal(page.getTotal());
		sendJsonDataToClient(page, response);
	}
	
	/**
	 * 跳转到GPS设备维护登记新增页面
	 */
	@RequestMapping("/toAddEquipRegister.do")
	public String toAddEquipRegister(HttpServletRequest request,HttpServletResponse response){
		Map<String, String> params = new HashMap<String, String>();
		params.put("equipStatus","dic.equipStatusType");//设备当前状态
		params.put("repairReason","dic.repairReasonType");//维修原因
		List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
		request.setAttribute("dictionarys", JSONArray.fromObject(dics));
		return "gpsEquipRegister/gpsEquipRegister_add";
	}
	
	
	/**
	 *  所有车牌号码下拉列表查询  
	 */
	@RequestMapping("/queryAllCarNumber.do")
	public void queryAllCarNumber(HttpServletRequest request,HttpServletResponse response){
		/**前台用户输入的字符*/
		String inputValue=request.getParameter("q");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inputVal",inputValue);
		String deleteF=request.getParameter("deleteF");
		if(StringUtil.isNotNullStr(deleteF)){
			params.put("deleteFlag",deleteF);
		}else{
			params.put("deleteFlag",null);
		}
		List<Map<String,Object>> list=vehicleMaintainService.queryAllCarNum(params);
		sendJsonDataToClient(JSONArray.fromObject(list).toString(), response);
	}
	
	/**
	 * 新增GPS设备维护登记记录
	 */
	@RequestMapping("/insert.do")
	public void insert(HttpServletRequest request,HttpServletResponse response,GpsEquipRegister gpsEquipRegister){
		Message msg = null;
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		gpsEquipRegister.setInputUser(user.getUserId());
		gpsEquipRegister.setDeleteFlag(0);
		gpsEquipRegister.setInputTime(DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        msg = (Message)gpsEquipRegisterService.insertGpsER(gpsEquipRegister);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "新增GPS设备维护登记记录：OA编号为："+gpsEquipRegister.getOaId());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 跳转到GPS设备维护登记修改页面
	 */
	@RequestMapping("toEditEquipRegister.do")
	public String toEditEquipRegister(HttpServletRequest request,HttpServletResponse response){
		Map<String, String> params = new HashMap<String, String>();
		params.put("equipStatus","dic.equipStatusType");//设备当前状态
		params.put("repairReason","dic.repairReasonType");//维修原因
		List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
		request.setAttribute("dictionarys", JSONArray.fromObject(dics));
		return "gpsEquipRegister/gpsEquipRegister_edit";
	}
	
	/**
	 * 获取一条GPS设备登记记录
	 */
	@ResponseBody
	@RequestMapping("/selectByOaId.do")
	public void selectByOaId(HttpServletResponse response,HttpServletRequest request) {
		GpsEquipRegister gpsEquipRegister = null;
		String idStr = request.getParameter("id");
		if (StringUtil.isNotNullStr(idStr)) {
			gpsEquipRegister = gpsEquipRegisterService.selectByOaId(idStr);
		}
		sendJsonDataToClient(gpsEquipRegister, response);
	}
	
	/**
	 * 修改GPS设备维护登记信息
	 */
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request,HttpServletResponse response,GpsEquipRegister gpsEquipRegister){
		Message msg = null;
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		gpsEquipRegister.setInputUser(user.getUserId());
		gpsEquipRegister.setInputTime(DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        msg = (Message)gpsEquipRegisterService.updateGpsER(gpsEquipRegister);
        if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改GPS设备维护登记记录：OA编号为："+gpsEquipRegister.getOaId());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 删除GPS设备维护登记信息
	 */
	@RequestMapping("/delGpsEquipRegister.do")
	public void delGpsEquipRegister(HttpServletRequest request,HttpServletResponse response){
		String idStr = request.getParameter("ids");
		Message msg = (Message)gpsEquipRegisterService.deleteByOaId(idStr);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除GPS设备维护登记信息：ID为：" + idStr);
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * GPS设备维护登记信息导出
	 * 
	 */

	@RequestMapping(value = "/exportGpsEquipRegister.do")
	public void exportGpsEquipRegister(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
		String param = request.getParameter("param");
		JSONObject obj = null;
		if (StringUtil.isNullStr(param)) {
			obj = new JSONObject();
		} else {
			obj = JSONObject.fromObject(param);
		}
		GpsEquipRegister gpsEquipRegister = (GpsEquipRegister) JSONObject.toBean(obj,GpsEquipRegister.class);
		String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
		String displayName = "GPS设备维护登记信息" + date + ".xls";
		response.setContentType("application/vnd.ms-excel");
		BufferedOutputStream out = null;
		// 进行转码，使其支持中文文件名
		response.addHeader("Content-Disposition", "attachment;filename="+ new String(displayName.getBytes("UTF-8"), "iso-8859-1"));
        try {
        out = new BufferedOutputStream(response.getOutputStream());
		String gridHead = PropertiesManager.getProperty("export.gpsRegister.columnNames");
		String fieldNames = PropertiesManager.getProperty("export.gpsRegister.fieldNames");

		HSSFWorkbook wb = new HSSFWorkbook();
		// 生成sheet
		HSSFSheet mainSheet = this.generateSheet(wb, displayName, gridHead);
		HSSFCellStyle normalDataStyle = wb.createCellStyle();
		normalDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		long total = this.getTotal();// 用户查询出来的总记录数
		// 计算需要分批查询的次数
		int eachQuerySize = Integer.parseInt(PropertiesManager.getProperty("export.excel.book.maxSheet"));
		int no = (int) total / eachQuerySize + 1;
		gpsEquipRegister.setPageSize((int) total);
		int rowNum = 0;
		int num = 0;
		int currentRecordNum = 0;
		int sheetNameIndex = 2;
		int maxRow = Integer.parseInt(PropertiesManager.getProperty("export.excel.sheet.maxRow"));
		for (int n = 1; n <= no; n++) {
			gpsEquipRegister.setPageNo(n);
			Page page = gpsEquipRegisterService.pageQuery(gpsEquipRegister);
			List<GpsEquipRegister> list = (List<GpsEquipRegister>) page.getRows();
			for (GpsEquipRegister ger : list) {
			    Integer isComplate=ger.getIsComplate();
				if(isComplate !=null && isComplate==1){
					ger.setTemp("是");
				}else{
					ger.setTemp("否");
				}
				if (currentRecordNum > maxRow) {
					mainSheet = this.generateSheet(wb, displayName+ sheetNameIndex, gridHead);
					sheetNameIndex++;
					currentRecordNum = 0;
					rowNum = 0;
				}
				currentRecordNum++;
				num = 0;
				HSSFRow row = mainSheet.getRow(1 + rowNum);
				if (row == null) {
					row = mainSheet.createRow(1 + rowNum);
				}
				// Insert data
				for (String field : fieldNames.split(",")) {
					HSSFCell cell = row.getCell(num);
					if (cell == null) {
						cell = row.createCell(num);
					}
					cell.setCellStyle(normalDataStyle);
					Field f = GpsEquipRegister.class.getDeclaredField(field);
					f.setAccessible(true);
					Object value = f.get(ger);

					this.setCellValue(cell, value);
					num++;
				}
				rowNum++;
			}
		}
		
			wb.write(out);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		  if(out != null){
			try {
				out.close();
				JCDFWebUtil.Log(request, "gps设备维护信息导出,参数为:" + param);
			} catch (IOException e) {
				log.error(e);
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
	private HSSFSheet generateSheet(HSSFWorkbook wb, String sheetName,
			String gridHead) {
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
