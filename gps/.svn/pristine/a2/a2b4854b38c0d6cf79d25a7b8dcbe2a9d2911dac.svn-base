package com.yunda.gps.vehicleMaintain.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFJsonUtil;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.KeyUtil;
import com.yunda.gps.util.PropertiesManager;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.service.VehicleCategoryService;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;
import com.yunda.gps.vehicleMaintain.pojo.VideoLine;
import com.yunda.gps.vehicleMaintain.service.VehicleMaintainService;
import com.yunda.gps.vehicleMaintain.service.VideoLineService;
import com.yunda.gps.vehicleMaintain.vo.VehicleMaintainVo;


@Controller
@RequestMapping(value="/vehicleMaintain")
public class VehicleMaintainController extends BaseController{
	Log log = LogFactory.getLog(VehicleMaintainController.class);
	
	@Resource(name="vehicleMaintainService")
	private VehicleMaintainService vehicleMaintainService;

	@Resource(name="vehicleCategoryServiceImpl")
	private VehicleCategoryService vehicleCategoryService;
	
	@Autowired
	private VideoLineService videoLineService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	/**
	 * 转发到车辆安装维护列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "vehicleMaintain.do",method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> params = new HashMap<String,String>();
		params.put("status", "dic.status");
		List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
		request.setAttribute("dictionarys", JSONArray.fromObject(dics));
		return "vehicleMaintain/vehicleMaintain_list";
		
	}
	
	/**
         * 转发到车辆安装维护新增
         * @return
         */
	@RequestMapping(value="vehicleMaintainAdd.do",method=RequestMethod.GET)
	public String forwardAddJsp(HttpServletRequest request){
	    Map<String,String> params = new HashMap<String,String>();
            params.put("camcorderMemory", "dic.camcorderMemory");
            params.put("status", "dic.status");
            params.put("openfixType", "dic.openfixType");
            params.put("checkLineType", "dic.checkLineType");
            params.put("installStatus", "dic.installStatus");
            List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
            request.setAttribute("dictionarys", JSONArray.fromObject(dics));
            return "vehicleMaintain/vehicleMaintain_add";
	}
	
	/**
         * 车辆安装维护唯一性校验
         * @return
         */
	@RequestMapping(value="validateVehicleMaintain.do")
	public void validateVehicleMaintain(HttpServletResponse response,VehicleMaintain vehicleMaintain){
	    String id = vehicleMaintain.getId() == null ?"":vehicleMaintain.getId().trim();
	    List<VehicleMaintain> list = this.vehicleMaintainService.validateVehicleMaintain(vehicleMaintain);
	    Message msg = null;
	    if( id.equals("")){
	            if( list.size() == 0){
	                msg = new Message(true,"");
	            }else{
	                msg = new Message(false,"");
	            }
	    }else{
	        VehicleMaintain v = this.vehicleMaintainService.selectById(id);
	        if(!(vehicleMaintain.getVehicleId() == null)){
	            if( vehicleMaintain.getVehicleId().equals(v.getVehicleId())){
	                msg = new Message(true,"");
	            }else{
	                if(list.size() == 0){
	                    msg = new Message(true,"");
	                }else{
	                    msg = new Message(false,"");
	                }
	            }
	        }else if( !(vehicleMaintain.getSimPhoneno() == null)){
	            if( vehicleMaintain.getSimPhoneno().equals(v.getSimPhoneno())){
                        msg = new Message(true,"");
                    }else{
                        if(list.size() == 0){
                            msg = new Message(true,"");
                        }else{
                            msg = new Message(false,"");
                        }
                    }
	        }else if( !(vehicleMaintain.getEquipId() == null) ){
	            if( vehicleMaintain.getEquipId().equals(v.getEquipId())){
                        msg = new Message(true,"");
                    }else{
                        if(list.size() == 0){
                            msg = new Message(true,"");
                        }else{
                            msg = new Message(false,"");
                        }
                    }
	        }else{
	            msg = new Message(true,"");
	        }
	    }
	    sendJsonDataToClient(msg, response);
	}
	
