package com.yunda.gps.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 实现常用类型转换的公用工具类。
 * 
 * @author nfs on 2007-07-23
 *
 */
public class Transition 
{
	private Transition()
	{	
	}
	
	/**
	 * 功能：将字符型数字数组转换为整型数组。
	 * 
	 * @param strIds
	 * @return int[]
	 */
	public static int[] convertStrArrToIntArr(String[] strIds)
	{
		int[] intIds = new int[strIds.length];
		for (int i=0; i<strIds.length; i++)
			intIds[i] = Integer.parseInt(strIds[i]);
		return intIds;
	}
	
	/**
	 * 功能：转换MapList为数组List
	 * @param list
	 * @return
	 */
	public static List convertMapListToArrayList(List list)
	{
		List content = new ArrayList();
		String[] row = null;
		Map map = null;
		
		if (list!=null)
		{
			Iterator iterator = list.iterator();
			while (iterator.hasNext())
			{
				int i = 0;
				map = (Map)iterator.next();
				row = new String[map.size()];
				Iterator keyIt = map.keySet().iterator();
				while (keyIt.hasNext())
				{
					String key = (String)keyIt.next();
					row[i++] = ((Object)map.get(key))==null?"":((Object)map.get(key)).toString();
				}
				
				content.add(row);
			}
		}
		
		return content;
	}
	/**
	 * @param m Map形式的数据集
	 * @param o 输出成相应对象,此方法只限于给公有属性赋值
	 * @return 将转换之后的对象输出
	 * */
	public static Object convertMapToObject(Map m,Object o) {
		if(o!=null){
			try {
				Class cla = o.getClass().getClassLoader().loadClass(
						o.getClass().getName());
				o = cla.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Field[] f=o.getClass().getDeclaredFields();
label1:		for(int i=0;i<f.length;i++){
			Iterator keyIt = m.keySet().iterator();
			while(keyIt.hasNext()){
				String key = (String)keyIt.next();
				if((f[i].getName().equalsIgnoreCase(key))||(f[i].getName().equalsIgnoreCase(key.replaceAll("_","")))){
					try {
						if(m.get(key)==null)
							continue label1;
						f[i].set(o, m.get(key).toString());
						continue label1;
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		
		return o;
	}
	
	
	public static String convertListToString(List l,String regex){
		StringBuffer s = new StringBuffer();
		for(int i = 0;i < l.size();i++){
			s.append(l.get(i).toString() + regex);
		}
		return s.toString().equals("") ? "" : s.substring(0,s.length() - regex.length());
	}
}
