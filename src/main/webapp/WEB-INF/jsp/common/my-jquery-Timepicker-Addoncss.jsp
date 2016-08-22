<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="st" tagdir="/WEB-INF/tags" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- myjqueryTimepickerAddonCss -->
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/smoothness/jquery-ui.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-addon.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/myAddon.css"/>
<script>
  var ctx = '${ctx}';
</script>
