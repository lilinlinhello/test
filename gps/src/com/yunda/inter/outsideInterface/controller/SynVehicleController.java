package com.yunda.inter.outsideInterface.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunda.app.base.BaseController;
import com.yunda.gps.constant.Constant;
import com.yunda.inter.outsideInterface.bean.SynVehicle;
import com.yunda.inter.outsideInterface.service.SynVehicleService;
import com.yunda.gps.util.KeyUtil;
import com.yunda.gps.util.MD5;
import com.yunda.gps.util.StringUtil;

/**
 * 石墨车辆同步接口
 * @author gdd
 * @Time 2016-04-8
 */
@Controller
@RequestMapping(value = "/synVehicle")
public class SynVehicleController extends BaseController {

	Log log = LogFactory.getLog(SynVehicleController.class);

	@Resource(name = "synVehicleService")
	private SynVehicleService synVehicleService;

	
	/**
	 * 跳转到GPS设备维护登记列表页面
	 */
	@RequestMapping("/vehicleInsert.do")
	public void insertSynVehicle(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
		    String sign = request.getParameter("SIGN");
		    String type = request.getParameter("TYPE");
	    	String licensecard = request.getParameter("EquipmentNumber"); //车牌号码
	    	String vehicleType = request.getParameter("EquipmentType"); //车辆类型
	    	String vehicleProperties = request.getParameter("CarProperty"); //车辆性质	 
	    	String carrierCode = request.getParameter("SapCode"); //承运商编码
	    	String transportCompany = request.getParameter("CarComCode"); //运输公司

		    if(StringUtil.isNullStr(sign) ||!MD5.md5(Constant.SIGN.getBytes()).equals(sign) ){
		    	 map.put("flag", false);
				 map.put("msg", "数字签名认证失败!");
		    }else{
		    	SynVehicle synVehicle = new SynVehicle();
		    	if(StringUtil.isNullStr(licensecard)){
		    		 map.put("flag", false);
					 map.put("msg", "车牌号码为空！");
		    	}else{
		    		 String province_name = licensecard.substring(0, 1);
		    		 List<String> prov_Name_list = Arrays.asList(Constant.prov_Name);
		             List<String> prov_Code_list = Arrays.asList(Constant.prov_Code);
		             if(prov_Name_list.contains(province_name) && licensecard.length()==7){ //车牌长度校验
		            	String province_code = prov_Code_list.get(prov_Name_list.indexOf(province_name));
		            	String vehicleId = licensecard.replace(province_name, province_code); //得到车牌编码
		            	synVehicle.setVehicleId(vehicleId);
		            	synVehicle.setId(KeyUtil.getIdByTime(4)); //生成主键编码id
		 				synVehicle.setDeleteFlag(Constant.NO_DELETE_FLAG.byteValue());
		 				if(StringUtil.isNotNullStr(type)&&Constant.INTERFACE_DEL.equals(type)){ //删除状态
		 					synVehicle.setDeleteFlag(Byte.valueOf(Constant.DELETE_FLAG));
		 				}
		 				synVehicle.setLicensecard(licensecard);  
		 				synVehicle.setCarrierCode(carrierCode);
		 				synVehicle.setVehicleType(vehicleType);
		 				synVehicle.setTransportCompany(transportCompany);
		 				synVehicle.setVehicleProperties(vehicleProperties);
		 				map = synVehicleService.insert(synVehicle);
		 				synVehicle = null;
		             }else{
		            	 map.put("flag", false);
						 map.put("msg", "车牌号码有误!");
		             }
		    	}
		    }			
		} catch (Exception e) {
			 map.put("flag", false);
			 map.put("msg", e.getMessage());
		}
		sendJsonDataToClient(map, response);
	}

	
}
