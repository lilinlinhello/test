<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>GPS短信模板</title>
<script type="text/javascript">
	$(function(){
		
		loadDatagrid();
		
		loadBrand("smsBrand","0");
		
		$("#search_button").bind("click",function(){
			var params = $$.serializeToJson("#searchForm");
			$('#gpsSMSTemplateDatagrid').datagrid('options').queryParams=params;
			$('#gpsSMSTemplateDatagrid').datagrid('load');
			
		});
		
		$("#clear_button").bind("click",function(){
			$$.resetContent("searchForm");
			$("#smsModel").combobox("loadData",[{text:'',value:''}]);
		});
		
		$("#addButton").bind("click",function(){
			$$.openJcdfDialog(basePath+'/smsTemplate/addSmsTemplate.do', ' ',
					$$.getDatagridHeight()*0.37,$$.getDatagridWidth()*0.35);
		});
		
		$("#editButton").bind("click",function(){
			var selectRow = $$.getSingleSelectRow("gpsSMSTemplateDatagrid","请选择一条记录进行操作");
			if(null != selectRow){
				var deleteFlag = selectRow.deleteFlag;
				if('1'==deleteFlag){
					$$.showJcdfMessager('提示消息', "已删除的数据不能发生修改", 'warning');
					return;
				}
				$$.openJcdfDialog(basePath+'/smsTemplate/smsTemplateEdit.do?smsId='+selectRow.smsId, ' ',
	    				$$.getDatagridHeight()*0.37, $$.getDatagridWidth()*0.35);
			}
		});
		
		$("#deleteButton").bind("click",function(){
			var deleteNotes = $$.getSelectIds("gpsSMSTemplateDatagrid","请选择要删除的记录","smsId");
	    	if(deleteNotes){
	    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
	    	}
		});
		
	});
	
	function loadDatagrid(){
		$("#gpsSMSTemplateDatagrid").datagrid({
			height:$$.getDatagridHeight(),
			width:$$.getDatagridWidth(),
			nowrap: true,
			striped: true,
			url:basePath+'/smsTemplate/smsTemplateQuery.do',
			idField:'smsId',
			frozenColumns:[[{field:'ck',checkbox:true,align:'center'}]],
			columns:[[
				{field:'smsId',title:'ID',width:$$.fillsize(0.08),align:'center',hidden:'true'},
				{field:'smsType',title:'短信类型',width:$$.fillsize(0.06),align:'center',
						formatter:function(value,row,index){
							if('0'==value){
								return "激活";
							}else if('1'==value){
								return "查询";
							}else{
								return "";
							}
						}
				},
				{field:'smsBrand',title:'设备品牌',width:$$.fillsize(0.08),align:'center'},
				{field:'smsModel',title:'设备型号',width:$$.fillsize(0.08),align:'center'},
				{field:'smsOrder',title:'短信序号',width:$$.fillsize(0.04),align:'center'},
				{field:'smsContent',title:'短信内容',width:$$.fillsize(0.04),align:'center',
						formatter:function(value,row,index){
							return "<a style='color:green' href='javascript:void(0)' onclick='smsInfo(\""+row.smsId+"\")'>"+"查看"+"</a>";
						}					
				},
				{field:'createBy',title:'创建人',width:$$.fillsize(0.08),align:'center'},
				{field:'createTime',title:'创建时间',width:$$.fillsize(0.12),align:'center'},
				{field:'updateBy',title:'修改人',width:$$.fillsize(0.08),align:'center'},
				{field:'updateTime',title:'修改时间',width:$$.fillsize(0.12),align:'center'},
				{field:'deleteFlag',title:'删除状态',width:$$.fillsize(0.04),align:'center',
						 formatter:function(value,row,index){
							if('0'==value){
								return "<font color='green'>未删除</font>"; 
							}else if('1'==value){
								return "<font color='red'>已删除</font>";
							}else{
								return "";
							}
						}
				}
			]],
			onBeforeLoad:function(){$$.clearSelect("gpsSMSTemplateDatagird");},
			queryParams:{deleteFlag:0},
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			pageList:[10,15,20,30,50,100],
			pageSize:15,
			toolbar:'#menu'
		});
		
	}
	
	function smsInfo(smsId){
// 		$.ajax({
// 			url:basePath+'/smsTemplate/smsTemplateGet.do',
// 			data:{"smsId":smsId},
// 			type:'POST',
// 			dataType:'json',
// 			success:function(data){
// 				if(data){
// 					//$$.showJcdfMessager('短信内容', data.smsContent, 'info');
// 				}
// 			}
// 		})
		$$.openJcdfDialog(basePath+'/smsTemplate/smsTemplateGetMsg.do?smsId='+smsId, '短信内容',
				$$.getDatagridHeight()*0.3,$$.getDatagridWidth()*0.4);
	}
	
	/*
 	*删除记录
 	*/
 	function deleteNoteById(ids){
 		$$.openProcessingDialog();
 		$.post("smsTemplateDelete.do",{
 				"smsId":ids
 				},function(data){
 				$$.closeProcessingDialog();
 				if (data && data.result) {
					$$.showJcdfMessager('提示消息', data.msg, 'info');
					$$.refreshJcdfDatagrid("gpsSMSTemplateDatagrid");
				} else {
					$$.showJcdfMessager('提示消息', "删除失败", 'warning');
				}
				$$.clearSelect("gpsSMSTemplateDatagird");
 		}, 'json');
 	}
	
 	/*加载设备品牌 **/
	function loadBrand(id,param){
		var url = basePath + "/equipmentBrand/getBrandInfo.do?parentCode="
					+ param;
		$("#"+id).combobox({
			panelHeight:150,
			url:url,
			valueField: 'ID',    
        	textField: 'NAME',
		    onLoadSuccess:function(data){
		    	$("#smsBrand").next('.combo').find('input').blur(function(){
		    		var inputData = $('#smsBrand').combobox('getText');
		    		for(var i=0;i<data.length;i++){
		    			if(inputData==data[i].NAME){
		    				$('#smsBrand').combobox('setValue',data[i].ID);
		    				loadOperation("smsModel",$("#smsBrand").combobox("getValue"));
		    				return;
		    			}
		    		}
		    		$('#smsBrand').combobox('clear');
		    	});
		    },
		    
		    onChange :function (){
		    	$("#smsModel").combobox("loadData",[{text:'',value:''}]);
		    },
        	onSelect:function(recode){
        		loadOperation("smsModel",$("#smsBrand").combobox("getValue"));
        	} 
		});
	}
	/*加载设备型号 **/
	function loadOperation(id,param){
		var url = basePath + "/equipmentBrand/getBrandInfo.do?parentCode="
							+ param;
		$("#"+id).combobox({
			panelHeight:150, 
			url:url,
			valueField: 'ID',    
        	textField: 'NAME',
        	onLoadSuccess : function (data){
		    	$("#smsModel").next('.combo').find('input').blur(function(){
		    		var inputData = $('#smsModel').combobox('getText');
		    		//console.log(data);
		    		for(var i=0;i<data.length;i++){
		    			if(inputData==data[i].NAME){
		    				$('#smsModel').combobox('setValue',data[i].ID);
		    				return;
		    			}
		    		}
		    		$('#smsModel').combobox('clear');
		    	});
		    },
		    onChange :function (){
		    	$("#smsModel").combobox('options').url = url;
		    },
		});
	}
