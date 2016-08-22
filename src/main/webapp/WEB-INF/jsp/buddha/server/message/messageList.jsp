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

                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">主题</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入主题" id="title" name="search.title_like"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><nobr>发送号码</nobr></label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入发送号码" id="sendMobile" name="search.sendMobile_like"/>
		                </div>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><nobr>接收号码</nobr></label>
                        <div class="col-sm-9">
                         	<input type="text" class="form-control" placeholder="请输入接收号码" id="revMobile" name="search.revMobile_like"/>
		                </div>
                    </div>
                </div>

               <%-- <div class="col-md-3">
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
                </div>--%>
                
                <div class="col-md-3">
                    <div class="form-group"  style="margin-bottom: 0px;">
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
            url:'${ctx}/buddha/server/message/list',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: ['','主题','内容','发送号码','接收号码','发送状态','回执信息','创建时间'],
            colModel: [
				{name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'title', index: 'name', width: 120, sorttype: "string",hidden:false},
                {name: 'content', index: 'content', width: 360, sorttype: "string",hidden:false},
                {name: 'sendMobile', index: 'sendMobile', width: 200, sorttype: "string",hidden:false},
                {name: 'revMobile', index: 'revMobile', width: 200, sorttype: "string",hidden:false},
                {name: 'stat', index: 'stat', width: 100, sorttype: "string",hidden:false},
                {name: 'backMessage', index: 'backMessage', width: 220, sorttype: "string",hidden:false},
                {name: 'createTime', index: 'createTime', width: 220, sorttype: "string",hidden:false}
                
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "短信信息查询列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

       /* //添加按钮
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
        });*/

     	
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
    });
</script>
</body>
</html>