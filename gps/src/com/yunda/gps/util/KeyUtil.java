package com.yunda.gps.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 主键生成工具类
 * @author luogengxian
 *
 */
public class KeyUtil {
	private static int sequenceNumber = 0;
	
	/**
	 *得到当前年月日时分秒毫秒数+5位随机数
	 * @return String
	 * @author luogengxian
	 * @since v1.0_2015-8-02
	 */
	public synchronized static String getIdByTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String curYearMonDayMil = dateFormat.format(new Date());
		if ((sequenceNumber++) > 99999) {
			sequenceNumber -= 100000;
		}
		return curYearMonDayMil+""+Integer.toString(100000+(sequenceNumber% 100000)).substring(1);
	}
	/**
	 * 得到一个0-10?之间的随机数
	 * @return String
	 * @author luogengxian
	 * @since v1.0_2015-8-02
	 */
	public synchronized static String getIdByTime(int n){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String curYearMonDayMil = sdf.format(new Date());
		String rs = "";
		switch (n) {
		case 1:	break;
		case 2: break;
		case 3: 
			if ((sequenceNumber++) > 999) {
				sequenceNumber -= 1000;
			}
			rs = curYearMonDayMil+""+Integer.toString(1000+(sequenceNumber% 1000)).substring(1);
			break;
		case 4:
			if ((sequenceNumber++) > 9999) {
				sequenceNumber -= 10000;
			}
			rs = curYearMonDayMil+""+Integer.toString(10000+(sequenceNumber% 10000)).substring(1);
			break;
		case 5: 
			if ((sequenceNumber++) > 99999) {
				sequenceNumber -= 100000;
			}
			rs = curYearMonDayMil+""+Integer.toString(100000+(sequenceNumber% 100000)).substring(1);
			break;
		default:
			break;
		}
		return rs;
	}
	
}
