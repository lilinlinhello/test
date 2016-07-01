<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改报警参数</title>
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
</head>
<script type="text/javascript">

	var param = ${alarmParam};
	
	$(function(){
		
		$("#addForm").form("load",param);
		$('#apType').combobox('select', param.apType);
		
		if(param.apType != '22'){
			$("#distanceField").css("display","none");
			$("#collectionField").css("display","none");
		}
		

		$("#addButton").click("click",function(){
			//检验参数			
			if(! validate()){
				return false;
			}
			//保存
			var param = $("#addForm").serialize() ;			
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
				type : "POST",
				url : "update.do",
				dataType : "json",
				data : param,
				success : function(data) {
					$$.closeProcessingDialog();
					if (data && data.result) {
						$$.showJcdfMessager('提示消息', data.msg, 'info')
						$$.closeJcdfDialog();
						$$.refreshJcdfDatagrid("alarmParamDatagrid");
					} else {
						$$.showJcdfMessager('提示消息', data.msg, 'warning');
					}
				}
			});
		});
		
		//重置按钮单击事件
		$("#resetButton").bind("click", function() {
			$$.resetContent("addForm");
		});
	});
	
	//验证form
	function validate(){
		var apType = $("#apType").combobox("getValue");
		if(!apType){
			$$.showJcdfMessager('', '选择报警类型', 'warning');
			return false;
		}
		if(apType == 22){
			if(!$("#addForm").form('validate')){
				$$.showJcdfMessager('', '填写内容有误', 'warning');
				return false;
			}
		}
		
		if(!$("#duration").numberbox("getValue")){
			$$.showJcdfMessager('', '填写报警时长', 'warning');
			return false;
		}
		if(!$("#startTime").datebox("getValue")){
			$$.showJcdfMessager('', '填写生效时间', 'warning');
			return false;
		}
		
		return true;
	}

	
</script>
<body >
	   	<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<input type="hidden" name="apId">				
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
	   			<tr style = "dsiplay:block">
					<td class="bule" align="right"> 报警类型： </td>
					<td align="left">
						<select id="apType" class="easyui-combobox" name="apType" style="width:140px;" data-options="required:true,readonly:true" >   
							    <option value=""></option>
							    <option value="12">低速报警</option>
							    <option value="13">无信号报警</option>
							    <option value="22">线路偏移报警</option>
						</select>
					</td>
				</tr>
				<tr id="distanceField">
					<td class="bule" align="right">
						偏移距离(米)：
					</td>
					<td align="left">
						<input type="text" name="distance" id = "distance" field="distance" class="easyui-numberbox" data-options="required:true,validType:['maxLength[10]','integer']"/>
					</td>
				</tr>
				
				<tr id="collectionField">
					<td class="bule" align="right">
						GPS点数：
					</td>
					<td align="left">
						<input type="text" name="collection" id = "collection" field="distance" class="easyui-numberbox" data-options="required:true,validType:['maxLength[10]','integer']"/>
					</td>
				</tr>
				
				<tr >
					<td class="bule" align="right">
						报警时长(分钟)：
					</td>
					<td align="left">
						<input type="text" name="duration" id = "duration" field="duration" class="easyui-numberbox" data-options="required:true,validType:['maxLength[10]','integer']"/>
					</td>
				</tr>
				
				<tr >
					<td class="bule" align="right"> 生效时间 ： </td>
					<td align="left">
						<input type="text" id="startTime" name="startTime"  field="startTime" class="easyui-datebox" data-options="required:true"/>
					</td>
				</tr>
			</table>
		</form>
	    
	    <div  style="position: absolute;bottom:0px;right:0px;background-color: #F4F4F4;height: 40px;width: 100%;text-align: right;">
	   		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
	   		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	   	</div>
</body>
</html>