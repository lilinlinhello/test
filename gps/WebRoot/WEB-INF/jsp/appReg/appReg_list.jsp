<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>应用注册页面</title>
</head>
<script type="text/javascript">
$(function(){
	//页面加载时初始化datagrid列表并加载列表数据进行显示
	loadDatagrid();
	
	/**查询按钮单击事件 */
	$("#search_button").bind("click",function(){
		if (!$("#searchForm").form('validate')) {
			$$.showJcdfMessager('提示消息','查询参数不合法','info');
			return false;
		}
		var params = $$.serializeToJson("#searchForm");
		$('#appRegDatagrid').datagrid('options').queryParams=params;
		$('#appRegDatagrid').datagrid('load');
	});
	
	/**清空按钮单击事件 */
	$("#clear_button").bind("click",function(){
		$$.resetContent("searchForm");
		$("#appType").combobox('clear');
	});
	
	/**新增按钮单击事件 */
	$("#addButton").bind("click",function(){
		$$.openJcdfDialog(basePath+'/appReg/appReg.do?method=forwardAddJsp', '新增应用',
    			330,620);
	});
	
	/**编辑按钮单击事件 */
	$("#editButton").bind("click",function(){
		var selectRow = $$.getSingleSelectRow("appRegDatagrid", '请选择一条记录进行操作！');
		if (null != selectRow) {
			$$.openJcdfDialog(basePath+'/appReg/appReg.do?method=forwardEditJsp&ak='+selectRow.ak,'编辑应用', 330, 620);
		}
	});
	
	 //停用按钮单击事件
    $("#deleteButton").bind("click", function(){
    	var deleteNotes = $$.getSelectIds("appRegDatagrid", "请选择你要停用的应用！", "regId");
    	if(deleteNotes){
    		$$.showJcdfConfirm("确认", "确定停用所选应用?", 'deleteNoteById("'+deleteNotes.ids+'")');
    	}
    });
	 
    // 导出
	$("#exportButton").bind("click",function(){
		var params = $$.serializeToJson("#searchForm");
		$("#exportForm").form('options').queryParams=params;
		$("#exportForm").submit();
	});
})


function loadDatagrid(){
	$('#appRegDatagrid').datagrid({
		height:$$.getDatagridHeight(),
		width:$$.getDatagridWidth(),
		nowrap: true,
		striped: true,
		url:basePath+'/appReg/appReg.do?method=pageQuery',
		idField:'regId',
		frozenColumns:[[{field:'ck',checkbox:true,align:'center'}]],
		columns:[[
			{field:'regId',title:'ID',width:$$.fillsize(0.07),align:'center'},
			{field:'appName',title:'应用名称',width:$$.fillsize(0.07),align:'center'},
			{field:'contMan',title:'联系人',width:$$.fillsize(0.07),align:'center'},
			{field:'department',title:'所属部门',width:$$.fillsize(0.07),align:'center'},
			{field:'phoneNo',title:'联系电话',width:$$.fillsize(0.07),align:'center'},
			{field:'bqq',title:'BQQ',width:$$.fillsize(0.07),align:'center'},
			{field:'ak',title:'授权码',width:$$.fillsize(0.07),align:'center'},
			{field:'ipWhiteList',title:'白名单',width:$$.fillsize(0.07),align:'center'},
			{field:'appType',title:'应用类型',width:$$.fillsize(0.07),align:'center',formatter:function(value,row,index){
						if(0==value){
							return "服务端";
						}else{
							return "浏览器端";
						}
						}},
			{field:'delFlag',title:'是否启用',width:$$.fillsize(0.07),align:'center',formatter:function(value,row,index){
						if(0==value){
							return "<font color='green'>启用</font>";
						}else{
							return "<font color='red'>停用</font>";
						}
						}},
			{field:'updateBy',title:'修改人',width:$$.fillsize(0.07),align:'center'},
			{field:'updateTime',title:'修改时间',width:$$.fillsize(0.07),align:'center'},
			{field:'createBy',title:'创建人',width:$$.fillsize(0.07),align:'center'},
			{field:'createTime',title:'创建时间',width:$$.fillsize(0.07),align:'center'}
		]],
		onBeforeLoad:function(){$$.clearSelect("appRegDatagrid");},
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		pageList:[10,15,20,30,50,100],
		pageSize:15,
		toolbar:'#menu'
	});
	$("#appType").combobox('clear');
}
/**
 *	停用所选应用
 */
function deleteNoteById(ids){
	$$.openProcessingDialog();
	$.post(basePath+'/appReg/appReg.do?method=deleteById',{"regIds":ids},function(data){
		$$.closeProcessingDialog();
		if (null != data && data.result) {
			$$.showJcdfMessager('提示消息',data.msg,'info');	
			$$.refreshJcdfDatagrid("appRegDatagrid");
		} else {
			$$.showJcdfMessager('提示消息',data.msg,'warning');
		}
		$$.clearSelect("appRegDatagrid");
	},'json');
}
/**
 *	启用所选应用
 */
function useNoteById(ids){
	$$.openProcessingDialog();
	$.post(basePath+'/appReg/appReg.do?method=useById',{"regIds":ids},function(data){
		$$.closeProcessingDialog();
		if (null != data && data.result) {
			$$.showJcdfMessager('提示消息',data.msg,'info');	
			$$.refreshJcdfDatagrid("appRegDatagrid");
		} else {
			$$.showJcdfMessager('提示消息',data.msg,'warning');
		}
		$$.clearSelect("appRegDatagrid");
	},'json');
}
</script>
<body>
	<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div id="searchPanel" title=" " class="easyui-panel"
			style="width: 100%;" data-options="collapsible:true,border:0">
			<form action="#" name="searchForm" id="searchForm"
				style="display: inline;">
				<table class="searchArea">
					<tr>
						<td>应用类型：</td>
						<td>
						    <select id="appType" name="appType"   class="easyui-combobox" 
					                style="width:100px;" panelHeight="auto" >
					                      <option value = "0">服务端</option>
						                  <option value = "1">浏览器端</option>
						    </select>  
						</td>
						<td>应用ID ：</td>
						<td>
							<input id="regId" name="regId" style="width:120px" type="text" class="easyui-numberbox" >
						</td>
						<td>应用名称 ：</td>
						<td>
							<input id="appName" name="appName" style="width:120px" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
						</td>
						<td>授权码 ：</td>
						<td >
							<input id="ak" name="ak" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
						</td>
				</table>
			 	<table class="searchMenu">
			 		<tr>
				  	<td id="icon-button" >
				  		<a id="search_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				  		&nbsp;&nbsp;&nbsp;&nbsp;
				  		<a id="clear_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
				  	</td>
				  </tr>
			 	</table>
			</form>
		</div>
		<div class="menu-area">
			<jcdf:auth code="070101">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<jcdf:auth code="070102">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="070103">
				<a href="#" id="useButton" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true">启用</a>
			</jcdf:auth>
			<jcdf:auth code="070104">
				<a href="#" id="deleteButton" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true">停用</a>
			</jcdf:auth>
			<jcdf:auth code="070105">
				<a href="#" id="exportButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
		</div>
	</div>
	<form id="exportForm" action="appReg.do?method=exportExcel" method="post" type="hidden"></form>
	<!-- 数据展示列表区 -->
	<table id="appRegDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post"
		style="display: inline;" target="downloadFormIframe">
		<input type="hidden" name="param" />
	</form>
</body>
</html>