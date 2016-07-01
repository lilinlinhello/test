package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.ShipmentMonitorDao;
import com.yunda.gps.monitor.pojo.ShipmentMonitorPo;
import com.yunda.gps.monitor.pojo.ShipmentMonitorVo;
import com.yunda.gps.monitor.service.ShipmentMonitorService;
import com.yunda.gps.util.StringUtil;

@Service(value="shipmentMonitorServiceImpl")
public class ShipmentMonitorServiceImpl implements ShipmentMonitorService {

	@Resource(name="shipmentMonitorDaoImpl")
	private ShipmentMonitorDao shipmentMonitorDao;
	
	@Override
	public Page pageQuery(ShipmentMonitorVo shipment) {
		Page page = null;
		page = shipmentMonitorDao.pageQuery(shipment);		
		//
		transformFlag(page);
		
		return page;
	}
	
	/**
	 * 将数据中特殊标记转化成名称
	 * @param page
	 */
	private void transformFlag(Page page) {
		//转换标记可视化
		@SuppressWarnings("unchecked")
		List<ShipmentMonitorPo> list = (List<ShipmentMonitorPo>) page.getRows();
		for(ShipmentMonitorPo shipmentMonitor : list){
			if(StringUtil.isNotNullStr(shipmentMonitor.getMonitorFlag())){
				String monitorFlag = shipmentMonitor.getMonitorFlag();
				if(monitorFlag.equals("0")){
					shipmentMonitor.setMonitorFlag("待监控");
				}else if(monitorFlag.equals("1")){
					shipmentMonitor.setMonitorFlag("监控中");
				}else if(monitorFlag.equals("2")){
					shipmentMonitor.setMonitorFlag("监控结束");
				}else{
					shipmentMonitor.setMonitorFlag("");
				}				
			}
			
			if(StringUtil.isNotNullStr(shipmentMonitor.getShipmentFlag())){
				String shipmentFlag = shipmentMonitor.getShipmentFlag();
				if(shipmentFlag.equals("0")){
					shipmentMonitor.setShipmentFlag("正常");
				}else if(shipmentFlag.equals("1")){
					shipmentMonitor.setShipmentFlag("车牌修改");
				}else if(shipmentFlag.equals("2")){
					shipmentMonitor.setShipmentFlag("线路修改，手工结束");
				}else{
					shipmentMonitor.setShipmentFlag("");
				}	
			}
			
			if(StringUtil.isNotNullStr(shipmentMonitor.getMileageFlag())){
				String mileageFlag = shipmentMonitor.getMileageFlag();
				if(mileageFlag.equals("0")){
					shipmentMonitor.setMileageFlag("未计算");
				}else if(mileageFlag.equals("1")){
					shipmentMonitor.setMileageFlag("已计算");
				}else{
					shipmentMonitor.setMileageFlag("");
				}	
			}
			
			if(StringUtil.isNotNullStr(shipmentMonitor.getOrderId())){
				String orderId = shipmentMonitor.getOrderId();
				if(orderId.equals("0")){
					shipmentMonitor.setOrderId("始发");
				}else if(orderId.equals("100")){
					shipmentMonitor.setOrderId("目的");
				}else{
					shipmentMonitor.setOrderId("途径");
				}	
			}
		}
	}

}
