/**
 * 
 */
 $(document).ready(function() {
	var path=getRootPath();
	initStaff();
	$("#txt_parkId").combobox({
		url:path+'/sysset/getAllPark'
	});
	
	
	$("#add_building").combobox({
		url:path+'/sysset/queryAllBuilding'
	});
	
	
	$("#btn_saveAptDiagram").click(function(){
		$('#dlg_aptDiagramSave').dialog({title:'增加'})
		$("#dlg_aptDiagramSave").dialog("open");
	});
	
	$("#btn_editAd").click(function(){
		var row=$('#tbl_Ad_detail').datagrid('getSelected');
		$('#dlg_AdSave').dialog({title:'修改'})
		$("#dlg_AdSave").dialog("open");		
		$("#add_AdName").textbox("setValue",row.AdName);
		$("#add_AdPro").textbox("setValue",row.AdPro);
		$("#add_AdCity").textbox("setValue",row.AdCity);
		$("#add_AdDis").textbox("setValue",row.AdDis);
		$("#add_AdAddress").textbox("setValue",row.AdAddress);
		$("#add_AdAdmin").textbox("setValue",row.AdAdmin);
		$("#add_AdArea").textbox("setValue",row.AdArea);
		$("#add_AdId").textbox("setValue",row.AdId);
	});
	
	//$.post(url,postData,function(data){
	$("#btn_search").click(function(){
		$('#tbl_aptDiagram_detail').datagrid('reload',$("#queryForm").serializeJSON());
	})
	
	$("#btn_firmAptDiagram").click(function(){		
		var form = $("#form_importphoto")[0];
		var file = $(form).find('input[type=file]')[0].files[0];
		
		var area=$("#add_area").textbox("getValue");
		if(area==''){
			$.messager.alert('提醒','户型面积不能为空!','info',function(){
                 $("#add_area + span input:first-child").focus();
            });
			return false;
		}
		
		var pattern=$("#add_pattern").textbox("getValue");
		if(pattern==''){
			$.messager.alert('提醒','户型格局不能为空!','info',function(){
                 $("#add_pattern + span input:first-child").focus();
            });
			return false;
		}
		
		var toward=$("#add_toward").textbox("getValue");
		if(toward==''){
			$.messager.alert('提醒','户型朝向不能为空!','info',function(){
                 $("#add_toward + span input:first-child").focus();
            });
			return false;
		}
		
		var parkId=$("#txt_parkId").textbox("getValue");
		if(parkId==''){
			$.messager.alert('提醒','所属不能为空!','info',function(){
                 $("#txt_parkId + span input:first-child").focus();
            });
			return false;
		}
		
		var toward=$("#add_toward").textbox("getValue");
		if(toward==''){
			$.messager.alert('提醒','户型朝向不能为空!','info',function(){
                 $("#add_toward + span input:first-child").focus();
            });
			return false;
		}
		
		
		
		
		if (file == null) {
			$.messager.alert("错误", "请先选择一个文件！", "error");
		}
		var fileName = file.name;
		var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
		if (file_typename.toLowerCase() == '.jpg') {
			var postData=$("#form_importphoto").serializeJSON();
			console.log(postData);
			phoFileUpload(form, "/aptDiagram/addDiagram",postData);
		} else {
			$.messager.alert("错误", "请选择一个.jpg格式的文件！", "error");
		}
	   //新增或修改
	

	
	});
	
	//删除
	$("#btn_delAptDiagram").click(function(){

		var row=$('#tbl_aptDiagram_detail').datagrid('getSelected');
		if(row.length===0){
			$.messager.alert('提醒','请选中要删除的数据！','info');
			return false;
		}else{
			$.messager.confirm('确认','你是否要删除'+row.adCode+'户型',function(r){
				if(r){
					var url=path+"/aptDiagram/delSysAd";
					var postData={"adId":row.adId}
					$.post(url,postData,function(data){
						var mess=data.mess;
						if(mess=='success'){
							$.messager.alert('提醒','删除成功！','info');
							initStaff();
						}else{
							$.messager.alert('提醒',mess,'info');
						}
					})
			}
		});
		}
	})
	
	//新增时取消
	$("#btn_cancelAptDiagram").click(function(){
		closDialog();
	});
	
	
	$("#btn_search").click(function() {
		$('#tbl_staff_detail').datagrid('reload',$("#queryForm").serializeJSON());
	});
	
	function initStaff() {
		var url=path+"/aptDiagram/queryAptDiagram";
		$('#tbl_aptDiagram_detail').datagrid({
			border:false,
			singleSelect:true,
			fit:true,
			fitColumns:true,
			rownumbers:true,
			autoRowHeight:false,
			nowrap:true,
			loadMsg:"正在加载，请稍后...",
			striped:true,
			url: url,
			queryParams:$("#queryForm").serializeJSON(),
			pagination:true,
			pageSize:10,
			pageList:[5,10,20,30],
			columns: [[
				{field:'area',title:'户型面积',width:50,halign:'center',align:'right'},
				{field:'img',title:'户型图路径',width:100,halign:'center'},
				{field:'pattern',title:'格局',width:120,halign:'center'},
				{field:'toward',title:'朝向',width:80,halign:'center'},	
				{field:'adCode',title:'户型编号',width:80,halign:'center'},	
		    ]],
		    onBeforeLoad:function(param){
		    	var pageNo = param.page; 
				delete param.page; 
				param.pageNo = pageNo; 
				var pageSize = param.rows;
				delete param.rows; 
				param.pageSize = pageSize; 
		    },
		   
		});
	}
	
	function closDialog(){
		$("#add_area").textbox("setValue","");
		$("#add_adId").textbox("setValue","");
		$("#add_pattern").textbox("setValue","");
		$("#add_toward").textbox("setValue","");
		$("#txt_parkId").textbox("setValue","");
		$("#add_building").textbox("setValue","");
		$("#add_adCode").textbox("setValue","");
		$("#dlg_aptDiagramSave").dialog("close");
	}
		
	function phoFileUpload(form, uploadPath,postData) {
		var formData = new FormData(form);
		formData.append("postData", postData);
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
				if (null != e.error) {
					$.messager.alert("错误", e.error, "error");

				} else {
					$.messager.alert("提示", e.mess, "info", function() {
						$("#dlg_AdSave").dialog("close");
						//initTable();
						location.reload();
					});
				}
			},
			error: function(e) {
				$.messager.progress("close");
				$.messager.alert("错误", "未知错误！", "error");
			}
		});
	}
	
	

	
	
});