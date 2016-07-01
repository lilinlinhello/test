<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<style type="text/css">
		.fileInput{
	        margin-left:-100px;
	        right:0;
	        top:0;
	        opacity:0;
	        filter:alpha(opacity=0);
	        cursor:pointer;
    	}	
	</style>
	<script type="text/javascript">
		$(function() {
			initCarNumber("carLicense");
			loadCompany("corpId");
			//保存按钮单击事件
			$("#addButton").bind("click", function() {
				submitAddData();
			});
			//重置按钮单击事件
			$("#resetButton").bind("click", function() {
				$$.resetContent("addForm");
			});
		})
		//加载公司数据
		function loadCompany (id) {
		var	url="retriveCorpName.do";
		$("#"+id).combobox({
			panelHeight:"150",
			url:url,
			valueField: 'id',    
        	textField: 'name',        	
        	onLoadSuccess : function (data){
        		//失去焦点触发事件，手动对combobox赋value值
        		$("#"+id).next('.combo').find('input').blur(function(){
        			var inputData = $("#"+id).combobox("getText");
        			for(var i = 0 ;i < data.length ;i++){
        				var tmp = data[i];
        				if(tmp.name == inputData){
        					$("#"+id).combobox("setValue",tmp.id);
        					return;
        				}
        			}
        			$("#"+id).combobox("clear");
        		});
        	},
		});
		}
		function submitAddData() {
			if (!$("#addForm").form('validate')) {
				return false;
			}
			var driver = $$.serializeToJson("#addForm");
			if (!driver)
				return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
				type : "POST",
				url : basePath + "/gpsDriverManager/insertGpsDriver.do",
				dataType : "json",
				data : driver,
				success : function(data) {
					$$.closeProcessingDialog();
					if (data && data.result) {
						$$.showJcdfMessager('提示消息', data.msg, 'info')
						$$.closeJcdfDialog();
						$$.refreshJcdfDatagrid("gpsDriverManagerDatagrid");
					} else {
						$$.showJcdfMessager('提示消息', data.msg, 'warning');
					}
				}
			});
		}
	</script>
	
  </head>
  
 <body style="background-color:#F4F4F4;">
     <div id="light" class="white_content"> 
        <div align="center" style="position:absolute;z-index:1002;left: ;right:0;top:0">
           <image src="${pageContext.request.contextPath}/images/close.png" id="closeId"  /></div>   
     </div> 
     <div id="fade" class="black_overlay"> 
     </div>
  	<div class="easyui-panel" title="驾驶员信息录入" collapsible="true" style="width:100%;">
	   	<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="bule" align="right" width="100px;">
						<font style="color:red">*</font><span>单位 ：</span>
					</td>
					<td align="left">
							<input id="corpId" name="corpId" style="width:120px" type="text" data-options="valueField:'id',textField:'name'" class="easyui-combobox" prompt="请选择单位名称">
					</td>
					<td class="bule" align="right" width="100px;">
						<font style="color:red">*</font><span>车牌号：</span>
					</td>
					<td align="left">
						<input id="carLicense" name="carLicense" style="width:120px" type="text" data-options="valueField:'id',textField:'name',validType:['maxLength[10]','unnormal']" class="easyui-combobox" prompt="请选择车牌号"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="100px;">
						<font style="color:red">*</font><span>姓名：</span>
					</td>
					<td align="left">
						<input type="text" name="userName" style="width:120px" class="easyui-textbox" data-options="required:true,validType:['maxLength[60]','name']" required="y"/>
					</td>
					<td class="bule" align="right" width="100px;">
						<font style="color:red">*</font><span>手机号码：</span>
					</td>
					<td align="left">
						<input type="text" name="telephone" style="width:120px" class="easyui-textbox" data-options="validType:['mobile']"/>
					</td>
				</tr>			
			</table>
		</form>
		<iframe name ="hframe" id="hframe" style=" display: none" ></iframe > 
  	</div>
	<div style="position:relative;bottom:0px;right:0px;background-color: #F4F4F4;height:30px;width:100%;text-align: right;">
		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	</div>
 </body>
</html>