package com.yunda.inter.outsideInterface.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.gps.constant.Constant;
import com.yunda.inter.outsideInterface.bean.SynVehicle;
import com.yunda.inter.outsideInterface.mapper.SynVehicleMapper;
import com.yunda.inter.outsideInterface.service.SynVehicleService;
import com.yunda.gps.util.DateUtil;


@Service(value="synVehicleService")
@Transactional
public class SynVehicleServiceImpl implements SynVehicleService {
	  @Autowired
	  private SynVehicleMapper synVehicleMapper;
	  /**
	   * 插入数据
	   * @param  SynVehicle
	   * @author Administrator
	   */
		public Map<String,Object> insert(SynVehicle synVehicle){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			String licensecard = synVehicle.getLicensecard();
			SynVehicle vo = synVehicleMapper.selectByCard(licensecard);
			if(vo !=null){
				synVehicle.setId(vo.getId());
				synVehicle.setCreateBy(vo.getCreateBy());
				synVehicle.setCreateTime(vo.getCreateTime());
				synVehicle.setUpdateTime(DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss));
				synVehicle.setUpdateBy(Constant.FROM);
				int count =	synVehicleMapper.updateByPrimaryKey(synVehicle);
				if(count >0){
					resultMap.put("flag", true);
					resultMap.put("msg", "接口同步gps修改成功！");
				}else{
					resultMap.put("flag", false);
					resultMap.put("msg", "接口同步gps修改失败！");
				}
			}else{
				synVehicle.setCreateTime(DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss));
				synVehicle.setCreateBy(Constant.FROM);
				int count =	synVehicleMapper.insert(synVehicle);
				if(count >0){
					resultMap.put("flag", true);
					resultMap.put("msg", "接口同步gps新增成功！");
				}else{
					resultMap.put("flag", false);
					resultMap.put("msg", "接口同步gps新增失败！");
				}
			
			}
			return resultMap;
			
		}
}
