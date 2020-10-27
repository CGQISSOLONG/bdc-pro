// 获取当前时间，星期	

// ------------------左上角时间点：日期 时分秒--------------------------------------------
function getCurDate(){
	var d = new Date();
	// var week;
	// switch (d.getDay()){
	// 	case 1: week="星期一"; break;
	// 	case 2: week="星期二"; break;
	// 	case 3: week="星期三"; break;
	// 	case 4: week="星期四"; break;
	// 	case 5: week="星期五"; break;
	// 	case 6: week="星期六"; break;
	// 	default: week="星期天";
	// }
	var years = d.getFullYear();
	var month = add_zero(d.getMonth()+1);
	var days = add_zero(d.getDate());
	var hours = add_zero(d.getHours());
	var minutes = add_zero(d.getMinutes());
	var seconds=add_zero(d.getSeconds());
	var ndate = years+"-"+month+"-"+days+"  "+ hours+":"+minutes+":"+seconds+"  ";
	// var ndate = years+"年"+month+"月"+days+"日 " + week +" "+ hours+":"+minutes+":"+seconds+"  ";
	var divT=document.getElementById("monitorTime");
	divT.innerHTML= ndate;
}
function add_zero(temp)
{
	if(temp<10) return "0"+temp;
	else return temp;
}
//setInterval("getCurDate()",1000);


// ------------------时钟时间点：时分--------------------------------------------
// 获取当前时间，星期	
function getCurDate3(){
	var d3 = new Date();	
	var week3;
	switch (d3.getDay()){
		case 1: week3="星期一"; break;
		case 2: week3="星期二"; break;
		case 3: week3="星期三"; break;
		case 4: week3="星期四"; break;
		case 5: week3="星期五"; break;
		case 6: week3="星期六"; break;
		default: week3="星期天";
	}
	var years3 = d3.getFullYear();
	var month3 = add_zero(d3.getMonth()+1);
	var days3 = add_zero(d3.getDate());
	var hours3 = add_zero(d3.getHours());
	var minutes3 = add_zero(d3.getMinutes());
	var seconds3=add_zero(d3.getSeconds());
	var ndate3 = years3+"-"+month3+"-"+days3+" " + week3;
	var divT3=document.getElementById("monitorTime3");
	divT3.innerHTML= ndate3;
}
function add_zero(temp)
{
	if(temp<10) return "0"+temp;
	else return temp;
}
//setInterval("getCurDate3()",1000);


// ------------------时钟时间点：几年几月几号 星期几--------------------------------------------
// 获取当前时间，星期	
function getCurDate2(){
	var d2 = new Date();
	var years2 = d2.getFullYear();
	var month2 = add_zero(d2.getMonth()+1);
	var days2 = add_zero(d2.getDate());
	var hours2 = add_zero(d2.getHours());
	var minutes2 = add_zero(d2.getMinutes());
	var seconds2=add_zero(d2.getSeconds());
	var ndate2 = hours2+":"+minutes2;
	// var ndate = years+"年"+month+"月"+days+"日 " + week +" "+ hours+":"+minutes+":"+seconds+"  ";
	var divT2=document.getElementById("monitorTime2");
	divT2.innerHTML= ndate2;
}
function add_zero(temp)
{
	if(temp<10) return "0"+temp;
	else return temp;
}
//setInterval("getCurDate2()",1000);