<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
	     var dictionarys = '${requestScope.dictionarys}' ;
	     var t = eval('('+dictionarys+')') ;
		$(function(){
			  //加载选择下拉框 
			initDictionary(t,"searchForm");
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			
			//新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	$$.openJcdfDialog(basePath+'/auth/user.do?method=forwardAddJsp', '用户新增', 280, 500);
		    });
		    
		     //修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("userDatagrid", '请选择一条记录进行操作！');
				if (null != selectRow) {
					if("dic_userType01" == selectRow.userType) {
		    			$$.showJcdfMessager('提示消息',"超级管理员不允许执行修改操作！",'info');
		    			return false;
		    		} 
					$$.openJcdfDialog(basePath+'/auth/user.do?method=forwardEditJsp&userId='+selectRow.userId, '用户修改', 330, 620);
				}
		    });
		     //删除按钮单击事件
		    $("#deleteButton").bind("click", function(){
		    	var deleteNotes = $$.getSelectIds("userDatagrid", "请选择你要删除的记录！", "userId");
		    	if(deleteNotes){
		    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
		    	}
		    });
		     //授权按钮单击事件
		    $("#authButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("userDatagrid", '请选择一条记录进行操作！');
		    	if (selectRow) {
		    		if("dic_userType01" == selectRow.userType) {
		    			$$.showJcdfMessager('提示消息',"超级管理员不允许执行授权操作！",'info');
		    		} else {
		    			$$.openJcdfDialog(basePath+'/auth/user.do?method=forwardAuthJsp&userId='+selectRow.userId, '用户授权', 600, 500);
		    		}
		    	}
		    });
		    
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#userDatagrid').datagrid('options').queryParams=params;
				$('#userDatagrid').datagrid('load');
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$('#userDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'user.do?method=pageQuery',
				idField:'userId',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'userId',title:'用户账号',width:$$.fillsize(0.1),align:'center'},
					{field:'userName',title:'用户名称',width:$$.fillsize(0.1),align:'center'},
					{field:'userType',title:'用户类型',width:$$.fillsize(0.1),align:'center',formatter:function(value,row,index){return matchDics(value,row,index);}},
					{field:'createTime',title:'创建时间 ',width:$$.fillsize(0.2),align:'center'},
					{field:'createBy',title:'创建人  ',width:$$.fillsize(0.1),align:'center'},
					{field:'updateTime',title:'修改时间 ',width:$$.fillsize(0.2),align:'center'},
					{field:'updateBy',title:'修改人  ',width:$$.fillsize(0.1),align:'center'}
					]],
				onBeforeLoad:function(){$$.clearSelect("userDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageSize : 15,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/**
		 *	删除所选记录
		 */
		function deleteNoteById(ids){
			$$.openProcessingDialog();
			$.post("user.do?method=deleteById",{"userIds":ids},function(data){
				$$.closeProcessingDialog();
				if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
					$$.refreshJcdfDatagrid("userDatagrid");
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("userDatagrid");
			},'json');
		}
	</script>
  </head>
  
  <body>
    
    <!-- 数据展示列表查询区 -->
   <div id="menu">
 		<div id="searchPanel"  title=" " class="easyui-panel" data-options="collapsible:true,border:0" style="width:100%;">
        	<form action="#" name="searchForm" id="searchForm" style="display: inline;">
        		<table class="searchArea">
        		   <tr>
				    <td>用户类型：</td>
				    <td >
						<input id="userType" name="userType" type="text" class="easyui-combobox" panelHeight="auto">
				    </td>
				    <td >用户账号：</td>
				    <td >
				    	<input name="userId" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    <td >用户名称：</td>
				    <td>
				    	<input name="userName" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				  </tr>
			 	</table>
			 	<table class="searchMenu">
			 		<tr>
				  	<td id="icon-button" >
				  		<a id="search_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				  		&nbsp;&nbsp;&nbsp;&nbsp;
				  		<a id="clear_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
				  	</td>
				  </tr>
			 	</table>
		     </form>
		     </div>
		    <div class="menu-area">
		     <jcdf:auth code="010101">
		        <a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="010102">
		    	<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="010103">
			 	<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
			 <jcdf:auth code="010104">
				<a href="#" id="authButton" class="easyui-linkbutton" iconCls="icon-ok" plain="true">授权</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="userDatagrid" tagType="datagrid">
    	
    </table>
  </body>
</html>
