package com.yunda.app.service;

import java.util.List;
import java.util.Map;

import com.yunda.app.base.BaseService;
import com.yunda.app.entity.pojo.Dictionarys;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;

/***
 * 地点信息接口类
 * @author luogengxian
 *
 */
public interface DicService extends BaseService<Dictionarys>{
	/***
	 * 获取所有字典信息
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> getAllDics();
	/**
	 * 通过分类加载名称
	 * @param type 字典类型
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	List<Map<String,String>> getNameByType(String type);
	/**
	 * 通过编码加载对应的字典中文解释
	 * @param code
	 * @return Map<String,String>
	 */
	Map<String,String> getNameById(String code);
	/***
	 * 字典编码转换为名称
	 * @param code 编码
	 * @return
	 */
	String codeToName(String code);
	/**
	 * 获取多类字典类型集合
	 * @param Map<String,String>
	 * @return List<Map<String,List<Map<String,String>>>>
	 */
	List<Map<String,List<Map<String,String>>>> getDicByTypes(Map<String,String> params);
	
	/**
	 * 分页查询字典数据
	 * 
	 * @param pageQueryParams	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Dictionarys dictionary);
	/**
	 * 删除指定的字典
	 * @param Ids	将要删除的所有字典，多个用逗号分隔
	 * @return
	 */
	public Message deleteById(String ids);
	/**
	 * 根据查询条件
	 * 获取Dictionarys对象
	 * */
	public Dictionarys getDictionarys(Dictionarys dictionary);
	
	/***
	 * 字典名称转换为code
	 * @param name 字典名称
	 * @return
	 */
	String nameToCode(String name);
	
        List<Map<String, Object>> queryDicType(Map<String, String> params);
}
