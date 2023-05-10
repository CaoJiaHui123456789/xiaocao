$(document).ready(function(){
    var path=getRootPath();
    initStaff();
    $("#btn_search").click(function(){
        $('#tbl_user_detail').datagrid('reload',$("#queryForm").serializeJSON());
    });
    
    $("#txt_deptId").combobox({
		url:path+"/dept/queryAllDept"
});


//删除
    $("#btn_delUser").click(function(){
        var row=$('#tbl_user_detail').datagrid("getSelected");
        if (row==null){
            $.messager.alert('提醒','请选中要删除的数据！','info');
            return false;
        }else{
            $.messager.confirm('确认', '你是否要删除'+row.userName+'?', function(r){
                if (r){
                    var url=path+"/sysset/delUser";
                    var postData={"userId":row.userId};
                    $.post(url,postData,function(data){
                        var mess=data.mess;
                        if (mess=="success"){
                            $.messager.alert('提醒','删除成功！','info');
                            $('#tbl_user_detail').datagrid('reload',$("#queryForm").serializeJSON());
                        }else{
                            $.messager.alert('提醒',mess,'info');
                        }
                    })
                }
            });
        }
    });



//弹出对话框 增加和修改
    $("#btn_addUser").click(function(){
        closDialog();
        $('#dlg_userSave').dialog({title:'增加'})
        $("#dlg_userSave").dialog("open");
    });
    $("#btn_editUser").click(function(){
        var row=$('#tbl_user_detail').datagrid("getSelected");
        if (row==null){
            $.messager.alert('提醒','请选中要修改的数据','info');
            return false;
        }else{
            $('#dlg_userSave').dialog({title:'修改'})
            $("#add_userCode").textbox("setValue",row.userCode);
            $("#add_userName").textbox("setValue",row.userName);
            $("#add_birthday").textbox("setValue",row.birthday);
            $("#add_entryDay").textbox("setValue",row.entryDay);
            $("#add_userPass").textbox("setValue",row.userPass);
            $("#add_userSex").textbox("setValue",row.userSex);
            $("#add_userPhone").textbox("setValue",row.userPhone);
            $("#add_poster").textbox("setValue",row.poster);
            $("#add_address").textbox("setValue",row.address);
            $("#add_userId").val(row.userId);
            $("#dlg_userSave").dialog("open");
        }
    });


    $("#btn_cancelUser").click(function(){
        closDialog();
    });

    function closDialog(){
        $("#add_userCode").textbox("setValue","");
        $("#add_userName").textbox("setValue","");
        $("#add_birthday").textbox("setValue","");
        $("#add_entryDay").textbox("setValue","");
        $("#add_userPass").textbox("setValue","");
        $("#add_userSex").textbox("setValue","");
        $("#add_userPhone").textbox("setValue","");
        $("#add_poster").textbox("setValue","");
        $("#add_address").textbox("setValue","");
        $("#add_userId").val("");
        //大家补充
        $("#dlg_userSave").dialog("close");
    }


    $("#btn_saveUser").click(function(){
        var userCode=$("#add_userCode").textbox("getValue");
        if (userCode==""){
            $.messager.alert('提醒','员工编号不能为空！','info');
            return false;
        }
        var userName=$("#add_userName").textbox("getValue");
        if (userName==""){
            $.messager.alert('提醒','员工姓名不能为空！','info');
            return false;
        }
        var userPass=$("#add_userPass").textbox("getValue");
        if (userPass==""){
            $.messager.alert('提醒','员工密码不能为空！','info');
            return false;
        }
       /* var poster=$("#add_poster").textbox("getValue");
        if (poster==""){
            $.messager.alert('提醒','所属部门不能为空！','info');
            return false;
        }*/
        var userId=$("#add_userId").val();
        var url=""
        if (userId==""){
            url = path+"/sysset/addUser";
        }else{
            url = path+"/sysset/updUser";
        }
        var postData=$("#saveFormPost").serializeJSON();
        console.log(postData);
        $.post(url,postData,function(data){
            var mess=data.mess;
            if (mess=="success"){
                if (userId==""){
                    $.messager.alert('提醒','新增成功！','info',function(){
                        closDialog();
                    });
                }else{
                    $.messager.alert('提醒','修改成功！','info',function(){
                        closDialog();
                    });
                }
                $('#tbl_user_detail').datagrid('reload',$("#queryForm").serializeJSON());
            }else{
                $.messager.alert('提醒',mess,'info');
            }
        })
    });







//部门主页布局
    function initStaff() {
        var url=path+"/sysset/querUser";
        $('#tbl_user_detail').datagrid({
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
                {field:'userCode',title:'员工编号',width:50,halign: 'center'},
                {field:'userName',title:'员工姓名',width:50,halign: 'center'},
                {field:'userPass',title:'员工密码',width:50,halign: 'center'},
                {field:'userSex',title:'性别',width:50,halign: 'center',formatter: function(value,row,index){
					if (row.userSex==true){
						return "男";
					} else {
						return "女";
					}
				}},
                {field:'poster',title:'职务名称',width:50,halign: 'center'},
                {field:'deptName',title:'所属部门',width:50,halign: 'center',formatter: function(value,row,index){
					if (row.dept){
						return row.dept.deptName
					} else {
						return "暂无部门";
					}
				}},
                {field:'birthday',title:'员工生日',width:50,halign: 'center',align:'right'},
                {field:'entryDay',title:'入职时间',width:50,halign: 'center',align:'right'},
                {field:'address',title:'员工住址',width:50,halign: 'center'},
                {field:'userPhone',title:'员工电话',width:50,halign: 'center',align:'right'},
                
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
});