package com.yunda.gps.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 系统各种配置参数获取类
 * 调用方式：InterConfig.getProperties().getProperty("paramName");
 * 
 */
public class InterConfig {

	/**
	 * 日志记录器
	 */
	private static Logger log = Logger.getLogger(InterConfig.class);
	/**
	 * 系统各种配置参数配置文件（Properties）
	 */
	private static Properties config;
	
	/**
	 * 单例模式加载配置文件
	 * 
	 * @return
	 */
	public static Properties getProperties(){
		try {
			if(null == config){
				config = new Properties();
				InputStream inputStream=Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("properties/gps.properties");
				config.load(inputStream);
				
				log.info("参数配置文件[gps.properties]加载成功.......");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
	
}
