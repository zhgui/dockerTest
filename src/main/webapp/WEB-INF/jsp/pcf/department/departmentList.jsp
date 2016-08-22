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
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">组织编号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入组织编号" id="departmentCd" name="search.department_cd_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">组织名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="search.department_name_like" class="form-control" placeholder="请输入组织名称" id="form-field-1"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">组织状态</label>
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
                            <button type="reset" class="resetBtn btn btn-sm btn-info">
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
<div>
    <button id='toDepartmentTreeBtn' class='btn btn-xs btn-primary'>⇒显示组织树</button>
</div>
<div class="row">
    <div class="col-sm-12">
        <table id="grid-table-department"></table>
        <div id="grid-pager-department"></div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table-department";
        var pager_selector = "#grid-pager-department";

        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/department/list',
            datatype: "json",
            height: 321,
            shrinkToFit: false,
            colNames: [' ', '组织编号', '组织名', '电子邮箱','最后更新者', '更新时间', '状态'],
            colModel: [
                {name: 'DEPARTMENT_ID', index: 'DEPARTMENT_ID', width: 160, sorttype: "int",hidden:true},
                {name: 'DEPARTMENT_CD', index: 'DEPARTMENT_CD', width: 160, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 290, sorttype: "string"},
                {name: 'EMAIL_ADDRESS1', index: 'EMAIL_ADDRESS1', width: 250},
                {name: 'RECORD_USER_CD', index: 'RECORD_USER_CD', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "组织列表"

        }));
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        // 添加组织按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-department", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-department", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加组织</button>",
            buttonicon: "none"
        });
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toAdd";
        });
         // 更新组织按钮
        gridTools.jqGrid("navButtonAdd", "#grid-pager-department", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改组织</button>",
            buttonicon: "none"
        });
        $("#updateBtn").click(function () {
            //console.debug("开始修改组织数据");
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            //console.log(selr);
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                //console.log(row);
                //console.debug(row);
                document.location.href = "${ctx}/pcf/department/toUpdate?departmentId=" +row.DEPARTMENT_ID+"&parentDepartmentId=" +row.DEPARTMENT_ID;
            } else {
                addGritter("error", "请选择要修改的记录");
            }
        });
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
        // 页面跳转到组织树页面
        $("#toDepartmentTreeBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toTreeList";
        });
    });
</script>
</body>
</html>