package com.yunda.gps.vehicleCategory.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.app.entity.vo.Message;
import com.yunda.app.entity.vo.Page;
import com.yunda.gps.constant.Constant;
import com.yunda.gps.util.StringUtil;
import com.yunda.gps.vehicleCategory.bean.VehicleCategory;
import com.yunda.gps.vehicleCategory.bean.VehicleCategoryVo;
import com.yunda.gps.vehicleCategory.dao.VehicleCategoryDao;
import com.yunda.gps.vehicleCategory.mapper.VehicleCategoryMapper;

@Service("vehicleCategoryServiceImpl")
@Transactional
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

	@Resource(name="vehicleCategoryDaoImpl")
	private VehicleCategoryDao vehicleCategoryDao;
	
	@Autowired
	private VehicleCategoryMapper vehicleCategoryMapper;
	
	
	@Override
	public Message insert(VehicleCategoryVo vehicle) {
		Message msg = null;
		VehicleCategoryVo param = new VehicleCategoryVo();
		param.setName(vehicle.getName());
		param.setParentCode(vehicle.getParentCode());
		param.setDeleteFlag(Constant.NO_DELETE_FLAG);
		
		List<VehicleCategoryVo> list = vehicleCategoryMapper.selectSelective(param);
		if(list.size() > 0){//数据重复判断
			msg = new Message(false,vehicle.getName()+"数据已存在");
		}else{
			//查询数据库是否存在被删除过的数据
			//存在则恢复，否则新增
			param.setDeleteFlag(1);
			List<VehicleCategoryVo> existsList = vehicleCategoryMapper.selectSelective(param);
			if(existsList.size() == 1){
//				vehicleCategoryMapper.backByID(existsList.get(0).getChildCode());
				vehicle.setChildCode(existsList.get(0).getChildCode());
				vehicleCategoryMapper.updateByPrimaryKeySelective(vehicle);
			}else{
				vehicleCategoryMapper.insert(vehicle);
			}
			msg = new Message(true,"数据添加成功！");
		}
		
		return msg;
	}
	
	@Override
	public Page pageQuery(VehicleCategoryVo vo) {
		Page page = vehicleCategoryDao.pageQuery(vo);
		
		//从三个编码中选出最低级的作为主id 主删除标志
				@SuppressWarnings("unchecked")
		List<VehicleCategoryVo> vehicleList = (List<VehicleCategoryVo>) page.getRows();
		for(VehicleCategoryVo vehicle : vehicleList){
			if(vehicle.getThirdCode() != null){
				vehicle.setId(vehicle.getThirdCode());
			}else if(vehicle.getSecondCode() != null){
				vehicle.setId(vehicle.getSecondCode());
			}else if(vehicle.getTopCode() != null){
				vehicle.setId(vehicle.getTopCode());
			}
		}
		return null == page ? new Page() : page;
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
					vehicleCategoryMapper.deleteByID(id); 
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
	public Message backByID(String ids) {
		String message = null;
		int successNum = 0;
		int totleNum = 0;
		if (null != ids) {
			String[] idsArr = ids.split(",");
			totleNum = idsArr.length;
			for (String id : idsArr) {
				try {
					vehicleCategoryMapper.backByID(id); 
					successNum = successNum + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		if (0 == successNum) {
			message = "恢复失败！";
		} else if (totleNum == successNum) {
			message = "成功恢复"+successNum+"条记录！";
		} else if (successNum < totleNum) {
			message = "成功恢复"+(successNum)+"条记录，失败恢复"+(totleNum-successNum)+"条记录！" ;
		}
		return new Message(successNum > 0, message);
		
	}
	
	/**
	 * 子节点childCode生成
	 * 顶级code生成规则为两位数字初始为01，次顶级编码生成为3位数字初始为001，三级code编码也为三位数字初始为001
	 * 组合在一起标示一条唯一记录01000000，不同层次编码依次累加
	 * @author guoduan
	 * @param 模块类别 modelType 父节点编码parentCode = 新增最顶级设为0 根据选择级别在页面传递过来
	 * @return 生成的childCode
	 */
	public String createCode(VehicleCategoryVo record){ //数据库child编码格式 ,字母：C01001001
		String type = record.getType(); //0 顶级维护   1 次顶级 维护 2  三级维护
		String createChildCode="";
		String topCode="00";
		String childCode1 ="000";
		String childCode2 ="000";
		String paramParentCode = record.getParentCode();//获取穿过阿里的父节点，判断编码递增位置 字母：C01001001
		List<VehicleCategory> list = vehicleCategoryMapper.selectBy(record);
		if(list !=null && list.size()==0 && StringUtil.isNotNullStr(paramParentCode)){
			if(StringUtil.isNotNullStr(type) && Constant.TYPE_00.equals(type)){
				return "01000000";
			}
			if(StringUtil.isNotNullStr(type) && Constant.TYPE_01.equals(type)){
				topCode = paramParentCode.substring(1,3);
				return topCode+"001000";
			}
			if(StringUtil.isNotNullStr(type) && Constant.TYPE_02.equals(type)){
				topCode = paramParentCode.substring(1,3);
				childCode1 = paramParentCode.substring(3,6); //截取次顶级3位编码
				return topCode+childCode1+"001";
			}
		}
		if(list !=null && list.size()>0){
			VehicleCategory vc = list.get(0); //获取childCode编码最大的对象（sql排序实现）
			String childCode = vc.getChildCode(); //获取最大child编码  字母：C01001001
			if(StringUtil.isNotNullStr(childCode) && childCode.length()==9){ //数据库9位child编码
				//顶级编码生成
				if(StringUtil.isNotNullStr(type) && Constant.TYPE_00.equals(type)){
					topCode = childCode.substring(1,3); //截取前两位编码
					Integer pCode = Integer.parseInt(topCode);
					pCode++;
					if(pCode>=0 && pCode<10){
						topCode = "0"+ pCode;	
					}
					if(pCode>=10 && pCode<100 ){
						topCode = pCode.toString();
					}
					createChildCode = topCode+"000000";
				}else{
					//次顶级编码生成
					if(StringUtil.isNotNullStr(type) && Constant.TYPE_01.equals(type)  ){
						topCode = paramParentCode.substring(1,3);
						childCode1 = childCode.substring(3,6); //截取次顶级3位编码
						Integer cCode1 = Integer.parseInt(childCode1);
						cCode1++;
						if(cCode1>=0 && cCode1<10){
							childCode1 = "00"+ cCode1.toString();	
						}
						if(cCode1>=10 && cCode1<100 ){
							childCode1 = "0"+cCode1.toString();
						}
						if(cCode1>=100 && cCode1<1000 ){
							childCode1 = cCode1.toString();
						}
						createChildCode = topCode + childCode1+"000";
					}
					//编码生成
					if(StringUtil.isNotNullStr(type) && Constant.TYPE_02.equals(type)){
						topCode = paramParentCode.substring(1,3);
						childCode1 = paramParentCode.substring(3,6); //前五位保持和父级一致 
						childCode2 = childCode.substring(6,9); //截取后三位编码
						Integer cCode2 = Integer.parseInt(childCode2);
						cCode2++;
						if(cCode2>=0 && cCode2<10){
							childCode2 = "00"+ cCode2.toString();	
						}
						if(cCode2>=10 && cCode2<100 ){
							childCode2 = "0"+cCode2.toString();
						}
						if(cCode2>=100 && cCode2<1000 ){
							childCode2 = cCode2.toString();
						}
						createChildCode = topCode + childCode1+childCode2;
					}
				}
			}
		}
		return createChildCode;
	}

	@Override
	public List<Map<String, Object>> getVeicleInfo(VehicleCategoryVo vo) {
		List<Map<String, Object>> list = vehicleCategoryMapper.select(vo);
		return list;
	}

	/**
	 * 查出单车次的信息
	 * @param id 
	 * @return
	 */
	@Override
	public VehicleCategoryVo selectById(String id) {
		VehicleCategoryVo vehicle = vehicleCategoryMapper.selectById(id);
		return vehicle;
	}

	@Override
	public Message update(VehicleCategoryVo vo) {
		Message msg = null;
		VehicleCategoryVo param = new VehicleCategoryVo();
		param.setName(vo.getName());
		param.setParentCode(vo.getParentCode());
		
		List<VehicleCategoryVo> list = vehicleCategoryMapper.selectSelective(param);
		if(list.size() > 0 && !list.get(0).getChildCode() .equals(vo.getChildCode())){
			msg = new Message(false,vo.getName()+"数据已存在");
		}else{
			vehicleCategoryMapper.updateByPrimaryKeySelective(vo);
			msg = new Message(true,"数据修改成功！");
		}
		
		return msg;
	}



}
