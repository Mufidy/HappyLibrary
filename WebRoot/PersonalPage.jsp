<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri ="/struts-tags" prefix ="s" %>

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
	<title>个人页面</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
	<link href="CSS/PersonalPage.css" rel="stylesheet" type="text/css">
	<script src="JS/jquery-1.11.1.js">
	</script>
	<script src="JS/FolderEffect.js">
	</script>
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/RandomBackground.js">
	</script>
	<script src="JS/PersonalPage.js">
	</script>
	<script src="JS/TopBarMessage.js">
	</script>
</head>
<body>
<%@include file="TopBar.html"%>
<script>
showLoginCondition('<%=username%>');
</script>
	<div class="LargerPageCover" id="PageCover">
		<div class="CoverUpper">
		</div>
		<div class="PersonalPageCoverContent">
			<div class="UserName">
				<font id="UserNameText"></font><br><br>
				<font id="UserIDText"></font>
			</div>
			<div class="UserInfo">
				<font class="UserInfoText" id="BookNumberText"></font><br>
				<font class="UserInfoText" id="ScoreText"></font><br>
			</div>
		</div>
		<div class="CoverLower">
		</div>
	</div>
<script>
randomBackground();
setCoverMessage('<s:property value="user.username"/>','<s:property value="user.uid"/>','${bookNum}','<s:property value="user.score"/>');
</script>
	<div id="Content">
		<div class="Space">
		</div>
		<div class="Column" id="MyBookButton" onclick="doubleFloder('MyBookShown','MyBookHidden');">
			<div class="ColumnContent">
				<font class="ColumnText" style="cursor:pointer">我的书籍</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="MyBookButtonIMG" style="cursor:pointer">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="MyBookShown">
			<div class="Space">
			</div>
			<div class="ColumnBodyContent">
<!--拥有书籍的循环体开始-->
<s:iterator value="myBookList" status="u" id="myBookList">
				<div class="ColumnBodyRecord" onclick="simpleFloder('MyBookDetail<s:property value="myBid"/>');">
					<font class="RecordText" id="MyBookRecord<s:property value='myBid'/>" style="cursor:pointer"></font>
				</div>
				<div class="ColumnDetail" id="MyBookDetail<s:property value='myBid'/>">
		<!--书籍借阅记录的循环体开始-->
					<div class="ColumnDetailRecord">
						<font class="ColumnDetailRecordText" id="MyBookRecordDetail<s:property value='myBid'/>"></font><font style="cursor:pointer" class="ColumnDetailButtonText" id="MyBookRecordDetailButton" onclick="ClickDetailButton(<s:property value='myBid'/>)"></font>
					</div>
		<script>
		myBookRecordDetail('<s:property value="myBid"/>','<s:property value="borrowedUserName"/>','<s:property value="borrowedDate"/>','');
		</script>
		<!--书籍借阅记录的循环体结束-->
					<div class="ColumnDetailRecord" >
						<font class="ColumnDetailButtonText"  style="cursor:pointer" onclick="simpleFloder('ModifyBookForm');pageMoveTo('ModifyBookForm');showBookType();getChangeBookDetail('<s:property value='myBid'/>')">修改书目</font>&nbsp;&nbsp;&nbsp;&nbsp;<font class="ColumnDetailButtonText" onclick="goBookDetailPage('<s:property value='myBid'/>');">进入书籍页面</font>
					</div>
				</div>
<script>
myBookRecordInfo('<s:property value="myBid"/>','<s:property value="myBookName"/>');
</script>
</s:iterator> 
<!--拥有书籍的循环体结束-->
				<div class="ColumnBodyRecord" onclick="simpleFloder('AddBookForm');showBookType();">
					<font class="ColumnButtonText" style="cursor:pointer">添加书目</font>
				</div>
				<div class="InnerForm" id="AddBookForm">
					<div class="ColumnBodySimpleForm" >
						<div class="SimpleFormTitle">
							<font class="RecordText">书籍名称</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookNameInput"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">作者</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookAuthorInput"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">出版社</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookPrintInput"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">类别(点击可切换类别)</font>
						</div>
						<div class="ColumnBodyRecord">
						<font class="ColumnButtonText" id="BookType" onclick="switchBookType();" style="cursor:pointer"></font>
					</div> 
					</div>
					
					<div class="FormAlert" id="AddBookFormAlert">
						<font class="FormAlertText" id="AddBookFormAlertText"></font><br>
					</div>
					<div class="IconContent" onclick="submitAddBook()">
						<div class="SubmitIconGray" style="cursor:pointer">
						</div>
					</div>
				</div>
				
				<!-- modify book -->
				<div class="InnerForm" id="ModifyBookForm">
					<font class="ColumnButtonText">修改书目</font>
					<div class="Dots">
						<div class="DotsContent">
							<font class="DotsText">·</font>
						</div>
					</div>
					<div class="ColumnBodySimpleForm" >
						<div class="SimpleFormTitle">
							<font class="RecordText">书籍名称</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookNameInput2"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">作者</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookAuthorInput2"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">出版社</font>
						</div>
						<div class="SimpleFormInput">
							<input class="SimpleForm" type="text" id="BookPrintInput2"></input>
						</div>
					</div>
					<div class="ColumnBodySimpleForm">
						<div class="SimpleFormTitle">
							<font class="RecordText">类别(点击可切换类别)</font>
						</div>
						<div class="ColumnBodyRecord">
						<font class="ColumnButtonText" id="BookType2" onclick="switchBookType2();" style="cursor:pointer"></font>
					</div> 
					</div>
					
					<div class="FormAlert" id="AddBookFormAlert">
						<font class="FormAlertText" id="AddBookFormAlertText"></font><br>
					</div>
					<div class="IconContent" onclick="submitModifyBook()">
						<div class="SubmitIconGray" style="cursor:pointer">
						</div>
					</div>
				</div>
				<!-- modify book end -->
				
			</div>
			<div class="Space">
			</div>
		</div>
		<div class="Hidden" id="MyBookHidden">
			<div class="HiddenContent" id="BorrowedBookHiddenContent">
				<font class="HiddenText">·<br>·<br>·</font>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column" onclick="doubleFloder('BorrowedBookShown','BorrowedBookHidden');">
			<div class="ColumnContent">
				<font class="ColumnText" style="cursor:pointer">我的借阅</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="BorrowedBookButtonIMG" style="cursor:pointer">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="BorrowedBookShown">
			<div class="Space">
			</div>
			<div class="ColumnBodyContent" id="BorrowedBookContent">
