<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>新增应用</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	submitAddData();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	$$.resetContent("addForm");
		    });
		    $("#ipWhiteList").textbox('setValue','0.0.0.0');
		})
		
		

		/**
		 * 保存新增应用信息
		 */
		function submitAddData() {
			if(!$("#addForm").form('validate')){
		      return false;
		    }
			var appReg = $$.serializeToJson("#addForm");
			if (!appReg)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/appReg/appReg.do?method=insert",
			   dataType:"json",
			   data: appReg,
			   success: function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info')
					$$.closeJcdfDialog();
					$$.refreshJcdfDatagrid("appRegDatagrid");
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			   }
			});
		}
	</script>
  </head>
  
  <body >
	   	<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
	   			<tr >
					<td class="bule" align="right" width="20%">
						应用类型：
					</td>
					<td align="left"><select id="appType" class="easyui-combobox" style="width:100px;" panelHeight="auto" 
							name="appType" data-options="required:true,editable:false">
							<option value = "0">服务端</option>
							<option value = "1">浏览器端</option>
							</select>
					</td>
				</tr>
				<tr >
					<td class="bule" align="right" >
						应用名称：
					</td>
					<td align="left">
						<input type="text" id="appName" name="appName"   class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','cne']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						联系人：
					</td>
					<td align="left">
						<input type="text" name="contMan" id = "contMan"  class="easyui-textbox" data-options="required:true,validType:['maxLength[10]','chinese']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						所属部门：
					</td>
					<td align="left">
						<input type="text" name="department" id = "department"  class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','cne']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						联系电话：
					</td>
					<td align="left">
						<input type="text" name="phoneNo" id = "phoneNo"  class="easyui-textbox" data-options="required:true,validType:['mobile']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						BQQ：
					</td>
					<td align="left">
						<input type="text" id = "bqq" name="bqq" class="easyui-numberbox" data-options="required:true,validType:['maxLength[11]']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						IP白名单：
					</td>
					<td align="left">
						<input type="text" id = "ipWhiteList" name="ipWhiteList" class="easyui-textbox" data-options="required:true,validType:['ips']"/>
					<font style="color:red">*</font> 
					<font >默认填0.0.0.0&nbsp;&nbsp;&nbsp;&nbsp;多个用英文符号，分隔</font> 
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