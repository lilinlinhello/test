package com.yunda.app.entity.vo;

/**
 * 登录用户信息
 * 
 * @author Jiangshui
 * @date 2013-10-21
 */
public class LoginUser {

	/**用户账号*/
    private String userId;
	/** 用户名称 */
	private String userName;
	/** 本次登录的令牌 */
	private String token;
	/**用户类型：1：超级管理员，2：运营人，3仓库管理员  4 商家  5 普通用户  6 系统管理员*/
    private String userType;
    /**dic_userFrom01:SOA同步   dic_userFrom02：外部注册用户**/
    private String userFrom; 
    /**用户状态 0停用 1 启用*/
    private Integer state;
    /**公司编码*/
    private String companyNo;
    /**手机号*/
    private String telPhone;
   
    
    

    
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


}