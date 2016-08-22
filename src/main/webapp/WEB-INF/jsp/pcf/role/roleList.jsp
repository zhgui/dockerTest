<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Dashboard - Ace Admin</title>

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
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">角色名称</label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入角色名称" id="resourceName"
                                   name="search.resource_name_like"/>
                        </div>
                    </div>
                </div>
                <%--<div class="col-md-4">--%>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label" for="form-field-1">Label</label>--%>
                <%--<div class="col-sm-9">--%>
                <%--<input type="text" class="form-control" placeholder="Username" id="form-field-1"/>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="role">角色标识</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="请输入角色标识" id="role"
                                   name="search.role_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="deleteFlag">角色状态</label>

                        <div class="col-sm-8">
                            <select class="form-control" id="deleteFlag" name="search.delete_flag_eq">
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
                            <button type="button" class="resetBtn btn btn-sm btn-info">
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
        <table id="grid-table"></table>
        <div id="grid-pager"></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">

    jQuery(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";

        jQuery(grid_selector).jqGrid(jqGridOption({
            url: '${ctx}/pcf/role/list',
            datatype: "json",
            height: 350,
//        height: "auto",
            shrinkToFit: false,
            colNames: [' ', '角色标识', '角色名称', '备注', '状态', '排序键', '更新时间'],
            colModel: [
                {name: 'ROLE_ID', index: 'ROLE_ID', width: 60, sorttype: "int", hidden: true},
                {name: 'ROLE', index: 'ROLE', width: 190, sorttype: "string"},
                {name: 'ROLE_NAME', index: 'ROLE_NAME', width: 150},
                {name: 'NOTES', index: 'NOTES', width: 370},
                {
                    name: 'DELETE_FLAG',
                    index: 'DELETE_FLAG',
                    width: 90,
                    formatter: "select",
                    editoptions: {value: "0:有效;1:无效"}
                },
                {name: 'SORT_KEY', index: 'SORT_KEY', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE'}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            pager: pager_selector,
            altRows: true,
            //toppager: true,
            multiselect: true,
            //multikey: "ctrlKey",
            multiboxonly: true,
            caption: "角色列表"

        }));
        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager", {
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: false,
            view: false
        });
        <shiro:hasPermission name="role:create">

        gridTools.jqGrid("navButtonAdd", "#grid-pager", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加角色</button>",
            buttonicon: "none"
        });
        </shiro:hasPermission>
        <shiro:hasPermission name="role:update">

        gridTools.jqGrid("navButtonAdd", "#grid-pager", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改角色</button>",
            buttonicon: "none"
        });
        </shiro:hasPermission>

        $("#addBtn").click(function () {
            document.location.href = "${ctx}/pcf/role/toAdd";
        });
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            if (selr) {
                var ret = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/pcf/role/toUpdate?roleId=" + ret.ROLE_ID;
            } else {
                addGritter("error", "请选择要修改的记录")
            }
        });
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });

        $(".resetBtn").on("click", function () {
            $(this).closest("form").find("input").val("");
            $(this).closest("form").find("select").val("");
        });
    });
</script>


</body>
</html>