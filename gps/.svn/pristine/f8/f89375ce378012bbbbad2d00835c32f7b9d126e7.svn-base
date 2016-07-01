package com.yunda.app.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.dao.MenuDao;
import com.yunda.app.dao.RoleDao;
import com.yunda.app.dao.SysUserDao;
import com.yunda.app.entity.pojo.Auth;
import com.yunda.app.entity.pojo.RoleUserMap;
import com.yunda.app.entity.pojo.SysUser;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.SysUserMapper;
import com.yunda.app.util.JCDFStringUtil;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.StringUtil;

/**
 * 用户管理业务类实现
 * 
 *
 */
@Service(value="sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService{

	/**用户数据库操作接口*/
	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	/**功能菜单数据库访问接口*/
	@Resource(name="menuDao")
	private MenuDao menuDao;
	/**角色数据库操作接口*/
	@Resource(name="roleDao")
	private RoleDao roleDao;
	@Autowired
	private SysUserMapper sysUserMapper ;
	
	/* (non-Javadoc)
	 * @see com.yunda.app.service.ISysUserService#pageQuery(com.yunda.app.entity.pojo.SysUser)
	 */
	@Override
	public Page pageQuery(SysUser sysUser) {
		Page page = null;
		try {
			page = sysUserDao.pageQuery(sysUser);
			List<SysUser> list = (List<SysUser>) page.getRows();
			if(list !=null && list.size()>0){
				for(SysUser user:list){
					if(StringUtil.isNotNullStr(user.getCreateBy())){
						SysUser u = sysUserMapper.get(user.getCreateBy());
						if(u !=null){
							user.setCreateBy(u.getUserName());
						}
					}			
					if(StringUtil.isNotNullStr(user.getUpdateBy())){
						SysUser u = sysUserMapper.get(user.getUpdateBy());
						if(u !=null){
							user.setUpdateBy(u.getUserName());
						}
					}		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.service.ISysUserService#deleteById(java.lang.String, com.yunda.app.entity.pojo.SysUser)
	 * 批量删除操作，超级用户和自己不能删除
	 */
	@Override
	public Message deleteById(String userIds, LoginUser loginUser) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		boolean isHaveSuperUser = false;//本次删除操作是否存在超级管理员
		boolean isHavaSelf = false;//本次删除操作是否存在当前登录用户(当前登录用户不能删除自己)
		SysUser user = null;
		Auth auth = new Auth();
		auth.setAuthType(2);//AuthType=1:表示角色权限，2：表示用户权限
		RoleUserMap roleUserMap = new RoleUserMap();
		
		if (null != userIds) {
			String[] userIdsArr = userIds.split(",");
			totleNum = userIdsArr.length;
			for (String userId : userIdsArr) {
				try {
					//自己不允许删除自己
					if (userId.equals(loginUser.getUserId())) {
						isHavaSelf = true;
						continue;
					}
					//超级管理员不允许删除
					user = sysUserDao.get(userId);
					if (null != user && Constant.USERTYPE_SUPER01.equals(user.getUserType()) ) {
						isHaveSuperUser = true;
						continue;
					}
					//删除用户
					sysUserDao.deleteById(userId);
					//删除用户与权限的映射关系
					auth.setUserRoleId(userId);
					menuDao.deleteUserOrRoleAuthByAuth(auth);
					//删除用户与角色的映射关系
					roleUserMap.setUserId(userId);
					roleDao.deleteRoleUserMap(roleUserMap);
					successNum = successNum + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
				user = null;
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if (0 == successNum) {
			message = "删除失败！" + ((isHaveSuperUser || isHavaSelf) ? "（注意：超级管理员和自己不允许删除）":"");
		} else if (totleNum == successNum) {
			message = "成功删除"+successNum+"条记录！";
		} else if (successNum < totleNum) {
			message = "成功删除"+(successNum)+"条记录，失败删除"+(totleNum-successNum)+"条记录！" + ((isHaveSuperUser || isHavaSelf) ? "（注意：超级管理员和自己不允许删除）":"");
		}
		return new Message(successNum > 0, message);
	}
	
	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseService
	 */
	@Override
	public Object deleteById(String userIds) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseService
	 */
	@Override
	public SysUser get(String id) {
		return sysUserDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseService
	 */
	@Override
	public Object insert(SysUser sysUser) {
		Message msg = new Message(false, "新增失败！");
		try {
			if(null != sysUserDao.get(sysUser.getUserId())) {
				msg = new Message(false, "用户账号已存在，新增失败！");
			} else {
				sysUser.setState(Constant.USER_ACTIVE_STATE); //激活状态 
				sysUser.setUserPass(JCDFStringUtil.encodePassword(StaticVar.DEFAULT_PASSWORD));
				sysUser.setCreateTime(DateUtil.format(new Date()));
				sysUser.setUpdateTime(DateUtil.format(new Date()));
				sysUserDao.insert(sysUser);
				msg = new Message(true, "新增成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseService
	 */
	@Override
	public List list() {
		return sysUserDao.list();
	}

	/* (non-Javadoc)
	 * @see com.yunda.app.base.BaseService
	 * 修改用户信息
	 */
	@Override
	public Object update(SysUser sysUser) {
		try {
			sysUser.setUpdateTime(DateUtil.format(new Date()));
			sysUserDao.update(sysUser);
			return new Message(true, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "修改失败！");
	}
    
	/**
	 * @author 郭端端
	 * 用户密码修改
	 * **/
	@Override
	public Message changePass(LoginUser loginUser, String oldPass, String newPass) {
		Message msg = null;
		try {
			if(!JCDFStringUtil.isNotNullAndEmpty(oldPass) || !JCDFStringUtil.isNotNullAndEmpty(newPass)) {
				msg = new Message(false, "原、新密码不能为空！");
			} else {
				String userId = loginUser.getUserId();
				oldPass = URLDecoder.decode(oldPass.trim(), "utf-8");
				newPass = URLDecoder.decode(newPass.trim(), "utf-8");
				SysUser sysUser = sysUserMapper.getUserInfo(userId);
				if(null == sysUser) {
					msg = new Message(false, "当前登录用户已不存在，可能是被销户！");
				} else if (!oldPass.equals(sysUser.getUserPass())) {
					msg = new Message(false, "原密码不正确！");
				} else {
					sysUser.setUserPass(newPass);
					sysUserDao.update(sysUser);
					msg = new Message(true, "密码修改成功，请重新登录！");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null != msg ? msg : (new Message(false, "密码修改失败！"));
	}

	@Override
	public SysUser getUserInfo(String userId) {
		return sysUserDao.getUserInfo(userId);
	}
}