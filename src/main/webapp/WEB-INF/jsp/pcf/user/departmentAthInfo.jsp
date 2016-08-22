<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>用户组织所属信息</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form">
            <input name="search.user_id" value="${user.userId}" type="hidden">
        </form>
    </div>
</div>
<div class="row" style="height: 800px;">
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-departmentAth"></table>
        <div id="grid-pager-departmentAth"></div>
    </div>
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-department"></table>
        <div id="grid-pager-department"></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    $(function () {
        var grid_selector = "#grid-table-departmentAth";
        var pager_selector = "#grid-pager-departmentAth";

        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/user/departmentAthList?userId=' +${user.userId},
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '组织编号', '组织名','主所属'],
            colModel: [
                {name: 'DEPARTMENT_ATH_ID', index: 'DEPARTMENT_ATH_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'DEPARTMENT_CD', index: 'DEPARTMENT_CD', width: 660, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 600, sorttype: "string"},
                {name: 'DEPARTMENT_MAIN', index: 'DEPARTMENT_MAIN', width: 60, sorttype: "string"}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "用户所属的部门列表"
        }));
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        // 添加所属部门
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-departmentAth", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        // 设置用户的主所属组织
        gridTools.jqGrid("navButtonAdd", "#grid-pager-departmentAth", {
            id: "setDepartmentMainToUser",
            caption: "<button id='setDepartmentMainToUserBtn' class='btn btn-xs btn-primary'>设置用户的主所属组织</button>",
            buttonicon: "none"
        });
        // 设置用户的主所属组织
        $("#setDepartmentMainToUserBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要将选中的组织，设置为用户的主所属组织吗?", function(result) {
                    if(result) {
                        var departmentAthIdlist= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            departmentAthIdlist[i]=ret.DEPARTMENT_ATH_ID;
                        }
                        if(departmentAthIdlist.length>1){
                            addGritter("error", "只能给用户设定一个主所属组织");
                        }else{
                            // Set departmentMain to user
                            doPost("${ctx}/pcf/user/toSetDepartmentMainToUser?departmentAthIdList=" +departmentAthIdlist+"&userId=" +${user.userId}, function (data) {
                                //console.debug(data);
                                if (data.success) {
                                    addGritter("success", data.message);
                                    // Grid reload
                                    jQuery("#grid-table-departmentAth").trigger("reloadGrid");
                                } else {
                                    addGritter("error", data.message);
                                }
                            }, "json")
                        }
                    }
                });
            } else {
                addGritter("error", "请选择要设置为用户的主所属的组织");
            }
        });
        // 删除所属部门按钮
        gridTools.jqGrid("navButtonAdd", "#grid-pager-departmentAth", {
            id: "updateCase",
            caption: "<button id='deleteDepartmentBtn' class='btn btn-xs btn-primary'>删除所属组织</button>",
            buttonicon: "none"
        });
        // 删除用户的所属组织信息
        $("#deleteDepartmentBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的用户所属组织吗?", function(result) {
                    if(result) {
                        var departmentAthIdlist= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            departmentAthIdlist[i]=ret.DEPARTMENT_ATH_ID;
                        }
                        // delete department from user
                        doPost("${ctx}/pcf/user/toDeleteDepartmentAth?departmentAthIdList=" +departmentAthIdlist+"&userId=" +${user.userId}, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-departmentAth").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要删除的用户所属组织");
            }
        });
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
        
        // 全部的用户集合列表GRID
        var grid_selector_department = "#grid-table-department";
        var pager_selector_department = "#grid-pager-department";

        jQuery(grid_selector_department).jqGrid(jqGridOption({
            url:'${ctx}/pcf/department/list',
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '组织编号', '组织名'],
            colModel: [
                {name: 'DEPARTMENT_ID', index: 'DEPARTMENT_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'DEPARTMENT_CD', index: 'DEPARTMENT_CD', width: 660, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 660, sorttype: "string"}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector_department,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "部门列表"
        }));
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector_department).jqGrid('setGridWidth', $(".page-content").width());
        });

        // 添加所属部门
        var gridTools_department = $(grid_selector_department).jqGrid("navGrid", "#grid-pager-department", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools_department.jqGrid("navButtonAdd", "#grid-pager-department", {
            id: "addDepartmentToUser",
            caption: "<button id='addDepartmentToUserBtn' class='btn btn-xs btn-primary'>给用户添加所属部门</button>",
            buttonicon: "none"
        });
        // 给当前用户增加所属组织
        $("#addDepartmentToUserBtn").click(function () {
            var selrArray = jQuery(grid_selector_department).jqGrid('getGridParam', 'selarrrow');
            // 必须要有选择一条数据
            if (selrArray.length>0) {
                bootbox.confirm("您确认要给用户增加选中的组织吗?", function(result) {
                    if(result) {
                        var departmentIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector_department).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            departmentIdList[i]=ret.DEPARTMENT_ID;
                        }
                        // Add department to user
                        doPost("${ctx}/pcf/user/toAddDepartmentToUser?departmentIdList=" +departmentIdList+"&userId=" +${user.userId}, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-departmentAth").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要给用户增加的组织");
            }
        });
        
        // 当是用户还没有建立的时候，所属组织增加，所属组织删除的按钮为不可用
        if(${user.userId}=='0'){
            $('#deleteDepartmentBtn').attr('disabled',"true");
            $('#addDepartmentToUserBtn').attr('disabled',"true");
        }
    });
</script>
</body>
</html>