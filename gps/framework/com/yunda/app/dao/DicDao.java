package com.yunda.app.dao;

import java.util.List;
import java.util.Map;

import com.yunda.app.entity.pojo.Dictionarys;
import com.yunda.app.entity.vo.Page;

public interface DicDao {
	/***
	 * 获取所有字典信息
	 * @return List<Map<String,String>>
	 */
	List<Map<String, String>> getAllDics();
	/**
	 * 通过分类加载名称
	 * @param type 字典类型
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<Map<String, String>> getNameByType(String type);
	/**
	 * 通过编码加载对应的字典中文解释
	 * @param code
	 * @return Map<String,String>
	 */
	Map<String, String> getNameById(String code);
	
	/**
	 * 分页查询用户数据
	 * 
	 * @param dictionarys	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Dictionarys dictionarys) throws Exception;
	/***
	 * 字典编码转换为名称
	 * @param code 编码
	 * @return String
	 */
	public String codeToName(String code) ;
}
