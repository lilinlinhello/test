package com.yunda.gps.alarmParam.mapper;

import java.util.List;

import com.yunda.gps.alarmParam.data.AlarmParam;
import com.yunda.gps.alarmParam.data.AlarmParamVo;

public interface AlarmParamMapper {
	
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	List<AlarmParamVo> pageQuery(AlarmParam param);
	
	/**
	 * 插入报警参数信息
	 * @param param
	 * @return
	 */
	int insert(AlarmParam param);


	/**
	 * 更新所有的同类型的结束时间
	 * @param param
	 */
	public void updateAllEndTime(AlarmParam param);
	

	/**
	 * 
	 * @param alarmParam
	 * @return
	 */
	int updateByPrimaryKeySelective(AlarmParam record);

	
    AlarmParamVo selectByPrimaryKey(Integer apId);


}