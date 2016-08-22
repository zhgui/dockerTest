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
                            <input type="text" class="form-control" placeholder="请输入善信名称" id="real_name" name="search.ud.real_name_like"/>
                        </div>
                    </div>
                </div>
                
                 <div class="col-md-4">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">善信账号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入善信账号" id="userCd" name="search.a.user_Cd_like"/>
                        </div>
                    </div>
                </div>
              </div>  
              <div class="row">
                 <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">功德币</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入功德币" id="virtualGold" name="search.virtual_gold_eq"/>
                        </div>
                    </div>
                </div>

                  <div class="col-md-4">
                      <div class="form-group">
                          <label class="col-sm-3 control-label no-padding-right">供养金额</label>
                          <div class="col-sm-7">
                              <input type="text" class="form-control" placeholder="请输入供养金额" id="money" name="search.b.money_like"/>
                          </div>
                      </div>
                  </div>

                <div class="col-md-5">
                    <div class="form-group"  style="margin-bottom: 0px;">
                        <div class="col-md-5">
                            <button type="button" class="queryBtn btn btn-sm btn-info">
                                <i class="ace-icon fa fa-search bigger-125"></i>
                                查询
                            </button>
                            <button class="resetBtn btn btn-sm btn-info" type="reset">
                                <i class="ace-icon fa fa-undo bigger-125"></i>
                                重置
                            </button>
                           <%-- <button id='exportBtnC' type="button" class="btn btn-sm btn-info">
                                <i class="ace-icon fa fa-download bigger-125"></i>
                                导出用户功德币列表
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
            url:'${ctx}/buddha/server/user/buUserByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','注册时间','善信账号','善信名称','善信昵称','注册来源','唯一标识码','手机号码','功德币','捐赠金额(元)','赠送金额(元)','可用金额(元)','消费金额(元)'],
            colModel: [
                {name: 'userId', index: 'userId', width: 60, sorttype: "int",hidden:true},
                {name: 'create_time', index: 'create_time', width: 220, edittype: "string",hidden:false},
                {name: 'userCd', index: 'userCd', width: 120, sorttype: "string",hidden:false},
                {name: 'realName', index: 'realName', width: 180, sorttype: "string",hidden:false},
                {name: 'nickName', index: 'nickName', width: 180, sorttype: "string",hidden:false},
                {name: 'sourceStr', index: 'sourceStr', width: 180, sorttype: "string",hidden:false},
                {name: 'unique_code', index: 'unique_code', width: 320, edittype: "string", hidden:true},
                {name: 'mobileNumber', index: 'mobileNumber', width: 120, edittype: "string", hidden:false},
                {name: 'virtual_gold', index: 'virtual_gold', width: 120, edittype: "string", hidden:false},
                {name: 'money', index: 'money', width: 120, edittype: "string", hidden:false},
                {name: 'balanceMoney', index: 'balanceMoney', width: 120, edittype: "string", hidden:false},
                {name: 'chargeBalance', index: 'chargeBalance', width: 120, edittype: "string", hidden:false},
                {name: 'consumerMoney', index: 'consumerMoney', width: 120, edittype: "string", hidden:false},

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
        
      //  gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
      //      id: "viewCase",
      //      caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看用户的燃香记录</button>",
      //      buttonicon: "none"
      //  });

        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/school/schoolAdd";
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
                document.location.href = "${ctx}/buddha/server/school/schoolEditor?schoolId=" +row.school_id;
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
                        doPost("${ctx}/buddha/server/school/deleteSchool?schoolIdList=" +schoolIdList, function (data) {
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
                document.location.href = "${ctx}/buddha/server/user/buUserView?userId=" +row.id;
            } else {
                addGritter("error", "请选择您要查看的学校信息");
            }
        });
     	
     	
        $("#exportBtnC").click(function () {
       		var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');          
            var name = $("#name").val();
            var uniqueCode = $("#uniqueCode").val();
            var virtualGold = $("#virtualGold").val();
            document.location.href = "${ctx}/buddha/server/user/expertUserVirtualGoldData?name="+name+"&uniqueCode="+uniqueCode+"&virtualGold="+virtualGold;
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