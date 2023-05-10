/**
 *
 */
function numberInputPlaceholder() {
    $(".easyui-numberbox").each(function (i) {
        var span=$(this).siblings("span")[0];
        var targetInput = $(span).find("input:first");
        if(targetInput){
            $(targetInput).attr("placeholder",$(this).attr("placeholder"));
        }
    });
}
$(document).ready(function () {
    $(function (){window.onload=numberInputPlaceholder()});
    var path = getRootPath();
    initEntry();
    // $("#txt_parkId").combobox({
    //     url: path + '/cutomer/getAllPark'
    // });
    $("#btn_search").click(function () {
        $('#tbl_entry_detail').datagrid('reload', $("#queryForm").serializeJSON());
    });


    //下拉窗
    /**   var deptlist;
     $.getJSON(path+"/depset/queryAllDept",function(data){    //获得部门列表
	deptlist=data;      //将数据赋值给deptlist
    });
     function getDepart(depId){
	console.log(deptlist);
	return deptlist.rows.filter(function(row) {       //查找id符合要求的json对象
	  if(row.deptId == depId){          //判断json对象的部门id是否等于传入的部门id
		return true;
	  }else{
		return false;
	  }
	});

    }  */

    $("#btn_delEntry").click(function () {
        var row = $('#tbl_entry_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要删除的数据！', 'info');
            return false;
        } else {
            $.messager.confirm('确认', '你是否要删除' + row.entryName + '?', function (r) {
                if (r) {
                    var url = path + "/cutomer/delEntryTable";
                    var postData = {"entryId": row.entryId};
                    $.post(url, postData, function (data) {
                        var mess = data.mess;
                        if (mess == "success") {
                            $.messager.alert('提醒', '删除成功！', 'info');
                            $('#tbl_entry_detail').datagrid('reload', $("#queryForm").serializeJSON());
                        } else {
                            $.messager.alert('提醒', mess, 'info');
                        }
                    })
                }
            });
        }
    });

    //弹出对话框
    $("#btn_addEntry").click(function () {
        $('#dlg_entrySave').dialog({title: '增加'})
        $("#dlg_entrySave").dialog("open");
    });
    $("#btn_editEntry").click(function () {
        var row = $('#tbl_entry_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要修改的数据', 'info');
            return false;
        } else {
            $('#dlg_entrySave').dialog({title: '修改'})


            $("#add_companyName").textbox("setValue", row.companyName); //企业名称
            $("#add_businessAct").textbox("setValue", row.businessAct); //经营活动


            //入驻位置
            $("#add_entryLocation1").combobox("setValue", row.industryClass);
            $("#add_entryLocation2").combobox("setValue", row.industryClass);
            $("#add_entryLocation3").combobox("setValue", row.industryClass);

            $("#add_industryClass").combobox("setValue", row.industryClass); //行业分类
            $("#add_registerClass").combobox("setValue", row.registerClass); //登记注册


            //国家级高级技术
            if(row.countryhighTech){
                $("#rdo_countryhighTech_1").radiobutton("check");
                $("#rdo_countryhighTech_0").radiobutton("uncheck");
            }else {
                $("#rdo_countryhighTech_0").radiobutton("check");
                $("#rdo_countryhighTech_1").radiobutton("uncheck");
            }

            //是否上市
            if(row.listingSituation){
                $("#rdo_listingSituation_1").radiobutton("check");
                $("#rdo_listingSituation_0").radiobutton("uncheck");
            }else {
                $("#rdo_countryhighTech_0").radiobutton("check");
                $("#rdo_countryhighTech_1").radiobutton("uncheck");
            }

            $("#add_legalPerson").textbox("setValue",row.legalPerson);
            $("#add_practioner").textbox("setValue",row.practioner);

            $("#add_entryId").val(row.entryId);
            $("#dlg_entrySave").dialog("open");
        }
    });


    $("#btn_cancelEntry").click(function () {
        closDialog();
    });
    $("#add_entryLocation1").combobox('reload', path + '/busi/queryAllPark');
    $("#add_entryLocation1").combobox({
        onChange: function (newValue, oldValue) {
            let parkid = newValue;
            console.log(parkid);
            console.log($("#add_entryLocation1").combobox('getText'));
            $("#add_entryLocation2").combobox('reload', path + '/busi/queryBuilding?parkId=' + parkid);
        }
    });
    $("#add_entryLocation2").combobox({
        onChange: function (newValue, oldValue) {
            let buildingid = newValue;
            console.log(buildingid);
            console.log($("#add_entryLocation2").combobox('getText'));
            $("#add_entryLocation3").combobox('reload', path + '/busi/queryHouse?buildingId=' + buildingid);
        }
    });

    function closDialog() {
        // $("#add_entryName").textbox("setValue", "");
        // $("#add_entryImg").textbox("setValue", "");
        // $("#add_floorNumber").textbox("setValue", "");
        // $("#add_houseNumber").textbox("setValue", "");
        // $("#add_entryArea").textbox("setValue", "");
        // $("#add_publicArea").textbox("setValue", "");
        // $("#add_contractArea").textbox("setValue", "");
        // $("#add_avgArea").textbox("setValue", "");
        // $("#add_cusNumber").textbox("setValue", "");
        // $("#add_rentArea").textbox("setValue", "");
        $("#add_entryId").val("");
        // //大家补充
        $("#dlg_entrySave").dialog("close");
    }


    $("#btn_saveEntry").click(function () {
        var entryName = $("#add_entryName").textbox("getValue");
        if (entryName == "") {
            $.messager.alert('提醒', '楼宇名称不能为空！', 'info', function () {
                $("#add_entryName + span input:first-child").focus();
            });
            return false;
        }
        //留着有用
        // var parkId = $("#txt_parkId").combobox("getValue");
        // if (parkId == "") {
        // 	$.messager.alert('提醒', '所属园区不能为空！', 'info');
        // 	return false;
        // }
        var entryArea = $("#add_entryArea").textbox("getValue");
        if (entryArea == "") {
            $.messager.alert('提醒', '建筑面积不能为空！', 'info', function () {
                $("#add_entryArea + span input:first-child").focus();
            });
            return false;
        }

        var entryId = $("#add_entryId").val();
        var url = ""
        if (entryId == "") {
            url = path + "/cutomer/addEntryTable";
        } else {
            url = path + "/cutomer/updEntryTable";
        }
        var postData = $("#saveFormPost").serializeJSON();
        console.log(postData);
        $.post(url, postData, function (data) {
            var mess = data.mess;
            if (mess == "success") {
                if (entryId == "") {
                    $.messager.alert('提醒', '新增成功！', 'info', function () {
                        closDialog();
                    });
                } else {
                    $.messager.alert('提醒', '修改成功！', 'info', function () {
                        closDialog();
                    });
                }
                $('#tbl_entry_detail').datagrid('reload', $("#queryForm").serializeJSON());
            } else {
                $.messager.alert('提醒', mess, 'info');
            }
        })
    });


    function initEntry() {
        var url = path + "/customer/querEntryTable";
        $('#tbl_entry_detail').datagrid({
            border: false,
            singleSelect: true,
            fit: true,
            fitColumns: true,
            rownumbers: true,
            autoRowHeight: false,
            nowrap: true,
            loadMsg: "正在加载，请稍后...",
            striped: true,
            url: url,
            queryParams: $("#queryForm").serializeJSON(),
            pagination: true,
            pageSize: 10,
            pageList: [5, 10, 20, 30],
            columns: [[
                {field: 'companyName', title: '企业名称', width: 80, halign: 'center'},
                {field: 'entryLocation', title: '入驻位置', width: 80, halign: 'center'},
                {field: 'businessAct', title: '经营活动', width: 80, halign: 'center'},
                {field: 'investmentSource', title: '投资来源', width: 80, halign: 'center'},
                {field: 'industryClass', title: '行业分类', width: 80, halign: 'center'},
                {field: 'registerClass', title: '登记注册', width: 80, halign: 'center'},
                {
                    field: 'countryhighTech', title: '高新技术', width: 80, halign: 'center',
                    formatter: function (value, row, index) {
                        if (row.countryhighTech) {
                            return '是'
                        } else {
                            return '否'
                        }
                    }
                },
                {
                    field: 'listingSituation', title: '上市情况', width: 80, halign: 'center',
                    formatter: function (value, row, index) {
                        if (row.countryhighTech) {
                            return '是'
                        } else {
                            return '否'
                        }
                    }
                },
                {field: 'buildFactories', title: '购地建厂', width: 80, halign: 'center'},
                {field: 'practioner', title: '从业人员', width: 80, halign: 'center'},
                {field: 'teamComposition', title: '创业者团队构成', width: 80, halign: 'center'},
                {field: 'incomeResearch', title: '收入级研发', width: 80, halign: 'center'},
                {field: 'legalPerson', title: '法人', width: 50, halign: 'center'},
                {field: 'fixContact', title: '固定联系人', width: 80, halign: 'center'},

            ]],
            onBeforeLoad: function (param) {
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