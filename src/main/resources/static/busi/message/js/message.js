$(function(){
		$('#tj').click(function(){
			//alert(1)
			if($('#name').val()==''){alert('请输入您的姓名！'); $("#name").focus(); return false;}
			if ($("#tel").val() == "") { alert("请输入你的手机！"); $("#tel").focus(); return false; } 
			if (!$("#tel").val().match(/^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(19[0-9]{1}))+\d{8})$/)) { alert("手机号码格式不正确！"); $("#tel").focus(); return false;} 
		})
	})
	
	
	//
	   function loadwow() {
			var wow = new WOW({
		    boxClass: 'wow',
		    animateClass: 'animated',
		    offset:0,//100
		    mobile: true,
		    live: true
		});
		wow.init();
		}