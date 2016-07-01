<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
	var dictionarys = '${requestScope.dictionarys}' ;
	var t = eval('('+dictionarys+')') ;
		$(function(){
			//加载选择下拉框 
			initDictionary(t,"editForm");
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
				$$.serializeToForm("#editForm", editHistoryData,"text");
				$("#userType").combobox("setValue",editHistoryData["userType"]); 
		    });
		})
		
		//从request域中获取userId
		var userId = "<%=request.getParameter("userId")%>";
		var editHistoryData = null;
		/**
		 * 加载航班历史数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/user.do?method=get",
			   dataType:"json",
			   data: {"userId":userId},
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (!data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						editHistoryData = data;
						//填充修改记录的历史数据
						$$.serializeToForm("#editForm", editHistoryData,"text")
						$("#userType").combobox("setValue",editHistoryData["userType"]); 
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
			var user = $$.serializeToJson("#editForm");
			if (!user)return false;
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/user.do?method=update",
			   dataType:"json",
			   data: user,
			   success: function(data){
			   		$$.closeProcessingDialog();
				    if (data && data.result) {
				    	$$.showJcdfMessager('提示消息',data.msg,'info');
						$$.closeJcdfDialog();
						$$.refreshJcdfDatagrid("userDatagrid");
					} else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					}
				}
			});
		}
	</script>
  </head>

  <body>
	   	<form action="#" id="editForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="bule" align="right" width="20%">
						用户账号：
					</td>
					<td align="left">
						<input id="userId" type="text" name="userId" field="userId" readonly="readonly" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						用户名称：
					</td>
					<td align="left">
						<input id="userName" type="text" name="userName" field="userName" class="easyui-textbox" data-options="required:true,validType:['maxLength[25]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						用户类型：
					</td>
					<td align="left">
						<input id="userType" name="userType"  type="text" class="easyui-combobox" panelHeight="auto" data-options='editable:false'>
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