<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>查询列表</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
	<%@include file="/WEB-INF/jsp/common/import-My97DatePicker.jsp"%>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <div class="row">
                <div class="col-md-3" style="width:25%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入名称" id="name" name="search.a.name_like" style="width:150px"/>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-3" style="width:56%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" style="text-align: left;width:80px">地区选择</label>
                        <div class="col-sm-9" style="float:left;width:80%">
	                        <select id="provinceId" name="search.a.provinceid_eq" onchange="getCity()" style="width:150px">
	                            <option value=''>请选择</option>
	                        </select>
	                        <select id="cityId" name="search.a.cityid_eq" onchange="getCounty()" style="width:150px">
	                            <option value=''>请选择</option>
	                        </select>
	                        <select id="countyId" name="search.a.countyid_eq" onclick="getTown()" style="width:150px">
	                            <option value=''>请选择</option>
	                        </select>
                         </div>
                         
                    </div>
                </div>
             </div>
             <div class="row">   
             
                <div class="col-md-6" style="width:55%">
                    <div class="form-group" style="margin-bottom: 0px;">
                        <label class="col-sm-3 control-label no-padding-right">时间</label>
                        <div class="col-sm-9">
                            <input style="width:150px" type="text" name="startTimeVo" onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
							placeholder="开始时间" id="startTimeVo" value="" />至
							<input style="width:150px" type="text"  name="endTimeVo" onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
							placeholder="结束时间" id="endTimeVo"
							value="" />
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
            url:'${ctx}/buddha/server/temple/templeByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','地址','住持','省份','市','区(县)','发心','结缘'/*,'是否认证','是否推荐'*/,'状态','创建人','创建时间','修改人','修改时间','预览'],
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 100, sorttype: "string",hidden:false},
                {name: 'address', index: 'address', width: 200, edittype: "string", hidden:false},
                {name: 'buddhistName', index: 'buddhistName', width: 120, edittype: "string", hidden:false},
                {name: 'provincename', index: 'provincename', width: 100, edittype: "string", hidden:false},
                {name: 'cityname', index: 'cityname', width: 100, edittype: "string",hidden:false},
                {name: 'countyname', index: 'countyname', width: 100, edittype: "string",hidden:false},
                {name: 'muom', index: 'muom', width: 100, edittype: "string",hidden:false},
                {name: 'sumMoney', index: 'sumMoney', width: 100, edittype: "string",hidden:false},
                /*{name: 'isRz', index: 'isRz', width: 100, edittype: "string",hidden:false},
                {name: 'isTj', index: 'isTj', width: 100, edittype: "string",hidden:false},*/
                {name: 'status_value', index: 'status_value', width: 50, edittype: "string",hidden:false},
                {name: 'createuser', index: 'createuser', width: 80, edittype: "string",hidden:false},
                {name: 'create_time', index: 'create_time', width: 150, edittype: "string",hidden:false},
                {name: 'modifieruser', index: 'modifieruser', width: 80, edittype: "string",hidden:false},
                {name: 'update_time', index: 'update_time', width: 160, edittype: "string",hidden:false},
                {name: 'pvw', index: 'pvw', width: 120, edittype: "string",hidden:false,
                formatter:function(value,index,row){
            return "<a href='"+templeUrl+row.id+"' target='_blank'><button type='button' class='queryBtn btn btn-sm btn-info'>" +
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

        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-temple", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "addCase",
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加寺院</button>",
             buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改寺院</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-temple", {
            id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>删除寺院</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/temple/templeAdd";
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
                document.location.href = "${ctx}/buddha/server/temple/templeEdit?id=" +row.id;
            } else {
                addGritter("error", "请选择您要修改的寺院信息");
            }
        });
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的寺院吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/temple/deleteTemple?idList="+idList, function (data) {
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
                addGritter("error", "请勾选您要删除的寺院");
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