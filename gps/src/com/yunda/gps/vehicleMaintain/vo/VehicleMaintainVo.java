package com.yunda.gps.vehicleMaintain.vo;

import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;

/**
 * 车辆安装维护实体类的扩展
 * @author
 *
 */
public class VehicleMaintainVo extends VehicleMaintain {
	
	private static final long serialVersionUID = 7045549904927168424L;
	//最小创建时间
	private String minCreateTime;
	//最大创建时间
	private String maxCreateTime;
	//车牌号/SIM卡/设备编码:条件查询时提交的数据
	private String item;
	
	
	public String getMinCreateTime() {
		return minCreateTime;
	}
	public void setMinCreateTime(String minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	public String getMaxCreateTime() {
		return maxCreateTime;
	}
	public void setMaxCreateTime(String maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	

	
	

	
	
	
	
	
	

}
