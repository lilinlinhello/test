package com.yunda.gps.smsTemplate.pojo;

import java.io.Serializable;

import com.yunda.app.entity.vo.PageQueryParams;

public class SMSTemplateVo extends PageQueryParams implements Serializable{
	private static final long serialVersionUID = 1L;
	/*短信模板Id**/
	private Integer smsId;
	/*短信类型  0-激活   1-查询**/
	private Integer smsType;
	/*设备品牌编码**/
	private String smsBrand;
	/*设备型号编码**/
	private String smsModel;
	/*短信序号**/
	private Integer smsOrder;
	/*短信内容**/
	private String smsContent;
	/*新增时间**/
	private String createTime;
	/*新增人**/
	private String createBy;
	/*更新时间**/
	private String updateTime;
	/*更新人**/
	private String updateBy;
	/*删除标志    0-未删除   1-已删除**/
	private Integer deleteFlag;
	
	/*新增人姓名**/
	private String createName;
	
	public Integer getSmsId() {
		return smsId;
	}
	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}
	public Integer getSmsType() {
		return smsType;
	}
	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}
	public String getSmsBrand() {
		return smsBrand;
	}
	public void setSmsBrand(String smsBrand) {
		this.smsBrand = smsBrand;
	}
	public String getSmsModel() {
		return smsModel;
	}
	public void setSmsModel(String smsModel) {
		this.smsModel = smsModel;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Integer getSmsOrder() {
		return smsOrder;
	}
	public void setSmsOrder(Integer smsOrder) {
		this.smsOrder = smsOrder;
	}
	

}
