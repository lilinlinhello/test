package com.yunda.gps.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;

public class ToolUtil {

	public static boolean isNull(Object obj){
		if ((obj!=null) && (!"".equals(obj)))
			return false;
		else 
			return true;
	}
	
	/**
	 * 判断是否为非空
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj){
		if ((obj!=null) && (!"".equals(obj.toString())))
			return true;
		else 
			return false;
	}
	
	/**
	 * list的每一值为一个String数组，取得一个数组的第一个元素
	 * @param list
	 * @return
	 */
	public static String getData(List list){
		if ((list==null) || (list.size()==0)) {
			return "";
		}else{
			return ((String[])list.get(0))[0];
		}
	}
	
	/**
	 * list的每一值为一个String数组，取得索引位置为rowIndex的数组
	 * @param list
	 * @return
	 */
	public static String[] getData(List list,int rowIndex){
		if ((list!=null) && (list.size()>0) && (list.size()>rowIndex)) {
			return (String[])list.get(rowIndex);
		} else 
			return null;
	}
	
	/**
	 * list的每一值为一个String数组，取得取得索引位置为rowIndex的数组的colIndex位置的元素
	 * @param list
	 * @return
	 */
	public static String getData(List list,int rowIndex,int colIndex){
		if ((list!=null) && (list.size()>0) && (list.size()>rowIndex)) {
			String[] row = (String[])list.get(rowIndex);
			if (row.length>colIndex)
				return row[colIndex];
			else 
				return null;
		} else 
			return null;
	}
	
	public static String md5Encode(byte[] b) { 
	    String resultString = null;
	    try {
	    	MessageDigest md = MessageDigest.getInstance("MD5"); 
	    	resultString = byteArrayToHexString(md.digest(b)); 
	    }
	    catch (Exception ex) { 
	    	ex.printStackTrace();
	    	resultString = null;
	    }
	    
	    return resultString; 
	} 
	
	public static String md5Encode(String fileName) {
		return md5Encode(new File(fileName));
	} 
	
	public static String md5Encode(File file) {
		if (file.exists() && file.isFile()) {
			FileInputStream in = null;
			try {
				byte[] b = new byte[1024];
				MessageDigest md = MessageDigest.getInstance("MD5"); 
				in = new FileInputStream(file);
				while ((in.read(b)) != -1) {
					md.update(b);
				}
				return ToolUtil.byteArrayToHexString(md.digest());
			} catch (Exception e) {
				return "";
			} finally {
				try {
					in.close();
					in = null;
				} catch (Exception e) {
				}
			}
		}
		return "";
	} 
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/** 
	 * 转换字节数组为16进制字串 
	 * @param b 字节数组 
	 * @return 16进制字串 
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	
	/**
	 * @param imgPath 图片上传物理路径 如：c://love_day_1_01.jpg
	 * @return String 为width,height形式的字符串如："300*200",如果有异常放回","需要前台页面补填
	 * 
	 * */
	public static String getExIfImgInfo(String imgPath){
		StringBuilder returnStr=new StringBuilder();
		try {
			File jpegFile = new File(imgPath);
			ExifReader er = new ExifReader(jpegFile);
			Metadata metadata =er.extract();
			//Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			Iterator directories = metadata.getDirectoryIterator();
			while (directories.hasNext()) {
				Directory directory = (Directory) directories.next();
				Iterator tags = directory.getTagIterator();
				String width="";
				String height="";
				while (tags.hasNext()) {
					Tag tag = (Tag) tags.next();
					if((tag.toString()).indexOf("Image Width")!=-1){
			        	 width=(tag.toString()).substring((tag.toString()).indexOf('-')+1, (tag.toString()).length());
			         }
					if((tag.toString()).indexOf("Image Height")!=-1){
						 height=(tag.toString()).substring((tag.toString()).indexOf('-')+1, (tag.toString()).length());
			        }
					if(!("".equals(width)||"".equals(height))){
						returnStr.append(width+"*"+height);
						return returnStr.toString();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnStr.append(",");
		}
		return returnStr.toString();
	}
	

}
