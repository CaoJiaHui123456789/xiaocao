	/**
 *
 */
var path = getRootPath();
var editor;

$(document).ready(function() {
	
	
	//创建富文本编辑器
	var options = {
		    		uploadJson: path+'/newsCont/addNewsPicture',
   		   		    allowFileManager: false
				};
	console.log(options)
        editor = KindEditor.create('textarea[name="content"]',options);
        
        
	 	
	initCont();
	$("#add_newsTable").combobox({
		url:path+'/sysset/queryAllNewsTable'
	});
	
	
	$("#btn_search").click(function() {
		$('#tbl_Cont_detail').datagrid('reload',$("#queryForm").serializeJSON());
	});
	
	
	$("#btn_delCont").click(function(){
		var row=$('#tbl_Cont_detail').datagrid("getSelected");
		if (row==null){
			$.messager.alert('提醒','请选中要删除的数据！','info');
			return false;
		}else{
			$.messager.confirm('确认', '你是否要删除'+row.newsName+'?', function(r){
				if (r){
					var url=path+"/newsCont/delNewsCont";
					var postData={"newcontId":row.newcontId};
					$.post(url,postData,function(data){
						var mess=data.mess;
						if (mess=="success"){
							$.messager.alert('提醒','删除成功！','info');
							$('#tbl_Cont_detail').datagrid('reload',$("#queryForm").serializeJSON());
						}else{
							$.messager.alert('提醒',mess,'info');
						}
					})
				}
			});
		}
	});
	
	//弹出对话框
	$("#btn_addCont").click(function(){
		$('#dlg_ContSave').dialog({title:'增加'})
		$("#dlg_ContSave").dialog("open");
		$(document).off('focusin.modal');	
			
	});
	
	
	//预览
	
	$("btn_preview").click(function(){
		var row=$('#tbl_Cont_detail').datagrid("getSelected");
		if (row==null){
			$.messager.alert('提醒','请选中要修改的数据','info');
			return false;
		}else{
			url=path+""
			$.post(url,postData,function(){
				
			})
			
		}
		
	})
	
	
	
	//修改
	$("#btn_editCont").click(function(){
		var row=$('#tbl_Cont_detail').datagrid("getSelected");
		if (row==null){
			$.messager.alert('提醒','请选中要修改的数据','info');
			return false;
		}else{
			$('#dlg_ContSave').dialog({title:'修改'})
			$("#add_newsName").textbox("setValue",row.newsName);
			$("#add_subtitleName").textbox("setValue",row.subtitleName);
			$("#add_source").textbox("setValue",row.source);
			$("#add_newsTable").combobox("setValue",row.newsTable);
			$("#add_content").textbox("setValue",row.content);
		
			//$("#add_recommend").switchbutton({});	
			$("#add_recommend").switchbutton("setValue","on");			
			$("#add_newcontId").val(row.newcontId);
			$("#dlg_ContSave").dialog("open");
			editor.html(row.content);
		}
	});
	
	$("#btn_cancelCont").click(function(){
		closDialog();
	});

	function closDialog(){
			$("#add_newsName").textbox("setValue","");
			$("#add_subtitleName").textbox("setValue","");
			$("#add_source").textbox("setValue","");
			$("#add_newsTable").textbox("setValue","");
			editor.html('');
			$("#add_content").textbox("setValue","");
			$("#add_recommend").textbox("setValue","");
			$("#add_newcontId").val("");		
			$("#dlg_ContSave").dialog("close");
	}


	$("#btn_saveCont").click(function(){
		var newsName=$("#add_newsName").textbox("getValue");
		if (newsName==""){
			$.messager.alert('提醒','新闻标题不能为空！','info');
			return false;
		}
		
		var newsTable=$("#add_newsTable").textbox("getValue");
		if (newsTable==""){
			$.messager.alert('提醒','新闻模块不能为空！','info');
			return false;
		}
		
		var source=$("#add_source").textbox("getValue");
		if (source==""){
			$.messager.alert('提醒','新闻来源不能为空！','info');
			return false;
		}
		
		editor.sync();
		html = $('#editor_id').val()
		
		var newcontId=$("#add_newcontId").val();
		var url=""
		if (newcontId==""){
			url = path+"/newsCont/addNewsCont";
		}else{
			url = path+"/newsCont/updNewsCont";
		}
		/*var postData=$("#saveFormPost").serializeJSON();
		var postData;
		postData={
			"newsName":newsName,
			"content":html,
			"source":source,
			"newsId":newsTable
			}
		*/
		var form = $("#form_importphoto")[0];
		var file = $(form).find('input[type=file]')[0].files[0];
		if (file == null) {
			$.messager.alert("错误", "请先选择一个文件！", "error");
		}
		var fileName = file.name;
		var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
		if (file_typename.toLowerCase() == '.jpg') {
			phoFileUpload(form,url,html);
		} else {
			$.messager.alert("错误", "请选择一个.jpg格式的文件！", "error");
		}
	});


	function initCont() {
		var url=path+"/newsCont/querNewsCont";
		$('#tbl_Cont_detail').datagrid({
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
				{field:'newsName',title:'新闻标题',width:180,halign: 'center'},
				{field:'subtitleName',title:'副标题',width:60,halign: 'center'},
				{field:'source',title:'新闻来源',width:50,halign: 'center'},
				{field:'dateDay',title:'发布时间',width:50,halign: 'center'},
				{field:'author',title:'新闻作者',width:40,halign: 'center'},
				{field:'recommend',title:'是否推荐',width:30,halign: 'center',formatter: function(value,row,index){
					if (row.recommend){
						return "是";
					} else {
						return "否";
					}	      
				}},				
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
	
	
	
	
	function phoFileUpload(form, url, html) {
		var formData = new FormData(form);
		formData.append("content", html);	
		$.ajax({
			url: url,
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


    