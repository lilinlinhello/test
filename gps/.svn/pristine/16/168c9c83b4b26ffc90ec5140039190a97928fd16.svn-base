package com.yunda.gps.fixUsers.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.fixUsers.dao.FixUsersDao;
import com.yunda.gps.fixUsers.data.FixUsers;
import com.yunda.gps.fixUsers.mapper.FixUsersMapper;
import com.yunda.gps.fixUsers.service.FixUserService;

@Service(value="fixUsersServiceImpl")
public class FixUsersServiceImpl implements FixUserService {

	@Resource(name="fixUserDaoImpl")
	private FixUsersDao fixUsersDao;
	
	@Autowired
	private FixUsersMapper fixMapper;
	
	@Override
	public Page pageQuery(FixUsers vo) {
		return fixUsersDao.pageQuery(vo);
	}

	@Override
	public Message deleteByID(String ids) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		if (null != ids) {
			String[] idsArr = ids.split(",");
			totleNum = idsArr.length;
			for (String id : idsArr) {
				try {
					fixMapper.deleteByPrimaryKey(Integer.parseInt(id)); 
					successNum = successNum + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		if (0 == successNum) {
			message = "删除失败！";
		} else if (totleNum == successNum) {
			message = "成功删除"+successNum+"条记录！";
		} else if (successNum < totleNum) {
			message = "成功删除"+(successNum)+"条记录，失败删除"+(totleNum-successNum)+"条记录！" ;
		}
		return new Message(successNum > 0, message);
	}

	@Override
	public Message insert(FixUsers vo) {
		int i = fixMapper.insert(vo);
		if(i == 1){
			return new Message(true,"数据新增成功");
		}
		return new Message(false,"数据新增失败");
	}

	@Override
	public FixUsers getFixUserById(String id) {
		return fixMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public Message update(FixUsers vo) {
		int i = fixMapper.updateByPrimaryKeySelective(vo);
		if(i == 1){
			return new Message(true,"数据修改成功");
		}
		return new Message(false,"数据修改失败");
	}

	@Override
	public List<Map<String, Object>> getUserMap() {
		
		return fixMapper.getUserMap();
	}

}
