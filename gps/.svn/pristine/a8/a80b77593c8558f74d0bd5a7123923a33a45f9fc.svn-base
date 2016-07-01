package com.yunda.app.dao;

import java.util.List;

import com.yunda.app.base.BaseDao;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.Page;

/**
 * 用户数据库访问接口
 * 
 * @author Jiangshui
 * @date	2013-10-13
 * 
 */
public interface SysUserDao extends BaseDao<SysUser>{

	/**
	 * 分页查询用户数据
	 * 
	 * @param sysUser	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(SysUser sysUser) throws Exception;

	/**
	 * 根据用户账号(userId)获取用户所有有权限的资源编码
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> queryUserMenuCode(String userId);

	/**
	 * 根据用户账号(userId)获取用户所有有权限的资源编码
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysUser> listAll(String userId);
	/**
	 * 根据id 查询用户信息
	 * @param userId
	 * @return
	 */
	public SysUser getUserInfo(String userId);
}