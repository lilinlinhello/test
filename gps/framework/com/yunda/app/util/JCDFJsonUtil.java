package com.yunda.app.util;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author jxq
 * @date 2013-10-10
 *
 */
public final class JCDFJsonUtil {
	 /**
		 * 将对象格式化成Json字符串并返回
		 * 
		 * @param obj	需要格式化的对象
		 * @param dateFmt	格式化日期字段时采用的“模式”
		 * 
		 * @return	如果obj为空，则返回空的json对象，即“{}”，如果是字符串则返回字符串本身，
		 * 			如果是数组或者集合类对象，则采用JSONArray对象格式化成json中的数组对象，如果不满足以上情况，则默认全部采用JSONObject格式化成json普通对象
		 */
		public static String fromObjctToJson(Object obj, String dateFmt) {
			if(null == dateFmt || "".equals(dateFmt.trim()))dateFmt = "yyyy-MM-dd";
			JsonConfig config = new JsonConfig();  
			JCDFDateUtil processor = new JCDFDateUtil(dateFmt);
	        config.registerJsonValueProcessor(java.util.Date.class, processor);
	        config.registerJsonValueProcessor(java.sql.Date.class, processor);
			String json = null;
			if (null == obj) {
				json = "{}";
			} else if (obj  instanceof String) {
				json = obj.toString();
			} else if (obj.getClass().isArray() || obj instanceof Collection) {
				json=JSONArray.fromObject(obj,config).toString();
			} else {
				json=JSONObject.fromObject(obj,config).toString();
			}
			return json.replace("null", "\"\"");
		}
		
		/**
		 * 将对象格式化成Json字符串并返回
		 * 格式化日期字段值时默认采用模式：yyyy-MM-dd，如果需要其他模式，
		 * 请采用重载方法：fromObjctToJson(Object obj, String dateFmt)以便自行指定模式
		 * 
		 * @param obj	需要格式化的对象
		 * 
		 * @return 
		 */
		public static String fromObjctToJson(Object obj) {
			return fromObjctToJson(obj, null);
		}
		
}
