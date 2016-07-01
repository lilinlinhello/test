<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>GPS设备维护登记页面</title>
</head>
<script type="text/javascript">
var dictionarys = '${requestScope.dictionarys}';
var t = eval('('+dictionarys+')');
$(function(){
	//加载选择下拉框
    initDictionary(t,"searchForm");
	
	/**初始加载车牌号 */
	initCarNumber("carNum");
	
	/**加载所有的安装人员 */
    initInstallUser("installPerson");
	
	/**加载所有的用户 */
    initAllUser("inputUser");

	/**隐藏维修原因*/
    document.getElementById("re1").style.display="none";
    document.getElementById("re2").style.display="none";

	/**加载列表数据*/
    loadDatagrid();
	
	/**查询按钮单击事件 */
	$("#search_button").bind("click",function(){
		if(!$("#searchForm").form('validate')){
		      return false;
		 } 
		var params = $$.serializeToJson("#searchForm");
		$('#gpsEquipRegisterDatagrid').datagrid('options').queryParams=params;
		$('#gpsEquipRegisterDatagrid').datagrid('load');
	});
	
	/**清空按钮单击事件 */
	$("#clear_button").bind("click",function(){
		$$.resetContent("searchForm");
	});
	
	/**新增按钮单击事件 */
	$("#addButton").bind("click",function(){
		$$.openJcdfDialog(basePath+'/gpsEquipRegister/toAddEquipRegister.do', ' ',600,500);
	});
	
	/**修改按钮单击事件*/
    $("#editButton").bind("click", function(){
    	var selectRow = $$.getSingleSelectRow("gpsEquipRegisterDatagrid", '请选择一条记录进行操作！');
		if (null != selectRow) {
			$$.openJcdfDialog(basePath+'/gpsEquipRegister/toEditEquipRegister.do?id='+selectRow.oaId,' ',600,500);
		}
    });
    
  	/**删除按钮单击事件*/
    $("#deleteButton").bind("click", function(){
    	var deleteNotes = $$.getSelectIds("gpsEquipRegisterDatagrid", "请选择你要删除的记录！", "oaId");
    	if(deleteNotes){
    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
    	}
    });
	
    /**导出*/
    $("#export").bind("click", function(){
    	var params = $$.stringify($$.serializeToJson("#searchForm")) ;
		$("#postForm")[0].action = basePath+'/gpsEquipRegister/exportGpsEquipRegister.do' ;
		$("#postForm")[0].param.value = params ;
		$("#postForm")[0].submit() ;
    });
})
/**加载列表数据*/
function loadDatagrid(){
	$('#gpsEquipRegisterDatagrid').datagrid({
		height:$$.getDatagridHeight(),
		width:$$.getDatagridWidth(),
		nowrap: true,
		striped: true,
		url:basePath+'/gpsEquipRegister/queryEquipRegister.do',
		idField:'oaId',
		frozenColumns:[[
	        {field:'ck',checkbox:true,align:'center'}
		]],
		columns:[[
			{field:'oaId',title:'OA编号',width:$$.fillsize(0.05),align:'center'},
			{field:'originalCarNumber',title:'原车牌号',width:$$.fillsize(0.05),align:'center'},
			{field:'newCarNumber',title:'新车牌号',width:$$.fillsize(0.05),align:'center'},
			{field:'carLine',title:'车线',width:$$.fillsize(0.07),align:'center'},
			{field:'installPerson',title:'安装人员',width:$$.fillsize(0.04),align:'center'},
			{field:'installDate',title:'安装日期',width:$$.fillsize(0.09),align:'center'},
			{field:'telephone',title:'联系电话',width:$$.fillsize(0.06),align:'center'},
			{field:'equipStatus',title:'设备状态 ',width:$$.fillsize(0.04),align:'center'},
			{field:'repairReason',title:'维修原因 ',width:$$.fillsize(0.05),align:'center'},
			{field:'reportDate',title:'上报日期',width:$$.fillsize(0.09),align:'center'},
			{field:'inputTime',title:'录入日期',width:$$.fillsize(0.09),align:'center'},
			{field:'isComplate',title:'是否完成',width:$$.fillsize(0.04),align:'center',
			     formatter:function(value,row,index){
					if(0==value){
						return "<font>否</font>";
				  }
					if(1==value){
						return "<font>是</font>";
				  }
			     }
			},
			{field:'complateDate',title:'完成日期',width:$$.fillsize(0.09),align:'center'},
			{field:'installComment',title:'安装备注',width:$$.fillsize(0.17),align:'center'}
		]],
		onBeforeLoad:function(){$$.clearSelect("gpsEquipRegisterDatagrid");},
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		pageList:[10,15,20,30,50,100],
		pageSize:15,
		toolbar:'#menu'
	});
}

