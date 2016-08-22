<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>随喜列表</title>

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
                        <label class="col-sm-3 control-label no-padding-right"><nobr>随喜名称</nobr></label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入随喜名称" id="name" name="search.name_like"/>
		                </div>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><nobr>随喜金额</nobr></label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入随喜金额" id="content" name="search.money_like"/>
		                </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">状态</label>
                        <div class="col-sm-9">
                            <div class="col-sm-9">
                                <select class="form-control" id="status" name="search.status_like">
                                    <option value="">请选择</option>
                                    <option value="1">启用</option>
                                    <option value="0">禁用</option>
                                </select>
                            </div>
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
            url:'${ctx}/buddha/server/amodana/list',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: ['','随喜名称','随喜金额','备注','状态','创建人','创建时间'],
            colModel: [
				{name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 220, sorttype: "string",hidden:false},
                {name: 'money', index: 'money', width: 200, sorttype: "string",hidden:false},
                {name: 'remark', index: 'remark', width: 320, sorttype: "string",hidden:false},
                {name: 'statusValue', index: 'statusValue', width: 220, sorttype: "string",hidden:false},
                {name: 'createAccount', index: 'createAccount', width: 220, sorttype: "string",hidden:false},
                {name: 'createTime', index: 'createTime', width: 220, sorttype: "string",hidden:false}
                
            ],
            ondblClickRow:function(){
                $("#saveUpdate").trigger("click");
            },
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "随喜管理查询列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-feedback", {edit: false, add: false, del: false, search: false, refresh: false, view: false});

        gridTools.jqGrid("navButtonAdd", "#grid-pager-feedback", {
            id: "viewCase",
            caption: "<button id='saveBtn' class='btn btn-xs btn-primary'>添加</button>",
            buttonicon: "none"
        });

        gridTools.jqGrid("navButtonAdd", "#grid-pager-feedback", {
            id: "updateCase",
            caption: "<button id='saveUpdate' class='btn btn-xs btn-primary'>编辑</button>",
            buttonicon: "none"
        });


       
     	// 查看
        $("#saveUpdate").click(function () {
            
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行编辑");
            	return false;
            }
            
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/buddha/server/amodana/update?id=" +row.id;
            } else {
                addGritter("error", "请勾选一条随喜记录");
            }
        });

// 增加
        $("#saveBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/amodana/toDetail";
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