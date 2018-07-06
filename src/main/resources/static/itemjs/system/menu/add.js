$("#form-menu-add").validate({
	rules:{
		menuName:{
			required:true,
			remote: {
                url: ctx + "sys/menu/checkMenuNameUnique",
                type: "post",
                data: {
                	"menuName" : function() {
                        return $.trim($("#menuName").val());
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
		add();
	}
});

$(function() {
	$("input[name='icon']").focus(function() {
        $(".icon-drop").show();
    });
	$("#form-menu-add").click(function(event) {
	    var obj = event.srcElement || event.target;
	    if (!$(obj).is("input[name='icon']")) {
	    	$(".icon-drop").hide();
	    }
	});
	$(".icon-drop").find(".ico-list i").on("click", function() {
		$('#icon').val($(this).attr('class'));
    });
	$('input').on('ifChecked', function(event){  
		var menuType = $(event.target).val();
		if (menuType == "0") {
            $("#icon").parents(".form-group").show();
        } else if (menuType == "1") {
            $("#icon").parents(".form-group").hide();
        } else if (menuType == "2") {
            $("#icon").parents(".form-group").hide();
        }
	});  
});

function add() {
	_ajax_save(ctx + "sys/menu/save", $("#form-menu-add").serialize());
}

/*菜单管理-新增-选择菜单树*/
function selectMenuTree() {
	var menuId = $("#treeId").val();
	if(menuId > 0)
	{
		var url = ctx + "sys/menu/selectMenuTree/" + menuId;
        layer_show("选择菜单", url, '380', '380');
	}
	else
	{
        var url = ctx + "sys/menu/selectMenuTree/1";
        layer_show("选择菜单", url, '380', '380');
    }
}