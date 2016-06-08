function textChange(id,textContent)
{
	var text=document.getElementById(id);
	text.innerHTML=textContent;
}

function backGoundChange(id,backGound)
{
	var bg=document.getElementById(id);
	bg.style.backgroundImage=("url("+backGound+")");
}

function textColorChange(id,color)
{
	document.getElementById(id).style.color=color;
}

function setDisplay(id,flag)
{
	var div=document.getElementById(id);
	if(flag)
	{
		div.style.display="inline";
	}
	else
	{
		div.style.display="none";
	}
}

function getInputContent(id)
{
	var input=document.getElementById(id);
	return input.value;
}

function isPureNum(input)
{
    var n = /^[0-9,]*$/;
    if (!n.test(input))
    {
        return false;
    }
    else
    {
        return true;
    }
}

function formAlert(alertID,alertTextID,alertText)
{
    $("#"+alertID).slideDown("slow");
    textChange(alertTextID,alertText);
}

function pageMoveTo(id)
{
    $('html, body').animate({
    scrollTop: $("#"+id).offset().top
    },"slow");
}

function adaptHeight(id,type)
{
    var thisHeight=document.getElementById(type+"RightLane"+id).offsetHeight+15;
    document.getElementById(type+"TwoLane"+id).style.height=thisHeight+"px";
    document.getElementById(type+"LeftLane"+id).style.height=thisHeight+"px";
}

function goAjax(method,sorce,flag)
{
	var ajaxCondition;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		ajaxCondition=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		ajaxCondition=new ActiveXObject("Microsoft.XMLHTTP");
	}
	ajaxCondition.open("POST",sorce,flag );
	ajaxCondition.send();
    return ajaxCondition.responseText;
}

function inputProcessing(ipt)
{
	return ipt;
}

function outputProcessing(opt)
{
	return opt;
}

function setInputContent(id,content)
{
	document.getElementById(id).value=content;
}
