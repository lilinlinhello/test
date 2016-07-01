package com.yunda.app.util.platform.springlog;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.util.JCDFDateUtil;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.StaticVar;

import org.apache.commons.logging.Log;


@Component(value="jcdfLogMgr")
public class JcdfLogMgr {

	//@Autowired  
	//private  HttpServletRequest request;

	
	@Resource(name = "jcdfLogServiceAop")
	private JcdfLogServiceAop jcdfLogServiceAop;
    private Log log = LogFactory.getLog(JcdfLogMgr.class);
    
	public void commitLog(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		
		String logModuleName = request
				.getAttribute(StaticVar.SYS_LOG_MODULE_KEY) == null ? null
				: (String) request.getAttribute(StaticVar.SYS_LOG_MODULE_KEY);
		//日志主体内容
		String logContextString = (String)request.getAttribute(StaticVar.SYS_LOG_KEY);
		
		//打印日期时间戳
		JCDFDateUtil.printlnSysTime();
		
		//@Before("pointCut()")
		//public void before(JoinPoint jp) {
		//jp.getStaticPart().
		// 获取目标类名
		
		String className = jp.getTarget().getClass().toString();
		className = className.substring(className.indexOf("com"));
		// 获取目标方法签名
		String signature = jp.getSignature().toString();
		// 获取方法名
		String methodName = signature.substring(signature.lastIndexOf(".") + 1,signature.indexOf("("));
		// 获取方法参数
		String paramStr =  Arrays.toString(jp.getArgs());
		String operation = className+"."+methodName+"("+paramStr+")";
		String operator = null;
		LoginUser sessionUser = ((LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY));
		operator = "admin";
		if(!"login".equalsIgnoreCase(methodName) &&  sessionUser != null){
			operator = sessionUser.getUserId() + StaticVar.SEPARATOR + sessionUser.getUserName();
		}
		//jp.getArgs();
		String operatorIP = JCDFWebUtil.getIP(request);
		
		log.fatal("==调用方法返回后 操作模块为=="+operation+"  | 操作内容为 == "+ logContextString);
		
		if (!StaticVar.SYS_LOG_ENABLED.equals(request.getAttribute(StaticVar.SYS_LOG_ENABLED_KEY))) {
			return;
		}
		JcdfLogEvent logEvent = new JcdfLogEvent(this);
		
		logEvent.setLoginName(operator);
		logEvent.setOperatoContent(logContextString);//logModuleName
		logEvent.setModuleName(className);
		
		logEvent.setLogIp(operatorIP);
		logEvent.setLogPara3(operation);//操作方法
		logEvent.setLogPara4(logModuleName);
		jcdfLogServiceAop.commitLog(logEvent); //写入数据库jcdf_log表中记录
		
	}

	public void beginLog(JoinPoint joinpoint) {
		String className = joinpoint.getTarget().getClass().toString();
		className = className.substring(className.indexOf("com"));
		// 获取目标方法签名
		String signature = joinpoint.getSignature().toString();
		// 获取方法名
		String methodName = signature.substring(signature.lastIndexOf(".") + 1,signature.indexOf("("));
		// 获取方法参数
		String paramStr =  Arrays.toString(joinpoint.getArgs());
		String operation = className+"."+methodName+"("+paramStr+")";
		log.fatal("=======调用方法之前  操作模块为========"+operation);
	}

	public void logException(Throwable ex) {
		JcdfLogEvent logEvent = new JcdfLogEvent(this);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		// 打印日期时间戳
		JCDFDateUtil.printlnSysTime();

		String logModuleName = request
				.getAttribute(StaticVar.SYS_LOG_MODULE_KEY) == null ? null
				: (String) request.getAttribute(StaticVar.SYS_LOG_MODULE_KEY);
		// 日志主体内容
		String logContextString = (String) request
				.getAttribute(StaticVar.SYS_LOG_KEY);

		// @Before("pointCut()")
		// public void before(JoinPoint jp) {
		// jp.getStaticPart().
		// 获取目标类名

		LoginUser sessionUser = ((LoginUser) request.getSession().getAttribute(
				StaticVar.LOGIN_USER_KEY));
		String operator = "admin";
		if (sessionUser != null) {
			operator = sessionUser.getUserId() + "_"
					+ sessionUser.getUserName();
		}
		// jp.getArgs();
		String operatorIP = JCDFWebUtil.getIP(request);

		logEvent.setLoginName(operator);
		logEvent.setOperatoContent(logContextString);
		logEvent.setModuleName(logModuleName);
		logEvent.setLogIp(operatorIP);
		// logEvent.setLogPara4(logModuleName);

		logEvent.setEx(ex);
		jcdfLogServiceAop.logException(logEvent);
		log.fatal("=======调用方法期间 操作异常信息信息为========"+ex);
	}

}
