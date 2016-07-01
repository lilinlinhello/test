<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>修改字典</title>
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
				$("#dicOrder").numberbox("setValue",editHistoryData.dicOrder);
				$('#dicType').combobox('setValue',editHistoryData.dicType);
		    });
		})
		
		//从request域中获取dicId
		var dicId = "<%=request.getParameter("dicId")%>";
		var editHistoryData = null;
		/**
		 * 加载航班历史数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/dictionary.do?method=get",
			   dataType:"json",
			   data: {"dicId":dicId},
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (!data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						editHistoryData = data;
						//填充修改记录的历史数据
						$$.serializeToForm("#editForm", editHistoryData,"text")
						$("#dicOrder").numberbox("setValue",editHistoryData.dicOrder);
						$('#dicType').combobox('setValue',editHistoryData.dicType);
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
			var dictionary = $$.serializeToJson("#editForm");
			if (!dictionary)return false;
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/dictionary.do?method=update",
			   dataType:"json",
			   data: dictionary,
			   success: function(data){
			   		$$.closeProcessingDialog();
				    if (data && data.result) {
				    	$$.showJcdfMessager('提示消息',data.msg,'info');
						$$.closeJcdfDialog();
						$$.refreshJcdfDatagrid("dictionaryDatagrid");
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
	   			<tr>
					<td class="bule" align="right">
						字典类型：
					</td>
					<td align="left">
						<input id="dicType" name="dicType" type="text" class="easyui-combobox" panelHeight="200" data-options="required:true,readonly:true">
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="20%">
						字典编号：
					</td>
					<td align="left">
						<input id="dicId" type="text" name="dicId" field="dicId" readonly="readonly" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						字典名称：
					</td>
					<td align="left">
						<input id="dicName" type="text" name="dicName" field="dicName" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						字典序号：
					</td>
					<td align="left">
						<input id="dicOrder" type="text" name="dicOrder" field="dicOrder" class="easyui-numberbox" data-options="required:true,validType:['maxLength[2]','unnormal']"/>
					</td>
				</tr>
					<tr>
					<td class="bule" align="right">
						字典备注：
					</td>
					<td align="left">
						<input id="discription" type="text" name="discription" field="discription" class="easyui-textbox" data-options="validType:['maxLength[60]','unnormal']"/>
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