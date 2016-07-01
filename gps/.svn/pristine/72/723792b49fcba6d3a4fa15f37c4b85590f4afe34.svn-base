package com.yunda.app.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunda.app.entity.pojo.FileLoad;
import com.yunda.app.entity.vo.Page;
import com.yunda.app.entity.vo.PageQueryParams;
import com.yunda.app.mapper.FileLoadMapper;


@Repository(value="fileloadDao")
public class FileLoadDaoImpl extends SqlSessionDaoSupport implements FileLoadDao{

	@Autowired
	private FileLoadMapper fileloadMapper;
	
	
	public Page pageQuery(Object queryParams, PageQueryParams pageQueryParams) {
		List list = this.getSqlSession().selectList("com.yunda.app.mapper.FileLoadMapper.pageQuery", queryParams, new RowBounds(0,pageQueryParams.getPageSize()));
		return new Page(list);
	}
	
	public List<FileLoad> list() {
		return fileloadMapper.listAll();
	}

	
	public FileLoad get(String id) {
		return fileloadMapper.selectByPrimaryKey(id);
	}

	
	public void insert(FileLoad t) {
		fileloadMapper.insert(t);
	}

	
	public void update(FileLoad t) {
		fileloadMapper.updateByPrimaryKeySelective(t);
	}

	
	public void deleteById(String id) {
		fileloadMapper.deleteByPrimaryKey(id);
	}

}
