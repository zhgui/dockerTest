<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>组织检索树</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
    <script src="${ctx}/static/plugin/zTree_v3/js/jquery.ztree.all-3.5.js"></script>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="row" style="display:none">
    <div class="col-sm-12">
        <form class="form-horizontal well well-sm queryForm">
            <input name="parentDepartmentId" type="hidden" value="0">
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">部门ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="departmentId" id="departmentId" name="search.c.department_id_eq"/>
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
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div>
    <button id='toDepartmentListBtn' class='btn btn-xs btn-primary'>⇒组织查询</button>
    <button id='toDepartmentAddBtn' class='btn btn-xs btn-primary'>⇒创建新组织</button>
</div>
<div class="row" style="height: 800px;">
     <div class="col-sm-2 ">
        <!--<div class="zTreeDemoBackground left">-->
        <div class="well" style="height: 800px;overflow-y: scroll;overflow-x: auto;">
            <ul id="treeDepartment" class="ztree"></ul>
        </div>
    </div>
    <div class="col-sm-8" style="height: 400px;">
        <table id="grid-table-departmentAth"></table>
        <div id="grid-pager-departmentAth"></div>
    </div>
    <div class="col-sm-8" style="height: 400px;">
        <table id="grid-table-user"></table>
        <div id="grid-pager-user"></div>
    </div>
</div>
<!-- 右键菜单div -->  
<div id="rMenu">
    <ul>
        <li id="m_addDepartment" >创建新组织</li>
        <li id="m_resetDepartmentTree" >展开所有的节点</li>
        <li id="m_updateDepartment" >编辑组织</li>
        <li id="m_addUnderDepartment" >将新组织创建在下面</li>
        <!-- 已用其他方式实现-->
        <!--<li id="m_addUserAth" >添加所属用户</li>-->
        <!-- 保留功能，待商定-->
        <!--<li id="m_addRoleToUserByDepartment" >给所属该组织的用户添加角色</li>-->
        <li id="m_addDepartmentToIncAth" >将无所属组织添加下面</li>
        <li id="m_removeDepartment" >从组成中去掉组织</li>
        <li id="m_delDepartment" >彻底删除组织</li>
    </ul>
