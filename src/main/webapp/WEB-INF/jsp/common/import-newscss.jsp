<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="st" tagdir="/WEB-INF/tags" %>

<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/</c:set>
<base href="${basePath}">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="Shortcut Icon" href="${ctx}/static/img/favicon.ico">
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>

<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/jquery.gritter.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/ace_1.3.1/assets/css/ace.min.css"/>

<link rel="stylesheet" href="${ctx}/static/css/mystyle.css"/>
<script>
  var ctx = '${ctx}';
</script>
	<style>
		.container .row .col-md-12 p{
	  	  text-indent:0 !important;
		}
		 .btn_like{ 
		 	width: 55px;
			height: 55px;
			background: url('${ctx}/static/img/zan.png') no-repeat;
			margin: 0 auto;
			position: relative;
			background-size: 100% auto;
		 }
		 .container .row .col-md-12 p{
		 	  text-indent:0 !important;
		 }
		 #newsLoaded img{ width:80%; display:block; margin:.5em auto;}
		 .btn_like label{ 
			color: white;
			position: absolute;
			bottom: 0;
			line-height: 21px;
			font-size: 12px;
			width: 100%;
			left: 0;
			-webkit-transform: scale(0.85);
		 }
		 .btn_liked{ background-position: 0 bottom; }
		 .count_view{line-height: 26px;margin: 0 auto;position: relative;color:#393939;text-align: center;}
	</style>