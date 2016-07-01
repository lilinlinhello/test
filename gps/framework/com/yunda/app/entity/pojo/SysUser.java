package com.yunda.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.yunda.app.entity.vo.PageQueryParams;

/**
 * 用户信息实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class SysUser extends PageQueryParams implements Serializable {

	private static final long serialVersionUID = 3647233284813657927L;
	/**用户账号*/
    private String userId;
    /**用户名称*/
    private String userName;
    /**用户密码*/
    private String userPass;
    /**创建时间*/
    private String createTime;
	/**用户类型：1：超级管理员*/
    private String userType;
    /**dic_userFrom01:SOA同步   dic_userFrom02：外部注册用户**/
    private String userFrom; 
    /****用户状态 0停用，1启用*****/
    private Integer state;
    
    private String createBy;
    
    /**公司编码*/
    private String companyNo;
    
    private String telPhone;
    
    private String updateBy;
    private String updateTime;
    
    public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}



	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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