package com.yunda.gps.vehicleMaintain.service;


import java.util.List;

import com.yunda.app.entity.vo.Message;
import com.yunda.gps.vehicleMaintain.pojo.VideoLine;

public interface VideoLineService {

    /**
     * 新增摄像头信息
     * @return
     */
    Message addVideoLine(VideoLine videoLine);

    /**
     * 查询摄像头信息
     * @return
     */
    List<VideoLine> queryByVehicleMaintainId(String id);

    Message updateVideoLineByVehicelMaintainId(String id);

    Message deleteVideoLineByVehicelMaintainId(String id);

}
