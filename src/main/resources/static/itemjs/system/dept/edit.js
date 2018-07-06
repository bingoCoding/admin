
$("#form-dept-edit").validate({
	rules:{
		deptName:{
			required:true,
			remote: {
                url: ctx + "sys/dept/checkDeptNameUnique",
                type: "post",
                data: {
                    "deptId": function () {
                        return $("input[name='id']").val();
                    },
                    "deptName": function () {
                        return $("input[name='deptName']").val();
                    }
                }
            }
		},
		orderNum:{
			required:true,
			digits:true
		},
	},
	messages: {
        "deptName": {
            remote: "部门已经存在"
        }
    },
	submitHandler:function(form){
		update();
	}
});

function update() {
	_ajax_save(ctx + "sys/dept/save", $("#form-dept-edit").serialize());
}

/*部门管理-修改-选择部门树*/
function selectDeptTree() {
	var deptId = $("#treeId").val();
    var url = ctx + "sys/dept/selectDeptTree/" + deptId;
    layer_show("选择部门", url, '380', '380');
}