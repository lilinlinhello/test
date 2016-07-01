package com.yunda.gps.common.fileUD.bean;

import java.io.Serializable;
import java.util.Date;

public class Documents  implements Serializable {
	private static final long serialVersionUID = 166858858L;
	
	private String id;
	private String objectId;
	private String documentName;
	private String documentType;
	private String documentAddress;
	private Date createTime;
	private String createBy;
	private Integer deleteFlag ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentAddress() {
		return documentAddress;
	}
	public void setDocumentAddress(String documentAddress) {
		this.documentAddress = documentAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
}
