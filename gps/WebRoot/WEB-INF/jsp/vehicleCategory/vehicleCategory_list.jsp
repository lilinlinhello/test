<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		//页面加载时初始化datagrid列表并加载列表数据进行显示
		loadDatagrid();

		$("#tipButton").tooltip({
			position : 'right',
			content : '<span>1、车辆类型为空时，你将删除运营属性。<br/>2、如果运营属性也为空，你将删除整个公司。</span>',
		});

		$("#clear_button").bind("click", function() {
			$("#searchForm").form("reset");
		});

		$("#search_button").bind("click", function() {
			if (!$("#searchForm").form('validate')) {
				$$.showJcdfMessager('提示消息','查询参数不合法','info');
				return false;
			}
			var params = $$.serializeToJson("#searchForm");
			$('#vehicleDatagrid').datagrid('options').queryParams = params;
			$('#vehicleDatagrid').datagrid('reload');
		});

		$("#addButton").bind("click",function(){
		    $$.openJcdfDialog(basePath+'/vehicleCategory/addPage.do', ' ',600, 900);
		});

		$("#editButton").bind("click",function(){
			var selectRow = $$.getSingleSelectRow("vehicleDatagrid", '请选择一条记录进行操作！');
			
			if (null != selectRow) {
				if(selectRow.useState == "已废弃"){
					$$.showJcdfMessager('提示消息',"数据已废弃！！！",'warning');
					return;
				}
				var opType;
				if(selectRow.thirdName != ''){
					opType = "2";
				}else if(selectRow.secondName != ""){
					opType = "1";
				}else{
					opType = "0";
				}
				$$.openJcdfDialog(basePath+'/vehicleCategory/editPage.do?child_code='+selectRow.id+'&opType='+opType,' ', 400, 700);
			}
		});

		$("#deleteButton").bind("click",function(){
			var rows = $("#vehicleDatagrid").datagrid("getSelections");
			var ids = [];
			if(rows.length == 0){
				$$.showJcdfMessager('提示消息',"请先选择要删除的数据！！！",'warning');
				return;
			}
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			};
			var idStr = ids.join(",");
			$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteById("'+idStr+'")');			
		});
		
		$("#backButton").bind("click",function(){
			var rows = $("#vehicleDatagrid").datagrid("getSelections");
			var ids = [];
			if(rows.length == 0){
				$$.showJcdfMessager('提示消息',"请先选择要恢复的数据！！！",'warning');
				return;
			}
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			};
			var idStr = ids.join(",");
			$$.openProcessingDialog();
			$.ajax({
				url:'backByIds.do',
				type:"POST",
				data:{ids:idStr},
				dataType:"json",
				success:function(data){
					if(data.result){
						$$.closeProcessingDialog();
						$$.showJcdfMessager('提示消息',data.msg,'warning');
						$$.refreshJcdfDatagrid("vehicleDatagrid");
					}else{
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					}
				}
			});
		});
		
		$("#exportButton").bind("click",function(){
			var params = $$.stringify($$.serializeToJson("#searchForm")) ;
			$("#postForm")[0].action = basePath+'/vehicleCategory/export.do' ;
			$("#postForm")[0].param.value = params ;
			$("#postForm")[0].submit() ;
		});
	})

	function deleteById (idStr) {
		$$.openProcessingDialog();
		$.ajax({
			url:'deleteByIds.do',
			type:"POST",
			data:{ids:idStr},
			dataType:"json",
			success:function(data){
				if(data.result){
					$$.closeProcessingDialog();
					$$.showJcdfMessager('提示消息',data.msg,'warning');
					$$.refreshJcdfDatagrid("vehicleDatagrid");
				}else{
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
			}
		});
	}

	/**
	 * 页面加载时初始化datagrid列表并加载列表数据进行显示
	 */
	function loadDatagrid() {
		$('#vehicleDatagrid').datagrid({
			height : $$.getDatagridHeight(),
			width : $$.getDatagridWidth(),
			nowrap : true,
			striped : true,
			url : 'pageQuery.do',
			idField : 'id',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true,
				align : 'center'
			} ] ],
			columns : [ [ 
				{field : 'topName',title : '公司名称',width : $$.fillsize(0.2),align : 'center'}, 
				{field : 'secondName',title : '运营属性',width : $$.fillsize(0.2),align : 'center'}, 
				{field : 'thirdName',title : '车辆类别',width : $$.fillsize(0.2),align : 'center'}, 
				/* {field : 'useState',title : '使用状态',width : $$.fillsize(0.2),align : 'center',formatter:function(value,row,index){
					if (value == "已废弃"){
						return "<div style='color:red'>"+value+"</div>";
					}else{
						return value;
					}
				}}, */
				] ],
			onBeforeLoad : function() {
				$$.clearSelect("vehicleDatagrid");
			},
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			pageList : [ 10, 15, 20, 30, 50, 100 ],
			pageSize : 15,
			toolbar : '#menu'
		});
	}
</script>
</head>

<body>

	<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div id="searchPanel" title=" " class="easyui-panel"
			style="width: 100%;" data-options="collapsible:true,border:0">
			<form action="#" name="searchForm" id="searchForm"
				style="display: inline;">
				<table class="searchArea">
					<td><span>公司名称:</span></td><td><input type="text" id="topName" name="topName" class="easyui-textbox" data-options="validType:['unnormal']"></td>
					<td><span>运营属性:</span></td><td><input type="text" id="secondName" name="secondName" class="easyui-textbox" data-options="validType:['unnormal']"></td>
					<td><span>车辆类别:</span></td><td><input type="text" id="thirdName" name="thirdName" class="easyui-textbox" data-options="validType:['unnormal']"></td>
				</table>
				<table class="searchMenu">
					<tr>
						<td colspan="6" id="icon-button"><a id="search_button"
							href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">查询</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a id="clear_button" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="menu-area">
			<jcdf:auth code="040201">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">增加</a>
			</jcdf:auth>
			<jcdf:auth code="040202">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="040203">
				<a href="#" id="deleteButton" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true">删除</a>
			</jcdf:auth>
			<%-- <jcdf:auth code="040206">
				<a href="#" id="backButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">恢复</a>
			</jcdf:auth> --%>
			<jcdf:auth code="040205">
				<a href="#" id="exportButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
			<jcdf:auth code="040204">
				<a href="#" id="tipButton" class="easyui-linkbutton"
					iconCls="icon-tip" plain="true">提示</a>
			</jcdf:auth>
		</div>
	</div>
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
	<!-- 数据展示列表区 -->
	<table id="vehicleDatagrid" tagType="datagrid">

	</table>
</body>
</html>