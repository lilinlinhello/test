/**
 * 页面数据转换公用js
 */
		
		/**
		  *初始化地点信息 ，省市区三级联动 
		**/
		function initAddress (e) {
			$("#city").combobox("readonly",true) ;
			$("#county").combobox("readonly",true) ;
			/**1:查询省，2查询市，3查询县区**/
			var url = basePath+"/address/location.do?method=loadLocation&type=1" ;
			$("#"+e).combobox({
				url : '#',
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
				onBeforeLoad : function (param){
					var t = $("#"+e).combobox('options').url ;
					if(t == '#' || t==null){
						return false ;
					}else{
						$("#"+e).combobox('options').url = url ;
						return true;
					}
			    },
			    onShowPanel : function () {
			    	$("#"+e).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#"+e).combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#"+e).combobox('options').url = url ;
			    	if($("#longitude").length>0){
			    		$("#longitude").textbox("clear") ;
			    	}
			    	if($("#latitude").length>0){
			    		$("#latitude").textbox("clear") ;
			    	}
			    },
			    onSelect:function(rec){
			    	$("#city").combobox("clear");
			    	$("#county").combobox("clear");
			    	initCity(rec.VALUE); //省带出市			    	
			    }
			});
		}
		function initCity(provinceId){
			$("#city").combobox("readonly",false) ;
            var url = basePath+"/address/location.do?method=loadLocation&type=2"+"&provinceId="+provinceId ;
            $("#city").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
			    onShowPanel : function () {
			    	$("#city").combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#city").combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#city").combobox('options').url = url ;
			    	if($("#longitude").length>0){
			    		$("#longitude").textbox("clear") ;
			    	}
			    	if($("#latitude").length>0){
			    		$("#latitude").textbox("clear") ;
			    	}
			    },
			    onSelect:function(rec){
			    	$("#county").combobox("clear");
			    	initCounty(rec.VALUE);// 市带出县
			    }
			});
		}
		function initCounty(cityId){
			$("#county").combobox("readonly",false) ;
            var url = basePath+"/address/location.do?method=loadLocation&type=3"+"&cityId="+cityId ;
            $("#county").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
			    onShowPanel : function () {
			    	$("#county").combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#county").combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#county").combobox('options').url = url ;
			    	if($("#longitude").length>0){
			    		$("#longitude").textbox("clear") ;
			    	}
			    	if($("#latitude").length>0){
			    		$("#latitude").textbox("clear") ;
			    	}
			    }
			});
		}
		
		/*************************仓库报价模块省、市、仓库联动加载开始****************************/
		function initWarehouseAddress (e) {
			$("#wareName").combobox("clear");
			/**1:查询省，2查询市，3查询县区**/
			var url = basePath+"/address/location.do?method=loadLocation&type=1" ;
			$("#"+e).combobox({
				url : '#',
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
				onBeforeLoad : function (param){
					var t = $("#"+e).combobox('options').url ;
					if(t == '#' || t==null){
						return false ;
					}else{
						$("#"+e).combobox('options').url = url ;
						return true;
					}
			    },
			    onShowPanel : function () {
			    	$("#"+e).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#"+e).combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#"+e).combobox('options').url = url ;
			    },
			    onSelect:function(rec){
			    	initWarehouseCity(rec.VALUE); //省带出市			    	
			    }
			});
		}
		
		function initWarehouseCity(provinceId){
			$("#city").combobox("readonly",false) ;
            var url = basePath+"/address/location.do?method=loadLocation&type=2"+"&provinceId="+provinceId ;
            $("#city").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
			    onShowPanel : function () {
			    	$("#city").combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#city").combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#city").combobox('options').url = url ;
			    
			    },
			    onSelect:function(rec){
			    	$("#wareName").combobox("clear");
			    	initWarehouse(rec.VALUE);// 市带出仓库
			    }
			});
		}
		//加载仓库列表
		function initWarehouse(cityId){
			$("#wareName").combobox("readonly",false) ;
            var url = basePath+"/warehouseQuote/getWarehouseByCity.do?cityId="+cityId ;
            $("#wareName").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
			    onShowPanel : function () {
			    	$("#wareName").combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#wareName").combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#wareName").combobox('options').url = url ;
			    },
			    onSelect:function(rec){
			    	$("#itemType").combobox('clear');
			    	initItemType(rec.VALUE);// 仓库带出物品类别
					keyValueMap[rec.VALUE]=rec.NAME;
			    }
			});
		}
		//仓库带出物品类别列表，注意保持下拉框ID一致
		function initItemType(wareId){
            var url = basePath+"/warehouseQuote/getItemTypeByWareId.do?wareId="+wareId ;
            $("#itemType").combobox({
				url : url,
				//prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote',
			    onShowPanel : function () {
			    	$("#itemType").combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#itemType").combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#itemType").combobox('options').url = url ;
			    },
			    onSelect:function(rec){
			    	$("#itemType").combobox('setValue',rec.VALUE);
			    	keyValueMap[rec.VALUE]=rec.NAME;  //暂存使用
			    }
			});
		}

		/*************************仓库报价模块省、市、仓库联动加载结束end****************************/
		
		/**
		 * 初始化编辑地点信息 
		 **/
		function initEditAddress(e,provinceId,cityId,countyId){
			$("#city").combobox("readonly",true) ;
			$("#county").combobox("readonly",true) ;
			/**1:查询省，2查询市，**/
			var url = basePath+"/address/location.do?method=loadLocation&type=1" ;
			$("#"+e).combobox({
				url : url,
				//width : 150,
				valueField :'VALUE',
				textField : 'NAME',
				mode : 'remote' ,
			    onShowPanel : function () {
			    	$("#"+e).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#"+e).combobox('clear');
					}
			    },
			    onChange :function (){
			    	$("#"+e).combobox('options').url = url ;
			    	if($("#longitude").length>0){
			    		$("#longitude").textbox("clear") ;
			    	}
			    	if($("#latitude").length>0){
			    		$("#latitude").textbox("clear") ;
			    	}
			    },
			    onSelect:function(rec){
			    	$("#city").combobox("clear");
			    	$("#county").combobox("clear");
			    	initCity(rec.VALUE); //省带出市
			    }
			});
			initCity(provinceId); //省带出市
			initCounty(cityId);
		}
		/**
		 * 初始公司下拉列表信息
		 **/
		function initCompany(e){
			var url = basePath+"/com/company.do?method=loadCompanyNames&limit=20" ;
			$("#"+e).combobox({
				url : '#',
				prompt:'输入关键字后自动搜索',
				//width : 150,
				valueField :'CNO',
				textField : 'CNAME',
				mode : 'remote',
				onBeforeLoad : function (param){
					var t = $("#"+e).combobox('options').url ;
					if(t == '#' || t==null){
						return false ;
					}else{
						$("#"+e).combobox('options').url = url ;
						return true;
					}
			    },
			    onShowPanel : function () {
			    	$("#"+e).combobox('clear');
			    	$("#"+e).combobox('reload',url);
			    },
			    onLoadSuccess : function (data){
			    	if(data.length<=0){
			    		$("#"+e).combobox('clear');
					}
			    }
			});
		}

	/***获取屏幕宽高***/	
		
		function getWindowHeight() {
			if (window.self && self.innerHeight) {
				return self.innerHeight;
			}
			if (document.documentElement && document.documentElement.clientHeight) {
				return document.documentElement.clientHeight;
			}
			return 0;
		}

		function getWindowWidth() {
			if (window.self && self.innerWidth) {
			return self.innerWidth;
			}
			if (document.documentElement && document.documentElement.clientWidth) {
			return document.documentElement.clientWidth;
			}
			return 0;
		}	
		
		
		
		/************************转换数据库code *************************/
		
		function swicthNumber(number){
			if(number==0){
				return "否";
			}
			if(number==1){
				return "是";
			}
			return "";
		}
		/**运营状态(dic_op_status01：未运营，dic_op_status02：在运营)**/
		function switchOperateStatus(operateStatus){
			if(operateStatus=="dic_op_status01"){
				return "未运营";
			}
			if(operateStatus=="dic_op_status02"){
				return "在运营";
			}
		}
		
		/***货架类型(dic_shelf_type01：轻；dic_shelf_type02：中；dic_shelf_type03：重；dic_shelf_type04：重高)**/
	   function switchShelfType(shelfType){
		   var result = "";
		   var arr = [] ;
		   if(shelfType != null){
			  arr =  shelfType.split(",");
			  for(var i=0;i<arr.length;i++){
				  if(arr[i]=="dic_shelf_type01"){
					  result=result+ "轻,";
					}
					if(arr[i]=="dic_shelf_type02"){
						result=result+ "中,";
					}
		            if(arr[i]=="dic_shelf_type03"){
		            	result=result+ "重,";
					}
					if(arr[i]=="dic_shelf_type04"){
						result=result+ "重高,";
					}
			  }
			  if(result.indexOf(",")!=-1){
				  result = result.substring(0,result.length-1);
			  }
		   }
		   return  result ;
		}
		
		/**流水线种类(dic_line_type01：人工；dic_line_type02：半自动；dic_line_type03：全自动)lineType**/
	   function switchLineType(lineType){
		   var result = "";
		   var arr = [] ;
		   if(lineType != null){
			  arr =  lineType.split(",");
			  for(var i=0;i<arr.length;i++){
				  if(arr[i]=="dic_line_type01"){
					  result=result+ "人工,";
					}
					if(arr[i]=="dic_line_type02"){
						result=result+ "半自动,";
					}
					if(arr[i]=="dic_line_type03"){
						result=result+ "自动,";
					}
			  }
			  if(result.indexOf(",")!=-1){
				  result = result.substring(0,result.length-1);
			  }
		   }
			  return  result ;
		}
		
		/**业务类型(dic_business_type01：B2B；dic_business_type02：B2C；dic_business_type03：转运(海外))**/
	   function switchBusinessType(businessType){
		   var result = "";
		   var arr = [] ;
		   if(businessType != null){
			  arr =  businessType.split(",");
			  for(var i=0;i<arr.length;i++){
				  if(arr[i]=="dic_business_type01"){
						result=result+ "B2B,";
					}
					if(arr[i]=="dic_business_type02"){
						result=result+ "B2C,";
					}
					if(arr[i]=="dic_business_type03"){
						result=result+ "转运(海外),";
					}
			  }
			  if(result.indexOf(",")!=-1){
				  result = result.substring(0,result.length-1);
			  }
		   }
		
			  return  result ;
			
		}
		/**库房类型(dic_house_type01:常温，dic_house_type02：恒温，dic_house_type03:避光)**/
	   function switchHouseType(houseType){
		   var result = "";
		   var arr = [] ;
		   if(houseType != null){
			  arr =  houseType.split(",");
			  for(var i=0;i<arr.length;i++){
				   if(arr[i]=="dic_house_type01"){
						result=result+ "常温,";
				   }
				   if(arr[i]=="dic_house_type02"){
						result=result+ "恒温,";
				   }
				   if(arr[i]=="dic_house_type03"){
						result=result+ "避光,";
				   }
			  }
			  if(result.indexOf(",")!=-1){
				  result = result.substring(0,result.length-1);

			  }
		   }
			  return  result ;
			
		}
		/**经营模式(dic_operate_mode01:自营,dic_operate_mode02:外包)**/
	   function switcOperateMode(operateMode){
			if(operateMode=="dic_operate_mode01"){
				return "自营";
			}
			if(operateMode=="dic_operate_mode02"){
				return "外包";
			}
		}
		
		
	  /****************首页信息窗口js*******************/
	    
	 //构建自定义信息窗体	
	   function createInfoWindow(title,content){
	   	var info = document.createElement("div");
	   	info.className = "info";

	   	//可以通过下面的方式修改自定义窗体的宽高
	   	//info.style.width = "400px";

	   	// 定义顶部标题
	   	var top = document.createElement("div");
	   	var titleD = document.createElement("div");
	   	var closeX = document.createElement("img");
	   	top.className = "info-top"; 
	   	titleD.innerHTML = title; 
	   	closeX.src = imageMarkerUrl+"close2.gif";
	   	closeX.onclick = closeInfoWindow;
	   	  
	   	top.appendChild(titleD);
	   	top.appendChild(closeX);
	   	info.appendChild(top);
	   	
	       
	   	// 定义中部内容
	   	var middle = document.createElement("div");
	   	middle.className = "info-middle";
	   	middle.style.backgroundColor='white';
	   	middle.style.height='120px';
//	   	middle.style.overflow='none';
//	   	middle.style.overflow='auto';
	   	middle.innerHTML = content;
	   	info.appendChild(middle);
	   	
	   	// 定义底部内容
	   	var bottom = document.createElement("div");
	   	bottom.className = "info-bottom";
	   	bottom.style.position = 'relative';
	   	bottom.style.top = '0px';
	   	bottom.style.margin = '0 auto';
	   	var sharp = document.createElement("img");
	   	sharp.src = imageMarkerUrl+"sharp.png";
	   	bottom.appendChild(sharp);	
	   	info.appendChild(bottom);
	   	return info;
	   }

	   //关闭信息窗体
	   function closeInfoWindow(){
	   	map.clearInfoWindow();
	   }
	   //HTML反转义
	   function HTMLDecode(text)
	   {
	   var temp = document.createElement("div");
	   temp.innerHTML = text;
	   var output = temp.innerText || temp.textContent;
	   temp = null;
	   return output;
	   }
	   
	   /*********************************首页信息窗户关闭*************************************/

	var keyValueMap ={}; //存储 key --value ,省市编码对应省市名称  ,物品类别对应物品类别名称 common.js
 
	/*************字典信息转换公共方法 start****************/
	   //加载搜索下拉框，t为jsonStr,formId 为所在form表单的id编号
		function initDictionary(t,formId){
			$.each(t,function(key,value){
				$.each(value,function(k,v){
					var obj = $("#"+formId).find("input[id='"+k+"']") ;
					if(obj.length>0){
						var data = [] ;
						$.each(v,function(x,y){
							var o = {} ;
							o.ID = y.ID ;
							o.NAME = y.NAME ;
							if(k=="itemType" || k=="otherFeeUnit" ){
								keyValueMap[y.ID]=y.NAME; //把所有子弹的值以 key-value形式存储
							}
							data.push(o) ;
						});
						$("#"+k).combobox({
							 valueField :'ID',
							 textField :'NAME',
							 data : data,
							 onChange:function(e1,e2){
								 if (e1=="dic_equipStatus02"){
									   document.getElementById("re1").style.display="";
									   document.getElementById("re2").style.display="";
								 }
								 if (e1=="dic_equipStatus01"){
									   document.getElementById("re1").style.display="none";
									   document.getElementById("re2").style.display="none";
										$("#repairReason").combobox('clear');
								 }
								 if (e1=="dic_equipStatus03"){
									   document.getElementById("re1").style.display="none";
									   document.getElementById("re2").style.display="none";
									   $("#repairReason").combobox('clear');
								 }
							 }
						});
					}
				});
			});
		}
		//列表返回结果
		function matchDics(value,row,index){
			var str = "" ;
			$.each(t,function(key,obj){
				$.each(obj,function(k,v){
					$.each(v,function(x,y){
						if(value.indexOf(",")>0){
							var tem = value.split(",");
							for(var i=0;i<tem.length;i++){
								if(y.ID==tem[i]){
									if(str==''){
										str = str+y.NAME ;
									}else{
										str = str+","+y.NAME ;
									}
								}
							}
						}else{
							if(y.ID == value){
								str = y.NAME ;
								return ;
							}
						}
					});
				}) ;
			}) ;
			return str;
		}
	/*************字典信息转换公共方法 end****************/
		/**
		 *填充多选框 字典字段信息
		**/
		function fillDicField(t,formId){
		
			$.each(t,function(key,value){
				$.each(value,function(k,v){
					var obj = $("#"+formId).find("input[name='"+k+"']") ;
					if(obj!=null && obj.length>0){
						var str = "";
						var count = 0;
						$.each(v,function(x,y){
							count = count+1;
							str += "<input type='checkbox' name='"+k+"tem' value='"+y.ID+"'/>&nbsp;"+y.NAME+"&nbsp;&nbsp;" ;
							if(count % 10==0){  //每10个换一行
								str+="<br />";
							}
							if(count<10 && k=="itemType" && count%4==0){ //物品类别每行四个
								str+="<br/>";
							}
						    if(k=="provinceArr" || k=="cityArr"||k=="warehouseArr"){ //省市，仓库报价模块暂存数据数据页面转换
						    	keyValueMap[y.ID]=y.NAME;
						    }
						});
						obj.before(str);
					}
				});
			});
		}	
		
		//获取checkbox 值赋给 hidden ，作为表单提交 
		function getCheckValue(name) {
			var id = document.getElementsByName(name + "tem"); //common.js拼接的name 加了tem 
			var value = "";
			for (var i = 0; i < id.length; i++) {
				if (id[i].checked){
					value = value + id[i].value + ",";
				}
			}
			if (value.indexOf(",") != -1) {
				value = value.substring(0, value.length - 1);
			}
			$("#" + name).val(value);
			return value;
		}		
   //单选泽框
		function filldata(e){
			$(e).parent("td").find("input[required='y']").val(e.value);
		}
		/***
		 * 统计字符长度，汉子算2或者3个字符
		 * @param s
		 * @returns {Number}
		 */
		function mbStringLength(s) { 
			var totalLength = 0; 
			var i; 
			var charCode; 
			for (i = 0; i < s.length; i++) { 
				charCode = s.charCodeAt(i); 
				if (charCode < 0x007f) { 
					totalLength = totalLength + 1; 
				} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) { 
					totalLength += 2; 
				} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) { 
					totalLength += 3; 
				} 
			} 
			return totalLength; 
		}
	/**
	 * 格式化查询区样式，自动补齐td
	 * @author LHM
	 * @date 2015-10-20
	 */
	$(function(){
		var searchWidth = $(".searchArea").width();//获取页面查询区宽度
		var tdCount=0,tdWidth=0,maxTdCount=0;
		var trObj = $(".searchArea tr");
		//找出最多td行
		$(trObj).each(function(){
			tdCount = $(this).children('td').length;
			if(tdCount > maxTdCount){
				maxTdCount = tdCount;
			}
		});
		tdCount=0;
		$(trObj).each(function(){
			tdCount = $(this).children('td').length;
			var tdDiff = maxTdCount -tdCount;
			var tdColspan = tdDiff==0?tdDiff:tdDiff+1;
			tdWidth = searchWidth - tdCount*(150+120)/2;
			$(this).append("<td colspan="+tdColspan+"></td>");
			$(this).children('td:last').css('width',tdWidth);
		});
	});
