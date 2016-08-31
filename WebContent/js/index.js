$(function(){
	$("#publish").click(function(){
		name=$.trim(getCookie('username'));
		if(name == null || name == ""){
			$("#publish").attr('href','#');
			$("#signin").show();
			$("#mask").show();
		}else{
			window.close();
			window.open("publish.html");
		}
	});

	$.ajax({
		type :"get",
		url : "loadAll",
		data :{},
		dataType : "json",
		success : function(data){
			var d = eval("{"+data+"}");
			var length = d.length;
			for(var i = 0;i<5;i++){
				for(var j = 0;j<4;j++){
					length = length - 1;
	//				console.log(d[length].id);
					var line = $(".index-line").eq(i);
					var message = line.children().eq(j);
					var img = message.children("img");
					var p = message.children("p");
					var a = message.children("a");
					message.show();
					message.css("display","inline-block");
					img.attr('src',d[length].img);
					p.html("￥"+d[length].price);
					a.html(d[length].name);
	//				a.attr('value',d[length].id);
					if(length == 0){
						return false;
					}
				}
			}
		}
	});

	/*$.ajax({
		type :"get",
		url : "loadAll",
		data :{},
		dataType : "json",
		success : function(data){
			var d = eval("{"+data+"}");
			for (var i = 0; i < d.length; i++) {
				var img = d[i].img;
				var price = d[i].price;
				var name = d[i].name;
			}
		}
	});*/
});