	/**
         * 车辆安装维护新增
         * @return
         */
	@RequestMapping(value = "vehicleMaintainAdd.do",method=RequestMethod.POST)
	public void vehicleMaintainAdd(HttpServletResponse response,HttpServletRequest request,VehicleMaintain vehicleMaintain){
	    LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
	    Message msg = null;
	    boolean flag = true;
	    //防止sql注入和对省编码进行校验
	    String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
	    String vehicleId = vehicleMaintain.getVehicleId().trim();
	    String licencecard = vehicleMaintain.getLicensecard().trim();
	    String equipId = vehicleMaintain.getEquipId().trim();
	    String simPhoneno = vehicleMaintain.getSimPhoneno().trim();
	    String simId = vehicleMaintain.getSimId().trim();
	    String province_name = licencecard.substring(0, 1);
            String province_code = vehicleId.substring(0, 2);
            List<String> prov_Name_list = Arrays.asList(Constant.prov_Name);
            List<String> prov_Code_list = Arrays.asList(Constant.prov_Code);
            //车牌号，sim卡号，设备编码合法性校验
	    if( vehicleId == null || licencecard == null || equipId == null || simPhoneno ==null || simId == null || !prov_Name_list.contains(province_name) || !prov_Code_list.contains(province_code) || p.matcher(simPhoneno).find() || p.matcher(simId).find()|| p.matcher(equipId).find()||p.matcher(licencecard).find()||p.matcher(vehicleId).find()){
	        flag = false;
	        msg = new Message(flag,"传入非法数据");
	        sendJsonDataToClient(msg, response);
	        return ;
	    }
	    //sim卡ID号唯一性校验
	    VehicleMaintain simPhonenoV = new VehicleMaintain();
	    simPhonenoV.setSimId(simId);
	    if(!(this.validateVehicleMaintainWithoutId(simPhonenoV)).isResult()){
	        msg = new Message(false,"sim卡ID号重复");
                sendJsonDataToClient(msg, response);
                return;
	    };
	    //车牌号唯一性校验
	    VehicleMaintain vehicleIdV = new VehicleMaintain();
	    vehicleIdV.setVehicleId(vehicleId);
	    if(!(this.validateVehicleMaintainWithoutId(vehicleIdV)).isResult()){
	        msg = new Message(false,"车牌号重复");
	        sendJsonDataToClient(msg, response);
	        return;
	    };
	    //设备编码唯一性校验
	    VehicleMaintain equipIdV = new VehicleMaintain();
	    equipIdV.setEquipId(equipId);
	    if(!(this.validateVehicleMaintainWithoutId(equipIdV)).isResult()){
	        msg = new Message(false,"设备Id重复");
	        sendJsonDataToClient(msg, response);
	        return;
	    };
	    //初始化vehicleMaintain
	    vehicleMaintain.setId(KeyUtil.getIdByTime());
	    vehicleMaintain.setIsTemp("0");
	    vehicleMaintain.setDeleteFlag("0");
	    if(StringUtil.isNullStr(vehicleMaintain.getIsShowAutoLicensecard()) ){
	        vehicleMaintain.setIsShowAutoLicensecard("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getLongtimeElectric())){
	        vehicleMaintain.setLongtimeElectric("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getAcc())){
	        vehicleMaintain.setAcc("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getIsGuard())){
	        vehicleMaintain.setIsGuard("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getIsSendMessage())){
	        vehicleMaintain.setIsSendMessage("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getIsFixcamera())){
	        vehicleMaintain.setIsFixcamera("0");
	    }
	    if(StringUtil.isNullStr(vehicleMaintain.getIsDvr3gcamera())){
	        vehicleMaintain.setIsDvr3gcamera("0");
	    }
	    vehicleMaintain.setCreateBy(user.getUserId());
	    vehicleMaintain.setCreateTime(DateUtil.format(new Date()));
	    msg =  (Message)this.vehicleMaintainService.addVehicleMaintain(vehicleMaintain);
	    if( !msg.isResult()){
	        sendJsonDataToClient(msg, response);
	        return;
	    }
	    //对摄像头进行新增
	    String isFixCamera = vehicleMaintain.getIsFixcamera() == null ?"":vehicleMaintain.getIsFixcamera();
	    if( isFixCamera.equals("1")){
	        String installLineArr =  request.getParameter("installLine");
	            String [] installLine = installLineArr .split(",");
	            String isInstallArr = request.getParameter("isInstall");
	            String [] isInstall = isInstallArr .split(",");
	            String installLocationArr = request.getParameter("installLocation");
	            String[] installLocation = installLocationArr.split(",");
	            String installStatusArr = request.getParameter("installStatus");
	            String [] installStatus = installStatusArr .split(",");
	            String remarkArr = request.getParameter("remark");
	            String [] remark = remarkArr.split(",");
	            for( int i = 0;i<4;i++){
	                String isInstallNo =isInstall[i];
	                if( isInstallNo.equals("0")){
	                    VideoLine videoLine = new VideoLine();
	                        videoLine.setVehicleMaintainId(vehicleMaintain.getId());
	                        videoLine.setInstallLine(installLine[i]);
	                        videoLine.setIsInstall(1);
	                        try {
                                videoLine.setInstallLocation(new String(installLocation[i].getBytes("ISO-8859-1"),"utf-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
	                        videoLine.setInstallStatus(installStatus[i]);
	                        try {
                                    videoLine.setRemark(new String(remark[i].getBytes("ISO-8859-1"),"utf-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
	                        videoLine.setCreateBy(user.getUserId());
	                        videoLine.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
	                        videoLine.setDeleteFlag(0);
	                        msg = (Message)this.videoLineService.addVideoLine(videoLine);
	                        if( !msg.isResult()){
	                            sendJsonDataToClient(msg, response);
	                            return;
	                        }
	                }
	            }
	    }
	    sendJsonDataToClient(msg, response);
	}
	//id不存在时进行唯一性校验
	private Message validateVehicleMaintainWithoutId(VehicleMaintain vehicleMaintain) {
	    List<VehicleMaintain> list = this.vehicleMaintainService.validateVehicleMaintain(vehicleMaintain);
            Message msg = null;
            if(list.size() == 0){
                msg = new Message(true,"");
            }else{
                msg = new Message(false,"");
            }
            return msg;
        }

        //id存在时进行唯一性校验
        private Message validateVehicleMaintain(VehicleMaintain vehicleMaintain,String id) {
	    List<VehicleMaintain> list = this.vehicleMaintainService.validateVehicleMaintain(vehicleMaintain);
	    Message msg = null;
	    VehicleMaintain v = this.vehicleMaintainService.selectById(id);
            if(!(vehicleMaintain.getVehicleId() == null)){
                if( vehicleMaintain.getVehicleId().equals(v.getVehicleId())){
                    msg = new Message(true,"");
                }else{
                    if(list.size() == 0){
                        msg = new Message(true,"");
                    }else{
                        msg = new Message(false,"");
                    }
                }
            }else if( !(vehicleMaintain.getSimPhoneno() == null)){
                if( vehicleMaintain.getSimPhoneno().equals(v.getSimPhoneno())){
                    msg = new Message(true,"");
                }else{
                    if(list.size() == 0){
                        msg = new Message(true,"");
                    }else{
                        msg = new Message(false,"");
                    }
                }
            }else if( !(vehicleMaintain.getEquipId() == null) ){
                if( vehicleMaintain.getEquipId().equals(v.getEquipId())){
                    msg = new Message(true,"");
                }else{
                    if(list.size() == 0){
                        msg = new Message(true,"");
                    }else{
                        msg = new Message(false,"");
                    }
                }
            }else{
                msg = new Message(true,"");
            }
            return msg;
        }

        //字符串分割成数组
	private String[] stringToArr(String str) {
	    int begin = 0;
	    int end = 0;
	    int m = 0;
	    String[] string = new String[4];
	    String[] split = str.split(",");
	    if(split.length==4){
	    	return split;
	    }else{
	    	for(int i=0;i<str.length();i++){
	    		if(str.substring(i, i+1).equals(",")){
	    			end = i;
	    			string[m] = str.substring(begin, end);
	    			begin = i+1;
	    			m++;
	    		}
	    	}
	    	return string;
	    }
        }

    /**
     * 转发到gps数据包详情
     * @return
     */
        @RequestMapping(value="forwardDetailJsp.do",method=RequestMethod.GET)
        public String  forwardDataDetail(HttpServletRequest request, String vehicleId){
        	request.setAttribute("vehicleId", vehicleId);
            return "vehicleMaintain/recentDataDetail";
        }
        
        @RequestMapping(value="findRecentGpsData.do")
        public void findRecentGpsData(HttpServletResponse response,String vehicleId){
        	String tablePart = vehicleMaintainService.findVehiclePart(vehicleId);
        	Date now = new Date();
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        	String tableTime = dateFormat.format(now);
        	String tableName = "gps_veh_package"+"_"+tableTime+"_"+tablePart;
        	List<Map<String, Object>> gpsdataList = vehicleMaintainService.findRecentGpsData(tableName,vehicleId);
        	sendJsonDataToClient(gpsdataList, response);
        }
    /**
         * 转发到车辆安装维护新增
         * @return
         */
        @RequestMapping(value="vehicleMaintainEdit.do",method=RequestMethod.GET)
        public String forwardEditJsp(HttpServletRequest request,String id){
            Map<String,String> params = new HashMap<String,String>();
            params.put("camcorderMemory", "dic.camcorderMemory");
            params.put("status", "dic.status");
            params.put("openfixType", "dic.openfixType");
            params.put("checkLineType", "dic.checkLineType");
            params.put("installStatus", "dic.installStatus");
            List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
            request.setAttribute("dictionarys", JSONArray.fromObject(dics));
            request.setAttribute("vehicleMaintainId", id);
            VehicleMaintain vehicleMaintain = this.vehicleMaintainService.selectById(id);
            String isFixcamera = vehicleMaintain.getIsFixcamera() == null?"":vehicleMaintain.getIsFixcamera();
            if( isFixcamera.equals("1")){
                List<VideoLine> list = this.videoLineService.queryByVehicleMaintainId(id);
                int num = 1;
                for(int i = 0;i<list.size();i++){
                    VideoLine videoLine = list.get(i);
                    String installLine = videoLine.getInstallLine();
                    int no = Integer.parseInt(installLine.substring(6, 7));
                   for(int n = num; n <= no;n++){
                       if( n == no){
                           request.setAttribute("video0"+n, JCDFJsonUtil.fromObjctToJson(videoLine, null));
                           num++;
                       }else{
                           request.setAttribute("video0"+n, "");
                           num++;
                       }
                   } 
                }
            }
            return "vehicleMaintain/vehicleMaintain_edit";
        }
        
        /**
         * 车辆安装维护修改
         * @return
         */
        @RequestMapping(value = "vehicleMaintainUpdate.do",method = RequestMethod.POST)
        public void editVehicleMaintain(HttpServletRequest request,HttpServletResponse response,VehicleMaintain vehicleMaintain){
            LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
            VehicleMaintain v = this.vehicleMaintainService.selectById(vehicleMaintain.getId());
            Message msg = null;
            boolean flag = true;
          //防止sql注入和对省编码进行校验
            String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            String vehicleId = vehicleMaintain.getVehicleId().trim();
            String licencecard = vehicleMaintain.getLicensecard().trim();
            String equipId = vehicleMaintain.getEquipId().trim();
            String simPhoneno = vehicleMaintain.getSimPhoneno().trim();
            String simId = vehicleMaintain.getSimId().trim();
            String province_name = licencecard.substring(0, 1);
            String province_code = vehicleId.substring(0, 2);
            List<String> prov_Name_list = Arrays.asList(Constant.prov_Name);
            List<String> prov_Code_list = Arrays.asList(Constant.prov_Code);
            
        	if("1".equals(v.getDeleteFlag())){
        		msg = new Message(false,"已删除的数据不能修改");
        		sendJsonDataToClient(msg, response);
        		return;
        	}
        	
          //车牌号，sim卡号，设备编码合法性校验
            if( vehicleId == null || licencecard == null || equipId == null || simPhoneno ==null || simId == null || !prov_Name_list.contains(province_name) || !prov_Code_list.contains(province_code) || p.matcher(simPhoneno).find() || p.matcher(simId).find() || p.matcher(equipId).find()||p.matcher(licencecard).find()||p.matcher(vehicleId).find()){
                flag = false;
                msg = new Message(flag,"传入非法数据");
                sendJsonDataToClient(msg, response);
                return;
            }
          //后台进行唯一性校验
            //simPhoneno校验
            VehicleMaintain simPhonenoV = new VehicleMaintain();
            simPhonenoV.setSimId(simId);
            simPhonenoV.setId(vehicleMaintain.getId());
            if(!(this.validateVehicleMaintain(simPhonenoV,vehicleMaintain.getId())).isResult()){
                msg = new Message(false,"sim卡ID号重复");
                sendJsonDataToClient(msg, response);
                return;
            }
            //车牌号唯一性校验
            VehicleMaintain vehicleIdV = new VehicleMaintain();
            vehicleIdV.setVehicleId(vehicleId);
            vehicleIdV.setId(vehicleMaintain.getId());
            if(!(this.validateVehicleMaintain(vehicleIdV,vehicleMaintain.getId())).isResult()){
                msg = new Message(false,"车牌号重复");
                sendJsonDataToClient(msg, response);
                return;
            }
            //车载设备唯一性校验
            VehicleMaintain equipIdV = new VehicleMaintain();
            equipIdV.setEquipId(equipId);
            equipIdV.setId(vehicleMaintain.getId());
            if(!(this.validateVehicleMaintain(equipIdV,vehicleMaintain.getId())).isResult()){
                msg = new Message(false,"车载设备重复");
                sendJsonDataToClient(msg, response);
                return;
            }
            //sim或者车牌号修改时进行逻辑删除然后新增
            if( (!vehicleMaintain.getSimPhoneno().equals(v.getSimPhoneno())) || (!vehicleMaintain.getVehicleId().equals(v.getVehicleId()))){
                v.setDeleteFlag("1");
                v.setUpdateBy(user.getUserId());
                v.setUpdateTime(DateUtil.format(new Date()));
                //逻辑删除vehicleMaintain
                msg = (Message)this.vehicleMaintainService.updateVehicleMaintain(v);
                if( !msg.isResult()){
                    sendJsonDataToClient(msg, response);
                    return ;
                }
                //新增车辆安装
                vehicleMaintain.setId(KeyUtil.getIdByTime());
                vehicleMaintain.setCreateBy(user.getUserId());
//              vehicleMaintain.setUpdateBy(user.getUserId());
//              vehicleMaintain.setUpdateTime(DateUtil.format(new Date()));
//              vehicleMaintain.setCreateTime(v.getCreateTime());
                vehicleMaintain.setCreateTime(DateUtil.format(new Date()));
                vehicleMaintain.setIsTemp("0");
                vehicleMaintain.setDeleteFlag("0");
                if(StringUtil.isNullStr(vehicleMaintain.getIsShowAutoLicensecard())){
                    vehicleMaintain.setIsShowAutoLicensecard("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getLongtimeElectric())){
                    vehicleMaintain.setLongtimeElectric("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getAcc())){
                    vehicleMaintain.setAcc("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getIsGuard())){
                    vehicleMaintain.setIsGuard("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getIsSendMessage())){
                    vehicleMaintain.setIsSendMessage("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getIsFixcamera())){
                    vehicleMaintain.setIsFixcamera("0");
                }
                if(StringUtil.isNullStr(vehicleMaintain.getIsDvr3gcamera())){
                    vehicleMaintain.setIsDvr3gcamera("0");
                }
                msg =  (Message)this.vehicleMaintainService.addVehicleMaintain(vehicleMaintain);
                if( !msg.isResult()){
                    sendJsonDataToClient(msg, response);
                    return ;
                }
                //对摄像头信息进行逻辑删除
                msg = (Message)this.videoLineService.updateVideoLineByVehicelMaintainId(vehicleMaintain.getId());
                if( !msg.isResult()){
                    sendJsonDataToClient(msg, response);
                    return ;
                }
                //对摄像头信息进行新增
                String isFixCamera = vehicleMaintain.getIsFixcamera() == null ?"":vehicleMaintain.getIsFixcamera();
                if( isFixCamera.equals("1")){
                    String installLineArr =  request.getParameter("installLine");
                        String [] installLine = installLineArr .split(",");
                        String isInstallArr = request.getParameter("isInstall");
                        String [] isInstall = isInstallArr .split(",");
                        String installLocationArr = request.getParameter("installLocation");
                        String[] installLocation = installLocationArr.split(",");
                        String installStatusArr = request.getParameter("installStatus");
                        String [] installStatus = installStatusArr .split(",");
                        String remarkArr = request.getParameter("remark");
                         String [] remark = remarkArr.split(",");
                         //避免空指针异常
                         //if()
                        for( int i = 0;i<4;i++){
                            String isInstallNo =isInstall[i];
                            if( isInstallNo.equals("0")){
                                VideoLine videoLine = new VideoLine();
                                    videoLine.setVehicleMaintainId(vehicleMaintain.getId());
                                    videoLine.setInstallLine(installLine[i]);
                                    videoLine.setIsInstall(1);
                                    try {
                                        videoLine.setInstallLocation(new String(installLocation[i].getBytes("ISO-8859-1"),"utf-8"));
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                    videoLine.setInstallStatus(installStatus[i]);
                                    try {
                                        videoLine.setRemark(new String(remark[i].getBytes("ISO-8859-1"),"utf-8"));
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    videoLine.setCreateBy(user.getUserId());
                                    videoLine.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
                                    videoLine.setDeleteFlag(0);
                                    msg = (Message)this.videoLineService.addVideoLine(videoLine);
                                    if( !msg.isResult()){
                                        sendJsonDataToClient(msg, response);
                                        return;
                                    }
                            }
                        }
                }
            }else{
                vehicleMaintain.setUpdateBy(user.getUserId());
                vehicleMaintain.setUpdateTime(DateUtil.format(new Date()));
                //当sim卡号没有修改时，进行修改
                msg = (Message)this.vehicleMaintainService.updateVehicleMaintain(vehicleMaintain);
              //对摄像头信息进行物理删除
                msg = (Message)this.videoLineService.deleteVideoLineByVehicelMaintainId(vehicleMaintain.getId());
                if( !msg.isResult()){
                    sendJsonDataToClient(msg, response);
                    return ;
                }
                //对摄像头信息进行新增
                String isFixCamera = vehicleMaintain.getIsFixcamera() == null ?"":vehicleMaintain.getIsFixcamera();
                if( isFixCamera.equals("1")){
                    String installLineArr =  request.getParameter("installLine");
                        String [] installLine = installLineArr .split(",");
                        String isInstallArr = request.getParameter("isInstall");
                        String [] isInstall = isInstallArr .split(",");
                        String installLocationArr = request.getParameter("installLocation");
                        String[] installLocation = this.stringToArr(installLocationArr);
                        //String[] installLocation = installLocationArr.split(",");
                        String installStatusArr = request.getParameter("installStatus");
                        String [] installStatus = installStatusArr .split(",");
                        String remarkArr = request.getParameter("remark");
                        String [] remark = this.stringToArr(remarkArr);
                        //String [] remark = remarkArr.split(",");
                        if(installLocation.length>remark.length){
                        	int len = installLocation.length-remark.length;
                        	int length = remark.length;
                        	for(int i=0;i<len;i++){
                        		remark[length]="";
                        		length++;
                        	}
                        }
                        
                        for( int i = 0;i<4;i++){
                            String isInstallNo =isInstall[i];
                            if( isInstallNo.equals("0")){
                                VideoLine videoLine = new VideoLine();
                                    videoLine.setVehicleMaintainId(vehicleMaintain.getId());
                                    videoLine.setInstallLine(installLine[i]);
                                    videoLine.setIsInstall(1);
                                    videoLine.setInstallLocation(installLocation[i]);
                                    videoLine.setInstallStatus(installStatus[i]);
                                    videoLine.setRemark(remark[i]);
                                    videoLine.setCreateBy(user.getUserId());
                                    videoLine.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
                                    videoLine.setDeleteFlag(0);
                                    msg = (Message)this.videoLineService.addVideoLine(videoLine);
                                    if( !msg.isResult()){
                                        sendJsonDataToClient(msg, response);
                                        return;
                                    }
                            }
                        }
                }
            }
            sendJsonDataToClient(new Message(true,"车辆安装维护修改成功"), response);
        }
        
        /**
         * 转发到车辆安装维护新增
         * @return
         */
        @RequestMapping(value="queryVehicleMaintain.do",method=RequestMethod.GET)
        public void queryVehicleMaint(HttpServletRequest request,HttpServletResponse response,String id){
            VehicleMaintain vehicleMaintain = this.vehicleMaintainService.selectById(id);
            sendJsonDataToClient(vehicleMaintain, response);
        }
	
	/**
	 * 分页查询车辆安装维护
	 * @param request
	 * @param response
	 * @param vehicleMaintainVo
	 */
	@RequestMapping(value="vehicleMaintainQuery.do")
	public void pageQuery(HttpServletRequest request,HttpServletResponse response,VehicleMaintainVo vehicleMaintainVo){
		Page page = vehicleMaintainService.pageQuery(vehicleMaintainVo);
		this.setTotal(page.getTotal());
		sendJsonDataToClient(page, response);
	}
	/**
	 * 根据id 删除车辆安装维护信息
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping(value="vehicleMaintainDelete.do")
	public void deleteById(HttpServletRequest request,HttpServletResponse response,String ids){
		
		Message msg = (Message) vehicleMaintainService.deleteById(ids);
		if (null != msg && msg.isResult()) {
			JCDFWebUtil.Log(request, "删除车辆安装维护信息：ID为："+ids);
		}
		sendJsonDataToClient(msg, response);
	}
	
	/**
	 *加载公司名称、运营属性、车辆分类三级下拉框数据
	 * @param request
	 * @param response
	 * @param vo
	 */
	@RequestMapping(value="getCompanyInfo.do")
	public void getCompanyInfo(HttpServletRequest request,HttpServletResponse response,VehicleCategoryVo vo){
		
		vo.setModelType("company");
		List<Map<String, Object>> list = vehicleCategoryService.getVeicleInfo(vo);
		sendJsonDataToClient(JSONArray.fromObject(list), response);
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="vehicleMaintainExport.do")
	public void exportVehicleMaintain(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		String param = request.getParameter("param");
		JSONObject jsonObject = JSONObject.fromObject(param);
		
		VehicleMaintainVo vehicleMaintainVo = (VehicleMaintainVo) JSONObject.toBean(jsonObject, VehicleMaintainVo.class);
		String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
		// 获取输出文件的名字
		String displayName = PropertiesManager.getProperty("export.vehicleMaintain.fileName")+date+".xls";
		response.setContentType("application/vnd.ms-excel");
		BufferedOutputStream out = null;
		// 进行转码，使其支持中文文件名
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(displayName.getBytes("UTF-8"), "iso-8859-1"));
		try {
			// 获取输出流
			out = new BufferedOutputStream(response.getOutputStream());
		} catch (FileNotFoundException e1) {
			log.error(e1);
		}
		// 获取表格的表头字段名称
		String gridHead = PropertiesManager.getProperty("export.vehicleMaintain.columnNames");
		// 获取字段变量的名字
		String fieldNames = PropertiesManager.getProperty("export.vehicleMaintain.fieldNames");
		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		// 生成工作薄sheet(包含头信息)
		HSSFSheet mainSheet = this.generateSheet(wb, displayName, gridHead);
		// 设置数据样式
		HSSFCellStyle normalDataStyle = wb.createCellStyle();
		normalDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 用户查询出来的总记录
		long total = this.getTotal();
		// 计算需要分批查询的次数
		int eachQuerySize = Integer.parseInt(PropertiesManager.getProperty("export.excel.book.maxSheet"));
		int no = (int) total / eachQuerySize + 1;
		vehicleMaintainVo.setPageSize((int) total);
		// 追加数据时,第几行
		int rowNum = 0;
		// 一行时,第几个单元格
		int num = 0;
		// 当前记录的数目
		int currentRecordNum = 0;
		int sheetNameIndex = 2;
		int maxRow = Integer.parseInt(PropertiesManager.getProperty("export.excel.sheet.maxRow"));
		for(int n=1;n<=no;n++){
			vehicleMaintainVo.setPage(n);
			Page page = vehicleMaintainService.pageQuery(vehicleMaintainVo);
			List<VehicleMaintain>list = (List<VehicleMaintain>) page.getRows();
			for(VehicleMaintain vm:list){
				vm.setDeleteFlag(vm.getDeleteFlag().equals("0")?"未删除":"已删除");
				if (currentRecordNum > maxRow) {
					mainSheet = this.generateSheet(wb, displayName
							+ sheetNameIndex, gridHead);
					sheetNameIndex++;
					currentRecordNum = 0;
					rowNum = 0;
				}
				currentRecordNum++;
				num = 0;
				HSSFRow row = mainSheet.getRow(1 + rowNum);
				if (row == null) {
					row = mainSheet.createRow(1 + rowNum);
				}
				// Insert data
				for (String field : fieldNames.split(",")) {
					HSSFCell cell = row.getCell(num);
					if (cell == null) {
						cell = row.createCell(num);
					}
					cell.setCellStyle(normalDataStyle);
					Field f = VehicleMaintain.class.getDeclaredField(field);
					f.setAccessible(true);
					Object value = f.get(vm);

					this.setCellValue(cell, value);
					num++;
				}
				rowNum++;
			}
		}
		
		try {
			wb.write(out);
			out.flush();
		} catch (Exception e) {
			log.error(e);
		}finally{
			try {
				out.close();
				JCDFWebUtil.Log(request, "导出车辆安装维护,参数为:"+param);
			} catch (IOException e) {
				log.error(e);
			}
		}
	}
	
	/**
	 * 获取sheet
	 * 
	 * @param wb
	 * @param sheetName 表格工作薄的名字
	 * @param gridHead 表格的表头字段名称
	 * @return
	 */
	private HSSFSheet generateSheet(HSSFWorkbook wb, String sheetName,
			String gridHead) {
		// 判断表头信息是否为空
		if (null == gridHead) {
			throw new RuntimeException("表头信息为空,无法导出!");
		}
		HSSFSheet mainSheet = wb.createSheet(sheetName);
		// 设置标题sheet信息的样式
		HSSFFont headFont = wb.createFont();
		headFont.setFontHeightInPoints((short) 20);
		headFont.setColor(HSSFFont.COLOR_RED);
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);

		// 设置头信息的样式
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 8);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setColor(HSSFFont.COLOR_NORMAL);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(titleFont);

		String[] gridList = gridHead.split(",");
		// 创建第一行
		HSSFRow headRow = mainSheet.createRow(0);

		// 在第一行中添加头信息数据
		int num = 0;
		for (int i = 0; i < gridList.length; i++) {
			String column = gridList[i];
			// 创建单元格
			HSSFCell headCell = headRow.createCell(num);
			HSSFRichTextString h = new HSSFRichTextString(column);
			headCell.setCellValue(h);
			headCell.setCellStyle(headerStyle);
			int columnWidth = 100;
			mainSheet.setColumnWidth(num, columnWidth * 30);
			num++;
		}
		return mainSheet;
	}
	
	
	private void setCellValue(HSSFCell cell, Object value) {
		if (value instanceof java.lang.String) {
			cell.setCellValue(new HSSFRichTextString(value.toString()));
		} else if (value instanceof java.lang.Number) {
			cell.setCellValue(Double.parseDouble(value.toString()));
		} else if (value instanceof java.lang.Boolean) {
			cell.setCellValue(Boolean.parseBoolean(value.toString()));
		} else if (value instanceof java.util.Date) {
			cell.setCellValue(DateUtil.format((Date) value,DateUtil.YYYY_MM_DD));
		} else {
			cell.setCellValue("");
		}
	}

}
