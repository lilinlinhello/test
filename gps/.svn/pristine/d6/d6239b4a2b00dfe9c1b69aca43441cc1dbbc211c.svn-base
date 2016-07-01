<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>新增GPS设备登记记录页面</title>
<script type="text/javascript">
	var dictionarys = '${requestScope.dictionarys}';
	var t = eval('('+dictionarys+')');
	$(function(){
		//加载选择下拉框
		initDictionary(t,"addForm");
		//加载所有的车牌号
		initCarNumber("originalCarNumber");
		//加载所有的新车牌号 
	    initCarNumber("newCarNumber");
		//加载所有的安装人员 
		initInstallUser("installPerson");
       
	    document.getElementById("repairReasonId").style.display="none";//隐藏维修原因
		document.getElementById("newCarNumberId").style.display="none";//隐藏新车牌号
		document.getElementById("complateDateId").style.display="none";//隐藏新车牌号

		//选择切换报价状态
        $("#equipStatus").combobox({
        	  onLoadSuccess: function () { 
        		var data = $('#equipStatus').combobox('getData');
        	      if (data.length > 0) {
        	         $('#equipStatus').combobox("select", data[0].ID);//安装状态（默认）
        	      }  
        	  },
        	 onChange:function(newV,oldV){
        		document.getElementById("repairReasonId").style.display="none";//隐藏维修原因
        	    document.getElementById("newCarNumberId").style.display="none";//隐藏新车牌号
        		document.getElementById("complateDateId").style.display="none";//隐藏完成日期
        	    clearData();  //清除填写框
        		if(newV=="dic_equipStatus01"){
        		}
        		if(newV=="dic_equipStatus02"){//维修
        			  var data = $('#repairReason').combobox('getData');
            	      if (data.length > 0) {
            	         $('#repairReason').combobox("select",data[7].ID);//维修原因（默认）
            	      }
            	      if($('input:radio:checked').val()==1){
            				document.getElementById("complateDateId").style.display="";//显示完成日期 
            	      }
        			  document.getElementById("repairReasonId").style.display="";//显示维修原因 
        		}
        		if(newV=="dic_equipStatus03"){// 移机
        			 document.getElementById("newCarNumberId").style.display="";//显示新车牌号 
        		}
        	}
        });
	
		//保存按钮单击事件-
		$("#addButton").bind("click",function(){
			   submitAddData();
		});
		
		//重置按钮单击事件
		$("#resetButton").bind("click", function(){
			document.getElementById("repairReasonId").style.display="none";//隐藏维修原因
			document.getElementById("newCarNumberId").style.display="none";//隐藏新车牌号
		  	$$.resetContent("addForm");
	         $('#equipStatus').combobox("setValue","dic_equipStatus01");//安装状态（默认）
		});
	})
	
	//保存数据
	function submitAddData(){
		   if(!$("#addForm").form('validate')){
		      return false;
		   } 
		   var equipSta=$("#equipStatus").combobox('getValue'); 
		   if(equipSta=="dic_equipStatus03"){//设备移机状态
			  if(!checkNew()) {//校验必填的新车牌信息 
				  $.messager.alert('提示消息', "新车牌信息未填写 ！", 'warning');
				  return  false;
			  }
			  if(!checkCarNum()) {//校验新老车牌信息一致 
				  $.messager.alert('提示消息', "新老车牌信息一致！", 'warning');
				  return  false;
			  }
		  }
		  if(!checkDates()){
				$.messager.alert('提示消息', "安装日期需在上报日期之后！", 'warning');
				return  false;
			}
		  if($('input:radio:checked').val()==1){//校验选"是"时 完成日期必填
			  if(!checkDate()) {
				  $.messager.alert('提示消息', "完成日期未填写 ！", 'warning');
				  return  false;
			   } 
		 } 
		 var gpsEquipRegister = $$.serializeToJson("#addForm");
		 if (!gpsEquipRegister) return false;
		 $$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type : "POST",
			   url : basePath+"/gpsEquipRegister/insert.do",
			   dataType : "json",
			   data : gpsEquipRegister,
			   success : function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
					$$.closeJcdfDialog();
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			     $$.refreshJcdfDatagrid("gpsEquipRegisterDatagrid");
			   }
			});
		}
	
	//重置 表格数据
	function clearData(){
		$("#repairReason").combobox('clear');
		$("#newCarNumber").combobox('clear');
	}
	
	//是否单选按钮点击事件 
	function isCom(value){
		if(value==1){
			document.getElementById("complateDateId").style.display="";//显示完成日期 
		}else{
			$("#complateDate").datetimebox("setValue","");
			document.getElementById("complateDateId").style.display="none";//隐藏完成日期 
		}
	}
	
	 //校验新车牌号
	function checkNew(){
		var newCar=$("#newCarNumber").combobox('getValue'); 
        if( newCar==''|| newCar==null){
        	return false;
        }else{
        	return true;
        }
	} 
	 //校验完成日期 
	 function checkDate(){
		var comDate=$("#complateDate").datetimebox('getValue'); 
        if(comDate==''||comDate==null){
        	return false;
        }else{
        	return true;
        }
	}  
	//校验安装日期需在上报日期之后
	 function checkDates(){
		 var reportDate=$("#reportDate").datetimebox('getValue');
		 var installDate=$("#installDate").datetimebox('getValue');
		 if(reportDate!=null && installDate!=null && installDate!=""){
			 if(reportDate>installDate){
				 return false;
			 }
		 }
		 return true;
	 }
	 //校验新老车牌号 
	 function checkCarNum(){
		var oldCar=$("#originalCarNumber").combobox('getValue'); 
		var newCar=$("#newCarNumber").combobox('getValue'); 
		if(oldCar==newCar){
			return false;
		}else{
			return true;
		} 
	} 
