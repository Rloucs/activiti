var projectPath;
var date;
$(function(){
	projectPath=getRootPath();
	changeImage();
});

function getRootPath(){
	var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}
function changeImage(){
	date = new Date().getTime();
	var src=projectPath+"service/getYZM.do?date="+date+"&cacheFlag="+date;
	$("img").attr("src",src);
}
var flag = false;
//登录
function login(){
	
	var userName = $("#userName").val().trim();
	var password = $("#password").val().trim();
	var yzm = $("#yzm").val().trim();
	if(yzm.length != 4){
		alert("验证码不正确！");
		return;
	}else{
		 matchCode(yzm);
	}
	if(flag == false){
		return;
	}
	$.ajax({
		type:"post",
		url:projectPath+"service/login.do",
		dataType:"json",
		data:{
			"userName":userName,
			"password":password
		},
		success:function(res){
			var success = res.success;
			if(success == true){
				window.location.href=projectPath+"page/index.html?cacheFlag="+new Date().getTime();
			}else{
				alert(res.message);
			}
		}
	});
}

function matchCode(code){
	$.ajax({
		async:false,
		type:"post",
		url:projectPath+"/service/matchCode.do",
		dataType:"json",
		data:{
			"date":date,
			"code":code
		},
		success:function(res){
			var success = res.success;
			if(success == true){
				flag = true;
			}else{
				alert(res.message);
				return;
			}
		}
	});
}
