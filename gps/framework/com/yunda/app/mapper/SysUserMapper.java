package com.yunda.app.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunda.app.entity.pojo.SysUser;

/**
 * 用户的Mybatis映射工具类
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
public interface SysUserMapper {

	/**
	 * 分页查询用户数据
	 * 
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> pageQuery(SysUser sysUser);
	
	/**
	 * 新增用户
	 * 
	 * @param sysUser
	 * @return
	 */
	public int insert(SysUser sysUser);

	/**
	 * 查询所有的用户信息
	 * 
	 * @return
	 */
	public List<SysUser> list();
	
	/**
	 * 通过用户账号(user_id)查询用户信息登录
	 * 
	 * @param userId
	 * @return
	 */
	public SysUser get(String userId);
	
	/**
	 * 通过用户账号(user_id)查询用户信息修改密码
	 * 
	 * @param userId
	 * @return
	 */
	SysUser getUserInfo(String userId);
	/**
	 * 根据用户账号(user_id)进行用户信息修改
	 * 
	 * @param sysUser
	 */
	public void update(SysUser sysUser);

	/**
	 * 根据用户账号(user_id)删除用户
	 * 
	 * @param userId
	 */
	public void deleteById(String userId);
	
	/**
	 * 根据用户账号(userId)获取用户所有有权限的资源编码
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> queryUserMenuCode(String userId);
	
	/**
	 * 查询所有的用户信息，用于导出
	 * 
	 * @return
	 */
	public List<Object> export(HashMap map);

	public List<SysUser> listAll(String userId);
	
	/**
	 * 根据用户账号(userId)获取用户所有有权限的url
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> queryUserMenuUrls(String userId);

        public List<Map<String, Object>> queryUsername(Map<String, Object> params);
        
     /**
      * 通过用户账号(user_id)查询用户信息登录
      * @param userId
      * @return
      */
    	public SysUser getById(String userId);
        
    	
   /**
    * 动态加载所有的用户信息
    */
    public List<Map<String,Object>> queryAllUser();
}
