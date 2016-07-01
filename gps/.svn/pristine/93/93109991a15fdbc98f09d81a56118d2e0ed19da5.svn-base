package com.yunda.gps.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.dao.DicDao;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.monitor.dao.AlarmLoggingDao;
import com.yunda.gps.monitor.pojo.AlarmLogging;
import com.yunda.gps.util.StringUtil;

@Service(value="alarmLoggingServiceImpl")
public class AlarmLoggingServiceImpl implements AlarmLoggingService {

	@Resource(name="alarmLoggingDaoImpl")
	private AlarmLoggingDao alarmLoggingDao;
	
	@Autowired
	private DicDao dicDao;
	
	@Override
	public Page pageQuery(AlarmLogging alarmLogging) {
		Page page = null;
		page = alarmLoggingDao.pageQuery(alarmLogging);		
		
		transformFlag(page);		
		
		return page;
	}

	/**
	 * 将数据中特殊标记转化成名称
	 * @param page
	 */
	private void transformFlag(Page page) {
		//转换标记可视化
		@SuppressWarnings("unchecked")
		List<AlarmLogging> list = (List<AlarmLogging>) page.getRows();
		for(AlarmLogging alarm : list){
			if(StringUtil.isNotNullStr(alarm.getAlarmType())){
				String logging = alarm.getAlarmType();
				if(logging.equals("13")){
					alarm.setAlarmType("无信号报警");
				}else if(logging.equals("12")){
					alarm.setAlarmType("低速报警");
				}else if(logging.equals("22")){
					alarm.setAlarmType("线路偏离报警");
				}else{
					alarm.setAlarmType("");
				}				
			}
			
			if(StringUtil.isNotNullStr(alarm.getTxdFlag())){
				String txd = alarm.getTxdFlag();
				if(txd.equals("0")){
					alarm.setTxdFlag("待发送");
				}else if(txd.equals("1")){
					alarm.setTxdFlag("已发送");
				}
			}
		}
	}

}
