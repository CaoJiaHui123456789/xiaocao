/**
 * 
 */
$(document).ready(function() {
	var path = getRootPath();
	initBuilding();
	$("#txt_parkId").combobox({
		url: path + '/sysset/getAllPark'
	});
	$("#btn_search").click(function() {
		$('#tbl_building_detail').datagrid('reload', $("#queryForm").serializeJSON());
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
	$("#btn_delBuilding").click(function() {
		var row = $('#tbl_building_detail').datagrid("getSelected");
		if (row == null) {
			$.messager.alert('提醒', '请选中要删除的数据！', 'info');
			return false;
		} else {
			$.messager.confirm('确认', '你是否要删除' + row.buildingName + '?', function(r) {
				if (r) {
					var url = path + "/sysset/delBuilding";
					var postData = { "buildingId": row.buildingId };
					$.post(url, postData, function(data) {
						var mess = data.mess;
						if (mess == "success") {
							$.messager.alert('提醒', '删除成功！', 'info');
							$('#tbl_building_detail').datagrid('reload', $("#queryForm").serializeJSON());
						} else {
							$.messager.alert('提醒', mess, 'info');
						}
					})
				}
			});
		}
	});

	//弹出对话框
	$("#btn_addBuilding").click(function() {
		$('#dlg_buildingSave').dialog({ title: '增加' })
		$("#dlg_buildingSave").dialog("open");
	});
	$("#btn_editBuilding").click(function() {
		var row = $('#tbl_building_detail').datagrid("getSelected");
		if (row == null) {
			$.messager.alert('提醒', '请选中要修改的数据', 'info');
			return false;
		} else {
			$('#dlg_buildingSave').dialog({ title: '修改' })
			$("#add_buildingName").textbox("setValue", row.buildingName);
			$("#add_buildingImg").textbox("setValue", row.buildingImg);
			$("#add_floorNumber").textbox("setValue", row.floorNumber);
			$("#add_houseNumber").textbox("setValue", row.houseNumber);
			$("#add_buildingArea").textbox("setValue", row.buildingArea);
			$("#add_publicArea").textbox("setValue", row.publicArea);
			$("#add_contractArea").textbox("setValue", row.contractArea);
			$("#add_avgArea").textbox("setValue", row.avgArea);
			$("#add_cusNumber").textbox("setValue", row.cusNumber);
			$("#add_rentArea").textbox("setValue", row.rentArea);
			$("#txt_parkId").val(row.park.parkId);
			$("#add_buildingId").val(row.buildingId);
			$("#dlg_buildingSave").dialog("open");
		}
	});



	$("#btn_cancelBuilding").click(function() {
		closDialog();
	});

	function closDialog() {
		$("#add_buildingName").textbox("setValue", "");
		$("#add_buildingImg").textbox("setValue", "");
		$("#add_floorNumber").textbox("setValue", "");
		$("#add_houseNumber").textbox("setValue", "");
		$("#add_buildingArea").textbox("setValue", "");
		$("#add_publicArea").textbox("setValue", "");
		$("#add_contractArea").textbox("setValue", "");
		$("#add_avgArea").textbox("setValue", "");
		$("#add_cusNumber").textbox("setValue", "");
		$("#add_rentArea").textbox("setValue", "");
		$("#txt_parkId").val("1");
		$("#add_buildingId").val("");
		//大家补充
		$("#dlg_buildingSave").dialog("close");
	}


	$("#btn_saveBuilding").click(function() {
		var buildingName = $("#add_buildingName").textbox("getValue");
		if (buildingName == "") {
			$.messager.alert('提醒', '楼宇名称不能为空！', 'info',function(){
				$("#add_buildingName + span input:first-child").focus();
			});
			return false;
		}
		//留着有用
		// var parkId = $("#txt_parkId").combobox("getValue");
		// if (parkId == "") {
		// 	$.messager.alert('提醒', '所属园区不能为空！', 'info');
		// 	return false;
		// }
		var buildingArea = $("#add_buildingArea").textbox("getValue");
		if (buildingArea == "") {
			$.messager.alert('提醒', '建筑面积不能为空！', 'info',function(){
				$("#add_buildingArea + span input:first-child").focus();
			});
			return false;
		}
		
		var buildingId = $("#add_buildingId").val();
		var url = ""
		if (buildingId == "") {
			url = path + "/sysset/addBuilding";
		} else {
			url = path + "/sysset/updBuilding";
		}
		var postData = $("#saveFormPost").serializeJSON();
		console.log(postData);
		$.post(url, postData, function(data) {
			var mess = data.mess;
			if (mess == "success") {
				if (buildingId == "") {
					$.messager.alert('提醒', '新增成功！', 'info', function() {
						closDialog();
					});
				} else {
					$.messager.alert('提醒', '修改成功！', 'info', function() {
						closDialog();
					});
				}
				$('#tbl_building_detail').datagrid('reload', $("#queryForm").serializeJSON());
			} else {
				$.messager.alert('提醒', mess, 'info');
			}
		})
	});


	function initBuilding() {
		var url = path + "/sysset/queryBuilding";
		$('#tbl_building_detail').datagrid({
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
					field: 'parkName', title: '所属园区', width: 80, halign: 'center', formatter: function(value, row, index) {
						if (row.park) {
							return row.park.parkName
						} else {
							return "暂无园区";
						}
					}
				},
				{ field: 'buildingName', title: '楼宇名称', width: 50, halign: 'center' },
				{ field: 'floorNumber', title: '楼宇层数', width: 80, halign: 'center', align: 'right' },
				{ field: 'houseNumber', title: '房源数量', width: 80, halign: 'center', align: 'right' },
				{ field: 'buildingArea', title: '建筑面积', width: 80, halign: 'center', align: 'right' },
				{ field: 'publicArea', title: '公共面积', width: 80, halign: 'center', align: 'right' },
				{ field: 'contractArea', title: '合约面积', width: 80, halign: 'center', align: 'right' },
				{ field: 'avgArea', title: '户均面积', width: 80, halign: 'center', align: 'right' },
				{ field: 'cusNumber', title: '客户数量', width: 80, halign: 'center', align: 'right' },
				{ field: 'rentArea', title: '租赁面积', width: 80, halign: 'center', align: 'right' },

			]],
			onBeforeLoad: function(param) {
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