package com.yunda.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.Dictionarys;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.DicMapper;
import com.yunda.app.util.platform.filter.JcdfDaoSupport;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.util.StringUtil;
/**
 * 字典信息数据库访问接口实现
 * 
 * @author luogengxian
 * @date	2015-07-30
 *
 */
@Repository(value="dicDao")
public class DicDaoImpl extends JcdfDaoSupport implements DicDao{
	@Autowired
	private DicMapper dicMapper ;

	/***
	 * 获取所有字典信息
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> getAllDics() {
		return dicMapper.getAllDics();
	}
	/**
	 * 通过分类加载名称
	 * @param type 字典类型
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String, String>> getNameByType(String type){
		return dicMapper.getNameByType(type);
	}
	/**
	 * 通过编码加载对应的字典中文解释
	 * @param code
	 * @return Map<String,String>
	 */
	public Map<String, String> getNameById(String code){
		return dicMapper.getNameById(code);
	}
	
	/**
	 * 分页查询字典数据
	 * 
	 * @param dictionarys	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	@Override
	public Page pageQuery(Dictionarys dictionary) throws Exception{
		return this.pageQuery(dicMapper, "pageQuery",dictionary);
	}
	/***
	 * 字典编码转换为名称
	 * @param code 编码
	 * @return String
	 */
	public String codeToName(String code) {
		if(code==null)		return "";
		String name = "" ;
		String[] strArr = code.split(",") ;
		for(String str : strArr){
			if(StringUtil.isNotNullStr(str)){
				String tem = null==JedisOpExecuter.getSingleObject(str)?"":(String)JedisOpExecuter.getSingleObject(str);
				if(StringUtil.isNullStr(tem)){
					Map<String,String> m = this.getNameById(str);
					if(null!=m && m.get("NAME")!=null){
						name += ","+m.get("NAME") ;
						JedisOpExecuter.putSingleObject(str,m.get("NAME"));
					}
				}else{
					name += ","+tem;
				}
			}
		}
		return name.replaceFirst(",","") ;
	}
}
