package com.yunda.gps.vehicleRecord.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.dao.SysUserDao;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;
import com.yunda.gps.vehicleRecord.dao.VehicleRecordDao;
import com.yunda.gps.vehicleRecord.mapper.VehicleRecordMapper;

@Transactional
@Service(value="vehicleRecordService")
public class VehicleRecordServiceImpl implements VehicleRecordService {
        Log log = LogFactory.getLog(VehicleRecordServiceImpl.class);
        
        @Autowired
	private VehicleRecordMapper vehicleRecordMapper;
        
        @Autowired
        private VehicleRecordDao vehicleRecordDao;
        
        @Autowired
        private SysUserDao sysUserDao;
        
	
	/**
	 * 车辆数据插入记录表
	 * @param record
	 * @author gdd
	 * @return
	 */
	@Override
	public void insert(VehicleRecord record){
		String vehicleId = record.getVehicleId();
		if(StringUtil.isNullStr(vehicleId)){
			return ;
		}
		VehicleRecord vehicle = vehicleRecordMapper.selectByPrimaryKey(vehicleId);
		if(vehicle !=null){
			record.setTableName(vehicle.getTableName()); //保持车牌所在表字段值一致
			record.setUpdateTime(DateUtil.getDateYMDHMs(new Date()));
			record.setDeleteFlag(Constant.NO_DELETE_FLAG);
			vehicleRecordMapper.updateByPrimaryKeySelective(record);
		}else{
			Map<String,String> map = vehicleRecordMapper.selectMaxTableName(record);
			Integer subscript =1;
			if(map !=null && map.get("NAME")!=null && map.get("NAME").indexOf(Constant.TABLE_NAME)!=-1){
				int length = Constant.TABLE_NAME.length();
				   subscript = Integer.parseInt(map.get("NAME").substring(length));//获取表中表字段最大下标值
			}
			String tableName = Constant.TABLE_NAME+ subscript;//生成表名字段值
			record.setTableName(tableName);
			record.setDeleteFlag(Constant.NO_DELETE_FLAG);
			record.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
			Integer count = vehicleRecordMapper.selectCount(record);
			if(subscript == 1 && count <= Constant.MAX_CAR_RECORD ){ //说明还在生成第一张表
				vehicleRecordMapper.insertSelective(record);
			}else{  //说明之前已经生成若干张表
				Double disCount = Constant.MAX_CAR_RECORD * Constant.DISCOUNT_PARAM ;
				tableName = Constant.TABLE_NAME + subscript ;
				record.setTableName(tableName);
				Integer count1 = vehicleRecordMapper.selectCount(record);  
				if(count1 < disCount ){
					vehicleRecordMapper.insertSelective(record); //最近一张表记录数，只要小于打折数目，直接插入
				}else{
					Boolean flag= false;
					for(int i=1;i<subscript;i++){
						tableName = Constant.TABLE_NAME + i ;
						record.setTableName(tableName);
						Integer count2 = vehicleRecordMapper.selectCount(record);
						if(count2 <= disCount ){
							vehicleRecordMapper.insertSelective(record);
							flag =true;  //标示数据成功插入
							break;
						}else{
							continue ;
						}
					}
					if(!flag && count1 < Constant.MAX_CAR_RECORD ){ //未插入成功,且小于最大记录数
						tableName = Constant.TABLE_NAME + subscript ; //
						record.setTableName(tableName);
						vehicleRecordMapper.insertSelective(record);
					}else{
						subscript++ ;
						tableName = Constant.TABLE_NAME + subscript ; //新增一张表
						record.setTableName(tableName);
						vehicleRecordMapper.insertSelective(record);
					}	
				}
	
			}
			
		}
	}
	
	/**
	 * 记录获取
	 * @param vehicleId
	 * @return
	 */
	@Override
	public VehicleRecord getById(String vehicleId){
		return vehicleRecordMapper.selectByPrimaryKey(vehicleId);
	}
	
	/**
	 * 记录表数据修改
	 * @param record
	 * @return
	 */
	@Override
	public int updateById(VehicleRecord record){
		return vehicleRecordMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 物理删除数据
	 * @param id
	 * @return
	 */
	@Override
	public int deleteById(String vehicleId){
		return vehicleRecordMapper.deleteByPrimaryKey(vehicleId);
	}
	
	/**
         * 分页查询车辆监控管理
         */
        @SuppressWarnings("unchecked")
        @Override
        public Page pageQuery(VehicleRecord vehicleRecord) {
                Page page = null;
                
                try {
                        page = vehicleRecordDao.pageQuery(vehicleRecord);
                        List<VehicleRecord> list = new ArrayList<VehicleRecord>();
                        list = (List<VehicleRecord>) page.getRows();
                        //转换
                        for (VehicleRecord vr : list) {
                                //创建人
                                if(StringUtil.isNotNullStr(vr.getCreateBy())){
                                        SysUser userInfo = sysUserDao.getUserInfo(vr.getCreateBy());
                                        if(userInfo!=null){
                                                String name = userInfo.getUserName()==null?"":userInfo.getUserName();
                                                vr.setCreateBy(name);
                                        }
                                }
                                //更新人
                                if(StringUtil.isNotNullStr(vr.getUpdateBy())){
                                        SysUser userInfo = sysUserDao.getUserInfo(vr.getUpdateBy());
                                        if(userInfo!=null){
                                                String name = userInfo.getUserName()==null?"":userInfo.getUserName();
                                                vr.setUpdateBy(name);
                                        }
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                }
                return null == page ? new Page():page;
        }
}
