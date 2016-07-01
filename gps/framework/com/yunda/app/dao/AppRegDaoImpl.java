package com.yunda.app.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.AppRegMapper;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;


/**
 * 接口管理数据库访问接口实现
 * 
 * @author Jiangshui
 * @date	2013-10-13
 *
 */
@Repository(value="appRegDao")
public class AppRegDaoImpl extends JcdfDaoSupport implements AppRegDao{

    @Autowired
    private AppRegMapper appRegMapper;

    @Override
    public Page pageQuery(AppReg appReg) throws Exception {
        return this.pageQuery(appRegMapper, "pageQuery", appReg);
    }

	@Override
	public AppReg checkAppRegIsExist(AppReg appReg) {
		return appRegMapper.checkAppRegIsExist(appReg);
	}


	@Override
	public AppReg get(String ak) {
		return appRegMapper.get(ak);
	}

	@Override
	public void insert(AppReg t) {
		appRegMapper.insert(t);
	}

	@Override
	public void update(AppReg t) {
		appRegMapper.update(t);
	}

	@Override
	public void updateById(AppReg t) {
		appRegMapper.updateById(t);
	}
}