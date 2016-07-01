package com.yunda.gps.smsTemplate.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.yunda.app.entity.vo.Message;
import com.yunda.gps.smsTemplate.dao.SMSTemplateDao;
import com.yunda.gps.smsTemplate.dao.SmsRecordDao;
import com.yunda.gps.smsTemplate.dao.SmsSendDao;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;
import com.yunda.gps.smsTemplate.service.SmsSendService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.InterConfig;
import com.yunda.gps.util.PostSms;
import com.yunda.gps.vehicleMaintain.mapper.VehicleMaintainMapper;
import com.yunda.gps.vehicleMaintain.pojo.VehicleMaintain;

@Service(value="smsSendService")
public class SmsSendServiceImpl implements SmsSendService {
	

	@Resource(name="smsSendDao")
	private SmsSendDao smsSendDao;
	
	@Resource(name="smsTemplateDao")
	private SMSTemplateDao smsTemplateDao;
	
	@Resource(name="smsRecordDao")
	private SmsRecordDao smsRecordDao;
	
	@Autowired
	private VehicleMaintainMapper vehicleMaintainMapper;

	@SuppressWarnings("deprecation")
	@Override
	public void autoSmsTime() {
		String isSendSms = InterConfig.getProperties().getProperty("isSendSms");
		synchronized ("Object") {
			if("yes".equals(isSendSms)){//发送短信
				Integer maxSendNo = smsSendDao.maxSendNo();
				if(maxSendNo!=0){
					//用于计时，要求5秒后开始发送短信，短信之间间隔2秒
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date dateTiming = new Date();
					dateTiming.setSeconds(dateTiming.getSeconds()+3);
					for(int i=1;i<=maxSendNo;i++){
						//根据sendNo查找短信
						List<Map<String, Object>> list = smsSendDao.queryList(String.valueOf(i));
						for (Map<String, Object> map : list) {
							try {
								String timingFormat = sdf.format(dateTiming);
								dateTiming.setSeconds(dateTiming.getSeconds()+3);
								System.out.println("----------------"+timingFormat);
								map.put("timing", timingFormat);
								System.out.println("================"+map.get("timing"));
								String sim = (String)map.get("SIM_PHONENO");
								int SEND_STATUS=0;
								if(sim.length()==11){//0  普通短信发送
									SEND_STATUS = PostSms.postMessage(smsToXml(map),0);
								}
								if(sim.length()==13){//1  物联网卡短信发送
									SEND_STATUS = PostSms.postMessage(smsNetToXml(map),1);
								}
								map.put("CREATE_TIME",  DateUtil.getDateYMDHMs(new Date()));
								map.put("SEND_STATUS", SEND_STATUS);
								map.put("SEND_COUNT", 1);
								map.put("UPDATE_TIME", DateUtil.getDateYMDHMs(new Date()));
								smsSendDao.deleteSmsSend(map);
								smsRecordDao.insertSmsRecord(map);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
					}
				}
			}
		}
		
	}

	/**
	 * 物联网卡短信xml  13位
	 * @param m
	 * @return
	 */
	private String smsNetToXml(Map<String, Object> m) {
		StringBuffer smsXmlBuffer = new StringBuffer();
		smsXmlBuffer.append("<req op='sms_01send'>");
		smsXmlBuffer.append("<h><ver>1.0</ver></h>");
		smsXmlBuffer.append("<items>");
		smsXmlBuffer.append("<item>");
		smsXmlBuffer.append("<id>"+m.get("SMS_ID")+"</id>");
		smsXmlBuffer.append("<content>"+m.get("SEND_CONTENT")+"</content>");
		smsXmlBuffer.append("<tele>" + m.get("SIM_PHONENO")+ "</tele>");
		smsXmlBuffer.append("<timing>" + m.get("timing")+ "</timing>");
		smsXmlBuffer.append("</item>");
	
		
		smsXmlBuffer.append("</items>");
		smsXmlBuffer.append("</req>");
		return smsXmlBuffer.toString();
	}
	/**
	 * 普通短信xml  11位
	 * @param m
	 * @return
	 */
	private String smsToXml(Map<String, Object> m) {
		StringBuffer smsXmlBuffer = new StringBuffer();
		smsXmlBuffer.append("<req op='sms_01send'>");
		smsXmlBuffer.append("<h>");
		smsXmlBuffer.append("<ver>1.0</ver>");
		smsXmlBuffer.append("<user>cbs2_mws</user>");
		smsXmlBuffer.append("<pass>Tj1TgpARSwBy3IKl</pass>");
		smsXmlBuffer.append("</h>");
		smsXmlBuffer.append("<items>");
		
		
		smsXmlBuffer.append("<item>");
		smsXmlBuffer.append("<id>"+m.get("SMS_ID")+"</id>");
		smsXmlBuffer.append("<content>"+m.get("SEND_CONTENT")+"</content>");
		smsXmlBuffer.append("<tele>" + m.get("SIM_PHONENO")
				+ "</tele>");
		smsXmlBuffer.append("<timing>" + m.get("timing")+ "</timing>");
		smsXmlBuffer.append("</item>");
		
		
		smsXmlBuffer.append("</items>");
		smsXmlBuffer.append("</req>");
		return smsXmlBuffer.toString();
	}
	
	
	


	
	@Override
	public Object insertSmsSend(Map<String, Object> map) {
		String id = (String) map.get("SMS_ID");
		String smsTemplateId = (String)map.get("smsTemplateId");
		String isOwn = (String) map.get("isOwn");
		String[] ids = id.split(",");
			for(int i=0;i<ids.length;i++){//遍历需要发送的每一辆车
				VehicleMaintain vm = vehicleMaintainMapper.selectById(ids[i]);
				String licensecard = vm.getLicensecard();//车牌号
				String vehicleId = vm.getVehicleId();//车辆编码
				String simPhoneno = vm.getSimPhoneno();
				map.put("SIM_PHONENO", simPhoneno);//存放手机号
				if(isOwn.equals("0")){//自定义
					//查询短信内容中特殊符号:{V}(车牌号)、{VID}(车牌编码)、{S}(手机号)
					if(smsTemplateId.contains("{V}")){
						smsTemplateId = smsTemplateId.replace("{V}", licensecard);
					}
					if(smsTemplateId.contains("{VID}")){
						smsTemplateId = smsTemplateId.replace("{VID}", vehicleId);
					}
					if(smsTemplateId.contains("{S}")){
						smsTemplateId = smsTemplateId.replace("{S}", simPhoneno);
					}
					map.put("SEND_CONTENT", smsTemplateId);//存放短信模板
					map.put("SEND_NO", 1);//存放序号
					//sms_id的生成   yyMMddHHmmss+6位随机数
					String sms_id = getIdTemplate();
					map.put("SMS_ID", sms_id);
					smsSendDao.insertSmsSend(map);
				}else{
					String[] smsTempIds = smsTemplateId.split(",");
					//每辆车都需要存放所有的短信模板
					for(int j=0;j<smsTempIds.length;j++){
						SMSTemplateVo smsTemplateVo = smsTemplateDao.get(smsTempIds[j]);
						String smsContent = smsTemplateVo.getSmsContent();
						Integer smsOrder = smsTemplateVo.getSmsOrder();
						//查询短信内容中特殊符号:{V}(车牌号)、{VID}(车牌编码)、{S}(手机号)
						if(smsContent.contains("{V}")){
							smsContent = smsContent.replace("{V}", licensecard);
						}
						if(smsContent.contains("{VID}")){
							smsContent = smsContent.replace("{VID}", vehicleId);
						}
						if(smsContent.contains("{S}")){
							smsContent = smsContent.replace("{S}", simPhoneno);
						}
						map.put("SEND_CONTENT", smsContent);//存放短信模板
						map.put("SEND_NO", smsOrder);//存放序号
						//sms_id的生成   yyMMddHHmmss+6位随机数
						String sms_id = getIdTemplate();
						map.put("SMS_ID", sms_id);
						smsSendDao.insertSmsSend(map);
					}
				}
			}
		
		
		return new Message(true,"发送成功");
	}
	
	
	/**
	 * id的生成   yyMMddHHmmss+6位随机数
	 * @return
	 */
	private String getIdTemplate(){
		int rand = (int)((new Random().nextDouble()*900000)+100000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String dateString = sdf.format(new Date());
		String id = dateString+rand;
		return id;
	}

	/**
	 * 短信回复
	 */
	@Override
	public Object smsSendResponse(String xml) {
		//创建新的字符串
		StringReader reader = new StringReader(xml);
		//创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(reader);
		//创建一个新的SAXBuilder
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			//通过输入源构造一个Document
			Document document = saxBuilder.build(source);
			//获取根节点
			Element root = document.getRootElement();
			//获取根节点中的子元素
			Element child = root.getChild("item");
			Element childTele = child.getChild("tele");
			Element childContent = child.getChild("content");
			Element childRcvtime = child.getChild("rcvtime");
			String textContent = childContent.getText();
			//textContent = new String(textContent.getBytes("iso-8859-1"), "utf-8");
			String textRcvtime = childRcvtime.getText();
			String textTel = childTele.getText();
			Map<String,Object> map = new HashMap<String,Object>();
			//sms_id的生成   yyMMddHHmmss+6位随机数
			String sms_id = getIdTemplate();
			map.put("SMS_ID", sms_id);
			map.put("SIM_PHONENO", textTel);
			map.put("SEND_CONTENT", textContent);
			map.put("SMS_TYPE", 2);
			map.put("SEND_STATUS", 1);
			map.put("SEND_COUNT", 1);
			map.put("SEND_COUNT", 1);
			map.put("CREATE_TIME", DateUtil.getDateYMDHMs(new Date()));
			map.put("CREATE_BY","gpsadmin");
			map.put("UPDATE_TIME", textRcvtime);
			smsRecordDao.insertSmsRecord(map);
			return new Message(true);
		} catch (JDOMException e) {
			//e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e);
		}
		return new Message(false);
	}


}
