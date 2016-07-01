package com.yunda.gps.monitor.pojo;

import com.yunda.app.entity.vo.PageQueryParams;


/**
 * 
 * 运行里程实体
 *
 */
public class RunMileage extends PageQueryParams{
	//货运单号
    private String shipmentId;
    //GPS里程
    private Double gpsMileage;
    //设备里程
    private Double equipMileage;
    //里程更新时间
    private String updateTime;
    //创建时间
    private String createTime;
    //数据发送状态
    private String txdFlag;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId == null ? null : shipmentId.trim();
    }

    public Double getGpsMileage() {
        return gpsMileage;
    }

    public void setGpsMileage(Double gpsMileage) {
        this.gpsMileage = gpsMileage;
    }

    public Double getEquipMileage() {
        return equipMileage;
    }

    public void setEquipMileage(Double equipMileage) {
        this.equipMileage = equipMileage;
    }


	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTxdFlag() {
		return txdFlag;
	}

	public void setTxdFlag(String txdFlag) {
		this.txdFlag = txdFlag;
	}

}