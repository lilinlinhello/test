<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
<title>短信模板页面</title>
<script type="text/javascript">
		
		var id ='<%=request.getParameter("id")%>';//用于查找车辆信息中的sim卡号
		var equipBrand ='<%=request.getParameter("equipBrand")%>';
		var equipType ='<%=request.getParameter("equipType")%>';
		var smsType ='<%=request.getParameter("smsType")%>';
		$(function(){
			loadDatagrid();
			$("#sendButton").bind("click",function(){
				var smsTemplateId='';
				var isOwn='';//判断是否是自定义
				var selectNotes = '';
				if($("#ownEdit").is(':checked')){
					smsTemplateId = $("#msgText").val();
					selectNotes="own";//当选择子编辑时，不选择记录，也提交请求
					isOwn="0";
				}else{
					selectNotes = $$.getSelectIds("smsSendTemplateDatagrid","请选择要发送的记录","smsId");
					smsTemplateId = selectNotes.ids;
					isOwn="1";
				}
		//		console.log(selectNotes);
				if(selectNotes){
		 	    	$.ajax({
			    		url:basePath+'/smsSend/smsSendInsert.do',
			    		type:'POST',
			    		dataType:'json',
			    		data:{"id":id,"smsType":smsType,"smsTemplateId":smsTemplateId,"isOwn":isOwn},
			    		success:function(data){
			    			if(data && data.result){
			    				$$.showJcdfMessager('提示消息',data.msg,'info');
			    				$$.closeJcdfDialog();
			    			}else{
			    				$$.showJcdfMessager('提示消息',data.msg,'warning');
			    			}
			    		}
			    	});
				}
				
			});
			
		});
		
		/*自定义**/
		function customize(){
			if($("#ownEdit").is(':checked')){
				$("#zidingyi").show();
				$('input:checkbox[name!="ck"]').unbind();
				$('input:checkbox[name="ck"]').each(function(index,el){
					el.checked=false;
					el.disabled=true;
				});
			}else{
				$('input:checkbox[name="ck"]').bind();
				$('input:checkbox[name="ck"]').each(function(index,el){
					el.disabled=false;
				});
				$('#msgText').textbox('setValue','');
				$("#zidingyi").hide();
			}
		}
	function loadDatagrid(){
		$("#smsSendTemplateDatagrid").datagrid({
			height:$$.getDatagridHeight()*0.65,
			width:$$.getDatagridWidth(),
			nowrap: true,
			striped: true,
			url:basePath+'/smsTemplate/smsTemplateQuery.do',
			idField:'smsId',
			checkOnSelect:false,//只有点击checkbox才能选中该行 
			frozenColumns:[[{field:'ck',checkbox:true,align:'center'}]],
			columns:[[
				{field:'smsId',title:'ID',width:$$.fillsize(0.08),align:'center',hidden:'true'},
				{field:'smsOrder',title:'短信序号',width:$$.fillsize(0.08),align:'center'},
				{field:'smsContent',title:'短信模板内容',width:$$.fillsize(0.84),align:'center'}
			]],
			onBeforeLoad:function(){$$.clearSelect("smsSendTemplateDatagrid");},
			queryParams:{deleteFlag:0,smsBrand:equipBrand,smsModel:equipType,smsType:smsType},
			singleSelect:false,
			toolbar:'#menu'
	});
	
}
</script>
</head>
<body>
<!-- 数据展示列表查询区 -->
	<div id="menu">
		<div title="短信模板信息" class="easyui-panel">
		
		</div>
		<div class="menu-area">
			<a href="#" id="sendButton" class="easyui-linkbutton"
					iconCls="icon-redo" plain="true">发送</a>
		</div>
	</div>
	<!-- 数据展示列表区 -->
	<table id="smsSendTemplateDatagrid" tagType="datagrid">
	</table>
	<div>
		<input type="checkbox" id="ownEdit" onclick="customize()">自定义<br>
		<div id="zidingyi" style="display: none;">
			<input id="msgText" type="text" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:100px;">
		</div>
	</div>
</body>
</html>