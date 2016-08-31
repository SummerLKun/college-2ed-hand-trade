$(function(){
	$("#js-signin-btn,#login-btn").click(function(){
		$("#signin").show();
		$("#mask").show();
	});
	$("#js-signup-btn,#regist").click(function(){
		$("#signup").show();
		$("#mask").show();
		$("#regist-main").show();
		$("#regist-success").hide();
	});
	$("#mask,.close-ico").click(function(){
		$("#signin").hide();
		$("#signup").hide();
		$("#mask").hide();
		$("#regist-main").show();
		$("#regist-success").hide();

		loginReset();
		registReset();
	});
	$("#login-btn").click(function(){
		$("#signup").hide();
		registReset();
	});
	$("#regist").click(function(){
		$("#signin").hide();
		loginReset();
	});
	$("#login-exit").click(function(){
		$("#login-area").show();
		$("#login-success-area").hide();
		setCookie('username',null,0);
		loginReset();
	//清空setion
	});
	loginCookie();
});

function loginCookie(){
	name =$.trim(getCookie('username'));
	if(name == null || name == ""){
		$("#login-success-area").hide();
		$("#login-area").show();
	}else{
		$("#login-success-area").show();
		$("#login-area").hide();
		$("#login-username").html(name);
	}
}

function setCookie(c_name,value)//设置cookie
{
	//var exdate=new Date()
	//exdate.setTime(exdate.getTime()+expiredays*1000)
	//document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	document.cookie=c_name+ "=" +escape(value);
}
function getCookie(c_name)//获取cookie
{
	if (document.cookie.length>0)
	{
		c_start=document.cookie.indexOf(c_name + "=")
		if (c_start!=-1)
		{
			c_start=c_start + c_name.length+1
			c_end=document.cookie.indexOf(";",c_start)
			if (c_end==-1) c_end=document.cookie.length
				return unescape(document.cookie.substring(c_start,c_end))
		}
	}
	return ""
}

function loginReset(){
	$("#username").val("");
	$("#password").val("");
	$("#username-tips").html("");
	$("#password-tips").html("");
	$("#signin-tips").html("");
}

function registReset(){
	$("#regist-username").val("");
	$("#regist-password").val("");
	$("#regist-password2").val("");
	$("#regist-number").val("");
	$("#regist-tel").val("");
	$("#regist-mail").val("");
	$("#regist-username-tips").html("");
	$("#regist-password-tips").html("");
	$("#regist-password2-tips").html("");
	$("#regist-number-tips").html("");
	$("#regist-tel-tips").html("");
	$("#regist-mail-tips").html("");
}
