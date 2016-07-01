<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>车辆安装维护</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	
	<script type="text/javascript">
		var dictionarys = '${requestScope.dictionarys}' ;
		var t = eval('('+dictionarys+')') ;
		
		//用户判断发送短信时，是否查询了设备品牌和设备型号
		var equipBrandExit='';
		var equipTypeExit='';
	
		$(function(){
			//加载选择下拉框 
			initDictionary(t,"searchForm");
			
			//加载公司名称数据
			loadCompany("corpId","0");
			//加载设备品牌 
			loadBrand("equipBrand","0");
			loadDatagrid();
			
			
			//新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	$$.openJcdfDialog(basePath+'/vehicleMaintain/vehicleMaintainAdd.do', ' ',
		    				$$.getDatagridHeight()*0.85, $$.getDatagridWidth()*0.85);
		    });
			
			//修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("vehicleMaintainDatagrid", '请选择一条记录进行操作！');
		    	
		    	if(null != selectRow){
		    		var deleteFlag = selectRow.deleteFlag;
			    	if(deleteFlag=="1"){
			    		 $$.showJcdfMessager('提示消息',"已删除的数据不能修改",'info');
			    		 return false;
			    	}
		    		$$.openJcdfDialog(basePath+'/vehicleMaintain/vehicleMaintainEdit.do?id='+selectRow.id, ' ',
		    				$$.getDatagridHeight()*0.85, $$.getDatagridWidth()*0.85);
		    	}
		    });
		    //查询按钮单击事件
		    $("#search_button").bind("click",function(){
			    if(!$("#searchForm").form('validate')){
			      return false;
			  	}
		    	var params = $$.serializeToJson("#searchForm");
		    	//console.log(params);
		    	equipBrandExit = params.equipBrand;
		    	equipTypeExit = params.equipType;
				$('#vehicleMaintainDatagrid').datagrid('options').queryParams=params;
				$('#vehicleMaintainDatagrid').datagrid('load');
				
		    });
		    
		    
		    //删除按钮单击事件
		    $("#deleteButton").bind("click",function(){
		    	var deleteNotes = $$.getSelectIds("vehicleMaintainDatagrid","请选择要删除的记录","id");
		    	if(deleteNotes){
		    	//alert(deleteNotes.ids);
		    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
		    	}
		    });
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
				$("#typeId").combobox("loadData",[{text:'',value:''}]);
				$("#operAttr").combobox("loadData",[{text:'',value:''}]);
				$("#typeId").combobox("clear");
				$("#equipType").combobox("loadData",[{text:'',value:''}]);
				 equipBrandExit='';
				 equipTypeExit='';
			});
			
			// export
		    $("#export").bind("click", function(){
		    	var params = $$.stringify($$.serializeToJson("#searchForm")) ;
				$("#postForm")[0].action = basePath+'/vehicleMaintain/vehicleMaintainExport.do';
				$("#postForm")[0].param.value = params;
				$("#postForm")[0].submit() ;
		    });
		    
		    $('#deleteFlag').combobox({
		    	panelHeight:"auto",
		    	width:100,
		    	editable:false
		    });
		    
		    $('#selectItem').combobox({
		    	panelHeight:"auto",
		    	width:100,
		    	editable:false,
		    	onChange:function(){
		    		$("#licensecard").textbox('clear');
		    	},
		    });
		});
		
		
		//加载公司的数据
		function loadCompany(id,param){
			//$("#operAttr").combobox("readonly",true) ;
			//$("#typeId").combobox("readonly",true) ;
			var url = 'getCompanyInfo.do?parentCode='+param;
			$("#"+id).combobox({
				panelHeight:150,
				url:url,
				valueField: 'ID',    
	        	textField: 'NAME',
			    onLoadSuccess:function(data){
			    	$("#corpId").next('.combo').find('input').blur(function(){
			    		var inputData = $('#corpId').combobox('getText');
			    		for(var i=0;i<data.length;i++){
			    			//console.log(inputData+"..."+data[i].NAME+"..."+data[i].ID);
			    			if(inputData==data[i].NAME){
			    				$('#corpId').combobox('setValue',data[i].ID);
			    				loadOperationAttribute("operAttr",$("#corpId").combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#corpId').combobox('clear');
			    	});
			    },
			    
			    onChange :function (){
			    	$("#typeId").combobox("loadData",[{text:'',value:''}]);
					$("#operAttr").combobox("clear");
					$("#typeId").combobox("clear");
			    },
	        	onSelect:function(recode){
	        		loadOperationAttribute("operAttr",$("#corpId").combobox("getValue"));
	        	} 
			});
		}
		
		//加载运营属性
		function loadOperationAttribute(id,param){
			//$("#operAttr").combobox("readonly",false) ;
			var url = 'getCompanyInfo.do?parentCode='+param;
			$("#typeId").combobox("clear");
			$("#"+id).combobox({
				panelHeight:120,
				url:url,
				valueField: 'ID',    
	        	textField: 'NAME',
	        	onLoadSuccess : function (data){
			    	$("#operAttr").next('.combo').find('input').blur(function(){
			    		var inputData = $('#operAttr').combobox('getText');
			    		//console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#operAttr').combobox('setValue',data[i].ID);
			    				loadVehicleClassification("typeId",$("#operAttr").combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#operAttr').combobox('clear');
			    	});
			    },
	        	onSelect:function(recode){
	     			loadVehicleClassification("typeId",$("#operAttr").combobox("getValue"));
	        	}
			});
		}
		
		//加载车辆分类
		function loadVehicleClassification(id,param){
			//$("#typeId").combobox("readonly",false) ;
			var url = 'getCompanyInfo.do?parentCode='+param;
			$("#"+id).combobox({
				panelHeight:150, 
				url:url,
				valueField: 'ID',    
	        	textField: 'NAME',
	        	onLoadSuccess : function (data){
			    	$("#typeId").next('.combo').find('input').blur(function(){
			    		var inputData = $('#typeId').combobox('getText');
			    		console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#typeId').combobox('setValue',data[i].ID);
			    				return;
			    			}
			    		}
			    		$('#typeId').combobox('clear');
			    	});
			    },
			    onChange :function (){
			    	$("#typeId").combobox('options').url = url ;
			    },
			});
		}
		
		/**
		*发送短信 
		*/
		function smsSend(param){
			var dataJson='';
			if(equipBrandExit=='' || equipTypeExit==''){
				 $$.showJcdfMessager('提示消息',"请先查询出相同设备品牌和设备型号的车辆",'info');
				 return false;
			}
			var checkedRows = $('#vehicleMaintainDatagrid').datagrid('getChecked');
			var num = checkedRows.length;
			for(var i=0;i<num;i++){
				//console.log(rows[i]);
				var deleteFlag = checkedRows[i].deleteFlag;
				if(deleteFlag=='1'){
					 $$.showJcdfMessager('提示消息',"已删除的数据不能发送短信",'info');
			    		return false;
				}
			}
			var selectRows = $$.getSelectIds("vehicleMaintainDatagrid","请选择要发送的记录","id");
			if(param=='smsActiveSend'){
			$$.openJcdfDialog(basePath+'/smsSend/selectSmsSendTemplate.do?id='+selectRows.ids+
					'&equipBrand='+equipBrandExit+'&equipType='+equipTypeExit+"&smsType=0",
					' ',$$.getDatagridHeight()*0.5, $$.getDatagridWidth()*0.4);
	    			//dataJson={"id":selectRows.ids,"smsType":0};
	    	}
	    	if(param=='smsSelectSend'){
	    		$$.openJcdfDialog(basePath+'/smsSend/selectSmsSendTemplate.do?id='+selectRows.ids+
						'&equipBrand='+equipBrandExit+'&equipType='+equipTypeExit+"&smsType=1",
						' ',$$.getDatagridHeight()*0.5, $$.getDatagridWidth()*0.4);
	    	}
	    	
			
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
			    	$("#equipBrand").next('.combo').find('input').blur(function(){
			    		var inputData = $('#equipBrand').combobox('getText');
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#equipBrand').combobox('setValue',data[i].ID);
			    				loadOperation("equipType",$("#equipBrand").combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#equipBrand').combobox('clear');
			    	});
			    },
			    
			    onChange :function (){
			    	$("#equipType").combobox('clear');
			    },
	        	onSelect:function(recode){
	        		loadOperation("equipType",$("#equipBrand").combobox("getValue"));
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
			    	$("#equipType").next('.combo').find('input').blur(function(){
			    		var inputData = $('#equipType').combobox('getText');
			    		//console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#equipType').combobox('setValue',data[i].ID);
			    				return;
			    			}
			    		}
			    		$('#equipType').combobox('clear');
			    	});
			    },
			    onChange :function (){
			    	$("#equipType").combobox('options').url = url ;
			    },
			});
		}
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		 function loadDatagrid(){
		 	$('#vehicleMaintainDatagrid').datagrid({
		 		height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap : true,
				striped : true,
				url : basePath+'/vehicleMaintain/vehicleMaintainQuery.do',
				idField : 'id',
				frozenColumns:[[
								{field:'ck',checkbox:true,align:'center'}
							]],
							  columns:[[
							  	{field:'corpId',title:'单位',widtgh:$$.fillsize(0.06),align:'center'},
							  	{field:'operAttr',title:'运营属性',width:$$.fillsize(0.06),align:'center'},
							  	{field:'typeId',title:'车辆分类',width:$$.fillsize(0.06),align:'center'},
							  	{field:'licensecard',title:'车牌号',width:$$.fillsize(0.06),align:'center'},
							  	{field:'simPhoneno',title:'SIM卡号',width:$$.fillsize(0.06),align:'center'},
							  	{field:'simId',title:'SIM卡ID',width:$$.fillsize(0.06),align:'center'},
							  	{field:'equipId',title:'车载设备',width:$$.fillsize(0.06),align:'center'},
							  	{field:'equipBrand',title:'设备品牌',width:$$.fillsize(0.05),align:'center'},
							  	{field:'equipType',title:'设备型号',width:$$.fillsize(0.05),align:'center'},
							  	{field:'vehicleId',title:'车辆ID',width:$$.fillsize(0.06),align:'center'},
							  	{field:'camcorderMemory',title:'摄录一体机容量',width:$$.fillsize(0.06),align:'center'},
							  	{field:'longtimeElectric',title:'常通电',width:$$.fillsize(0.03),align:'center'},
							  	{field:'acc',title:'接Acc',width:$$.fillsize(0.03),align:'center'},
							  	{field:'installUser',title:'安装人',width:$$.fillsize(0.06),align:'center'},
							  	{field:'installDate',title:'安装日期',width:$$.fillsize(0.06),align:'center'},
							  	{field:'isGuard',title:'安装防盗器',width:$$.fillsize(0.04),align:'center'},
							  	{field:'checkLineType',title:'检测线路配置方案',width:$$.fillsize(0.08),align:'center'},
							  	{field:'openfixType',title:'开关门状态',width:$$.fillsize(0.06),align:'center'},
							  	{field:'isTemp',title:'临时车',width:$$.fillsize(0.04),align:'center'},
							  	{field:'recentData',title:'数据包',width:$$.fillsize(0.04),align:'center',
							  		formatter:function(value,row,index){
					 					var rows = $("#vehicleMaintainDatagrid").datagrid("getRows");
					 					var vehId = rows[index].vehicleId;
										return "<a style='color:red' href='javascript:void(0)' onclick='fnDetailInfo(\""+vehId+"\")'>"+"详情"+"</a>";
									}
							  	},
							  	{field:'status',title:'状态',width:$$.fillsize(0.04),align:'center'},
							  	{field:'statusRemark',title:'备注状态',width:$$.fillsize(0.06),align:'center'},
							  	{field:'deleteFlag',title:'删除状态',width:$$.fillsize(0.04),align:'center',
							  			formatter:function(value,row,index){
												if('0'==value){
													return "<font color='green'>未删除</font>";
												}
												if('1'==value){
													return "<font color='red'>已删除</font>";
												}
											}
							  	},
							  	{field:'createBy',title:'创建人',width:$$.fillsize(0.06),align:'center'},
							  	{field:'createTime',title:'创建时间',width:$$.fillsize(0.06),align:'center'},
							  	{field:'updateBy',title:'更新人',width:$$.fillsize(0.06),align:'center'},
							  	{field:'updateTime',title:'更新时间',width:$$.fillsize(0.06),align:'center'},
							  ]],
				onBeforeLoad : function(){$$.clearSelect("vehicleMaintainDatagrid");},
// 				onClickCell:function(index, field, value){
// 					var rows = $("#warehouseDatagrid").datagrid("getRows");
// 					var dataDetail = rows[index].recentData;
// 				},
				pagination : true,
				rownumbers : true,
				singleSelect:false,
				pageList : [ 10, 15, 20, 30, 50, 100 ],
		 		pageSize:15,
		 		queryParams:{deleteFlag:"0"},
		 		toolbar:'#menu'
		 	});
		
		 }
		 	/*
		 	*删除记录
		 	*/
		 	function deleteNoteById(ids){
		 		$$.openProcessingDialog();
		 		$.post("vehicleMaintainDelete.do",{
		 				"ids":ids
		 				},function(data){
		 				$$.closeProcessingDialog();
		 				if (data && data.result) {
							$$.showJcdfMessager('提示消息', data.msg, 'info');
							$$.refreshJcdfDatagrid("vehicleMaintainDatagrid");
						} else {
							$$.showJcdfMessager('提示消息', data.msg, 'warning');
						}
						$$.clearSelect("vehicleMaintainDatagrid");
		 		}, 'json');
		 	}
		function fnDetailInfo(vehicleId){
		   	$$.openJcdfDialog(basePath+'/vehicleMaintain/forwardDetailJsp.do?vehicleId='+vehicleId, ' ',
    				$$.getDatagridHeight()*0.85, $$.getDatagridWidth()*0.85);
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
				    <td>单位：</td>
				    <td>
						<input id="corpId" name="corpId" type="text" class="easyui-combobox" prompt="请选择单位名称">
				    </td>
				    <td>运营属性：</td>
				    <td>
				    	<input id="operAttr" name="operAttr" type="text" class="easyui-combobox" prompt="请选择运营属性">
				    </td>

				    <td>车辆分类：</td>
				    <td>
				    	<input id="typeId" name="typeId" type="text" class="easyui-combobox" prompt="请选择分类">
				    </td>
				    
				    <td>状态：</td>
				    <td>
				    	<input id="status" name="status" type="text" class="easyui-combobox" data-options="panelHeight:120,validType:['unnormal']" prompt="请选择状态">
				    </td>
				    
				    <td>设备品牌：</td>
				    <td>
				    	<input id="equipBrand" name="equipBrand" type="text" class="easyui-combobox" prompt="请选择设备品牌">
				    </td>
				    
				    <td>设备型号：</td>
				    <td>
				    	<input id="equipType" name="equipType" type="text" class="easyui-combobox" prompt="请选择设备型号">
				    </td>
				    
				  </tr>
				  
				  <tr>
				    <td>安装日期：</td>
				    <td>
				    	<input id="minCreateTime" name="minCreateTime" class="easyui-datetimebox" data-options="editable:false" style="width:150px" prompt="最小安装日期"/>
				    </td>
				    <td>	
				    	<input id="maxCreateTime" name="maxCreateTime" class="easyui-datetimebox" data-options="editable:false" style="width:150px" prompt="最大安装日期" />
				    </td>
				    <td></td>
				   

				    <!-- <td>车牌号/SIM卡号/设备编码：</td> -->
				    <td>
				    	<select id="selectItem" name="item" class="easyui-combobox" data-options="validType:['unnormal']">
				    		<option value="车牌号">车牌号</option>
				    		<option value="SIM卡号">SIM卡号</option>
				    		<option value="设备编码">设备编码</option>
				    	</select>
				    </td>
				    <td>
				    	<input id="licensecard" name="licensecard" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    
				    <td>状态备注：</td>
				    <td>
				    	<input id="statusRemark" name="statusRemark" type="text" class="easyui-textbox" data-options="validType:['unnormal']">
				    </td>
				    
				    <td>删除状态：</td>
				    <td>
				    	<select id="deleteFlag" name="deleteFlag" class="easyui-combobox" data-options="validType:['unnormal']">
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
		     
		    <div class="menu-area">
		     <jcdf:auth code="030101">
		   	<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="030102">
		    	<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="030103">
			 	<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
			 <jcdf:auth code="030104">
			 	<a href="#" id="export" class="easyui-linkbutton" iconCls="icon-excel" plain="true">导出</a>
			 </jcdf:auth>
			 <jcdf:auth code="030105">
			 	<a href="#" id="smsActiveSend" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="smsSend('smsActiveSend')">激活短信发送</a>
			 </jcdf:auth>
			 <jcdf:auth code="030106">
			 	<a href="#" id="smsSelectSend" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="smsSend('smsSelectSend')">查询短信发送</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="vehicleMaintainDatagrid" tagType="datagrid">
    </table>
    <form id="postForm" name="postForm" method="post" style="display: inline;" target="downloadFormIframe">
		<input type="hidden" name="param"/>
	</form>
  </body>
</html>
