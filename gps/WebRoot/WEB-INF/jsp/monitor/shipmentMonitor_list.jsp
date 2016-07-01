<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>货运单监控</title>
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
				$('#shipmentMonitorDatagrid').datagrid('options').queryParams=params ;
				$('#shipmentMonitorDatagrid').datagrid('load') ;
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
			
			$("#exportButton").bind("click",function(){
				var params = $$.stringify($$.serializeToJson("#searchForm")) ;
				$("#postForm")[0].action = basePath+'/shipmentMonitor/export.do' ;
				$("#postForm")[0].param.value = params ;
				$("#postForm")[0].submit() ;
			});
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			var params = {};
			$('#shipmentMonitorDatagrid').datagrid({
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
									{field:'placeId',title:'始发点编码',width:$$.fillsize(0.1),align:'center'},
									{field:'orderId',title:'站点序号',width:$$.fillsize(0.1),align:'center'},
									{field:'licenseCardId',title:'车辆编码',width:$$.fillsize(0.1),align:'center'},
									{field:'monitorFlag',title:'监控标识',width:$$.fillsize(0.1),align:'center'},
									{field:'shipmentFlag',title:'凭证标识',width:$$.fillsize(0.1),align:'center'},
									{field:'mileageFlag',title:'里程标识',width:$$.fillsize(0.1),align:'center'},
									{field:'planLineId',title:'规划线路',width:$$.fillsize(0.1),align:'center'},
									
									{field:'planLeaveTime',title:'计划离开时间',width:$$.fillsize(0.1),align:'center'},
									{field:'planArriveTime',title:'计划到达时间',width:$$.fillsize(0.1),align:'center'},
									{field:'realLeaveTime',title:'实际离开时间',width:$$.fillsize(0.1),align:'center'},
									{field:'realArriveTime',title:'实际到达时间',width:$$.fillsize(0.1),align:'center'},
									{field:'gpsLeaveTime',title:'GPS离开时间',width:$$.fillsize(0.1),align:'center'},
									{field:'gpsArriveTime',title:'GPS到达时间',width:$$.fillsize(0.1),align:'center'},
									{field:'createTime',title:'创建时间',width:$$.fillsize(0.1),align:'center'},
									]],
				onBeforeLoad:function(){$$.clearSelect("shipmentMonitorDatagrid");},
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
				    <td >监控标识：</td>
				    <td >
				    	<select id="monitorFlag" class="easyui-combobox" name="monitorFlag" style="width:150px;">   
						    <option value=""></option>   
						    <option value="0">待监控</option>   
						    <option value="1">监控中</option>   						    
						    <option value="2">监控结束</option>   						    
						</select> 
				    </td>
				    <td >凭证标识：</td>
				    <td >
				    	<select id="shipmentFlag" class="easyui-combobox" name="shipmentFlag" style="width:150px;">   
						    <option value=""></option>   
						    <option value="0">正常</option>   
						    <option value="1">车牌修改</option>   						    
						    <option value="2">线路修改，手工结束</option>   						    
						</select> 
				    </td>
				    <td >里程标识：</td>
				    <td >
				    	<select id="mileageFlag" class="easyui-combobox" name="mileageFlag" style="width:150px;">   
						    <option value=""></option>   
						    <option value="0">未计算</option>   
						    <option value="1">已计算</option>   						    
						</select> 
				    </td>
				  </tr>
				  <tr>
        		   <td  >实际离开时间：</td>
				    <td >
						<input id="realLeaveTimeMin" name="realLeaveTimeMin" type="text" class="easyui-datetimebox" prompt="最小">
					</td>
					<td>
						<input id="realLeaveTimeMax" name="realLeaveTimeMax" type="text" class="easyui-datetimebox" prompt="最大">
				    </td>
					<td></td>
				    <td >实际到达时间：</td>
				    <td >
				    	<input id="realArriveTimeMin" name="realArriveTimeMin" type="text" class="easyui-datetimebox" prompt="最小">
				    </td>
				    <td>
						<input id="realArriveTimeMax" name="realArriveTimeMax" type="text" class="easyui-datetimebox" prompt="最大">
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
		     <jcdf:auth code="060401">
				<a href="#" id="exportButton" class="easyui-linkbutton"
					iconCls="icon-excel" plain="true">导出</a>
			</jcdf:auth>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="shipmentMonitorDatagrid" tagType="datagrid">
	</table>
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
