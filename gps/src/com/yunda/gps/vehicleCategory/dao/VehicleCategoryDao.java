package com.yunda.gps.vehicleCategory.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;

public interface VehicleCategoryDao {

	public Page pageQuery(VehicleCategoryVo vo);
	
}
