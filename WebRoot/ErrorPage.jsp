<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
Object obj = session.getAttribute("username");
int flag = 0;//0 means hasn't login
String username = null;
if(obj==null){
	username="";
}else{
	username = session.getAttribute("username").toString();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>错误</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
    <link href="CSS/ErrorPage.css" rel="stylesheet" type="text/css">
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/ErrorPage.js">
	</script>
	<script src="JS/TopBarMessage.js">
	</script>
</head>
<body>
<%@include file="TopBar.html"%>
<script>
showLoginCondition('<%=username%>');
</script>
	<div id="Content">
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent">
				<font class="ColumnText" id="ErrorText"></font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="ErrorIcon">
				</div>
			</div>
		</div>
	</div>
<script>
showError('');
</script>
</body>
</html>

