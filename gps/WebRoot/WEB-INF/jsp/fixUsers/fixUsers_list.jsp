<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>安装人信息维护</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
	
		$(function(){
		
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			
			
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				if (!$("#searchForm").form('validate')) {
					$$.showJcdfMessager('提示消息','查询参数不合法','info');
					return false;
				}
				var params = $$.serializeToJson("#searchForm") ;
				$('#fixUsersDatagrid').datagrid('options').queryParams=params ;
				$('#fixUsersDatagrid').datagrid('load') ;
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
			
			/**新增按钮单击事件 */
			$("#addButton").bind("click",function() {
				$$.openJcdfDialog('fixUsers/addPage.do', ' ',400, 500);
			});

			/**编辑按钮单击事件 */
			$("#editButton").bind("click",function() {
				var selectRow = $$.getSingleSelectRow("fixUsersDatagrid", '请选择一条记录进行操作！');
				if (null != selectRow) {
					var id = selectRow.id;
					$$.openJcdfDialog('fixUsers/editPage.do?id=' + id, ' ', 400, 500);
				}
			});

			/**删除按钮单击事件*/
			$("#deleteButton").bind("click",function() {
				var deleteNotes = $$.getSelectIds("fixUsersDatagrid", "请选择你要删除的记录！", "id");
				if (deleteNotes) {
					$$.showJcdfConfirm("确认", "确定删除所选记录?",'deleteNoteById("' + deleteNotes.ids + '")');
				}
			});
		})
		
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
				$$.refreshJcdfDatagrid("fixUsersDatagrid");
			} else {
				$$.showJcdfMessager('提示消息', data.msg, 'warning');
			}
			$$.clearSelect("fixUsersDatagrid");
		}, 'json');
	}
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			var params = {};
			$('#fixUsersDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'pageQuery.do',
				queryParams : params,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true,
					align : 'center'
				} ] ],
				columns:[[
							{ field : 'id', title : 'ID', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'name', title : '安装人姓名', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'phone', title : '安装人联系方式', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'createBy', title : '创建者', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'createTime', title : '创建时间', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'updateBy', title : '更新者', width : $$.fillsize(0.1), align : 'center'},
							{ field : 'updateTime', title : '更新时间', width : $$.fillsize(0.1), align : 'center'},
						]],
				onBeforeLoad:function(){$$.clearSelect("fixUsersDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageSize : 15,
				pageList: [15,20,30,50,100],
				toolbar:'#menu',
				pageSize : 15
			});
		}
	</script>
  </head>
  
  <body>
    
    <!-- 数据展示列表查询区 -->
   <div id="menu" >
 		<div id="searchPanel"  title=" " class="easyui-panel" style="width:100%;"  data-options="collapsible:true,border:0">
        	<form action="#" name="searchForm" id="searchForm" style="display: inline;">
        		<table class="searchArea">
        		   <tr>
        		   <td  >ID：</td>
				    <td >
						<input id="id" name="id" type="text" class="easyui-textbox" data-options="validType:['number']">
				    </td>
				    <td >姓名：</td>
				    <td >
				    	<input id="name" name="name" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    
				  </tr>
				  
				</table>
			 	<table class="searchMenu">
				  <tr>
				  	<td colspan="6" id="icon-button">
				  		<a id="search_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				  		&nbsp;&nbsp;&nbsp;&nbsp;
				  		<a id="clear_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
				  	</td>
				  </tr>
			 	</table>
		     </form>
		     </div>
			<jcdf:auth code="040301">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<jcdf:auth code="040302">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="040303">
				<a href="#" id="deleteButton" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true">删除</a>
			</jcdf:auth>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="fixUsersDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
