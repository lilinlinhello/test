package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.dao.DicDao;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.LineMileageDao;
import com.yunda.gps.monitor.pojo.LineMileage;
import com.yunda.gps.util.StringUtil;

@Service(value="lineMileageServiceImpl")
public class LineMileageServiceImpl implements LineMileageService {

	@Resource(name="lineMileageDaoImpl")
	private LineMileageDao lineMileageDao;
	
	@Autowired
	private DicDao dicDao;
	
	@Override
	public Page pageQuery(LineMileage lineMileage) {
		Page page = null;
		page = lineMileageDao.pageQuery(lineMileage);		
		
		transformFlag(page);
		return page;
	}

	/**
	 * 将数据中特殊标记转化成名称
	 * @param page
	 */
	private void transformFlag(Page page) {
		@SuppressWarnings("unchecked")
		List<LineMileage> list = (List<LineMileage>) page.getRows();
		for(LineMileage line : list){
			if(StringUtil.isNotNullStr(line.getTxdFlag())){
				String txd = line.getTxdFlag();
				if(txd.equals("0")){
					line.setTxdFlag("待发送");
				}else if(txd.equals("1")){
					line.setTxdFlag("已发送");
				}
			}
			
			if(StringUtil.isNotNullStr(line.getIsSkewing())){
				String isSkew = line.getIsSkewing();
				if(isSkew.equals("0")){
					line.setIsSkewing("未偏移");
				}else if(isSkew.equals("1")){
					line.setIsSkewing("已偏移");
				}
			}
		}
	}

}
