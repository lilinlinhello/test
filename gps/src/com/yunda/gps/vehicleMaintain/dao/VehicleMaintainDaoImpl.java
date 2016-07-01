package com.yunda.gps.vehicleMaintain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.vehicleMaintain.mapper.VehicleMaintainMapper;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;

@Repository(value="vehicleMaintainDao")
public class VehicleMaintainDaoImpl extends JcdfDaoSupport implements VehicleMaintainDao {
	
	@Autowired
	private VehicleMaintainMapper vehicleMaintainMapper;

	@Override
	public List<VehicleMaintainVo> list() {
		return null;
	}

	@Override
	public VehicleMaintainVo get(String id) {
		return vehicleMaintainMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insert(VehicleMaintainVo t) {
		
	}

	@Override
	public void update(VehicleMaintainVo t) {
		
	}
	
	/**
	 * 根据id 删除车辆安装维护信息
	 * @throws Exception 
	 */
	@Override
	public void deleteById(String id){
		vehicleMaintainMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询车辆安装维护
	 * @throws Exception 
	 */
	@Override
	public Page pageQuery(VehicleMaintainVo vehicleMaintainVo) throws Exception {
		return this.pageQuery(vehicleMaintainMapper, "pageQuery", vehicleMaintainVo);
	}

	/**
	 *  所有车牌号码下拉列表查询 
	 * @param inputValue   车牌号码
	 * @author lilinlin
	 * @date  2016-1-22
	 * @return  List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryAllCarNum(Map<String, Object> params) {
		return vehicleMaintainMapper.queryAllCarNum(params);
	}

        @Override
        public void addVehicleMaintain(VehicleMaintain vehicleMaintain) {
            vehicleMaintainMapper.insert(vehicleMaintain);
        }

}
