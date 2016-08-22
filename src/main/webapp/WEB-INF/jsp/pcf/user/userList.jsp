<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>用户列表</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <div class="row">
               
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" name="search.user_cd_like" class="form-control" placeholder="请输入真实姓名" id="form-field-1"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">用户状态</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="form-field-select-1" name="search.delete_flag_eq">
                                <option value="">所有</option>
                                <option value="1">无效</option>
                                <option value="0">有效</option>
                                </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="button" class="queryBtn btn btn-sm btn-info">
                                <i class="ace-icon fa fa-search bigger-125"></i>
                                查询
                            </button>
                            <button class="resetBtn btn btn-sm btn-info" type="reset">
                                <i class="ace-icon fa fa-undo bigger-125"></i>
                                重置
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <table id="grid-table-user"></table>
        <div id="grid-pager-user"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table-user";
        var pager_selector = "#grid-pager-user";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/user/list',
            datatype: "json",
            height: 321,
            shrinkToFit: false,
            colNames: [' ', '用户名', '修改时间', '状态','状态'],
            colModel: [
                {name: 'USER_ID', index: 'USER_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'USER_CD', index: 'USER_CD', width: 190, sorttype: "string"},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90,hidden:true},
                {
                    name: 'DELETE_FLAG_VALUE',
                    index: 'DELETE_FLAG_VALUE',
                    width: 90,
                    formatter: "select",
                    editoptions: {value: "0:有效;1:无效"}
                },
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "用户列表"
        }));
        	
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-user", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-user", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加用户</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-user", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改用户</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-user", {
            id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>彻底删除用户</button>",
            buttonicon: "none"
        });
        // 新增加用户
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/pcf/user/toAdd";
        });
        // 修改用户信息
        $("#updateBtn").click(function () {
            //console.debug("开始修改用户数据");
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            //console.log(selr);
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                //console.log(row);
                //console.debug(row);
                document.location.href = "${ctx}/pcf/user/toUpdate?userId=" +row.USER_ID;
            } else {
                addGritter("error", "请选择您要修改的用户");
            }
        });
        // 彻底删除用户
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要彻底删除选中的用户吗?彻底删除以后将无法恢复", function(result) {
                    if(result) {
                        var userIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            userIdList[i]=ret.USER_ID;
                        }
                        // delete user completely
                        doPost("${ctx}/pcf/user/toDeleteUser?userIdList=" +userIdList, function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-user").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择您要彻底删除的用户");
            }
        });
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
    });
</script>
</body>
</html>