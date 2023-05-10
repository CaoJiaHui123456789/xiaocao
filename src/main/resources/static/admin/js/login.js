/**
 * $(document).ready(function(){
	var path=getRootPath();
	tiao();
	$("#btlogin").click(function() {
		window.location.href="parkService.html";
});
  function tiao(){}
});
 */
/**
 * 
 */
 $(document).ready(function(){
	$("#btlogin").click(function(){
	var userCode=$("#txt_userCode").textbox("getValue");
	var userPass=$("#txt_userPass").textbox("getValue");
	var path=getRootPath();
	var url=path+"/sysset/queryByUserCode";
	var postData={"userCode":userCode,"userPass":userPass}
	$.post(url,postData,function(data){
		var mess=data.mess;
			if(mess=='true'){
				window.location.href="index.html";
			}else{
				$.messager.alert('提醒',mess,'info');
			}
	})
})
})
