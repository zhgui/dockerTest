<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>任务信息列表</title>

    <meta name="description" content="任务信息列表"/>
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
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">任务名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入任务名称" id="viewName" name="search.view_name_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">规则描述</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入规则描述" id="groups" name="search.time_expression_name_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">状态</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="statuss" name="search.status_eq">
                            	<option value="">请选择</option>
                                <option value="1">启用</option>
                                <option value="2">停用</option>
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
        <table id="grid-table-news"></table>
        <div id="grid-pager-news"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table-news";
        var pager_selector = "#grid-pager-news";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/task/taskByConsList',
            datatype: "json",
            height: 321,
            shrinkToFit: false,
            colNames: [' ', '','任务名称', '分组','','状态','类型','时间表达式','规则描述'],
            colModel: [
                {name: 'ID', index: 'ID', width: 60, sorttype: "int",hidden:true},
                {name: 'NAME', index: 'NAME', width: 190, sorttype: "string",hidden:true},
                {name: 'VIEW_NAME', index: 'VIEW_NAME', width: 190, sorttype: "string",hidden:false},
                {name: 'GROUPS', index: 'GROUPS', width: 120, sorttype: "string",hidden:false},
                {name: 'STATUS', index: 'STATUS', width: 120, edittype: "int", hidden:true},
                {name: 'STATUS_VALUE', index: 'STATUS_VALUE', width: 120, edittype: "string", hidden:false},
                {name: 'TYPE', index: 'TYPE', width: 120, edittype: "int",hidden:true},
                {name: 'TIME_EXPRESSION_VALUE', index: 'TIME_EXPRESSION_VALUE', width: 190, sorttype: "string", hidden:true},
                {name: 'TIME_EXPRESSION_NAME', index: 'TIME_EXPRESSION_NAME', width: 490, sorttype: "string", hidden:false},
                
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "任务信息列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-news", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-news", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加任务</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-news", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改任务</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-news", {
            id: "viewCase",
            caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看任务</button>",
            buttonicon: "none"
        });

        // 增加任务
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/pcf/task/taskAdd";
        });
        // 修改任务
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行修改");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/pcf/task/taskEditor?taskId=" +row.ID;
            } else {
                addGritter("error", "请选择您要修改的任务");
            }
        });
        // 查看任务
        $("#viewBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行查看");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/pcf/task/taskDetail?taskId=" +row.ID;
            } else {
                addGritter("error", "请选择您要查看的任务");
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