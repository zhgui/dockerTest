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
                <div class="col-md-3" style="width:15%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="name" name="search.a.name_like" style="width:150px"/>
                        </div>
                    </div>
                </div>  
                
                <div class="col-md-3" style="width:15%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">寺院名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="b.name" name="search.b.name_like" style="width:150px"/>
                        </div>
                    </div>
                </div> 
                
                <div class="col-md-3" style="width:15%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">供养类型名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="c.name" name="search.c.name_like" style="width:150px"/>
                        </div>
                    </div>
                </div>         
                 
                
                <div class="col-md-3" style="width:15%">
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
        <table id="grid-table-temple"></table>
        <div id="grid-pager-temple"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
    	initProvinces();
        var grid_selector = "#grid-table-temple";
        var pager_selector = "#grid-pager-temple";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/templesupport/templeSupportByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','金额','','寺院名称','','供养类型','发心','结缘','','状态','创建人','创建时间','修改人','修改时间'], 
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 150, sorttype: "string",hidden:false},
                {name: 'money', index: 'money', width: 150, sorttype: "string",hidden:false},
                {name: 'tId', index: 'tId', width: 150, sorttype: "int",hidden:true},
                {name: 'tName', index: 'tName', width: 150, sorttype: "string",hidden:false},
                {name: 'sId', index: 'sId', width: 150, sorttype: "int",hidden:true},
                {name: 'sName', index: 'sName', width: 150, sorttype: "string",hidden:false},
                {name: 'muom', index: 'muom', width: 150, edittype: "string",hidden:false},
                {name: 'sumMoney', index: 'sumMoney', width: 150, edittype: "string",hidden:false},
                {name: 'status', index: 'status', width: 50, edittype: "int",hidden:true},
                {name: 'statusValue', index: 'statusValue', width: 150, edittype: "string",hidden:false},
                {name: 'createAccount', index: 'createAccount', width: 150, edittype: "string",hidden:false},
                {name: 'createTime', index: 'createTime', width: 150, edittype: "string",hidden:false},
                {name: 'modifyAccount', index: 'modifyAccount', width: 150, edittype: "string",hidden:false},
                {name: 'modifyTime', index: 'modifyTime', width: 160, edittype: "string",hidden:false}
                
            ],
            ondblClickRow:function(){
                $("#updateBtn").trigger("click");
            },
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: false,
            multiselect: true,
            multiboxonly: true,
            caption: "查询列表"
        }));
        	
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-temple", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加</button>",
             buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "useCase",
            caption: "<button id='useBtn' class='btn btn-xs btn-primary'>启用</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "stopCase",
            caption: "<button id='stopBtn' class='btn btn-xs btn-primary'>禁用</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/templesupport/templeSupportAdd";
        });
        // 修改
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条记录,进行修改");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                document.location.href = "${ctx}/buddha/server/templesupport/templeSupportEdit?id=" +row.id;
            } else {
                addGritter("error", "请选择您要修改的记录");
            }
        });
        //启用
        $("#useBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个启用的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要更新选中的记录吗?", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/templesupport/updateTempleSupportStatus?idList="+idList+"&status=1", function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-temple").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要启用的记录");
            }
        });
        
        //停用
        $("#stopBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个停用的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要停用选中的记录吗?", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/templesupport/updateTempleSupportStatus?idList="+idList+"&status=0", function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-temple").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要停用的记录");
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