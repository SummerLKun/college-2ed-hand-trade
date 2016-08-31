$(function(){
	$("#signup-btn").click(function(){
		var username = $.trim($("#regist-username").val());
		var password = $.trim($("#regist-password").val());
		var password2 = $.trim($("#regist-password2").val());
		var sno = $.trim($("#regist-number").val());
		var tel = $.trim($("#regist-tel").val());
		var mail = $.trim($("#regist-mail").val());
		var regSno = /^\d{10}$/;
		var regMail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		var regTel =/^1[3|4|5|7|8]\d{9}$/;
		if(username == ""){
			$("#regist-username-tips").html("请输入正确的用户名");
			return false;
		}else{$("#regist-username-tips").html("");}
		if(password.length < 6 || password.length > 16){
			$("#regist-password-tips").html("请输入正确格式的密码");
			return false;
		}else{$("#regist-password-tips").html("");}
		if(password != password2){
			$("#regist-password2-tips").html("两次输入密码不一致");
			return false;
		}else{$("#regist-password2-tips").html("");}
		if(!regSno.test(sno)){
			$("#regist-number-tips").html("学号格式错误");
			return false;
		}else{$("#regist-number-tips").html("");}
		if(!regTel.test(tel)){
			$("#regist-tel-tips").html("手机号格式错误");
			return false;
		}else{$("#regist-tel-tips").html("");}
		if(!regMail.test(mail)){
			$("#regist-mail-tips").html("邮箱格式错误");
			return false;
		}else{$("#regist-mail-tips").html("");}
		$.ajax({
			type : "get",
			url : "regist",
			data : {
				sno : sno
			},
			dataType : "json",
			success : function(data) {
				var d = eval("(" + data + ")");
				if (d.flag) {
					$.ajax({
						type :"post",
						url : "registSuccess",
						data : {
							userid : sno,
							username : username,
							password : password,
							tel : tel,
							email : mail
						},
						dataType : "json",
						success : function(data2){
							var a = eval("(" + data2 + ")");
							if (a.flag) {
								alert("aaa");
								$("#regist-main").hide();
								$("#regist-success").show();
							}else {
								alert("该学号已存在，不可以使用");
							}
						}
					});

				} else {
					$("#regist-number-tips").html("该学号已存在，不可以使用");
				}

			}
		});

	});
});
