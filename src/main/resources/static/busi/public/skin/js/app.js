function check(form) {

	if (form.zs_name.value == '') {
		alert("请填写完姓名再进行提交！");
		form.zs_name.focus();
		return false;
	}
	if (form.zs_sex.value == '') {
		alert("请选择性别再进行提交！");
		form.zs_sex.focus();
		return false;
	}
	if (form.zs_phone.value == '') {
		alert("请填写完电话号码再进行提交！");
		return false;
	}
	 if($.trim($("#zs_phone").val())!="")
		 {
		  var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
		  if(!reg.test($.trim($('#zs_phone').val())))
		  {
		   alert("手机号码格式不对！");
		   $("#zs_phone").focus()
		   return false;
		  }
		 }

	if (form.zs_email.value == '') {
		alert("请填写完邮箱再进行提交！");
		form.zs_email.focus();
		return false;
	}
	var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
		 var email_val = $("#zs_email").val();
		 if(!search_str.test(email_val)){       
			 alert("电子邮箱格式不对！");
			 $('#zs_email').focus();
			 return false;
		 }

	if (form.province.value == '') {
		alert("请填写完省份再进行提交！");
		form.province.focus();
		return false;
	}
	if (form.city.value == '') {
		alert("请填写完城市再进行提交！");
		form.city.focus();
		return false;
	}
	if (form.zs_content.value == '') {
		alert("请填写完留言再进行提交！");
		form.zs_content.focus();
		return false;
	}
	return true;
} 