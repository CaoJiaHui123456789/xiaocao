// function showintro(data) {
// 	var intro_frame=$("#showintro iframe")
// 	var dlg_showintro=$("#showintro");
// 	intro_frame.attr("srcdoc", data);
// 	dlg_showintro.window("open");
// }


$(document).ready(function () {
    var path = getRootPath();
    var options = {
        uploadJson: path + '/sysset/addNewsPicture',
        allowFileManager: false
    };
    editor = KindEditor.create('textarea[name="content"]', options);

    initStaff();
    $("#btn_addPark").click(function () {
        $('#dlg_parkSave').dialog({title: '增加'});
        $("#dlg_parkSave").dialog("open");
        $("#add_parkAddress").textbox("setValue", "");
        $("#add_parkAdmin").textbox("setValue", "");
        $("#add_parkArea").textbox("setValue", "");
        $("#add_parkId").val('');
        $("#add_parkPhone").textbox("setValue", "");
        $("#add_parkMailbox").textbox("setValue", "");
        $("#add_parkSiteurl").textbox("setValue", "");
        $("#add_parkName").textbox("setValue", "");
        $("#add_parkDis").combobox("clear");
        $("#add_parkCity").combobox("clear");
        $("#add_parkPro").combobox("clear");

    });

    $("#btn_editPark").click(function () {
        var row = $('#tbl_park_detail').datagrid('getSelected');
        if (row != null) {
            $('#dlg_parkSave').dialog({title: '修改'})
            $("#dlg_parkSave").dialog("open");
            $("#add_parkName").textbox("setValue", row.parkName);
            $("#add_parkPro").combobox("select", row.parkPro);
            $("#add_parkCity").combobox("select", row.parkCity);
            $("#add_parkDis").combobox("select", row.parkDis);
            $("#add_parkAddress").textbox("setValue", row.parkAddress);
            $("#add_parkAdmin").textbox("setValue", row.parkAdmin);
            $("#add_parkArea").textbox("setValue", row.parkArea);
            $("#add_parkPhone").textbox("setValue", row.parkPhone);
            $("#add_parkMailbox").textbox("setValue", row.parkMailbox);
            $("#add_parkSiteurl").textbox("setValue", row.parkSiteurl);
            editor.sync();
            KindEditor.html("#editorid", row.parkIntro);

            $("#add_parkId").val(row.parkId);
        } else {
            $.messager.alert('提醒', '请选择要修改的数据', 'info');
            return false;
        }
    });

    //$.post(url,postData,function(data){


    $("#btn_savePark").click(function () {	                    //新增或修改

        var parkName = $("#add_parkName").textbox("getValue");
        if (parkName == '') {
            $.messager.alert('提醒', '园区名称不能为空!', 'info', function () {
                $("#add_parkName + span input:first-child").focus();
            });
            return false;
        }


        var parkPro = $("#add_parkPro").combobox("getValue");
        if (parkPro == '') {
            $.messager.alert('提醒', '所在省份不能为空!', 'info', function () {
                $("#add_parkPro").combo("showPanel");
            });
            return false;
        }


        var parkCity = $("#add_parkCity").combobox("getValue");
        if (parkCity == '') {
            $.messager.alert('提醒', '所在城市不能为空!', 'info', function () {
                $("#add_parkCity").combo("showPanel");
            });
            return false;
        }


        var parkDis = $("#add_parkDis").combobox("getValue");
        if (parkDis == '') {
            $.messager.alert('提醒', '所在城区不能为空!', 'info', function () {
                $("#add_parkDis").combo("showPanel");
            });
            return false;
        }


        var parkAddress = $("#add_parkAddress").textbox("getValue");
        if (parkAddress == '') {
            $.messager.alert('提醒', '详细地址不能为空!', 'info', function () {
                $("#add_parkAddress + span input:first-child").focus();
            });
            return false;
        }


        var parkAdmin = $("#add_parkAdmin").textbox("getValue");
        if (parkAdmin == '') {
            $.messager.alert('提醒', '管理人员不能为空!', 'info', function () {
                $("#add_parkAdmin + span input:first-child").focus();
            });
            return false;
        }
        var parkArea = $("#add_parkArea").textbox("getValue");
        if (parkArea == '') {
            $.messager.alert('提醒', '园区面积不能为空!', 'info', function () {
                $("#add_parkArea + span input:first-child").focus();
            });
            return false;
        }
        var parkPhone = $("#add_parkPhone").textbox("getValue");
        if (parkPhone == '') {
            $.messager.alert('提醒', '园区电话不能为空!', 'info', function () {
                $("#add_parkPhone + span input:first-child").focus();
            });
            return false;
        }
        var parkMailbox = $("#add_parkMailbox").textbox("getValue");
        if (parkMailbox == '') {
            $.messager.alert('提醒', '园区邮箱不能为空!', 'info', function () {
                $("#add_parkMailbox + span input:first-child").focus();
            });
            return false;
        }
        var parkSiteurl = $("#add_parkSiteurl").textbox("getValue");
        if (parkSiteurl == '') {
            $.messager.alert('提醒', '园区网站不能为空!', 'info', function () {
                $("#add_parkSiteurl + span input:first-child").focus();
            });
            return false;
        }


        var parkId = $("#add_parkId").val();
        if (parkId == "") {
            url = path + "/sysset/addPark";
        } else {
            url = path + "/sysset/updSysPark";
        }

        var postData = $("#saveFormPost").serializeJSON();
        editor.sync();
        postData.parkIntro = $("textarea[name='content']").val();
        console.log(postData);
        $.post(url, postData, function (data) {
            var mess = data.mess;
            if (mess == "success") {
                if (parkId == "") {
                    $.messager.alert('提醒', '保存成功！', 'info', function () {
                        closDialog();
                    });
                } else {
                    $.messager.alert('提醒', '保存成功！', 'info', function () {
                        closDialog();
                    });
                }
                $('#tbl_park_detail').datagrid('reload', $("#queryForm").serializeJSON());
            } else {
                $.messager.alert('提醒', mess, 'info');
            }
        })
    });


    //删除
    // $("#btn_delPark").click(function () {
    //
    //     var row = $('#tbl_park_detail').datagrid('getSelected');
    //     if (row.length === 0) {
    //         $.messager.alert('提醒', '请选中要删除的数据！', 'info');
    //         return false;
    //     } else {
    //         $.messager.confirm('确认', '你是否要删除' + row.parkName, function (r) {
    //             if (r) {
    //                 var url = path + "/sysset/delSysPark";
    //                 var postData = {"parkId": row.parkId}
    //                 console.log(postData);
    //
    //                 $.post(url, postData, function (data) {
    //                     var mess = data.mess;
    //                     if (mess == 'success') {
    //                         $.messager.alert('提醒', '删除成功！', 'info');
    //                         initStaff();
    //                     } else {
    //                         $.messager.alert('提醒', mess, 'info');
    //                     }
    //                 })
    //             }
    //         });
    //     }
    // })


    //新增时取消
    // $("#btn_cancelpark").click(function () {
    //     closDialog();
    // });
    //
    //
    // $("#btn_search").click(function () {
    //     $('#tbl_staff_detail').datagrid('reload', $("#queryForm").serializeJSON());
    // });

    function initStaff() {
        var url = path + "/sysset/queryAllPark";
        $.post(url,{},function (data) {
            row=data.rows[0];
            $('#dlg_parkSave').dialog({title: '修改'})
            $("#dlg_parkSave").dialog("open");
            $("#add_parkName").textbox("setValue", row.parkName);
            $("#add_parkPro").combobox("select", row.parkPro);
            $("#add_parkCity").combobox("select", row.parkCity);
            $("#add_parkDis").combobox("select", row.parkDis);
            $("#add_parkAddress").textbox("setValue", row.parkAddress);
            $("#add_parkAdmin").textbox("setValue", row.parkAdmin);
            $("#add_parkArea").textbox("setValue", row.parkArea);
            $("#add_parkPhone").textbox("setValue", row.parkPhone);
            $("#add_parkMailbox").textbox("setValue", row.parkMailbox);
            $("#add_parkSiteurl").textbox("setValue", row.parkSiteurl);
            editor.sync();
            KindEditor.html("#editorid", row.parkIntro);

            $("#add_parkId").val(row.parkId);

        });

        // $('#tbl_park_detail').datagrid({
        //     border: false,
        //     singleSelect: true,
        //     fit: true,
        //     fitColumns: true,
        //     rownumbers: true,
        //     autoRowHeight: false,
        //     nowrap: true,
        //     loadMsg: "正在加载，请稍后...",
        //     striped: true,
        //     url: url,
        //     queryParams: $("#queryForm").serializeJSON(),
        //     pagination: true,
        //     pageSize: 10,
        //     pageList: [5, 10, 20, 30],
        //     columns: [[
        //         {field: 'parkName', title: '园区名称', width: 50, halign: 'center'},
        //         {
        //             field: 'parkPro',
        //             title: '所在省份',
        //             width: 50,
        //             halign: 'center',
        //             formatter: function (value, row, index) {
        //                 const responsedata = $("#add_parkPro").combobox("getData");
        //                 const data = responsedata.filter(function (fp) {
        //                     //console.log($("#sheng").combobox("getValue"));
        //                     return fp.code === row.parkPro;
        //                 })[0];
        //                 if (typeof (data) !== undefined) {
        //                     return data.value;
        //                 } else {
        //                     return '省份未知'
        //                 }
        //             }
        //         },
        //         {
        //             field: 'parkCity',
        //             title: '所在市名',
        //             width: 50,
        //             halign: 'center',
        //             formatter: function (value, row, index) {
        //                 const responsedata = $("#add_parkPro").combobox("getData").filter(function (fp) {
        //                     //console.log($("#sheng").combobox("getValue"));
        //                     return fp.code === row.parkPro;
        //                 })[0].children;
        //                 if (typeof (responsedata) !== undefined) {
        //                     const data = responsedata.filter(function (fp) {
        //                         //console.log($("#sheng").combobox("getValue"));
        //                         return fp.code === row.parkCity;
        //                     })[0];
        //                     if (typeof (data) !== undefined) {
        //                         return data.value;
        //                     } else {
        //                         return '城市未知'
        //                     }
        //                 } else {
        //                     return ''
        //                 }
        //             }
        //         },
        //         {
        //             field: 'parkDis',
        //             title: '所在区名',
        //             width: 120,
        //             halign: 'center',
        //             formatter: function (value, row, index) {
        //                 const responsedata = $("#add_parkPro").combobox("getData").filter(function (fp) {
        //                     //console.log($("#sheng").combobox("getValue"));
        //                     return fp.code === row.parkPro;
        //                 })[0].children.filter(function (fp) {
        //                     //console.log($("#sheng").combobox("getValue"));
        //                     return fp.code === row.parkCity;
        //                 })[0].children;
        //                 const data = responsedata.filter(function (fp) {
        //                     //console.log($("#sheng").combobox("getValue"));
        //                     return fp.code === row.parkDis;
        //                 })[0];
        //                 return data.value;
        //             }
        //         },
        //         {field: 'parkAddress', title: '详细地址', width: 80, halign: 'center'},
        //         {field: 'parkAdmin', title: '管理人员', width: 80, halign: 'center'},
        //         {field: 'parkArea', title: '园区面积', width: 80, halign: 'center', align: 'right'},
        //         {
        //             field: 'parkIntro',
        //             title: '园区简介',
        //             width: 80,
        //             halign: "center",
        //             align: 'right',
        //             formatter: function (value, row, index) {
        //                 if (row.parkIntro) {
        //                     return '<a href="../../busi/parkIntro.html" target="_blank">查看简介</a>';
        //                 } else {
        //                     return '暂无简介'
        //                 }
        //             }
        //         },
        //         {field: 'parkPhone', title: '园区电话', width: 80, halign: 'center', align: 'right'},
        //         {field: 'parkMailbox', title: '园区邮箱', width: 80, halign: 'center', align: 'right'},
        //         {field: 'parkSiteurl', title: '园区网址', width: 80, halign: 'center', align: 'right'},
        //     ]],
        //     onBeforeLoad: function (param) {
        //         var pageNo = param.page;
        //         delete param.page;
        //         param.pageNo = pageNo;
        //         var pageSize = param.rows;
        //         delete param.rows;
        //         param.pageSize = pageSize;
        //     }
        //
        //     // onClickCell: function(index,field,value){
        //     // 	if(field=="parkIntro"){
        //     // 		showintro($("#tbl_park_detail").datagrid("getRows")[index].parkIntro);
        //     // 	}
        //     // }
        //
        // });
    }

    // function closDialog() {
    //     editor.sync();
    //     $("#dlg_parkSave").dialog("close");
    //     $("#add_parkName").textbox("setValue", "");
    //     $("#add_parkDis").combobox("clear");
    //     $("#add_parkCity").combobox("clear");
    //     $("#add_parkPro").combobox("clear");
    //     $("#add_parkAddress").textbox("setValue", "");
    //     $("#add_parkAdmin").textbox("setValue", "");
    //     $("#add_parkArea").textbox("setValue", "");
    //     $("#add_parkId").val('');
    //     $("#add_parkPhone").textbox("setValue", "");
    //     $("#add_parkMailbox").textbox("setValue", "");
    //     $("#add_parkSiteurl").textbox("setValue", "");
    //     KindEditor.html("#editorid", "");
    //
    // }


});