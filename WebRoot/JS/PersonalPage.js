var tmpBid = 0;
function setCoverMessage(name,id,bookNum,score)
{
	textChange("UserNameText",name);
	textChange("UserIDText",id);
	textChange("BookNumberText","现有书目<br>"+bookNum);
	textChange("ScoreText","现有积分<br>"+score);
}

function myBookRecordInfo(bookID,bookName)
{
	textChange("MyBookRecord"+bookID,bookName);
}

function myBookRecordDetail(bookID,name,date,button)
{
	if(name!="null"){
		textChange("MyBookRecordDetail"+bookID,"被 "+name+" 于 "+date+" 借走 ");
		textChange("MyBookRecordDetailButton",button);
	}
}

function goBookDetailPage(bid){
	window.location.href="getBookDetail.action?bid="+bid;
}

function borrowedBookRecordInfo(bookID,bookName,name)
{
	textChange("BorrowedBookRecord"+bookID,bookName+"(由"+name+"提供)");
}

function borrowedBookRecordDetail(id,name,date,button)
{
	textChange("BorrowedBookRecordDetail"+id,"于  "+date+" 借阅 。");
	textChange("BorrowedBookRecordDetailButton"+id,button);
}

function ClickDetailButton(bid)
{
	$.ajax({
		  url:"applyReturn.action?bid="+bid,
		  type:"post",
		  dataType:"text",
		  success:function(data){
		  	alert(data);	
		  },
		  error:function(){
		  	alert("AJAX 未知错误,请重试~");
		  }
	});
}

function goBookPage()
{

}

var bookType=1;//添加的变量--------------------------------------------------------------------------------------------------------------------------
var type=new Array;//这里要想办法把这个数组设成书籍的类型
type[1]="绘本";
type[2]="童话";
type[3]="小说";
type[4]="科普";
type[5]="诗歌";
type[6]="散文";
type[7]="其他";

function submitAddBook()
{
    var name=getInputContent("BookNameInput");
    var author=getInputContent("BookAuthorInput");
    var print=getInputContent("BookPrintInput");
    //var type=getInputContent("BookTypeInput");
    var type = bookType;
    if(name!=""&&author!==""&&print!=""&&type!==""){
    	 var bookInfo = "{\"bookname\":\""+name+"\",\"author\":\""+author+"\",\"press\":\""+print+"\",\"category\":"+type+"}";
    		
    		$.ajax({
    			  url:"addBook.action",
    			  type:"post",
    			  data:{
    				  bookInfo:bookInfo,
    			  },
    			  dataType:"text",
    			  success:function(data){	
    			  	formAlert("AddBookFormAlert","AddBookFormAlertText","成功添加书目！");
    		    	clearAddBookForm();	  	
    		    	location.reload();
    			  },
    			  error:function(){
    				  formAlert("AddBookFormAlert","AddBookFormAlertText","可能由于网络原因添加失败，请重试！");
    			  }
    			  });
    }else{
		  formAlert("AddBookFormAlert","AddBookFormAlertText","请完善书本信息！");
    }
   
}

function submitNewPassword()
{
	var oldpsw=getInputContent("OldPasswordInput");
    var newpsw=getInputContent("NewPasswordInput");
    var newpswre=getInputContent("NewPasswordReinput");
    if(newpsw==newpswre)
    {
		//可利用ajax返回结果，若登陆不成功则调用formAlert函数返回错误信息
    	var changeInfo = "{\"oldpsw\":\""+oldpsw+"\",\"newpsw\":\""+newpsw+"\"}";
    	
    	$.ajax({
    		  url:"changePwd.action",
    		  type:"post",
    		  data:{
    			  changeInfo:changeInfo,
    		  },
    		  dataType:"text",
    		  success:function(data){
    			  if(data=="Success"){
    				  alert("修改成功，请重新登录~");
    				  window.location.href="Login.jsp";
    			  }else{
    				  formAlert("ChangePasswordFormAlert","ChangePasswordFormAlertText","旧密码错误，请重试");
    			  }
    		  },
    		  error:function(){
    		  	alert("AJAX 未知错误,请重试~");
    		  }
    		  });
    }
    else
    {
        formAlert("ChangePasswordFormAlert","ChangePasswordFormAlertText","两次输入的新密码不一致");
    }
}

function setCommentAvalibility(id,flag)
{
    setDisplay("BookCommentButton"+id,flag);
}

