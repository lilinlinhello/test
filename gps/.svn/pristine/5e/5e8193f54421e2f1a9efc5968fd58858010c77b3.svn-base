package com.yunda.gps.vehicleRecord.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;
import com.yunda.gps.vehicleRecord.mapper.VehicleRecordMapper;

@Repository(value="vehicleRecordDao")
public class VehicleRecordDaoImpl extends JcdfDaoSupport implements VehicleRecordDao{
    
    @Autowired
    private VehicleRecordMapper vehicleRecordMapper;

    @Override
    public Page pageQuery(VehicleRecord vehicleRecord) throws Exception {
        return this.pageQuery(vehicleRecordMapper, "pageQuery", vehicleRecord);
    }

}
