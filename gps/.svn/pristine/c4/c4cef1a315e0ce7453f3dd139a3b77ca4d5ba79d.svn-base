package com.yunda.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.SysUserMapper;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;

/**
 * 用户数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="sysUserDao")
public class SysUserDaoImpl extends JcdfDaoSupport implements SysUserDao {

	/**用户数据库操作工具*/
	@Autowired
	private SysUserMapper sysUserMapper = null;
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.ISysUserDao#pageQuery(com.yunda.app.entity.pojo.SysUser)
	 */
	@Override
	public Page pageQuery(SysUser sysUser) throws Exception {
		return this.pageQuery(sysUserMapper, "pageQuery", sysUser);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		sysUserMapper.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public SysUser get(String id) {
		return sysUserMapper.get(id);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void insert(SysUser t) {
		sysUserMapper.insert(t);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public List<SysUser> list() {
		return sysUserMapper.list();
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void update(SysUser t) {
		sysUserMapper.update(t);
	}

	@Override
	public List<String> queryUserMenuCode(String userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.queryUserMenuCode(userId);
	}

	@Override
	public List<SysUser> listAll(String userId) {
		return sysUserMapper.listAll(userId);
	}

	@Override
	public SysUser getUserInfo(String userId) {
		return sysUserMapper.getUserInfo(userId);
	}
	
}