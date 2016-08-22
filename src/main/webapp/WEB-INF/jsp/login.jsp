<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>欢迎登录后台管理系统</title>
    <link rel="Shortcut Icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="欢迎登录后台管理系统">
    <meta name="author" content="">
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>
    <%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
    <!-- Le styles -->
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 600px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input,
        .form-signin input{
          
            font-size: 12px;

            line-height:12px;
            margin-bottom: 15px;
            padding: 7px 9px;
            border-radius: 4px!important;
        }

    </style>
    <link href="${ctx}/static/plugin/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.js"></script>
    <![endif]-->

</head>

<body>

<div class="container" style="margin-top:4%;">
    <h3  class="text-center">欢迎使用后台管理系统</h3>
      <div class="inputline inputline-dashed inputline-lg inputpull-in"></div>
    <form class="form-signin" method="post" action="">
    <c:if test="${!empty shiroLoginFailure}">
    <div class="clearfix" style="margin-bottom:10px;">
      <div class="pull-left alert alert-success no-margin input-block-level">
		<button type="button" class="close" data-dismiss="alert" >
		<i class="ace-icon fa fa-times" ></i>
		</button>
		 <i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
		   用户名或密码错误，认证失败，请重新输入^_^
		</div>
    </div>
    </c:if>
        <label class="control-label">用户名</label>
        <span class="block input-icon input-icon-right">
        <input type="text" class="input-block-level" id="username" name="username" placeholder="username">
        <i class="ace-icon fa fa-user"></i>
         </span>
        <label class="control-label">密码</label>
        <span class="block input-icon input-icon-right">
        <input type="password" class="input-block-level" id="password" name="password" placeholder="password">
        <i class="ace-icon fa fa-lock"></i>
        </span>
        <label class=" control-label ">
            <input type="checkbox" name="rememberMe"> 记住我
        </label>
        <button class="btn btn-sm btn-info submitBtn width-20 pull-right" type="button">登录</button>
    </form>

</div>
<!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/static/plugin/jquery/jquery-1.11.1.js"></script>
<script src="${ctx}/static/plugin/jquery/jquery.md5.js"></script>

<script type="text/javascript">

    $(function () {
        $(".submitBtn").on("click", function () {
            $("#password").val($.md5($.md5($("#username").val()+$("#password").val())));
            $("form").submit();
            //    document.location.href = "${ctx}/index";
        });
        
        $("input").keydown(function(event){
        	  if(event.keyCode ==13){
        	    $(".submitBtn").trigger("click");
        	  }
        	});

    })
</script>

</body>
</html>
