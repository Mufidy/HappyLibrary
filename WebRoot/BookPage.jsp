<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib prefix="s" uri="/struts-tags" %>

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
<!-- saved from url=(0014)about:internet -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>书本页面</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
	<link href="CSS/BookPage.css" rel="stylesheet" type="text/css">
	<script src="JS/jquery-1.11.1.js">
	</script>
	<script src="JS/FolderEffect.js">
	</script>
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/RandomBackground.js">
	</script>
	<script src="JS/BookPage.js">
	</script>
	<script src="JS/TopBarMessage.js">
	</script>
</head>
<body>
<%@include file="TopBar.html"%>
<script>
showLoginCondition('<%=username%>');
</script>
	<div class="Cover" id="PageCover">
		<div class="CoverUpper">
		</div>
		<div class="CoverMiddle">
			<div class="CoverContent">
				<div class="ContentText">
					<font class="ContentMainText" id="BookName"></font><br><br>
					<font class="ContentSecondaryText" id="BookAuthor"></font><br>
					<font class="ContentSecondaryText" id="BookPrint"></font><br>
					<font class="ContentSecondaryText" id="BookType"></font>
				</div>
			</div>
		</div>
		<div class="CoverLower">
		</div>
	</div>
<script>
randomBackground();
</script>
	<div id="Content">
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent" id="AvailabilityColumnContent">
				<font class="ColumnText" id="Owner"></font><font class="ColumnText" id="Avaliability"></font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="AvailabilityIcon">
				</div>
			</div>
		</div>
		
		<div class="Column">
			<div class="ColumnContent" id="borrowInfoContent">
				<font class="ColumnText" id="borrowInfo">可借阅哦，赶紧借阅吧~</font>
			</div>
		</div>
		
		<div class="Dots">
			<div class="DotsContent">
				<font class="DotsText">·</font>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent">
				<font class="ColumnText">读者评论</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="CommentIcon">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody">
			<div class="Space">
			</div>
			<div class="ColumnBodyContent">
<!--书籍评论的循环体开始-->
<s:iterator value="listCommentShow" status="u" id="listCommentShow">
				<div class="ColumnBodyRecord">
					<font class="RecordText" id="ReaderComment<s:property value='commentID'/>"></font><br>
					<font class="RecordText" id="ReaderCommentDetail<s:property value='commentID'/>"></font>
				</div>
<script>
bookComment('<s:property value="commentID"/>','<s:property value="comment"/>','<s:property value="name"/>','<s:property value="date"/>');
</script>
</s:iterator> 
<!--书籍评论的循环体结束-->
			</div>
			<div class="Space">
			</div>
		</div>
		<div class="Dots">
			<div class="DotsContent">
				<font class="DotsText">·</font>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column" onclick="simpleFloder('NewCommentForm');" style="cursor:pointer">
			<div class="ColumnContent">
				<font class="ColumnText">发表评论</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="NewCommentIcon">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="NewCommentForm">
			<div class="ColumnContent">
				<div class="Space">
				</div>
					<div class="ColumnBodyRecord">
		                <div class="ColumnBodySimpleForm" >
		                	<input class="CommentFormInput" id="NewCommentInput"></input>
						</div>
					</div>
	                <div class="FormAlert" id="NewCommentFormAlert">
						<font class="FormAlertText" id="NewCommentFormAlertText"></font><br>
					</div>
					<div class="IconContent" onclick="newCommentConfirm('${book.bid}');">
						<div class="SubmitIconGray" style="cursor:pointer">
						</div>
					</div>
				<div class="Space">
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
		<div class="Column" id="ConfirmColumn" onclick="borrowConfirm('${book.bid}');">
			<div class="ColumnContent">
				<font class="ColumnText" style="cursor:pointer">确认借阅</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="ConfirmIcon" style="cursor:pointer">
				</div>
			</div>
			<div class="DotsContent">
				<font class="DotsText">·</font>
			</div>
		</div>
	</div>
<script>
var flag="";
var status = <s:property value="book.bookstatus"/>;
var bookOwner = "${owner.username}";
if(status==1){
flag = "1";
}else if(status==2){
flag = "2";
}else if(status==3){
flag = "3";
}
bookInfo('${book.bookname}','${book.author}','${book.press}','${booktype}',bookOwner,flag,'${borrowusername}','${borrowdate}');
showConfirmColumn(true);
</script>
</body>

