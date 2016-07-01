<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<!-- 导入框架css和js库 -->
		<jsp:include page="head-ui-134.jsp"></jsp:include>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/MD5+BASE64Encoder.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jcdf-index-1.0.js"></script>
	</head>
	<body class="easyui-layout">
		<div class="autoDefined" data-options="region:'north',border:false" style="height: 60px; padding: 3px; font-size: 20px;overflow:hidden;">
			<div class="logo">
			<a href="#"><img height="56px"  src="${pageContext.request.contextPath}/images/logoIn.png" alt="韵达GPS系统LOGO"></a>			
			</div>
			<div style="position: absolute; right: 10px;top:20px">
				<div style="float:left;">${loginUser.userName}<span style="padding-left:5px;padding-right:5px;">|</span></div>
				<div id="passwordId" style="float:left;"><a id="changePass" href="#" >修改密码 </a><span style="padding-left:5px;padding-right:5px;">|</span></div>
				<a id="logout" href="#" style="padding-right:10px;">退出</a>
			</div>
		</div>

		<!-- -------------------系统功能菜单展示区-------------------------------------- -->
		<div data-options="region:'west',split:true,title:'系统菜单'" style="width: 200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<jcdf:auth code="010000">
					<div title="系统管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="010100">
								<li>
									<a id="yhgl" href="#" link="auth/user.do?method=view" onclick="openNewTab('yhgl')">用户管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="010200">
								<li>
									<a id="jsgl" href="#" link="auth/role.do" onclick="openNewTab('jsgl')">角色管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="010300">
								<li>
									<a id="zygl" href="#" link="auth/resource.do" onclick="openNewTab('zygl')">资源管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="010400">
								<li>
									<a id="zdgl" href="#" link="auth/dictionary.do" onclick="openNewTab('zdgl')">字典管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="010500">
								<li>
									<a id="rzgl" href="#" link="sys/syslog.do" onclick="openNewTab('rzgl')">日志管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="010600">
								<li>
									<a id="xxdlgl" href="#" link="auth/mq.do" onclick="openNewTab('xxdlgl')">消息队列管理</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
				<jcdf:auth code="020000">
					<div title="GPS设备维护管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="020100">
								<li>
									<a id="ger" href="#" link="gpsEquipRegister/equipRegister.do" onclick="openNewTab('ger')">GPS设备维护登记</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
				<jcdf:auth code="030000">
					<div title="车辆安装管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="030100">
								<li>
									<a id="clanwh" href="#" link="vehicleMaintain/vehicleMaintain.do" onclick="openNewTab('clanwh')">车辆安装维护</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>								
				<jcdf:auth code="040000">
					<div title="用户字典管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="040100">
								<li>
									<a id="ppsbccflwh" href="#" link="equipmentBrand/listPage.do" onclick="openNewTab('ppsbccflwh')">品牌设备分类维护</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="040200">
								<li>
									<a id="clccflwh" href="#" link="vehicleCategory/listPage.do" onclick="openNewTab('clccflwh')">车辆层次分类维护</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="040300">
								<li>
									<a id="azrxxwh" href="#" link="fixUsers/listPage.do" onclick="openNewTab('azrxxwh')">安装人信息维护</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="040400">
								<li>
									<a id="gpsdxmb" href="#" link="smsTemplate/listPage.do" onclick="openNewTab('gpsdxmb')">GPS短信模板</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>		
				<jcdf:auth code="050000">
					<div title="驾驶员信息管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="050100">
								<li>
									<a id="jsyxxwh" href="#" link="gpsDriverManager/DriverManager.do" onclick="openNewTab('jsyxxwh')">驾驶员信息维护</a>
								</li>
							</jcdf:auth>
							
						</ul>
					</div>
				</jcdf:auth>	
				<jcdf:auth code="060000">
					<div title="监控管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="060100">
								<li>
									<a id="cpjkgl" href="#" link="vehicleRecord/vehicleRecordManager.do" onclick="openNewTab('cpjkgl')">车牌监控管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060200">
								<li>
									<a id="bjjl" href="#" link="alarmLogging/listPage.do" onclick="openNewTab('bjjl')">报警记录</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060300">
								<li>
									<a id="xllc" href="#" link="lineMileage/listPage.do" onclick="openNewTab('xllc')">线路里程</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060400">
								<li>
									<a id="hydjk" href="#" link="shipmentMonitor/listPage.do" onclick="openNewTab('hydjk')">货运单监控</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060500">
								<li>
									<a id="hydxx" href="#" link="shipment/listPage.do" onclick="openNewTab('hydxx')">货运单信息</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060600">
								<li>
									<a id="hydjkyc" href="#" link="shipmentMonitorError/listPage.do" onclick="openNewTab('hydjkyc')">货运单监控异常信息</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="060700">
								<li>
									<a id="yxlcxx" href="#" link="runMileage/listPage.do" onclick="openNewTab('yxlcxx')">运行里程信息</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>	
				<jcdf:auth code="070000">
					<div title="接口管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="070100">
								<li>
									<a id="yyzc" href="#" link="appReg/appReg.do" onclick="openNewTab('yyzc')">应用注册</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
				<jcdf:auth code="080000">
					<div title="报警参数管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="080100">
								<li>
									<a id="bjcshgl" href="#" link="alarmParam/listPage.do" onclick="openNewTab('bjcshgl')">报警参数管理</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
			</div>
		</div>
		

		<!-- ---------------------首页页脚版本信息展示区------------------------------------- -->
		<div class="autoDefined" data-options="region:'south',border:false"
			style="height: 30px;  padding: 5px; text-align: center; font-size: 16px;overflow:hidden;">
			Copyright©&nbsp;上海东普信息科技有限公司&nbsp;&nbsp;Versions：2016-02-10(V1.0)
		</div>

		<!-- ---------------------首页中间内容展示区------------------------------------- -->
		<div data-options="region:'center'">
			<div id="centerTab" class="easyui-tabs" fit="true" border="false" plain="true">
				<div id="welcomeTab" title="welcome" href="welcome.jsp"></div>
			</div>
		</div>
       		<!-- 密码修改窗口页面 -->
		<div id="changePassDialog" style="display: none;">
			<form action="#" id="changePassForm" style="display: inline;"  class="tab">
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="bule" align="right" width="35%">
							原密码：
						</td>
						<td align="left">
							<input style="width:200px;" type="password" name="oldPass" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]']" />
						</td>
					</tr>
					<tr>
						<td class="bule" align="right">
							新密码：
						</td>
						<td align="left">
							<input style="width:200px;" id="newPass" type="password" name="newPass" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]']" />
						</td>
					</tr>
					<tr>
						<td class="bule" align="right">
							新密码确认：
						</td>
						<td align="left">
							<input style="width:200px;" type="password" name="confirmNewPass" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]','same[\'newPass\']']" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	</body>
</html>
