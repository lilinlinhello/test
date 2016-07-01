package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.ShipmentDao;
import com.yunda.gps.monitor.pojo.ShipmentPo;

@Service(value="shipmentServiceImpl")
public class ShipmentServiceImpl implements ShipmentService {

	@Resource(name="shipmentDaoImpl")
	private ShipmentDao shipmentDao;
	
	@Override
	public Page pageQuery(ShipmentPo shipment) {
		Page page = null;
		page = shipmentDao.pageQuery(shipment);		
		//
		transformFlag(page);
		
		return page;
	}

	private void transformFlag(Page page) {
		@SuppressWarnings("unchecked")
		List<ShipmentPo> list = (List<ShipmentPo>) page.getRows();
		for(ShipmentPo shipment : list){
			if(shipment.getPreFlag() != null){
				String preFlag = shipment.getPreFlag();
				if(preFlag.equals("0")){
					shipment.setPreFlag("未处理");
				}else if(preFlag.equals("1")){
					shipment.setPreFlag("已处理");
				}
			}
			
			if(shipment.getPlanLineType() != null){
				String planLineType = shipment.getPlanLineType();
				if(planLineType.equals("1")){
					shipment.setPlanLineType("标准轨迹");
				}else if(planLineType.equals("2")){
					shipment.setPlanLineType("参考轨迹");
				}
			}
		}
		
	}

}
