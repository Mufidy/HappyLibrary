<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="JS/jquery-1.11.1.js"></script>
	<title>快乐图书馆</title>
</head>
<body onload="checkBrowser();checkLogin();">
</body>
<script>
function checkBrowser(){
    /*var userAgent=navigator.userAgent.toLowerCase(), s, o = {};  
    var browser={
        version:(userAgent.match(/(?:firefox|opera|safari|chrome|msie)[\/: ]([\d.]+)/))[1],
        safari:/version.+safari/.test(userAgent),
        chrome:/chrome/.test(userAgent),
        firefox:/firefox/.test(userAgent),
        ie:/msie/.test(userAgent),
        opera: /opera/.test(userAgent )
    } /* 获得浏览器的名称及版本信息 */
    
    if (isIe6()||isIe7()||isIe8())
    {
      /* 判断是否为IE 6以上版本，是则执行以下操作 */
      alert("IE版本过低，部分功能无法使用，请升级IE版本至IE10及以上。推荐使用Google Chrome浏览器！");
      window.open("","_self");
      this.window.opener=null;
      window.close();
  }
}

function isIe(){
       return window.ActiveXObject ? true : false;
   }

function isIe6() {
   // ie6是不支持window.XMLHttpRequest的
    return isIe() && !window.XMLHttpRequest;
 }
 
 function isIe7() {
   //只有IE8+才支持document.documentMode
   return isIe() && window.XMLHttpRequest && !document.documentMode;
 }
 
 function isIe8(){
   // alert(!-[1,])//->IE678返回NaN 所以!NaN为true 标准浏览器返回-1 所以!-1为false
  return isIe() &&!-[1,]&&document.documentMode;
}

function checkLogin(){
	$.ajax({
  url:"checkLogin.action",
  type:"post",
  dataType:"json",
  success:function(data){
  	if(data.flag==0){
  		window.location.href="Login.jsp";
  	}else if(data.flag==1){
  		if(data.username=="teacher"){
  			window.location.href="getClassInfo.action?s=0";
  		}else{
  			var searchInfo = "{\"s\":\""+0+"\",\"k\":\""+0+"\"}";
			window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
  		}
  	}
  },
  error:function(){
  	alert("index checkLogin AJAX Error");
  }
  });
  }
</script>
</html>