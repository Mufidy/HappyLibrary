var totalMessages=0;

function showLoginCondition(response)
{
	var text=document.getElementById("LoginCondoitinText");
	var button=document.getElementById("LoginConditionButton");
	document.getElementById("TopBarAllBooks").innerHTML = "所有书目 ";
	document.getElementById("TopBarPerson").innerHTML = "个人中心 ";
	document.getElementById("TopBarLogout").innerHTML = "退出登录";
	if(response=="")
	{
		text.innerHTML="欢迎来到图书馆 ";
		button.innerHTML="请登录";
	}
	else
	{
		text.innerHTML="当前登入 "+response;
	}
}

function showLoginConditionT(response)
{
	var text=document.getElementById("LoginCondoitinText");
	var button=document.getElementById("LoginConditionButton");
	document.getElementById("TopBarAllBooks").innerHTML = "所有书目 ";
	document.getElementById("TopBarLogout").innerHTML = "退出登录";
	if(response=="")
	{
		text.innerHTML="欢迎来到图书馆 ";
		button.innerHTML="请登录";
	}
	else
	{
		text.innerHTML="当前登入 "+response;
	}
}

function logOut(){
	$.ajax({
	    url:"logout.action",
	    type:"post",
	    dataType:"text",
	    success:function(data){
	    	if(data=="logoutSuccess"){
	    		window.location.href="Login.jsp"; 
	    	}
	    },
	    error:function(){
	    	alert("可能由于网络原因退出失败，请重试！");
	    }
	});
}

function hideMessage()
{
	$(".TopBarMessage").slideUp("slow");
}

function goPersonalPage(response)
{
	if(response=="teacher"){
		window.location.href="getClassInfo.action?s=0";
	}else{
		window.location.href="getUserInfo.action";
	}
}

function goBookShow(response)
{
	if(response=="teacher"){
		var searchInfo = "{\"s\":\""+0+"\",\"k\":\""+0+"\"}";
		window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
	}else{
		window.location.href="index.jsp"; 
	}
}


function newMessage(user,book,operation,bid,flag,i)
{
	if(flag == 1){
		var msg=document.createElement("div");
		msg.innerHTML="<div id='aaa' style='background-color:#000033'><font class='OnTopBar'>"+user+" 申请向您"+operation+book+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style='cursor:pointer' onclick='confirmBorrow("+bid+")'>确认借出</font>&nbsp;&nbsp;&nbsp;&nbsp;<font style='cursor:pointer' onclick='rejectBorrow("+bid+")'>拒绝借出</font></font></div>";
		$("#MainTopBar").append(msg);
	}else if (flag == 2){
		var msg=document.createElement("div");
		msg.innerHTML="<div id='aaa' style='background-color:#000033'><font class='OnTopBar'>"+user+" 申请向您"+operation+book+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style='cursor:pointer' onclick='confirmReturn("+bid+")'>确认已归还</font></font></div>";
		$("#MainTopBar").append(msg);
	}else if(flag == 3){
		var msg=document.createElement("div");
		msg.innerHTML="<div id='bbb' style='background-color:#000033'><font class='OnTopBar'>"+"您向"+user+"借阅的"+book+"在 "+operation+"到期，请记得及时归还哦~ "+"</font></div>";
		$("#MainTopBar").append(msg);
	}else if(flag == 4){
		var msg=document.createElement("div");
		msg.innerHTML="<div id='ddd' style='background-color:#000033'><font class='OnTopBar'>"+"您已确认将"+book+"借给"+user+"，记得及时带给他/她哦</font></div>";
		$("#MainTopBar").append(msg);
	}else if(flag == 5){
		var msg=document.createElement("div");
		msg.innerHTML="<div id='eee' style='background-color:#000033'><font class='OnTopBar'>"+"对方已经同意将"+book+"借给你啦~收到请确认哦&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font style='cursor:pointer' onclick='confirmGet("+bid+")'>确认收到</font></font></div>";
		$("#MainTopBar").append(msg);
	}
	
}

$(document).ready(function(){
	
	pushMsg();
	
});

