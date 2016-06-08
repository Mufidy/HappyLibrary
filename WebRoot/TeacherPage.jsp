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
	<title>教师管理页面</title>
	<style>
	</style>
	<link href="CSS/Body.css" rel="stylesheet" type="text/css">
	<link href="CSS/TopBar.css" rel="stylesheet" type="text/css">
	<link href="CSS/GlobalFrame.css" rel="stylesheet" type="text/css">
	<link href="CSS/TeacherPage.css" rel="stylesheet" type="text/css">
	<script src="JS/GlobalFunctions.js">
	</script>
	<script src="JS/RandomBackground.js">
	</script>
	<script src="JS/TeacherPage.js">
	</script>
	<script src="JS/jquery-1.11.1.js">
	</script>
	<script src="JS/FolderEffect.js">
	</script>
	<script src="JS/TopBarMessage.js">
	</script>
</head>
<body>
	<%@include file="Teacher.html"%>
<script>
showLoginConditionT('<%=username%>');
</script>
	<%@include file="CoverWithMotto.html"%>
<script>
randomBackground();
randomMotto();
</script>
	<div id="Content">
		<div class="Space">
		</div>
		<div class="Column">
			<div class="ColumnContent">
				<font class="ColumnText">我的班级</font>
			</div>
			<div class="IconContent">
				<div class="IconIMG" id="TeacherIcon">
				</div>
			</div>
		</div>
		<div class="Space">
		</div>
		<div class="Column">
		<div class="ColumnContent">
			<div class="TwoLaneButton">
				<div class="LeftLanePart" onclick="sortByID();">
					<font class="ColumnText" id="PreviousPageTextTeacher" style="cursor:pointer" >按学号排序</font><br>
				</div>
				<div class="RightLanePart" onclick="sortByScore();">
					<font class="ColumnText" id="NextPageTextTeacher" style="cursor:pointer">按分数排序</font><br>
				</div>
			</div>
		</div>
	</div>
		
		<div class="ColumnBody">
			<div class="Space">
			</div>
			<div class="ColumnBodyContent">
<!--学生记录的循环体开始-->
<s:iterator value="studentList" status="u" id="studentList">
				<div class="ColumnBodyRecord" onclick="simpleFloder('StudentDetail<s:property value='studentID'/>');adaptStudentInfoHeight('<s:property value="studentID"/>');" style="cursor:pointer" >
					<font class="RecordText" id="StudentInfo<s:property value='studentID'/>"></font>
				</div>
				<div class="ColumnDetail" id="StudentDetail<s:property value='studentID'/>">
					<div class="TwoLaneColumn" id="MyBookTwoLane<s:property value='studentID'/>">
						<div class="LeftLaneColumn" id="MyBookLeftLane<s:property value='studentID'/>">
		    				<div class="LeftColumnDetailRecord">
								<font class="ColumnDetailRecordText">拥有书籍</font>
							</div>
						</div>
						<div class="RightLaneColumn" id="MyBookRightLane<s:property value='studentID'/>">
<!--拥有书籍记录的循环体开始-->
		<s:iterator value="myHaveBookList" id="myHaveBookList">		
							<div class="RightColumnDetailRecord">
								<font class="ColumnDetailRecordText" id="MyBook<s:property value='myHaveBookID'/>"></font>
							</div>

				<!--拥有书籍详细记录的循环体开始-->
											<div class="RightColumnDetailRecord">
												<font class="ColumnDetailRecordSecondaryText" id="MyBookRecordDetail"></font>
											</div>
				<script>
				//myBookRecordDetail('','','','');
				</script>
				<!--拥有书籍详细记录的循环体结束-->

		<script>
		myBook('<s:property value="myHaveBookID"/>','<s:property value="myHaveBookName"/>');
		</script>
		</s:iterator>
						</div>
					</div>
						
