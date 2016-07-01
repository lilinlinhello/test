package com.yunda.gps.alarmParam.servcie.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.alarmParam.dao.AlarmParamDao;
import com.yunda.gps.alarmParam.data.AlarmParam;
import com.yunda.gps.alarmParam.data.AlarmParamVo;
import com.yunda.gps.alarmParam.mapper.AlarmParamMapper;
import com.yunda.gps.alarmParam.servcie.AlarmParamService;
import com.yunda.gps.util.StringUtil;

@Service(value="alarmParamServiceImpl")
public class AlarmParamServiceImpl implements AlarmParamService {

	@Resource(name="alarmParamDaoImpl")
	private AlarmParamDao alarmParamDao;
	
	@Autowired
	private AlarmParamMapper alarmParamMapper;

	@Override
	public Page pageQuery(AlarmParam alarmParam) {
		Page page = alarmParamDao.pageQuery(alarmParam);
		
		transform(page);
		
		return page;
	}
	


	@Override
	public AlarmParamVo selectById(String id) {
		return alarmParamMapper.selectByPrimaryKey(Integer.valueOf(id));
	}

	@Transactional
	@Override
	public Message insert(AlarmParam alarmParam) {
		alarmParamMapper.updateAllEndTime(alarmParam);
		int i = alarmParamMapper.insert(alarmParam);
		if(i == 1){
			return new Message(true, "数据新增成功");
		}else{
			return new Message(false, "数据新增失败");
		}
	}
	


	@Override
	public Message update(AlarmParam alarmParam) {
		int i = alarmParamMapper.updateByPrimaryKeySelective(alarmParam);
		alarmParamMapper.updateAllEndTime(alarmParam);
		if(i == 1){
			return new Message(true, "数据修改成功");
		}else{
			return new Message(false, "数据修改失败");
		}
	}

	private void transform(Page page) {
		@SuppressWarnings("unchecked")
		List<AlarmParamVo> list = (List<AlarmParamVo>) page.getRows();
		for (AlarmParamVo alarmParam : list) {
			if(StringUtil.isNotNullStr(alarmParam.getApType())){
				String apType = alarmParam.getApType();
				if(apType.equals("12")){
					alarmParam.setApType("低速报警");
				}else if(apType.equals("13")){
					alarmParam.setApType("无信号报警");
				}else if(apType.equals("22")){
					alarmParam.setApType("线路偏移报警");
				}
			}
			
			if(alarmParam.getDistance().equals("0")){
				alarmParam.setDistance("无");
			}
			if(alarmParam.getCollection().equals("0")){
				alarmParam.setCollection("无");
			}
			if(alarmParam.getSpeed().equals("0")){
				alarmParam.setSpeed("无");
			}
		}
		
	}


}
