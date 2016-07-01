package com.yunda.gps.vehicleRecord.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;

public interface VehicleRecordDao {

    /**
     * 分页查询车辆安装维护
     * @param vehicleMaintainVo
     * @return
     */
    public Page pageQuery(VehicleRecord vehicleRecord) throws Exception;
}
