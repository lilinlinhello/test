<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>应用注册信息修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		
		var ak = "<%=request.getParameter("ak")%>";
		var editHistoryData = null;
		
		$(function(){
			//加载修改数据
			loadEditData();
			
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	submitEditData();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	//重置新增窗口中的所有控件
				$$.resetContent("editForm");
				//填充修改记录的历史数据
				$$.serializeToForm("#editForm", editHistoryData);
				selectAppType();
		    });
		    
		  
		})
		
		function selectAppType(){
		var appType = $("#appType").combobox('getValue');
        $("#appType").combobox('setValue',appType);
		}
		/**
		 * 加载应用注册数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/appReg/appReg.do?method=get",
			   dataType:"json",
			   data: {"ak":ak},
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (null == data || "" == data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						editHistoryData = data;
						//填充修改记录的历史数据
						$$.serializeToForm("#editForm", editHistoryData);
						selectAppType();
					}
			   }
			});
		}
		
		/**
		 *	提交修改数据
		 */
		function submitEditData() {
			if(!$("#editForm").form('validate')){
		      return false;
		    }
		  	//获取表单数据
			var appReg = $$.serializeToJson("#editForm");
			if (!appReg)return false;
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/appReg/appReg.do?method=update",
			   dataType:"json",
			   data: appReg,
			   success: function(data){
			   		$$.closeProcessingDialog();
				    if (data.result) {
				    	$.messager.alert('提示消息',data.msg,'info', function () {
							$$.closeJcdfDialog();
							$$.refreshJcdfDatagrid("appRegDatagrid");
						});
					} else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					}
				}
			});
		}
	</script>
  </head>

  <body >
	   	<form action="#" id="editForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
	   			<tr >
					<td class="bule" align="right" width="20%">
					授权码：
					</td>
					<td align="left">
					    <input id="ak" name="ak"  field="ak" field="ak" class="easyui-textbox"  data-options="readonly:true"/>
					</td>
				</tr>
	   			<tr >
					<td class="bule" align="right" >
						应用类型：
					</td>
					<td align="left">
					    <select id="appType" name="appType"  field="appType" class="easyui-combobox"  data-options="required:true,editable:false"
					    style="width:100px;" panelHeight="auto" >
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
						<input type="text" id="appName" name="appName"  field="appName" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','cne']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						联系人：
					</td>
					<td align="left">
						<input type="text" name="contMan" id = "contMan" field="contMan" class="easyui-textbox" data-options="required:true,validType:['maxLength[10]','chinese']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						所属部门：
					</td>
					<td align="left">
						<input type="text" name="department" id = "department" field="department" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','cne']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						联系电话：
					</td>
					<td align="left">
						<input type="text" name="phoneNo" id = "phoneNo" field="phoneNo" class="easyui-textbox" data-options="required:true,validType:['mobile']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						BQQ：
					</td>
					<td align="left">
						<input type="text" id = "bqq" name="bqq" field="bqq" class="easyui-numberbox" data-options="required:true,validType:['maxLength[11]']"/>
					<font style="color:red">*</font> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						IP白名单：
					</td>
					<td align="left">
						<input type="text" id = "ipWhiteList" name="ipWhiteList" field="ipWhiteList" class="easyui-textbox" data-options="required:true,validType:['ips']"/>
					<font style="color:red">*</font> 
					<font >默认填0.0.0.0&nbsp;&nbsp;&nbsp;&nbsp;多个用英文符号，分隔</font> 
					</td>
				</tr>
			</table>
   		</form>
	    
	    <div style="position: absolute;bottom:0px;right:0px;background-color: #F4F4F4;height: 40px;width: 100%;text-align: right;">
	   		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
	   		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	   	</div>
  </body>
</html>