function pushMsg()
{
		$.ajax({
		    url:"getPushMsg.action",
		    type:"post",
		    dataType:"json",
		    success:function(data){
//		    	var msg=document.createElement("div");
//				msg.innerHTML="<div id='aaa'></div>";
//				$("#MainTopBar").append(msg);
//				var msg2=document.createElement("div");
//				msg2.innerHTML="<div id='bbb'></div>";
//				$("#MainTopBar").append(msg2);
//				var msg3=document.createElement("div");
//				msg3.innerHTML="<div id='ddd'></div>";
//				$("#MainTopBar").append(msg3);
//				var msg4=document.createElement("div");
//				msg4.innerHTML="<div id='eee'></div>";
//				$("#MainTopBar").append(msg4);
		    	removeDiv("aaa");
				removeDiv("bbb");
				removeDiv("ddd");
				removeDiv("eee");
		    	if(data.flag==0){
		    		//location.href="Login.jsp";
		    	}else if(data.flag==1){
		    		if(data.applynum>0){
		    			var applys = data.apply;
		    			for(var i=0;i<data.applynum;i++){
		    				var bid  = applys[i].bid;
		    				var bookname = applys[i].bookname;
		    				var username = applys[i].username;
		    				var status   = applys[i].status;
		    				var operation = "借阅";
		    				var x=1;
		    				if(status=="return"){
		    					operation = "归还";
		    					x = 2;
		    				}else if(status=="borrow"){
		    					operation = "借阅";
		    					x = 1;
		    				}else if(status=="bring"){
		    					x = 4;
		    				}else if(status=="get"){
		    					x=5;
		    				}
		    				newMessage(username,bookname,operation,bid,x,i);
		    			}
		    		}
		    		if(data.borrownum>0){
		    			var borrows = data.borrow;
		    			for(var i=0;i<data.borrownum;i++){
		    				var borrowid = borrows[i].borrowid;
		    				var bookname = borrows[i].bookname;
		    				var username = borrows[i].username;
		    				var returndate = borrows[i].returndate;
		    				newMessage(username,bookname,returndate,borrowid,3);
		    			}
		    		}
		    	}
		    },
		    error:function(){
		    	//alert("Get Push Message AJAX Error");
		    }
		});
	
	t=setTimeout("pushMsg()",60000);
}

function removeDiv(strID) {
    var all = document.body.getElementsByTagName("*");
    for (var i = all.length; i > 0; --i) 
    {
        var o = all[i - 1];
        if (o.id == strID) 
        	o.parentNode.removeChild(o);
    }
}

function confirmBorrow(bid){
	$.ajax({
	    url:"confirmBorrow.action?bid="+bid,
	    type:"post",
	    dataType:"text",
	    success:function(data){
	    	removeDiv("aaa");
			alert(data); 
			location.reload();
	    },
	    error:function(){
	    	alert("AJAX 未知错误");
	    }
	});
}

function rejectBorrow(bid){
	$.ajax({
	    url:"rejectBorrow.action?bid="+bid,
	    type:"post",
	    dataType:"text",
	    success:function(data){
	    	removeDiv("aaa");
			alert(data);	
			location.reload();
	    },
	    error:function(){
	    	alert("AJAX 未知错误");
	    }
	});
}

function confirmReturn(bid){
	$.ajax({
	    url:"confirmReturn.action?bid="+bid,
	    type:"post",
	    dataType:"text",
	    success:function(data){
	    	removeDiv("aaa");
			alert(data);
			location.reload();
	    },
	    error:function(){
	    	alert("AJAX 未知错误");
	    }
	});
}

function confirmGet(bid){
	$.ajax({
	    url:"confirmGet.action?bid="+bid,
	    type:"post",
	    dataType:"text",
	    success:function(data){
	    	removeDiv("eee");
			alert(data);
			location.reload();
	    },
	    error:function(){
	    	alert("AJAX 未知错误");
	    }
	});
}

function goBookShowTeacher(){
	var searchInfo = "{\"s\":\""+0+"\",\"k\":\""+0+"\"}";
	window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
}
