package com.yunda.app.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;

/**
 * 导出测试
 * 
 * @author JiangShui
 * @date	2013-10-29
 */
@Repository(value="importDao")
public class ImportDaoImpl extends JcdfDaoSupport implements ImportDao{
	/* (non-Javadoc)
	 * @see com.yunda.app.dao.ImportDao#processImport(java.util.HashMap, java.util.ArrayList)
	 */
	public Message processImport(HashMap<String,String> params, ArrayList<Object[]> fileDatas) {
		String content = "";
		if(null != fileDatas && fileDatas.size() > 0) {
			for (Object[] objs : fileDatas) {
				int i = 0;
				for (Object obj : objs) {
					i = i + 1;
					if (1 == i) {
						content = content+(null == obj ? "," : obj.toString());
					} else {
						content = content+ " , " + (null == obj ? "" : (obj.toString()));
					}
				}
				content = content+"<br/>";
			}
		}
		Message msg = new Message(true, content);
		msg.setParam(params);
		return msg;
	}
	
}
