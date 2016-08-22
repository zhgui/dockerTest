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
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">寺院名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入寺院名称" id="templeName" name="search.c.name_like" />
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入用户名称" id="realName" name="search.b.real_name_like"/>
                        </div>
                    </div>
                </div>
                </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">类型</label>
                        <div class="col-sm-9">
                        	<select class="form-control" id="type" name="search.a.type_like">
		                		<option value="">请选择</option>
		                		<option value="1">法师</option>
		                    	<option value="2">寺院</option>
		                    	<option value="3">善筹</option>
		                    	<option value="4">活动</option>
		                    	<option value="5">其它</option>
		                    </select>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">状态</label>
                        <div class="col-sm-9">
                        	<select class="form-control" id="status" name="search.a.status_like">
		                		<option value="">请选择</option>
		                		<option value="1">已完成</option>
		                    	<option value="0">待确认</option>
		                    </select>
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
            url:'${ctx}/buddha/server/templeChargeFlow/getTempleChargeFlow',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','供养时间','交易时间','用户名称','用户手机号码','寺院名称','','类型','供养金额','商户交易号'],
            colModel: [
                {name: 'flowId', index: 'flowId', width: 150, sorttype: "int",hidden:true},
                {name: 'createTime', index: 'createTime', width: 200, edittype: "string",hidden:false},
                {name: 'transDate', index: 'transDate', width: 200, edittype: "string",hidden:false},
                {name: 'realName', index: 'realName', width: 150, sorttype: "string",hidden:false},
                {name: 'name', index: 'name', width: 200, edittype: "string", hidden:false},
                {name: 'templeName', index: 'templeName', width: 200, edittype: "string", hidden:false},
                {name: 'type', index: 'type', width: 150, sorttype: "int",hidden:true},
                {name: 'typeValue', index: 'typeValue', width: 150, sorttype: "int",hidden:true},
                {name: 'chargeMoney', index: 'chargeMoney', width: 200, edittype: "string",hidden:false},
                {name: 'tradeNo', index: 'tradeNo', width: 300, edittype: "string",hidden:false}

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

        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
        
    });
</script>
</body>
</html>