<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>意见反馈列表</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <div class="row">
            	<!-- 
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入姓名" id="name" name="search.name_like"/>
                        </div>
                    </div>
                </div>
                 -->
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">联系方式</label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入联系方式" id="name" name="search.mobile_like"/>
		                </div>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">内容</label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入内容" id="content" name="search.content_like"/>
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
        <table id="grid-table-feedback"></table>
        <div id="grid-pager-feedback"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table-feedback";
        var pager_selector = "#grid-pager-feedback";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/feedback/feedbackByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: ['','联系方式','内容','手机型号','系统型号','系统类型','APP版本号','创建日'], 
            colModel: [
				{name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'mobile', index: 'mobile', width: 120, sorttype: "string",hidden:false},
                {name: 'content', index: 'content', width: 420, sorttype: "string",hidden:false},
                {name: 'mobile_model', index: 'mobile_model', width: 220, sorttype: "string",hidden:false},
                {name: 'system_model', index: 'system_model', width: 220, sorttype: "string",hidden:false},
                {name: 'mobile_type', index: 'mobile_type', width: 220, sorttype: "string",hidden:false},
                {name: 'app_number', index: 'app_number', width: 220, sorttype: "string",hidden:false},
                {name: 'create_time', index: 'create_time', width: 220, edittype: "string",hidden:false},
                
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "意见反馈查询列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-feedback", {edit: false, add: false, del: false, search: false, refresh: false, view: false});

        gridTools.jqGrid("navButtonAdd", "#grid-pager-feedback", {
            id: "viewCase",
            caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看反馈内容</button>",
            buttonicon: "none"
        });
       
     	// 查看
        $("#viewBtn").click(function () {
            
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行查看");
            	return false;
            }
            
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/buddha/server/feedback/feedbackView?id=" +row.id;
            } else {
                addGritter("error", "请勾选一条意见反馈");
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