function submitComment(id)
{
    var comment=getInputContent("CommentInput"+id);
    if(comment!="")
    {
        //可利用ajax返回结果，若登陆不成功则调用formAlert函数返回错误信息
    }
    else
    {
        formAlert("CommentFormAlert"+id,"CommentFormAlertText"+id,"请输入内容");
    }
}

function clearAddBookForm()
{
	setInputContent("BookNameInput","");
	setInputContent("BookAuthorInput","");
	setInputContent("BookPrintInput","");
	//setInputContent("BookTypeInput","");
}

function showBookType()//添加的函数--------------------------------------------------------------------------------------------------------------------------
{
	textChange("BookType",type[bookType]);
}

function switchBookType()//添加的函数--------------------------------------------------------------------------------------------------------------------------
{
	bookType=((bookType)%type.length)+1;
	if(bookType == 8){
		bookType = 1;
	}
	showBookType();
}

function getChangeBookDetail(bid)
{
	tmpBid = bid;
	$.ajax({
		  url:"getChangeBookDetail.action",
		  type:"post",
		  data:{
			  bid:bid,
		  },
		  dataType:"json",
		  success:function(data){
			  setInputContent("BookNameInput2",data.bookname);
			  setInputContent("BookAuthorInput2",data.author);
			  setInputContent("BookPrintInput2",data.press);
			  textChange("BookType2",data.type);
		  },
		  error:function(){
		  	alert("AJAX 未知错误,请重试~");
		  }
		  });
}

function switchBookType2()//添加的函数--------------------------------------------------------------------------------------------------------------------------
{
	bookType=((bookType)%type.length)+1;
	if(bookType == 8){
		bookType = 1;
	}
	textChange("BookType2",type[bookType]);
}

function submitModifyBook()
{
	bid = tmpBid;
    var name=getInputContent("BookNameInput2");
    var author=getInputContent("BookAuthorInput2");
    var print=getInputContent("BookPrintInput2");
    //var type=getInputContent("BookTypeInput");
    var type = bookType;
    if(name!=""&&author!==""&&print!=""&&type!==""){
    	 var bookInfo = "{\"bid\":"+bid+",\"bookname\":\""+name+"\",\"author\":\""+author+"\",\"press\":\""+print+"\",\"category\":"+type+"}";
    		
    		$.ajax({
    			  url:"modifyBook.action",
    			  type:"post",
    			  data:{
    				  bookInfo:bookInfo,
    			  },
    			  dataType:"text",
    			  success:function(data){
    				  if(data=="success"){
    					  alert("成功修改书目！");
    					  location.reload();
    				  }else{
    					  alert("可能由于网络原因修改失败，请重试！");
    				  }
    			  	
    			  },
    			  error:function(){
    				  alert("可能由于网络原因ajax调用失败，请重试！");
    			  }
    			  });
    }else{
			alert("请完善书本信息！");
    }
}

var checkApplyflag = 0;
function checkApplyInfo(){
	if(checkApplyflag == 0){
		$.ajax({
	          url:"getCheckApply.action",
	          type:"post",
	          dataType:"json",
	          success:function(data){
	              var str = "<div class=\"Space\"></div>";
	              if (data.count==0){
	            	  str += "暂无相关申请~";
	              }
	              if (data.count>0){
	                var applys = data.apply;
	                for (var i = 0; i < data.count; i++) {
	                    var bookname = applys[i].bookname;
	                    var username = applys[i].username;
	                    var status = applys[i].status;
	                    if(status == "borrow"){
	                        var strtmp = "您已向 "+username+" 同学申请借阅《"+bookname+"》一书了！<br>";
	                        str += strtmp;
	                    }else if(status == "return"){
	                        var strtmp = "您已向 "+username+" 同学申请归还《"+bookname+"》一书了！<br>";
	                        str += strtmp;
	                    }
	                  };   
	              }
	              str += "<div class=\"Space\"></div>";
	              document.getElementById("CheckApply").innerHTML=str;
	              document.getElementById("CheckApplyDot").innerHTML="";
	              checkApplyflag = 1;
	          },
	          error:function(){
	              alert("可能由于网络原因ajax调用失败，请重试！");
	          }
	    });
	}
	else if(checkApplyflag == 1){
		document.getElementById("CheckApply").innerHTML="";
		document.getElementById("CheckApplyDot").innerHTML="·<br>·<br>·";
		checkApplyflag = 0;
	}
    
}