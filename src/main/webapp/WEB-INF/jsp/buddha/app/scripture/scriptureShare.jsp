<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${ctx}/static/css/sharestyle.css" type="text/css"/>
</head>
<body>
	<div class="book_top"><img src="${ctx}/static/img/book-logo.png" width="25%" /></div>
        <div class="book_container">
                <div class="book-title">${scripture.title}</div>
                <div class="book-info">${scripture.translator}</div>
                <div class="book-info">${scripture.disclosure}</div>
                <div class="book-txt">
                        ${scripture.content}
                </div>
        </div>
</body>
</html>
