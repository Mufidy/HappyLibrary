function goToRegisterForm()
{
    pageMoveTo("RegisterForm");
    document.getElementById('IDInput').focus();
}



function submitRegister()
{
	var id=getInputContent("IDInput");
    var name=getInputContent("NameInput");
    var psw=getInputContent("PasswordInput");
    var repsw=getInputContent("PasswordReinput");
    if((id!="")&&(name!="")&&(psw!="")&&(repsw!=""))
    {
        if(psw==repsw)
        {
			var registerInfo = "{\"uid\":\""+id+"\",\"password\":\""+psw+"\",\"username\":\""+name+"\"}";
			$.ajax({
				  url:"register.action",
				  type:"post",
				  data:{
					  registerInfo:registerInfo,
				  },
				  dataType:"text",
				  success:function(data){
				  	
				  	if(data=="SUCCESS"){
				  		alert("注册成功");
				  		var searchInfo = "{\"s\":\""+0+"\",\"k\":\""+0+"\"}";
				    	window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
				  	}else if(data=="ERROR"){
				  		formAlert("RegisterFormAlert","RegisterFormAlertText","该学号已经存在,请重新注册！");
				  		//document.getElementById("IDInput").value="";
				  		document.getElementById("NameInput").value="";
				  		document.getElementById("PasswordInput").value="";
				  		document.getElementById("PasswordReinput").value="";

				  	}
				  },
				  error:function(){
					  formAlert("RegisterFormAlert","RegisterFormAlertText","注册失败");
				  }
			});
        }
        else
        {
            formAlert("RegisterFormAlert","RegisterFormAlertText","两次输入的密码不一致");
        }
    }
    else
    {
        formAlert("RegisterFormAlert","RegisterFormAlertText","请输入完整的信息");
    }
}