package com.yunda.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.Role;
import com.yunda.app.entity.pojo.RoleUserMap;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.RoleMapper;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;


/**
 * 角色数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="roleDao")
public class RoleDaoImpl extends JcdfDaoSupport implements RoleDao{

	/**角色数据库操作工具*/
	@Autowired
	private RoleMapper roleMapper = null;
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.RoleDao#pageQuery(com.yunda.app.entity.pojo.Role)
	 */
	@Override
	public Page pageQuery(Role role) throws Exception {
		return this.pageQuery(roleMapper, "pageQuery", role);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void insert(Role r) {
		if(null != r) {
			r.setCreateTime(new Date());
		}
		roleMapper.insert(r);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public List<Role> list() {
		return roleMapper.list();
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public Role get(String id) {
		return roleMapper.get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void update(Role r) {
		roleMapper.update(r);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void deleteById(String id) {
		roleMapper.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.RoleDao#deleteRoleUserMap(com.yunda.app.entity.RoleUserMap)
	 */
	@Override
	public void deleteRoleUserMap(RoleUserMap roleUserMap) {
		roleMapper.deleteRoleUserMap(roleUserMap);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.RoleDao#getRoleUserMap(com.yunda.app.entity.RoleUserMap)
	 */
	@Override
	public List<String> getRoleUserMap(RoleUserMap roleUserMap) {
		return roleMapper.getRoleUserMap(roleUserMap);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.RoleDao#insertRoleUserMap(com.yunda.app.entity.RoleUserMap)
	 */
	@Override
	public void insertRoleUserMap(RoleUserMap roleUserMap) {
		roleMapper.insertRoleUserMap(roleUserMap);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.RoleDao#checkRoleIsExist(com.yunda.app.entity.pojo.Role)
	 */
	@Override
	public Role checkRoleIsExist(Role role) {
		return roleMapper.checkRoleIsExist(role);
	}

	/**
	 *通过userType查找roleId
	 * 
	 * @param usertype0
	 */
	@Override
	public String queryRoleIdByUserType(String usertype) {
		return roleMapper.queryRoleIdByUserType(usertype);
	}
}