package com.yunda.app.service;

import com.yunda.app.entity.pojo.AppReg;
import com.yunda.app.entity.vo.Page;

public interface AppRegService {
	Page pageQuery(AppReg appReg);

	AppReg get(String id);

	Object insert(AppReg appReg);

	Object update(AppReg appReg);

	Object deleteById(String regIds, String updateBy);

	Object useById(String regIds, String updateBy);
}
