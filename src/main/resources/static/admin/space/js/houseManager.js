		/**
 *
 */
var path = getRootPath();

function showimg(picpath) {
    var img=$("#showimg")
    var dlg_showimg=$("#dlg_showimg");
    img.attr("src", path + "/sysset/picture/" + picpath);
    /*
    img[0].onload=function (){
        dlg_showimg.panel("height",img[0].height);
    }
    */
    dlg_showimg.window("open");
}

$(document).ready(function () {
    //var path = getRootPath();
    initsyshouse();
    $("#txt_buildingId").combobox({
        url: path + '/sysset/queryAllBuilding'
    });
    $("#txt_adId").combobox({
        url: path + '/aptDiagram/queryAllAd'
    });
    $("#btn_search").click(function () {
        $('#tbl_syshouse_detail').datagrid('reload', $("#queryForm").serializeJSON());
    });


    //下拉窗
    $("#btn_delsyshouse").click(function () {
        var row = $('#tbl_syshouse_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要删除的数据！', 'info');
            return false;
        } else {
            $.messager.confirm('确认', '你是否要删除' + row.houseCode + '?', function (r) {
                if (r) {
                    var url = path + "/sysset/delHouse";
                    var postData = {"houseId": row.houseId};
                    $.post(url, postData, function (data) {
                        var mess = data.mess;
                        if (mess == "success") {
                            $.messager.alert('提醒', '删除成功！', 'info');
                            $('#tbl_syshouse_detail').datagrid('reload', $("#queryForm").serializeJSON());
                        } else {
                            $.messager.alert('提醒', mess, 'info');
                        }
                    })
                }
            });
        }
    });

    //弹出对话框
    $("#btn_addsyshouse").click(function () {
        $('#dlg_syshouseSave').dialog({title: '增加'})
        $("#dlg_syshouseSave").dialog("open");
    });
    $("#btn_editsyshouse").click(function () {
        $('#dlg_syshouseSave').dialog({title: '修改'})
        var row = $('#tbl_syshouse_detail').datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提醒', '请选中要修改的数据', 'info');
            return false;
        } else {
            console.log(row);
            $("#add_syshouseCode").textbox("setValue", row.houseCode);
            $("#add_housePrice").textbox("setValue", row.housePrice);
            $("#add_houseStatus").textbox("setValue", row.houseStatus);
            $("#add_houseType").textbox("setValue", row.houseType);
            $("#add_custom").textbox("setValue", row.custom);
            $("#txt_buildingId").combobox("setValue", row.building.buildingId);
            $("#add_basePrice").textbox("setValue", row.basePrice);
            $("#add_areaRent").textbox("setValue", row.areaRent);
            $("#add_payStandard").combobox("setValue", row.payStandard);
            $("#add_tollMoney").textbox("setValue", row.tollMoney);
            $("#add_aircondPrice").textbox("setValue", row.aircondPrice);
            $("#add_aircondTollmode").textbox("setValue", row.aircondTollmode);
            $("#txt_adId").combobox("setValue", row.aptdiagram.adId);
            if (row.payStatus) {
                $("#rdo_payStatus_yes").radiobutton("check");
                $("#rdo_spayStatus_no").radiobutton("uncheck");
            } else {
                $("#rdo_payStatus_yes").radiobutton("uncheck");
                $("#rdo_spayStatus_no").radiobutton("check");
            }
            $("#add_houseId").val(row.houseId);
            $("#dlg_syshouseSave").dialog("open");
        }
    });


    $("#btn_cancelsyshouse").click(function () {
        closDialog();
    });

    function closDialog() {
        $("#add_houseCode").textbox("setValue", "");
        $("#add_housePrice").textbox("setValue", "");
        $("#add_basePrice").textbox("setValue", "");
        $("#add_houseStatus").textbox("setValue", "");
        $("#add_houseType").textbox("setValue", "");
        $("#add_custom").textbox("setValue", "");
        $("#txt_buildingId").combobox("clear");
        $("#add_areaRent").textbox("setValue", "");
        $("#add_payStandard").combobox("setValue", "");
        $("#add_tollMoney").textbox("setValue", "");
        $("#add_aircondPrice").textbox("setValue", "");
        $("#add_aircondTollmode").textbox("setValue", "");
        $("#txt_adId").combobox("clear");
        $("#add_houseId").val("");
        //大家补充
        $("#dlg_syshouseSave").dialog("close");
    }


    $("#btn_savesyshouse").click(function () {

       var buildingId = $("#txt_buildingId").combobox("getValue");
        if (buildingId == "") {
            $.messager.alert('提醒', '楼栋名称不能为空！', 'info',function(){
                $("#txt_buildingId").combo("showPanel");
            });
            return false;
        } 
        var houseCode = $("#add_syshouseCode").textbox("getValue");
        if (houseCode == "") {
            $.messager.alert('提醒', '房间编号不能为空！', 'info',function(){
                $("#add_syshouseCode + span input:first-child").focus();
            });
            return false;
        } 
        var adId = $("#txt_adId").combobox("getValue");
        if (adId == "") {
            $.messager.alert('提醒', '户型图不能为空！', 'info',function(){
                $("#txt_adId").combo("showPanel");
            });
            return false;
        }


        var houseId = $("#add_houseId").val();
        var url = ""
        if (houseId == "") {
            url = path + "/sysset/addHouse";
        } else {
            url = path + "/sysset/updHouse";
        }
        var postData = $("#saveFormPost").serializeJSON();
        console.log(postData);
        $.post(url, postData, function (data) {
            var mess = data.mess;
            if (mess == "success") {
                if (houseId == "") {
                    $.messager.alert('提醒', '新增成功！', 'info', function () {
                        closDialog();
                    });
                } else {
                    $.messager.alert('提醒', '修改成功！', 'info', function () {
                        closDialog();
                    });
                }
                $('#tbl_syshouse_detail').datagrid('reload', $("#queryForm").serializeJSON());
            } else {
                $.messager.alert('提醒', mess, 'info');
            }
        })
    });


    function initsyshouse() {
        var url = path + "/sysset/queryHouse";
        $('#tbl_syshouse_detail').datagrid({
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
                {
                    field: 'parkName',
                    title: '所属园区',
                    halign: 'center',
                    width: 80,
                    formatter: function (value, row, index) {
                        //console.log(row);
                        //console.log(row.building.park)
                        if (row.building.park) {
                            return row.building.park.parkName
                        } else {
                            return "暂无园区";
                        }
                    }
                },
                {
                    field: 'buildingName',
                    title: '所属楼宇',
                    width: 80,
                    halign: 'center',
                    formatter: function (value, row, index) {
                        if (row.building) {
                            return row.building.buildingName;
                        } else {
                            return "暂无楼宇";
                        }
                    }
                },
                {
                    field: 'img',
                    title: '户型图片',
                    width: 80,
                    halign: 'center',
                    formatter: function (value, row, index) {
                        if (row.aptdiagram) {
                            return "<a href='javascript:void(0);'>查看详情</a>";
                        } else {
                            return "暂无户型图";
                        }
                    }
                },
                {field: 'houseCode', title: '房间编号', width: 80, halign: 'center', align: 'right'},
                {field: 'houseStatus', title: '房间状态', width: 80, halign: 'center'},
                {field: 'houseType', title: '房间类型', width: 80, halign: 'center'},
                {field: 'housePrice', title: '房屋价格', width: 80, halign: 'center', align: 'right'},
                {field: 'basePrice', title: '基准单价', width: 80, halign: 'center', align: 'right'},
                {field: 'areaRent', title: '租赁面积', width: 80, halign: 'center', align: 'right'},
                {field: 'payStandard', title: '交付标准', width: 80, halign: 'center'},
                {field: 'tollMoney', title: '月收金额', width: 80, halign: 'center', align: 'right'},
                {field: 'aircondPrice', title: '空调单价', width: 80, halign: 'center', align: 'right'},
                {field: 'aircondTollmode', title: '空调收费模式', width: 90, halign: 'center'},
                {
                    field: 'payStatus',
                    title: '交付状态',
                    halign: 'center',
                    width: 80,
                    formatter: function (value, row, index) {
                        if (row.payStatus) {
                            return "已交付";
                        } else {
                            return "未交付";
                        }
                    }
                },
                {field: 'custom', title: '客户信息', width: 80, halign: 'center'},
            ]],
            onBeforeLoad: function (param) {
                var pageNo = param.page;
                delete param.page;
                param.pageNo = pageNo;
                var pageSize = param.rows;
                delete param.rows;
                param.pageSize = pageSize;

            },
            onClickCell: function(index,field,value){
                if(field=="img"){
				    showimg($("#tbl_syshouse_detail").datagrid("getRows")[index].aptdiagram.img);
                }
			}

        })
        ;
    }

});