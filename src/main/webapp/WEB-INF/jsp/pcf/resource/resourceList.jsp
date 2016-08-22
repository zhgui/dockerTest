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
    <%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>


    <link rel="stylesheet" href="${ctx}/static/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
    <script src="${ctx}/static/plugin/zTree_v3/js/jquery.ztree.all-3.5.js"></script>
</head>
<body style="background-color: #fff">
<div class="page-header">
	<label>
		资源列表
	</label>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 ">
            <h4></h4>
        </div>

    </div>
    <div class="row" style="height: 800px;">
        <div class="col-sm-2 ">
            <div class="well">

                <ul id="treeDemo" class="ztree"></ul>

            </div>
        </div>
        <div class="col-sm-10">
            <%--<div class="well  well-large">--%>
            <!-- #section:elements.tab -->
            <div class="tabbable">
                <ul class="nav nav-tabs" id="myTab">
                    <shiro:hasPermission name="resource:view">
                        <li class="active">
                            <a data-toggle="tab" href="#resourceView">
                                <i class="green ace-icon fa fa-home bigger-120"></i>
                                查看
                            </a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="resource:update">
                        <li>
                            <a data-toggle="tab" href="#resourceUpdate">
                                <i class="green ace-icon fa fa-home bigger-120"></i>
                                修改
                            </a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="resource:create">

                        <li>
                            <a data-toggle="tab" href="#resourceAdd">
                                <i class="green ace-icon fa fa-home bigger-120"></i>
                                增加子节点
                            </a>
                        </li>
                    </shiro:hasPermission>
                </ul>

                <div class="tab-content">
                    <shiro:hasPermission name="resource:view">
                        <div id="resourceView" class="tab-pane fade in active">
                        </div>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="resource:update">
                        <div id="resourceUpdate" class="tab-pane fade">
                        </div>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="resource:create">
                        <div id="resourceAdd" class="tab-pane fade">

                        </div>
                    </shiro:hasPermission>
                </div>
            </div>

        </div>
        <%--</div>--%>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->

        </div>
    </div>
</div>


<script type="text/javascript">
    function zTreeOnClick(event, treeId, treeNode) {
        console.log(treeId + "," + treeNode.tId + ", " + treeNode.name + ", " + treeNode.id);
        //$("#resourceView").load("${ctx}/pcf/resource/toView", {"resourceId": treeNode.id});
        $("#myTab li.active").trigger("click");
    }

    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            // onExpand: onExpand,
            onClick: zTreeOnClick
        },
        select: {
            btn: $("#selectResourceTree,#resourceName"),
            id: "resourceId",
            name: "resourceName",
            includeRoot: false
        }
    };

    var zNodes = [

        <c:forEach items="${zTrees}" var="m" varStatus="status">
        <c:if test="${!status.last}">
        {
            id:${m.id},
            pId:${m.pId},
            name: "${m.name}",
            iconSkin: "",
            open: "${m.id<=1?'true':'false'}",
            root: ${m.root},
            isParent:${m.isParent}
        },
        </c:if>
        <c:if test="${status.last}">
        {
            id:${m.id},
            pId:${m.pId},
            name: "${m.name}",
            iconSkin: "",
            open: false,
            root: ${m.root},
            isParent:${m.isParent}
        }
        </c:if>

        </c:forEach>

    ];

    // var log, className = "dark", curDragNodes, autoExpandNode;


    function setTrigger() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
    }
    var zTree = null;
    $(document).ready(function () {
        zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $("#callbackTrigger").bind("change", {}, setTrigger);

        var nodes = zTree.getNodes();
        if (nodes && nodes.length) {
            zTree.selectNode(nodes[0]);
            $("#resourceView").load("${ctx}/pcf/resource/toView", {"resourceId": nodes[0].id});
        }
        $("#myTab li").on("click", function () {
            var $this = $(this);
            var nodes = zTree.getSelectedNodes();
            if ($this.find("a").attr("href") == "#resourceView") {
                if (nodes && nodes.length) {
                    $("#resourceView").load("${ctx}/pcf/resource/toView", {"resourceId": nodes[0].id});
                } else {
                    addGritter("info", "请选择你要查看的节点");
                }
            } else if ($this.find("a").attr("href") == "#resourceUpdate") {
                if (nodes && nodes.length) {
                    $("#resourceUpdate").load("${ctx}/pcf/resource/toUpdate", {"resourceId": nodes[0].id}, function () {
                        $('#resourceUpdateForm').validate(jValidateOption({
                            rules: {
                            	resourceName: {
                                    required: true,
                                    maxlength:1300
                                },
                                identify: {
                                    required: true,
                                    maxlength:65
                                },
                                sortKey: {
                                    required: true,
                                    maxlength:6
                                },
                                notes: {
                                    required: false,
                                    maxlength:1300
                                },
                                url: {
                                    required: false,
                                    maxlength:65
                                },
                                icon: {
                                    required: false,
                                    maxlength:65
                                }
                            }
                        }));

                        $(".saveBtn").on("click", function () {
                            if (!$('#resourceUpdateForm').valid()) {
                                return false;
                            }
                            console.log($("#resourceUpdateForm").serialize())
                            $.post("${ctx}/pcf/resource/update", $("#resourceUpdateForm").serialize(), function (data) {
                                addGritter("success", "修改成功")
                            });
                        })
                    });
                } else {
                    addGritter("info", "请选择你要编辑的节点");
                }
            } else if ($this.find("a").attr("href") == "#resourceAdd") {
                if (nodes && nodes.length) {
                    $("#resourceAdd").load("${ctx}/pcf/resource/toAdd", {"resourceId": nodes[0].id}, function () {
                        $('#resourceAddForm').validate(jValidateOption({
                            rules: {
                                resourceName: {
                                    required: true,
                                    maxlength:1300
                                },
                                identify: {
                                    required: true,
                                    maxlength:65
                                },
                                sortKey: {
                                    required: true,
                                    maxlength:6
                                },
                                notes: {
                                    required: false,
                                    maxlength:1300
                                },
                                url: {
                                    required: false,
                                    maxlength:65
                                },
                                icon: {
                                    required: false,
                                    maxlength:65
                                }
                            }
                        }));

                        $(".saveBtn").on("click", function () {
                            if (!$('#resourceAddForm').valid()) {
                                return false;
                            }
                            $.post("${ctx}/pcf/resource/add", $("#resourceAddForm").serialize(), function (data) {
                                addGritter("success", "添加成功")
                            });
                        })
                    });

                } else {
                    addGritter("info", "请选择你要增加节点的父节点");
                }
            }
        });
    })

</script>


</body>
</html>