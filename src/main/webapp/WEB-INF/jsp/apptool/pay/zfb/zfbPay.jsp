<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%    
    String itemUrl = (String)request.getAttribute("itemUrl");
    response.sendRedirect(itemUrl);
%>