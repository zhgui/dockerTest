<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>后台管理系统</title>

    <meta name="description" content="后台管理系统"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
</head>

<body class="no-skin" style="overflow: hidden">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
               <!-- <img src="${ctx}/static/img/logo.png" style="height: 24px;width: 36px;"/>  --> 
                <small>
                   	 后台管理系统
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">


                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${ctx}/static/plugin/ace_1.3.1/assets/avatars/user.jpg"
                             alt="Jason's Photo"/>
								<span class="user-info">
									<small>欢迎您,</small>
									${user.userCd}
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                    <!--
                        <li>
                            <a href="javascript:;" id="userInfo">
                                <i class="ace-icon fa fa-user"></i>
                                个人信息修改
                            </a>
                        </li>
  					-->
                       <%-- <li class="divider"></li>--%>
                        <li>
                            <a href="javascript:;" id="userPwUpdate">
                                <i class="ace-icon fa fa-user"></i>
                                修改密码
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="${ctx}/logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar responsive sidebar-fixed sidebar-scroll">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <!-- #section:basics/sidebar.layout.shortcuts -->
                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>

                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div>
        <!-- /.sidebar-shortcuts -->
        <ul class="nav nav-list">
            <jsp:include page="menu.jsp"/>
        </ul>
        <!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
   
       
      <div class="">
		<div id="user-profile-2" class="user-profile">
			<div class="tabbable" style="margin-top:1px;position:relative;top:5px;height:37px;">
				<ul class="nav nav-tabs padding-10" id="stTab">
					<li class="active">
						<a data-toggle="tab" href="#home">
			            <i class="green ace-icon fa fa-user bigger-120"></i>
							首页
						</a>
					</li>
				</ul>

				
			</div>
		</div>
	</div>

        <!-- /section:basics/content.searchbox -->
        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content padding-2" style="margin-top:4px;">
            <!-- #section:settings.box -->

            <!-- /.ace-settings-container -->

            <!-- /section:settings.box -->
            <div class="page-content-area">

                <div class="row">
                    <!-- /.page-header -->
                    <%--<div class="col-xs-12">--%>


                    <div class="widget-box" style="border:0">
                      
                        <div id="myspin"></div>
                             <div class="widget-main" style="padding:0;">
                                    <%--<div class="scrollable">--%>
                                    <div class="tab-content " id="tab-content"
                                         style="padding:0;height: 520px;overflow:hidden;">
                                        <div id="home" class="tab-pane in active">
                                            <h4>欢迎使用后台管理系统</h4>


                                        </div>

                                    </div>

                            </div>


             
                        <%--</div>--%>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content-area -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    var tabSize = 7;
    jQuery(function ($) {
//        ace.settings.check('sidebar', 'collapse');
        $("#userInfo").on("click", function () {
            $(".nav.nav-list").find("a.stmenu[menuname='个人信息修改']").trigger("click");
        });
//
        $(".stmenu").on("click", function () {
            if ($(this).attr("isLeaf") == "true") {
                if (!$(this).parents("li").hasClass("active")) {
                    $("li.active").removeClass("open").parentsUntil(".nav", "ul").removeClass("nav-show").addClass("nav-hide").css("display", "none");
                }
                $(".nav").find("li.active").removeClass("active");
                $(this).parents("li").addClass("active");
                addTab($(this).attr("menuId"), $(this).attr("menuName"), $(this).attr("url"),$(this).parents("li.open").children().first().attr("icon"));
            }
        });


        $('.widget-box').on('reload.ace.widget', function (e) {
            $("#myspin").spin(defaultSpinOpt);
            var memuId = $("#stTab").find("li.active").attr("memuId");
            $("#iframe-" + memuId).attr("src", $("#iframe-" + memuId).attr("src"));
//            setTimeout(function(){$("#myspin").spin(false);},1000)
            return false;
        });

        $('.widget-box').on('close.ace.widget', function (e) {
            $("#stTab").find("li.active span.closeable").trigger("click");
            return false;
        });

        $('.widget-box').on('fullscreened.ace.widget', function (e) {
            if ($(".fullscreen").hasClass("fullscreen")) {//放大
                $(".tab-content").height($(".widget-box").height() - $(".tab-content").offset().top - 10);
            } else {//还原
                $(".tab-content").height($(".widget-box").height() - $(".tab-content").offset().top - 13);
            }
        });
        $(".tab-content").height($(window).height() - $(".tab-content").offset().top - 20);
    });

    function addTab(id, title, url,icon) {
            if ($("#li-" + id).length > 0) {
            	$("#myspin").spin(defaultSpinOpt);
                $(".main-title").text($("#li-" + id + " a").text());
//                ("#stTab li:not('.active')").addClass("active");
                $("#stTab li").find(".ace-icon").removeClass("red");
                $("#li-" + id + " a").trigger("click");
                $("#iframe-" + id).attr("src", url);
                $("#iframe-" + id).load(function () {
                  //  this.height = $(".tab-content").height();
                    $("#myspin").spin(false);
                });
            } else {
            	
                if($("#stTab li").size()>=tabSize){
               	 addGritter("error", "^_^您最多只能打开"+tabSize+"个不同的菜单,请您关闭一些菜单后，重新点击");
               	return false;
               }
            $("#myspin").spin(defaultSpinOpt);
            $("#stTab li").removeClass("active");
            $("#stTab li .ace-icon").removeClass("red");
            $("#tab-content div").removeClass("active");
            $("#stTab").append("<li class='active ' id='li-" + id + "' memuId='" + id + "'><a data-toggle ='tab' class='action-buttons' href='#" + id + "'><i class='iframeMenuIcon "+icon+" menuIcon'></i>" + title + " <span class='ace-icon fa fa-times-circle red closeable'></span></a></li>");
            $("#tab-content").append('<div id="' + id + '" class="tab-pane in active" style="overflow:hidden;"><iframe id="iframe-' + id + '" scrolling="auto" frameborder="0"  src="' + url + '"  frameborder="0" height="100%"  width="100%"></iframe></div>');
            $("#li-" + id ).siblings().find(".iframeMenuIcon").removeClass("iframeMenuIcon");
            $("#iframe-" + id).load(function () {
                this.height = $(".tab-content").height();
                $("#myspin").spin(false); 
            });
            $(".main-title").text(title);
            $("#stTab").find("span.closeable").on("click", function (e) {
                e.preventDefault();
                if ($(this).closest("li").hasClass("active")) {
                    $(this).closest("li").prev().find("a").trigger("click");
                }
                $("#" + $(this).closest("li").attr("memuId")).remove();
                $(this).closest("li").remove();
            });
         }
    }
    $(window).resize(function () {
        $(".tab-content").height($(window).height() - $(".tab-content").offset().top - 20);
    });
    
    $(function(){
    	   $("body").on("click","#stTab li" ,function () {
               $(".main-title").text($(this).text());
               $("#stTab li").find(".ace-icon").removeClass("red");
               $(this).find(".ace-icon").addClass("red");
               $("#stTab li").find(".iframeMenuIcon").removeClass("iframeMenuIcon");
               $(this).find("i").addClass("iframeMenuIcon");
           });
    });

    $("#userPwUpdate").on("click", function () {
        bootbox.dialog({
            title : "修改密码",
            message : "<div class='well ' style='margin-top:25px;'><form class='form-horizontal' role='form'><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtOldPwd'>旧密码</label><div class='col-sm-9'><input type='password' id='txtOldPwd' name='txtOldPwd' placeholder='请输入旧密码' class='col-xs-10 col-sm-9' /></div></div><div class='space-4'></div><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtNewPwd1'>新密码</label><div class='col-sm-9'><input type='password' id='txtNewPwd1'  name='txtNewPwd1' placeholder='请输入新密码' class='col-xs-10 col-sm-9' /></div></div><div class='space-4'></div><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtNewPwd2'>确认新密码</label><div class='col-sm-9'><input type='password' id='txtNewPwd2'  name='txtNewPwd2' placeholder='再次输入新密码' class='col-xs-10 col-sm-9' /></div></div></form></div>",
            buttons : {
                "success" : {
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var txt1 = $("#txtOldPwd").val();
                        var txt2 = $("#txtNewPwd1").val();
                        var txt3 = $("#txtNewPwd2").val();

                        if(txt1 == "" || txt2 == "" || txt3 == ""){
                            bootbox.alert("密码项不能为空");
                            return false;
                        }
                        if(txt2 != txt3 ){
                            bootbox.alert("两次输入新密码不一致，请重新输入!");
                            return false;
                        }
                        doPost("${ctx}/pcf/user/updateUserPw?oldPassword="+txt1+"&newPassword="+txt2, function (data) {
                            if (data.success) {
                                //退出登录
                                window.location.href="${ctx}/logout";
                            } else {
                                addGritter("error", data.message);
                            }
                        }, "json")
                    }
                },
                "cancel" : {
                    "label" : "<i class='icon-info'></i> 取消",
                    "className" : "btn-sm btn-danger",
                    "callback" : function() { }
                }
            }
        });
    });

</script>

</body>
</html>

