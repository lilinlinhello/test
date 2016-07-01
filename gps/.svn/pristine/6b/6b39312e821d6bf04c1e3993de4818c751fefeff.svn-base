package com.yunda.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunda.app.base.BaseController;
import com.yunda.app.entity.pojo.Dictionarys;
import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.entity.vo.Message;
import com.yunda.app.util.StaticVar;
import com.yunda.app.util.platform.jedis.JedisOpExecuter;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.StringUtil;

/**
 * 字典管理
 * 
 * @author 郭端端
 */
@Controller
@RequestMapping(value = "/auth/dictionary.do")
public class DictionaryController extends BaseController {

    /**
     * 转发到字典列表页面
     * 
     * @param response
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String forwardList(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
        request.setAttribute("dictionarys", JSONArray.fromObject(dics));
        return "auth/dictionary_list";
    }

    /**
     * 转发到字典新增页面
     * 
     * @param response
     * 
     * @return 字典新增页面视图
     */
    @RequestMapping(params = "method=forwardAddJsp")
    public String forwardAdd(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
        request.setAttribute("dictionarys", JSONArray.fromObject(dics));
        return "auth/dictionary_add";
    }

    /**
     * 转发到字典修改
     * 
     * @param response
     * 
     * @return 字典修改页面视图
     */
    @RequestMapping(params = "method=forwardEditJsp")
    public String forwardEdit(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
        request.setAttribute("dictionarys", JSONArray.fromObject(dics));
        return "auth/dictionary_edit";
    }

    /**
     * 分页查询字典数据
     * 
     * @param response
     * @param request
     * @param Dictionarys
     */
    @RequestMapping(params = "method=pageQuery")
    public void pageQuery(HttpServletResponse response, HttpServletRequest request, Dictionarys dictionary) {
        sendJsonDataToClient(dicService.pageQuery(dictionary), response);
    }

    /**
     * 字典新增
     * 
     * @param response
     * @param Dictionarys
     */
    @RequestMapping(params = "method=insert")
    public void insert(HttpServletResponse response, HttpServletRequest request, Dictionarys dictionary) {
        LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
        dictionary.setCreateBy(user.getUserId());
        dictionary.setUpdateBy(user.getUserId());
        dictionary.setCreateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
        dictionary.setUpdateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
        dictionary.setDeleteFlag(Constant.NO_DELETE_FLAG); // 未删除状态
        Message msg = (Message) dicService.insert(dictionary);
        //重新加载字典
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
        request.setAttribute("dictionarys", JSONArray.fromObject(dics));
        // 刷新redis缓存
        String dicType = dictionary.getDicType();
        if (StringUtil.isNotNullStr(dicType)) {
            List<Map<String, String>> dic = dicService.getNameByType(dicType);
            JedisOpExecuter.removeSingleObject(dicType); // 移除缓存
            JedisOpExecuter.putSingleObject(dictionary.getDicId(), dictionary.getDicName()); // 单条数据也要放到缓存中
            JedisOpExecuter.putSingleObject(dicType, dic);// 重新加载
        }
        sendJsonDataToClient(msg, response);
    }

    /**
     * 通过字典id(dicId)查询字典信息
     * 
     * @param response
     * @param dicId
     */
    @RequestMapping(params = "method=get")
    public void get(HttpServletResponse response, String dicId) {
        sendJsonDataToClient(dicService.get(dicId), response);
    }

    /**
     * 根据字典(dicId)进行字典信息修改
     * 
     * @param response
     * @param Dictionarys
     */
    @RequestMapping(params = "method=update")
    public void update(HttpServletResponse response, HttpServletRequest request, Dictionarys dictionary) {
        LoginUser user = (LoginUser) request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY);
        dictionary.setUpdateBy(user.getUserId());
        dictionary.setUpdateTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_mm_ss));
        Message msg = (Message) dicService.update(dictionary);
        //重新加载字典
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String, List<Map<String, String>>>> dics = dicService.getDicByTypes(params);
        request.setAttribute("dictionarys", JSONArray.fromObject(dics));
        // 刷新redis缓存
        String dicType = dictionary.getDicType();
        if (StringUtil.isNotNullStr(dicType)) {
            List<Map<String, String>> dic = dicService.getNameByType(dicType);
            JedisOpExecuter.removeSingleObject(dictionary.getDicId()); // 移除单条记录重新缓存
            JedisOpExecuter.putSingleObject(dictionary.getDicId(), dictionary.getDicName());
            JedisOpExecuter.removeSingleObject(dicType); // 移除缓存
            JedisOpExecuter.putSingleObject(dicType, dic);// 重新加载
        }
        sendJsonDataToClient(msg, response);
    }

    /**
     * 根据字典(dicId)逻辑删除字典
     * 
     * @param response
     * @param ids
     */
    @RequestMapping(params = "method=deleteById")
    public void deleteById(HttpServletResponse response, HttpServletRequest request, String ids) {
        Message msg = dicService.deleteById(ids);
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            JedisOpExecuter.removeSingleObject(idArr[i]); // 移除缓存
        }
        // 字典信息预加载(字典信息key暂时不加前缀)
        List<Map<String, String>> dics = dicService.getAllDics();
        Set<String> dicTypes = new HashSet<String>();
        for (Map<String, String> m : dics) {
            String key = String.valueOf(m.get("ID"));
            String value = m.get("NAME") == null ? "" : m.get("NAME");
            String type = m.get("TYPE") == null ? "" : m.get("TYPE");
            dicTypes.add(type);
            // JedisOpExecuter.removeSingleObject(key); //移除缓存
            JedisOpExecuter.putSingleObject(key, value);
        }
        for (String dicType : dicTypes) {
            List<Map<String, String>> dic = dicService.getNameByType(dicType);
            JedisOpExecuter.removeSingleObject(dicType); // 移除缓存
            JedisOpExecuter.putSingleObject(dicType, dic);// 重新刷新缓存
        }
        sendJsonDataToClient(msg, response);
    }
    
    @RequestMapping(params = "method=loadDicType")
    public void loadDicType(HttpServletResponse response){
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicType", "dic.dicType");
        List<Map<String,Object>> list = dicService.queryDicType(params);                      
        sendJsonDataToClient(JSONArray.fromObject(list).toString(), response);
    }
}