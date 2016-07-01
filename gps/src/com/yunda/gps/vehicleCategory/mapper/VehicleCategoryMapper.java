package com.yunda.gps.vehicleCategory.mapper;

import java.util.List;
import java.util.Map;

import com.yunda.gps.vehicleCategory.bean.VehicleCategory;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;

public interface VehicleCategoryMapper {
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<VehicleCategoryVo> pageQuery(VehicleCategoryVo vo);
    
	/**
	 * 删除
	 * @param id
	 * @return
	 */
    int deleteByID(String id);
    

	int backByID(String id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(VehicleCategory record);

    int insertSelective(VehicleCategory record);

    /**
     * 
     * @param vo
     * @return
     */
	List<Map<String, Object>> select(VehicleCategoryVo vo); 

	/**
	 * 更新操作
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(VehicleCategory record);

    int updateByPrimaryKey(VehicleCategory record);
    
    /**
     * 查询code最大值，按照同一类别同一父节点降序排列，取第一位数值
     * @author guoduan
     * @param VehicleCategory
     * @return list<VehicleCategory>
     */
    List<VehicleCategory> selectBy(VehicleCategoryVo record);

    /**
     * 根据id查询
     * @param id
     * @return VehicleCategoryVo
     */
    VehicleCategoryVo selectById(String id);
    
    /**
     * 按具体条件查询
     * @param vehicle
     * @return
     */
	List<VehicleCategoryVo> selectSelective(VehicleCategoryVo vehicle);


}