<!--拥有书籍记录的循环体end-->
					<div class="TwoLaneColumn" id="BorrowedBookTwoLane<s:property value='studentID'/>">
						<div class="LeftLaneColumn" id="BorrowedBookLeftLane<s:property value='studentID'/>">
		    				<div class="LeftColumnDetailRecord">
								<font class="ColumnDetailRecordText">借阅记录</font>
							</div>
						</div>
						<div class="RightLaneColumn" id="BorrowedBookRightLane<s:property value='studentID'/>">
<!--借阅记录的循环体开始-->
	<s:iterator value="myBorrowBookList" id="myBorrowBookList">
							<div class="RightColumnDetailRecord">
								<font class="ColumnDetailRecordText" id="BorrowedBook<s:property value='borrowID'/>"></font>
							</div>
											<!--借阅记录的循环体详细记录循环体开始-->
																		<div class="RightColumnDetailRecord">
																			<font class="ColumnDetailRecordSecondaryText" id="BorrowedBookRecordDetail<s:property value='borrowID'/>"></font>
																		</div>
											<script>
											borrowedBookRecordDetail('<s:property value="borrowID"/>','<s:property value="borrowUserName"/>','<s:property value="borrowDate"/>','<s:property value="borrowStatus"/>','<s:property value="borrowReturnDate"/>');
											</script>
											<!--借阅记录的循环体详细记录循环体结束-->
	<script>
	borrowedBook('<s:property value="borrowID"/>','<s:property value="borrowBookName"/>','<s:property value="borrowUserName"/>');
	</script>
	</s:iterator>
						</div>
					</div>
<!--借阅记录的循环体结束-->
					<div class="TwoLaneColumn" id="ScoreTwoLane<s:property value='studentID'/>">
						<div class="LeftLaneColumn" id="ScoreLeftLane<s:property value='studentID'/>">
		    				<div class="LeftColumnDetailRecord">
								<font class="ColumnDetailRecordText">积分记录</font>
							</div>
						</div>
						<div class="RightLaneColumn" id="ScoreRightLane<s:property value='studentID'/>">
<!--积分记录的循环体开始-->
	<s:iterator value="scoreHistoryList" id="scoreHistoryList">
							<div class="RightColumnDetailRecord">
								<font class="ColumnDetailRecordSecondaryText" id="ScoreRecordDetail<s:property value='sid'/>"></font>
							</div>
<script>
scoreRecordDetail('<s:property value="sid"/>','<s:property value="reason"/>','<s:property value="score"/>','<s:property value="scoredate"/>');
</script>
	</s:iterator>
<!--积分记录的循环体结束-->
						</div>
					</div>
					<div class="ColumnDetailRecord" onclick="simpleFloder('EditPasswordForm<s:property value="studentID"/>');">
						<font class="ColumnDetailRecordText" style="cursor:pointer" >修改密码</font>
					</div>
					<div class="InnerForm" id="EditPasswordForm<s:property value='studentID'/>">
						<div class="ColumnBodySimpleForm">
							<div class="SimpleFormTitle">
								<font class="RecordText">新的密码</font>
							</div>
							<div class="SimpleFormInput<s:property value='studentID'/>">
								<input class="SimpleForm" type="text" id="SpeStudentPwd<s:property value='studentID'/>"></input>
							</div>
						</div>
						<div class="IconContent" style="cursor:pointer" onclick="changeStuPwd('<s:property value="studentID"/>')">
							<div class="SubmitIconGray">
							</div>
						</div>
					</div>
					<div class="ColumnDetailRecord">
						<font class="ColumnDetailRecordText">·</font>
					</div>
				</div>
<script>
studentInfo('<s:property value="studentID"/>','<s:property value="studentName"/>','<s:property value="score"/>');
</script>
</s:iterator> 
<!--学生记录的循环体结束-->
			</div>
			<div class="Space">
			</div>
		</div>
		<div class="Dots">
			<div class="DotsContent">
				<font class="DotsText">·</font>
			</div>
		</div>
	</div>
</body>
</html>
