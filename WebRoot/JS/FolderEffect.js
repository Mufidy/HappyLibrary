function simpleFloder(divID)
{
	$("#"+divID).slideToggle("slow");
}

function doubleFloder(mainDivID,viceDivID)
{
	$("#"+mainDivID).slideToggle("slow",function(){
		$("#"+viceDivID).slideToggle("slow");
	});
}

function innerFloder(mainDivID,innnerDivID)
{
	$("#"+mainDivID).slideToggle("slow");
	$("#"+innnerDivID).slideToggle("slow");
}