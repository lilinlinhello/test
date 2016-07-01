<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑车次信息</title>
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>

<script type="text/javascript">

	var type ='<%= request.getParameter("opType")%>';
	var childCode = '<%= request.getParameter("child_code")%>';

	//初始化渲染
	$(function(){
		$("#opType").combobox({
			value:type,
			readonly:true
		});
		getInfoById();		
		
	});

	//按钮绑定事件
	$(function(){

		$("#saveBtn").bind("click",function(){
			var data = {};
			data["childCode"] = childCode;
			data["type"] = type;
			if(type == "0"){
				data["topName"] = $("#topName").textbox("getText");
			}
			if(type == "1"){
				data["topCode"] = $("#topCode").combobox("getValue");
				data["secondName"] = $("#secondName").textbox("getText");
			}
			if(type == "2"){
				// data["topName"] = $("#topCode").combobox("getText");
				data["topCode"] = $("#topCode").combobox("getValue");
				// data["secondName"] = $("#secondCode").combobox("getText");
				data["secondCode"] = $("#secondCode").combobox("getValue");
				data["thirdName"] = $("#thirdName").textbox("getText");
			}
			if(!validate(data)){
				return;
			}
			$$.openProcessingDialog();
			$.ajax({
				type : "POST",
			    data : data,
			    url : "updateVehicle.do",
			    dataType : "json",
			    success : function(data){
			    	$$.closeProcessingDialog();
			    	if(data && data.result){
			    		$$.showJcdfMessager('提示消息',data.msg,'warning');
			    	}else{
			    		$$.showJcdfMessager('提示消息',data.msg,'warning');
			    	}
			    	$$.closeJcdfDialog();
			    	$$.refreshJcdfDatagrid("vehicleDatagrid");
			    }
			});
		});

		$("#resetBtn").bind("click",function(){
			getInfoById();
		});
	});

	function getInfoById () {
		$.ajax({
			type : "POST",
		    url : "getVehicleById.do",
		    dataType : "json",
		    data : {type:type,childCode:childCode},
		    success : function(data){
		    	if(!data){
		    		$$.showJcdfMessager('提示消息','数据加载失败!','warning');
				} else {
					$("#editForm").form("load",data);
					if(type == "0"){
						$("#companyDiv").css("display","");
						$("#companyDiv2").css("display","none");
						$("#oppDiv").css("display","none");
						$("#oppDiv2").css("display","none");
						$("#vehicleDiv").css("display","none");
					}
					if(type == "1"){
						$("#companyDiv").css("display","");
						$("#companyDiv2").css("display","none");
						$("#oppDiv").css("display","");
						$("#oppDiv2").css("display","none");
						$("#vehicleDiv").css("display","none");
						$("#topName").textbox({readonly:true});
						$("#topCode").combobox("setValue",data.topCode);
					}
					if(type == "2"){
						$("#companyDiv").css("display","");
						$("#companyDiv2").css("display","none");
						$("#oppDiv").css("display","");
						$("#oppDiv2").css("display","none");
						$("#vehicleDiv").css("display","");
						$("#topName").textbox({readonly:true});
						$("#topCode").combobox("setValue",data.topCode);
						$("#secondName").textbox({readonly:true});
						$("#secondCode").combobox("setValue",data.secondCode);
						
					}
		    	}
		    }
		});
	}

	//合法性验证
	function validate(data){
		var pattern = /^[^<>`~!#()=|:,?￥【】。，、\/\'\\\"#$%&\^\*]+$/;
		for(var tmpData in data){
			if(data[tmpData] == ""){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息","请填写"+name.substring(1,name.length-1),"waring");
				return false;
			}
			if(!pattern.test(data[tmpData])){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息",name.substring(1,name.length-1)+"出现非法字符！","waring");
				return false;
			}
			if(data[tmpData].length > 25){
				var name = $.trim($("#"+tmpData).parent().parent().first().text());
				$$.showJcdfMessager("提示信息",name.substring(1,name.length-1)+"长度过长","waring");
				return false;
			}
		}
		return true;
	}

	

</script>
</head>
<body>
		<div class="easyui-panel" data-options="title:'车辆层次信息编辑',collapsible:true">
		<form action="#" id="editForm" name="editForm">
			<table style="width:100%;" cellpadding="5" cellspacing="0" border="1">
				<tr>
					<td align="right" width="150px;">维护类别: </td>
					<td><select name="opType" id="opType" class="easyui-combobox"  style="width:150px;">
						<option value="0">公司信息</option>
						<option value="1">运营类型</option>
						<option value="2">车辆类别</option>
					</select>
					</td>
				</tr>

				<!-- 公司待定区域 -->
				<tr id="companyDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>公司名称: </td>
					<td><input type="text" id="topName" style="width:150px;" name="topName" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]']"></td>
				</tr>
				<!-- 公司下拉区域 -->
				<tr id="companyDiv2">
					<td align="right" width="150px;"><span><font color="red">*</font></span>所属公司: </td>
					<td><input type="text" id="topCode" class="easyui-combobox" style="width:150px;" prompt="请选择公司名称"></td>
				</tr>
				<!-- 运营属性区域 -->
				<tr id="oppDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>运营属性: </td>
					<td><input type="text" id="secondName" name="secondName" class="easyui-textbox" style="width:150px;" data-options="required:true,validType:['maxLength[25]']"></td>
				</tr>
				<!-- 运营下拉区域 -->
				<tr id="oppDiv2">
					<td align="right" width="150px;"><span><font color="red">*</font></span>运营属性: </td>
					<td><input type="text" id="secondCode" class="easyui-combobox" style="width:150px;" prompt="请选择运营属性"></td>
				</tr>
				<!-- 车辆类别 -->
				<tr id="vehicleDiv">
					<td align="right" width="150px;"><span><font color="red">*</font></span>车辆类别: </td>
					<td><input type="text" id="thirdName" name="thirdName" class="easyui-textbox" style="width:150px;" data-options="required:true,validType:['maxLength[25]']"></td>
				</tr>
			</table>
		</form>		
		</div>
		<div id="tool" style="text-align:right;">
			<a href="#" id="saveBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
			<a href="#" id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload" plain="true">重置</a>
		</div>
</body>
</html>