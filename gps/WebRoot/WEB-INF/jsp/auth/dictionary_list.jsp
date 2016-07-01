<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>字典管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			var url = basePath+"/auth/dictionary.do?method=loadDicType" ;
			//select框重新加载
			$("#dicType").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'NAME',
				textField : 'VALUE',
				mode : 'remote',
				onBeforeLoad : function (param){
					var t = $("#dicType").combobox('options').url ;
					if(t == '#' || t==null){
						return false ;
					}else{
						$("#dicType").combobox('options').url = url ;
						return true;
					}
			    },
				onShowPanel:function(){
					$("#dicType").combobox('reload',url);
				},
				onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#dicType").combobox('clear');
					}
			    },
		    });
			//新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	$$.openJcdfDialog(basePath+'/auth/dictionary.do?method=forwardAddJsp', '新增字典', 280, 500);
		    });
		    
		     //修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("dictionaryDatagrid", '请选择一条记录进行操作！');
				if (null != selectRow) {
					$$.openJcdfDialog(basePath+'/auth/dictionary.do?method=forwardEditJsp&dicId='+selectRow.dicId, '字典修改', 330, 620);
				}
		    });
		     //删除按钮单击事件
		    $("#deleteButton").bind("click", function(){
		    	var deleteNotes = $$.getSelectIds("dictionaryDatagrid", "请选择你要删除的记录！", "dicId");
		    	if(deleteNotes){
		    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
		    	}
		    });
		    
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#dictionaryDatagrid').datagrid('options').queryParams=params;
				$('#dictionaryDatagrid').datagrid('load');
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
			$('#dictionaryDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'dictionary.do?method=pageQuery',
				idField:'dicId',
				
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'dicId',title:'字典编号',width:$$.fillsize(0.15),align:'center'},
					{field:'dicName',title:'字典名称',width:$$.fillsize(0.2),align:'center'},
					{field:'dicType',title:'字典类型',width:$$.fillsize(0.2),align:'center'},
					{field:'dicOrder',title:'字典序号',width:$$.fillsize(0.1),align:'center'},
					{field:'discription',title:'字典备注 ',width:$$.fillsize(0.2),align:'center'},
					{field:'deleteFlag',title:'使用状态',width:$$.fillsize(0.1),align:'center',formatter:function(value,row,index){
						if(0==value){
							return "<font color='green'>使用中</font>";
						}else{
							return "<font color='red'>已弃用</font>";
						}
						}}
				]],
				onBeforeLoad:function(){$$.clearSelect("dictionaryDatagrid");},
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
			$.post("dictionary.do?method=deleteById",{"ids":ids},function(data){
				$$.closeProcessingDialog();
				if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
					$$.refreshJcdfDatagrid("dictionaryDatagrid");
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("dictionaryDatagrid");
			},'json');
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
				    <td  >字典编号：</td>
				    <td >
						<input id="dicId" name="dicId" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    <td >字典名称：</td>
				    <td>
				    	<input id="dicName" name="dicName" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>

				    <td >字典类型：</td>
				    <td >
				    	<input id="dicType" name="dicType" type="text" class="easyui-combobox" panelHeight="200">
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
		     <jcdf:auth code="010401">
		   	<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="010402">
		    	<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="010403">
			 	<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="dictionaryDatagrid" tagType="datagrid">
    	
    </table>
  </body>
</html>
