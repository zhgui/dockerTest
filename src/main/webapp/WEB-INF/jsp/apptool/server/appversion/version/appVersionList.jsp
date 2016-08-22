<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>App版本列表</title>

    <meta name="description" content="App版本列表"/>
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
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">App名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入App名称" id="titless" name="search.app_name_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">App类型</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="statuss" name="search.app_type_eq">
                            	<option value="">请选择</option>
                                <option value="1">安卓</option>
                                <option value="2">苹果IOS</option>
                                </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">状态</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="statuss" name="search.app_status_eq">
                            	<option value="">请选择</option>
                                <option value="0">无效</option>
                                <option value="1">有效</option>
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
            url:'${ctx}/apptool/server/appversion/version/appVersionByConsList',
            datatype: "json",
            height: 321,
            shrinkToFit: false,
            colNames: [' ', 'APP名称', '', 'APP类型','','应用类型','版本号','URL','','状态','','更新机制','日期'],
            colModel: [
                {name: 'APP_VERSION_ID', index: 'APP_VERSION_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'APP_NAME', index: 'APP_NAME', width: 190, sorttype: "string"},
                {name: 'APP_TYPE', index: 'APP_TYPE', width: 120, edittype: "int", hidden:true},
                {name: 'APP_TYPE_VALUE', index: 'APP_TYPE_VALUE', width: 120, edittype: "string", hidden:false},
                {name: 'BUSINESS_TYPE', index: 'BUSINESS_TYPE', width: 120, edittype: "int", hidden:true},
                {name: 'BUSINESS_TYPE_VALUE', index: 'BUSINESS_TYPE_VALUE', width: 120, edittype: "int", hidden:false},
                {name: 'VERSION_NO', index: 'VERSION_NO', width: 120, edittype: "string",hidden:false},
                {name: 'URL', index: 'URL', width: 620, edittype: "string",hidden:false},
                {name: 'APP_STATUS', index: 'APP_STATUS', width: 120, edittype: "int",hidden:true},
                {name: 'APP_STATUS_VALUE', index: 'APP_STATUS_VALUE', width: 120, edittype: "string",hidden:false},
                {name: 'UPDATE_MECHANISM', index: 'UPDATE_MECHANISM', width: 120, edittype: "int",hidden:true},
                {name: 'UPDATE_MECHANISM_VALUE', index: 'UPDATE_MECHANISM_VALUE', width: 120, edittype: "string",hidden:false},
                {name: 'MODIFY_TIME', index: 'MODIFY_TIME', width: 220, edittype: "string",hidden:false},
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "App版本列表"
        }));
        	
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-news", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-news", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加版本</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-news", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改版本</button>",
            buttonicon: "none"
        });

        // 增加版本
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/apptool/server/appversion/version/appVersionAdd";
        });
        // 修改版本
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行修改");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/apptool/server/appversion/version/appVersionEditor?appVersionId=" +row.APP_VERSION_ID;
            } else {
                addGritter("error", "请选择您要修改的版本");
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