$(function() {	
		if($("meta[name=toTop]").attr("content")=="true"){
			$("<div id='toTop'><img src="+imageMarkerUrl+"backTop.png></div>").appendTo('body');
			$("#toTop").css({
				width: '50px',
				height: '50px',
				bottom:'10px',
				right:'15px',
				position:'fixed',
				cursor:'pointer',
				zIndex:'999999',
			});
			if($(this).scrollTop()==0){
					$("#toTop").hide();
				}
			$(window).scroll(function(event) {
				/* Act on the event */
				if($(this).scrollTop()==0){
					$("#toTop").hide();
				}
				if($(this).scrollTop()!=0){
					$("#toTop").show();
				}
			});	
				$("#toTop").click(function(event) {
							/* Act on the event */
							$("html,body").animate({
								scrollTop:"0px"},
								666
								)
						});
			}
	});
  /**
   * 初始加载所有的用户
   **/
function initAllUser(e){
	var url = basePath+"/auth/user.do?method=queryAllUser" ;
	$("#"+e).combobox({
		url : url,
		valueField :'VALUE',
		textField : 'NAME',
		mode : 'remote' ,
	    onShowPanel : function () {
	    	$("#"+e).combobox('reload',url);
	    },
	    onLoadSuccess : function (data){
	    	$("#"+e).next('.combo').find('input').blur(function(){
	    		var inputData = $('#'+e).combobox('getText');
	    		console.log(data);
	    		for(var i=0;i<data.length;i++){
	    			if(inputData==data[i].NAME){
	    				$('#'+e).combobox('setValue',data[i].ID);
	    				return;
	    			}
	    		}
	    		$('#'+e).combobox('clear');
	    	});
	    }
	});
}
/**
 * 初始化安装人
 **/
