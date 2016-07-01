package com.yunda.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.dao.DicDao;
import com.yunda.app.entity.pojo.Dictionarys;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.mapper.DicMapper;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.StringUtil;
/**
 * @date 2015-07-30
 * */
@Service(value="dicService")
@Transactional
public class DicServiceImpl implements DicService{
	@Resource(name="dicDao")
	private DicDao dicDao;
	@Autowired
	private DicMapper dicMapper ;
	
	/***
	 * 获取所有字典信息
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getAllDics(){
		return dicDao.getAllDics();
	}
	/**
	 * 通过分类加载名称
	 * @param type 字典类型
	 * @author luogengxian
	 * @date :2015-07-30
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> getNameByType(String type){
		return dicDao.getNameByType(type);
	}
	/**
	 * 通过编码加载对应的字典中文解释
	 * @param code
	 * @return Map<String,String>
	 */
	public Map<String,String> getNameById(String code){
		return dicDao.getNameById(code);
	}
	/***
	 * 字典编码转换为名称
	 * @param code 编码
	 * @return String
	 */
	public String codeToName(String code) {
		return dicDao.codeToName(code);
	}
	/**
	 * 获取多类字典类型集合
	 * @param params
	 * @return List<Map<String,List<Map<String,String>>>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,List<Map<String,String>>>> getDicByTypes(Map<String,String> params){
		List<Map<String,List<Map<String,String>>>> list = new ArrayList<Map<String,List<Map<String,String>>>>();
		if(null!=params){
			for(Map.Entry<String, String> entry : params.entrySet()){
				List<Map<String,String>> map =(List<Map<String,String>>)JedisOpExecuter.getSingleObject(entry.getValue());
				if(null==map){// redis 缓存没有，就直接去数据库查询
					map = this.getNameByType(entry.getValue());//按照类型查出的字典
				}
				if(null!=map){
					Map<String,List<Map<String,String>>> ma = new HashMap<String,List<Map<String,String>>>();
					ma.put(entry.getKey(),map);
					list.add(ma);
				}
			}
		}
		return list ;
	}
	
	/**
	 * 分页查询字典数据
	 * 
	 * @param pageQueryParams	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public Page pageQuery(Dictionarys dictionary){
		Page page = null;
		try {
			page = dicDao.pageQuery(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == page ? new Page() : page;
	}
	/**
	 * 删除指定的字典0
	 * @param Ids	将要删除的所有字典，多个用逗号分隔
	 * @return
	 */
	public Message deleteById(String idstr){
          try{
        	  String[] ids = idstr.split(",");
        	  dicMapper.deleteById(ids);
        	  return new Message(true,"删除成功！");
          }catch(Exception e){
        	  e.printStackTrace();
          }
		return new Message(false,"删除失败！");
	}
	
	@Override
	public Dictionarys get(String id) {
		return dicMapper.get(id);
	}
	
	@Override
	public Object insert(Dictionarys dictionary) {
		try{
			Dictionarys dic = new Dictionarys();
			dic.setDicId(dictionary.getDicId());
			Dictionarys result = dicMapper.getDictionarys(dic);
			if(result!=null){
				return new Message(false,"该字典编号已经存在！");
			}
			dic = new Dictionarys();
			dic.setDicType(dictionary.getDicType());
			dic.setDicName(dictionary.getDicName());
		    result = dicMapper.getDictionarys(dic);
			if(result!=null){
				return new Message(false,"该字典名称已经存在！");
			}
			dic = new Dictionarys();
			dic.setDicType(dictionary.getDicType());
			dic.setDicOrder(dictionary.getDicOrder());
		    result = dicMapper.getDictionarys(dic);
			if(result!=null){
				return new Message(false,"该字典类型的序号已经存在！");
			}
			dicMapper.insert(dictionary);
			return new Message(true,"新增成功！");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Message(false,"新增失败！");
	}
	
	@Override
	public Object update(Dictionarys dictionary) {
		try{
			Dictionarys dic = new Dictionarys();
			dic.setDicType(dictionary.getDicType());
			dic.setDicName(dictionary.getDicName());
			Dictionarys result = dicMapper.getDictionarys(dic);
			if(result!=null && !dictionary.getDicId().equals(result.getDicId())){
				return new Message(false,"该字典名称已经存在！");
			}
			dic = new Dictionarys();
			dic.setDicType(dictionary.getDicType());
			dic.setDicOrder(dictionary.getDicOrder());
		    result = dicMapper.getDictionarys(dic);
			if(result!=null &&  !dictionary.getDicId().equals(result.getDicId())){
				return new Message(false,"该字典类型的序号已经存在！");
			}
			String dicId = dictionary.getDicId();
			dic = dicMapper.get(dicId);
			if(Constant.DELETE_FLAG.equals(dic.getDeleteFlag())){
				return new Message(false,"该字典已被停用！");
			}
			dicMapper.update(dictionary);
			return new Message(true,"修改成功！");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Message(false,"修改失败！");
	}
	
	@SuppressWarnings("unchecked")
	public List list() {
		return null;
	}
	
	/**
	 * 根据查询条件
	 * 获取Dictionarys对象
	 **/
	public Dictionarys getDictionarys(Dictionarys dictionary){
		return dicMapper.getDictionarys(dictionary);
	}
	
	/***
	 * 字典名称转换为code
	 * @param name 字典名称
	 * @return
	 */
	public String nameToCode(String name){
		if(StringUtil.isNullStr(name)){
			return " " ;
		}
		String[] str = null;
		String codeStr ="";
		if(name.indexOf("，")!=-1){ //中文的符号

			str = name.split("，");
		}
		if(name.indexOf("、")!=-1){

			str = name.split("、");
		}
		if(str !=null && str.length>0){
			for(int i=0;i<str.length;i++){
				  if("B2B".equalsIgnoreCase(str[i])){
					  codeStr+="dic_business_type01,";  //字典表特殊处理
				  }else{
					  if("母婴类".equalsIgnoreCase(str[i])){
						  codeStr+="dic_itemType02,"; 
					  }else{
						  Map<String,String> map = dicMapper.nameToCode(str[i]);
						   if(map !=null){
							codeStr+=map.get("ID")+","; //英文 的符号 
						   }
					  }
				  }
				}
			if(StringUtil.isNotNullStr(codeStr) && codeStr.endsWith(",")){
				codeStr = codeStr.substring(0,codeStr.length()-1);
			}
		}else{
			if(StringUtil.isNotNullStr(name)){
				  if("B2B".equalsIgnoreCase(name)){
					  codeStr+="dic_business_type01,";  //字典表特殊处理
				  }else{
					  if("母婴类".equalsIgnoreCase(name)){
						  codeStr+="dic_itemType02,"; 
					  }else{
						  Map<String,String> map = dicMapper.nameToCode(name);
						   if(map !=null){
							codeStr=map.get("ID"); //英文 的符号 
						   }
					  }
				  }
			}else{
				codeStr ="  ";
			}
	
		}
       
		return codeStr;
	}

        @Override
        public List<Map<String, Object>> queryDicType(Map<String, String> params) {
            return this.dicMapper.queryDicType(params);
        }
}
