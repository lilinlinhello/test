<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>新增字典</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		var dictionarys = '${requestScope.dictionarys}' ;
	    var t = eval('('+dictionarys+')') ;
		$(function(){
			 //加载选择下拉框 
			initDictionary(t,"addForm");
			selectDicType();
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	submitAddData();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	$$.resetContent("addForm");
		    });
		})
		
		
		function selectDicType(){
			$('#dicType+.combo').hide();
			$('#parent').combobox({
			onChange:function(){
				var parent = $('#parent').combobox('getValue');
				if(parent == ''){
					$('#dicType+.combo').hide();
				}else if(parent == '0'){
					$('#dicType+.combo').hide();
					$('#dicType').combobox('setValue','dic.dicType');
				}else if(parent == '1'){
					$('#dicType+.combo').show();
				}
			}
			})
			$('#parent').combobox('setValue','');
		}
		/**
		 * 保存新增航班信息
		 */
		function submitAddData() {
			if(!$("#addForm").form('validate')){
		      return false;
		    }
			var user = $$.serializeToJson("#addForm");
			if (!user)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/dictionary.do?method=insert",
			   dataType:"json",
			   data: user,
			   success: function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info')
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
	   	<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
	   			<tr style = "dsiplay:block">
					<td class="bule" align="right">
						字典类型：
					</td>
					<td align="left"><select id="parent" class="easyui-combobox" style="width:100px;" panelHeight="auto" 
							name="parent" data-options="required:true">
							<option value = "0">父级</option>
							<option value = "1">子级</option>
							</select>
							<input id="dicType"
							 name="dicType" class="easyui-combobox"  panelHeight="200" data-options="required:true" /> 
					</td>
				</tr>
				<tr >
					<td class="bule" align="right" width="20%">
						字典编号：
					</td>
					<td align="left">
						<input type="text" id="dicId" name="dicId"  field="dicId" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','unnormal']"/>
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						字典名称：
					</td>
					<td align="left">
						<input type="text" name="dicName" id = "dicName" field="dicName" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','unnormal']"/>
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						字典序号：
					</td>
					<td align="left">
						<input type="text" name="dicOrder" id = "dicOrder" field="dicOrder" class="easyui-numberbox" data-options="required:true,validType:['maxLength[2]','unnormal']"/>
					</td>
				</tr>
					<tr >
					<td class="bule" align="right">
						字典备注：
					</td>
					<td align="left">
						<input type="text" id = "discription" name="discription" field="discription" class="easyui-textbox" data-options="validType:['maxLength[60]','unnormal']"/>
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