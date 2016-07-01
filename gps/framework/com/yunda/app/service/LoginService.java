package com.yunda.app.service;

/**
 * 用户登录业务接口
 * 
 * @author Jiangshui
 * @date	2013-10-21
 */
public interface LoginService {

	/**
	 * 用户登录
	 * 
	 * @param userId	用户账号
	 * @param userPass	用户密码
	 * @return
	 */
	public abstract Object login(String userId, String userPass);

}