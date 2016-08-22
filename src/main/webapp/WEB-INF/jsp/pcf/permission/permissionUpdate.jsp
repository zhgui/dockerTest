<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>权限更新</title>

    <meta name="description" content="权限更新"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <script src="${ctx}/static/plugin/jquery/jquery-1.11.1.js"></script>

    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

</head>
<body class="no-skin" style="background-color: #fff">
<div class="page-header">
	<label>
		权限列表
	<small>
	 <i class="ace-icon fa fa-angle-double-right"></i>
									修改权限
	</small>
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
                <label class="col-sm-3 control-label no-padding-right" for="permissionName"> 权限名称 </label>

                <div class="col-sm-9">
                    <input type="text" id="permissionName" name="permissionName" value="${permission.permissionName}"
                           placeholder="权限名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
         <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="permission"> 权限标识符 </label>

                <div class="col-sm-9">
                    <input type="text" id="permission" name="permission" value="${permission.permission}"
                           placeholder="权限标识符"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <!-- /section:elements.form -->
            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="notes"> 权限描述 </label>

                <div class="col-sm-9">
                    <input type="text" id="notes" placeholder="权限描述" class="col-xs-10 col-sm-5" name="notes"
                           value="${permission.notes}"/>
                </div>
            </div>

            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sortKey"> 排序键 </label>

                <div class="col-sm-9">
                    <input type="text" id="sortKey" placeholder="排序键" class="col-xs-10 col-sm-5" name="sortKey"
                           value="${permission.sortKey}"/>
                </div>
            </div>

            <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deleteFlag"> 状态 </label>

                <div class="col-sm-9" id="deleteFlag">
                    <label class="radio-inline">
                        <input name="deleteFlag" type="radio" class="ace"
                               value="0" ${permission.deleteFlag =="0"?'checked':""}/>
                        <span class="lbl"> 有效 </span>
                    </label>
                    <label class="radio-inline">
                        <input name="deleteFlag" type="radio" class="ace"
                               value="1" ${permission.deleteFlag =="1"?'checked':""}/>
                        <span class="lbl"> 无效 </span>
                   </label>
                </div>
            </div>
            <div class="well">
                <div class="row ">
                    <label class="col-sm-3 control-label">&nbsp;</label>
                      <div class="col-sm-6">
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
                        <!-- <a href="javascript:;"  onClick="localhref()"  class="btn btn-success">   -->
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
    $(function () {
        $('#permissionUpdateForm').validate(jValidateOption({
            rules: {
                permissionName: {
                    required: true,
                    maxlength: 300
                },
                permission: {
                    required: true,
                    maxlength: 8
                },
                sortKey: {
                    required: true,
                    maxlength: 5
                },
                deleteFlag: {
                    required: true
                },
                notes: {
                    required: false,
                    maxlength: 1000
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
                },
                notes: {
                	required: "请输入描述"
                }
            }
        }));

        $(".saveBtn").on("click", function () {
            if (!$('#permissionUpdateForm').valid()) {
                return false;
            }
            doPost("${ctx}/pcf/permission/update", $("#permissionUpdateForm").serialize(), function (data) {
                if (data.success) {
                    //bootbox.alert(data.message, function (e) {
                    //    document.location.href = "${ctx}/pcf/permission/toUpdate?permissionId=" + data.responseData.permissionId;
                    //});
                	addGritter("success", data.message);
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
        })
    });
    
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/pcf/permission/toList";
	}
</script>
</body>
</html>