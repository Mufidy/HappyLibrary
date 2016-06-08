function bookRowInfo(bookID,bookName,bookAuthor,boookPrint,bookType,bookAvaliability,owner)
{
	
	textChange("BookName"+bookID,bookName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"(由"+owner+"提供)");
	textChange("BookAuthor"+bookID,bookAuthor);
	textChange("BookPrint"+bookID,boookPrint);
	textChange("BookType"+bookID,bookType);
    if(bookAvaliability)
    {
        textChange("BookAvaliability"+bookID,"可借");
        textColorChange("BookAvaliability"+bookID,"#0000E3");
    }
    else
    {
        textChange("BookAvaliability"+bookID,"不可借");
        textColorChange("BookAvaliability"+bookID,"#FF0000");
    }
    var thisHeight=document.getElementById("ShowRowLeft"+bookID).offsetHeight;
    document.getElementById("ShowRowOne"+bookID).style.height=thisHeight+"px";
    document.getElementById("ShowRowRight"+bookID).style.height=thisHeight+"px";
    document.getElementById("BookAvaliability"+bookID).style.top=thisHeight/2-19+"px";
}

var method=1;

function showSearchMethod()
{
	switch(method)
	{
		case 1:
			textChange("SearchMethod","按书名搜索");
			textChange("searchTips","");
			break;
		case 2:
			textChange("SearchMethod","按作者搜索");
			textChange("searchTips","");
			break;
		case 3:
			textChange("SearchMethod","按类别搜索");
			textChange("searchTips","可选类别：绘本，童话，小说，科普，诗歌，散文，其他");
			break;
		case 4:
			textChange("SearchMethod","按所有人搜索");
			textChange("searchTips","");
			break;
	}
}

function switchSearchMethod()
{
	method=((method)%4)+1;
	showSearchMethod();
}

var naviButtonMode=0;
function navigationButton(v)
{
    naviButtonMode=v;
    switch(naviButtonMode)
    {
        case 1:
            textChange("PreviousPageText","");
            backGoundChange("PreviousPageIcon","");
            textChange("NextPageText","下一页");
            backGoundChange("NextPageIcon","CSS/IMG/NextPage.jpg");
            break;
        case 0:
            textChange("PreviousPageText","上一页");
            backGoundChange("PreviousPageIcon","CSS/IMG/PreviousPage.jpg");
            textChange("NextPageText","下一页");
            backGoundChange("NextPageIcon","CSS/IMG/NextPage.jpg");
            break;
        case (-1):
            textChange("PreviousPageText","上一页");
            backGoundChange("PreviousPageIcon","CSS/IMG/PreviousPage.jpg");
            textChange("NextPageText","");
            backGoundChange("NextPageIcon","");
            break;
    }
}

function goSearch()
{
    var SearchKeyName = document.getElementById("SearchKeyName").value;
    if(SearchKeyName==""){
    	alert("请输入关键词~");
    }else{
    	var searchInfo = "{\"s\":\""+method+"\",\"k\":\""+SearchKeyName+"\"}";
    	window.location.href="getAllBooks.action?searchInfo="+searchInfo+"&p=1";
    }   
}

function goBookPage(bid)
{
	window.location.href="getBookDetail.action?bid="+bid;
}

function nextPage(maxstr)
{
	var max = parseInt(maxstr);
    if(naviButtonMode>=0)
    {
        var currentLocation = window.location+"";
       	var array=currentLocation.split("p="); 
        var nowPage = parseInt(array[1]);
        nextP = nowPage+1;
        if((max/10+1)<nextP){
        	alert("已经到最后啦~");
        }else{
        	var nextLocation = array[0]+"p="+nextP;
            window.location.href = nextLocation;
        }      
    }
}

function previousPage()
{
    if(naviButtonMode<=0)
    {
        pageMoveTo("ShowRows");
        var currentLocation = window.location+"";
       	var array=currentLocation.split("p="); 
        var nextPage = parseInt(array[1]);
        nextPage --;
        if(nextPage<=0){
        	alert("已经是第一页啦~");
        }else{
        	var nextLocation = array[0]+"p="+nextPage;
            window.location.href = nextLocation;
        }   
    }
}

function changeTips(numstr){
	var num = parseInt(numstr);
	if(num==0){
		textChange("bookTips","暂无相应书目");
	}
}