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
       <div  class="row">   
                <div class="col-md-3">
            		<div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="position">服务名称</label>
                		 <div class="col-sm-9">
                    			<input type="text" class="form-control" placeholder="请输入服务名称"  id="title" name="search.a.sName_like" />
               			 </div>
           			 </div>
                </div>
                
                                          
               <div class="col-md-3">
            		<div class="form-group">
               			 <label class="col-sm-3 control-label no-padding-right" for="degree">状态</label>
                		 <div class="col-sm-9">
                    			 <div class="col-sm-9">
		                	<select class="form-control" id="status" name="search.a.status_like">
		                		<option value="">请选择</option>
		                		<option value="2">完成</option>
		                    	<option value="1">新建</option>
		                    	<option value="0">取消</option>
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
            url:'${ctx}/buddha/server/order/orderByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: ['','用户昵称','下单人','手机号','订单编号','订单总金额','服务名称','服务类型','服务类型金额','服务人数','服务人员类型','服务人员金额',
                       '是否加急','加急金额','是否需要吉日','黄道吉日','吉日金额','回向人','回向内容','回向人电话','支付状态','状态','支付时间','创建时间','更新日期'], 
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'nickName', index: 'nickName', width: 100, sorttype: "string",hidden:false},
                {name: 'name', index: 'name', width: 100, sorttype: "string",hidden:false},
                {name: 'mobile', index: 'scriptureType', width: 100, sorttype: "string",hidden:false},
                {name: 'orderNo', index: 'orderNo', width: 120, edittype: "string", hidden:false},
                {name: 'sumMoney', index: 'sumMoney', width: 120, edittype: "string", hidden:false},
                {name: 'sName', index: 'sName', width: 120, edittype: "string", hidden:false},
                {name: 'serveTypeName', index: 'serveTypeName', width: 120, edittype: "string", hidden:false},
                {name: 'stMoney', index: 'stMoney', width: 120, edittype: "string", hidden:false},
                {name: 'servePersonNum', index: 'servePersonNum', width: 90, edittype: "Integer", hidden:false},
                {name: 'servePersonType', index: 'servePersonType', width: 120, edittype: "string",hidden:false},
                {name: 'sptMoney', index: 'sptMoney', width: 120, edittype: "string",hidden:false},
                {name: 'express', index: 'express', width: 120, edittype: "string",hidden:false},
                {name: 'eMoney', index: 'eMoney', width: 120, edittype: "string",hidden:false},
                {name: 'luck', index: 'luck', width: 120, edittype: "string",hidden:false},
                {name: 'luckDate', index: 'luckDate', width: 120, edittype: "string",hidden:false},
                {name: 'luckDateMoney', index: 'luckDateMoney', width: 120, edittype: "string",hidden:false},
                
                {name: 'backObject', index: 'backObject', width: 120, edittype: "string",hidden:false},
                {name: 'backContent',index:'backContent', width: 120, edittype: "string",hidden:false},
                {name: 'backMobile',index:'backMobile', width: 120, edittype: "string",hidden:false},
                
                {name: 'payStatus', index: 'payStatus', width: 120, edittype: "string", hidden:false},   
                {name: 'statusValue', index: 'statusValue', width: 120, edittype: "string", hidden:false},
               
                {name: 'payTime', index: 'payTime', width: 120, edittype: "string",hidden:false},
                {name: 'createTime', index: 'createTime', width: 120, edittype: "string",hidden:false},
                {name: 'modifyTime', index: 'modifyTime', width: 120, edittype: "string",hidden:false}
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
            id: "useCase",
            caption: "<button id='useBtn' class='btn btn-xs btn-primary'>完成订单</button>",
            buttonicon: "none"
        });
       
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "stopCase",
            caption: "<button id='stopBtn' class='btn btn-xs btn-primary'>取消订单</button>",
            buttonicon: "none"
        });
       
        // 停用
        $("#stopBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要取消选中的订单吗?", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/order/updateOrderStatus?idList=" +idList+"&status=0", function (data) {
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
                addGritter("error", "请勾选您要取消的订单");
            }
        });
        
        //启用
        $("#useBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要完成选中的订单吗?", function(result) {
                    if(result) {
                        var idList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            idList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/order/updateOrderStatus?idList=" +idList+"&status=2", function (data) {
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
                addGritter("error", "请勾选您要完成的经文");
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