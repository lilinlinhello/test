package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.ShipmentMonitorErrorDao;
import com.yunda.gps.monitor.pojo.ShipmentMonitorError;
import com.yunda.gps.util.StringUtil;

@Service(value="shipmentMonitorErrorServiceImpl")
public class ShipmentMonitorErrorServiceImpl implements
		ShipmentMonitorErrorService {

	@Resource(name="shipmentMonitorErrorDaoImpl")
	private ShipmentMonitorErrorDao shipmentMonitorErrorDao;
	
	@Override
	public Page pageQuery(ShipmentMonitorError ship) {
		Page page = null;
		page = shipmentMonitorErrorDao.pageQuery(ship);		
		//
		transformFlag(page);
		
		return page;
	}

	private void transformFlag(Page page) {
		@SuppressWarnings("unchecked")
		List<ShipmentMonitorError> list = (List<ShipmentMonitorError>) page.getRows();
		for(ShipmentMonitorError ship : list ){
			if(StringUtil.isNotNullStr(ship.getErrorType())){
				String errorType = ship.getErrorType();
				if(errorType.equals("1")){
					ship.setErrorType("无监控开始时间");
				}else if(errorType.equals("2")){
					ship.setErrorType("无监控结束时间");
				}else if(errorType.equals("3")){
					ship.setErrorType("无此车辆");
				}
			}
			
			if(StringUtil.isNotNullStr(ship.getTxdFlag())){
				String txd = ship.getTxdFlag();
				if(txd.equals("0")){
					ship.setTxdFlag("待发送");
				}else if(txd.equals("1")){
					ship.setTxdFlag("已发送");
				}
			}
		}
	}

}
