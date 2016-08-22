<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="st" tagdir="/WEB-INF/tags" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>

<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ui.jqgrid.css" />
<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-fonts.css"/>

<!-- gritter -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/jquery.gritter.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace.min.css"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-skins.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-rtl.min.css"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-ie.min.css" />
<![endif]-->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace.onpage-help.css"/>

<!-- ace settings handler -->
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/html5shiv.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" href="${ctx}/static/css/mystyle.css"/>
<script src="${ctx}/static/plugin/jquery/jquery-1.11.1.js"></script>

<script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/ueditor/ueditor.all.min.js"></script>
<link rel="stylesheet" href="${ctx}/ueditor/lang/zh-cn/zh-cn.js"/>
<script>
  var ctx = '${ctx}';
</script>
