package com.yunda.gps.util;

import java.util.Date;
import com.yd.lbs.gps.tools.util.StatCode;

public class ValidateOnLineUtil {
	private static long _5Minute = 1000 * 60 * 5;
	private static long _1Hour = 1000 * 60 * 60 * 1;
	private static long _12Hour = 1000 * 60 * 60 * 12;
	private static long _24Hour = 1000 * 60 * 60 * 24;
	private static long _72Hour = 1000 * 60 * 60 * 72;
	 
	public static String getStatus(Date date) {
		long timelen = System.currentTimeMillis() - date.getTime();
		
		//正常
		if (validateOnLine(timelen))
			return String.valueOf(StatCode.NORMAL.getCode());
		//离线I
		if (validateNotOnLine1(timelen))
			return String.valueOf(StatCode.NOT_ONLINE1.getCode());
		//离线II
		if (validateNotOnLine2(timelen))
			return String.valueOf(StatCode.NOT_ONLINE2.getCode());
		//离线III
		if (validateNotOnLine3(timelen))
			return String.valueOf(StatCode.NOT_ONLINE3.getCode());
		//离线IV
		if (validateNotOnLine4(timelen))
			return String.valueOf(StatCode.NOT_ONLINE4.getCode());
		//离线V
		if (validateNotOnLine5(timelen))
			return String.valueOf(StatCode.NOT_ONLINE5.getCode());
        
		return String.valueOf(StatCode.NORMAL.getCode());
		
	}
	
	public static boolean validateOnLine(long timelen) {
		return timelen <= _5Minute ;
	}
	
	public static boolean validateNotOnLine1(long timelen) {
		return timelen > _5Minute && timelen <= _1Hour;
	}
	
	public static boolean validateNotOnLine2(long timelen) {
		return timelen > _1Hour && timelen <= _12Hour;
	}

	public static boolean validateNotOnLine3(long timelen) {
		return timelen > _12Hour && timelen <= _24Hour;
	}

	public static boolean validateNotOnLine4(long timelen) {
		return timelen > _24Hour && timelen <= _72Hour;
	}

	public static boolean validateNotOnLine5(long timelen) {
		return  timelen > _72Hour;
	}
}
