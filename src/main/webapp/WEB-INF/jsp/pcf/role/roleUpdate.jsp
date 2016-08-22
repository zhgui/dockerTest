<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>权限增加</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <style>
        html {
            overflow-x: hidden;
        }
    </style>
</head>
<body class="no-skin" style="background-color: #fff;">

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="roleUpdateForm">
            <input type="hidden" name="roleId" value="${role.roleId}">
            <c:if test="${not empty message}">
                <div class="space-4"></div>
                <div class="form-group">

                    <div class="col-sm-offset-3 col-sm-4 text-center">
                        <div class="alert alert-success no-margin .pcfMsg">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>

                            <i class="ace-icon fa fa-check bigger-120 blue"></i>
                                ${message}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="well">
                <div class="space-4"></div>

                <!-- #section:elements.form -->
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="roleName"> 角色名称 </label>

                    <div class="col-sm-9">
                        <input type="text" id="roleName" name="roleName" placeholder="角色名称" value="${role.roleName}"
                               class="col-xs-10 col-sm-5"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="role"> 角色标识 </label>

                    <div class="col-sm-9">
                        <input type="text" id="role" name="role" placeholder="角色标识" value="${role.role}"
                               class="col-xs-10 col-sm-5"/>
                    </div>
                </div>

                <!-- /section:elements.form -->
                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="NOTES"> 角色描述 </label>

                    <div class="col-sm-9">
                        <input type="text" id="notes" name="notes" placeholder="角色描述" value="${role.notes}"
                               class="col-xs-10 col-sm-5"/>
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="sortKey"> 排序键 </label>

                    <div class="col-sm-9">
                        <input type="text" id="sortKey" placeholder="排序键" value="${role.sortKey}"
                               class="col-xs-10 col-sm-5" name="sortKey"
                                />
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="deleteFlag"> 状态 </label>

                    <div class="col-sm-9" id="deleteFlag">
                    <span>
                        <input name="deleteFlag" type="radio" class="ace"
                               value="0" ${role.deleteFlag =="0"?'checked':""}/>
                        <span class="lbl"> 有效 </span>
                    </span>
                    <span>
                        <input name="deleteFlag" type="radio" class="ace"
                               value="1" ${role.deleteFlag =="1"?'checked':""}/>
                        <span class="lbl"> 无效 </span>
                    </span>
                    </div>
                </div>
            </div>

            <div class="well col-sm-6" style="height: 280px;">
                <label class="col-sm-2 control-label no-padding-right" for="treeDemo"> 资源列表 </label>

                <div class="col-sm-7">
                    <div class="widget-box">
                        <div class="widget-body">
                            <div class="widget-main padding-4">
                                <!-- #section:custom/scrollbar -->
                                <div class="scrollable" data-height="300">
                                    <div class="content" style="height: 220px;">
                                        <ul id="treeDemo" class="ztree"></ul>

                                    </div>
                                </div>

                                <!-- /section:custom/scrollbar -->
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="well col-sm-6" style="height: 280px;">
                <label class="col-sm-2 control-label no-padding-right" for="permissionList"> 权限列表 </label>

                <div class="col-sm-7">
                    <select class="form-control" id="permissionList" multiple="multiple" style="height: 230px;">
                        <c:forEach items="${permission}" var="m">
                            <option value="${m.permissionId}">${m.permissionName}[${m.permission}]</option>
                        </c:forEach>

                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <span class="btn btn-sm btn-success selectBtn">
                        <i class="ace-icon fa fa-check"></i>
                        添加到列表
                    </span>

                    <div class="space-16"></div>

                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center" style="width: 30%">
                                        资源
                                    </th>
                                    <th style="width: 40%">权限</th>
                                    <th style="width: 30%">操作</th>
                                </tr>
                                </thead>

                                <tbody id="tbDetail">
                                <c:forEach items="${rrpList}" var="m">
                                    <tr id="auth_${m.resourceId}">
                                        <td class="center">${m.resourceName}<input type="hidden" name="resourceId"
                                                                                   value="${m.resourceId}"></td>
                                        <td class="hidden-480">${m.permissionNames}<input type="hidden"
                                                                                          name="permissionIds"
                                                                                          value="${m.permissionIds}">
                                        </td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <label class="btn btn-xs btn-danger delAuthBtn"><i
                                                        class="ace-icon fa fa-trash-o bigger-120"></i></label>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="well">
                <div class="row ">
                    <div class="col-md-offset-5 col-md-7">
                        <button class="btn btn-info saveBtn" type="button">
                            <i class="ace-icon fa fa-check bigger-110"></i>
                            提交
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="reset">
                            <i class="ace-icon fa fa-undo bigger-110"></i>
                            重置
                        </button>
                        &nbsp; &nbsp; &nbsp;
                        <a href="<st:BackURL/>" class="btn btn-success">
                            <i class="ace-icon fa  fa-reply icon-only bigger-110"></i>
                            返回
                        </a>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script src="${ctx}/static/plugin/zTree_v3/js/jquery.ztree.all-3.5.js"></script>

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
        check: {
            enable: true,
            chkStyle: "checkbox",
            onlyCheckLeaf: false,
            onlySelectLeaf: true
        }
    };

    var zNodes = [

        <c:forEach items="${resources}" var="m" varStatus="status">
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

    // var log, className = "dark", curDragNodes, autoExpandNode;


    function setTrigger() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
    }
    var zTree = null;
    jQuery(function ($) {
        $('#roleUpdateForm').validate(jValidateOption({
            rules: {
            	roleName: {
                    required: true,
                    maxlength: 330
                },
                role: {
                    required: true,
                    maxlength: 65
                },
                sortKey: {
                    required: true,
                    maxlength: 5
                },
                notes: {
                    required: false,
                    maxlength: 1300
                }
            }
        }));

        $(".delAuthBtn").on("click", function () {
            $(this).closest("tr").remove();
        });

        // scrollables
        $('.scrollable').each(function () {
            var $this = $(this);
            $(this).ace_scroll({
                size: $this.data('size') || 220
                //styleClass: 'scroll-left scroll-margin scroll-thin scroll-dark scroll-light no-track scroll-visible'
            });
        });

        $(window).on('resize.scroll_reset', function () {
            $('.scrollable-horizontal').ace_scroll('reset');
        });
        zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $("#callbackTrigger").bind("change", {}, setTrigger);
        $(".selectBtn").on("click", function (e) {
            //获得选中树中的资源
            var nodes = zTree.getCheckedNodes(true);
            if (!nodes || !nodes.length) {
                addGritter("warn", "请选择要授权的资源");
                return;
            }
            if (!$("#permissionList>option:selected").length) {
                addGritter("warn", "请选择要授权的权限");
                return;
            }
            if (nodes && nodes.length) {
                for (var i = 0; i < nodes.length; i++) {
                    var ztreeNode = nodes[i];
                    //console.log(ztreeNode)
                    if (!ztreeNode.isParent) {
                        $("#auth_" + ztreeNode.id).remove();
                        var trHtml = '<tr id="auth_' + ztreeNode.id + '"><td class="center">';
                        trHtml += ztreeNode.name;
                        trHtml += '<input type="hidden" name="resourceId" value="' + ztreeNode.id + '"/>';
                        trHtml += '</td> <td class="hidden-480">';
                        var permissionIds = "";
                        $("#permissionList>option:selected").each(function (idx, dom) {
                            if (idx != 0) {
                                trHtml += "，";
                                permissionIds += ","
                            }
                            permissionIds += $(this).val();
                            trHtml += $(this).text();
                        });
                        trHtml += '<input type="hidden" name="permissionIds" value="' + permissionIds + '"/>';
                        trHtml += '<td><div class="hidden-sm hidden-xs btn-group"><button class="btn btn-xs btn-danger delAuthBtn">' +
                        '<i class="ace-icon fa fa-trash-o bigger-120"></i></button></div></td></tr>';
                        $("#tbDetail").append(trHtml);
                    }
                }
                $(".delAuthBtn").on("click", function () {
                    $(this).closest("tr").remove();
                });
            }

            //获得选中的权限
        });

        $(".saveBtn").on("click", function () {
            if (!$('#roleUpdateForm').valid()) {
                return false;
            }
            // $("#roleUpdateForm").attr("action", "${ctx}/pcf/role/addAndAuth").submit();
            //console.log( $("#roleUpdateForm").serializeJson())
            doPost("${ctx}/pcf/role/addAndAuth", $("#roleUpdateForm").serialize(), function (data) {
                if (data.success) {
                	//此处注释代码是角色信息修改提交完成后,再次跳到角色信息修改页面,由于二次跳转,所以修改页面上的返回按钮记录的是上一次的页面访问地址,存在无法跳转返回列表页面的问题.
                    //bootbox.alert(data.message, function (e) {
                    //    document.location.href = "${ctx}/pcf/role/toUpdate?roleId=" + data.responseData;
                    //});
                    addGritter("success", data.message);
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
        });
    });
</script>

</body>
</html>