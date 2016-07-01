<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
     String msg = (String)request.getAttribute("msg");
%>
<html>
  <head>
    <title>用户登录</title>
<%--     <link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" /> --%>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
	<meta charset="UTF-8" />
	<jsp:include page="head-ui-134.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/MD5+BASE64Encoder.js"></script>
	<script type="text/javascript">
		if (window != top)top.location.href = location.href;
		//为回车事件绑定自动登录业务处理
		$(function(){
			$(window).keydown(function(event){
				if (event.keyCode == 13) {
					login();
				}
			});
		})
		
        function changeImg() { 
            var imgSrc = $("#imgObj"); 
            var src = imgSrc.attr("src"); 
            imgSrc.attr("src", chgUrl(src)); 
        } 
        //时间戳     
        //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
        function chgUrl(url) { 
            var timestamp = (new Date()).valueOf(); 
//             url = url.substring(0, 17); 
            if ((url.indexOf("&") >= 0)) { 
                url = url + "×tamp=" + timestamp; 
            } else { 
                url = url + "?timestamp=" + timestamp; 
            } 
            return url; 
        } 
        /**
         *当没有提示消息时，设置提示框为隐藏
         */
        $(function(){
        	if("" == $.trim($("#errorMessage").val())){
        		$("#errorMsg").hide();
        	}
        })
        
       
        /**
         * 验证登陆表单，并提交登陆数据
         */
        function login() {
        	if ("" == $.trim($("#userId").val()) || "" == $.trim($("#passtemp").val()) ) {
        		$("#errorMsg").show();
        		$("#errorMessage").html("账号或者密码不能为空");
        		return false;
        	}
        	var pattern = /^[^<>`~!#()=|:,?￥【】。，、\/\'\\\"#$%&\^\*]+$/;
		    if(!pattern.test($.trim($("#userId").val()))){
		    	$("#errorMsg").show();
        		$("#errorMessage").html("请不要包含特殊字符");
				return false;
		    } 
        	if( "" == $.trim($("#code").val())){
        		$("#errorMsg").show();
        		$("#errorMessage").html("验证码不能为空");
        		return false;
        	}
        	if(!pattern.test($.trim($("#code").val()))){
		    	$("#errorMsg").show();
        		$("#errorMessage").html("请不要包含特殊字符");
				return false;
		    } 
        	$("#userPass").val(encodeURIComponent(b64_md5($("#passtemp").val())));
        	$("#loginForm").submit();
        }
        /**
         * 登陆失败，返回失败信息
         */
         $(function(){
        	 var msg = "<%=msg%>"
        	 if("" == msg || msg == "null" ){
        		 $("#errorMsg").hide();
        	 }else{
        		 $("#errorMsg").show();
         		$("#errorMessage").html(msg);
        	 }
         })
	</script>
  </head>
  
<!--     <body id="login_b" style="overflow:hidden;"> -->
<!--     <div id="login" > -->
<!--         <div class="login_c"> -->
<%--             <form id="loginForm" action="${pageContext.request.contextPath}/login.do" method="post"> --%>
<!--             	<div id="errorMsg"><label class="login_tips" id="errorMessage" ></label></div> -->
<!--                 <label><span class="login_icon l_user"></span> -->
<%--                 <input name="userId" id="userId" type="text" placeholder="请输入用户名" value="<%=null == request.getAttribute("userId") ? "" : request.getAttribute("userId")%>"  /> --%>
<!--                 <input name="userPass" type="hidden" id="userPass"/> -->
<!--                 </label> -->
<!--                 <label><span class="login_icon l_password"></span><input id="passtemp" type="password"   placeholder="请输入密码"  /></label> -->
<%--                 <label><span class="login_icon l_yanzheng"></span><input class="yanzheng_input" id="code" type="text" name="code" placeholder="请输入验证码" /><em><img id="imgObj" alt="验证码" src="${pageContext.request.contextPath }/codeContrl/code.do" onclick="changeImg()"/> </em></label> --%>
<!--                 <p><a href="#" class="forget_pw">忘记密码？</a><a href="#">立即注册</a></p> -->
<!--                 <label><a class="sys_btn login_btn" onclick="login()">登录</a></label> -->
<!--             </form> -->
<!--         </div> -->
<!--     </div> -->
<!-- </body> -->
<body>
<div class="cover">
    <img class="login_bgimg" src="${pageContext.request.contextPath}/images/login_bg.jpg"/>
</div>
<div class="login_bigbackground">
  <div class="login_background">
    <ul class="login_logo">
       <img src="${pageContext.request.contextPath}/images/login_logo.png" />
       <form id="loginForm" action="${pageContext.request.contextPath}/login.do" method="post">
           <li class="user_sr">
              <img src="${pageContext.request.contextPath}/images/user.png"><input id="userId" name="userId" type="text" placeholder="请输入用户名" value="<%=null == request.getAttribute("userId") ? "" : request.getAttribute("userId")%>" >
           </li>
           <li class="password_sr">
              <input name="userPass" type="hidden" id="userPass"/>
              <img src="${pageContext.request.contextPath}/images/mm.png"><input type="password" id="passtemp" placeholder="请输入密码" />
           </li>
           <li class="password_yz">
               <span>
              	<img src="images/users.png"><input id="code" type="text" name="code" placeholder="请输入验证码" />
                <em><img style="margin-top:0px" id="imgObj" alt="验证码" src="${pageContext.request.contextPath }/codeContrl/code.do" onclick="changeImg()"> </em>
              </span>           
           </li>
           <li >
           		<div id="errorMsg" align="center" style="display: block;"><font><label class="login_tips" id="errorMessage"></label></font></div>
           </li>
           <li class="login_button">
              <a href="javascript:void(0);" onclick="login()">登录</a>
           </li>
       </form>
    </ul>
  </div>
</div>
</body>
</html>
