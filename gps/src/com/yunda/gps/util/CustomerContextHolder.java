package com.yunda.gps.util;

public class CustomerContextHolder {
	public static final String DATA_SOURCE_A = "dataSource";
	public static final String DATA_SOURCE_B = "gpsdataSource";
	public static final String DATA_SOURCE_SERVICE = "gpsServiceSource";
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
