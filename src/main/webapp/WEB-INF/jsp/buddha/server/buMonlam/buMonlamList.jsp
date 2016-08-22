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
<!--                  <div class="col-md-3">
                    <div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="mainName">主会人</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入法号"  id="mainName" name="search.a.mainName_like" />
               			 </div>
           		   </div>
                </div> -->
                <div class="col-md-3">
            		<div class="form-group">
		                <label class="col-sm-3 control-label no-padding-right" for="templeId">寺院</label>
		                <div class="col-sm-9">
		                     <input type="text" class="form-control" placeholder="请输入名称" id="b.name" name="search.b.name_like" style="width:150px"/>
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
<%--       <div  class="row">

            </div>--%>
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
            url:'${ctx}/buddha/server/buMonlam/buMonlamByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','内容','寺院','预计参加人数','实际参加人数','浏览数','地址','开始时间','结束时间','类型','是否推荐','状态',/*'发心','结缘',*/'创建者','创建日期','更新者','更新日期','预览'],
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 250, sorttype: "string",hidden:false},
                {name: 'content', index: 'content', width: 220, edittype: "string", hidden:true},
                {name: 'templeName', index: 'templeName', width: 120, edittype: "string", hidden:false},
                {name: 'sumNum', index: 'sumNum', width: 60, edittype: "Integer", hidden:true},
                {name: 'realNum', index: 'realNum', width: 60, edittype: "string", hidden:false},
                {name: 'browseNum', index: 'browseNum', width: 30, edittype: "string", hidden:false},
                {name: 'address', index: 'address', width: 180, edittype: "string", hidden:false},
                {name: 'monlamStartTime', index: 'monlamStartTime', width: 130, edittype: "string", hidden:false},
                {name: 'monlamEndTime', index: 'monlamEndTime', width: 130, edittype: "string", hidden:false},
                {name: 'type', index: 'type', width: 60, sorttype: "string",hidden:false},
                {name: 'isTj', index: 'isTj', width: 60, sorttype: "string",hidden:false},
                {name: 'statusValue', index: 'statusValue', width: 60, sorttype: "string",hidden:false},
//                {name: 'muom', index: 'muom', width: 120, sorttype: "string",hidden:false},
//                {name: 'sumMoney', index: 'sumMoney', width: 120, edittype: "Integer", hidden:false},
                {name: 'createAccount', index: 'createAccount', width: 80, edittype: "string", hidden:false},
                {name: 'createTime', index: 'createTime', width: 120, edittype: "string",hidden:false},
                {name: 'modifyAccount', index: 'modifyAccount', width: 80, edittype: "string", hidden:false},
                {name: 'modifyTime', index: 'modifyTime', width: 120, edittype: "string",hidden:false},
                {name: 'pvw', index: 'pvw', width: 100, edittype: "string",hidden:false,
                    formatter:function(value,index,row) {
                        if(row.type==1){
                            return "<a href='" + monlamActiveUrl + row.id + "' target='_blank'><button type='button' class='queryBtn btn btn-sm btn-info'>" +
                                    "<i class='ace-icon fa fa-search bigger-125'></i>" +
                                    " 预览</button></a>";
                        }else {
                            return "<a href='" + monlamDutyUrl + row.id + "' target='_blank'><button type='button' class='queryBtn btn btn-sm btn-info'>" +
                                    "<i class='ace-icon fa fa-search bigger-125'></i>" +
                                    " 预览</button></a>";
                        }
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
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加法会</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改法会</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
           id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>删除法会</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "viewCase",
            caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看法会信息</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/buMonlam/buMonlamDetail?param=add";
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
                document.location.href = "${ctx}/buddha/server/buMonlam/buMonlamDetail?buMonlamId=" +row.id+"&param=edit";
            } else {
                addGritter("error", "请选择您要修改的法会");
            }
        });
        
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的法会吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var buMonlamIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            buMonlamIdList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/buMonlam/deleteBuMonlam?buMonlamIdList=" +buMonlamIdList, function (data) {
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
                addGritter("error", "请勾选您要删除的法会");
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
                document.location.href = "${ctx}/buddha/server/buMonlam/buMonlamView?buMonlamId=" +row.id;
            } else {
                addGritter("error", "请选择您要查看的法会信息");
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
	            $("select[name='search.a.tId_eq']").empty();
	            $("select[name='search.a.tId_eq']").append("<option value=''>请选择</option>");
	            for (var i = 0; i < obj.length; i++) {
	                $("select[name='search.a.tId_eq']").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
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