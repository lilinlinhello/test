package com.yunda.app.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.service.LoginService;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;

/**
 * 用户登录管理控制器
 * 
 * @author JiangShui
 * @date	2013-10-18
 * 
 */
@Controller
@RequestMapping(value = "/login.do")
public class LoginController {

	/**用户管理业务类*/
	@Resource(name="loginService")
	private LoginService loginService;
	Log log = LogFactory.getLog(LoginController.class);
	/**
	 * 如果以get请求的方式直接进行登录请求，则直接跳转到登录页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String forwardToLogin(HttpServletResponse response, HttpServletRequest request){
		request.removeAttribute("userId");
		request.removeAttribute("msg");
		return "public/login";
	}
	
	/**
	 * 用户登录
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletResponse response, HttpServletRequest request, String userId, String userPass ) throws IOException{
		log.fatal("用户登录ID="+userId);
		String code = request.getParameter("code").toUpperCase();
		String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern   p   =   Pattern.compile(regEx);
		Matcher   m   =   p.matcher(code);
		Matcher   matcher   =   p.matcher(userId);
		if(m.find() || matcher.find()){
			request.setAttribute("userId", userId);
			request.setAttribute("msg", "请输入正确字符");
			String msg =(String) request.getAttribute("msg");
			System.out.println(msg);
			return "public/login";
		}
		String codeInput = (String) request.getSession().getAttribute("code");
		if(codeInput==null||!codeInput.equals(code)){
			request.setAttribute("userId", userId);
			request.setAttribute("msg", "验证码错误！");
			return "public/login";
		}
		Object result = loginService.login(userId, userPass);
		if(null == result) {
			request.setAttribute("userId", userId);
			request.setAttribute("msg", "登录失败！");
			return "public/login";
		} else {
			
			if (result instanceof Message) {
				Message msg = (Message)result;
				request.setAttribute("userId", userId);
				request.setAttribute("msg", msg.getMsg());
				return "public/login";
			} else {
//				String userType = ((LoginUser)result).getUserType();
//				if(Constant.USERTYPE_02.equals(userType)){
//					request.setAttribute("userId", userId);
//					request.setAttribute("msg", "普通用户禁止登陆！");
//					return "public/login";
//				}
				Integer state = ((LoginUser)result).getState();
				if(state==null || state==Constant.USER_STOP_STATE){ //停用状态
					request.setAttribute("userId", userId);
					request.setAttribute("msg", "该用户已被停用，请联系管理员！");
					return "public/login";
				}
				request.getSession().setAttribute(StaticVar.LOGIN_USER_KEY, result);
				response.sendRedirect("index.do");
				return null;
			}
		}
		
	}
	
	/**
	 * 用户登出
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="method=loginOut")
	public String loginOut(HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
    	request.getSession().invalidate();
    	return "public/login";
	}
}
