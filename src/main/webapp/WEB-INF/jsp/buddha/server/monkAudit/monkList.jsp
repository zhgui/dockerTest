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
                <div class="col-md-3">
            		<div class="form-group">
		                <label class="col-sm-3 control-label no-padding-right" for="auditStatus">审核状态</label>
		                <div class="col-sm-9">
		                    <select id="auditStatus" name="search.a.auditStatus_eq" style="width:150px">
		                    		<option value="" selected="selected">请选择</option>
				                    <option value="0">未审核</option>
			                   <%--     <option value="1">审核通过</option>--%>
			                        <option value="2">审核未通过</option>
		                    </select>
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
            url:'${ctx}/buddha/server/monkAudit/getMonkList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','名称','法号','寺院','手机号码',/*'宗教类别',*/'职位','','审核状态','审核未通过原因',/*'戒纳',*//*'出生年月','籍贯','学历','皈依本师','戒师',*/'创建日期','更新日期','预览'],
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                {name: 'name', index: 'name', width: 120, sorttype: "string",hidden:false},
                {name: 'buddhistName', index: 'buddhistName', width: 120, edittype: "string", hidden:false},
                {name: 'templeName', index: 'templeName', width: 120, edittype: "string", hidden:false},
                {name: 'mobileNumber', index: 'mobileNumber', width: 120, edittype: "string", hidden:false},
                /*{name: 'religionTypeStr', index: 'religionTypeStr', width: 120, edittype: "string", hidden:false},*/
                {name: 'position', index: 'position', width: 120, edittype: "string", hidden:false},
                {name: 'auditStatus', index: 'auditStatus', width: 120, edittype: "string", hidden:true},
                {name: 'auditStatusValue', index: 'auditStatusValue', width: 120, edittype: "string", hidden:false},
                {name: 'auditRemark', index: 'auditRemark', width: 120, edittype: "string", hidden:false},
               /* {name: 'ringNa', index: 'ringNa', width: 120, edittype: "string", hidden:false},*/
                /*{name: 'born', index: 'born', width: 120, edittype: "string", hidden:false},
                {name: 'ancestralHome', index: 'ancestralHome', width: 120, edittype: "string", hidden:false},
                {name: 'degree', index: 'degree', width: 120, edittype: "string", hidden:false},
                {name: 'convertTeacher', index: 'convertTeacher', width: 120, sorttype: "string",hidden:false},
                {name: 'acariya', index: 'acariya', width: 120, sorttype: "string",hidden:false},*/
                {name: 'create_time', index: 'create_time', width: 200, edittype: "string",hidden:false},
                {name: 'update_time', index: 'update_time', width: 200, edittype: "string",hidden:false},
                {name: 'pvw', index: 'pvw', width: 120, edittype: "string",hidden:false,
                    formatter:function(value,index,row){
                        return "<a href='"+monkUrl+row.id+"' target='_blank'><button type='button' class='queryBtn btn btn-sm btn-info'>" +
                                "<i class='ace-icon fa fa-search bigger-125'></i>" +
                                " 预览</button></a>";
                    }}
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
        
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>自动审核</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "auditPassCase",
            caption: "<button id='auditPassBtn' class='btn btn-xs btn-primary'>审核通过</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "auditNotCase",
            caption: "<button id='auditNotBtn' class='btn btn-xs btn-primary'>审核不通过</button>",
            buttonicon: "none"
        });
        // 修改
        $("#updateBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行审核");
            	return false;
            }
            if (selr) {
                var row = jQuery(grid_selector).jqGrid('getRowData', selr);
                doPost("${ctx}/buddha/server/monkAudit/auditMonk?monkId=" +row.id, function (data) {
                    if (data.success) {
                        addGritter("success", data.message);
                        jQuery("#grid-table-school").trigger("reloadGrid");
                    } else {
                        addGritter("error", data.message);
                    }
                }, "json");
                //document.location.href = "${ctx}/buddha/server/monkAudit/monkAuditDetail?monkId=" +row.id+"&param=edit";
            } else {
                addGritter("error", "请选择您要审核的法师");
            }
        });
        $("#auditNotBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行审核");
            	return false;
            }
            if (selr) {
            	var row = jQuery(grid_selector).jqGrid('getRowData', selr);
            	if(row.auditStatus==2){
            		addGritter("warning", "法师状态已经是审核不通过,不能再次审核");
            		return false;
            	}
            	bootbox.dialog({
                    title : "法师审核",
                    message : "<div class='well ' style='margin-top:25px;'><form class='form-horizontal' role='form'><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='auditRemark'>不通过原因</label><div class='col-sm-9'><input type='text' id='auditRemark' name='auditRemark' placeholder='请输入审核不通过原因' class='col-xs-10 col-sm-9' /></div></div></form></div>",
                    buttons : {
                        "success" : {
                            "label" : "<i class='icon-ok'></i> 审核",
                            "className" : "btn-sm btn-success",
                            "callback" : function() {
                                var auditRemark = $("#auditRemark").val();
                                if(auditRemark == ""){
                                    bootbox.alert("审核不通过原因不能为空！");
                                    return false;
                                }
                                doPost("${ctx}/buddha/server/monkAudit/manualAuditMonk?monkId="+row.id+"&remark="+auditRemark+"&status=2", function (data) {
                                    if (data.success) {
                                    	addGritter("success", data.message);
                                        jQuery("#grid-table-school").trigger("reloadGrid");
                                    } else {
                                        addGritter("error", data.message);
                                    }
                                }, "json");
                               
                            }
                        },
                        "cancel" : {
                            "label" : "<i class='icon-info'></i> 取消",
                            "className" : "btn-sm btn-danger",
                            "callback" : function() { }
                        }
                    }
                });
            	
            } else {
                addGritter("error", "请选择您要审核的法师");
            }
        });
        
        
        $("#auditPassBtn").click(function () {
            var selr = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            if(selrArray.length>1){
            	addGritter("warning", "请选择一条,进行审核");
            	return false;
            }
            if (selr) {
            	var row = jQuery(grid_selector).jqGrid('getRowData', selr);
            	if(row.auditStatus==1){
            		addGritter("warning", "法师状态已经是审核通过,不能再次审核");
            		return false;
            	}
            	doPost("${ctx}/buddha/server/monkAudit/manualAuditMonk?monkId="+row.id+"&status=1", function (data) {
                    if (data.success) {
                    	addGritter("success", data.message);
                        jQuery("#grid-table-school").trigger("reloadGrid");
                    } else {
                        addGritter("error", data.message);
                    }
                }, "json");
            	
            } else {
                addGritter("error", "请选择您要审核的法师");
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
	            alertError(result.message);
	        }
	    });
	}
</script>
</body>
</html>