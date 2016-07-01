package com.yunda.gps.fixUsers.service;

import java.util.List;
import java.util.Map;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.fixUsers.data.FixUsers;


public interface FixUserService {

	Page pageQuery(FixUsers vo);

	Message deleteByID(String ids);

	Message insert(FixUsers vo);

	FixUsers getFixUserById(String id);

	Message update(FixUsers vo);

	List<Map<String, Object>> getUserMap();

}
