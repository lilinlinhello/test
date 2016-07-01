package com.yunda.app.dao;

import com.yunda.app.base.BaseDao;
import com.yunda.app.entity.pojo.FileLoad;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.entity.vo.PageQueryParams;

public interface FileLoadDao  extends BaseDao<FileLoad>{
	Page pageQuery(Object queryParams, PageQueryParams pageQueryParams);
}
