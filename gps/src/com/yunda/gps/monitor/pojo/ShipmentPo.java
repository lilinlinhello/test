package com.yunda.gps.monitor.pojo;

import com.yunda.app.entity.vo.PageQueryParams;

/**
 * 货运单信息
 *
 */
public class ShipmentPo extends PageQueryParams{
    //
    private Integer id;

    //货运单号
    private String shipmentId;

    //线路编码
    private String lineId;

    //线路名称
    private String lineName;

    //始发站
    private Integer startPlace;

    //目的站
    private Integer endPlace;

    //多个途径地‘,’分割
    private String passPlace;

    //计划离开时间
    private String planLeaveTime;

    //计划到达时间
    private String planArriveTime;

    //车牌号
    private String licenseCard;

    //预处理标识
    private String preFlag;

    //规划线路
    private String planLineId;
    
    //线路类型
    private String planLineType;
    
    //创建时间
    private String createTime;

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

    public Integer getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Integer startPlace) {
        this.startPlace = startPlace;
    }

    public Integer getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(Integer endPlace) {
        this.endPlace = endPlace;
    }

    public String getPassPlace() {
        return passPlace;
    }

    public void setPassPlace(String passPlace) {
        this.passPlace = passPlace == null ? null : passPlace.trim();
    }

    public String getLicenseCard() {
        return licenseCard;
    }

    public void setLicenseCard(String licenseCard) {
        this.licenseCard = licenseCard == null ? null : licenseCard.trim();
    }

    public String getPlanLineId() {
        return planLineId;
    }

    public void setPlanLineId(String planLineId) {
        this.planLineId = planLineId == null ? null : planLineId.trim();
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

	public String getPreFlag() {
		return preFlag;
	}

	public void setPreFlag(String preFlag) {
		this.preFlag = preFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPlanLineType() {
		return planLineType;
	}

	public void setPlanLineType(String planLineType) {
		this.planLineType = planLineType;
	}

    
}