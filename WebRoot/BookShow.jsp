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
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书展示</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
	<link href="CSS/BookShow.css" rel="stylesheet" type="text/css" >
	<script src="JS/jquery-1.11.1.js">
	</script>
	<script src="JS/FolderEffect.js">
	</script>
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/RandomBackground.js">
	</script>
	<script src="JS/BookShow.js">
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
	<div class="Content">
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent">
                <div class="TwoLaneButton">
                    <div class="LeftLanePart" onclick="pageMoveTo('ShowRows')">
                        <font class="ColumnText" style="cursor:pointer">图书展示</font><br>
                        <div class="IconIMG" id="BookIcon" style="cursor:pointer"></div>
                    </div>
                    <div class="RightLanePart" onclick="doubleFloder('BookSearchForm','SearchHidden');showSearchMethod();">
                        <font class="ColumnText" style="cursor:pointer">图书搜索</font><br>
                        <div class="IconIMG" id="SearchIcon" style="cursor:pointer"></div>
                    </div>
                </div>
			</div>
		</div>
		
		<div class="Space">
		</div>
		
		<h1 style="align:center" id="bookTips"></h1>
		<script>
			changeTips('<s:property value="BookNum"/>');
		</script>
		
		<div class="ColumnBody" id="BookSearchForm">
			<div class="Space">
			</div>
			<div class="InnerFormVisible" id="BookSearchInnerForm">
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="ColumnDetailButtonText" id="SearchMethod" onclick="switchSearchMethod();" style="cursor:pointer"></font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="text" id="SearchKeyName"></input>
					</div>
				</div>
				<font id="searchTips"></font>
				<div class="IconContent" onclick="goSearch();">
					<div class="SubmitIconGray" style="cursor:pointer">
					</div>
				</div>
			</div>
			<div class="Space">
			</div>
		</div>
		
		<div class="Space">
		</div>
		

		<div class="ShowRow" id="ShowRows">
<!--书籍循环体开始-->
		<s:iterator value="bookList" status="u" id="bookList">
			<div class="ShowRowAll" onclick="goBookPage(<s:property value='bid'/>);">
				<div class="ShowRowUppper"></div>
				<div class="ShowRowOne" id="ShowRowOne">
                    <div class="ShowRowLeft" id="ShowRowLeft">
                        <font class="ShowBookName" id="BookName<s:property value='bid'/>"></font><br>
                        <font class="ShowLesserInfo" id="BookAuthor<s:property value='bid'/>"></font><br>
                        <font class="ShowLesserInfo" id="BookPrint<s:property value='bid'/>"></font><br>
                        <font class="ShowLesserInfo" id="BookType<s:property value='bid'/>"></font>
                    </div>
                    <div class="ShowRowRight" id="ShowRowRight">
                        <font class="AvaliabilityInfo " id="BookAvaliability<s:property value='bid'/>">true</font>
                    </div>
				</div>
				<div class="ShowRowLower"></div>
			</div>
			<div class="ShowSpace">
			</div>
<script>
var flag=false;
var status = <s:property value="bookstatus"/>;
if(status==1){
flag = true;
}
bookRowInfo('<s:property value="bid"/>','<s:property value="bookname"/>','<s:property value="author"/>','<s:property value="press"/>','<s:property value="category"/>',flag,'<s:property value="ownername"/>');
</script>
			</s:iterator> 
<!--书籍循环体结束-->
		</div>
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent">
                <div class="TwoLaneButton">
                    <div class="LeftLanePart"onclick="previousPage();">
                        <font class="ColumnText" id="PreviousPageText">上一页</font><br>
                        <div class="IconIMG" id="PreviousPageIcon" style="cursor:pointer"></div>
                    </div>
                    <div class="RightLanePart"onclick="nextPage('<s:property value='BookNum'/>');">
                        <font class="ColumnText" id="NextPageText">下一页</font><br>
                        <div class="IconIMG" id="NextPageIcon" style="cursor:pointer"></div>
                    </div>
                </div>
				
			</div>
		</div>
<script>
navigationButton(0);
</script>
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