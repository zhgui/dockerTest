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
                        <label class="col-sm-3 control-label no-padding-right">善信名称</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入善信名称" id="name" name="search.a.name_like"/>
                        </div>
                    </div>
                </div>
                
                 <div class="col-md-4">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">善信账号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入善信账号" id="uniqueCode" name="search.a.user_cd_like"/>
                        </div>
                    </div>
                </div>
              </div>
            <div class="row">
                 <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">燃香时长</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入燃香时长" id="useTime" name="search.b.use_time_eq"/>
                        </div>
                    </div>
                </div>
                 <div class="col-md-4">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">功德币</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入功德币" id="virtualGold" name="search.b.virtual_gold_eq"/>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-5">
                    <div class="form-group">
                        <div class="col-md-5">
                            <button type="button" class="queryBtn btn btn-sm btn-info">
                                <i class="ace-icon fa fa-search bigger-125"></i>
                                查询
                            </button>
                            <button class="resetBtn btn btn-sm btn-info" type="reset">
                                <i class="ace-icon fa fa-undo bigger-125"></i>
                                重置
                            </button>
                            <%--<button id='exportBtnC' type="button" class="btn btn-sm btn-info">
                                <i class="ace-icon fa fa-download bigger-125"></i>
                                导出用户燃香记录明细列表
                            </button>--%>
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
        var grid_selector = "#grid-table-school";
        var pager_selector = "#grid-pager-school";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/joss/buUserJossByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','创建日期','善信账号','善信名称','唯一标识码','手机号码','点香时间','燃香时长(秒)','功德币'],
            colModel: [
                {name: 'userId', index: 'userId', width: 60, sorttype: "int",hidden:true},
                {name: 'create_time', index: 'create_time', width: 220, edittype: "string",hidden:false},
                {name: 'userCd', index: 'userCd', width: 220, sorttype: "string",hidden:false},
                {name: 'realName', index: 'realName', width: 220, sorttype: "string",hidden:false},
                {name: 'unique_code', index: 'unique_code', width: 320, edittype: "string", hidden:true},
                {name: 'mobileNumber', index: 'mobileNumber', width: 280, edittype: "string", hidden:false},
                {name: 'start_time', index: 'start_time', width: 220, edittype: "string", hidden:false},
                {name: 'use_time', index: 'use_time', width: 220, edittype: "string", hidden:false},
                {name: 'virtual_gold', index: 'virtual_gold', width: 220, edittype: "string", hidden:false}
                
            ],
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
        

        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/ctcapp/server/school/schoolAdd";
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
                document.location.href = "${ctx}/ctcapp/server/school/schoolEditor?schoolId=" +row.school_id;
            } else {
                addGritter("error", "请选择您要修改的学校信息");
            }
        });
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的学校吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var schoolIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            schoolIdList[i]=ret.school_id;
                        }
                        doPost("${ctx}/ctcapp/server/school/deleteSchool?schoolIdList=" +schoolIdList, function (data) {
                            if (data.success) {
                                addGritter("success", data.message);
                                jQuery("#grid-table-school").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请勾选您要删除的学校");
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
                document.location.href = "${ctx}/ctcapp/server/school/schoolView?schoolId=" +row.school_id;
            } else {
                addGritter("error", "请选择您要查看的学校信息");
            }
        });	

        $("#exportBtnC").click(function () {
       		var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');          
            var name = $("#name").val();
            var uniqueCode = $("#uniqueCode").val();
            var useTime = $("#useTime").val();
            var virtualGold = $("#virtualGold").val();
            document.location.href = "${ctx}/buddha/server/joss/expertUserJossDetailData?name="+name+"&uniqueCode="+uniqueCode+"&virtualGold="+virtualGold+"&useTime="+useTime;
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