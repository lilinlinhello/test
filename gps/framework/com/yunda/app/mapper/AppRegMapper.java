package com.yunda.app.mapper;

import java.util.List;

import com.yunda.app.entity.pojo.AppReg;

public interface AppRegMapper {
	List<AppReg> pageQuery(AppReg appReg);
	
	AppReg checkAppRegIsExist(AppReg appReg) ;
	
	void updateById(AppReg record);

    int insert(AppReg record);
	/**
	 * 查询所有的应用注册信息
	 * 
	 * @return
	 */
	List<AppReg> list();
	
	/**
	 * 通过应用注册ak查询应用注册信息
	 * 
	 * @param ak
	 * @return
	 */
	 AppReg get(String ak);
	
	/**
	 * 根据应用注册名称(appName)进行应用注册信息修改
	 * 
	 * @param appReg
	 */
	 void update(AppReg appReg);

}