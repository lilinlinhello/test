package com.yunda.gps.vehicleMaintain.serviceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.dao.DicDao;
import com.yunda.app.dao.SysUserDao;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.fixUsers.data.FixUsers;
import com.yunda.gps.fixUsers.mapper.FixUsersMapper;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.mapper.VehicleCategoryMapper;
import com.yunda.gps.vehicleMaintain.dao.GpsRawDataDao;
import com.yunda.gps.vehicleMaintain.dao.VehicleMaintainDao;
import com.yunda.gps.vehicleMaintain.mapper.VehicleMaintainMapper;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.service.VehicleMaintainService;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;
import com.yunda.gps.vehicleRecord.mapper.VehicleRecordMapper;

@Service(value="vehicleMaintainService")
public class VehicleMaintainServiceImpl implements VehicleMaintainService {
	Log log = LogFactory.getLog(VehicleMaintainServiceImpl.class);
	
	@Autowired
	private GpsRawDataDao gpsRawDataDao;
	@Resource(name="vehicleMaintainDao")
	private VehicleMaintainDao vehicleMaintainDao;
	
	@Resource(name="dicDao")
	private DicDao dicDao;
	
	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	
	@Autowired
	private VehicleCategoryMapper vehicleCategoryMapper;
	
	@Autowired
	private VehicleMaintainMapper vehicleMaintainMapper;
	
	@Autowired VehicleRecordMapper vehicleRecordMapper;
	
	@Autowired
	private FixUsersMapper fixUserMapper;

	@Override
	public List<VehicleMaintainVo> list() {
		return null;
	}

	@Override
	public VehicleMaintainVo get(String id) {
		return null;
	}

	@Override
	public Object insert(VehicleMaintainVo t) {
		return null;
	}

	@Override
	public Object update(VehicleMaintainVo t) {
		return null;
	}
	
