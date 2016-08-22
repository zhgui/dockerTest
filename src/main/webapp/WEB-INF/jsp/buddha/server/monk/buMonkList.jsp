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
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="name" name="search.a.name_like"/>
                        </div>
                    </div>
                 </div>
                 <div class="col-md-3">
                    <div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="buddhistName">法号</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入法号"  id="buddhistName" name="search.a.buddhistName_like" />
               			 </div>
           		   </div>
                </div>
                <div class="col-md-3">
            		<div class="form-group">
		                <label class="col-sm-3 control-label no-padding-right" for="templeId">寺院</label>
		                <div class="col-sm-9">
		                     <input type="text" class="form-control" placeholder="请输入名称" id="b.name" name="search.b.name_like" style="width:150px"/>
		                </div>
            		</div>
                </div>
                
                
       </div>
       <div  class="row">   
                <div class="col-md-3">
            		<div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="position">职位</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入职位"  id="position" name="search.a.position_like" />
               			 </div>
           			 </div>
                </div>
                <div class="col-md-3">
            		<div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="ancestralHome">祖籍</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入祖籍"  id="ancestralHome" name="search.a.ancestralHome_like" />
               			 </div>
           			 </div>
                </div>
                
                <div class="col-md-3">
            		<div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="degree">学位</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入学位"  id="degree" name="search.a.degree_like" />
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
        <table id="grid-table-school"></table>
        <div id="grid-pager-school"></div>
    </div>
</div>
	
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">
    jQuery(function ($) {
    	getTempleList();
        var grid_selector = "#grid-table-school";
        var pager_selector = "#grid-pager-school";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/monk/buMonkByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','法号','寺院','','审核状态','手机号码',/*'','宗教类别','职位','戒纳','出生年月','籍贯','学历','皈依本师','戒师','',*/'状态','创建日期','更新日期','预览'],
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 120, sorttype: "string",hidden:false},
                {name: 'buddhistName', index: 'buddhistName', width: 120, edittype: "string", hidden:false},
                {name: 'templeName', index: 'templeName', width: 120, edittype: "string", hidden:false},
                {name: 'auditStatus', index: 'auditStatus', width: 120, edittype: "string", hidden:true},
                {name: 'auditStatusValue', index: 'auditStatusValue', width: 120, edittype: "string", hidden:false},
                {name: 'mobileNumber', index: 'mobileNumber', width: 120, edittype: "string", hidden:false},
                /*{name: 'religionType', index: 'religionType', width: 120, edittype: "Integer", hidden:true},
                {name: 'religionTypeStr', index: 'religionTypeStr', width: 120, edittype: "string", hidden:false},
                {name: 'position', index: 'position', width: 120, edittype: "string", hidden:false},
                {name: 'ringNa', index: 'ringNa', width: 120, edittype: "string", hidden:false},
                {name: 'born', index: 'born', width: 120, edittype: "string", hidden:false},
                {name: 'ancestralHome', index: 'ancestralHome', width: 120, edittype: "string", hidden:false},
                {name: 'degree', index: 'degree', width: 120, edittype: "string", hidden:false},
                {name: 'convertTeacher', index: 'convertTeacher', width: 120, sorttype: "string",hidden:false},
                {name: 'acariya', index: 'acariya', width: 120, sorttype: "string",hidden:false},
                {name: 'status', index: 'status', width: 120, edittype: "Integer", hidden:true},*/
                {name: 'statusValue', index: 'statusValue', width: 120, edittype: "string", hidden:false},
                {name: 'create_time', index: 'create_time', width: 200, edittype: "string",hidden:false},
                {name: 'update_time', index: 'update_time', width: 200, edittype: "string",hidden:false},
                {name: 'pvw', index: 'pvw', width: 120, edittype: "string",hidden:false,
                formatter:function(value,index,row){
                    return "<a href='"+monkUrl+row.id+"' target='_blank'><button type='button' class='queryBtn btn btn-sm btn-info'>" +
                            "<i class='ace-icon fa fa-search bigger-125'></i>" +
                            " 预览</button></a>";
                }}
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

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-school", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加法师</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改法师</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
           id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>删除法师</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "stopCase",
            caption: "<button id='stopBtn' class='btn btn-xs btn-primary'>停用法师</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "useCase",
            caption: "<button id='useBtn' class='btn btn-xs btn-primary'>启用法师</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "viewCase",
            caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看法师信息</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/monk/buMonkDetail?param=add";
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
                document.location.href = "${ctx}/buddha/server/monk/buMonkDetail?monkId=" +row.id+"&param=edit";
            } else {
                addGritter("error", "请选择您要修改的法师");
            }
        });
        
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的法师吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var monkIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            monkIdList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/monk/deleteMonk?monkIdList=" +monkIdList, function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-school").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要删除的法师");
            }
        });
        
        // 停用
        $("#stopBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要停用选中的法师吗?", function(result) {
                    if(result) {
                        var monkIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            monkIdList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/monk/updateMonkStatus?monkIdList=" +monkIdList+"&status=0", function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-school").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要停用的法师");
            }
        });
        
        //启用
        $("#useBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要启用选中的法师吗?", function(result) {
                    if(result) {
                        var monkIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            monkIdList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/monk/updateMonkStatus?monkIdList=" +monkIdList+"&status=1", function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-school").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                addGritter("error", "请勾选您要启用的法师");
            }
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
                document.location.href = "${ctx}/buddha/server/monk/buMonkView?monkId=" +row.id;
            } else {
                addGritter("error", "请选择您要查看的法师信息");
            }
        });
        
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
    });
    
    //获取寺庙
	function getTempleList(){
	    $.ajax({
	        url : '${ctx}/buddha/server/temple/getTempleList',
	        cache : true,
	        async : false,
	        data:{},
	        type : "POST",
	        dataType : 'json',
	        success : function (result) {
	        	var obj = result.responseData;
	            $("select[name='search.a.temple_id_eq']").empty();
	            $("select[name='search.a.temple_id_eq']").append("<option value=''>请选择</option>");
	            for (var i = 0; i < obj.length; i++) {
	                $("select[name='search.a.temple_id_eq']").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
	            }
	        },
	        error:function(result) {
	            alertError(result.message)
	        }
	    });
	}
</script>
</body>
</html>