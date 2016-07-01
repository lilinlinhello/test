package com.yunda.gps.constant;

public class Constant {
	public static final Integer USER_STOP_STATE = 0; // 用户停用状态
	public static final Integer USER_ACTIVE_STATE = 1; // 用户激活状态
	public static final Integer NO_DELETE_FLAG = 0; // 未删除状态
	public static final String DELETE_FLAG = "1"; // 已删除状态
	public static final String USERTYPE_SUPER01 = "dic_userType01"; // 超级管理员
	public static final String USERTYPE_02 = "dic_userType02"; // 安装人
	public static final String USERTYPE_03 = "dic_userType03"; // 普通用户
	public static final String USERFROM_SOA = "dic_userFrom01"; // 来自soa用户
	public static final int SENDMESSAGE_COUNTER_SEND = 50;// 一条信息可发送的手机号码数量(默认50)
	
	/**
	 * redis保存用户资源权限url的key前缀
	 * 用于缓存登录用户权限
	 */
	public static String REDIS_MENU_URL_KEY_PREFIX = "menu_url_";
	
	public static final String TYPE_00 ="0"; //顶级编码
	public static final String TYPE_01 ="1"; //次顶级编码
	public static final String TYPE_02 ="2"; //三级编码
	
	/**
	 * 车牌号码对应省市编码转换
	 */
	//省编码
	public static String []    prov_Code={"11","12","13","14","15","21","22","23","31","32","33","34","35","36","37","41","42","43","44","45","46","50","51","52","53","54","61","62","63","64","65"};
	//省
	public static String []    prov_Name={"京","津","冀","晋","蒙","辽","吉","黑","沪","苏","浙","皖","闽","赣","鲁","豫","鄂","湘","粤","桂","琼","渝","川","贵","云","藏","陕","甘","青","宁","新"};
    
	//石墨同步gps车辆数字签名
	public static String   SIGN = "shimo_gps2016";
	//接口标识车辆来源
	public static String FROM = "shimo";
	//接口新增，修改，删除状态
	public static String INTERFACE_ADD ="0"; //新增
	public static String INTERFACE_UPDATE ="1";//修改
	public static String INTERFACE_DEL ="2";  //删除
	
	//车辆记录表分表参数设定
	public static final Integer MAX_CAR_RECORD = 1000; //最大车辆记录数
	
    public static final Double DISCOUNT_PARAM = 0.9; //车辆记录冗余系数
    
    public static final String TABLE_NAME = "part";//自动创建表名称
    
    public static final short DISTANCE = 0;  //报警参数偏移距离默认值
	public static final short COLLECTION = 0; //报警参数GPS点数默认值
	public static final short SPEED = 0;
	
}
