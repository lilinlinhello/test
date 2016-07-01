package com.yunda.app.util.platform.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.util.StaticVar;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.StringUtil;

/**
 * 登录及权限控制过滤器
 * 
 * @author JiangShui
 * @date	2013-10-28
 */
public class LoginAndAuthFilter implements Filter {
 
	@Override
	public void destroy() {

	}
	private final Logger logger = Logger.getLogger(getClass());

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
	    HttpServletResponse response = (HttpServletResponse)rsp; 
		HttpSession session = request.getSession();
		Object user = session.getAttribute(StaticVar.LOGIN_USER_KEY);
		String url = request.getRequestURL().toString();
		String rootUrl = request.getContextPath() ;//获取项目名称路径
		String requestType = request.getHeader("X-Requested-With");
	     // 登陆页面无需过滤
		if (url.endsWith("login.jsp") || url.endsWith("login.do")||url.endsWith("index.html") || url.endsWith("index.jsp")||url.endsWith("location.do")||url.endsWith("code.do")
				||url.endsWith("inport.do")||url.endsWith("vehicleInsert.do") ||url.endsWith("smsSendResponse.do")) {
			chain.doFilter(request, response);
		//首先如果登录成功放行,登录请求处理默认放行
		} else if ((null != session && null != user)) {
			LoginUser loginUser = (LoginUser)user;
			//检查当前访问用户的令牌是否与服务器redis中缓存的令牌一致，如果不一致则代表有用户重新登录了，剔除当前用户
			if (!loginUser.getToken().equals(JedisOpExecuter.getSingleObject(StaticVar.REDIS_SESSION_KEY_PREFIX+loginUser.getUserId()))) {
				
				//根据当前请求的类型给予不同的响应回复(ajax或者普通请求)
				if(requestType != null && requestType.equals("XMLHttpRequest")) {
					BaseController.sendJsonDataToClient("{\"noLogin\":true,\"rows\":[],\"msg\":'您的账号在另一地点登录，您被迫下线，如非本人操作，可能是密码泄露，建议您及时修改密码！'}", response);
	            } else {
	            	request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
	            	request.getSession().invalidate();
	            	request.setAttribute("msg", "您的账号在另一地点登录，您被迫下线，如非本人操作，可能是密码泄露，建议您及时修改密码！");
//	            	request.getRequestDispatcher("/error.jsp").forward(request, response);
	            	response.sendRedirect(rootUrl+"/error.jsp");
	            }
			} else {

				//权限校验(userType==1的超级用户不用管权限)
				String queryStr = request.getQueryString();
				String checkUrl = request.getServletPath() ;
				//get,post方法暂且放过，不校验
				if(null!=queryStr && !"/welcome.jsp".equals(checkUrl)){
					int cnt = queryStr.indexOf("&") ;
					if(cnt>0){
						if(StringUtil.isNotNullStr(queryStr) && queryStr.indexOf("method")!=-1){ //防止url后面直接带参数的情况出现
							checkUrl += "?"+queryStr.substring(0,cnt) ;
						}
					}else{
						if(StringUtil.isNotNullStr(queryStr) && queryStr.indexOf("method")!=-1){
							checkUrl += "?"+queryStr ;
						}
					}
				}
				if(checkRight(loginUser,checkUrl)){
					chain.doFilter(request, response) ;
				}else{
					if(requestType != null && requestType.equals("XMLHttpRequest")) {
						BaseController.sendJsonDataToClient("{\"noLogin\":true,\"rows\":[],\"msg\":'访问被拒绝！'}", response);
		            } else {
		            	request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
		            	request.getSession().invalidate();
		            	request.setAttribute("msg", "访问被拒绝！");
//		            	request.getRequestDispatcher("/index.html").forward(request, response);
		            	response.sendRedirect(rootUrl+"/index.html");
		            }
					//throw new JcdfException("访问被拒绝!");
				}
			}
		} else {
//			String requestType = request.getHeader("X-Requested-With");
			//根据当前请求的类型给予不同的响应回复(ajax或者普通请求)
			if(requestType != null && requestType.equals("XMLHttpRequest")) {
//				response.sendError();
				BaseController.sendJsonDataToClient("{\"noLogin\":true,\"rows\":[],\"msg\":'你没有登录，无权进行该操作！'}", response);
            } else {
            	request.getSession().removeAttribute(StaticVar.LOGIN_USER_KEY);
            	request.getSession().invalidate();  //session过期 
//            	request.getRequestDispatcher("/index.html").forward(request, response);
            	response.sendRedirect(rootUrl+"/index.html");
            }
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	@SuppressWarnings("unchecked")
	private boolean checkRight(LoginUser loginUser,String serverPath){
		logger.fatal("action:"+serverPath) ;
		System.out.println("serverPath = " + serverPath);
		if(Constant.USERTYPE_SUPER01.equals(loginUser.getUserType())){//超管放行
			return true ;
		}
		List<String> anonynousAction = Arrays.asList(StaticVar.ANONYMOUS_ACTIONNAME.split(",")) ;
		if(anonynousAction.contains(serverPath)){
			return true ;
		}
		List<String> menuUrls = (List<String>)JedisOpExecuter.getSingleObject(Constant.REDIS_MENU_URL_KEY_PREFIX+loginUser.getUserId()) ;
		if(null!=menuUrls && menuUrls.contains(serverPath)){
			return true ;
		}
		return false;
	}

	
}