package com.yunda.app.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.JcdfLog;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.JcdfLogMapper;
import com.yunda.app.util.JCDFWebUtil;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.gps.util.DateUtil;


@Repository(value="jcdfLogDao")
public class JcdfLogDaoImpl extends JcdfDaoSupport implements JcdfLogDao{

	@Autowired
	private JcdfLogMapper jcdfLogMapper;
	
	
	public Page pageQuery(JcdfLog log) throws Exception {
		return pageQuery(jcdfLogMapper, "pageQuery", log);
	}
	


	public void insert(JcdfLog t) {
		t.setLogId(JCDFWebUtil.getGlobalKey());
		t.setOperatorTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
		jcdfLogMapper.insert(t);
	}


}