//function initInstallUser(e){
//	/**1:查询安装人**/
//	var url = basePath+"/auth/user.do?method=queryUsername&userType=dic_userType02" ;
//	$("#"+e).combobox({
//		url : url,
//		valueField :'VALUE',
//		textField : 'NAME',
//		mode : 'remote' ,
//	    onShowPanel : function () {
//	    	$("#"+e).combobox('reload',url);
//	    },
//	    onLoadSuccess : function (data){
//	    	$("#"+e).next('.combo').find('input').blur(function(){
//	    		var inputData = $('#'+e).combobox('getText');
//	    		console.log(data);
//	    		for(var i=0;i<data.length;i++){
//	    			if(inputData==data[i].NAME){
//	    				$('#'+e).combobox('setValue',data[i].ID);
//	    				return;
//	    			}
//	    		}
//	    		$('#'+e).combobox('clear');
//	    	});
//	    }
//	});
//}
function initInstallUser(e){
	/**1:查询安装人**/
	var url = basePath+"/fixUsers/userMap.do" ;
	$("#"+e).combobox({
		url : url,
		valueField :'VALUE',
		textField : 'NAME',
		mode : 'remote' ,
	    onShowPanel : function () {
	    	$("#"+e).combobox('reload',url);
	    },
	    onLoadSuccess : function (data){
	    	$("#"+e).next('.combo').find('input').blur(function(){
	    		var inputData = $('#'+e).combobox('getText');
	    		//console.log(data);
	    		for(var i=0;i<data.length;i++){
	    			if(inputData==data[i].NAME){
	    				$('#'+e).combobox('setValue',data[i].ID);
	    				return;
	    			}
	    		}
	    		$('#'+e).combobox('clear');
	    	});
	    }
	});
}