	/**
	 * 根据id 删除车辆安装维护信息
	 */
	@Override
	public Object deleteById(String ids) {
		String message = null;
		int successNum = 0;
		int totalNum = 0;
		if(StringUtil.isNotNullStr(ids)){
			String[] idsArr = ids.split(",");
			totalNum = idsArr.length;
			for(String id:idsArr){
				try {
					//删除车辆安装维护信息(逻辑删除)
					vehicleMaintainDao.deleteById(id);
					/*****更新分表记录表车牌状态@author gdd*****/
					VehicleMaintain result = vehicleMaintainMapper.selectById(id);
					if(result !=null && StringUtil.isNotNullStr(result.getVehicleId())){
						VehicleRecord record = new VehicleRecord();
						record.setVehicleId(result.getVehicleId()); //获取车牌编码
						record.setDeleteFlag(Integer.valueOf(Constant.DELETE_FLAG));
						vehicleRecordMapper.updateByPrimaryKeySelective(record); //更新分表状态为删除状态
					}
					/*****更新分表记录表车牌状态@author gdd*****/
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
				successNum = successNum+1;
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if(0==successNum){
			message = "删除失败";
		}else if(totalNum == successNum){
			message = "成功删除"+successNum+"条记录";
		}else if(successNum < totalNum){
			message = "成功删除"+(successNum)+"条记录，失败删除"+(totalNum-successNum)+"条记录！" ;
		}
		return new Message(successNum > 0, message);
	}
	
	/**
	 * 分页查询车辆安装维护
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page pageQuery(VehicleMaintainVo vehicleMaintainVo) {
		Page page = null;
		
		try {
			
			String item = vehicleMaintainVo.getItem();
			if(StringUtil.isNotNullStr(item)){
				if(item.equals("SIM卡号")){
					if(StringUtil.isNotNullStr(vehicleMaintainVo.getLicensecard())){
						vehicleMaintainVo.setSimPhoneno(vehicleMaintainVo.getLicensecard());
						vehicleMaintainVo.setLicensecard("");
					}
				}else if(item.equals("设备编码")){
					if(StringUtil.isNotNullStr(vehicleMaintainVo.getLicensecard())){
						vehicleMaintainVo.setEquipId(vehicleMaintainVo.getLicensecard());
						vehicleMaintainVo.setLicensecard("");
					}
				}
			}
			page = vehicleMaintainDao.pageQuery(vehicleMaintainVo);
			List<VehicleMaintain> list = new ArrayList<VehicleMaintain>();
			list = (List<VehicleMaintain>) page.getRows();
			Map<String,String> params = new HashMap<String,String>();
			
			//下拉框
			params.put("status", "dic.status");
			params.put("camcorderMemory", "dic.camcorderMemory");
			params.put("checkLineType", "dic.checkLineType");
			params.put("openfixType", "dic.openfixType");
			//1:是，0:否
			params.put("isTemp", "0/1");
			params.put("isShowAutoLicensecard", "0/1");
			params.put("longtimeElectric", "0/1");
			params.put("acc", "0/1");
			params.put("isGuard", "0/1");
			params.put("isSendMessage", "0/1");
			params.put("isDvr3gcamera", "0/1");
			
			Set<String> dicFields = params.keySet();
			
			
			//转换
			for (VehicleMaintain vm : list) {
				//转换公司名称、运营属性、车辆分类
				String corpCode = vm.getCorpId()==null?"":vm.getCorpId();
				String operAttrCode = vm.getOperAttr()==null?"":vm.getOperAttr();
				String typeCode = vm.getTypeId()==null?"":vm.getTypeId();
				//转换设备型号
				if(StringUtil.isNotNullStr(vm.getEquipType())){
					VehicleCategoryVo vo = vehicleCategoryMapper.selectById(vm.getEquipType());
					if(vo!=null){
						vm.setEquipType(vo.getName());
					}
				}
				//转换设备品牌
				if(StringUtil.isNotNullStr(vm.getEquipBrand())){
					VehicleCategoryVo vo = vehicleCategoryMapper.selectById(vm.getEquipBrand());
					if(vo!=null){
						vm.setEquipBrand(vo.getName());
					}
				}
				//创建人
				if(StringUtil.isNotNullStr(vm.getCreateBy())){
					SysUser userInfo = sysUserDao.getUserInfo(vm.getCreateBy());
					if(userInfo!=null){
						String name = userInfo.getUserName()==null?"":userInfo.getUserName();
						vm.setCreateBy(name);
					}
				}
				//更新人
				if(StringUtil.isNotNullStr(vm.getUpdateBy())){
					SysUser userInfo = sysUserDao.getUserInfo(vm.getUpdateBy());
					if(userInfo!=null){
						String name = userInfo.getUserName()==null?"":userInfo.getUserName();
						vm.setUpdateBy(name);
					}
				}
				//安装人
//				if(StringUtil.isNotNullStr(vm.getInstallUser())){
//					SysUser userInfo = sysUserDao.getUserInfo(vm.getInstallUser());
//					if(userInfo!=null){
//						String name = userInfo.getUserName()==null?"":userInfo.getUserName();
//						vm.setInstallUser(name);
//					}
//				}
				if(StringUtil.isNotNullStr(vm.getInstallUser())){
					FixUsers userInfo = fixUserMapper.selectByPrimaryKey(Integer.parseInt(vm.getInstallUser()));
					if(userInfo!=null){
						String name = userInfo.getName() ==null ? "":userInfo.getName();
						vm.setInstallUser(name);
					}
				}
				
				if(StringUtil.isNotNullStr(corpCode) && corpCode.length()==9){
					VehicleCategoryVo vc = vehicleCategoryMapper.selectById(corpCode);
					if(vc!=null){
						vm.setCorpId(vc.getName());
					}
				}
				if(StringUtil.isNotNullStr(operAttrCode) && corpCode.length()==9){
					VehicleCategoryVo vc = vehicleCategoryMapper.selectById(operAttrCode);
					if(vc!=null){
						vm.setOperAttr(vc.getName());
					}
				}
				if(StringUtil.isNotNullStr(typeCode) && typeCode.length()==9){
					VehicleCategoryVo vc = vehicleCategoryMapper.selectById(typeCode);
					if(vc!=null){
						vm.setTypeId(vc.getName());
					}
				}
				for (String s : dicFields) {
					Field f = vm.getClass().getDeclaredField(s);
					f.setAccessible(true);
					Object value = f.get(vm);
					if(null!=value){
						if("0/1".equals(params.get(s))){
							int v = Integer.parseInt(value.toString());
							if(v==0){
								f.set(vm,"否");
							}else{
								f.set(vm,"是");
							}
						}else{
							if(null!=value){
								f.set(vm, dicDao.codeToName(value.toString()));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null == page ? new Page():page;
	}

	/**
	 *  所有车牌号码下拉列表查询 
	 * @param inputValue   车牌号码
	 * @author lilinlin
	 * @date  2016-1-22
	 * @return  List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryAllCarNum(Map<String, Object> params) {
		return vehicleMaintainDao.queryAllCarNum(params);
	}
	
	
  /**
   * 新增车辆设备维护信息
   */
    @Override
    public Message addVehicleMaintain(VehicleMaintain vehicleMaintain) {
            Message msg = null;
            try {
                this.vehicleMaintainDao.addVehicleMaintain(vehicleMaintain);
                msg = new Message(true,"新增车辆安装成功");
                
                /*******往分表记录表插入数据@author gdd***********/
                if(vehicleMaintain !=null && StringUtil.isNotNullStr(vehicleMaintain.getVehicleId())){
                	String vehicleId = vehicleMaintain.getVehicleId();
                	 VehicleRecord record = new VehicleRecord();
                     record.setVehicleId(vehicleId);
                     record.setCreateBy(vehicleMaintain.getCreateBy());
                     record.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
                     record.setDeleteFlag(Integer.valueOf(Constant.NO_DELETE_FLAG));
                     VehicleRecord result = vehicleRecordMapper.selectByPrimaryKey(vehicleId);
                     if(result !=null){
                    	 record.setUpdateBy(vehicleMaintain.getCreateBy());
                    	 record.setUpdateTime(DateUtil.getDateYMDHMs(new Date()));
                    	 record.setCreateBy(null);
                    	 record.setCreateTime(null);
                    	 vehicleRecordMapper.updateByPrimaryKeySelective(record);
                     }else{
                    	 insert(record);
                     }                    
                }               
                /*******往分表记录表插入数据@author gdd***********/
                
            } catch (Exception e) {
                msg = new Message(false,"新增车辆安装失败");
                e.printStackTrace();
                log.error(e.getMessage());
            }
            return msg;
        }

        @Override
        public VehicleMaintain selectById(String id) {
            VehicleMaintain vehicleMaintain = this.vehicleMaintainMapper.selectById(id);
            return vehicleMaintain;
        }
        /***
         * 修改车辆设备维护
         * @param vehicleMaintain
         * ***/
        @Override
        public Message updateVehicleMaintain(VehicleMaintain vehicleMaintain) {
            Message msg = null;
            try {
                vehicleMaintainMapper.updateByPrimaryKeySelective(vehicleMaintain);
                msg = new Message(true,"修改成功");
                /*******往分表记录表插入数据@author gdd***********/
                if(vehicleMaintain !=null && StringUtil.isNotNullStr(vehicleMaintain.getVehicleId())){
                	String vehicleId = vehicleMaintain.getVehicleId();
                	 VehicleRecord record = new VehicleRecord();
                     record.setVehicleId(vehicleId);
                     record.setUpdateBy(vehicleMaintain.getUpdateBy());
                     record.setUpdateTime(DateUtil.getDateYMDHMs(new Date()));
                     record.setDeleteFlag(Integer.valueOf(Constant.NO_DELETE_FLAG));
                     VehicleRecord result = vehicleRecordMapper.selectByPrimaryKey(vehicleId);
                     if(result !=null){
                    	 vehicleRecordMapper.updateByPrimaryKeySelective(record);
                     }else{
                    	 insert(record);
                     }                    
                }   
                
            } catch (Exception e) {
                msg = new Message(false,"修改失败");
                e.printStackTrace();
                log.error(e.getMessage());
            }
            return msg;
        }

        @Override
        public List<VehicleMaintain> validateVehicleMaintain(VehicleMaintain vehicleMaintain) {
            return this.vehicleMaintainMapper.validateVehicleMaintain(vehicleMaintain);
        }
        /**
         * 分表数据插入
         * @author gdd
         * @param record
         */
        public synchronized void insert(VehicleRecord record){
		String vehicleId = record.getVehicleId();
		if (StringUtil.isNullStr(vehicleId)) {
			return;
		}
		Map<String, String> map = vehicleRecordMapper
				.selectMaxTableName(record);
		Integer subscript = 1;
		if (map != null && map.get("NAME") != null
				&& map.get("NAME").indexOf(Constant.TABLE_NAME) != -1) {
			int length = Constant.TABLE_NAME.length();
			subscript = Integer.parseInt(map.get("NAME").substring(length));// 获取表中表字段最大下标值
		}
		String tableName = Constant.TABLE_NAME + subscript;// 生成表名字段值
		record.setTableName(tableName);
		record.setDeleteFlag(Constant.NO_DELETE_FLAG);
		Integer count = vehicleRecordMapper.selectCount(record);
		if (subscript == 1 && count < Constant.MAX_CAR_RECORD) { // 说明还在生成第一张表
			vehicleRecordMapper.insertSelective(record);
		} else { // 说明之前已经生成若干张表
			Double disCount = Constant.MAX_CAR_RECORD
					* Constant.DISCOUNT_PARAM;
			tableName = Constant.TABLE_NAME + subscript;
			record.setTableName(tableName);
			Integer count1 = vehicleRecordMapper.selectCount(record);
			if (count1 < disCount) {
				vehicleRecordMapper.insertSelective(record); // 最近一张表记录数，只要小于打折数目，直接插入
			} else {
				Boolean flag = false;
				for (int i = 1; i < subscript; i++) {
					tableName = Constant.TABLE_NAME + i;
					record.setTableName(tableName);
					Integer count2 = vehicleRecordMapper.selectCount(record);
					if (count2 < disCount) {
						vehicleRecordMapper.insertSelective(record);
						flag = true; // 标示数据成功插入
						break;
					} else {
						continue;
					}
				}
				if (!flag && count1 < Constant.MAX_CAR_RECORD) { // 未插入成功,且小于最大记录数
					tableName = Constant.TABLE_NAME + subscript; //
					record.setTableName(tableName);
					vehicleRecordMapper.insertSelective(record);
				} else {
					subscript++;
					tableName = Constant.TABLE_NAME + subscript; // 新增一张表
					record.setTableName(tableName);
					vehicleRecordMapper.insertSelective(record);
				}
			}
		}
    	}
        
        public String findVehiclePart(String vehicleId){
        	return vehicleMaintainMapper.findVehiclePart(vehicleId);
        }
        
        public List<Map<String, Object>> findRecentGpsData(String tableName,String vehicleId){
        	return gpsRawDataDao.findRecentGpsData(tableName,vehicleId);
        }

}
