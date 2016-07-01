package com.yunda.gps.util;

import java.util.Arrays;
import java.util.List;

import com.yunda.gps.constant.Constant;
/**
 * 车牌号编码工具类
 */
public class VehicleEncode {
	/**
	 * 
	 * @param licenseCard 车牌号
	 * @return 车牌编码
	 * @throws IllegalArgumentException
	 */
	public static String encode(String  vehicleCode) throws IllegalArgumentException{
		String province_name = vehicleCode.substring(0, 1);
		List<String> prov_Name_list = Arrays.asList(Constant.prov_Name);
        List<String> prov_Code_list = Arrays.asList(Constant.prov_Code);
        
        if(prov_Name_list.contains(province_name) && vehicleCode.length()==7){
        	String province_code = prov_Code_list.get(prov_Name_list.indexOf(province_name));
        	String vehicleId = vehicleCode.replace(province_name, province_code); 
        	return vehicleId;
        }else{
        	throw new IllegalArgumentException(vehicleCode+"：车牌号格式有误");
        }
	}
}
