<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改安装人</title>
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
</head>
<script type="text/javascript">

	var id = '<%=request.getParameter("id")%>';

	$(function(){
		
		
		getInfoById();
		
		$("#addButton").click("click",function(){
			//检验参数			
			if(!$("#editForm").form("validate")){
				return false;
			}
			//保存
			var param = $("#editForm").serialize() ;			
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
						$$.refreshJcdfDatagrid("fixUsersDatagrid");
					} else {
						$$.showJcdfMessager('提示消息', data.msg, 'warning');
					}
				}
			});
		});
		
		//重置按钮单击事件
		$("#resetButton").bind("click", function() {
			getInfoById();
		});
	});
	
	function getInfoById(){
		$.ajax({
			type : "POST",
		    url : "getFixUserById.do",
		    dataType : "json",
		    data : {id:id},
		    success : function(data){
		    	if(!data){
		    		$$.showJcdfMessager('提示消息','数据加载失败!','warning');
				} else {
					$("#editForm").form("load",data);
				}
		    }
		});
	}
	
	
</script>
<body >
	   	<form action="#" id="editForm" style="display: inline;" class="tab">
	   		<input type="hidden" name="id" id = "id"/>
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr >
					<td class="bule" align="right">
						姓名：
					</td>
					<td align="left">
						<input type="text" name="name" id = "name" class="easyui-textbox" data-options="required:true,validType:['unnormal']"/>
					</td>
				</tr>
				<tr >
					<td class="bule" align="right">
						联系方式：
					</td>
					<td align="left">
						<input type="text" name="phone" id = "phone" class="easyui-numberbox" data-options="required:true,validType:['mobile']"/>
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