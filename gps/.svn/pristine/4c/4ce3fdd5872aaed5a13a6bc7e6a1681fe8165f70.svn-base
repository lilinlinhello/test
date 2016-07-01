<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>货运单信息</title>
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
				$('#shipmentDatagrid').datagrid('options').queryParams=params ;
				$('#shipmentDatagrid').datagrid('load') ;
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
			
			$("#exportButton").bind("click",function(){
				var params = $$.stringify($$.serializeToJson("#searchForm")) ;
				$("#postForm")[0].action = basePath+'/shipment/export.do' ;
				$("#postForm")[0].param.value = params ;
				$("#postForm")[0].submit() ;
			});
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			var params = {};
			$('#shipmentDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'pageQuery.do',
				queryParams : params,
				frozenColumns:[[
								]],
								columns:[[
									{field:'shipmentId',title:'货运单号',width:$$.fillsize(0.1),align:'center'},
									{field:'lineId',title:'线路编码',width:$$.fillsize(0.1),align:'center'},
									{field:'lineName',title:'线路名称',width:$$.fillsize(0.15),align:'center'},
									
									{field:'startPlace',title:'始发站',width:$$.fillsize(0.1),align:'center'},
									{field:'endPlace',title:'目的站',width:$$.fillsize(0.1),align:'center'},
									{field:'passPlace',title:'多个途径地',width:$$.fillsize(0.1),align:'center'},
									
									{field:'planLeaveTime',title:'计划离开时间',width:$$.fillsize(0.1),align:'center'},
									{field:'planArriveTime',title:'计划到达时间',width:$$.fillsize(0.1),align:'center'},
									{field:'licenseCard',title:'车牌号',width:$$.fillsize(0.1),align:'center'},
									
									{field:'preFlag',title:'预处理标识',width:$$.fillsize(0.1),align:'center'},									
									{field:'planLineId',title:'规划线路',width:$$.fillsize(0.1),align:'center'},
									{field:'planLineType',title:'轨迹类型',width:$$.fillsize(0.1),align:'center'},
									{field:'createTime',title:'创建时间',width:$$.fillsize(0.1),align:'center'},
									]],
				onBeforeLoad:function(){$$.clearSelect("shipmentDatagrid");},
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
				    <td >线路编码：</td>
				    <td >
				    	<input id="lineId" name="lineId" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    <td >线路名称：</td>
				    <td >
				    	<input id="lineName" name="lineName" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
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
		     <jcdf:auth code="060501">
				<a href="#" id="exportButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="shipmentDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
