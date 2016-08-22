<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>用户角色所属信息</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <!--
        <form class="form-horizontal well well-sm queryForm">
            <!--<input type="text" class="form-control" name="search.user_id_like" value="${user.userId}" > -->
        <!--</form>-->
        
    </div>
</div>
<div class="row" style="height: 800px;">
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-roleAth"></table>
        <div id="grid-pager-roleAth"></div>
    </div>
    <p></p>
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-role"></table>
        <div id="grid-pager-role"></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    $(function () {
        var grid_selector = "#grid-table-roleAth";
        var pager_selector = "#grid-pager-roleAth";

        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/user/roleAthList?userId=' +${user.userId},
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '角色编号', '角色名'],
            colModel: [
                {name: 'ROLE_ATH_ID', index: 'ROLE_ATH_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'ROLE_NAME', index: 'ROLE_NAME', width: 660, sorttype: "string"},
                {name: 'ROLE', index: 'ROLE', width: 660, sorttype: "string"}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "用户所拥有的角色列表"
        }));
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-roleAth", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-roleAth", {
            id: "updateCase",
            caption: "<button id='deleteRoleBtn' class='btn btn-xs btn-primary'>删除角色</button>",
            buttonicon: "none"
        });
        // 删除用户的角色信息
        $("#deleteRoleBtn").on(ace.click_event, function() {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的用户角色吗?", function(result) {
                    if(result) {
                        var roleAthIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            roleAthIdList[i]=ret.ROLE_ATH_ID;
                        }
                        // delete role from user
                        doPost("${ctx}/pcf/user/toDeleteRoleAth?roleAthIdList=" +roleAthIdList+"&userId=" +${user.userId}, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-roleAth").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要删除的用户角色");
            }
        });
        
        // 角色集合
        var grid_selector_role = "#grid-table-role";
        var pager_selector_role = "#grid-pager-role";

        jQuery(grid_selector_role).jqGrid(jqGridOption({
            url:'${ctx}/pcf/role/list',
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '角色编号', '角色名'],
            colModel: [
                {name: 'ROLE_ID', index: 'ROLE_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'ROLE_NAME', index: 'ROLE_NAME', width: 660, sorttype: "string"},
                {name: 'ROLE', index: 'ROLE', width: 660, sorttype: "string"}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector_role,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "角色列表"
        }));
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector_role).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools_role = $(grid_selector_role).jqGrid("navGrid", "#grid-pager-role", 
            {edit: false, 
             add: false, 
             del: false, 
             search: false, 
             refresh: false, 
             view: false
            });
        gridTools_role.jqGrid("navButtonAdd", "#grid-pager-role", {
            id: "addRoleToUser",
            caption: "<button id='addRoleToUserBtn' class='btn btn-xs btn-primary'>给用户添加角色</button>",
            buttonicon: "none"
        });
        // 给当前用户增加角色
        $("#addRoleToUserBtn").click(function () {
            var selrArray = jQuery(grid_selector_role).jqGrid('getGridParam', 'selarrrow');
            // 必须要有选择一条数据
            if (selrArray.length>0) {
                bootbox.confirm("您确认要给用户增加选中的角色吗?", function(result) {
                    if(result) {
                        var roleIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector_role).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            roleIdList[i]=ret.ROLE_ID;
                        }
                        // Add role to user
                        doPost("${ctx}/pcf/user/toAddRoleToUser?roleIdList=" +roleIdList+"&userId=" +${user.userId}, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-roleAth").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要给用户增加的角色");
            }
        });
        
        // 当是用户还没有建立的时候，角色增加，角色删除的按钮为不可用
        if(${user.userId}=='0'){
            $('#addRoleToUserBtn').attr('disabled',"true");
            $('#deleteRoleBtn').attr('disabled',"true");
        }
        
    });
</script>
</body>
</html>