</script>
</head>
<body style="background-color:#F4F4F4;">
	<div class="easyui-panel" title="添加信息" collapsible="true" style="width:100%;">
		<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table id="" width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>OA编号：</span>
					</td>
					<td align="left">
					<input  id="oaId" name="oaId" type="text" class="easyui-textbox" style="width:50%" data-options="required:true,validType:['maxLength[28]','unnormal']">
					<font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>设备当前状态：</span>
					</td>
					<td align="left" >
						<input id="equipStatus"  name="equipStatus" field="equipStatus" type="text" panelHeight="auto" 
						class="easyui-combobox" style="width:100px" editable="false"/>
					</td>
				</tr>
				<tr id="repairReasonId">
					<td class="blue" align="right" width="120px;">
						<span>维修原因：</span>
					</td>
					<td align="left" >
					<input id="repairReason"  name="repairReason" field="repairReason"  panelHeight="auto"
						class="easyui-combobox" style="width:150px"  editable="false"/>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px">
						<span>车牌号：</span>
					</td>
					<td align="left" >
						<input id="originalCarNumber"  name="originalCarNumber" data-options="required:true,validType:['unnormal']"
						class="easyui-combobox" style="width:50%" panelHeight="200px" prompt='请选择车牌号' />
						<font style="color:red">*</font> 
					</td>
				</tr>
				<tr id="newCarNumberId">
					<td class="blue" align="right" width="120px">
						<span>新车牌号：</span>
					</td>
					<td align="left" >
						<input id="newCarNumber"  name="newCarNumber"  type="text" 
						class="easyui-combobox" style="width:50%;" panelHeight="200px"  prompt='请选择车牌号' data-options="validType:['unnormal']"/>
						<font style="color:red">*</font> 
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>车线：</span>
					</td>
					<td align="left">
					<input  id="carLine" name="carLine" type="text" class="easyui-textbox" style="width:50%" data-options="required:true,validType:['maxLength[48]','unnormal']">
					<font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>上报日期：</span>
					</td>
					<td align="left">
					<input id="reportDate" name="reportDate" class="easyui-datetimebox" style="width:50%" data-options="required:true" editable="false"/>
					<font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>安装日期：</span>
					</td>
					<td align="left">
					<input id="installDate" name="installDate" class="easyui-datetimebox" style="width:50%" editable="false"/>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>安装人员：</span>
					</td>
					<td align="left">
					<input id="installPerson" name="installPerson" type="text" class="easyui-combobox" style="width:50%"  panelHeight="200px" prompt='请选择安装人员' data-options="validType:['unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>联系电话：</span>
					</td>
					<td align="left">
					<input id="telephone" name="telephone" class="easyui-numberbox" data-options="validType:['mobile']" style="width:50%"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px">
						<span>是否完成：</span>
					</td>
					<td align="left">
						<input type="radio" name="isComplate"  checked="checked"  onclick="isCom(this.value)" value="0"/>否
						<input type="radio" name="isComplate"  onclick="isCom(this.value)"  value="1" />是
					</td>
				</tr>
				<tr id="complateDateId">
					<td class="blue" align="right" width="120px">
						<span>完成日期：</span>
					</td>
					<td align="left">
					<input id="complateDate" name="complateDate" class="easyui-datetimebox" style="width:50%" editable="false"/>
					<font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">备注：</td>
					<td align="left">
					<input id="installComment" type="text" name="installComment"
							class="easyui-textbox" style="width:70%;height:60px;"
							data-options="multiline:true,validType:['maxLength[900]','unnormal']"/>
					</td>
				</tr>
			</table>
			</form>
			
		</div>
		<div style="position:relative;right:0px;background-color: #F4F4F4;height:50px;width: 100%;text-align: right;">
			   	<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
			   	<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
			 </div>
	
</body>

</html>