package com.yunda.gps.vehicleRecord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleRecord.bean.VehicleRecord;
import com.yunda.gps.vehicleRecord.service.VehicleRecordService;

@Controller
@RequestMapping(value = "/vehicleRecord")
public class VehicleRecordController extends BaseController{
    
    @Resource(name = "vehicleRecordService")
    private VehicleRecordService vehicleRecordService;
    
    @RequestMapping(value = "vehicleRecordManager.do",method= RequestMethod.GET)
    public String toVehicleRecordPage(){
        return "vehicleRecord/vehicleRecord_list";
    }

    /**
     * 分页查询车辆监控管理
     * @param request
     * @param response
     * @param vehicleMaintainVo
     */
    @RequestMapping(value="vehicleRecordQuery.do")
    public void pageQuery(HttpServletRequest request,HttpServletResponse response,VehicleRecord vehicleRecord){
            Page page = vehicleRecordService.pageQuery(vehicleRecord);
            this.setTotal(page.getTotal());
            sendJsonDataToClient(page, response);
    }
}
