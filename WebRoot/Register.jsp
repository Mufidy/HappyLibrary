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
	<title>用户注册</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
	<link href="CSS/Register.css" rel="stylesheet" type="text/css">
	<script src="JS/jquery-1.11.1.js">
	</script>
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/RandomBackground.js">
	</script>
	<script src="JS/Register.js">
	</script>
	<script src="JS/TopBarMessage.js">
	</script>
</head>
<body>
	<%@include file="TopBar.html"%>
<script>
showLoginCondition('<%=username%>');
</script>
	<%@include file="CoverWithMotto.html"%>
<script>
randomBackground();
randomMotto();
</script>
	<div id="Content">
		<div class="Space">
		</div>
		<div class="Column" onclick="goToRegisterForm();">
			<div class="ColumnContent">
				<font class="ColumnText">新用户注册</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="RegisterIcon">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="RegisterForm">
			<div class="Space">
			</div>
			<div class="ColumnBodyContent">
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">ID</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="text" id="IDInput"></input>
					</div>
				</div>
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">姓名</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="text" id="NameInput"></input>
					</div>
				</div>
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">密码</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="password" id="PasswordInput"></input>
					</div>
				</div>
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">重复密码</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="password" id="PasswordReinput"></input>
					</div>
				</div>
                <div class="FormAlert" id="RegisterFormAlert">
					<font class="FormAlertText" id="RegisterFormAlertText"></font><br>
				</div>
			</div>
			<div class="Space">
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column" onclick="submitRegister();">
			<div class="IconContent">
				<div class="IconIMG" id="SubmitIcon">
				</div>
			</div>
		</div>
		<div class="Dots">
			<div class="DotsContent">
				<font class="DotsText">·</font>
			</div>
		</div>
		<div class="Space">
		</div>
	</div>
</body>
</html>
