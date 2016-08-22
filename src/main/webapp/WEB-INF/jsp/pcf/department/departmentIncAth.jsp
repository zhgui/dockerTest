<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>组织检索</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div>
    <button id='toDepartmentTreeBtn' class='btn btn-xs btn-primary'>⇒显示组织树</button>
</div>
<div class="row" style="height: 800px;">
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-departmentIncAth"></table>
        <div id="grid-pager-departmentIncAth"></div>
    </div>
    <div class="col-sm-12" style="height: 400px;">
        <table id="grid-table-department"></table>
        <div id="grid-pager-department"></div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        // 组织内包GRID
        var grid_selector_departmentIncAth = "#grid-table-departmentIncAth";
        var pager_selector_departmentIncAth = "#grid-pager-departmentIncAth";

        jQuery(grid_selector_departmentIncAth).jqGrid(jqGridOption({
            url:'${ctx}/pcf/department/departmentIncAthList?parentDepartmentId=' +${department.departmentId},
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '组织编号', '组织名', '电子邮箱','最后更新者', '更新时间', '状态'],
            colModel: [
                {name: 'DEPARTMENT_ID', index: 'DEPARTMENT_ID', width: 160, sorttype: "int",hidden:true},
                {name: 'DEPARTMENT_CD', index: 'DEPARTMENT_CD', width: 160, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 290, sorttype: "string"},
                {name: 'EMAIL_ADDRESS1', index: 'EMAIL_ADDRESS1', width: 250},
                {name: 'RECORD_USER_CD', index: 'RECORD_USER_CD', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90, edittype: "select", editoptions: {value: "0:无效;1:有效"}}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector_departmentIncAth,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "现包含组织列表"

        }));
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector_departmentIncAth).jqGrid('setGridWidth', $(".page-content").width());
        });

        // 工具栏
        var gridTools_departmentIncAth = $(grid_selector_departmentIncAth).jqGrid("navGrid", "#grid-pager-departmentIncAth", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
         // 移除所属组织按钮
         /** 暂时不显示所属组织删除按钮
        gridTools_departmentIncAth.jqGrid("navButtonAdd", "#grid-pager-departmentIncAth", {
            id: "deleteDepartmentIncAth",
            caption: "<button id='deleteDepartmentIncAthBtn' class='btn btn-xs btn-primary'>移除所属组织</button>",
            buttonicon: "none"
        });
        $("#deleteDepartmentIncAthBtn").click(function () {
            var selrArray = jQuery(grid_selector_departmentIncAth).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的所属内包组织吗?", function(result) {
                    if(result) {
                        var departmentIdlist= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector_departmentIncAth).jqGrid('getRowData', selrArray[i]);
                            console.debug(ret);
                            departmentIdlist[i]=ret.DEPARTMENT_ID;
                        }
                        // delete department from user
                        doPost("${ctx}/pcf/department/toDeleteDepartmentIncAth?departmentIdList=" +departmentIdList, function (data) {
                            console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // 组织所属内包GRID数据刷新
                                jQuery("#grid_selector_departmentIncAth").trigger("reloadGrid");
                                // 组织GRID数据刷新
                                jQuery("#grid_selector_department").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要删除的所属内包组织");
            }
        });
        */
        // 组织GRID
        var grid_selector_department = "#grid-table-department";
        var pager_selector_department = "#grid-pager-department";

        jQuery(grid_selector_department).jqGrid(jqGridOption({
            url:'${ctx}/pcf/department/departmentListWithOutIncAth',
            datatype: "json",
            height: 250,
            shrinkToFit: false,
            colNames: [' ', '组织编号', '组织名', '电子邮箱','最后更新者', '更新时间', '状态'],
            colModel: [
                {name: 'DEPARTMENT_ID', index: 'DEPARTMENT_ID', width: 160, sorttype: "int",hidden:true},
                {name: 'DEPARTMENT_CD', index: 'DEPARTMENT_CD', width: 160, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 290, sorttype: "string"},
                {name: 'EMAIL_ADDRESS1', index: 'EMAIL_ADDRESS1', width: 250},
                {name: 'RECORD_USER_CD', index: 'RECORD_USER_CD', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90, edittype: "select", editoptions: {value: "0:无效;1:有效"}}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector_department,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "无所属组织列表"

        }));
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector_department).jqGrid('setGridWidth', $(".page-content").width());
        });

        // 工具栏
        var gridTools_department = $(grid_selector_department).jqGrid("navGrid", "#grid-pager-department", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
         // 增加所属组织内包按钮
        gridTools_department.jqGrid("navButtonAdd", "#grid-pager-department", {
            id: "addDepartmentToIncAth",
            caption: "<button id='addDepartmentToIncAthBtn' class='btn btn-xs btn-primary'>给组织添加子组织</button>",
            buttonicon: "none"
        });
        $("#addDepartmentToIncAth").click(function () {
            var selrArray = jQuery(grid_selector_department).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个增加的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要将选中的组织作为子组织添加给当前组织吗?", function(result) {
                    if(result) {
                        var departmentIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector_department).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            departmentIdList[i]=ret.DEPARTMENT_ID;
                        }
                        // delete department from user
                        doPost("${ctx}/pcf/department/toAddDepartmentToIncAth?departmentIdList=" +departmentIdList+"&parentDepartmentId=" +${department.departmentId}, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // 组织所属内包GRID数据刷新
                                jQuery("#grid-table-departmentIncAth").trigger("reloadGrid");
                                // 组织GRID数据刷新
                                jQuery("#grid-table-department").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要添加的组织");
            }
        });
        
        // 页面跳转到组织树页面
        $("#toDepartmentTreeBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toTreeList";
        });
    });
</script>
</body>
</html>