$(document).ready(function(){	
	var path=getRootPath();
	iniImg(0);
	iniImg(1);
	iniImg(2);
	iniImg(3);
	iniImg(4);
	
	
	$("#savePopup").click(function(){
		//数据验证
		var indexSwiperName=$("#add_indexSwiperName").textbox("getValue");
	
		if(indexSwiperName==""){
			$.messager.alert('提醒','名称不能为空!','info');
			return false;
		}
		var form = $("#form_importphoto")[0];
		var file = $(form).find('input[type=file]')[0].files[0];
		if (file == null) {
			$.messager.alert("错误", "请先选择一个文件！", "error");
		}
		var fileName = file.name;
		var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
		if (file_typename.toLowerCase() == '.jpg') {
			phoFileUpload(form, "/indexSwiper/addIndexSwiper");
			
		} else {
			$.messager.alert("错误", "请选择一个.jpg格式的文件！", "error");
		}
	})
	
	$("#add_btn").click(function(){
		$("#dlg_indexSwiperSave").dialog("open");
	})
	
	$("#closePopup").click(function(){
		$("#dlg_indexSwiperSave").dialog("close");
	})
	
	
	$("#del_btn").click(function(){
		
		var arr=new Array(0);
		for(var i=0;i<=4;i++){
			var selector="#check"+i;
			if($(selector).val()==1){
				var idSelector="#indexSwiperId"+i;
				var id=$(idSelector).val();
				arr.push(id);
				console.log(arr);
			}
		}
		del(arr);
	})

	
function iniImg(i){
	console.log(path);
	$.ajax({
		url:path+"/indexSwiper/iniImg",
		type: "post",
		async: true,
		data:{
			index:i
		},
		cache: false,
		success:function(data){
			var idUrl="#indexSwiperId"+i;
			var arr=data.split("!");
			var id=arr[0];	
			var base64=arr[1];	
		    $(idUrl).attr("value",id);       	
			var space="#showimg"+i
			$(space).attr("src",base64);
		},
		error: function(e) {
				//$.messager.alert("错误", "未知错误！", "error");
			}		
	})
}

function del(arr){
	var data = { typeIDArray: arr };  
	$.ajax({
		url:path+"/indexSwiper/delImg",
		type: "post",
		async: true,
		
		traditional: true,
		data:data,
		success:function(e){
			$.messager.alert("提示", e.mess, "info");
			location.reload();
		}
		
	})
}
	
	
	


function phoFileUpload(form, uploadPath) {
		var formData = new FormData(form);	
		console.log(formData);
		$.ajax({
			url: path + uploadPath,
			type: "post",
			async: true,
			cache: false,
			data: formData,
			processData: false,
			contentType: false,
			beforeSend: function() {
				$.messager.progress({
				text: "上传中..."
					});
			},
			success: function(e) {
				$.messager.progress("close");		
				$.messager.alert("提示", e.mess, "info", function() {
					$("#dlg_indexSwiperSave").dialog("close");
						//initTable();
						location.reload();
					});
				
			},
			error: function(e) {
				$.messager.progress("close");
				$.messager.alert("错误", "未知错误！", "error");
			}
			
		});
		return false;
	}


//效果控制
$("#border0").mouseover(function(){
	$("#border0").css("background-color","white");
	$("#border0").click(function(){
		if($("#check0").val()==1){
			$("#check0").val("0");
		}else if($("#check0").val()==0){
			$("#check0").val("1");
		}
	})
})
	
$("#border1").mouseover(function(){
	$("#border1").css("background-color","white");
	$("#border1").click(function(){
		if($("#check1").val()==1){
			$("#check1").val("0");
		}else if($("#check1").val()==0){
			$("#check1").val("1");
		}
	})
})
$("#border2").mouseover(function(){
	$("#border2").css("background-color","white");
	$("#border2").click(function(){
		if($("#check2").val()==1){
			$("#check2").val("0");
		}else if($("#check2").val()==0){
			$("#check2").val("1");
		}
	})
})
$("#border3").mouseover(function(){
	$("#border3").css("background-color","white");
	$("#border3").click(function(){
		if($("#check3").val()==1){
			$("#check3").val("0");
		}else if($("#check3").val()==0){
			$("#check3").val("1");
		}
	})
})
$("#border4").mouseover(function(){
	$("#border4").css("background-color","white");
	$("#border4").click(function(){
		if($("#check4").val()==1){
			$("#check4").val("0");
		}else if($("#check4").val()==0){
			$("#check4").val("1");
		}
	})
})






$("#border0").mouseout(function(){
	var num=$("#check0").val();
	if(num==0){
		$("#border0").css("background-color","black")
	}
})
$("#border1").mouseout(function(){
	var num=$("#check1").val();
	if(num==0){
		$("#border1").css("background-color","black")
	}
})
$("#border2").mouseout(function(){
	var num=$("#check2").val();
	if(num==0){
		$("#border2").css("background-color","black")
	}
})
$("#border3").mouseout(function(){
	var num=$("#check3").val();
	if(num==0){
		$("#border3").css("background-color","black")
	}
})
$("#border4").mouseout(function(){
	var num=$("#check4").val();
	if(num==0){
		$("#border4").css("background-color","black")
	}
})


})


