<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Dashboard - Ace Admin</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="page-header">
	<label>
		权限列表
	</label>
	<shiro:hasPermission name="permission:create">
	<button type="button" class="btn btn-white  pull-right" onclick="window.location.href='${ctx}/pcf/permission/toAdd'"><i class="ace-icon fa fa-plus"></i>添加权限</button>
     </shiro:hasPermission>
    <button type="button" onclick = "exportData()" class="btn btn-white  pull-right"><i class="ace-icon fa fa-download"></i>导出</button>
   
</div>

<div id="navbar" class="navbar navbar-default" style="background:#ffffff;min-height: 1px;"></div>
<div class="page-content" style="padding-left:5px;padding-right:5px;">
<div class="page-content-area">
<div class="row">
    <div class="col-sm-12">
        <form id = "permissionSearchForm" class="form-horizontal well well-sm queryForm">
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">权限名称</label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="请输入权限名称" id="resourceName"
                                   name="search.permission_name_like"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">权限标识</label>

                        <div class="col-sm-8">
                            <input type="text" name="search.permission_like" class="form-control" placeholder="请输入权限标识"
                                   id="form-field-1"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="form-field-1">权限状态</label>

                        <div class="col-sm-8">
                            <select class="form-control" id="form-field-select-1" name="search.delete_flag_eq">
                                <option value="">所有</option>
                                <option value="1">无效</option>
                                <option value="0">有效</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">

                            <button type="button" class="queryBtn btn btn-white">
                                <i class="ace-icon fa fa-search bigger-125"></i>
                                查询
                            </button>
                            <button type="button" class="resetBtn btn btn-white">
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
    <div class="col-xs-12">
        <table id="grid-table"></table>
        <div id="grid-pager"></div>
    </div>
</div>
</div>
</div><!-- /.page-content -->
<script type="text/javascript">
   function exportData(){
	     var elemIF = document.createElement("iframe");
         elemIF.src = '${ctx}/pcf/permission/listExport?' + $("#permissionSearchForm").formSerialize();
         elemIF.style.display = "none";
         document.body.appendChild(elemIF);
   }

    jQuery(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });
        jQuery(grid_selector).jqGrid(jqGridOption({
            url: '${ctx}/pcf/permission/list',
            datatype: "json",
            height: "350",
//        height: "auto",
            //shrinkToFit: false,
            colNames: [' 操作','权限名称', '权限标识', '权限描述', '排序', '修改时间', '状态'],
            colModel: [
                
                {name: 'PERMISSION_ID', index: 'PERMISSION_ID', width: 80,fixed:true, sortable:false,
                	formatter:function(cellvalue, options, rowObject){
                		var operate=[];
                		var oneOperateEdit={"operateType":"edit","url":"${ctx}/pcf/permission/toUpdate?permissionId=" + cellvalue};
                		var oneOperateDelete={"operateType":"delete","url":"${ctx}/pcf/permission/toDelete?permissionId="+ cellvalue,"message":"您确认要彻底删除选中的权限吗?彻底删除以后将无法恢复","tableId":grid_selector};
                		//查看权限详情时,可以有两种打开方式 一种是daliog方式,此种方式打开的详情不需要显示详情中的返回按钮. 第二种直接跳转到详情页面,此时需要显示详情页面上的返回按钮返回权限列表页面. flag=1:隐藏返回按钮 flag!=1的情况下都显示返回按钮.
                		var oneOperateDetail={"operateType":"detail","url":"${ctx}/pcf/permission/toDetail?flag=1&permissionId=" + cellvalue,title:"权限名称:"+rowObject.PERMISSION_NAME,height:"600px",size:"large",target:""};
                		<shiro:hasPermission name="permission:update">
                		operate.push(oneOperateEdit);
                		</shiro:hasPermission>
                		operate.push(oneOperateDelete);
                		operate.push(oneOperateDetail);
						return jqGridAddButton(operate);
					}
                },
                {name: 'PERMISSION_NAME', index: 'PERMISSION_NAME', width: 150, sorttype: "string"},
                {name: 'PERMISSION', index: 'PERMISSION', width: 150},
                {name: 'NOTES', index: 'NOTES', width: 170},
                {name: 'SORT_KEY', index: 'SORT_KEY', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {
                    name: 'DELETE_FLAG',
                    index: 'DELETE_FLAG',
                    width: 90,
                    formatter: "select",
                    editoptions: {value: "0:有效;1:无效"}
                }
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            //toppager: true,
            multiselect: false,
            //multikey: "ctrlKey",
            multiboxonly: true,
            caption: "权限列表"
        }));
    

        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager", {
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: false,
            view: false
        });
        <shiro:hasPermission name="permission:update">
        </shiro:hasPermission>
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/pcf/permission/toAdd";
        });
        //查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });

        $(".resetBtn").on("click", function () {

            $(this).closest("form").find("input").val("");
            $(this).closest("form").find("select").val("");
        });
    });
</script>


</body>
</html>