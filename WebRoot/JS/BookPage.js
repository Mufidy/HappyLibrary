function bookInfo(bookName,bookAuthor,bookPrint,bookType,owner,flag,borrowusername,borrowdate)
{
	textChange("BookName",bookName);
	textChange("BookAuthor",bookAuthor);
	textChange("BookPrint",bookPrint);
	textChange("BookType",bookType);
	textChange("Owner","主人: "+owner+"<br>");
    if(flag=="1")
    {
        textChange("Avaliability","可借");
        textColorChange("Avaliability","#0000E3");
    }
    else
    {
        textChange("Avaliability","不可借");
        textColorChange("Avaliability","#FF0000");
        if(flag=="3"){
        	textChange("borrowInfo","这本书已经被"+borrowusername+"预定啦~");
        }else{
        	textChange("borrowInfo","被"+borrowusername+"于"+borrowdate+"借阅，暂未归还！");
        }
    }
}

function bookDescription(descriotionID,description)
{
	textChange("BookDescription"+descriotionID,description);
}

function bookComment(commentID,comment,user,date)
{
	textChange("ReaderComment"+commentID,comment);
	textChange("ReaderCommentDetail"+commentID,"by "+user+" "+date);
}

function showConfirmColumn(flag)
{
	setDisplay("ConfirmColumn",flag);
}

function borrowConfirm(bid)
{
	var r = confirm("确认要申请借阅这本书吗？");
	if(r==true){
		$.ajax({
			  url:"applyBorrow.action?bid="+bid,
			  dataType:"text",
			  success:function(data){
				  if(data=="1"){
					  alert("恭喜你，申请成功~请等候对方确认！");
					  location.reload();
				  }else{
					  alert(data);
				  }	
			  },
			  error:function(){
			  	alert("可能由于网络原因，申请失败，请重试！");
			  }
		 });
	}
}

function newCommentConfirm(bid)
{
	var comment=getInputContent("NewCommentInput");
	if(comment==""){
		formAlert("NewCommentFormAlert","NewCommentFormAlertText","请输入评论内容！");
	}else{
		var commentInfo = "{\"bid\":\""+bid+"\",\"comment\":\""+comment+"\"}";
		$.ajax({
			  url:"addComment.action",
			  type:"post",
			  data:{
				  commentInfo:commentInfo,
			  },
			  dataType:"text",
			  success:function(data){
				if(data=="addSuccess"){
					setInputContent("NewCommentInput","");
					formAlert("NewCommentFormAlert","NewCommentFormAlertText","评论成功");
					location.reload();
				}else{
					formAlert("NewCommentFormAlert","NewCommentFormAlertText","评论失败");
				}
			  },
			  error:function(){
			  	alert("AJAX 未知错误,请重试~");
			  }
		});
	}
}

