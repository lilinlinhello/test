package com.yunda.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 * 日期工具类
 * 
 * @author jxq
 * @date 2013-10-10
 *
 */
public final class JCDFDateUtil  implements JsonValueProcessor{

	/**日期格式化模式*/
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public JCDFDateUtil(){
		
	}
	
	public JCDFDateUtil(String dateFormat){
		this.dateFormat = dateFormat;
	}
	
	/**
	 * 获取明天的日期
	 * 
	 * @return
	 */
	public static Calendar tomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE,1);
		return calendar;
	}
	
	  /**  
     * 计算两个日期之间相差的天数  
     * 
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) {    
        long between_days = 0;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			smdate=sdf.parse(sdf.format(smdate));  
			bdate=sdf.parse(sdf.format(bdate));  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			between_days = (time2-time1)/(1000*3600*24);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return Integer.parseInt(String.valueOf(between_days));           
    }
    
    
    
    
   	///////////////////JsonValueProcessor 专用begin///////////////////////
   	 public Object processArrayValue(Object value, JsonConfig jsonConfig) {
   	        return null;
   	    }

   	    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
   	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
   	        if (null != value) {
   	        	if (value instanceof java.util.Date) {
   	        		return sdf.format(value);
   	            }else {
   	            	return value.toString();
   	            }
   	        }else{
   	        	return "";
   	        }
   	    }

   	    public String getDateFormat() {
   	        return dateFormat;
   	    }

   	    public void setDateFormat(String dateFormat) {
   	        this.dateFormat = dateFormat;
   	    }
   	    
   	    
   	    public static String formatDate(java.util.Date date){
   	    	String result = "";
   	    	result = new SimpleDateFormat(dateFormat).format(date);
   	    	return result;
   	    }
   	    
   	    public static void printlnSysTime(){
   	    }
   	 ////////////////////JsonValueProcessor 专用end////////////////////////
}
