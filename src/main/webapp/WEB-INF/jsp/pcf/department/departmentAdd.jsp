<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>增加组织</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <script src="${ctx}/static/plugin/jquery/jquery-1.11.1.js"></script>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="row">
<div class="col-xs-12">
<div class="col-sm-10">
            <%--<div class="well  well-large">--%>
            <!-- 页面迁移按钮-->
            <button id='toDepartmentListBtn' class='btn btn-xs btn-primary'>⇒组织查询</button>
            <button id='toDepartmentTreeBtn' class='btn btn-xs btn-primary'>⇒显示组织树</button>
            <!-- #section:elements.tab -->
            <div class="tabbable">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active">
                        <a data-toggle="tab" href="#departmentInfo">
                            <i class="green ace-icon fa fa-home bigger-120"></i>
                            组织详细信息
                        </a>
                    </li>
                    <!--为以后横向扩展留下接口，可仿造一下，增加TAB的方式，增加功能
                    <li>
                        <a data-toggle="tab" href="#roleAthInfo">
                            <i class="green ace-icon fa fa-home bigger-120"></i>
                            组织分类设计等功能的新增
                        </a>
                    </li>
                    -->
                </ul>

                <div class="tab-content" style="height:1224px">
                    <div id="departmentInfo" class="tab-pane fade in active">
                    </div>
                    <!--为以后横向扩展留下接口，可仿造一下，增加TAB的方式，增加功能
                    <div id="***Info" class="tab-pane fade">
                    </div>
                    -->
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        // 初期显示组织信息tab
        //console.debug("|-初期化_初期显示组织信息tab");
        //console.debug("|--初期化_组织节点ID:"+${department.departmentId});
        //console.debug("|__初期化_组织父节点ID:"+${departmentIncAth.parentDepartmentId});
        $("#departmentInfo").load("${ctx}/pcf/department/toDepartmentInfo?departmentId=" +${department.departmentId}+"&parentDepartmentId="+(${departmentIncAth.parentDepartmentId}), function () {
            /**
            $(".saveBtn").on("click", function () {
                console.log( $("form").serialize());
                $.post("${ctx}/pcf/department/setDepartment", $("form").serialize(), function (data) {
                    addGritter("success", "登录修改成功");
                });
            });
            */
        });
        // TAB切换操作
        $("#myTab li").on("click", function () {
            var $this = $(this);
            // 跳转到组织信息tab
            if ($this.find("a").attr("href") == "#departmentInfo") {
                //console.debug("跳转到组织信息tab");
                $("#departmentInfo").load("${ctx}/pcf/department/toDepartmentInfo?departmentId=" +${department.departmentId}+"parentDepartmentId="(${departmentIncAth.parentDepartmentId}), function () {
                    /**
                    $(".saveBtn").on("click", function () {
                        console.log( $("form").serialize());
                        $.post("${ctx}/pcf/department/setDepartment", $("form").serialize(), function (data) {
                            addGritter("success", "组织信息[登录/修改]成功");
                        });
                    });
                    */
                });
            }
            /**为以后横向扩展留下接口，可仿造以上↑代码，增加TAB的方式，增加功能*/
        });
        // 页面跳转到组织查询页面
        $("#toDepartmentListBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toList";
        });
        // 页面跳转到组织树页面
        $("#toDepartmentTreeBtn").click(function () {
            document.location.href = "${ctx}/pcf/department/toTreeList";
        });
    });
</script>
</body>
</html>