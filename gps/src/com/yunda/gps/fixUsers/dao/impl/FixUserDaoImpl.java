package com.yunda.gps.fixUsers.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.fixUsers.dao.FixUsersDao;
import com.yunda.gps.fixUsers.data.FixUsers;
import com.yunda.gps.fixUsers.mapper.FixUsersMapper;

@Repository(value="fixUserDaoImpl")
public class FixUserDaoImpl extends JcdfDaoSupport implements FixUsersDao {

	@Autowired
	private FixUsersMapper fixUsersMapper;
	
	@Override
	public Page pageQuery(FixUsers vo) {
		Page page = null;
		try {
			page = this.pageQuery(fixUsersMapper,"pageQuery",vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
