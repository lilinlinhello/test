package com.yunda.gps.vehicleCategory.service;

import java.util.List;
import java.util.Map;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;

public interface VehicleCategoryService {
	/**
	 * 分页
	 * @param vo
	 * @return
	 */
	public Page pageQuery(VehicleCategoryVo vo);
	
	/**
	 * 根据id删除
	 * @param ids 多个id按，分割
	 * @return
	 */
	public Message deleteByID(String ids);
	
	/**
	 * 删除恢复
	 * @param ids
	 * @return
	 */
	public Message backByID(String ids);
	
	/**
	 * 子节点childCode生成
	 * 顶级code生成规则为两位数字初始为01，次顶级编码生成为3位数字初始为001，三级code编码也为三位数字初始为001
	 * 组合在一起标示一条唯一记录01001001，不同层次编码依次累加
	 * @author guoduan
	 * @param 模块类别 modelType 父节点编码parentCode
	 * @return
	 */
	public String createCode(VehicleCategoryVo record);

	/**
	 * 获取子节点
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> getVeicleInfo(VehicleCategoryVo vo);
	
	/**
	 * 插入新数据
	 * @param vehicle
	 * @return
	 */
	public Message insert(VehicleCategoryVo vehicle);
	
	/**
	 * 查出单车次的信息
	 * @param vo
	 * @return
	 */
	VehicleCategoryVo selectById(String id);

	/**
	 * 
	 * @param vo
	 * @return
	 */
	public Message update(VehicleCategoryVo vo);

	
}
