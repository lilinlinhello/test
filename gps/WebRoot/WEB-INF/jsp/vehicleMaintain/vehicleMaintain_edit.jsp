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
    	/*form�?*/
		.tab-child {margin-top:2px;margin-bottom:3px;background-color: #F4F4F4;}
		.tab-child table {border:1px solid #52B64C; margin:10px 0px;border-collapse:collapse;width: 100%;}
		.tab-child table td {height:30px;border:1px solid #8F7A08; padding-left:5px;}
		.bule-child {background:#F7CC8F; text-align:center;}
	</style>

  
	<script type="text/javascript">
		var dictionarys = '${requestScope.dictionarys}' ;
	    var t = eval('('+dictionarys+')') ;
	    var id = '${requestScope.vehicleMaintainId}';
		$(
			function(){
				//加载选择下拉框 
				initDictionary(t,"editForm");
				//加载安装人
				initInstallUser("installUser");
				//摄像头初始化
				selectCamera();
				//加载修改数据
				loadEditData();
				//保存按钮单击事件
				$("#editButton").bind("click", function(){
					dealAttach();
				});			
				
				//重置按钮单击事件
				$("#resetButton").bind("click", function(){
				  	$$.resetContent("editForm");
				  	$$.resetContent("editCameraForm");
				  //摄像头初始化
					selectCamera();
				  //加载修改数据
					loadEditData();
				});
				$('#licensecard').textbox('textbox').bind('blur',function(){
					return validateVehicleId();
				});
				//车载设备校验
				$('#equipId').textbox('textbox').bind('blur',function(){
					return validateEquipId();
				});
				
				//sim卡号校验
				$('#simPhoneno').textbox('textbox').bind('blur',function(){
					return validateSimPhoneno();
				});
			}		
		);
		
		/********************处理摄像头数据****/
		function loadCameraData(){
			var camera01 = '${requestScope.video01}';
			var camera02 = '${requestScope.video02}';
			var camera03 = '${requestScope.video03}';
			var camera04 = '${requestScope.video04}';
			if( camera01 == ''){
				
			}else{
				var video01 = eval('('+'${requestScope.video01}'+')') ;
				loadEachCameraData(1,video01);
			}
			if( camera02 == ''){
				
			}else{
				var video02 = eval('('+'${requestScope.video02}'+')') ;
				loadEachCameraData(2,video02);
			}
			if( camera03 == ''){
				
			}else{
				var video03 = eval('('+'${requestScope.video03}'+')') ;
				loadEachCameraData(3,video03);
			}
			if( camera04 == ''){
				
			}else{
				var video04 = eval('('+'${requestScope.video04}'+')') ;
				loadEachCameraData(4,video04);
			}
		}
		function loadEachCameraData(e,video){
			$('#remark0'+e).textbox('setValue',video.remark);
			$('#installLocation0'+e).textbox('setValue',video.installLocation);
			$('#installStatus0'+e).combobox('setValue',video.installStatus);
			document.getElementById('isInstall0'+e).checked = true;
			$("#installLocation0"+e).textbox("readonly",false);
			$("#remark0"+e).textbox("readonly",false);
			$("#installStatus0"+e).combobox("readonly",false);
		}
		/********************校验设备****/
		function validateEquipId(){
			var equipId = $('#equipId').textbox('getValue').trim();
			if(equipId == ""){
				$$.showJcdfMessager('提示消息','车载设备不能为空','warning');
				return false;
			}
			var vehicleMaintain = {};
			vehicleMaintain.equipId = equipId;
			vehicleMaintain.id = id;
			var flag = true;
			//校验唯一性
			 $.ajax({
                url: basePath+'/vehicleMaintain/validateVehicleMaintain.do',
                type: 'get',
                dataType:'json',
                data:vehicleMaintain,
                success:function(data){
                    if(data && data.result){
                    }else{
                    	$$.showJcdfMessager('提示消息','车载设备输入重复','warning');
						flag = false;
                    }
               }
			});
			return true;
		}
		/********************校验sim考好****/
		function validateSimPhoneno(){
			var simPhoneno = $('#simPhoneno').textbox('getValue').trim();
			if( simPhoneno ==""){
				return false
			}
// 			var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
// 			if(!reg.test(simPhoneno)){
// 				$$.showJcdfMessager('提示消息','请输入正确的SIM卡号','warning');
// 				return false;
// 			}
			var len = simPhoneno.length;
			if(len != 11 && len!=13){
				$$.showJcdfMessager('提示消息','请输入正确的SIM卡号','warning');
				return false;
			}
			var vehicleMaintain = {};
			vehicleMaintain.simPhoneno = simPhoneno;
			vehicleMaintain.id = id;
			if(len==11){
				vehicleMaintain.simId = simPhoneno;
			}else{
				vehicleMaintain.simId = simPhoneno.substring(2,len);
			}
			var flag = true;
			//校验唯一性
			 $.ajax({
                url: basePath+'/vehicleMaintain/validateVehicleMaintain.do',
                type: 'get',
                dataType:'json',
                data:vehicleMaintain,
                success:function(data){
                    if(data && data.result){
                    }else{
                    	$$.showJcdfMessager('提示消息','SIM卡号输入重复','warning');
						flag = false;
                    }
                }
    		})
    		$("#simId").textbox('setValue',vehicleMaintain.simId);
    		return flag;
		}
		/********************摄像头设置是否可写****/
		function selectCamera(){
			$('#addCamera').hide();
			for(i=1;i<=4;i++){
				$("#installLocation0"+i).textbox("readonly",true);
				$("#remark0"+i).textbox("readonly",true);
				$("#installStatus0"+i).combobox("readonly",true);
				$("#isInstall0"+i).val(i);
			}
		}
		
		function show_color(e){
			if($("#isInstall0"+e).is(':checked')){
				$("#installLocation0"+e).textbox("readonly",false);
				$("#remark0"+e).textbox("readonly",false);
				$("#installStatus0"+e).combobox("readonly",false);
				$("#isInstall0"+e).val(0);
			}else{
				$("#installLocation0"+e).textbox("readonly",true);
				$("#remark0"+e).textbox("readonly",true);
				$("#installStatus0"+e).combobox("readonly",true);
				$("#isInstall0"+e).val(e);
			}
		}
		//校验车牌号
		function validateVehicleId(){
			var licensecard = $('#licensecard').textbox('getValue');
			licensecard = licensecard.trim();
			if( licensecard.length == 0){
				return false;
			}
			if(licensecard.length != 8 ){
				$$.showJcdfMessager('提示消息','车牌号输入有误','warning');
				return false;
			}
			if(licensecard.substring(2,3) != '-'){
				$$.showJcdfMessager('提示消息','车牌号输入有误','warning');
				return false;
			}
			var reg= /^[A-Za-z]+$/;
			if(!reg.test(licensecard.substring(1,2))){
				$$.showJcdfMessager('提示消息','车牌号输入有误','warning');
				return false;
			}
			 var Regx = /^[A-Za-z0-9]*$/;
			if( !Regx.test(licensecard.substring(3,8))){
				$$.showJcdfMessager('提示消息','车牌号输入有误','warning');
				return false;
			}
			var pro_name = ["京","津","冀","晋","蒙","辽","吉","黑","沪","苏","浙","皖","闽","赣","鲁","豫","鄂","湘","粤","桂","琼","渝","川","贵","云","藏","陕","甘","青","宁","新"];
			var pro_code = ["11","12","13","14","15","21","22","23","31","32","33","34","35","36","37","41","42","43","44","45","46","50","51","52","53","54","61","62","63","64","65"];
			var province = licensecard.substring(0,1);
			var pro_name_arr = new Array("京","津","冀","晋","蒙","辽","吉","黑","沪","苏","浙","皖","闽","赣","鲁","豫","鄂","湘","粤","桂","琼","渝","川","贵","云","藏","陕","甘","青","宁","新");
			if( !(contains(pro_name_arr, province))){
				$$.showJcdfMessager('提示消息','车牌号输入有误','warning');
				return false;
			}
			var province_code ="";
			licensecard = licensecard.toUpperCase();
			$('#licensecard').textbox('setValue',licensecard);
			for( var i = 0;i<pro_name.length;i++){
				if( pro_name[i] != province){
				}else{
					province_code = pro_code[i];
				}
			}
			var vehicleId = licensecard.replace(province,province_code);
			vehicleId = vehicleId.replace("-","");
			var vehicleMaintain = {};
			vehicleMaintain.vehicleId = vehicleId;
			vehicleMaintain.id = id;
			var flag = true;
			//校验唯一性
			 $.ajax({
                url: basePath+'/vehicleMaintain/validateVehicleMaintain.do',
                type: 'get',
                dataType:'json',
                data:vehicleMaintain,
                success:function(data){
                    if(data && data.result){
                    	$('#vehicleId').textbox('setValue',vehicleId);
                    }else{
                    	$$.showJcdfMessager('提示消息','车牌号输入重复','warning');
						flag = false;
                    }
                }
    		}) 
			 return flag;
		}
		//保存数据 
		function dealAttach(){
			/**再校验form 和必填字段**/
			  if(!checkField()){
				return false;
			} 
			if(!$("#editForm").form('validate')){
				return false ;
			}
			//校验车牌号
			if(!validateVehicleId()){
				return false;
			};
			//校验sim卡号
			if(!validateSimPhoneno()){
				return false
			};
			//校验设备Id
			if(!validateEquipId()){
				return false
			};
			if(!validateFixcamera()){
				return false;
			}
			//提交数据
			submitEditData();
		}
		/**
		 * 加载历史数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "GET",
			   url: basePath+"/vehicleMaintain/queryVehicleMaintain.do?id="+id,
			   dataType:"json",
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (!data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						//填充修改记录的历史数据
						$("#editForm").form("load",data);
						$("#editForm").find("input[required='y']").each(function(){
							var tid = this.id ;
							this.value=data[tid];
						});
						// init edit corp
						initEditCompany("corpId",data.corpId,data.operAttr,data.typeId) ;						
						$("#corpId").combobox("setValue",data.corpId);
						$("#operAttr").combobox("setValue",data.operAttr);
						$("#typeId").combobox("setValue",data.typeId);
						//init edit equip
						initEditEquip("equipBrand",data.equipBrand,data.equipType) ;	
						$("#equipBrand").combobox("setValue",data.equipBrand);
						$("#equipType").combobox("setValue",data.equipType);
						if( data.isFixcamera == '1'){
							$('#editCamera').show();
							//加载摄像头信息
							loadCameraData();
						}
					}
			   }
			});
		}
		
		/**
		 * 初始化公司
		 **/
		function initEditCompany(e,corpId,operAttr,typeId){
			/**1:查询公司，2查询属性，**/
			var url = basePath+'/vehicleMaintain/getCompanyInfo.do?parentCode=0';
			$("#"+e).combobox({
				url : url,
				valueField: 'id',    
	        	textField: 'name',
				/* MODE : 'REMOTE' , */
				panelHeight:"150",
			   /*  onShowPanel : function () {
			    	$("#"+e).combobox('reload',url);
			    },
			    onChange :function (){
			    	$("#"+e).combobox('options').url = url ;
			    }, */
			    onSelect:function(){
			    	$("#operAttr").combobox("clear");
			    	$("#typeId").combobox("clear");
			    	loadOperationAttribute("operAttr",$("#corpId").combobox("getValue")); 
			    },
			    onLoadSuccess:function(data){
			    	$("#corpId").next('.combo').find('input').blur(function(){
			    		var inputData = $('#corpId').combobox('getText');
			    		for(var i=0;i<data.length;i++){
			    			//console.log(inputData+"..."+data[i].NAME+"..."+data[i].ID);
			    			if(inputData==data[i].NAME){
			    				$('#corpId').combobox('setValue',data[i].ID);
			    				loadOperationAttribute("operAttr",$("#corpId").combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#corpId').combobox('clear');
			    		$('#operAttr').combobox('clear');
			    		$('#typeId').combobox('clear');
			    	});
			    }
			});
			loadOperationAttribute("operAttr",corpId);
			loadVehicleClassification("typeId",operAttr);
		}
		/**
		 * 初始化设备
		 **/
		function initEditEquip(e,equipBrand,equipType){
			var url = basePath+"/equipmentBrand/getBrandInfo.do?parentCode=0";
			$("#"+e).combobox({
				url : url,
				valueField: 'id',    
	        	textField: 'name',
				//mode : 'remote' ,
				panelHeight:"150",
			    onShowPanel : function () {
			    	$("#"+e).combobox('reload',url);
			    },
			    onChange :function (){
			    	$("#"+e).combobox('options').url = url ;
			    },
			    onSelect:function(){
			    	$("#equipType").combobox("clear");
			    	loadBrand("equipType",$("#equipBrand").combobox("getValue"));
			    },
			    onLoadSuccess : function (data){
			    	$("#"+e).next('.combo').find('input').blur(function(){
			    		var inputData = $('#'+e).combobox('getText');
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#'+e).combobox('setValue',data[i].ID);
			    				loadOperation("equipType",$("#"+e).combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#'+e).combobox('clear');
			    		$('#equipType').combobox('clear');
			    	});
			    },
			});
			loadBrand("equipType",equipBrand);
		}
		/**
		  * 保存新增信息
		 */
		function submitEditData() {
			if($("#isInstall01").is(':checked')){
				show_color(1);
			}
			if($("#isInstall02").is(':checked')){
				show_color(2);
			}
			if($("#isInstall03").is(':checked')){
				show_color(3);
			}
			if($("#isInstall04").is(':checked')){
				show_color(4);
			}
			/********************对摄像头提交的数据封装成数组****/
			var installLine = document.getElementsByName("installLine");
			var installLineArr = new Array(4);
			for( i= 0;i<installLine.length;i++){
				installLineArr[i] = installLine[i].value;
			}
			var isInstall = document.getElementsByName("isInstall");
			var isInstallArr = new Array(4);
			for( i= 0;i<installLine.length;i++){
				isInstallArr[i] = isInstall[i].value;
			}
			var installLocation = document.getElementsByName("installLocation");
			var installLocationArr = new Array(4);
			for( i= 0;i<installLocation.length;i++){
				var temp = installLocation[i].value;
				temp.replace(',','，');
				installLocationArr[i] = temp;
			}
			var installStatus = document.getElementsByName("installStatus");
			var installStatusArr = new Array(4);
			for( i= 0;i<installStatus.length;i++){
				installStatusArr[i] = installStatus[i].value;
			}
			var remark = document.getElementsByName("remark");
			var remarkArr = new Array(4);
			for( i= 0;i<remark.length;i++){
				var temp = remark[i].value;
				temp.replace(',','，');
				remarkArr[i] = temp;
			}
		    var obj = {};
		    $.each( $("#editForm").serializeArray(), function(i,o){
		    	obj[o.name] = $.trim(o.value) ;
		    });
			var vehicleMaintain = $$.serializeToJson("#editForm") ;
			if (!vehicleMaintain)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type : "POST",
			   url : basePath+"/vehicleMaintain/vehicleMaintainUpdate.do?installLine="+installLineArr+"&remark="+remarkArr+"&installLocation="+installLocationArr+"&installStatus="+installStatusArr+"&isInstall="+isInstallArr,
			   dataType : "json",
			   data : vehicleMaintain,
			   success : function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
					$$.closeJcdfDialog();
					$$.refreshJcdfDatagrid("vehicleMaintainDatagrid");
				 } else {
					 $$.showJcdfMessager('提示消息',data.msg,'info');
				 }
			   }
			});
		}
		/**校验必填字段**/
		function checkField(){
			var flag = true;
			$("font").each(function(){
				var text = $(this).next("span").html()+"信息不完善!" ;
				var rqobj = $(this).parent("td").next("td").find("input[required='y']") ;
				if(rqobj.length>0 && rqobj[0].value==''){
					$$.showJcdfMessager('提示消息',text,'warning');
					flag = false ;
					return false ;
				}
			}) ;
			if(flag){
				if($("#equipBrand").combobox("getValue")=='' || $("#equipType").combobox("getValue")==''
						||$("#corpId").combobox("getValue")=='' || $("#typeId").textbox("getValue")==''|| $("#operAttr").textbox("getValue")==''){
					$$.showJcdfMessager('提示消息',"选择框信息不完善!",'warning');
					flag = false ;
					return false ;
				}
				if($("#installDate").datebox("getValue")==''){
					$$.showJcdfMessager('提示消息',"安装时间信息不完善!",'warning');
					flag = false ;
					return false ;
				}
			}
			return flag ;
		}
		function contains(arr, obj) {  
		    var i = arr.length;  
		    while (i--) {  
		        if (arr[i] === obj) {  
		            return true;  
		        }  
		    }  
		    return false;  
		} 
		
		
		//加载运营属性
		function loadOperationAttribute(id,param){
			//$("#operAttr").combobox("readonly",false) ;
			var url = 'getCompanyInfo.do?parentCode='+param;
			$("#typeId").combobox("clear");
			$("#"+id).combobox({
				panelHeight:120,
				url:url,
				valueField: 'ID',    
	        	textField: 'NAME',
	        	onLoadSuccess : function (data){
			    	$("#operAttr").next('.combo').find('input').blur(function(){
			    		var inputData = $('#operAttr').combobox('getText');
			    		//console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#operAttr').combobox('setValue',data[i].ID);
			    				loadVehicleClassification("typeId",$("#operAttr").combobox("getValue"));
			    				return;
			    			}
			    		}
			    		$('#operAttr').combobox('clear');
			    	});
			    },
	        	onSelect:function(recode){
	     			loadVehicleClassification("typeId",$("#operAttr").combobox("getValue"));
	        	}
			});
		}
		
		//加载车辆分类
		function loadVehicleClassification(id,param){
			//$("#typeId").combobox("readonly",false) ;
			var url = 'getCompanyInfo.do?parentCode='+param;
			$("#"+id).combobox({
				panelHeight:150, 
				url:url,
				valueField: 'ID',    
	        	textField: 'NAME',
	        	onLoadSuccess : function (data){
			    	$("#typeId").next('.combo').find('input').blur(function(){
			    		var inputData = $('#typeId').combobox('getText');
			    		console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#typeId').combobox('setValue',data[i].ID);
			    				return;
			    			}
			    		}
			    		$('#typeId').combobox('clear');
			    	});
			    },
			    onChange :function (){
			    	$("#typeId").combobox('options').url = url ;
			    },
			});
		}
	
		//加载设备品牌数据
		function loadBrand (id,param) {
			var url =basePath + "/equipmentBrand/getBrandInfo.do?parentCode="+param;
			$("#"+id).combobox({
				panelHeight:"150",
				url:url,
				valueField: 'id',    
	        	textField: 'name',
	        	onShowPanel : function () {
			    	$("#"+id).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	$("#"+id).next('.combo').find('input').blur(function(){
			    		var inputData = $('#'+id).combobox('getText');
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#'+id).combobox('setValue',data[i].ID);
			    				return;
			    			}
			    		}
			    		$('#'+id).combobox('clear');
			    	});
			    },
			    onChange :function (){
			    	$("#"+id).combobox('options').url = url ;
			    }	
			});
		}
		//加载设备型号数据
		 function loadOperation (id,param) {
		 	var url = basePath+"/equipmentBrand/getBrandInfo.do?parentCode="+param;
		 	$("#"+id).combobox({
		 		panelHeight:"150",
		 		url:url,
		 		valueField: 'id',    
	         	textField: 'name',
	        	onShowPanel : function () {
			    	$("#"+id).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	$("#"+id).next('.combo').find('input').blur(function(){
			    		var inputData = $('#'+id).combobox('getText');
			    		console.log(data);
			    		for(var i=0;i<data.length;i++){
			    			if(inputData==data[i].NAME){
			    				$('#'+id).combobox('setValue',data[i].ID);
			    				return;
			    			}
			    		}
			    		$('#'+id).combobox('clear');
			    	});
			    },
			    onChange :function (){
			    	$("#"+id).combobox('options').url = url ;
			    }
		 	});
		 	
		 }
		
		//增加摄像头
		function showFixCamera(){
			if($('input[name=isFixcamera]:checked').val()=='1'){
				  $('#editCamera').show();
			  }else{
				  $('#editCamera').hide();
			  }
		}
		/********************摄像头设置是否可写****/
		function selectCamera(){
			$('#editCamera').hide();
			for(i=1;i<=4;i++){
				$("#installLocation0"+i).textbox("readonly",true);
				$("#remark0"+i).textbox("readonly",true);
				$("#installStatus0"+i).combobox("readonly",true);
				$("#isInstall0"+i).val(i);
			}
		}
		//对摄像头进行校验
		function validateFixcamera(){
			if($('input[name=isFixcamera]:checked').val() == '1'){
				if( !validateFixcameraLocation(1)){
					return false;
				}
				if( !validateFixcameraLocation(2)){
					return false;
				}
				if( !validateFixcameraLocation(3)){
					return false;
				}
				if( !validateFixcameraLocation(4)){
					return false;
				}
			}
			return true;
		}
		function validateFixcameraLocation(e){
			if($("#isInstall0"+e).is(':checked')){
				if( $("#installLocation0"+e).textbox('getValue') == ''){
					$$.showJcdfMessager('提示消息', "摄像头视频"+e+"安装位置必须填写", 'info');
					return false;
				}
			}
			return true;
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
  	<div class="easyui-panel" title="车辆安装维护-修改" collapsible="true" style="width:100%;">
	   	<form action="#" id="editForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>车牌号：</span>
					</td>
					<td align="left">
						<input type="text" id ="licensecard" name="licensecard" style="width:150px" class="easyui-textbox" data-options="required:true,validType:['maxLength[50]','unnormal']" required="y"/>
					</td>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>车辆ID：</span>
					</td>
					<td align="left">
						<input type="hidden" name = "id" id ="id">
						<input type="text" id="vehicleId" name="vehicleId" style="width:150px" class="easyui-textbox" data-options="required:true,readonly:true,validType:['maxLength[30]','unnormal']" required="y"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>车载设备 ：</span>
					</td>
					<td align="left">
						<input type="text" id="equipId" name="equipId" style="width:200px" class="easyui-textbox" data-options="required:true,validType:['maxLength[30]','unnormal']" required="y"/>
					</td>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>设备品牌：</span>
					</td>
					<td align="left">
						<input id="equipBrand" type="text"
						panelHeight="150px"	name="equipBrand" class="easyui-combobox" prompt='设备品牌'  data-options="required:true" /> 
							<input id="equipType"
							type="text"  panelHeight="150px" name="equipType" class="easyui-combobox" prompt='设备型号'  data-options="required:true" /> 
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>SIM卡号：</span>
					</td>
					<td align="left">
						<input type="text" name="simPhoneno" id="simPhoneno" style="width:150px" class="easyui-numberbox" data-options="required:true,validType:['maxLength[30]','unnormal']" required="y"/>
					</td>	
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>SIM卡ID：</span>
					</td>
					<td align="left">
						<input type="text" name="simId" id="simId" style="width:150px" class="easyui-numberbox" data-options="required:true,readonly:true,validType:['maxLength[30]','unnormal']" required="y"/>
					</td>			
										
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<font style="color:red"></font><span>3G卡号：</span>
					</td>
					<td align="left">
						<input type="text" name="card3g" id="card3g" style="width:150px" class="easyui-textbox" data-options="validType:['maxLength[20]','unnormal']" />
					</td>
					<td class="bule" align="right" width="120px;">
						<span>DVR编号：</span>
					</td>
					<td align="left">
						<input type="text" name="dvrNumber" id="dvrNumber" style="width:150px" class="easyui-textbox" data-options="validType:['maxLength[30]','unnormal']" />
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<span>摄录一体机容量：</span>
					</td>
					<td align="left">
						<input id="camcorderMemory" name="camcorderMemory" type="text" class="easyui-combobox" panelHeight="auto" data-options="editable:false">
					</td>
					<td class="bule" align="right" width="120px;">
						<span>常通电：</span>
					</td>
					<td align="left">
						<input type="radio" name="longtimeElectric" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="longtimeElectric" value="0" onclick="filldata(this);"/>否
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<font style="color:red">*</font><span>车辆层次：</span>
					</td>
					<td align="left" colspan="3">
						<input id="corpId" name="corpId" class="easyui-combobox"  prompt='请选择单位'  panelHeight="150px" data-options="required:true" />
						<input id="operAttr" name="operAttr" class="easyui-combobox"  prompt='请选择运营属性' panelHeight="auto"  data-options="required:true" />
						<input id="typeId" name="typeId" class="easyui-combobox"  prompt='请选择车辆分类' panelHeight="auto" data-options="required:true" />
					</td>					
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<span>接ACC：</span>
					</td>
					<td align="left" >
						<input type="radio" name="acc" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="acc" value="0" onclick="filldata(this);"/>否
					</td>
					<td class="bule" align="right" width="120px;">
						<span>安装人：</span>
					</td>
					<td align="left">
						<input id="installUser" name="installUser" class="easyui-combobox"  panelHeight="200px" panelHeight="150px"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						 <font style="color:red">*</font><span>安装日期：</span>
					</td>
					<td align="left">
						<input id="installDate"  type="text" name="installDate" class="easyui-datebox" data-options="required:true,editable:false" />
					</td>					
					<td class="bule" align="right" width="120px;">
						<span>是否安装防盗器：</span>
					</td>
					<td align="left">
						<input type="radio" name="isGuard" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="isGuard" value="0" onclick="filldata(this);"/>否
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						 <span>是否发防盗短信：</span>
					</td>
					<td align="left" >
						<input type="radio" name="isSendMessage" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="isSendMessage" value="0" onclick="filldata(this);"/>否
					</td>
					<td class="bule" align="right" width="120px;">
						 <span>防盗短信接收号码：</span>
					</td>
					<td align="left" >
						<input id="guardSendno"  type="text" name="guardSendno" class="easyui-numberbox" data-options="validType:['maxLength[30]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						 <span>检测线配置方案：</span>
					</td>
					<td align="left" >
						<input id="checkLineType" name="checkLineType" class="easyui-combobox"  panelHeight="auto" data-options="editable:false"/>
					</td>
					<td class="bule" align="right" width="120px;">
						<span>开关门状态：</span>
					</td>
					<td align="left" >
						<input name="openfixType" id="openfixType" class="easyui-combobox"  panelHeight="auto" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						 <font style="color:red">*</font><span>状态：</span>
					</td>
					<td align="left">
						<input name="status" id="status" class="easyui-combobox" data-options="required:true,editable:false" panelHeight="auto" />
					</td>
					<td class="bule" align="right" width="120px;">
						<font style="color:red"></font><span>自编车牌号：</span>
					</td>
					<td align="left">
						<input type="text" name="autoLicensecard" id="autoLicensecard" style="width:150px" class="easyui-textbox" data-options="validType:['maxLength[50]','unnormal']" />
						<span>是否显示：</span>
						<input type="radio" name="isShowAutoLicensecard" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="isShowAutoLicensecard" value="0" onclick="filldata(this);"/>否
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						<span>状态备注：</span>
					</td>
					<td align="left" colspan="3">
						<input id="statusRemark"  type="text" name="statusRemark" style = "width:300px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right" width="120px;">
						 <span>是否安装摄像头：</span>
					</td>
					<td align="left" >
						<input type="radio" name="isFixcamera" value="1" onclick="showFixCamera(this);"/>是
						<input type="radio" name="isFixcamera" value="0" onclick="showFixCamera(this);"/>否
					</td>
					<td class="bule" align="right" width="120px;">
						 <span>通过3G设备拍照：</span>
					</td>
					<td align="left" >
						<input type="radio" name="isDvr3gcamera" value="1" onclick="filldata(this);"/>是
						<input type="radio" name="isDvr3gcamera" value="0" onclick="filldata(this);"/>否
					</td>
				</tr>				
			</table>
		</form>
		<div id="editCamera">
			<form action="#" id="addCameraForm" style="display: inline;" class="tab-child">
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" class="bule-child" width="150px;"> <span>安装线路</span></td>
						<td align="center" class="bule-child" width="150px;"><span>是否安装</span></td>
						<td align="center" class="bule-child" width="250px;"><!-- <font style="color:red">*</font> --><span>安装位置</span></td>
						<td align="center" class="bule-child" width="180px;"><!-- <font style="color:red">*</font> --><span>状态</span></td>
						<td align="center" class="bule-child">备注</td>
					</tr>
					<tr id= "video01">
						<td align="center" style ="background:#FF0000;">
							<span>视频1</span>
							<input type = "hidden" name = "installLine" value = "video01">
						</td>
						<td align="center">
							<input name="isInstall" id="isInstall01" type="checkbox" value="1" onclick = "show_color(1)"/>
						</td>
						<td align="center">
							<input type="text" name="installLocation" id="installLocation01" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
						<td align="center">
							<select  class="easyui-combobox" style="width:100px;" panelHeight="auto" id="installStatus01"
							name="installStatus" >
							<option value = "dic_installStatus01">未选择</option>
							<option value = "dic_installStatus02">正常</option>
							<option value = "dic_installStatus03">停用</option>
							</select>
						</td>
						<td align="center">
							<input type="text" name="remark" id="remark01" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
					</tr>
					<tr >
						<td align="center" style ="background:#0000FF;">
							<span>视频2</span>
							<input type = "hidden" name = "installLine" value = "video02">
						</td>
						<td align="center">
							<input name="isInstall" id="isInstall02" type="checkbox" value="2" onclick = "show_color(2)"/>
						</td>
						<td align="center">
							<input type="text" name="installLocation" id="installLocation02" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
						<td align="center">
							<select  class="easyui-combobox" style="width:100px;" panelHeight="auto" 
							name="installStatus" id="installStatus02">
							<option value = "dic_installStatus01">未选择</option>
							<option value = "dic_installStatus02">正常</option>
							<option value = "dic_installStatus03">停用</option>
							</select>
						</td>
						<td align="center">
							<input type="text" name="remark" id="remark02" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
					</tr>
					<tr >
						<td align="center" style ="background:#FFFF00;">
							<span>视频3</span>
							<input type = "hidden" name = "installLine" value = "video03">
						</td>
						<td align="center">
							<input name="isInstall" id="isInstall03" type="checkbox" value="3" onclick = "show_color(3)"/>
						</td>
						<td align="center">
							<input type="text" id="installLocation03" name="installLocation" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
						<td align="center">
							<select  class="easyui-combobox" style="width:100px;" panelHeight="auto" 
							name="installStatus" id="installStatus03">
							<option value = "dic_installStatus01">未选择</option>
							<option value = "dic_installStatus02">正常</option>
							<option value = "dic_installStatus03">停用</option>
							</select>
						</td>
						<td align="center" >
							<input type="text" name="remark" id="remark03" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
					</tr>
					<tr >
						<td align="center" style ="background:#00FF00;">
							<span>视频4</span>
							<input type = "hidden" name = "installLine" value = "video04">
						</td>
						<td align="center">
							<input name="isInstall" id="isInstall04" type="checkbox" value="4" onclick = "show_color(4)"/>
						</td>
						<td align="center">
							<input type="text" name="installLocation" id="installLocation04" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
						<td align="center">
							<select  class="easyui-combobox" style="width:100px;" panelHeight="auto" 
							name="installStatus" id="installStatus04">
							<option value = "dic_installStatus01">未选择</option>
							<option value = "dic_installStatus02">正常</option>
							<option value = "dic_installStatus03">停用</option>
							</select>
						</td>
						<td align="center">
							<input type="text" name="remark" id="remark04" style = "width:200px;" class="easyui-textbox" data-options="validType:['maxLength[100]','unnormal']"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<iframe name ="hframe" id="hframe" style=" display: none" ></iframe > 
  	</div>
	<div style="position:relative;bottom:0px;right:0px;background-color: #F4F4F4;height:30px;width:100%;text-align: right;">
		<a id="editButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	</div>
 </body>
</html>