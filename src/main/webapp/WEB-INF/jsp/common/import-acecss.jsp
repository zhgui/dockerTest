<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="st" tagdir="/WEB-INF/tags" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="Shortcut Icon" href="${ctx}/static/img/favicon.ico">
<!-- bootstrap & fontawesome -->
<!--bootstrap.min.css的原本源代码 被拆分成了两部分 1：bootstrap.min.css 2：bootstrap-font-face.css-->
<!--如果是IE则只先加载不含web字体的css文件，在import-acejs.jsp中延时加载web字体-->
 <!--如果不是IE则，则css 以及css拆分的web字体 都不使用延时加载-->
<c:choose>
 <c:when test="${fn:contains(header['User-Agent'],'MSIE')}">
  <link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/bootstrap.min.css"/>
 </c:when>
   <c:otherwise>
   <link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/bootstrap.min.css"/>
   <link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>
   </c:otherwise>   
</c:choose>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ui.jqgrid.css" />
<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-fonts.css"/>

<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/chosen.css" />


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

<!-- ace settings handler -->
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/html5shiv.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" href="${ctx}/static/css/mystyle.css"/>
<style type="text/css">
    html {
        position: static;
        min-height: 100%;
    }
</style>
<script>
  var ctx = '${ctx}';
</script>
