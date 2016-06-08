function randomBackground()
{
	var bg=Math.floor(Math.random()*6+1);
	var coverbg=document.getElementById("PageCover");
	coverbg.style.backgroundImage=("url(CSS/IMG/BG"+bg+".jpg)");
}

var mottos=new Array()
mottos[0]="读书使人明智——培根";
mottos[1]="读书破万卷 下笔如有神——杜甫";
mottos[2]="书是人类进步的阶梯——高尔基";

function randomMotto()
{
	var mt=Math.floor(Math.random()*3);
	textChange("SecondaryText",mottos[mt]);
}