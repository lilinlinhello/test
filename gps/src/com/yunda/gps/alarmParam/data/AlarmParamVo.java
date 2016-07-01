package com.yunda.gps.alarmParam.data;


public class AlarmParamVo {

	//报警参数id
    private Integer apId;
    //报警类型 12:低速 13：无信号 22：线路偏移
    private String apType;
    //时速
    private String speed;
    //报警时长
    private Short duration;
    //偏移距离
    private String distance;
    //gps点数
    private String collection;
    //生效时间
    private String startTime;
    //失效时间
    private String endTime;
    //创建者
    private String createBy;
    //创建时间
    private String createTime;
    //更新者
    private String updateBy;
    //更新时间
    private String updateTime;
    
    private Byte deleteFlag;
    
	public Byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getApId() {
		return apId;
	}
	public void setApId(Integer apId) {
		this.apId = apId;
	}

	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getApType() {
		return apType;
	}
	public void setApType(String apType) {
		this.apType = apType;
	}
	public Short getDuration() {
		return duration;
	}
	public void setDuration(Short duration) {
		this.duration = duration;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
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
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
    
}
