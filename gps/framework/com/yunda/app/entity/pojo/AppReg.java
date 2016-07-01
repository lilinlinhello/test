package com.yunda.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.yunda.app.entity.vo.PageQueryParams;

public class AppReg extends PageQueryParams implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Integer regId;

    private String appName;

    private String contMan;

    private String department;

    private String phoneNo;

    private Integer bqq;

    private String ak;

    private String ipWhiteList;

    private Integer appType;

    private Integer delFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getContMan() {
        return contMan;
    }

    public void setContMan(String contMan) {
        this.contMan = contMan == null ? null : contMan.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public Integer getBqq() {
        return bqq;
    }

    public void setBqq(Integer bqq) {
        this.bqq = bqq;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak == null ? null : ak.trim();
    }

    public String getIpWhiteList() {
        return ipWhiteList;
    }

    public void setIpWhiteList(String ipWhiteList) {
        this.ipWhiteList = ipWhiteList == null ? null : ipWhiteList.trim();
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}