package com.yunda.gps.vehicleCategory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.mapper.VehicleCategoryMapper;

@Repository("vehicleCategoryDaoImpl")
public class VehicleCategoryDaoImpl extends JcdfDaoSupport implements VehicleCategoryDao{

	@Autowired
	private VehicleCategoryMapper vehicleCategoryMapper;
	
	@Override
	public Page pageQuery(VehicleCategoryVo vo) {
		Page page = null;
		try {
			page = this.pageQuery(vehicleCategoryMapper,"pageQuery",vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
