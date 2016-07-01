package com.yunda.gps.fixUsers.mapper;

import java.util.List;
import java.util.Map;

import com.yunda.gps.fixUsers.data.FixUsers;

public interface FixUsersMapper {
	
	/**
	 * 分页查询
	 * @param fixUsers
	 * @return
	 */
	List<FixUsers> pageQuery(FixUsers fixUsers);
	
	/**
	 * 删除by id
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    int insert(FixUsers record);


    /**
     * 
     * @param id
     * @return
     */
    FixUsers selectByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FixUsers record);

    /**
     * key--id  value--name
     * @return
     */
	List<Map<String, Object>> getUserMap();


}