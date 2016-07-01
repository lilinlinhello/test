<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>公司信息管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			$('#deleteFlag').combobox({
		    	panelHeight:"auto",
		    	editable:false
		    });
			$('#deleteFlag').combobox("setValue","");
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm") ;
				$('#vehicleRecordDatagrid').datagrid('options').queryParams=params ;
				$('#vehicleRecordDatagrid').datagrid('load') ;
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				var data = $$.serializeToJson("#searchForm") ;
				$$.resetContent("searchForm");
				$('#deleteFlag').combobox("setValue","");
			});
			
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			var params = {};
			$('#vehicleRecordDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'vehicleRecordQuery.do',
				queryParams : params,
				frozenColumns:[[
								]],
								columns:[[
									{field:'vehicleId',title:'车辆编码',width:$$.fillsize(0.12),align:'center'},
									{field:'tableName',title:'分配表名称',width:$$.fillsize(0.18),align:'center'},
									{field:'deleteFlag',title:'删除状态',width:$$.fillsize(0.18),align:'center'},
									{field:'createBy',title:'创建人',width:$$.fillsize(0.12),align:'center'},
									{field:'updateBy',title:'修改人',width:$$.fillsize(0.12),align:'center'},
									{field:'createTime',title:'创建时间',width:$$.fillsize(0.12),align:'center'},
									{field:'updateTime',title:'修改时间',width:$$.fillsize(0.12),align:'center'},
									]],
				onBeforeLoad:function(){$$.clearSelect("vehicleRecordDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageSize : 15,
				pageList: [15,20,30,50,100],
				toolbar:'#menu',
				pageSize : 15,
				rowStyler:function(index,row){
					if(row.deleteFlag == '0'){
						row.deleteFlag = '未删除';
					}
					if(row.deleteFlag == '1'){
						row.deleteFlag = '已删除';
					}
				}
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
        		   <td  >车辆编码：</td>
				    <td >
						<input id="vehicleId" name="vehicleId" type="text" class="easyui-textbox">
				    </td>
				    <td >分配表名称：</td>
				    <td >
				    	<input id="tableName" name="tableName" type="text" class="easyui-textbox" >
				    </td>
				    <td>删除状态：</td>
				    <td>
				    	<select id="deleteFlag" name="deleteFlag" class="easyui-combobox"  style="width:150px;height:auto">
				    		<option value="0">未删除</option>
				    		<option value="1">已删除</option>
				    	</select>
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
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="vehicleRecordDatagrid" tagType="datagrid">
	</table>
  </body>
</html>
