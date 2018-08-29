
$(function(){
	var projectPath;
//	initUrl();
	initDatePicker();
});

function initUrl(){
	var pathName = window.location.pathname;
	// /activiti-service/page/index.html
	var href = window.location.href;
	projectPath = href.substring(0,href.lastIndexOf(pathName)+1);
//	alert(projectPath);
}
function initDatePicker(){
	var inis = laydate.render({
		elem:'#startDate',
		type:'date',
		range:'到',
//		show:true,
		showBottom:true,
		btns: ['clear','confirm'],
		theme:'grid',
		calendar:true,
		mark:{
			'0-8-15':'test'
		},
		/*done:function(value,date){
		    if(date.year === 2018 && date.month === 8 && date.date === 15){ //点击2017年8月15日，弹出提示语
		        inis.hint('中国人民抗日战争胜利72周年');
		      }
		},*/
		format:'yyyy-MM-dd'
	});
}