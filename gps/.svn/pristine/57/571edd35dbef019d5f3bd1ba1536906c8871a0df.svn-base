package com.yunda.gps.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostSms {
	
	private static String postSmsNetUrl = InterConfig.getProperties().getProperty("smsInterfaceNetUrl");
	
	private static String postSmsUrl = InterConfig.getProperties().getProperty("smsInterfaceUrl");
	public static int postMessage(String smsXml,int net) throws Exception {
		
			URL url = null;
			if(net==0){//普通短信发送
				url = new URL(postSmsUrl);
			}else{//物联网卡短信发送
				url = new URL(postSmsNetUrl);
			}
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10 ");
			conn.setRequestProperty("Content-type", "text/xml;charset=UTF-8");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
			bw.write(smsXml);
			bw.flush();
			bw.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String out="";
			String isNo="";
			while ((isNo = br.readLine()) != null) {
				out=out+isNo;
			}
			int responseCode = getResponseCode(out);
			br.close();
		
		return responseCode;
	}
	
	/**
	 * 获取短信的发送状态
	 * @param xml
	 * @return
	 */
	public static int getResponseCode(String xml){
		String[] split = xml.split("\"");
		return Integer.parseInt(split[1]);
	}

}
