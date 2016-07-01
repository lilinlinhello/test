<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>驾驶员管理页面</title>
</head>
<script type="text/javascript">
$(function(){
	loadCompany("corpId")
	//页面加载时初始化datagrid列表并加载列表数据进行显示
	loadDatagrid();
	/**查询按钮单击事件 */
	$("#search_button").bind("click",function(){
		if (!$("#searchForm").form('validate')) {
			$$.showJcdfMessager('提示消息','查询参数不合法','info');
			return false;
		}
		var params = $$.serializeToJson("#searchForm");
		$('#gpsDriverManagerDatagrid').datagrid('options').queryParams=params;
		$('#gpsDriverManagerDatagrid').datagrid('load');
	});
	
	/**清空按钮单击事件 */
	$("#clear_button").bind("click",function(){
		$$.resetContent("searchForm");
	});
	
	/**新增按钮单击事件 */
	$("#addButton").bind("click",function(){
		$$.openJcdfDialog(basePath+'/gpsDriverManager/addGpsDriverManager.do', ' ',
    			400,800);
	});
	
	/**编辑按钮单击事件 */
	$("#editButton").bind("click",function(){
		var selectRow = $$.getSingleSelectRow("gpsDriverManagerDatagrid", '请选择一条记录进行操作！');
		if (null != selectRow) {
			var id=selectRow.id;
			$$.openJcdfDialog(basePath+'/gpsDriverManager/editPage.do?id='+id,' ', 400, 800);
		}
	});
	
    /**删除按钮单击事件*/
    $("#deleteButton").bind("click", function(){
    	var deleteNotes = $$.getSelectIds("gpsDriverManagerDatagrid", "请选择你要删除的记录！", "id");
    	if(deleteNotes){
    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
    	}
    });
    
	// 导出
    $("#export").bind("click", function(){
    	var params = $$.stringify($$.serializeToJson("#searchForm")) ;
		$("#postForm")[0].action = basePath+'/gpsDriverManager/gpsDriverExport.do';
		$("#postForm")[0].param.value = params;
		$("#postForm")[0].submit() ;
    });
})

/**
*	删除所选记录
*/
function deleteNoteById(ids){
	$$.openProcessingDialog();
	$.post(basePath+"/gpsDriverManager/deleteGpsDriver.do",{"ids":ids},function(data){
		$$.closeProcessingDialog();
		if (data && data.result) {
			$$.showJcdfMessager('提示消息',data.msg,'info');
			$$.refreshJcdfDatagrid("gpsDriverManagerDatagrid");
		} else {
			$$.showJcdfMessager('提示消息',data.msg,'warning');
		}				
		$$.clearSelect("gpsDriverManagerDatagrid");
	},'json');
}

//加载公司数据
function loadCompany (id) {
var	url="retriveCorpName.do";
$("#"+id).combobox({
	panelHeight:"150",
	url:url,
	valueField: 'id',    
	textField: 'name',        	
	onLoadSuccess : function (data){
		//失去焦点触发事件，手动对combobox赋value值
		$("#"+id).next('.combo').find('input').blur(function(){
			var inputData = $("#"+id).combobox("getText");
			for(var i = 0 ;i < data.length ;i++){
				var tmp = data[i];
				if(tmp.name == inputData){
					$("#"+id).combobox("setValue",tmp.id);
					return;
				}
			}
			$("#"+id).combobox("clear");
		});
	},
});
}
function loadDatagrid(){
	$('#gpsDriverManagerDatagrid').datagrid({
		height:$$.getDatagridHeight(),
		width:$$.getDatagridWidth(),
		nowrap: true,
		striped: true,
		url:basePath+'/gpsDriverManager/driverManagerQuery.do',
		idField:'id',
		frozenColumns:[[{field:'ck',checkbox:true,align:'center'}]],
		columns:[[
			{field:'id',title:'ID',width:$$.fillsize(0.1),align:'center',hidden:'true'},
			{field:'userName',title:'姓名',width:$$.fillsize(0.1),align:'center'},
			{field:'carLicense',title:'车牌号',width:$$.fillsize(0.1),align:'center'},
			{field:'corpId',title:'所属公司',width:$$.fillsize(0.1),align:'center'},
			{field:'telephone',title:'联系电话',width:$$.fillsize(0.1),align:'center'},
			{field:'updateUser',title:'修改人',width:$$.fillsize(0.1),align:'center'},
			{field:'updateTime',title:'修改时间',width:$$.fillsize(0.15),align:'center'},
			{field:'createUser',title:'创建人',width:$$.fillsize(0.1),align:'center'},
			{field:'createTime',title:'创建时间',width:$$.fillsize(0.15),align:'center'}
		]],
		onBeforeLoad:function(){$$.clearSelect("gpsDriverManagerDatagrid");},
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		pageList:[10,15,20,30,50,100],
		pageSize:15,
		toolbar:'#menu'
	});
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
						<td>单位：</td>
						<td>
							<input id="corpId" name="corpId" type="text" data-options="valueField:'id',textField:'name'" class="easyui-combobox"   panelHeight="auto" prompt="请选择单位名称">
						</td>
						<td>姓名：</td>
						<td>
							<input id="userName" name="userName" style="width:120px" type="text" class="easyui-textbox" data-options="validType:['name']">
						</td>
						<td>车牌号码：</td>
						<td >
							<input id="carLicense" name="carLicense" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
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
			<jcdf:auth code="050101">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<jcdf:auth code="050102">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="050103">
				<a href="#" id="deleteButton" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true">删除</a>
			</jcdf:auth>
			<jcdf:auth code="050104">
				<a href="#" id="export" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
		</div>
	</div>
	<!-- 数据展示列表区 -->
	<table id="gpsDriverManagerDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post"
		style="display: inline;" target="downloadFormIframe">
		<input type="hidden" name="param" />
	</form>
</body>
</html>