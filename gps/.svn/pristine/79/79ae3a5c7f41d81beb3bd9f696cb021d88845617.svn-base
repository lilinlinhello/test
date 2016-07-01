package com.yunda.gps.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/***
 * .properties文件操作工具类
 */
public class PropertiesManager {
	private static Log log = LogFactory.getLog(PropertiesManager.class);
	private static Properties properties = null;
	private static String[] fileNames = null ;
	/**
	 * 属性文件哈希表[key:properties对象, value：文件对象]
	 */
	//private static Map<Properties, File> propertiesMap = new HashMap<Properties, File>();
	
	static {
		fileNames = new String[]{"platform.properties","webservice.properties","export/export.properties"};
		if (properties == null) {
			properties = new Properties();
//			List<File> list = new ArrayList<File>();
//			String classPath1 = PropertiesManager.class.getResource("/").getPath();
//			log.fatal("classPath1="+classPath1);
//			String basePath = PropertiesManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//			log.fatal("basePath="+basePath);
//			String classPath = basePath.substring(0, basePath.indexOf("WEB-INF/classes")+16);
//			if(classPath.indexOf("%e9%83%ad%e7%ab%af%e7%ab%af") != -1){
//				classPath = classPath.replaceAll("%e9%83%ad%e7%ab%af%e7%ab%af","郭端端"); //本地是中文的注意乱码替换
//			}
//			if(classPath.indexOf("%20")!=-1){
//				classPath = classPath.replaceAll("%20", " ") ; //本地包含空格的注意替换
//			}
//			log.fatal("classPath="+classPath);
//			loadProperties(list,classPath) ;
			
			loadProperties(fileNames);
		}
	}
	
	/**
	 * <p>
	 * Discription:从文件加载属性信息
	 * </p>
	 * @param list
	 * @param path
	 * @author luogengxian
	 */
//	private static void loadProperties(List<File> list, String path) {
//		try {
//			JCDFFileUtil.findFile(path, list, "properties");
//			Collections.sort(list, new Comparator<File>() {
//				public int compare(File o1, File o2) {
//					if (o1.isDirectory() && o2.isFile())
//						return -1;
//					if (o1.isFile() && o2.isDirectory())
//						return 1;
//					return o1.getName().compareTo(o2.getName());
//				}
//			});
//			for (int i = 0; i < list.size(); i++) {
//				File file = list.get(i);
//				if(file != null){
//					log.fatal("file.name="+file.getName());
//				}
//				properties.load(new FileInputStream(file));
//				Properties currentProperties = new Properties();
//				currentProperties.load(new FileInputStream(file));
//				propertiesMap.put(currentProperties, file);
//			}
//		} catch (Exception e) {
//			log.error("load properties error", e);
//		}
//	}
	 private static void loadProperties(String[] fileNames){
		 InputStream in = null ;
		 try {
	      for(String name:fileNames){
		      in = PropertiesManager.class.getResourceAsStream("/"+name);
		      properties.load(in);
	      }
	    } catch (Exception e) {
	      log.error("load properties error", e);
	    }finally{
	    	if(null!=in){
	    		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	 }
	/**
	 * 获取属性
	 * @param key 键名
	 * @return
	 * @author luogengxian
	 */
	public static String getProperty(String key) {
		String ret = null;
		if (properties != null) {
			ret = properties.getProperty(key);
		}
		return ret;
	}
	public static Map<String, String> getPropertyByPrefix(String prefix) {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<?> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String next = iterator.next().toString();
			if (next.indexOf(prefix) == 0) {
				map.put(next, properties.getProperty(next.toString()));
			}
		}
		return map;
	}
	
}
