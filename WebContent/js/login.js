$(function(){
	$("#signin-btn").click(function(){
		var username = $.trim($("#username").val());
		var password = $.trim($("#password").val());
		if(username == ""){
			$("#username-tips").html("请输入正确的用户名");
			return false;
		}else{$("#username-tips").html("");}
		if(password == ""){
			$("#password-tips").html("请输入6-16位密码，区分大小写，不能使用空格！");
			return false;
		}else{$("#password-tips").html("");}
		if(username != "" && password != ""){
			$.ajax({
				type :"post",
				url : "login",
				data :{
					username : username,
					password : password
				},
				dataType : "json",
				success : function(data){
					var d = eval("("+data+")");
					if(d.flag=="1"){
						var name =$.trim(d.username);
						setCookie('username',name);
						$("#login-username").html(name);
						//$("#login-username").html(username);
						$("#login-area").hide();
						$("#login-success-area").show();
						$("#signin").hide();
						$("#mask").hide();
					}else{
						$("#signin-tips").html("登录失败，用户名或密码错误");
					}
				},
				error : function(){
					$("#signin-tips").html("登录失败");
				}
			});
		}
	});
});
