package com.yunda.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.dao.AppRegDao;
import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.RandomStringUtil;

@Service(value = "appRegService")
@Transactional
public class AppRegServiceImpl implements AppRegService {

	@Autowired
	private AppRegDao appRegDao;
	
	@Override
	public Page pageQuery(AppReg appReg) {
		Page page = null;
		try {
			return appRegDao.pageQuery(appReg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	@Override
	public AppReg get(String ak) {
		return appRegDao.get(ak);
	}

	@Override
	public Object insert(AppReg t) {
		try{
			AppReg checkAppReg = new AppReg();
			checkAppReg.setAppName(t.getAppName());
			if (null != appRegDao.checkAppRegIsExist(checkAppReg)) {
				return new Message(false, "对应名称的应用已经存在！");
			} else {
				t.setAk(RandomStringUtil.random(32));
				appRegDao.insert(t);
				//记录日志
				JCDFWebUtil.Log("应用注册管理", "新增应用注册:"+t.getAppName());
				return new Message(true, "新增成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Message(false,"新增失败！");
	}

	@Override
	public Object update(AppReg t) {
		try {
			appRegDao.update(t);
			//记录日志
			JCDFWebUtil.Log("应用注册管理", "修改应用注册:"+t.getAppName());
			return new Message(true, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(false, "修改失败！");
	}

	@Override
	public Object deleteById(String regIds,String updateBy) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		if (null != regIds) {
			String[] regIdsArr = regIds.split(",");
			totleNum = regIdsArr.length;
			for (String regId : regIdsArr) {
				try {
					AppReg appReg=new AppReg();
			        appReg.setUpdateBy(updateBy);
			        appReg.setUpdateTime(new Date());
			        appReg.setDelFlag(Integer.parseInt(Constant.DELETE_FLAG));
			        appReg.setRegId(Integer.parseInt(regId));
					//停用应用
			        appRegDao.updateById(appReg);
					successNum = successNum + 1;
					JCDFWebUtil.Log("应用注册管理", "停用应用注册regID:"+regId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if (0 == successNum) {
			message = "停用失败，可能是对应的数据库正在被用户使用！";
		} else if (totleNum == successNum) {
			message = "成功停用"+successNum+"个应用！";
		} else if (successNum < totleNum) {
			message = "成功停用"+(successNum)+"个应用，失败停用"+(totleNum-successNum)+"个应用（可能是对应的数据库正在被用户使用）！";
		}
		return new Message(successNum > 0, message);
	}
	@Override
	public Object useById(String regIds,String updateBy) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		if (null != regIds) {
			String[] regIdsArr = regIds.split(",");
			totleNum = regIdsArr.length;
			for (String regId : regIdsArr) {
				try {
					AppReg appReg=new AppReg();
			        appReg.setUpdateBy(updateBy);
			        appReg.setUpdateTime(new Date());
			        appReg.setDelFlag(Constant.NO_DELETE_FLAG);
			        appReg.setRegId(Integer.parseInt(regId));
					//启用应用
			        appRegDao.updateById(appReg);
					successNum = successNum + 1;
					JCDFWebUtil.Log("应用注册管理", "启用应用注册regID:"+regId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//组装提示消息，响应到页面，给予操作提示
		if (0 == successNum) {
			message = "启用失败，可能是对应的数据库正在被用户使用！";
		} else if (totleNum == successNum) {
			message = "成功启用"+successNum+"个应用！";
		} else if (successNum < totleNum) {
			message = "成功启用"+(successNum)+"个应用，失败启用"+(totleNum-successNum)+"个应用（可能是对应的数据库正在被用户使用）！";
		}
		return new Message(successNum > 0, message);
	}
}