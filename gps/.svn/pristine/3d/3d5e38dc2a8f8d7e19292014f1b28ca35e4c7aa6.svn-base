package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.RunMileageMapper;
import com.yunda.gps.monitor.pojo.RunMileage;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="runMileageDaoImpl")
public class RunMileageDaoImpl extends JcdfDaoSupport implements RunMileageDao {

	@Autowired
	private RunMileageMapper runMileageMapper;
	
	@Override
	public Page pageQuery(RunMileage runMileage) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(runMileageMapper, "pageQuery", runMileage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
