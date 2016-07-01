package com.yunda.gps.gpsEquipRegister.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;
import com.yunda.gps.gpsEquipRegister.dao.GpsEquipRegisterDao;
import com.yunda.gps.gpsEquipRegister.mapper.GpsEquipRegisterMapper;

/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
@Repository(value="gpsEquipRegisterDao")
public class GpsEquipRegisterDaoImpl extends JcdfDaoSupport implements GpsEquipRegisterDao {

	@Autowired
	private GpsEquipRegisterMapper gpsEquipRegisterMapper;

	/**
	 *  分页查询GPS设备维护登记信息 
	 * @param GpsEquipRegister	数据筛选参数,包括分页参数
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(GpsEquipRegister gpsEquipRegister) throws Exception{
		return this.pageQuery(gpsEquipRegisterMapper, "pageQuery", gpsEquipRegister);
	}

	/**
	 * 新增GPS设备登记记录
	 * @param gpsEquipRegister
	 */
	public void insertGpsER(GpsEquipRegister gpsEquipRegister) {
		gpsEquipRegisterMapper.insertGpsER(gpsEquipRegister);
	}

	/**
	 * 查询OaId对应的记录
	 * @param oaId
	 * @return GpsEquipRegister
	 */
	public GpsEquipRegister selectByOaId(String oaId) {
		return gpsEquipRegisterMapper.selectByOaId(oaId);
	}

	/**
	 *  修改GPS设备登记记录
	 * @param GpsEquipRegister
	 */
	public void updateGpsER(GpsEquipRegister gpsEquipRegister) {
		gpsEquipRegisterMapper.updateGpsER(gpsEquipRegister);
	}

	@Override
	public void deleteByOaId(String[] idStr) {
		gpsEquipRegisterMapper.deleteByOaId(idStr);
	}
}
