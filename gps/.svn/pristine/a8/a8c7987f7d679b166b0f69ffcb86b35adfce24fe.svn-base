package com.yunda.gps.smsTemplate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Message;
import com.yunda.gps.smsTemplate.dao.SMSTemplateDao;
import com.yunda.gps.smsTemplate.mapper.SMSTemplateMapper;
import com.yunda.gps.smsTemplate.pojo.SMSTemplateVo;
import com.yunda.gps.smsTemplate.service.SMSTemplateService;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.mapper.VehicleCategoryMapper;

@Service(value="smsTemplateService")
public class SMSTemplateServiceImpl implements SMSTemplateService {
	
	@Resource(name="smsTemplateDao")
	private SMSTemplateDao smsTemplateDao;
	
	@Autowired
	private SMSTemplateMapper smsTemplateMapper;
	
	@Autowired
	private VehicleCategoryMapper vehicleCategoryMapper;

	@Override
	public List<SMSTemplateVo> list() {
		return null;
	}

	@Override
	public SMSTemplateVo get(String id) {
		return smsTemplateDao.get(id);
	}

	@Override
	public Object insert(SMSTemplateVo t) {
		smsTemplateDao.insert(t);
		return new Message(true,"新增成功");
	}

	@Override
	public Object update(SMSTemplateVo t) {
		smsTemplateDao.update(t);
		return new Message(true,"修改成功");
	}

	@Override
	public Object deleteById(String id) {
		if(StringUtil.isNotNullStr(id)){
			String[] ids = id.split(",");
			for (String idd : ids) {
				smsTemplateDao.deleteById(idd);
			}
		}else{
			return new Message(false,"删除失败");
		}
		return new Message(true,"删除成功");
	}

	/**
	 * 不分页
	 */
	@Override
	public List<SMSTemplateVo> getSMSList(SMSTemplateVo smsTemplateVo) {
		List<SMSTemplateVo> smsTemplateList = smsTemplateMapper.getSMSList(smsTemplateVo);
		for (SMSTemplateVo stv : smsTemplateList) {
			VehicleCategoryVo vcv = vehicleCategoryMapper.selectById(stv.getSmsBrand());
			VehicleCategoryVo vcv2 = vehicleCategoryMapper.selectById(stv.getSmsModel());
			if(vcv!=null){
				stv.setSmsBrand(vcv.getName());
			}
			if(vcv2!=null){
				stv.setSmsModel(vcv2.getName());
			}
		}
		return smsTemplateList;
	}

}
