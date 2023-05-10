/**
 *
 */
$(document).ready(function () {
    var path = getRootPath();
    goDept();
    $("#btn_search").click(function () {
        $('#tbl_dept_detail').datagrid('reload', $("#queryForm").serializeJSON());
    });


    $("#btn_delDept").click(function () {
        var row = $('#tbl_dept_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要删除的数据！', 'info');
            return false;
        } else {
            $.messager.confirm('确认', '你是否要删除' + row.deptName + '?', function (r) {
                if (r) {
                    var url = path + "/dept/delDept";
                    var postData = {"deptId": row.deptId};
                    $.post(url, postData, function (data) {
                        var mess = data.mess;
                        if (mess == "success") {
                            $.messager.alert('提醒', '删除成功！', 'info');
                            $('#tbl_dept_detail').datagrid('reload', $("#queryForm").serializeJSON());
                        } else {
                            $.messager.alert('提醒', mess, 'info');
                        }
                    })
                }
            });
        }
    });

    //弹出对话框
    $("#btn_addDept").click(function () {
        $('#dlg_deptSave').dialog({title: '增加'})
        $("#dlg_deptSave").dialog("open");
    });
    $("#btn_editDept").click(function () {
        var row = $('#tbl_dept_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要修改的数据', 'info');
            return false;
        } else {
            $('#dlg_deptSave').dialog({title: '修改'})
            $("#add_deptCode").textbox("setValue", row.deptCode);
            $("#add_deptName").textbox("setValue", row.deptName);
            $("#add_deptTel").textbox("setValue", row.deptTel);
            $("#add_deptLocation").textbox("setValue", row.deptLocation);
            $("#add_deptId").val(row.deptId);
            $("#dlg_deptSave").dialog("open");
        }
    });


    $("#btn_cancelDept").click(function () {
        closDialog();
    });

    function closDialog() {
        $("#add_deptCode").textbox("setValue", "");
        $("#add_deptName").textbox("setValue", "");
        $("#add_deptTel").textbox("setValue", "");
        $("#add_deptLocation").textbox("setValue", "");
        $("#add_deptId").val("");
        //大家补充
        $("#dlg_deptSave").dialog("close");
    }


    $("#btn_saveDept").click(function () {
        var deptCode = $("#add_deptCode").textbox("getValue");
        if (deptCode == "") {
            $.messager.alert('提醒', '部门编号不能为空！', 'info', function () {
                $("#add_deptCode + span input:first-child").focus();
            });
            return false;
        }
        var deptName = $("#add_deptName").textbox("getValue");
        if (deptName == "") {
            $.messager.alert('提醒', '部门名称不能为空！', 'info', function () {
                $("#add_deptName + span input:first-child").focus();
            });

            return false;
        }
        var deptLocation = $("#add_deptLocation").textbox("getValue");
        if (deptLocation == "") {
            $.messager.alert('提醒', '部门地址不能为空！', 'info', function () {
                $("#add_deptLocation + span input:first-child").focus();
            });

            return false;
        }
        var deptTel = $("#add_deptTel").textbox("getValue");
        if (deptTel == "") {
            $.messager.alert('提醒', '部门电话不能为空！', 'info', function () {
                $("#add_deptTel + span input:first-child").focus();
            });

            return false;
        }
        var deptId = $("#add_deptId").val();
        var url = ""
        if (deptId == "") {
            url = path + "/dept/addDept";
        } else {
            url = path + "/dept/updDept";
        }
        var postData = $("#saveFormPost").serializeJSON();
        console.log(postData);
        $.post(url, postData, function (data) {
            var mess = data.mess;
            if (mess == "success") {
                if (deptId == "") {
                    $.messager.alert('提醒', '保存成功！', 'info', function () {
                        closDialog();
                    });
                } else {
                    $.messager.alert('提醒', '修改成功！', 'info', function () {
                        closDialog();
                    });
                }
                $('#tbl_dept_detail').datagrid('reload', $("#queryForm").serializeJSON());
            } else {
                $.messager.alert('提醒', mess, 'info');
            }
        })
    });


    function goDept() {
        var url = path + "/dept/querDept";
        $('#tbl_dept_detail').datagrid({
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
            pageSize: 5,
            pageList: [5, 10, 20, 30],
            columns: [[
                {field: 'deptCode', title: '部门编号', width: 80, halign: 'center', align: 'right'},
                {field: 'deptName', title: '部门名称', width: 80, halign: 'center', align: 'left'},
                {field: 'deptLocation', title: '部门地址', width: 80, halign: 'center', align: 'left'},
                {field: 'deptTel', title: '部门电话', width: 80, halign: 'center', align: 'right'},
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