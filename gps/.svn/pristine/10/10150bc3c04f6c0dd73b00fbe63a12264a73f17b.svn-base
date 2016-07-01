package com.yunda.gps.vehicleStat.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.gps.util.ValidateOnLineUtil;
import com.yunda.gps.vehicleStat.dao.VehicleStatDao;
import com.yd.lbs.gps.tools.util.StatCode;

@Service(value = "vehicleStatService")
@Transactional
public class VehicleStatServiceImpl implements VehicleStatService {

	/**功能菜单数据库访问接口*/
	@Resource(name="vehicleStatDao")
	private VehicleStatDao vehicleStatDao;
	
	@Override
	public String getStatus(String vehicleId) {
		//获取车牌对应的状态
		String status = vehicleStatDao.queryStatusByVehicleId(vehicleId);	
		try {
			Integer.parseInt(status);
		} catch (Exception e) {
			return status;
		}
		//获取车牌对应的GPS时间
		Date date=vehicleStatDao.queryOnLineByVehicleId(vehicleId);
		//获取正常状态对应的数字
		String normal = String.valueOf(StatCode.NORMAL.getCode());
		//如果车牌对应的状态为正常状态对应的数字，验证是否离线，若离线则状态变成离线所对应的数字
		if (status.equals(normal)) {
			status=ValidateOnLineUtil.getStatus(date);
		}
		//把状态数字换成对应的中文，返回状态的中文
		for (StatCode statCode : StatCode.values()) {
             if (String.valueOf(statCode.getCode()).equals(status)) {
            	 status=statCode.getStatString();
            	 break;
			}
        } 
		return status;
	}
	
}
