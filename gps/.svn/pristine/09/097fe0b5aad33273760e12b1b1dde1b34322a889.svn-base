package com.yunda.gps.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class QueryFreight {

	static Log log = LogFactory.getLog(QueryFreight.class);
	
	@SuppressWarnings({ "deprecation" })
	public static String queryFreight(String startCity,String endCity,String weight){
		DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
        StringBuilder url = new StringBuilder();
        //url拼接
        url.append(PropertiesManager.getProperty("freight.url")).append("?").append("partnerid=").append(PropertiesManager.getProperty("freight.partnerid"))
        .append("&format=").append(PropertiesManager.getProperty("freight.format")).append("&xmldata=%3Corder%3E%3Cstart_city%3E").append(startCity).append("%3C/start_city%3E%3Cend_city%3E").append(endCity)
        .append("%3C/end_city%3E%3Cweight%3E").append(weight).append("%3C/weight%3E%3Ctype%3E1%3C/type%3E%3C/order%3E");
        String baseUrl = url.toString();
        log.fatal("create httppost:" + baseUrl);  
        HttpGet get = new HttpGet(baseUrl);  
        body = invoke(httpclient, get);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;
	}
	
	 @SuppressWarnings("deprecation")
	private static String invoke(DefaultHttpClient httpclient,  
	            HttpUriRequest httpost) {  
	          
	        HttpResponse response = sendRequest(httpclient, httpost);  
	        String body = paseResponse(response);  
	          
	        return body;  
	}
	 
	 @SuppressWarnings("deprecation")
	private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
	            HttpUriRequest httpost) {  
	        log.fatal("execute post...");  
	        HttpResponse response = null;  
	          
	        try {  
	            response = httpclient.execute(httpost);  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return response;  
	 }
	 
	 @SuppressWarnings("deprecation")
	private static String paseResponse(HttpResponse response) {  
	        log.fatal("get response from http server..");  
	        HttpEntity entity = response.getEntity();  
	          
	        log.fatal("response status: " + response.getStatusLine());  
	        String charset = EntityUtils.getContentCharSet(entity);  
	        log.fatal(charset);  
	          
	        String body = null;  
	        try {  
	            body = EntityUtils.toString(entity);  
	            log.fatal(body);  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	          
	        return body;  
	    }
}