/**
 *	删除所选记录
 */
function deleteNoteById(ids) {
	$$.openProcessingDialog();
	$.post(basePath+"/gpsEquipRegister/delGpsEquipRegister.do", 
		{"ids" : ids}, function(data) {
		$$.closeProcessingDialog();
		if (data && data.result) {
			$$.showJcdfMessager('提示消息', data.msg, 'info');
			$$.refreshJcdfDatagrid("gpsEquipRegisterDatagrid");
		} else {
			$$.showJcdfMessager('提示消息', data.msg, 'warning');
		}
		$$.clearSelect("gpsEquipRegisterDatagrid");
	}, 'json');
}
</script>
<body>
   <!-- 数据展示列表查询区 -->
   <div id="menu" >
 		<div id="searchPanel" class="easyui-panel" style="width:100%;"  data-options="collapsible:true,border:0">
        	<form action="#" name="searchForm" id="searchForm" style="display:inline;">
        		<table class="searchArea" >
        		  <tr>
        		    <td style="width: 1700px">
        		     <table>
        		     <tr>                
                        <td>OA编号：</td>
                        <td>
                          <input id="oaId" name="oaId" type="text" class="easyui-textbox" data-options="validType:['maxLength[48]','unnormal']">
                        </td>
                        <td>车牌类型：</td>
	                    <td>
	                        <select id="carNumType" name="carNumType" class="easyui-combobox" style="width:100px;">  
	                            <option value=" ">请选择</option>  
	                            <option value="0" >新车牌</option>
	                            <option value="1" selected>原车牌</option> 
	                        </select> 
	                    </td>
	                    <td>车牌号：</td>
	                    <td>
	                        <input id="carNum"  name="carNum" data-options="validType:['unnormal']"
	                        class="easyui-combobox"  panelHeight="200px" prompt='请选择车牌号' />
	                    </td>
	                    <td>安装人：</td>
	                    <td>
	                        <input id="installPerson" name="installPerson" type="text" class="easyui-combobox" 
	                          panelHeight="200px" prompt='请选择安装人员' data-options="validType:['unnormal']"/>
	                    </td>
	                     <td>维护人员：</td>
	                    <td>
	                        <input id="inputUser" name="inputUser" type="text" class="easyui-combobox"   
	                        panelHeight="200px"  prompt='请选择维护人员' data-options="validType:['unnormal']"/>
	                    </td>
                    </tr>
                    <tr>
	                    <td>维护类型：</td>
	                    <td>
	                        <input id="equipStatus"  name="equipStatus" field="equipStatus" type="text" panelHeight="auto" 
	                        class="easyui-combobox"  style="width:80px;"  editable="false"/>
	                    </td>
	                    
	                    <td id="re1">维修原因：</td>
	                    <td id="re2">
	                        <input id="repairReason"  name="repairReason" field="repairReason"  panelHeight="auto"
	                        class="easyui-combobox"    style="width:100px" editable="false"/>
	                    </td>
	                     
	                     <td>任务进度：</td>
	                    <td>
	                        <select id="isComplate" name="isComplate" style="width:100px;" class="easyui-combobox">  
	                            <option value=" ">请选择</option>  
	                            <option value="1">完成</option>
	                            <option value="0">未完成</option> 
	                        </select> 
	                    </td>
	                    <td>快捷设置：</td>
	                    <td colspan="3">
	                        <select id="quickTime" name="quickTime" style="width:100px;" class="easyui-combobox">  
	                            <option value=" ">请选择</option>  
	                            <option value="0">当天</option>
	                            <option value="1">本周</option>
	                            <option value="2">上周</option> 
	                            <option value="3">本月</option> 
	                            <option value="4">上月</option> 
	                        </select> 
	                    </td>
                    </tr>
        		    </table>
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
		<div class="menu-area">
			<jcdf:auth code="020101">
		     	<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020102">
		    	<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
			  <jcdf:auth code="020103">
			 	<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
			 <jcdf:auth code="020104">
		     	<a href="#" id="export" class="easyui-linkbutton" iconCls="icon-excel" plain="true">导出</a>
		     </jcdf:auth>
	   </div>
    </div>
    <!-- 数据展示列表区 -->
	<table id="gpsEquipRegisterDatagrid" tagType="datagrid"></table>
	<!-- 导出文件区 -->
	<form id="postForm" name="postForm" method="post" style="display:inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
</body>
</html>