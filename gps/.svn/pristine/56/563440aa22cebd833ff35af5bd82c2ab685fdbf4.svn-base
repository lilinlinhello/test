<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<script type="text/javascript">
	
	$(function(){
		
		loadBrand("smsBrand","0");
		
		
		$("#addButton").bind("click",function(){
			if(!$("#addForm").form('validate')){
			      return false;
			 }
			var $smsContent = $("#smsContent").val();
			if($smsContent==''){
				alert("短信不能为空！");
				return false;
			}
			var sms = $$.serializeToJson("#addForm");
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			$.ajax({
				
				url:basePath+"/smsTemplate/smsTemplateInsert.do",
				type:'POST',
				data:sms,
				dataType:'json',
				success:function(data){
					$$.closeProcessingDialog();
					if(data && data.result){
						$$.showJcdfMessager('提示消息', data.msg, 'info');
						$$.closeJcdfDialog();
						$$.refreshJcdfDatagrid("gpsSMSTemplateDatagrid");
					}else{
						$$.showJcdfMessager('提示消息', data.msg, 'warning');
					}
				}
				
			})
			
		});
		
		$("#resetButton").bind("click",function(){
			$$.resetContent("addForm");
			$("#smsModel").combobox("loadData",[{text:'',value:''}]);
		});
	});
	
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
		    	$("#smsModel").combobox('clear');
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
		    	$("#smsModel").combobox('options').url = url ;
		    },
		});
	}
	
	
</script>
</head>
<body>
	<div class="easyui-panel" title="新增短信模板" collapsible="true" style="width:100%;">
		<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table id="" width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>短信类型：</span>
					</td>
					<td align="left">
					<select id="smsType" name="smsType" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" panelHeight="auto">
						<option value="0">激活</option>
						<option value="1">查询</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>设备品牌：</span>
					</td>
					<td align="left" >
						<input id="smsBrand"  name="smsBrand" type="text" class="easyui-combobox" style="width:150px" data-options="required:true"/>
						<input id="smsModel"  name="smsModel" type="text" class="easyui-combobox" style="width:150px" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>短信序号：</span>
					</td>
					<td align="left" >
						<input id="smsOrder"  name="smsOrder" type="text" class="easyui-numberbox" style="width:150px" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td class="blue" align="right" width="120px;">
						<span>短信内容：</span>
					</td>
					<td align="left" >
					<input id="smsContent" name="smsContent" field="smsContent"
						class="easyui-textbox" data-options="multiline:true" style="width:300px;height:100px" data-options="required:true"/>
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