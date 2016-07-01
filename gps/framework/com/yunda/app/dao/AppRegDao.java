package com.yunda.app.dao;

import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.vo.Page;

/**
 * 角色数据库访问接口，负责定义接口操作协议
 * 
 * @author JiangShui
 * @date 2013-10-13
 * 
 */
public interface AppRegDao {

	/**
	 * 分页查询数据
	 * 
	 * @param 数据筛选参数
	 *            ，包括分页参数
	 * 
	 * @return 分页数据的封装对象
	 */
	Page pageQuery(AppReg appReg) throws Exception;

	AppReg checkAppRegIsExist(AppReg appReg);

	AppReg get(String ak);

	void insert(AppReg appReg);

	void update(AppReg appReg);

	void updateById(AppReg appReg);

}