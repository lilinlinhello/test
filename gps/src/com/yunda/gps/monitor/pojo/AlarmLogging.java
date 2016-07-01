package com.yunda.gps.monitor.pojo;

import com.yunda.app.entity.vo.PageQueryParams;

public class AlarmLogging extends PageQueryParams{
    private String id;
    //货运单号
    private String shipmentId;
	//车辆编码
    private String licenseCardId;
    //报警类型
    private String alarmType;
    //报警开始时间
    private String alarmStartTime;
    //报警结束时间
    private String alarmEndTime;
    //报警时间
    private String alarmTime;
    
    //数据发送状态
    private String txdFlag;

    //传参
    private String alarmStartTimeMin;
    private String alarmStartTimeMax;
    private String alarmEndTimeMin;
    private String alarmEndTimeMax;
    
    public String getAlarmStartTimeMin() {
		return alarmStartTimeMin;
	}

	public void setAlarmStartTimeMin(String alarmStartTimeMin) {
		this.alarmStartTimeMin = alarmStartTimeMin;
	}

	public String getAlarmStartTimeMax() {
		return alarmStartTimeMax;
	}

	public void setAlarmStartTimeMax(String alarmStartTimeMax) {
		this.alarmStartTimeMax = alarmStartTimeMax;
	}

	public String getAlarmEndTimeMin() {
		return alarmEndTimeMin;
	}

	public void setAlarmEndTimeMin(String alarmEndTimeMin) {
		this.alarmEndTimeMin = alarmEndTimeMin;
	}

	public String getAlarmEndTimeMax() {
		return alarmEndTimeMax;
	}

	public void setAlarmEndTimeMax(String alarmEndTimeMax) {
		this.alarmEndTimeMax = alarmEndTimeMax;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId == null ? null : shipmentId.trim();
    }

    public String getLicenseCardId() {
        return licenseCardId;
    }

    public void setLicenseCardId(String licenseCardId) {
        this.licenseCardId = licenseCardId == null ? null : licenseCardId.trim();
    }    
   
	public String getAlarmStartTime() {
		return alarmStartTime;
	}

	public void setAlarmStartTime(String alarmStartTime) {
		this.alarmStartTime = alarmStartTime;
	}

	public String getAlarmEndTime() {
		return alarmEndTime;
	}

	public void setAlarmEndTime(String alarmEndTime) {
		this.alarmEndTime = alarmEndTime;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	
	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getTxdFlag() {
		return txdFlag;
	}

	public void setTxdFlag(String txdFlag) {
		this.txdFlag = txdFlag;
	}

   
}