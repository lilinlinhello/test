package com.yunda.gps.vehicleMaintain.pojo;

import java.io.Serializable;
import java.util.Date;

import com.yunda.app.entity.vo.PageQueryParams;

public class VehicleMaintain extends PageQueryParams implements Serializable{
	
	private static final long serialVersionUID = -312559372024556117L;
	//主键,自动生成
    private String id;
    //车辆ID
    private String vehicleId;
    //车牌号
    private String licensecard;
    //车载设备(设备编码)
    private String equipId;
    //设备型号
    private String equipType;
    //设备品牌
    private String equipBrand;
    //SIM卡电话号码
    private String simPhoneno;
    //SIM卡ID
    private String simId;
    //自编车牌号
    private String autoLicensecard;
    //是否显示自编车牌号   0 否   1 是
    private String isShowAutoLicensecard;
    //3G卡号
    private String card3g;
    //DVR编号
    private String dvrNumber;
    //摄录一体机容量   8G、16G、32G
    private String camcorderMemory;
    //运营属性
    private String operAttr;
    //公司ID(单位)
    private String corpId;
    //车辆分类
    private String typeId;
    //常通电状态   0 否    1 是
    private String longtimeElectric;
    //接ACC  0 否   1 是
    private String acc;
    //安装人
    private String installUser;
    //安装日期
    private Date installDate;
    //是否安装防盗门      0 否     1 是
    private String isGuard;
    //防盗短信接收码 
    private String guardSendno;
    //是否发送防盗短信    0 否   1是
    private String isSendMessage;
    //检测线路配置方案
    private String checkLineType;
    //车辆图标
    private String carPicture;
    //状态      0 未安装   1 未开通   2 准备开通   3 已开通   4 停止使用
    private String status;
    //开关门状态
    private String openfixType;
    //状态备注
    private String statusRemark;
    //备注
    private String remark;
    //是否安装摄像头    0 否     1 是
    private String isFixcamera;
    //是否通过3g设备拍照      0 否     1 是
    private String isDvr3gcamera;
    //是否是临时车     0 非临时车     1 临时车
    private String isTemp;
    //删除状态     0 未删除      1 已删除
    private String deleteFlag;
    //更新人
    private String updateBy;
    //更新时间
    private String updateTime;
    //创建人
    private String createBy;
    //创建时间
    private String createTime;
    //车牌号登陆密码
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId == null ? null : vehicleId.trim();
    }

    public String getLicensecard() {
        return licensecard;
    }

    public void setLicensecard(String licensecard) {
        this.licensecard = licensecard == null ? null : licensecard.trim();
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId == null ? null : equipId.trim();
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType == null ? null : equipType.trim();
    }

    public String getSimPhoneno() {
        return simPhoneno;
    }

    public void setSimPhoneno(String simPhoneno) {
        this.simPhoneno = simPhoneno == null ? null : simPhoneno.trim();
    }

    public String getAutoLicensecard() {
        return autoLicensecard;
    }

    public void setAutoLicensecard(String autoLicensecard) {
        this.autoLicensecard = autoLicensecard == null ? null : autoLicensecard.trim();
    }

    public String getCard3g() {
        return card3g;
    }

    public void setCard3g(String card3g) {
        this.card3g = card3g == null ? null : card3g.trim();
    }

    public String getDvrNumber() {
        return dvrNumber;
    }

    public void setDvrNumber(String dvrNumber) {
        this.dvrNumber = dvrNumber == null ? null : dvrNumber.trim();
    }

    public String getCamcorderMemory() {
        return camcorderMemory;
    }

    public void setCamcorderMemory(String camcorderMemory) {
        this.camcorderMemory = camcorderMemory == null ? null : camcorderMemory.trim();
    }

    public String getOperAttr() {
        return operAttr;
    }

    public void setOperAttr(String operAttr) {
        this.operAttr = operAttr == null ? null : operAttr.trim();
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }


    public String getInstallUser() {
        return installUser;
    }

    public void setInstallUser(String installUser) {
        this.installUser = installUser == null ? null : installUser.trim();
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getGuardSendno() {
        return guardSendno;
    }

    public void setGuardSendno(String guardSendno) {
        this.guardSendno = guardSendno == null ? null : guardSendno.trim();
    }

    public String getCheckLineType() {
        return checkLineType;
    }

    public void setCheckLineType(String checkLineType) {
        this.checkLineType = checkLineType == null ? null : checkLineType.trim();
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture == null ? null : carPicture.trim();
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpenfixType() {
        return openfixType;
    }
    
    public void setOpenfixType(String openfixType) {
        this.openfixType = openfixType == null ? null : openfixType.trim();
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark == null ? null : statusRemark.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }



    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }


    public String getPassword() {
        return password;
    }

    public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public String getEquipBrand() {
		return equipBrand;
	}

	public void setEquipBrand(String equipBrand) {
		this.equipBrand = equipBrand;
	}

	public String getIsShowAutoLicensecard() {
		return isShowAutoLicensecard;
	}

	public void setIsShowAutoLicensecard(String isShowAutoLicensecard) {
		this.isShowAutoLicensecard = isShowAutoLicensecard;
	}

	public String getLongtimeElectric() {
		return longtimeElectric;
	}

	public void setLongtimeElectric(String longtimeElectric) {
		this.longtimeElectric = longtimeElectric;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getIsGuard() {
		return isGuard;
	}

	public void setIsGuard(String isGuard) {
		this.isGuard = isGuard;
	}

	public String getIsSendMessage() {
		return isSendMessage;
	}

	public void setIsSendMessage(String isSendMessage) {
		this.isSendMessage = isSendMessage;
	}

	public String getIsFixcamera() {
		return isFixcamera;
	}

	public void setIsFixcamera(String isFixcamera) {
		this.isFixcamera = isFixcamera;
	}

	public String getIsDvr3gcamera() {
		return isDvr3gcamera;
	}

	public void setIsDvr3gcamera(String isDvr3gcamera) {
		this.isDvr3gcamera = isDvr3gcamera;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	
    
    
}