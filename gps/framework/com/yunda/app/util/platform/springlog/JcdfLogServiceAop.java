package com.yunda.app.util.platform.springlog;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.yunda.app.dao.JcdfLogDao;
import com.yunda.app.entity.pojo.JcdfLog;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.util.StringUtil;

@Service("jcdfLogServiceAop") 
public class JcdfLogServiceAop implements ApplicationContextAware {

      private ApplicationContext applicationContext;
      
      /**用户数据库操作接口*/
  	  @Resource(name="jcdfLogDao")
  	  private JcdfLogDao jcdfLogDao;

      public void setApplicationContext (ApplicationContext applicationContext){
             this.applicationContext=applicationContext;
      }

      public void decideUser(int num,String loginName){
           if (num==0){ //用户第一次登入，发布事件
                 applicationContext.publishEvent(new JcdfLogEvent(this,loginName));
           }else{
                 ;//.......
           }
      }
      
      /**
       * 事件开始，记录日志操作
       * 默认，事件成功完成，才记录日志，这里不作额外操作
       * 留作接口待用
       * @param logEvent
       */
      public void beginLog(JcdfLogEvent logEvent){
    	  System.out.println(logEvent.getLoginName()+" begin log ...");
      }
      
      /**
       * 事件结束，记录日志操作
       * @param logEvent
       */
	public void commitLog(JcdfLogEvent logEvent) {
		JcdfLog log = new JcdfLog();
		log.setLogIp(logEvent.getLogIp());
		log.setModuleName(logEvent.getModuleName());
		log.setOperatoContent(logEvent.getOperatoContent());
		if (logEvent.getLoginName() != null) {
			String[] loginParam = logEvent.getLoginName().split(StaticVar.SEPARATOR);
			if (loginParam != null && loginParam.length >= 2) {
				log.setUserId(loginParam[0]);
				log.setUserName(loginParam[1]);
			} else
				log.setUserName(logEvent.getLoginName());
		}
		if(logEvent.getLogPara3()!=null && logEvent.getLogPara3().length()>200){
			log.setLogPara3(logEvent.getLogPara3().substring(0, 190));
		}else{
			log.setLogPara3(logEvent.getLogPara3());
		}
		if(StringUtil.isNotNullStr(logEvent.getLogPara4())&&logEvent.getLogPara4().length()>200){
			log.setLogPara4(logEvent.getLogPara4().substring(0, 190));
		}else{
			log.setLogPara4(logEvent.getLogPara4());
		}
		jcdfLogDao.insert(log);
		System.out.println("commit log ...");
      }
      
      /**
       * 记录异常信息
       * @param logEvent
       */
      public void logException(JcdfLogEvent logEvent){
		JcdfLog log = new JcdfLog();
		log.setLogIp(logEvent.getLogIp());
		log.setModuleName(logEvent.getModuleName());
		log.setOperatoContent(logEvent.getOperatoContent());
		log.setUserName(logEvent.getLoginName());
		if (logEvent.getLoginName() != null) {
			String[] loginParam = logEvent.getLoginName().split(
					StaticVar.SEPARATOR);
			if (loginParam != null && loginParam.length >= 2) {
				log.setUserId(loginParam[0]);
				log.setUserName(loginParam[1]);
			}else
				log.setUserName(logEvent.getLoginName());
		} 
		if(logEvent.getLogPara3()!=null &&logEvent.getLogPara3().length()>200){
			log.setLogPara3(logEvent.getLogPara3().substring(0, 190));
		}else{
			log.setLogPara3(logEvent.getLogPara3());
		}
		if(logEvent.getEx()!=null && logEvent.getEx().toString().length()>200){
			log.setLogPara4(logEvent.getEx().toString().substring(0, 190));
		}else{
			log.setLogPara4(logEvent.getEx().toString());
		}
		jcdfLogDao.insert(log);
		System.out.println("exception log ...");
  	  }

}
