$(function(){
	$("#login-exit").click(function(){
		window.open("index.html","_parent");
	});
	$(".publish-input input,.publish-input textarea").focus(function(){
		$(this).css("background-color","#fff")
	});
	$(".publish-input input,.publish-input textarea").blur(function(){
		$(this).css("background-color","#E3EEEC")
	});
	$(".myselect").click(function(){
		if($("#select-box ul").css("display") == "none"){
			$("#select-box ul").slideDown("fast");
		}else{$("#select-box ul").slideUp("fast");}
	});
	$("#select-box li").click(function(){
		$("#select-box input").val($(this).text());
		$("#select-box ul").slideUp("fast");
	});
	$("#publish-image-icon").click(function(){
		$("#publish-image").trigger("click");
	});
	$("#publish-image").change(function(){
		var filePath = $("input[name='image']").val();
		var extStart = filePath.lastIndexOf(".");
		var ext = filePath.substring(extStart,filePath.length).toUpperCase();
		if(ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG"){
			alert("图片格式错误");
			return false;
		}else{
			var objUrl = getObjectURL(this.files[0]) ;
			console.log("objUrl = "+objUrl) ;
			if (objUrl) {
				$("#publish-myimage").attr("src", objUrl) ;
			}
			$("#publish-image-icon").hide();
			$("#publish-my-image").show();
		}
	});
	$("#publish-image-exit").click(function(){
		$("#publish-image").trigger("click");
		$("#publish-my-image").hide();
	});


	$("#publish-btn").click(function(){
		$("#publish-form").submit();
	});
});

function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
