<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:forEach items="${menus}" var="m">
    <c:choose>
        <c:when test="${ empty m.children}">
            <li class="">
                <a href="javascript:;" class="stmenu" menuId="menu-${m.id}" isLeaf="true" url="${ctx}${m.url}"
                   menuName="${m.name}">
                    <i class="menu-icon fa fa-angle-double-right"></i>
                    <span class="menu-text"> ${m.name} </span>
                </a>
                <b class="arrow"></b>
            </li>
        </c:when>
        <c:otherwise>
            <li class="">
                <a href="javascript:;" class="dropdown-toggle stmenu" menuId="menu-${m.id}" isLeaf="false" icon="${m.icon }"
                   menuName="${m.name}">
                    <i class="menu-icon ${m.icon }"></i>
                    <span class="menu-text"> ${m.name} </span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <c:set var="menus" value="${m.children}" scope="request"/>
                    <jsp:include page="menu.jsp"/>
                </ul>
            </li>
        </c:otherwise>
    </c:choose>
</c:forEach>