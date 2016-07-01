<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
<head>
<title>消息队列管理</title>
<meta charset="UTF-8" />
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		//页面加载时初始化datagrid列表并加载列表数据进行显示
		loadDatagrid();

		//查询按钮单击事件绑定
		$("#search_button").bind("click", function() {
			if (!$("#searchForm").form('validate')) {
				$$.showJcdfMessager('提示消息', '查询参数不合法', 'info');
				return false;
			}
			var params = $$.serializeToJson("#searchForm");
			$('#mqDatagrid').datagrid('options').queryParams = params;
			$('#mqDatagrid').datagrid('load');
		});

		//清空按钮单击事件绑定
		$("#clear_button").bind("click", function() {
			$$.resetContent("searchForm");
		});

	})

	/**
	 * 页面加载时初始化datagrid列表并加载列表数据进行显示
	 */
	function loadDatagrid() {
		$('#mqDatagrid').datagrid({
			height : $$.getDatagridHeight(),
			width : $$.getDatagridWidth(),
			nowrap : true,
			striped : true,
			url : basePath + '/auth/mq.do?method=pageQuery',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true,
				align : 'center'
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '队列名称',
				width : $$.fillsize(0.2),
				align : 'center'
			}, {
				field : 'messagesReady',
				title : '已准备好消息数',
				width : $$.fillsize(0.15),
				align : 'center'
			}, {
				field : 'messagesUnacknowledged',
				title : '未应答消息数',
				width : $$.fillsize(0.15),
				align : 'center'
			}, {
				field : 'messages',
				title : '队列中消息总数',
				width : $$.fillsize(0.15),
				align : 'center'
			}, {
				field : 'consumers',
				title : '消费者',
				width : $$.fillsize(0.15),
				align : 'center'
			}, {
				field : 'state',
				title : '状态',
				width : $$.fillsize(0.15),
				align : 'center'
			} ] ],
			onBeforeLoad : function() {
				$$.clearSelect("mqDatagrid");
			},
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			pageList : [ 10, 15, 20, 30, 50, 100 ],
			toolbar : '#menu'
		});
	}
</script>
</head>

<body>
	<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div id="searchPanel" title=" " class="easyui-panel"
			style="width:100%;" data-options="collapsible:true,border:0">
			<form action="#" name="searchForm" id="searchForm"
				style="display: inline;">
				<table class="searchArea">
					<tr>
						<td>消息队列名称：</td>
						<td><input name="name" type="text" class="easyui-textbox"
							data-options="validType:['unnormal']"></td>
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
	</div>

	<!-- 数据展示列表区 -->
	<table id="mqDatagrid" tagType="datagrid">

	</table>
</body>
</html>
