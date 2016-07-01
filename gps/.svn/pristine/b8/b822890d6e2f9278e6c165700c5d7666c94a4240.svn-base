package com.yunda.app.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.exceptions.JedisException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.yunda.app.dao.SysUserDao;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.mapper.SysUserMapper;
import com.yunda.app.util.StaticVar;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.StringUtil;

/**
 * 用户管理业务类实现
 * 
 * @author JiangShui
 * @date	2013-10-21
 *
 */
@Service(value="loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	/**用户数据库操作接口*/
	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/* (non-Javadoc)
	 * @see com.yunda.app.service.ILoginService#login(java.lang.String, java.lang.String)
	 */
	public Object login(String userId, String userPass) {
		LoginUser loginUser = null;
		//验证账号密码非空
		if(null == userId || null == userPass || "".equals(userId.trim()) || "".equals(userPass.trim())) {
			return new Message(false, "账号或者密码不能为空！");
		}
		try {
			userId = userId.trim();
			userPass = getEncodePwd(URLDecoder.decode(userPass.trim(), "utf-8"));
			List<SysUser> users = sysUserDao.listAll(userId);
			if(null == users){
				return new Message(false, "账号或者密码不正确！");
			}
			for(SysUser user : users){
				if("SOA".equalsIgnoreCase(user.getCreateBy())){
					//来自soa的用户
					if(user.getUserId().equals(userId)){
						if(user.getUserPass().equals(userPass)){
							//获取用户本次登录的唯一令牌
							String token = UUID.randomUUID().toString();
							//产生放到session中的登录用户信息
							loginUser = new LoginUser();
							loginUser.setUserId(user.getUserId());
							loginUser.setUserName(user.getUserName());
							loginUser.setToken(token);
							loginUser.setUserType(user.getUserType());
							loginUser.setUserFrom(user.getUserFrom());
							loginUser.setState(user.getState());
							loginUser.setCompanyNo(user.getCompanyNo());

							//将令牌存于redis缓存(为防止redis中key的覆盖，所有用户令牌key=token_前缀+账号)
							JedisOpExecuter.putSingleObject(StaticVar.REDIS_SESSION_KEY_PREFIX+user.getUserId(), token);
							//获取用户权限编码并缓存到redis中
							List<String> menuCodes = sysUserDao.queryUserMenuCode(user.getUserId());
						
							JedisOpExecuter.putSingleObject(StaticVar.REDIS_MENU_CODE_KEY_PREFIX+user.getUserId(), menuCodes);
							
						}else{
							return new Message(false, "账号或者密码不正确！");
						}
					}
				}else{
					//来自外部注册的用户
						if(user.getUserPass().equals(userPass)){
							//获取用户本次登录的唯一令牌
							String token = UUID.randomUUID().toString();
							//产生放到session中的登录用户信息
							loginUser = new LoginUser();
							loginUser.setUserId(user.getUserId());
							loginUser.setUserName(user.getUserName());
							loginUser.setToken(token);
							loginUser.setUserType(user.getUserType());
							loginUser.setUserFrom(user.getUserFrom());
							loginUser.setState(user.getState());
							loginUser.setTelPhone(user.getTelPhone());
							loginUser.setCompanyNo(user.getCompanyNo());
							//将令牌存于redis缓存(为防止redis中key的覆盖，所有用户令牌key=token_前缀+账号)
							JedisOpExecuter.putSingleObject(StaticVar.REDIS_SESSION_KEY_PREFIX+user.getUserId(), token);
							//获取用户权限编码并缓存到redis中
							List<String> menuCodes = sysUserDao.queryUserMenuCode(user.getUserId());
							JedisOpExecuter.putSingleObject(StaticVar.REDIS_MENU_CODE_KEY_PREFIX+user.getUserId(), menuCodes);
						}else{
							return new Message(false, "账号或者密码不正确！");
						}
					}
				///===============缓存url============//
				if(StringUtil.isNotNullStr(user.getUserId())){
					 List<String> menuUrls = sysUserMapper.queryUserMenuUrls(user.getUserId());
					 List<String> meurls = new ArrayList<String>(); 
					 if(menuUrls!=null){
						 Pattern p = Pattern.compile("\\s*|\t|\r|\n");//去掉空格、换行符、制表符等
						 for(String url : menuUrls){
							 if(StringUtil.isNullStr(url)){
								 continue;
							 }
							Matcher m = p.matcher(url);
							if(m !=null){
								meurls.addAll(Arrays.asList(m.replaceAll("").split(",")));
							}
						}
					}
					 JedisOpExecuter.putSingleObject(Constant.REDIS_MENU_URL_KEY_PREFIX+user.getUserId(), meurls);
				}
				///===============缓存url============//
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginUser;
	}
	/**
	 * 取得加密后的密码。
	 * @param pwd
	 * @return String
	 */
	@SuppressWarnings("unused")
	private String getEncodePwd(String pwd) {
		java.security.MessageDigest md = null ;
		try {
			md = java.security.MessageDigest.getInstance("MD5") ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace() ;
		}
		// base64 解密一次
		String rs = "" ;
		byte[] sb = null ;
		if (pwd != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				sb = decoder.decodeBuffer(pwd);
				rs = encodeBase64(sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return rs.toString();
	}
	private static String encodeBase64(byte[] buf) throws Exception {
		return new BASE64Encoder().encodeBuffer(buf).replace("\n", "").replace("\r", "");
	}

}