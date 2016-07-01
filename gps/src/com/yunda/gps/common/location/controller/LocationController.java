package com.yunda.gps.common.location.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunda.app.base.BaseController;
import com.yunda.app.controller.LoginController;
import com.yunda.gps.common.location.service.LocationService;
import com.yunda.gps.util.StringUtil;

/**
 * 地点信息管理控制器 * 
 * @author luogengxian
 * @date	2015-07-29
 * 
 */
@Controller
@RequestMapping(value = "/address/location.do")
public class LocationController extends BaseController{
	/**地点信息业务类*/
	@Resource(name="locationService")
	private LocationService locationService ;
	Log log = LogFactory.getLog(LoginController.class);
	
	
	/**
	 * 加载地点信息
	 * @param response
	 * @return	void
	 */
	@RequestMapping(params="method=loadLocation")
	public void loadLocation(HttpServletResponse response,HttpServletRequest request){
		/**前台用户输入字符**/
		String inputValue = request.getParameter("q");
		/**查询类型：  1:查询省，2查询市，3查询县区**/
		String type = request.getParameter("type");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		String number = "[01234]";
		Pattern p = Pattern.compile(regEx);
		Pattern pNum = Pattern.compile(number);
		if(StringUtil.isNotNullStr(inputValue)){
			if(p.matcher(inputValue).find()){
				sendJsonDataToClient(JSONArray.fromObject(new ArrayList<Map<String,Object>>()).toString(), response);	
				return;
			}
		}
		if(StringUtil.isNotNullStr(type)){
			if(p.matcher(type).find() ||  !pNum.matcher(type).find()){
				sendJsonDataToClient(JSONArray.fromObject(new ArrayList<Map<String,Object>>()).toString(), response);	
				return;
			}
		}
		if(StringUtil.isNotNullStr(provinceId)){
			if(p.matcher(provinceId).find()){
				sendJsonDataToClient(JSONArray.fromObject(new ArrayList<Map<String,Object>>()).toString(), response);	
				return;
			}
		}
		if(StringUtil.isNotNullStr(cityId)){
			if(p.matcher(cityId).find()){
				sendJsonDataToClient(JSONArray.fromObject(new ArrayList<Map<String,Object>>()).toString(), response);	
				return;
			}
		}
		/**目前默认查询  中国 (CN)**/
		List<Map<String,Object>> list = locationService.loadLocation(inputValue,"CN",type,provinceId,cityId);			
		sendJsonDataToClient(JSONArray.fromObject(list).toString(), response);		
	}
	
	
}
