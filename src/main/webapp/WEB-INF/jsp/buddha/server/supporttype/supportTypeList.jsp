<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>查询列表</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="name" name="search.name_like" style="width:150px"/>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-3" >
                    <div class="form-group" style="margin-bottom: 0px;">
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
        <table id="grid-table-supportType"></table>
        <div id="grid-pager-supportType"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table-supportType";
        var pager_selector = "#grid-pager-supportType";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/supporttype/getSupportTypeList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','简称','状态','创建人','创建时间','修改人','修改时间'], 
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 150, sorttype: "string",hidden:false},
                {name: 'sname', index: 'sname', width: 150, edittype: "string", hidden:false},
                {name: 'status', index: 'status', width: 100, edittype: "string",hidden:false},
                {name: 'createAccount', index: 'createAccount', width: 120, edittype: "string",hidden:false},
                {name: 'createTime', index: 'create_time', width: 160, edittype: "string",hidden:false},
                {name: 'modifyAccount', index: 'modifyAccount', width: 120, edittype: "string",hidden:false},
                {name: 'modifyTime', index: 'modifyTime', width: 160, edittype: "string",hidden:false}
                
            ],
            ondblClickRow:function(){
                $("#updateBtn").trigger("click");
            },
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "查询列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-supportType", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-supportType", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加类型</button>",
             buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-supportType", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改类型</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-supportType", {
            id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>删除类型</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/supporttype/supportTypeAdd";
        });
        // 修改
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行修改");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/buddha/server/supporttype/supportTypeEdit?id=" +row.id;
            } else {
                addGritter("error", "请选择您要修改的类型信息");
            }
        });
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的类型吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/supporttype/deleteSupportType?idList="+idList, function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-supportType").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要删除的类型");
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