package com.yunda.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.Auth;
import com.yunda.app.entity.pojo.Menu;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.MenuMapper;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;

/**
 * 资源数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="menuDao")
public class MenuDaoImpl extends JcdfDaoSupport implements MenuDao{

	/**菜单功能数据库操作工具*/
	@Autowired
	private MenuMapper menuMapper;
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#pageQuery(com.yunda.app.entity.Menu)
	 */
	@Override
	public Page pageQuery(Menu menu) throws Exception {
		return this.pageQuery(menuMapper, "pageQuery", menu);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#findMenuByParentMenuCode(java.lang.String)
	 */
	@Override
	public List<Menu> findMenuByParentMenuCode(String parentMenuCode){
		return menuMapper.findMenuByParentMenuCode(parentMenuCode);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#findButtonByParentMenuCode(java.lang.String)
	 */
	@Override
	public List<Menu> findButtonByParentMenuCode(String parentMenuCode){
		return menuMapper.findButtonByParentMenuCode(parentMenuCode); 
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#findAllByParentMenuCode(java.lang.String)
	 */
	@Override
	public List<Menu> findAllByParentMenuCode(String parentMenuCode){
		return menuMapper.findAllByParentMenuCode(parentMenuCode);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void deleteById(String menuCode) {
		menuMapper.deleteById(menuCode);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public Menu get(String menuCode) {
		return menuMapper.get(menuCode);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void insert(Menu t) {
		menuMapper.insert(t);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public List<Menu> list() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseDao
	 */
	@Override
	public void update(Menu t) {
		menuMapper.update(t);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#insertUserOrRoleAuth(com.yunda.app.entity.Auth)
	 */
	@Override
	public void insertUserOrRoleAuth(Auth auth) {
		menuMapper.insertUserOrRoleAuth(auth);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#getUserOrRoleAuth(com.yunda.app.entity.Auth)
	 */
	@Override
	public List<String> getUserOrRoleAuth(Auth auth) {
		return menuMapper.getUserOrRoleAuth(auth);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.dao.IMenuDao#deleteUserOrRoleAuthByAuth(com.yunda.app.entity.Auth)
	 */
	@Override
	public void deleteUserOrRoleAuthByAuth(Auth auth) {
		menuMapper.deleteUserOrRoleAuthByAuth(auth);
	}

	@Override
	public List<Menu> findAllMenuAndButtonCode(Integer menuType) {
		// TODO Auto-generated method stub
		return menuMapper.findAllMenuAndButtonCode(menuType);
	}
}