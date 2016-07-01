package com.yunda.gps.monitor.pojo;

import com.yunda.app.entity.vo.PageQueryParams;

/**
 * 
 * 货运单监控异常信息vo
 *
 */
public class ShipmentMonitorError extends PageQueryParams {
    private Integer id;

    //货运单号
    private String shipmentId;

    //错误类别 1:无监控开始时间 2:无监控结束时间 3:无此车辆
    private String errorType;

    //1:预处理阶段 其他填写货运单上的站点编码
    private String shipmentStage;
    
    private String createTime;

    //数据发送状态 0:待发送，1:已发送
    private String txdFlag;

    
    public String getShipmentStage() {
		return shipmentStage;
	}

	public void setShipmentStage(String shipmentStage) {
		this.shipmentStage = shipmentStage;
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

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
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