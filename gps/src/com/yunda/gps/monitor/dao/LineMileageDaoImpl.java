package com.yunda.gps.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.monitor.mapper.LineMileageMapper;
import com.yunda.gps.monitor.pojo.LineMileage;
import com.yunda.gps.util.CustomerContextHolder;

@Repository(value="lineMileageDaoImpl")
public class LineMileageDaoImpl extends JcdfDaoSupport implements LineMileageDao {

	@Autowired
	private LineMileageMapper lineMileageMapper;
	
	@Override
	public Page pageQuery(LineMileage lineMileage) {
		Page page = null;
		try {
			//切换数据源
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.DATA_SOURCE_SERVICE);
			page = this.pageQuery(lineMileageMapper, "pageQuery", lineMileage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
		}
		return page;
	}

}
