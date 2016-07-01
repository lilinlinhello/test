<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>报警参数管理页面</title>
</head>
<script type="text/javascript">
	$(function() {
		//页面加载时初始化datagrid列表并加载列表数据进行显示
		loadDatagrid();
		/**查询按钮单击事件 */
		$("#search_button").bind("click",function() {
			if (!$("#searchForm").form('validate')) {
				$$.showJcdfMessager('提示消息', '查询参数不合法', 'info');
				return false;
			}
			var params = $$.serializeToJson("#searchForm");
			$('#alarmParamDatagrid').datagrid('options').queryParams = params;
			$('#alarmParamDatagrid').datagrid('load');
		});

		/**清空按钮单击事件 */
		$("#clear_button").bind("click", function() {
			$$.resetContent("searchForm");
		});

		/**新增按钮单击事件 */
		$("#addButton").bind("click",function() {
			$$.openJcdfDialog('alarmParam/addPage.do', ' ',400, 600);
		});

		/**编辑按钮单击事件 */
		$("#editButton").bind("click",function() {
			var selectRow = $$.getSingleSelectRow("alarmParamDatagrid", '请选择一条记录进行操作！');
			if (null != selectRow) {
				var id = selectRow.apId;
				$$.openJcdfDialog('alarmParam/editPage.do?id=' + id, ' ', 400, 800);
			}
		});

		/**删除按钮单击事件*/
		$("#deleteButton").bind("click",function() {
			var deleteNotes = $$.getSelectIds("alarmParamDatagrid", "请选择你要删除的记录！", "id");
			if (deleteNotes) {
				$$.showJcdfConfirm("确认", "确定删除所选记录?",'deleteNoteById("' + deleteNotes.ids + '")');
			}
		});
	});

	/**
	 *	删除所选记录
	 */
	function deleteNoteById(ids) {
		$$.openProcessingDialog();
		$.post("deleteByIds.do", {
			"ids" : ids
		}, function(data) {
			$$.closeProcessingDialog();
			if (data && data.result) {
				$$.showJcdfMessager('提示消息', data.msg, 'info');
				$$.refreshJcdfDatagrid("alarmParamDatagrid");
			} else {
				$$.showJcdfMessager('提示消息', data.msg, 'warning');
			}
			$$.clearSelect("alarmParamDatagrid");
		}, 'json');
	}

	function loadDatagrid() {
		$('#alarmParamDatagrid').datagrid({
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
			columns : [ [ 	{ field : 'apId', title : 'ID', width : $$.fillsize(0.1), align : 'center', hidden : 'true'},
							{ field : 'apType', title : '报警类型', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'duration', title : '报警时长(分钟)', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'distance', title : '偏移距离(米)', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'collection', title : 'GPS点数', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'speed', title : '时速(KM/h)', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'startTime', title : '生效时间', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'endTime', title : '失效时间', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'createBy', title : '创建者', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'createTime', title : '创建时间', width : $$.fillsize(0.1), align : 'center'},
							
					] ],
			onBeforeLoad : function() {
				$$.clearSelect("alarmParamDatagrid");
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
<body>
	<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div id="searchPanel" title=" " class="easyui-panel"
			style="width: 100%;" data-options="collapsible:true,border:0">
			<form action="#" name="searchForm" id="searchForm"
				style="display: inline;">
				<table class="searchArea">
					<tr>
						<td>报警类型：</td>
						<td><select id="apType" class="easyui-combobox" name="apType" style="width:200px;">   
							    <option value=""></option>
							    <option value="12">低速报警</option>
							    <option value="13">无信号报警</option>
							    <option value="22">线路偏移报警</option>
							</select>
						</td>
					</tr>
				</table>
				<table class="searchMenu">
					<tr>
						<td id="icon-button"><a id="search_button" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a id="clear_button" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="menu-area">
			<jcdf:auth code="080101">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<%-- <jcdf:auth code="070102">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth> --%>
		</div>
	</div>
	<!-- 数据展示列表区 -->
	<table id="alarmParamDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post"
		style="display: inline;" target="downloadFormIframe">
		<input type="hidden" name="param" />
	</form>
</body>
</html>