package com.yunda.gps.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.yunda.gps.constant.Constant;

/**
 *用于发送简洁模式的短信(暂不支持定时发送)
 * @author luogengxian
 * @since V1.0_2014-5-16
 */
public class SendMessage {
	static Log log = LogFactory.getLog(SendMessage.class);

	/**
	 * 发送简洁模式的短信
	 * @param phone 要发送的电话号码
	 * @param message   短信内容
	 * @param sender   
	 * @return HashMap<String,String> 返回该信息发送至各个手机的反馈情况，key：手机号，value：状态(1 成功发送
		2 op属性缺失 请求操作类型缺少 ;3 op属性错误 请求操作类型错误 ,4 密码错误 用户名密码不匹配或者用户名不存在;
		5 短信发送内容为空 发送短信内容不能为空;6 短信平台发生异常 内部出现异常或者xml格式错误; 
		7 短信发送号码异常 号码不能为空，长度为11位 ;8 id不能为空,长度不能超过20位 Id在这个业务内唯一，统计发送状态 
		9 ver不能为空，必须为指定版本 目前固定为1.0 ;	10 缺少发送信息组 缺少item标签 
	 */
	public static HashMap<String,String> sendSimpleMessge(String[] phone, String message,String sender) throws Exception{
		 log.fatal("sendSimpleMessge方法调用开始，手机号码为："+phone[0]+",验证信息："+message+",sender="+sender);
		String msg = URLEncoder.encode(message,"UTF-8");//对发送内容进行UTF-8编码
		HashMap<String,String> infoMap = new HashMap<String,String>();
		if(message.length()>70){
			throw new Exception("短信内容太长,请分条发送!");
		}else if(phone.length==0){
			throw new Exception("没有需要发送的手机号码!");
		}else{
			String content = "";
			String result = "";
			if(phone.length<=Constant.SENDMESSAGE_COUNTER_SEND){
				content = buildContent(phone,msg);
				result =getConnect(content);//发送
				parseResult(result, infoMap);//组织反馈结果
			}else{
				/*分批发送*/
				int len = 0;
				for (int i=0;i<=phone.length/Constant.SENDMESSAGE_COUNTER_SEND;i++){
					len=(i==phone.length/Constant.SENDMESSAGE_COUNTER_SEND)?(phone.length%Constant.SENDMESSAGE_COUNTER_SEND):Constant.SENDMESSAGE_COUNTER_SEND;//copy的长度
					String sendPhone[] = new String[len];
					System.arraycopy(phone,Constant.SENDMESSAGE_COUNTER_SEND*i,sendPhone,0,len);
					content = buildContent(sendPhone,msg);
					result = getConnect(content);
					parseResult(result,infoMap);
				}
			}
		}
		if(phone !=null && phone.length>0){
			log.fatal("sendSimpleMessge调用结束=返回状态="+infoMap.get(phone[0]));	
		}		
		return infoMap;
	}
	/**
	 * 组织发送内容
	 * @param phone
	 * @param msg
	 * @return String
	 * @author luogengxian
	 * @since v1.0_2014-5-16
	 */
	private static String buildContent(String[] phone, String msg){
		log.fatal("buildContent()方法调用开始===msg-->"+msg);
		StringBuilder content = new StringBuilder();
		content.append("<req op=\"sms_01send\">")
					.append("<h>").append("<ver>1.0</ver>")
						.append("<user>").append(PropertiesManager.getProperty("message.userName")).append("</user>")
						.append("<pass>").append(PropertiesManager.getProperty("message.passWord")).append("</pass>")
				   .append("</h>")
			.append("<items>");
		for(int i=0;i<phone.length;i++){
			content.append("<item>")
					       .append("<id>").append(phone[i]).append("</id>")
					       .append("<content>").append(msg).append("</content>")
					       .append("<tele>").append(phone[i]).append("</tele>")
				   .append("</item>");
		}
		content.append("</items>").append("</req>");
		log.fatal("buildContent()方法调用结束，content="+content.toString());
		return content.toString();
	}
	/**
	 *解析返回的结果
	 * @param result
	 * @param infoMap
	 * @return  HashMap<String,String>
	 * @author luogengxian
	 * @since v1.0_2014-5-16
	 */
	private static HashMap<String,String> parseResult(String result,HashMap<String,String> infoMap){
		log.fatal("parseResult()方法调用开始====result=>"+result);
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(
					new InputSource(
							new ByteArrayInputStream(result.getBytes())));
			XPathFactory xFactory = XPathFactory.newInstance();
			XPath xpath = xFactory.newXPath();
			XPathExpression express = xpath.compile("response");
			NodeList  nodeList = (NodeList)express.evaluate(doc,XPathConstants.NODESET);	//all <response>		
			for(int i=0;i<nodeList.getLength();i++){
				Node node = nodeList.item(i); 
				NodeList subNodes = node.getChildNodes();  // all <id>
				for(int j=0;j<subNodes.getLength();j++){
					Node t = subNodes.item(j);//id
					infoMap.put(t.getTextContent(),t.getAttributes().item(0).getTextContent());
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			log.fatal(e.getMessage());
		} catch(IOException e){
			e.printStackTrace();
		} catch(SAXException e){
			e.printStackTrace();
			log.fatal(e.getMessage());
		} catch(XPathExpressionException e){
			e.printStackTrace();
			log.fatal(e.getMessage());
		}
		log.fatal("parseResult()方法调用结束====result=>"+result);
		return infoMap;
	}
	/**
	 * 连接短信接口，与发短信
	 * @param content
	 * @return String
	 * @author luogengxian
	 * @since v1.0_2014-5-16
	 */
	private static String getConnect(String content) {
		log.fatal("getConnect()调用开始===content="+content);
		String result = "";
		try {
			URL url = new URL(PropertiesManager.getProperty("message.baseUrl"));
			log.fatal("url="+url.toString());
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out1 = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out1.write(content);
			out1.flush();
			out1.close();
			// 获取返回数据
			InputStream in = connection.getInputStream();
			byte[] b = new byte[1024];
			in.read(b);
			String input = new String(b);
			log.fatal("input="+input);
			result = input.trim().replaceAll("\"","'").replaceAll("\n","");
		}
		catch (MalformedURLException e) {
			log.fatal(e.getMessage());
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.fatal(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			log.fatal(e.getMessage());
		}
		log.fatal("===getConnect调用结束====result->"+result);
		return result;
	}
}
