$(document).ready(function() {
    var menuType = $('input[name="menuType"]:checked').val();
    menuVisible(menuType);
});

$("#form-menu-edit").validate({
	rules:{
		menuName:{
			required:true,
			remote: {
                url: ctx + "sys/menu/checkMenuNameUnique",
                type: "post",
                data: {
                    "menuId": function () {
                        return $("input[name='id']").val();
                    },
                    "menuName": function () {
                        return $("input[name='menuName']").val();
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
        "menuName": {
            remote: "菜单已经存在"
        }
    },
	submitHandler:function(form){
		update();
	}
});

$(function() {
    $("input[name='icon']").focus(function() {
        $(".icon-drop").show();
    });
    $("#form-menu-edit").click(function(event) {
        var obj = event.srcElement || event.target;
        if (!$(obj).is("input[name='icon']")) {
            $(".icon-drop").hide();
        }
    });
    $(".icon-drop").find(".ico-list i").on("click",
    function() {
        $('#icon').val($(this).attr('class'));
    });
    $('input').on('ifChecked',
    function(event) {
        var menuType = $(event.target).val();
        menuVisible(menuType);
    });
});

function menuVisible(menuType) {
    if (menuType == 0) {
        $("#icon").parents(".form-group").show();
    } else if (menuType == 1) {
        $("#icon").parents(".form-group").hide();
    } else if (menuType == 2) {
        $("#icon").parents(".form-group").hide();
    }
}

function update() {
	_ajax_save(ctx + "sys/menu/save", $("#form-menu-edit").serialize());
}

/*菜单管理-修改-选择菜单树*/
function selectMenuTree() {
	var menuId = $("#treeId").val();
	if(menuId > 0)
	{
		var url = ctx + "sys/menu/selectMenuTree/" + menuId;
        layer_show("选择菜单", url, '380', '380');
	}
	else
	{
		$.modalAlert("主菜单不能选择", "error");
	}
}