<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>日志管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			
			$("#clear_button").bind("click",function(){
				$("#searchForm").form("reset");
			});
			
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#logDatagrid').datagrid('options').queryParams=params;
				$('#logDatagrid').datagrid('reload');
			});
			
			$("#exportButton").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$("#exportForm").form('options').queryParams=params;
				$("#exportForm").submit();
			});
		}) 
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$('#logDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'syslog.do?method=pageQuery',
				idField:'logId',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'logIp',title:'操作人IP',width:$$.fillsize(0.1),align:'center'},
					{field:'logMac',title:'操作人Mac',width:$$.fillsize(0.1),align:'center'},
					{field:'userId',title:'用户编号',width:$$.fillsize(0.1),align:'center'},
					{field:'userName',title:'用户名称',width:$$.fillsize(0.1),align:'center'},
					{field:'moduleName',title:'模块名称',width:$$.fillsize(0.1),align:'center'},
					{field:'operatoContent',title:'操作明细',width:$$.fillsize(0.2),align:'center'},
					{field:'operatorTime',title:'日志记录时间',width:$$.fillsize(0.1),align:'center'},
					{field:'logPara1',title:'日志参数1',width:$$.fillsize(0.1),align:'center',hidden:true},
					{field:'logPara2',title:'日志参数2',width:$$.fillsize(0.1),align:'center',hidden:true},
					{field:'logPara3',title:'模块操作',width:$$.fillsize(0.1),align:'center'},
					{field:'logPara4',title:'异常信息',width:$$.fillsize(0.1),align:'center'}
					
				]],
				onBeforeLoad:function(){$$.clearSelect("logDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList:[10,15,20,30,50,100],
				pageSize:15,
// 				queryParams:{},
				toolbar:'#menu'
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
				  <td>操作时间：</td>
				    <td>
				    	<input id="minUpdateTime" name="minUpdateTime" class="easyui-datetimebox" style="width:150px" prompt="最小操作时间"/>
				    </td>
				    <td>	
				    	<input id="maxUpdateTime" name="maxUpdateTime" class="easyui-datetimebox" style="width:150px" prompt="最大操作时间" />
				    </td>
				    <td></td>
				     <td>用户名称：</td>
				    <td>
				    	<input id="userName" name="userName" class="easyui-textbox"  style="width:150px" />
				    </td>
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
		<div class="menu-area">
		     <jcdf:auth code="010501">
		     	<a href="#" id="exportButton" class="easyui-linkbutton" iconCls="icon-excel" plain="true">导出</a>
		     </jcdf:auth>
        </div>
    </div>
    <form id="exportForm" action="syslog.do?method=exportExcel" method="post" type="hidden"></form>
    <!-- 数据展示列表区 -->
    <table id="logDatagrid" tagType="datagrid">
    	
    </table>
  </body>
</html>