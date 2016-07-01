package com.yunda.gps.vehicleCategory.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.PropertiesManager;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.service.VehicleCategoryService;

/**
 * 车辆层次分类
 * @author sm
 *
 */
@Controller
@RequestMapping(value="/vehicleCategory")
public class VehicleCategoryController extends BaseController{

	@Resource(name="vehicleCategoryServiceImpl")
	private VehicleCategoryService vehicleCategoryService;
	
	@RequestMapping("/listPage.do")
	public String forwardListPage(HttpServletRequest request){
		
		return "vehicleCategory/vehicleCategory_list";
	}
	
	@RequestMapping("/addPage.do")
	public String forwardAddPage(){
		return "vehicleCategory/vehicleCategory_add";
	}
	
	@RequestMapping("/editPage.do")
	public String forwardEditPage(HttpServletRequest request,HttpServletResponse response){
		return "vehicleCategory/vehicleCategory_edit";
	}

	@RequestMapping("/getVehicleById.do")
	public void getVehicleById(HttpServletResponse response,String type,String childCode){
		VehicleCategoryVo vehicle = new VehicleCategoryVo();
		VehicleCategoryVo result = null;
		if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_00)){//编辑公司
			result = vehicleCategoryService.selectById(childCode);
			vehicle.setTopName(result.getName());
			vehicle.setTopCode(result.getChildCode());
		}
		if(StringUtil.isNotNullStr(type) &&type.equals(Constant.TYPE_01)){//编辑运营属性
			result = vehicleCategoryService.selectById(childCode);
			vehicle.setSecondName(result.getName());
			vehicle.setSecondCode(result.getChildCode());
			
			result = vehicleCategoryService.selectById(result.getParentCode());
			vehicle.setTopName(result.getName());
			vehicle.setTopCode(result.getChildCode());
			vehicle.setDeleteFlag(result.getDeleteFlag());
		}
		if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_02)){//编辑车辆信息
			result = vehicleCategoryService.selectById(childCode);
			vehicle.setThirdName(result.getName());
			vehicle.setThirdCode(result.getChildCode());
			
			result = vehicleCategoryService.selectById(result.getParentCode());
			vehicle.setSecondName(result.getName());
			vehicle.setSecondCode(result.getChildCode());
			
			result = vehicleCategoryService.selectById(result.getParentCode());
			vehicle.setTopName(result.getName());
			vehicle.setTopCode(result.getChildCode());
			vehicle.setDeleteFlag(result.getDeleteFlag());
		}
		sendJsonDataToClient(vehicle, response);
	}
	
	
	@RequestMapping("/pageQuery.do")
	public void pageQuery(HttpServletRequest request,HttpServletResponse response,VehicleCategoryVo vo){
		vo.setModelType("company");
		Page page = vehicleCategoryService.pageQuery(vo);
		this.total = page.getTotal();
		sendJsonDataToClient(page, response);
	}
	
	@RequestMapping("/updateVehicle.do")
	public void updateVehicle(HttpServletRequest request,HttpServletResponse response,VehicleCategoryVo vo){
		LoginUser loginUser  = (LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		vo.setUpdateBy(loginUser.getUserId());
		vo.setUpdateTime(DateUtil.getDateYMDHMs(new Date()));
		String type = vo.getType();
		if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_00)){//编辑公司
			vo.setParentCode("0");
			vo.setName(vo.getTopName());
		}
		if(StringUtil.isNotNullStr(type) &&type.equals(Constant.TYPE_01)){//编辑运营属性
			vo.setParentCode(vo.getTopCode());
			vo.setName(vo.getSecondName());
		}
		if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_02)){//编辑车辆信息
			vo.setParentCode(vo.getSecondCode());
			vo.setName(vo.getThirdName());
		}
		
		Message msg = vehicleCategoryService.update(vo);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "修改车辆层次分类信息：childCode="+vo.getChildCode()+",分类名称="+vo.getName());
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 按id批量逻辑删除
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("/deleteByIds.do")
	public void deleteByIds(HttpServletRequest request,HttpServletResponse response,String ids){
		Message msg = vehicleCategoryService.deleteByID(ids);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除车次信息：ID为："+ids);
		}
		sendJsonDataToClient(msg, response);
	}
	/**
	 * 逻辑恢复
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("/backByIds.do")
	public void backByIds(HttpServletRequest request,HttpServletResponse response,String ids){
		Message msg = vehicleCategoryService.backByID(ids);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "恢复车次信息：ID为："+ids);
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 获取对应的子节点
	 * @param request
	 * @param response
	 * @param vo 传入parentCode
	 */
	@RequestMapping("/getVehicleInfo.do")
	public void getVehicleInfo(HttpServletRequest request,HttpServletResponse response,VehicleCategoryVo vo){
		vo.setModelType("company");
		List<Map<String,Object>> list = vehicleCategoryService.getVeicleInfo(vo);
		sendJsonDataToClient(JSONArray.fromObject(list), response);
	}
	
	/**
	 * 批量增加车辆车次
	 * @param request
	 * @param response
	 */
	@RequestMapping("/insertVehicleCategorys.do")
	public synchronized void insertVehicleCategorys(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("opType");
		LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
		String jsonDataArr = request.getParameter("jsonDataStr");
		Message msg = null;
		StringBuffer retMsg = new StringBuffer("");
		
		if (StringUtil.isNotNullStr(jsonDataArr)) {
			JSONArray jsonArr = JSONArray.fromObject(jsonDataArr);
			@SuppressWarnings("unchecked")
			List<VehicleCategoryVo> list = (List<VehicleCategoryVo>) JSONArray.toCollection(jsonArr, VehicleCategoryVo.class);
			for (VehicleCategoryVo vehicle : list) {
				//组装参数 按照VehicleCategory实体状态添加
				vehicle.setCreateBy(user.getUserId());
				vehicle.setModelType("company");
				vehicle.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
				vehicle.setDeleteFlag(Constant.NO_DELETE_FLAG);
				
				if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_00)){//添加公司
					vehicle.setParentCode("0");
					vehicle.setName(vehicle.getTopName());
					vehicle.setType(Constant.TYPE_00);
				}
				if(StringUtil.isNotNullStr(type) &&type.equals(Constant.TYPE_01)){//添加运营属性
					vehicle.setParentCode(vehicle.getTopCode());
					vehicle.setName(vehicle.getSecondName());
					vehicle.setType(Constant.TYPE_01);
				}
				if(StringUtil.isNotNullStr(type) && type.equals(Constant.TYPE_02)){//添加车辆信息
					vehicle.setParentCode(vehicle.getSecondCode());
					vehicle.setName(vehicle.getThirdName());
					vehicle.setType(Constant.TYPE_02);
				}
				vehicle.setChildCode("C"+getCodeStr(vehicle)); //生成公司编码，如：C01001001
				msg = vehicleCategoryService.insert(vehicle);
				if (null != msg && msg.isResult()) {
					JCDFWebUtil.Log(request, "新增车辆层次分类信息：childCode="+vehicle.getChildCode()+",分类名称="+vehicle.getName());
				}
				//数据已经存在返回数据提示
				if(!msg.isResult()){
					retMsg.append(msg.getMsg());
				}
			}
			if(retMsg.length() > 0){
				msg.setMsg(retMsg.toString());
			}else{
				msg = new Message(true,"数据添加成功！");
			}
		} else {
			msg = new Message(false, "参数不完整！");
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 * 导出报表
	 * @param response
	 * @param request
	 * @param log
	 */
	@RequestMapping("/export.do")
	public void export(HttpServletResponse response, HttpServletRequest request){
		
		String fileName = PropertiesManager.getProperty("export.vehicleCategory.fileName").replaceAll(".xls", DateUtil.format(new Date(), DateUtil.YYYYMMDD)+".xls");
		String gridName = PropertiesManager.getProperty("export.vehicleCategory.columnNames");
		String fieldName = PropertiesManager.getProperty("export.vehicleCategory.fieldNames");
		String maxsheet = PropertiesManager.getProperty("export.excel.book.maxSheet");
		String maxrow = PropertiesManager.getProperty("export.excel.sheet.maxRow");

		String param = request.getParameter("param");
		JSONObject jsonObject = null;
		if (StringUtil.isNullStr(param)) {
			jsonObject = new JSONObject();
		} else {
			jsonObject = JSONObject.fromObject(param);
		}	
		VehicleCategoryVo vo = (VehicleCategoryVo) JSONObject.toBean(jsonObject, VehicleCategoryVo.class);
		
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
				vo.setModelType("company");
				Page page = vehicleCategoryService.pageQuery(vo);
				@SuppressWarnings("unchecked")
				List<VehicleCategoryVo> list = (List<VehicleCategoryVo>) page.getRows();
				
				int rownum = 1;//sheet 单元行记录索引
				for(VehicleCategoryVo value : list){
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
						
						Field field = VehicleCategoryVo.class.getDeclaredField(fieldele);
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
			JCDFWebUtil.Log(request, "导出车辆层次信息报表");
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
	public  String getCodeStr(VehicleCategoryVo vehicle){
		return vehicleCategoryService.createCode(vehicle);
	}
	
}