</script>
</head>
<body>
	<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div id="searchPanel" title=" " class="easyui-panel"
			style="width: 100%;" data-options="collapsible:true,border:0">
			<form action="#" name="searchForm" id="searchForm"
				style="display: inline;">
				<table class="searchArea">
					<tr>
						<td>短信类型：</td>
						<td>
							<select id="smsType" name="smsType" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" panelHeight="auto">
								<option value="">-请选择-</option>
								<option value="0">激活</option>
								<option value="1">查询</option>
							</select>
						</td>
						<td>设备品牌：</td>
						<td>
							<input id="smsBrand"  name="smsBrand" type="text" class="easyui-combobox" style="width:150px"/>
						</td>
						<td>设备型号：</td>
						<td>
							<input id="smsModel"  name="smsModel" type="text" class="easyui-combobox" style="width:150px"/>
						</td>
						<td>删除状态：</td>
						<td>
							<select id="deleteFlag" name="deleteFlag" class="easyui-combobox" style="width:100px" panelHeight="auto" editable="false">
								<option value="0">未删除</option>
								<option value="1">已删除</option>
							</select>
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
			<jcdf:auth code="040401">
				<a href="#" id="addButton" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<jcdf:auth code="040402">
				<a href="#" id="editButton" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="040403">
				<a href="#" id="deleteButton" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true">删除</a>
			</jcdf:auth>
		</div>
	</div>
	<!-- 数据展示列表区 -->
	<table id="gpsSMSTemplateDatagrid" tagType="datagrid">
	</table>
</body>
</html>