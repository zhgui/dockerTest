<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script src="${ctx}/static/plugin/jquery/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/plugin/bootstrap-3.3.0/js/bootstrap.min.js"></script>

