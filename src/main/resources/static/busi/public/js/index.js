/**
 *
 */
$(document).ready(function() {

	$("#maincontext").empty();
	$("#maincontext").load("./indexcontext.html")
	base_url = document.location.href.substring(0, document.location.href.indexOf('../parkService/parkService.html'), 0);
	$(".lightbox-2").lightbox({
		fitToScreen: false
	});

	$("#nav").slide({
		type: "menu",// 效果类型，针对菜单/导航而引入的参数（默认slide）
		titCell: ".nLi", //鼠标触发对象
		targetCell: ".sub", //titCell里面包含的要显示/消失的对象
		effect: "slideDown", //targetCell下拉效果
		delayTime: 300, //效果时间
		triggerTime: 0, //鼠标延迟触发时间（默认150）
		returnDefault: true //鼠标移走后返回默认状态，例如默认频道是“预告片”，鼠标移走后会返回“预告片”（默认false）
	});

	$("#nav li a").click(function(){
		$("#nav li").each(function(){
			$(this).removeClass("active")
		});
		$(this).parent().parent().addClass("active");
		let url=$(this).attr("link");
		$("#maincontext").empty();
		$("#maincontext").load(url)
	});

	function loadContext(url){
		$("#maincontext").empty();
		//div 动画
		$.get(url,function (data){
			//隐藏
			$("#maincontext").html(data);

		});

	}

});





