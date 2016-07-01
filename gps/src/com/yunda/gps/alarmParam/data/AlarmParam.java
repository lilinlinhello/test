package com.yunda.gps.alarmParam.data;

import java.util.Date;

import com.yunda.app.entity.vo.PageQueryParams;

public class AlarmParam extends PageQueryParams{
	//报警参数id
    private Integer apId;
    //报警类型 12:低速 13：无信号 22：线路偏移
    private Byte apType;

    //报警时长
    private Short duration;
    //偏移距离
    private Short distance;
    //时速
    private Short speed;
    //gps点数
    private Short collection;
    //生效时间
    private String startTime;
    //失效时间
    private String endTime;
    //创建者
    private String createBy;
    //创建时间
    private Date createTime;
    //更新者
    private String updateBy;
    //更新时间
    private Date updateTime;

    private Byte deleteFlag;

    public Integer getApId() {
        return apId;
    }

    public void setApId(Integer apId) {
        this.apId = apId;
    }

    public Short getSpeed() {
		return speed;
	}

	public void setSpeed(Short speed) {
		this.speed = speed;
	}

	public Byte getApType() {
        return apType;
    }

    public void setApType(Byte apType) {
        this.apType = apType;
    }

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    public Short getDistance() {
        return distance;
    }

    public void setDistance(Short distance) {
        this.distance = distance;
    }

    public Short getCollection() {
        return collection;
    }

    public void setCollection(Short collection) {
        this.collection = collection;
    }

    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}