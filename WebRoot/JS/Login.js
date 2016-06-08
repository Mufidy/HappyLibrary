function goToLoginForm()
{
    pageMoveTo("LoginMethod");
    document.getElementById('IDInput').focus();
}

var method=0;

function showLoginMethod()
{
	switch(method)
	{
		case 0:
		textChange("LoginMethod","学生登陆");
		break;
		case 1:
		textChange("LoginMethod","教师登陆");
		break;
	}
}

function switchLoginMethod()
{
	method=((method+1)%2);
	showLoginMethod();
}

function submitLogin()
{
	var id=getInputContent("IDInput");
    var psw=getInputContent("PasswordInput");
    if(id!=""&&psw!=""){
    	switch(method)
    	{
    		case 0:
    		//学生登陆的action
    			var loginInfo = "{\"uid\":\""+id+"\",\"password\":\""+psw+"\"}";
    			
    			$.ajax({
    				  url:"login.action",
    				  type:"post",
    				  data:{
    					  loginInfo:loginInfo,
    				  },
    				  dataType:"text",
    				  success:function(data){
    				  	
    				  	if(data=="SUCCESS"){
    				  		var searchInfo = "{\"s\":\""+0+"\",\"k\":\""+0+"\"}";
    				    	window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
    				  	}else if(data=="ERROR"){
    				  		formAlert("LoginFormAlert","loginFormAlertText","用户名或密码错，请重试！");
    				  	}
    				  },
    				  error:function(){
    				  	alert("AJAX 未知错误");
    				  }
    				  });
    			break;
    		case 1:
    		//教师登陆的action
    			var loginInfo = "{\"tid\":\""+id+"\",\"password\":\""+psw+"\"}";
    			
    			$.ajax({
    				  url:"teacherLogin.action",
    				  type:"post",
    				  data:{
    					  loginInfo:loginInfo,
    				  },
    				  dataType:"text",
    				  success:function(data){
    				  	if(data=="loginSuccess"){
    				  		window.location.href="getClassInfo.action?s=0";
    				  	}else if(data=="loginFail"){
    				  		formAlert("LoginFormAlert","loginFormAlertText","用户名或密码错，请重试！");
    				  	}
    				  },
    				  error:function(){
    				  	alert("AJAX 未知错误");
    				  }
    				  });
    		break;
    	}
    	//可利用ajax返回结果，若登陆不成功则调用formAlert函数返回错误信息
    }else{
    	formAlert("LoginFormAlert","loginFormAlertText","请输入完整信息！");
    }

}

function goRegister()
{
	window.location.href="Register.jsp";
}

function clearPassword()
{
	setInputContent("PasswordInput","");
}