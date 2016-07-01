<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>运行里程</title>
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
				$('#runMileageDatagrid').datagrid('options').queryParams=params ;
				$('#runMileageDatagrid').datagrid('load') ;
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
			
			$("#exportButton").bind("click",function(){
				var params = $$.stringify($$.serializeToJson("#searchForm")) ;
				$("#postForm")[0].action = basePath+'/runMileage/export.do' ;
				$("#postForm")[0].param.value = params ;
				$("#postForm")[0].submit() ;
			});
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			var params = {};
			$('#runMileageDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'pageQuery.do',
				queryParams : params,
				frozenColumns:[[
								]],
								columns:[[
									{field:'shipmentId',title:'货运单号',width:$$.fillsize(0.15),align:'center'},
									{field:'gpsMileage',title:'GPS里程',width:$$.fillsize(0.15),align:'center'},
									{field:'equipMileage',title:'设备里程',width:$$.fillsize(0.15),align:'center'},
									{field:'txdFlag',title:'数据发送状态',width:$$.fillsize(0.15),align:'center'},
									{field:'createTime',title:'创建时间',width:$$.fillsize(0.15),align:'center'},
									{field:'updateTime',title:'里程更新时间',width:$$.fillsize(0.15),align:'center'},
									]],
				onBeforeLoad:function(){$$.clearSelect("runMileageDatagrid");},
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
        		   <td  >货运单号：</td>
				    <td >
						<input id="shipmentId" name="shipmentId" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
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
		     <jcdf:auth code="060701">
				<a href="#" id="exportButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="runMileageDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
