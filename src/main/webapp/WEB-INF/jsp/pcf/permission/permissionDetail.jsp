<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>权限详情</title>

    <meta name="description" content="权限详情"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <script src="${ctx}/static/plugin/jquery/jquery-1.11.1.js"></script>

    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="page-header">
	<label>
		权限详情:
	</label>
	
</div>
<div class="row">
    <div class="col-xs-12">

        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="permissionUpdateForm" action="${ctx}/pcf/permission/update">
            <input name="permissionId" value="${permission.permissionId}" type="hidden">
            <!-- #section:elements.form -->
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
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="permissionName"> 权限名称 :</label>
                <div class="col-sm-9">
                	<label class="control-label">${permission.permissionName}</label>
                </div>
            </div>
         <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="permission"> 权限标识符: </label>

                <div class="col-sm-9">
                <label class="control-label">${permission.permission}</label>
                </div>
            </div>

            <!-- /section:elements.form -->
            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="notes"> 权限描述: </label>

                <div class="col-sm-9">
                   <label class="control-label">${permission.notes}</label>
                </div>
            </div>

            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="sortKey"> 排序键: </label>

                <div class="col-sm-9">
                	<label class="control-label">${permission.sortKey}</label>
                </div>
            </div>

            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="deleteFlag"> 状态 :</label>

                <div class="col-sm-9" id="deleteFlag">
                    <label class="radio-inline">
                        <input name="deleteFlag" type="radio" class="ace" disabled="disabled" 
                               value="0" ${permission.deleteFlag =="0"?'checked':""}/>
                        <span class="lbl"> 有效 </span>
                    </label>
                    <label class="radio-inline">
                        <input name="deleteFlag" type="radio" class="ace" disabled="disabled" 
                               value="1" ${permission.deleteFlag =="1"?'checked':""}/>
                        <span class="lbl"> 无效 </span>
                   </label>
                </div>
            </div>
            <div id="backDiv" class="well">
                <div class="row ">
                    <label class="col-sm-3 col-sm-offset-2">&nbsp;</label>
                      <div class="col-sm-6">
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

<script type="text/javascript">
	
	//判断页面是否显示返回按钮
	var flag = '${flag}';
 	if(flag == 'hide'){
 		$("#backDiv").hide();
 	}else if(flag == 'show'){
 		$("#backDiv").show();
 	}else{
 		$("#backDiv").show();
 	}
	
    $(function () {
        $('#permissionUpdateForm').validate(jValidateOption({
            rules: {
                permissionName: {
                    required: true
                },
                permission: {
                    required: true
                },
                sortKey: {
                    required: true
                }
            },

            messages: {
                permissionName: {
                    required: "请输入权限名称"
                },
                permission: {
                    required: "请输入权限标识"
                },
                sortKey: {
                    required: "请输入排序键"
                }
            }
        }));

        $(".saveBtn").on("click", function () {
            if (!$('#permissionUpdateForm').valid()) {
                return false;
            }
            doPost("${ctx}/pcf/permission/update", $("#permissionUpdateForm").serialize(), function (data) {
                if (data.success) {
                    bootbox.alert(data.message, function (e) {
                        document.location.href = "${ctx}/pcf/permission/toUpdate?permissionId=" + data.responseData.permissionId;
                    });
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
        })
    });

</script>
</body>
</html>