//车牌号下拉菜单
function initCarNumber(e){
		var url = basePath+"/gpsEquipRegister/queryAllCarNumber.do";
		if("originalCarNumber"== e || "newCarNumber" == e){
			url = url+"?deleteF=0";
		}
		$("#"+e).combobox({
			url : url,
			prompt:'输入关键字后自动搜索',
			valueField :'CNAME',
			textField : 'CNAME',
			mode : 'remote',
			onBeforeLoad : function (param){
				if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){
					var value = $(this).combobox('getValue');
					if(value){// 修改的时候才会出现q为空而value不为空
						param.id = value;
						return true;
					}
					return false;
				}
		    },/*
		    onShowPanel : function () {
		    	$("#"+e).combobox('clear');
		    	$("#"+e).combobox('reload',url);
		    },*/
		    onLoadSuccess : function (data){
		    	$("#"+e).next('.combo').find('input').blur(function(){
		    		var inputData = $('#'+e).combobox('getText');
		    		for(var i=0;i<data.length;i++){
		    			if(inputData==data[i].CNAME){
		    				$('#'+e).combobox('setValue',data[i].CID);
		    				return;
		    			}
		    		}
		    		$('#'+e).combobox('clear');
		    	});
		    }
		});
		/*$("#"+e).combobox({
			url :url,
			//prompt:'输入关键字后自动搜索',
			valueField :'CNAME',
			textField : 'CNAME',
			mode : 'remote',
			onBeforeLoad : function (param){
				var t = $("#"+e).combobox('options').url ;
			if(t == '#' || t==null){
					return false ;
				}else{
					$("#"+e).combobox('options').url = url ;
					return true;
				}
		    },
//		   onShowPanel : function () {
//		    	$("#"+e).combobox('clear');
//		    	$("#"+e).combobox('reload',url);
//		    },
		    onLoadSuccess : function (data){
		    	$("#"+e).next('.combo').find('input').blur(function(){
		    		var inputData = $('#'+e).combobox('getText');
		    		for(var i=0;i<data.length;i++){
		    			if(inputData==data[i].CNAME){
		    				$('#'+e).combobox('setValue',data[i].CID);
		    				return;
		    			}
		    		}
		    		$('#'+e).combobox('clear');
		    	});
		    }
		});*/
	}
