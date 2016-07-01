package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.RunMileageDao;
import com.yunda.gps.monitor.pojo.RunMileage;
import com.yunda.gps.util.StringUtil;

@Service(value="runMileageServiceImpl")
public class RunMileageServiceImpl implements RunMileageService {

	@Resource(name="runMileageDaoImpl")
	private RunMileageDao runMileageDao;

	@Override
	public Page pageQuery(RunMileage runMileage) {
		Page page = null;
		page = runMileageDao.pageQuery(runMileage);		
		
		transformFlag(page);
		return page;
	}

	private void transformFlag(Page page) {
		@SuppressWarnings("unchecked")
		List<RunMileage> list = (List<RunMileage>) page.getRows();
		for(RunMileage line : list){
			if(StringUtil.isNotNullStr(line.getTxdFlag())){
				String txd = line.getTxdFlag();
				if(txd.equals("0")){
					line.setTxdFlag("待发送");
				}else if(txd.equals("1")){
					line.setTxdFlag("已发送");
				}
			}
		}
	}
}
