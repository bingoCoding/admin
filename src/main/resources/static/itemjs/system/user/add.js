$("#form-user-add").validate({
	rules:{
		loginName:{
			required:true,
			minlength: 4,
			remote: {
                url: ctx + "sys/user/checkUserNameUnique",
                type: "post",
                data: {
                	name : function() {
                        return $.trim($("#loginName").val());
                    }
                }
            }
		},
		userName:{
			required:true,
		},
		password:{
			required:true,
			minlength: 6
		},
		email:{
			required:true,
			email:true
		},
		phonenumber:{
			required:true,
		},
	},
	messages: {
        "loginName": {
            remote: "用户已经存在"
        }
    },
	submitHandler:function(form){
		add();
	}
});

function add() {
	var userId = $("input[name='userId']").val();
	var deptId = $("input[name='deptId']").val();
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var email = $("input[name='email']").val();
	var phone = $("input[name='phone']").val();
	var sex = $("input[name='sex']:checked").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var userType = $("input[name='userType']").is(':checked') == true ? "Y" : "N";
	var roleIds = $.getCheckeds("role");
	var postIds = $.getSelects("post");
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "sys/user/save",
		data : {
			"id": userId,
			"deptId": deptId,
			"loginName": loginName,
			"userName": userName,
			"password": password,
			"email": email,
			"phone": phone,
			"sex": sex,
			"status": status,
			"userType": userType,
			"roleIds": roleIds
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					$.parentReload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

/*用户管理-新增-选择部门树*/
function selectDeptTree() {
	var treeId = $("#treeId").val();
	var deptId = treeId == null || treeId == "" ? "1" : treeId;
	var url = ctx + "sys/dept/getDept/" + deptId;
    layer_show("选择部门", url, '380', '380');
}
