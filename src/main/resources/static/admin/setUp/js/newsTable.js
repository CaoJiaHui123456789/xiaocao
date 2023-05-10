/**
 *
 */
var path = getRootPath();

$(document).ready(function () {
    /*
        $(function(){
                $('#te').texteditor({
                    //...
                });
            });*/

    var path = getRootPath();
    initNews();
    $("#btn_search").click(function () {
        $('#tbl_News_detail').datagrid('reload', $("#queryForm").serializeJSON());
    });


    $("#btn_delNews").click(function () {
        var row = $('#tbl_News_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要删除的数据！', 'info');
            return false;
        } else {
            $.messager.confirm('确认', '你是否要删除' + row.newsCode + '?', function (r) {
                if (r) {
                    var url = path + "/sysset/delNewsTable";
                    var postData = {"newsId": row.newsId};
                    $.post(url, postData, function (data) {
                        var mess = data.mess;
                        if (mess == "success") {
                            $.messager.alert('提醒', '删除成功！', 'info');
                            $('#tbl_News_detail').datagrid('reload', $("#queryForm").serializeJSON());
                        } else {
                            $.messager.alert('提醒', mess, 'info');
                        }
                    })
                }
            });
        }
    });

    //弹出对话框
    $("#btn_addNews").click(function () {
        $('#dlg_NewsSave').dialog({title: '增加'})
        $("#dlg_NewsSave").dialog("open");
    });
    $("#btn_editNews").click(function () {
        var row = $('#tbl_News_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要修改的数据', 'info');
            return false;
        } else {
            $('#dlg_NewsSave').dialog({title: '修改'})
            $("#add_newsCode").textbox("setValue", row.newsCode);
            $("#add_newsHead").textbox("setValue", row.newsHead);
            $("#add_newsRank").textbox("setValue", row.newsRank);
            $("#add_newsSum").datebox("setValue", row.newsSum);
            $("#add_newsId").val(row.newsId);
            $("#dlg_NewsSave").dialog("open");
        }
    });


    $("#btn_cancelNews").click(function () {
        closDialog();
    });

    function closDialog() {
        $("#add_newsCode").textbox("setValue", "");
        $("#add_newsHead").textbox("setValue", "");
        $("#add_newsRank").textbox("setValue", "");
        $("#add_newsSum").datebox("setValue", "");
        $("#add_newsId").val("");
        //大家补充
        $("#dlg_NewsSave").dialog("close");
    }


    $("#btn_saveNews").click(function () {
        var newsHead = $("#add_newsHead").textbox("getValue");
        if (newsHead == "") {
            $.messager.alert('提醒', '新闻模块不能为空！', 'info');
            return false;
        }
        var newsCode = $("#add_newsCode").textbox("getValue");
        if (newsCode == "") {
            $.messager.alert('提醒', '新闻编号不能为空！', 'info');
            return false;
        }


        var newsId = $("#add_newsId").val();
        var url = ""
        if (newsId == "") {
            url = path + "/sysset/addNewsTable";
        } else {
            url = path + "/sysset/updNewsTable";
        }
        var postData = $("#saveFormPost").serializeJSON();
        console.log(postData);
        $.post(url, postData, function (data) {
            var mess = data.mess;
            if (mess == "success") {
                if (newsId == "") {
                    $.messager.alert('提醒', '保存成功！', 'info', function () {
                        closDialog();
                    });
                } else {
                    $.messager.alert('提醒', '修改成功！', 'info', function () {
                        closDialog();
                    });
                }
                $('#tbl_News_detail').datagrid('reload', $("#queryForm").serializeJSON());
            } else {
                $.messager.alert('提醒', mess, 'info');
            }
        })
    });


    function initNews() {
        var url = path + "/sysset/queryNewsTable";
        $('#tbl_News_detail').datagrid({
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
                {field: 'newsCode', title: '版块编号', width: 50, halign: 'center', align: 'right'},
                {field: 'newsHead', title: '新闻板块', width: 120, halign: 'center'},
                {
                    field: 'newsRank',
                    title: '新闻等级',
                    width: 80,
                    halign: 'center',
                    formatter: function (value, row, index) {
                        switch (row.newsRank) {
                            case 1:
                                return "一级";
                            case 2:
                                return "二级";
                            case 3:
                                return "三级";
                            default:
                                return "未知等级";
                        }
                    }
                },
                {field: 'newsSum', title: '新闻概述', width: 80, halign: 'center'}
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