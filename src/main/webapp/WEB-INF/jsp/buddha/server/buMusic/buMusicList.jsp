<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>查询列表</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp"%>

</head>
<body class="no-skin" style="background-color: #fff">
	<div class="row">
		<div class="col-sm-12">
			<form class="form-horizontal well well-sm queryForm">
				<div class="row">
					 <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">状态</label>
                        <div class="col-sm-9">
                        	<select class="form-control" id="status" name="search.a.status_eq">
		                		<option value="">请选择</option>
		                		<option value="1">启用</option>
		                    	<option value="0">禁用</option>
		                    </select>
                        </div>
                    </div>
                </div>
					<div class="col-md-3">
						<div class="form-group">
							<div class="col-md-12">
								<button type="button" class="queryBtn btn btn-sm btn-info">
									<i class="ace-icon fa fa-search bigger-125"></i> 查询
								</button>
								<button class="resetBtn btn btn-sm btn-info" type="reset">
									<i class="ace-icon fa fa-undo bigger-125"></i> 重置
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

	<%@include file="/WEB-INF/jsp/common/import-acejs.jsp"%>
	<script type="text/javascript">
    jQuery(function ($) {
    	var grid_selector = "#grid-table-school";
        var pager_selector = "#grid-pager-school";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/buddha/server/buMusic/buMusicByConsList',
            datatype: "json",
            height: 350,
            shrinkToFit: false,
            colNames: [' ','歌名','作者','类型','图片','文件URL','URL','状态','创建者','创建日期','更新者','更新日期','试听'],
                       colModel: [
                           {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
                           {name: 'name', index: 'name', width: 200, sorttype: "int",hidden:false},
                           {name: 'author', index: 'author', width: 200, sorttype: "int",hidden:false},
                           {name: 'ptTitle', index: 'ptTitle', width: 200, sorttype: "int",hidden:false},
                           {name: 'picture', index: 'picture', width: 120, sorttype: "string",hidden:true},
                           {name: 'fileUrl', index: 'fileUrl', width: 120, edittype: "string", hidden:false},
                           {name: 'url', index: 'url', width: 120, edittype: "string", hidden:false},
                           {name: 'status', index: 'status', width: 120, edittype: "Integer", hidden:false},
                           {name: 'createAccount', index: 'createAccount', width: 120, edittype: "string", hidden:false},
                           {name: 'createTime', index: 'createTime', width: 200, edittype: "string",hidden:false},
                           {name: 'modifyAccount', index: 'modifyAccount', width: 120, edittype: "string", hidden:false},
                           {name: 'modifyTime', index: 'modifyTime', width: 200, edittype: "string",hidden:false},
                           {name: 'fileUrl', index: 'fileUrl', width: 80, edittype: "string",hidden:false,
                        	   formatter:function(value) {
                                       return "<audio controls='controls' style='width:40px;height:30px;'><source src='http://music.fotuozi.com/BUDDHA_MUSIC/"+value+"' /></audio>";
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
            caption: "<button id='addBtn' class='btn btn-xs btn-primary'>添加音乐</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "updateCase",
            caption: "<button id='updateBtn' class='btn btn-xs btn-primary'>修改音乐</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
           id: "deleteCase",
            caption: "<button id='deleteBtn' class='btn btn-xs btn-primary'>删除音乐</button>",
            buttonicon: "none"
        });
        gridTools.jqGrid("navButtonAdd", "#grid-pager-school", {
            id: "viewCase",
            caption: "<button id='viewBtn' class='btn btn-xs btn-primary'>查看音乐信息</button>",
            buttonicon: "none"
        });
        // 增加
        $("#addBtn").click(function () {
            document.location.href = "${ctx}/buddha/server/buMusic/buMusicDetail?param=add";
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
                document.location.href = "${ctx}/buddha/server/buMusic/buMusicDetail?buMusicId=" +row.id+"&param=edit";
            } else {
                addGritter("error", "请选择您要修改的音乐");
            }
        });
        
        // 删除
        $("#deleteBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的音乐吗?删除以后将无法恢复", function(result) {
                    if(result) {
                        var buMusicIdList= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            
                            buMusicIdList[i]=ret.id;
                        }
                        doPost("${ctx}/buddha/server/buMusic/deleteBuMusic?buMusicIdList=" +buMusicIdList, function (data) {
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
                addGritter("error", "请勾选您要删除的音乐");
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
                document.location.href = "${ctx}/buddha/server/buMusic/buMusicView?buMusicId=" +row.id;
            } else {
                addGritter("error", "请选择您要查看的音乐信息");
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