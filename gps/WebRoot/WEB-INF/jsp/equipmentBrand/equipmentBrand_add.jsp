<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加设备信息</title>
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>

<script type="text/javascript">
	//按钮绑定事件
	$(function(){

		//暂存数据到表格
		$("#addBtn").bind("click",function(){
			//获取数据
			var data = {};
			var opType = $("#opType").combobox("getValue");
			if(opType == "0"){
				data["topName"] = $("#topName").textbox("getText");
				$("#topName").textbox("clear");
			}
			if(opType == "1"){
				data["topName"] = $("#topCode").combobox("getText");
				data["topCode"] = $("#topCode").combobox("getValue");
				data["secondName"] = $("#secondName").textbox("getText");
				$("#secondName").textbox("clear");
			}
			/*if(opType == "2"){
				data["topName"] = $("#topCode").combobox("getText");
				data["topCode"] = $("#topCode").combobox("getValue");
				data["secondName"] = $("#secondCode").combobox("getText");
				data["secondCode"] = $("#secondCode").combobox("getValue");
				data["thirdName"] = $("#thirdName").textbox("getText");
			}*/
			if(!validate(data)){
				return;
			}
			checkRepeatData(data);
			$("#tempDatagrid").datagrid("appendRow",data);
			//clearForm();
		});


		//删除事件
		$("#removeBtn").bind("click",function(){
			var selectRows = $("#tempDatagrid").datagrid("getSelections");
			if(selectRows.length == 0){
				$$.showJcdfMessager("提示信息","请选择要删除的数据","waring");
				return;
			}
			for(var i = selectRows.length - 1; i >= 0; i--){
				var index = $("#tempDatagrid").datagrid("getRowIndex",selectRows[i]);
				$("#tempDatagrid").datagrid("deleteRow",index);
			}
		});

		$("#resetBtn").bind("click",function(){
			clearForm();
		});

		//提交所有编辑数据
		$("#saveBtn").bind("click",function(){
			var rows = $("#tempDatagrid").datagrid("getRows") ;
			if(rows!=null && rows.length==0){
			   $.messager.alert('提示消息',"列表不能为空！请先暂存数据再保存！" , 'warning');
			   return ;
			}
			var jsonDataStr = $$.stringify(rows); //数组 字符串 
			var opType = $("#opType").combobox("getValue");
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type : "POST",
			   url : "insertBrands.do",
			   dataType : "json",
			   data : {opType:opType,jsonDataStr:jsonDataStr},
			   success : function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
			   		for(var i = rows.length - 1; i >= 0; i--){
						var index = $("#tempDatagrid").datagrid("getRowIndex",rows[i]);
						$("#tempDatagrid").datagrid("deleteRow",index);
					}
					$$.showJcdfMessager('提示消息',data.msg,'warning');
					clearForm();
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			     $$.refreshJcdfDatagrid("brandDatagrid");
			   }
			});
		});
	});

	$(function(){

		//切换类别隐藏布局，删除数据
		$("#opType").combobox({
			onChange:function(newValue, oldValue ){
				var rows = $("#tempDatagrid").datagrid("getRows");
				for(var i = rows.length - 1; i >= 0; i--){
					var index = $("#tempDatagrid").datagrid("getRowIndex",rows[i]);
					$("#tempDatagrid").datagrid("deleteRow",index);
				}
				if(newValue == "0"){
					$("#companyDiv").css("display","");
					$("#companyDiv2").css("display","none");
					$("#oppDiv").css("display","none");
					// $("#oppDiv2").css("display","none");
					// $("#vehicleDiv").css("display","none");
					// $("#tempDatagrid").datagrid("hideColumn","thirdName");
					$("#tempDatagrid").datagrid("hideColumn","secondName");
					$("#tempDatagrid").datagrid("showColumn","topName");
				}
				if(newValue == "1"){
					$("#companyDiv").css("display","none");
					$("#companyDiv2").css("display","");
					$("#oppDiv").css("display","");
					// $("#oppDiv2").css("display","none");
					// $("#vehicleDiv").css("display","none");					
					// $("#tempDatagrid").datagrid("hideColumn","thirdName");
					$("#tempDatagrid").datagrid("showColumn","secondName");
					$("#tempDatagrid").datagrid("showColumn","topName");
					loadBrand("topCode","0");
				}
				// if(newValue == "2"){
				// 	$("#companyDiv").css("display","none");
				// 	$("#companyDiv2").css("display","");
				// 	$("#oppDiv").css("display","none");
				// 	$("#oppDiv2").css("display","");
				// 	$("#vehicleDiv").css("display","");
				// 	$("#tempDatagrid").datagrid("showColumn","thirdName");
				// 	$("#tempDatagrid").datagrid("showColumn","secondName");
				// 	$("#tempDatagrid").datagrid("showColumn","topName");
				// 	loadBrand("topCode","0");
				// }
			}
		});
	});

	function clearForm () {
		$("#addForm #topName").textbox("clear");
		$("#addForm #topCode").combobox("clear");
		$("#addForm #secondName").textbox("clear");
		// $("#addForm #secondCode").combobox("clear");
		// $("#addForm #thirdName").textbox("clear");
	}
	function validate(data){
		var pattern = /^[^<>`~!#()=|:,?￥【】。，、\/\'\\\"#$%&\^\*]+$/;
		for(var tmpData in data){
			if(data[tmpData] == ""){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息","请填写"+name.substring(1,name.length-1),"waring");
				return false;
			}
			/* if(!pattern.test(data[tmpData])){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息",name.substring(1,name.length-1)+"出现非法字符！","waring");
				return false;
			} */
			if(data[tmpData].length > 25){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息",name.substring(1,name.length-1)+"长度过长","waring");
				return false;
			}
		}
		return true;
	}

	function checkRepeatData (data) {
		var opType = $("#opType").combobox("getValue");
		var rows = $("#tempDatagrid").datagrid("getRows");
		for(var i = 0 ; i < rows.length ;i++){
			
			if(opType == "0" && rows[i].topName == data.topName){
				var index = $("#tempDatagrid").datagrid("getRowIndex",rows[i]);
				$("#tempDatagrid").datagrid("deleteRow",index);
			}
			if(opType == "1" && rows[i].topName == data.topName 
				&& rows[i].secondName == data.secondName){
				var index = $("#tempDatagrid").datagrid("getRowIndex",rows[i]);
				$("#tempDatagrid").datagrid("deleteRow",index);
			}
			/*if(opType == "2" && rows[i].topName == data.topName 
				&& rows[i].secondName == data.secondName
				&& rows[i].thirdName == data.thirdName){
				var index = $("#tempDatagrid").datagrid("getRowIndex",rows[i]);
				$("#tempDatagrid").datagrid("deleteRow",index);
			}*/
		}
	}
	//初始化渲染
	$(function(){
		$("#companyDiv").css("display","");
		$("#companyDiv2").css("display","none");
		$("#oppDiv").css("display","none");
		// $("#oppDiv2").css("display","none");
		// $("#vehicleDiv").css("display","none");
		// $("#tempDatagrid").datagrid("hideColumn","thirdName");
		$("#tempDatagrid").datagrid("hideColumn","secondName");
		$("#tempDatagrid").datagrid("showColumn","topName");
	});

	//加载设备品牌数据
	function loadBrand (id,param) {
		var url = "getBrandInfo.do?parentCode="+param;
		console.log(url);
		$("#"+id).combobox({
			panelHeight:"150",
			url:url,
			valueField: 'id',    
        	textField: 'name',
    		onLoadSuccess : function (data){
        		//失去焦点触发事件，手动对combobox赋value值
        		$("#topCode").next('.combo').find('input').blur(function(){
        			var inputData = $("#topCode").combobox("getText");
        			for(var i = 0 ;i < data.length ;i++){
        				var tmp = data[i];
        				if(tmp.name == inputData){
        					$("#topCode").combobox("setValue",tmp.id);
        					return;
        				}
        			}
        			$("#topCode").combobox("clear");
        		});
        	},
        	onSelect:function(record){
        		//比loadSuccess后触发，事件不触发手动获取value
        		//事件触发自动获取最终值
				//console.log($("#topCode").combobox("getValue")+"====="+$("#topCode").combobox("getText"));
        	}
        		
		});
	}
	//加载设备型号数据
	// function loadOperation (id,param) {
	// 	var url = "getBrandInfo.do?parentCode="+param;
	// 	console.log(url);
	// 	$("#"+id).combobox({
	// 		panelHeight:"auto",
	// 		url:url,
	// 		valueField: 'id',    
 //        	textField: 'name'        		
	// 	});
	// }
</script>
</head>
<body>
		<div class="easyui-panel" data-options="title:'设备信息添加',collapsible:true">
		<form action="#" id="addForm" name="addForm">
			<table style="width:100%;" cellpadding="5" cellspacing="0" border="1">
				<tr>
					<td align="right" width="150px;">维护类别: </td>
					<td><select name="opType" id="opType" class="easyui-combobox"  style="width:150px;" data-options="panelHeight:'auto',editable:false">
						<option value="0">设备品牌</option>
						<option value="1">设备型号</option>
						<!-- <option value="2">车辆类别</option> -->
					</select>
					<span><font color="red">切换类型，请先保存数据</font></span>
					</td>
				</tr>

				<!-- 设备待定区域 -->
				<tr id="companyDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>设备品牌: </td>
					<td><input type="text" id="topName" style="width:150px;" name="topName" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]']"></td>
				</tr>
				<!-- 设备下拉区域 -->
				<tr id="companyDiv2">
					<td align="right" width="150px;"><span><font color="red">*</font></span>设备品牌: </td>
					<td><input type="text" id="topCode" class="easyui-combobox" style="width:150px;" prompt="请选择设备品牌" data-options="required:true">
						<span style="color:red">设备品牌不存在时请先在维护类别添加</span></td>
				</tr>
				<!-- 设备型号区域 -->
				<tr id="oppDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>设备型号: </td>
					<td><input type="text" id="secondName" name="secondName" class="easyui-textbox" style="width:150px;" data-options="required:true,validType:['maxLength[25]']"></td>
				</tr>
				<!-- 设备型号下拉区域 -->
				<!-- <tr id="oppDiv2">
					<td align="right" width="150px;"><span><font color="red">*</font></span>设备型号: </td>
					<td><input type="text" id="secondCode" class="easyui-combobox" style="width:150px;" prompt="请选择设备型号" data-options="required:true">
						<span style="color:red">设备型号不存在时请先在维护类别添加</span></td>
				</tr> -->
				<!-- 车辆类别 -->
				<!-- <tr id="vehicleDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>车辆类别: </td>
					<td><input type="text" id="thirdName" name="thirdName" class="easyui-textbox" style="width:150px;" data-options="required:true"></td>
				</tr> -->
			</table>
		</form>		
		</div>

	<div id="tool" style="text-align:right;">
		<a href="#" id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">暂存</a>
		<a href="#" id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<a href="#" id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload" plain="true">重置</a>
		<a href="#" id="saveBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
	</div>
	<div id="saveArea" name="saveArea" class="easyui-panel" data-options="title:'设备待保存数据',collapsible:true">
		<div id="tempData">
			<table id="tempDatagrid" class="easyui-datagrid" width="100%" data-options="singleSelect:false">
				<thead>
					   <tr>
					        <th data-options="field:'ck',checkbox:true"></th>
					       	<th align="center" data-options="field:'topName',width:$$.fillsize(0.4)">设备品牌</th>
					       	<th align="center" data-options="field:'secondName',width:$$.fillsize(0.4)">设备型号</th>
					       	<!-- <th align="center" data-options="field:'thirdName',width:$$.fillsize(0.3)">车辆类型</th> -->
					       	<!-- <th align="center" data-options="field:'secondCode',width:$$.fillsize(0.3),hidden:true"></th> -->
					       	<th align="center" data-options="field:'topCode',width:$$.fillsize(0.3),hidden:true"></th>
					    </tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>