</div>
<script type="text/javascript">
    // 树的基本配置设定
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            // 鼠标左键事件onExpand: onExpand,
            onClick: zTreeOnClick,
            // 鼠标右击事件
            onRightClick : zTreeOnRightClick
        },
        select : {
            btn : $("#selectDepartmentTree,#departmentName"),
            id : "departmentId",
            name : "departmentName",
            includeRoot: false
        }
    };
    
    // 定义 树节点
    var zNodes = [
        // TODO 演示数据
        /**
        {"id":1, "pId":1, "name":"test1_申彤集团"},
        {"id":11, "pId":1, "name":"test11_大大财富"},
        {"id":12, "pId":1, "name":"test12_轩皇贸易"},
        {"id":111, "pId":11, "name":"test111_大大财富_上海"}, 
        {"id":112, "pId":11, "name":"test111_大大财富_北京"},
        {"id":113, "pId":11, "name":"test111_大大财富_宁波"},
        {"id":114, "pId":11, "name":"test111_大大财富_江苏"},
        {"id":115, "pId":11, "name":"test111_大大财富_海南岛"},
        {"id":116, "pId":11, "name":"test111_大大财富_云南"},
        {"id":13, "pId":116, "name":"test13_鱼鱼科技"},
        */
        // 循环节点数据，显示树
        <c:forEach items="${zTrees}" var="m" varStatus="status">
            <c:if test="${!status.last}">
            {
                id:${m.id},
                pId:${m.pId},
                name: "${m.name}",
                iconSkin: "${m.iconSkin}",
                open: true,
                root: ${m.root},
                isParent:${m.isParent}
            },
            </c:if>
            <c:if test="${status.last}">
            {
                id:${m.id},
                pId:${m.pId},
                name: "${m.name}",
                iconSkin: "${m.iconSkin}",
                open: true,
                root: ${m.root},
                isParent:${m.isParent}
            }
            </c:if>
        </c:forEach>
        
    ];
    
    // 左键点击树的节点
    function zTreeOnClick(event, treeId, treeNode) {
        //console.log("树ID："+treeId + ",树节点ID：" + treeNode.tId + ",树节点名：" + treeNode.name + ",节点ID：" + treeNode.id+"父节点："+treeNode.pId);
        $("#departmentId").val(treeNode.id);
        $(".queryBtn").click();
    };
    
    // 展开整个树形结构
    function setTrigger() {
        var zTree = $.fn.zTree.getZTreeObj("treeDepartment");
        zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
    }

    //鼠标右键事件-创建右键菜单
    function zTreeOnRightClick(event, treeId, treeNode) {
        //console.debug("|-右键菜单");
        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
            zTree.cancelSelectedNode();
            showRMenu("root", event.clientX, event.clientY);
        } else if (treeNode && !treeNode.noR) {
            zTree.selectNode(treeNode);
            //console.debug("|--右击菜单，节点信息⇒父节点："+treeNode.pId);
            $("#departmentId").val(treeNode.id);
            if(treeNode.pId == null){
                $("input[name='parentDepartmentId']").val(treeNode.id);
            }else{
                $("input[name='parentDepartmentId']").val(treeNode.pId);
            }
            showRMenu("node", event.clientX, event.clientY);
        }
    }
    //显示右键菜单
    function showRMenu(type, x, y) {
        $("#rMenu ul").show();
        //console.debug("|__节点类型type："+type);
        if (type=="root") {
            $("#m_addDepartment").show();
            $("#m_resetDepartmentTree").show();
            $("#m_updateDepartment").hide();
            $("#m_addUnderDepartment").hide();
            //$("#m_addUserAth").hide();
            //$("#m_addRoleToUserByDepartment").hide();
            $("#m_addDepartmentToIncAth").hide();
            $("#m_removeDepartment").hide();
            $("#m_delDepartment").hide();
        } else {
            $("#m_addDepartment").hide();
            $("#m_resetDepartmentTree").hide();
            $("#m_updateDepartment").show();
            $("#m_addUnderDepartment").show();
            //$("#m_addUserAth").show();
            //$("#m_addRoleToUserByDepartment").show();
            $("#m_addDepartmentToIncAth").show();
            $("#m_removeDepartment").show();
            $("#m_delDepartment").show();
        }
        rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

        $("body").bind("mousedown", onBodyMouseDown);
    }
    // 隐藏树形结构
    function hideRMenu() {
        if (rMenu) rMenu.css({"visibility": "hidden"});
        $("body").unbind("mousedown", onBodyMouseDown);
    }
    // 鼠标右击时间绑定
    function onBodyMouseDown(event){
        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
            rMenu.css({"visibility" : "hidden"});
        }
    }
    
    // DEMO 新增节点
    var addCount = 1;
    function addTreeNode() {
        hideRMenu();
        var newNode = { name:"增加" + (addCount++)};
        if (zTree.getSelectedNodes()[0]) {
            newNode.checked = zTree.getSelectedNodes()[0].checked;
            zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
        } else {
            zTree.addNodes(null, newNode);
        }
    }
    function removeTreeNode() {
        hideRMenu();
        var nodes = zTree.getSelectedNodes();
        if (nodes && nodes.length>0) {
            if (nodes[0].children && nodes[0].children.length > 0) {
                var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
                if (confirm(msg)==true){
                    zTree.removeNode(nodes[0]);
                }
            } else {
                zTree.removeNode(nodes[0]);
            }
        }
    }
    
    //恢复树形结构
    function resetTree() {
        hideRMenu();
        $.fn.zTree.init($("#treeDepartment"), setting, zNodes);
    }

    // 树初始化
    var zTree, rMenu;

    // jQuery开始函数
    jQuery(function ($) {
        // 重新载入树形结构数据
        $.fn.zTree.init($("#treeDepartment"), setting, zNodes);
        zTree = $.fn.zTree.getZTreeObj("treeDepartment");
        rMenu = $("#rMenu");
            
        // 组织所拥有的用户GRID
        var grid_selector = "#grid-table-departmentAth";
        var pager_selector = "#grid-pager-departmentAth";
        jQuery(grid_selector).jqGrid(jqGridOption({
            url:'${ctx}/pcf/department/userAthList',
            datatype: "json",
            height: 260,
            shrinkToFit: false,
            colNames: [' ', '用户编号','用户名', '组织名', '电子邮箱','最后更新者', '最后更新时间', '状态'],
            colModel: [
                {name: 'DEPARTMENT_ATH_ID', index: 'DEPARTMENT_ATH_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'USER_CD', index: 'USER_CD', width: 150, sorttype: "string"},
                {name: 'USER_NAME', index: 'USER_NAME', width: 150, sorttype: "string"},
                {name: 'DEPARTMENT_NAME', index: 'DEPARTMENT_NAME', width: 190, sorttype: "string"},
                {name: 'EMAIL_ADDRESS1', index: 'EMAIL_ADDRESS1', width: 150},
                {name: 'RECORD_USER_CD', index: 'RECORD_USER_CD', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
        {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90, edittype: "select", editoptions: {value: "0:无效;1:有效"}}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "组织所属用户列表"

        }));
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools = $(grid_selector).jqGrid("navGrid", "#grid-pager-departmentAth", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools.jqGrid("navButtonAdd", "#grid-pager-departmentAth", {
            id: "deleteUserCase",
            caption: "<button id='deleteUserBtn' class='btn btn-xs btn-primary'>删除所属用户</button>",
            buttonicon: "none"
        });
        // 删除组织的所属用户信息
        $("#deleteUserBtn").click(function () {
            var selrArray = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
            // 必须要有一个删除的对象
            if (selrArray.length>0) {
                bootbox.confirm("您确认要删除选中的组织所属用户吗?", function(result) {
                    if(result) {
                        var departmentAthIdlist= new Array();
                        for(var i=0; i< selrArray.length; i++){
                            var ret = jQuery(grid_selector).jqGrid('getRowData', selrArray[i]);
                            //console.debug(ret);
                            departmentAthIdlist[i]=ret.DEPARTMENT_ATH_ID;
                        }
                        // Delete departmentAth from department
                        doPost("${ctx}/pcf/department/toDeleteDepartmentAth?departmentAthIdList=" +departmentAthIdlist, function (data) {
                            //console.debug(data);
                            if (data.success) {
                                addGritter("success", data.message);
                                // Grid reload
                                jQuery("#grid-table-departmentAth").trigger("reloadGrid");
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                });
            } else {
                addGritter("error", "请选择要删除的组织所属用户");
            }
        });
        // 页面跳转到组织查询页面
        $("#toDepartmentListBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toList";
        });
        // 页面跳转到组织新增页面
        $("#toDepartmentAddBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toAdd";
        });
        // 查询
        $(".queryBtn").click(function () {
            var postData = getFormParams($(this).closest("form"));
            jqGridQuery(grid_selector, postData);
        });
        // 新增组织菜单
        $("#m_addDepartment").click(function () {
            //console.debug("画面跳转到新增组织页面");
            document.location.href = "${ctx}/pcf/department/toAdd";
        });
        $("#m_addDepartment").hover(function () {
            $("#m_addDepartment").css("background-color","#2D8BE7");
        });
        $("#m_addDepartment").mouseout(function () {
            $("#m_addDepartment").css("background-color","#DFDFDF");
        });
        // 展开所有的节点
        $("#m_resetDepartmentTree").click(function () {
            //console.debug("展开所有的节点");
            resetTree();
        });
        $("#m_resetDepartmentTree").hover(function () {
            $("#m_resetDepartmentTree").css("background-color","#2D8BE7");
        });
        $("#m_resetDepartmentTree").mouseout(function () {
            $("#m_resetDepartmentTree").css("background-color","#DFDFDF");
        });
        // 编辑组织菜单
        $("#m_updateDepartment").click(function () {
            //console.debug("-编辑组织菜单");
            //console.debug("--组织父节点ID："+$("input[name='parentDepartmentId']").val());
            document.location.href = "${ctx}/pcf/department/toUpdate?departmentId=" +$("#departmentId").val()+"&parentDepartmentId=" +$("input[name='parentDepartmentId']").val();
        });
        $("#m_updateDepartment").hover(function () {
            $("#m_updateDepartment").css("background-color","#2D8BE7");
        });
        $("#m_updateDepartment").mouseout(function () {
            $("#m_updateDepartment").css("background-color","#DFDFDF");
        });
        // 将新组织创建在下面菜单
        $("#m_addUnderDepartment").click(function () {
            //console.debug("将新组织创建在下面菜单");
            document.location.href = "${ctx}/pcf/department/toAdd?departmentId=" +$("#departmentId").val();
        });
        $("#m_addUnderDepartment").hover(function () {
            $("#m_addUnderDepartment").css("background-color","#2D8BE7");
        });
        $("#m_addUnderDepartment").mouseout(function () {
            $("#m_addUnderDepartment").css("background-color","#DFDFDF");
        });
        // 添加所属用户菜单
        /**
        $("#m_addUserAth").click(function () {
            //TODO 已用其他方式实现
            //console.debug("添加所属用户菜单");
        });
        */
        // 通过组织给所属用户给增加角色
        /**
        $("#m_addRoleToUserByDepartment").click(function () {
            //TODO 保留功能，待实现
            //console.debug("通过组织给所属用户给增加角色");
        });
        */
        // 将无所属组织添加到指定组织下
        $("#m_addDepartmentToIncAth").click(function () {
            //console.debug("将无所属组织添加到指定组织下");
            document.location.href = "${ctx}/pcf/department/toDepartmentIncAth?departmentId=" +$("#departmentId").val();
        });
        $("#m_addDepartmentToIncAth").hover(function () {
            $("#m_addDepartmentToIncAth").css("background-color","#2D8BE7");
        });
        $("#m_addDepartmentToIncAth").mouseout(function () {
            $("#m_addDepartmentToIncAth").css("background-color","#DFDFDF");
        });
        // 从组成中去掉组织菜单
        $("#m_removeDepartment").click(function () {
            //console.debug("从组成中去掉组织菜单");
            bootbox.confirm("您确认要将该组织从现组织构成中脱离?", function(result) {
                if(result) {
                    document.location.href = "${ctx}/pcf/department/toRemoveDepartment?departmentId=" +$("#departmentId").val();
                    //console.debug("已从组成中去除组织菜单");
                }
            });
        });
        $("#m_removeDepartment").hover(function () {
            $("#m_removeDepartment").css("background-color","#2D8BE7");
        });
        $("#m_removeDepartment").mouseout(function () {
            $("#m_removeDepartment").css("background-color","#DFDFDF");
        });
        // 彻底删除组织菜单
        $("#m_delDepartment").click(function () {
            //console.debug("彻底删除组织菜单");
            bootbox.confirm("您确认要彻底删除选中的组织吗?彻底删除后将无法恢复。", function(result) {
                if(result) {
                    document.location.href = "${ctx}/pcf/department/toDeleteDepartment?departmentId=" +$("#departmentId").val();
                    //console.debug("已经彻底删除组织菜单");
                }
            });
        });
        $("#m_delDepartment").hover(function () {
            $("#m_delDepartment").css("background-color","#2D8BE7");
        });
        $("#m_delDepartment").mouseout(function () {
            $("#m_delDepartment").css("background-color","#DFDFDF");
        });
        // 所有的用户信息GRID
        var grid_selector_user = "#grid-table-user";
        var pager_selector_user = "#grid-pager-user";
        jQuery(grid_selector_user).jqGrid(jqGridOption({
            url:'${ctx}/pcf/user/list',
            datatype: "json",
            height: 260,
            shrinkToFit: false,
            colNames: [' ', '用户编号', '用户名', '电子邮箱','手机', '最后更新者', '最后更新时间', '状态'],
            colModel: [
                {name: 'USER_ID', index: 'USER_ID', width: 60, sorttype: "int",hidden:true},
                {name: 'USER_CD', index: 'USER_CD', width: 150, sorttype: "string"},
                {name: 'USER_NAME', index: 'USER_NAME', width: 150, sorttype: "string"},
                {name: 'EMAIL_ADDRESS1', index: 'EMAIL_ADDRESS1', width: 170},
                {name: 'MOBILE_NUMBER', index: 'MOBILE_NUMBER', width: 170},
                {name: 'RECORD_USER_CD', index: 'RECORD_USER_CD', width: 170},
                {name: 'RECORD_DATE', index: 'RECORD_DATE', width: 170},
                {name: 'DELETE_FLAG', index: 'DELETE_FLAG', width: 90, edittype: "select", editoptions: {value: "0:无效;1:有效"}}
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector_user,
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            caption: "用户列表"
        }));
        
        //trigger window resize to make the grid get the correct size
        $(window).triggerHandler('resize.jqGrid');

        $(window).on('resize.jqGrid', function () {
            $(grid_selector_user).jqGrid('setGridWidth', $(".page-content").width());
        });

        //添加按钮
        var gridTools_user = $(grid_selector_user).jqGrid("navGrid", "#grid-pager-user", {edit: false, add: false, del: false, search: false, refresh: false, view: false});
        gridTools_user.jqGrid("navButtonAdd", "#grid-pager-user", {
            id: "addUserToDepartmentCase",
            caption: "<button id='addUserToDepartmentBtn' class='btn btn-xs btn-primary'>添加所属用户</button>",
            buttonicon: "none"
        });
        $("#addUserToDepartmentBtn").click(function () {
            // 必须选中一个组织
            //console.debug("组织ID:"+$("#departmentId").val());
            if($("#departmentId").val()==""){
                addGritter("error", "请选择要增加用户的组织");
            }else{
                var selrArray = jQuery(grid_selector_user).jqGrid('getGridParam', 'selarrrow');
                // 必须要有选择一条数据
                if (selrArray.length>0) {
                    bootbox.confirm("您确认要给组织增加选中的用户吗?", function(result) {
                        if(result) {
                            var userIdList= new Array();
                            for(var i=0; i< selrArray.length; i++){
                                var ret = jQuery(grid_selector_user).jqGrid('getRowData', selrArray[i]);
                                //console.debug(ret);
                                userIdList[i]=ret.USER_ID;
                            }
                            // Add role to user
                            doPost("${ctx}/pcf/department/toAddUserToDepartment?userIdList=" +userIdList+"&departmentId=" +$("#departmentId").val(), function (data) {
                                //console.debug(data);
                                if (data.success) {
                                    addGritter("success", data.message);
                                    // Grid reload
                                    jQuery("#grid-table-departmentAth").trigger("reloadGrid");
                                } else {
                                    addGritter("error", data.message);
                                }
                            }, "json")
                        }
                    });
                } else {
                    addGritter("error", "请选择要给组织增加的用户");
                }
            }
        });
    });
</script>
<style type="text/css">
div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555555;text-align: left;padding: 1px;}
div#rMenu ul {
    margin:1px 0;
    padding:0 5px;
    cursor: pointer;
    list-style: none outside none;
    background-color:#DFDFDF;
    display:none;
}
div#rMenu ul li{
    margin: 0;
    padding: 1px 0;
    cursor: pointer;
    list-style: none outside none;
    background-color: #DFDFDF;
}
</style>
</body>
</html>