package com.yunda.gps.monitor.pojo;

import com.yunda.app.entity.vo.PageQueryParams;

public class ShipmentMonitorVo extends PageQueryParams{
    private Integer id;

    //货运单号
    private String shipmentId;
    //线路编码
    private String lineId;
    //线路名称
    private String lineName;
    //始发点编码
    private Integer placeId;
    //站点序号0：始发，100：目的，0-100之间为途径
    private String orderId;
    //计划离开时间
    private String planLeaveTime;
    //计划到达时间
    private String planArriveTime;
    //实际离开时间
    private String realLeaveTime;
    //实际到达时间
    private String realArriveTime;
    //GPS离开时间
    private String gpsLeaveTime;
    //GPS到达时间
    private String gpsArriveTime;
    //车辆编码
    private String licenseCardId;
    //0：待监控，1：监控中，2：监控结束
    private String monitorFlag;
    //0：正常，1：车牌修改，2：线路修改，手工结束监控
    private String shipmentFlag;
    //0：未计算，1：已计算
    private String mileageFlag;
    //规划线路
    private String planLineId;
    //创建时间
    private String createTime;

    //传参
    private String realLeaveTimeMin;
    private String realLeaveTimeMax;
    private String realArriveTimeMin;
    private String realArriveTimeMax;
    
    
    public String getRealLeaveTimeMin() {
		return realLeaveTimeMin;
	}

	public void setRealLeaveTimeMin(String realLeaveTimeMin) {
		this.realLeaveTimeMin = realLeaveTimeMin;
	}

	public String getRealLeaveTimeMax() {
		return realLeaveTimeMax;
	}

	public void setRealLeaveTimeMax(String realLeaveTimeMax) {
		this.realLeaveTimeMax = realLeaveTimeMax;
	}

	public String getRealArriveTimeMin() {
		return realArriveTimeMin;
	}

	public void setRealArriveTimeMin(String realArriveTimeMin) {
		this.realArriveTimeMin = realArriveTimeMin;
	}

	public String getRealArriveTimeMax() {
		return realArriveTimeMax;
	}

	public void setRealArriveTimeMax(String realArriveTimeMax) {
		this.realArriveTimeMax = realArriveTimeMax;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId == null ? null : shipmentId.trim();
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlanLeaveTime() {
		return planLeaveTime;
	}

	public void setPlanLeaveTime(String planLeaveTime) {
		this.planLeaveTime = planLeaveTime;
	}

	public String getPlanArriveTime() {
		return planArriveTime;
	}

	public void setPlanArriveTime(String planArriveTime) {
		this.planArriveTime = planArriveTime;
	}

	public String getRealLeaveTime() {
		return realLeaveTime;
	}

	public void setRealLeaveTime(String realLeaveTime) {
		this.realLeaveTime = realLeaveTime;
	}

	public String getRealArriveTime() {
		return realArriveTime;
	}

	public void setRealArriveTime(String realArriveTime) {
		this.realArriveTime = realArriveTime;
	}

	public String getGpsLeaveTime() {
		return gpsLeaveTime;
	}

	public void setGpsLeaveTime(String gpsLeaveTime) {
		this.gpsLeaveTime = gpsLeaveTime;
	}

	public String getGpsArriveTime() {
		return gpsArriveTime;
	}

	public void setGpsArriveTime(String gpsArriveTime) {
		this.gpsArriveTime = gpsArriveTime;
	}


	public String getLicenseCardId() {
        return licenseCardId;
    }

    public void setLicenseCardId(String licenseCardId) {
        this.licenseCardId = licenseCardId == null ? null : licenseCardId.trim();
    }


    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMonitorFlag() {
		return monitorFlag;
	}

	public void setMonitorFlag(String monitorFlag) {
		this.monitorFlag = monitorFlag;
	}

	public String getShipmentFlag() {
		return shipmentFlag;
	}

	public void setShipmentFlag(String shipmentFlag) {
		this.shipmentFlag = shipmentFlag;
	}

	public String getMileageFlag() {
		return mileageFlag;
	}

	public void setMileageFlag(String mileageFlag) {
		this.mileageFlag = mileageFlag;
	}

	public String getPlanLineId() {
        return planLineId;
    }

    public void setPlanLineId(String planLineId) {
        this.planLineId = planLineId == null ? null : planLineId.trim();
    }

}