<!--借阅书籍的循环体开始-->
<s:iterator value="myBorrowList" status="u" id="myBorrowList">
				<div class="ColumnBodyRecord" onclick="simpleFloder('BorrowedBookDetail<s:property value='myBid'/>');">
					<font class="RecordText" style="cursor:pointer" id="BorrowedBookRecord<s:property value='myBid'/>"></font>
				</div>
				<div class="ColumnDetail" id="BorrowedBookDetail<s:property value='myBid'/>">
		<!--借阅书籍详细记录的循环体开始-->
							<div class="ColumnDetailRecord">
								<font class="ColumnDetailRecordText" id="BorrowedBookRecordDetail<s:property value='myBid'/>"></font><font style='cursor:pointer' class="ColumnDetailButtonText" id="BorrowedBookRecordDetailButton<s:property value='myBid'/>" onclick="ClickDetailButton('<s:property value='myBid'/>')"></font>
							</div>
		<script>
		borrowedBookRecordDetail('<s:property value="myBid"/>','<s:property value="borrowedUserName"/>','<s:property value="borrowedDate"/>',"申请还书");
		</script>
		<!--借阅书籍详细记录的循环体结束-->
					<!-- 
					<div class="ColumnDetailRecord" id="BookCommentButton">
						<font class="ColumnDetailButtonText" onclick="simpleFloder('CommentForm');">进行评论</font>
					</div>
                    <div class="InnerForm" id="CommentForm">
                        <div class="ColumnBodySimpleForm" >
                            <input class="CommentFormInput" id="CommentInput"></input>
                        </div>
                        <div class="FormAlert" id="CommentFormAlert">
					       <font class="FormAlertText" id="CommentFormAlertText"></font><br>
				        </div>
                        <div class="IconContent" onclick="submitComment('')">
                            <div class="SubmitIconGray">
                            </div>
                        </div>
                    </div>
                    -->
				</div>
<script>
borrowedBookRecordInfo('<s:property value="myBid"/>','<s:property value="myBookName"/>','<s:property value="borrowedUserName"/>');
//setCommentAvalibility('',false);
</script>
</s:iterator> 
<!--借阅书籍的循环体结束-->
			</div>
			<div class="Space">
			</div>
		</div>
		<div class="Hidden" id="BorrowedBookHidden">
			<div class="HiddenContent" id="BorrowedBookHiddenContent">
				<font class="HiddenText">·<br>·<br>·</font>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column" onclick="checkApplyInfo()">
			<div class="ColumnContent">
				<font class="ColumnText" style="cursor:pointer">我目前的申请</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" style="cursor:pointer">
				<img src="CSS/IMG/apply.ico"/>
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="CheckApply" >
		</div>
		
		<font class="HiddenText"  id="CheckApplyDot">·<br>·<br>·</font>
		<div class="Space">
		</div>
		<div class="Column" onclick="simpleFloder('ChangePasswordForm');">
			<div class="ColumnContent">
				<font class="ColumnText" style="cursor:pointer">修改密码</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="ChangePasswordIcon" style="cursor:pointer">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="ColumnBody" id="ChangePasswordForm">
			<div class="Space">
			</div>
			<div class="InnerFormVisible" id="ChangePasswordInnerForm">
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">旧密码</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="password" id="OldPasswordInput"></input>
					</div>
				</div>
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">新的密码</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="password" id="NewPasswordInput"></input>
					</div>
				</div>
				<div class="ColumnBodySimpleForm">
					<div class="SimpleFormTitle">
						<font class="RecordText">重复新密码</font>
					</div>
					<div class="SimpleFormInput">
						<input class="SimpleForm" type="password" id="NewPasswordReinput"></input>
					</div>
				</div>
                <div class="FormAlert" id="ChangePasswordFormAlert">
					<font class="FormAlertText" id="ChangePasswordFormAlertText"></font><br>
				</div>
				<div class="IconContent" onclick="submitNewPassword();">
					<div class="SubmitIconGray" style="cursor:pointer">
					</div>
				</div>
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
	</div>
</body>
</html>

