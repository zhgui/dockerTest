<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>权限增加</title>

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
            <!-- #section:elements.tab -->
            <div class="tabbable">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active">
                        <a data-toggle="tab" href="#userInfo">
                            <i class="green ace-icon fa fa-home bigger-120"></i>
                            个人信息
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#roleAthInfo">
                            <i class="green ace-icon fa fa-home bigger-120"></i>
                            角色
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#departmentAthInfo">
                            <i class="green ace-icon fa fa-home bigger-120"></i>
                            组织所属
                        </a>
                    </li>

                </ul>

                <div class="tab-content" style="height:1224px">
                    <div id="userInfo" class="tab-pane fade in active">
                    </div>

                    <div id="roleAthInfo" class="tab-pane fade">
                    </div>

                    <div id="departmentAthInfo" class="tab-pane fade">

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    function initDisplayTab(){
    
    };

    $(function () {
        // 初期显示用户信息tab
        //console.debug("初期化");
        //console.debug(${user.userId});
        // 初期显示TAB设定
        //console.debug("初期显示TAB设定");
        //console.debug("${tabDisplay}");
        //console.debug('departmentAthInfo'=="${tabDisplay}");
        $("#userInfo").load("${ctx}/pcf/user/toUserInfo?userId=" +${user.userId}, function () {
            //$(".saveBtn").on("click", function () {
                ////console.log( $("form").serialize());
                //$.post("${ctx}/pcf/user/setUser", $("form").serialize(), function (data) {
                    //addGritter("success", "登录修改成功");
                //});
            //});
        });
        /** TODO 需求：组织所属删除以后，依然跳转至组织所属TAB
        if("userInfo"=="${tabDisplay}"){
            $("#userInfo").load("${ctx}/pcf/user/toUserInfo?userId=" +${user.userId}, function () {
                $(".saveBtn").on("click", function () {
                    //console.log( $("form").serialize());
                    $.post("${ctx}/pcf/user/setUser", $("form").serialize(), function (data) {
                        addGritter("success", "登录修改成功");
                    });
                });
            });
        }else if("roleAthInfo"=="${tabDisplay}"){
            $("#roleAthInfo").load("${ctx}/pcf/user/toRoleAthInfo?userId=" +${user.userId}, function () {
                $(".saveBtn").on("click", function () {
                    //console.log( $("form").serialize());
                    $.post("${ctx}/pcf/user/addRole", $("form").serialize(), function (data) {
                        addGritter("success", "用户角色信息登录修改成功");
                    });
                });
            });
        }else if("departmentAthInfo"=="${tabDisplay}"){
            $("#departmentAthInfo").removeClass(); 
            $("#departmentAthInfo").attr("class","tab-pane fade in active"); 
            $("#userInfo").removeClass(); 
            $("#userInfo").attr("class","tab-pane fade"); 
            $("#roleAthInfo").removeClass(); 
            $("#roleAthInfo").attr("class","tab-pane fade"); 
            $("#departmentAthInfo").load("${ctx}/pcf/user/toDepartmentAthInfo?userId=" +${user.userId}, function () {
                $(".saveBtn").on("click", function () {
                    //console.log( $("form").serialize());
                    $.post("${ctx}/pcf/user/addDepartmentAth", $("form").serialize(), function (data) {
                        addGritter("success", "用户的部门所属信息登录修改成功");
                    });
                });
                
            });
        }
        */
        // TAB切换操作
        $("#myTab li").on("click", function () {
            var $this = $(this);
            // 跳转到用户信息tab
            if ($this.find("a").attr("href") == "#userInfo") {
                //console.debug("跳转到用户信息tab");
                $("#userInfo").load("${ctx}/pcf/user/toUserInfo?userId=" +${user.userId}, function () {
                });
            }else if ($this.find("a").attr("href") == "#roleAthInfo") {
                //console.debug("跳转到角色信息tab");
                //console.debug("用户ID:"+${user.userId});
                $("#roleAthInfo").load("${ctx}/pcf/user/toRoleAthInfo?userId=" +${user.userId}, function () {
                });
            }else if ($this.find("a").attr("href") == "#departmentAthInfo") {
                //console.debug("跳转到部门所属信息tab");
                $("#departmentAthInfo").load("${ctx}/pcf/user/toDepartmentAthInfo?userId=" +${user.userId}, function () {
                });
            }
        });
    });
</script>
</body>
</html>