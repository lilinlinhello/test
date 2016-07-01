<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>数据包详情</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	
	<script type="text/javascript">
		var vehId = '${requestScope.vehicleId}';
		$(function(){
			loadDatagrid();
		});
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		 function loadDatagrid(){
		 	$('#RecentGpsDataDatagrid').datagrid({
		 		height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap : true,
				striped : true,
				url : basePath+'/vehicleMaintain/findRecentGpsData.do',
				columns:[[
						{field:'longitude',title:'经度',width:$$.fillsize(0.12),align:'center',
							formatter:function(value,row,index){
								if(value!=undefined){
									if(value==0) return 0;
									var du = value.toString();
									var str1 = du.split(".");
									var du1 = str1[0];
									var tp = "0."+str1[1]
									var tp = String(tp*60);		//这里进行了强制类型转换
									var str2 = tp.split(".");
									var fen =str2[0];
									tp = "0."+str2[1];
									tp = Math.round(tp*60);
									var miao = tp;
									return (dufenmiao= du1+"°"+fen+"'"+miao+"\"");
								}
								else{
									return "";
								}
							}
						},
						{field:'latitude',title:'纬度',width:$$.fillsize(0.12),align:'center',
							formatter:function(value,row,index){
								if(value!=undefined){
									if(value==0) return 0;
									var du = value.toString();
									var str1 = du.split(".");
									var du1 = str1[0];
									var tp = "0."+str1[1]
									var tp = String(tp*60);		//这里进行了强制类型转换
									var str2 = tp.split(".");
									var fen =str2[0];
									tp = "0."+str2[1];
									tp = Math.round(tp*60);
									var miao = tp;
									return (dufenmiao= du1+"°"+fen+"'"+miao+"\"");
								}
								else{
									return "";
								}
							}
						},
						{field:'command_code',title:'命令字',width:$$.fillsize(0.10),align:'center'},
						{field:'gps_create_at',title:'数据包时间',width:$$.fillsize(0.12),align:'center'},
						{field:'create_at',title:'记录时间',width:$$.fillsize(0.12),align:'center'},

				]],
				rownumbers : true,
		 		queryParams:{vehicleId:vehId},
		 	});
		 }
	</script>
  </head>
  
  <body>

    <!-- 数据展示列表区 -->
    <table id="RecentGpsDataDatagrid" tagType="datagrid">
    </table>
    <form id="postForm" name="postForm" method="post" style="display: inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
