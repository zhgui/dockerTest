<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="st" tagdir="/WEB-INF/tags" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-fonts.css"/>
<!-- gritter -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/jquery.gritter.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-skins.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace-rtl.min.css"/>
<link rel="stylesheet" href="${ctx}/static/css/mystyle.css"/>

<script>
  var ctx = '${ctx}';
</script>
