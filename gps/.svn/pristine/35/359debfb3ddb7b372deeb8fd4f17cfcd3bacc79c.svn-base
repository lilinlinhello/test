package com.yunda.app.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunda.app.dao.JcdfLogDao;
import com.yunda.app.entity.pojo.JcdfLog;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="jcdfLogService")
public class JcdfLogServiceImpl implements JcdfLogService {

	/**用户数据库操作接口*/
	@Resource(name="jcdfLogDao")
	private JcdfLogDao jcdfLogDao;
	
	
	@Override
	public Page pageQuery(JcdfLog log) {
		Page page = null;
		try {
			page = jcdfLogDao.pageQuery(log);
			
			//转换null 字符串 防止前端识别有误
			if(page !=null){
				@SuppressWarnings("unchecked")
				List<JcdfLog> list = (List<JcdfLog>) page.getRows();
				for(JcdfLog jcdfLog :list){					
					Field[] fields = jcdfLog.getClass().getDeclaredFields();
					for(Field field : fields){
						field.setAccessible(true);
						if(field.getType() != String.class){
							continue;
						}else{
							String oldValue = (String) field.get(jcdfLog);
							if(StringUtil.isNotNullStr(oldValue)&& oldValue.indexOf("null")!=-1){
								String newValue = oldValue.replaceAll("null", "Null").replace("\"", "");
								field.set(jcdfLog, newValue);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}

	/**
	 * 插入记录
	 * @param jcdfLog
	 * @author Administrator
	 */
	public Object insert(JcdfLog t) {
		t.setOperatorTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
		jcdfLogDao.insert(t);
		return null;
	}

}
