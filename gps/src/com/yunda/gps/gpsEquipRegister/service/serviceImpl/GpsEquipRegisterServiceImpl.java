package com.yunda.gps.gpsEquipRegister.service.serviceImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.dao.DicDao;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.SysUserMapper;
import com.yunda.gps.gpsEquipRegister.bean.GpsEquipRegister;
import com.yunda.gps.gpsEquipRegister.dao.GpsEquipRegisterDao;
import com.yunda.gps.gpsEquipRegister.service.GpsEquipRegisterService;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleMaintain.dao.VehicleMaintainDao;
import com.yunda.gps.vehicleMaintain.mapper.VehicleMaintainMapper;

/**
 * GPS设备维护登记
 * @author lilinlin
 * @Time 2016-01-20
 */
@Service(value="gpsEquipRegisterService")
@Transactional
public class GpsEquipRegisterServiceImpl implements GpsEquipRegisterService {

	Log log=LogFactory.getLog(GpsEquipRegisterServiceImpl.class);
	
	@Resource(name="gpsEquipRegisterDao")
	private GpsEquipRegisterDao gpsEquipRegisterDao;
    @Autowired
	private VehicleMaintainDao vehicleMaintainDao;
    @Autowired
    private DicDao dicDao;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
	private VehicleMaintainMapper vehicleMaintainMapper;
    
	/**
	 * 分页查询GPS设备维护登记表
	 * @param gpsEquipRegister
	 * @return  page
	 */
	public Page pageQuery(GpsEquipRegister gpsEquipRegister){
		Page page=null;
		try {
			page=gpsEquipRegisterDao.pageQuery(gpsEquipRegister);
			if(page!=null){
				@SuppressWarnings("unchecked")
				List<GpsEquipRegister> list = (List<GpsEquipRegister>) page.getRows(); // 分页数据
				Map<String,String> params = new HashMap<String,String>();
				params.put("equipStatus","dic.equipStatusType");//设备当前状态
				params.put("repairReason","dic.repairReasonType");//维修原因
				Set<String> dicFields = params.keySet();
				for (GpsEquipRegister vo : list) {
					if(StringUtil.isNotNullStr(vo.getInstallPerson())){
						SysUser installUser=sysUserMapper.get(vo.getInstallPerson());
						if(installUser!=null){
							String installPerson= installUser.getUserName()==null?"":installUser.getUserName();
							vo.setInstallPerson(installPerson);
						}
                     }
					for(String s :dicFields){
						Field f = vo.getClass().getDeclaredField(s) ;
						f.setAccessible(true) ;
						Object value = f.get(vo) ;
						if(null!=value){
							f.set(vo,dicDao.codeToName(value.toString())) ;
						}
					}
				}
				}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	/**
	 * 新增GPS设备登记记录
	 * @param gpsEquipRegister
	 * @return   message
	 */
	public Object insertGpsER(GpsEquipRegister gpsEquipRegister) {
		if(gpsEquipRegisterDao.selectByOaId(gpsEquipRegister.getOaId())!=null){
			return new Message(false, "该OA编号已存在，新增记录失败！");
		}
		//处理原车牌号
		String tempCar=gpsEquipRegister.getOriginalCarNumber();
		if(StringUtil.isNotNullStr(tempCar)){
			if(vehicleMaintainMapper.selectByCard(tempCar)==null){
				return  new Message(false, "该车牌号不存在！");
			}
		}
		//处理新车牌
		String tempCar1=gpsEquipRegister.getNewCarNumber();
		if(StringUtil.isNotNullStr(tempCar1)){
			if(vehicleMaintainMapper.selectByCard(tempCar1)==null){
				return new Message(false, "新车牌号不存在！");
			}
		}
		//处理安装人
		String installPerson=gpsEquipRegister.getInstallPerson();
		if(StringUtil.isNotNullStr(installPerson)){
			if(sysUserMapper.getById(installPerson)==null){
				return new Message(false, "安装人员不存在！");
			}
		}
		gpsEquipRegisterDao.insertGpsER(gpsEquipRegister);
		return new Message(true, "操作成功！");
	}

	/**
	 * 修改GPS设备登记记录
	 * @param gpsEquipRegister
	 * @return   message
	 */
	public Object updateGpsER(GpsEquipRegister gpsEquipRegister) {
		String[] ids={gpsEquipRegister.getOaId()};
		gpsEquipRegisterDao.deleteByOaId(ids);
		Message mess=(Message)insertGpsER(gpsEquipRegister);
	    return mess;
	}

	/**
	 * 获取一条GPS设备登记记录
	 * @param oaId
	 * @return
	 */
	public GpsEquipRegister selectByOaId(String oaId) {
		return gpsEquipRegisterDao.selectByOaId(oaId);
	}
	
	/**
	 * 批量删除GPS设备维护信息
	 * @param ids
	 * */
	public Object deleteByOaId(String ids) {
		String[] idStr = ids.split(",");
		if (idStr.length > 0) {
			gpsEquipRegisterDao.deleteByOaId(idStr);
			return new Message(true, "删除成功！");
		}
		return new Message(false, "删除失败！");
	}
}
