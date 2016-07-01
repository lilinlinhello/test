package com.yunda.app.mapper;

import java.util.List;
import java.util.Map;

import com.yunda.app.entity.pojo.Dictionarys;

public interface DicMapper {
	//String getName();
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
	 * 分页查询字典数据
	 * 
	 * @param dictionarys	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public List<Dictionarys> pageQuery(Dictionarys dictionary) ;
	
	/**
	 * 通过(dicId)查询字典信息
	 * 
	 * @param dicId
	 * @return
	 */
	public Dictionarys get(String dicId);
	
	/**
	 * 根据查询条件
	 * 获取Dictionarys对象
	 * */
	public Dictionarys getDictionarys(Dictionarys dictionary);
	
	/**
	 * 新增字典
	 * @param Dictionarys
	 * @return
	 */
	public int insert(Dictionarys dictionary);

	
	/**
	 * 根据(dicId)进行字典信息修改
	 * 
	 * @param Dictionarys
	 */
	public void update(Dictionarys dictionary);
	
	//根据字典id删除字典
	public void deleteById(String[] ids);
	/**
	 * 根据字典名称获取对应的编码
	 * */
	Map<String, String> nameToCode(String name);
	
        List<Map<String, Object>> queryDicType(Map<String, String> params);
	
}
