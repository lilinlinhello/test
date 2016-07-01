package com.yunda.gps.fixUsers.dao;

import com.yunda.app.entity.vo.Page;
import com.yunda.gps.fixUsers.data.FixUsers;

public interface FixUsersDao {

	Page pageQuery(FixUsers vo);

}
