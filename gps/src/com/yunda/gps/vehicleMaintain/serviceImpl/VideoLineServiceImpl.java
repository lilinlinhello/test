package com.yunda.gps.vehicleMaintain.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Message;
import com.yunda.gps.vehicleMaintain.mapper.VideoLineMapper;
import com.yunda.gps.vehicleMaintain.pojo.VideoLine;
import com.yunda.gps.vehicleMaintain.service.VideoLineService;

@Service
public class VideoLineServiceImpl implements VideoLineService {
    
    @Autowired
    private VideoLineMapper videoLineMapper;

    /**
     * 新增摄像头信息
     * @return
     */
    @Override
    public Message addVideoLine(VideoLine videoLine) {
        Message msg = null;
        try {
            this.videoLineMapper.insertVideoLine(videoLine);
            msg = new Message(true,"新增摄像头成功");
        } catch (Exception e) {
            msg = new Message(false,"新增摄像头失败");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 根据vehicleMaintainId查询摄像头信息
     * @return
     */
    @Override
    public List<VideoLine> queryByVehicleMaintainId(String id) {
        return this.videoLineMapper.queryByVehicleMaintainId(id);
    }

    @Override
    public Message updateVideoLineByVehicelMaintainId(String vehicleMaintainId) {
        Message msg = null;
        VideoLine videoLine = new VideoLine();
        videoLine.setVehicleMaintainId(vehicleMaintainId);
        try {
            this.videoLineMapper.updateVideoLineByVehicleMaintainId(videoLine);
            msg = new Message(true,"修改成功");
        } catch (Exception e) {
            msg =  new Message(false,"修改失败");
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public Message deleteVideoLineByVehicelMaintainId(String vehicleMaintainId) {
        Message msg = null;
        VideoLine videoLine = new VideoLine();
        videoLine.setVehicleMaintainId(vehicleMaintainId);
        try {
            this.videoLineMapper.deleteVideoLineByVehicleMaintainId(videoLine);
            msg = new Message(true,"删除成功");
        } catch (Exception e) {
            msg =  new Message(false,"删除失败");
            e.printStackTrace();
        }
        return msg;
    }

}
