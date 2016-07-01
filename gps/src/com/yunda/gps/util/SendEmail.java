package com.yunda.gps.util;
 
 
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
/**
 * 
 * @author Zhouhui
 */
public class SendEmail {
	
	static Log log = LogFactory.getLog(SendEmail.class);
    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", PropertiesManager.getProperty("email.host"));//设置服务器地址
        props.put("mail.store.protocol" ,PropertiesManager.getProperty("email.protocol") );//设置协议
        props.put("mail.smtp.port", PropertiesManager.getProperty("email.port"));//设置端口
        props.put("mail.smtp.auth" , true); //设置为不需要验证试一下
        log.fatal("---邮箱的会话属性---"+props);
        Authenticator authenticator = new Authenticator() {
 
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            	log.fatal("----邮箱发送者的地址----"+PropertiesManager.getProperty("email.from"));
                return new PasswordAuthentication(PropertiesManager.getProperty("email.from"), PropertiesManager.getProperty("email.pwd"));
            }
             
        };
        Session session = Session.getInstance(props, authenticator); 
        return session;
    }
     
    public static void send(String toEmail , String content) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
  
            //Set message attributes
            msg.setFrom(new InternetAddress(PropertiesManager.getProperty("email.from")));
            InternetAddress[] address = {new InternetAddress(toEmail,false)};
            log.fatal("-----邮件接收者邮箱----"+toEmail);
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            log.fatal("----邮箱的发送时间为----"+new Date());
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");
            //Send the message
            log.fatal("------发送邮箱信息,邮箱信息为："+content);
            msg.saveChanges(); //    implicit with send()
            //Send the message
            log.fatal("------发送邮箱信息,邮箱信息为："+content);
            Transport transport = session.getTransport(PropertiesManager.getProperty("email.protocol").toString().toLowerCase());
            transport.connect(PropertiesManager.getProperty("email.host"), PropertiesManager.getProperty("email.from"), PropertiesManager.getProperty("email.pwd"));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
          //  Transport.send(msg);
        }
        catch (MessagingException mex) {
        	log.fatal("----异常的信息----"+mex.getMessage());
            mex.printStackTrace();
        }
    }
    public static void send (String toEmail , String content ,String subject){
    	Session session = getSession();
    	log.fatal("["+session.getProperties().get("mail.smtp.host")+"]");
    	log.fatal("["+session.getProperties().get("mail.store.protocol")+"]");
    	log.fatal("["+session.getProperties().get("mail.smtp.port")+"]");
    	log.fatal("["+session.getProperties().get("mail.smtp.auth")+"]");
    	
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
  
            //Set message attributes
            log.fatal("----邮件发送者地址----"+PropertiesManager.getProperty("email.from"));
            msg.setFrom(new InternetAddress(PropertiesManager.getProperty("email.from")));
//            InternetAddress[] address = {new InternetAddress(toEmail)};
            log.fatal("-----邮件接收者邮箱----"+toEmail);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            log.fatal("----发送邮件的主题----"+subject);
            msg.setSubject(subject);
            log.fatal("----邮箱的发送时间为----"+new Date());
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");
            msg.saveChanges(); //    implicit with send()
            //Send the message
            log.fatal("------发送邮箱信息,邮箱信息为："+content);
            Transport transport = session.getTransport(PropertiesManager.getProperty("email.protocol").toString().toLowerCase());
            transport.connect(PropertiesManager.getProperty("email.host"), PropertiesManager.getProperty("email.from"), PropertiesManager.getProperty("email.pwd"));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
          //  Transport.send(msg);
        }
        catch (MessagingException mex) {
        	log.fatal("----异常的信息----"+mex.getMessage());
            mex.printStackTrace();
        }
    }
 
}
