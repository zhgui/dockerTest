<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>buddhaApp</title>

    <meta name="description" content="buddhaApp"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-12  text-center">
            <h4> ${buMonlam.name}</h4>
        </div>
    </div>

    <div class="row" style="text-align: center;">
	     <span class="" style="color:#888f93;">
	     		<fmt:formatDate value="${buMonlam.modifyTime}" type="both" pattern="MM-dd HH:mm"/>
	     </span>
    </div>
    
    <div class="row">
<%--         <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>主会人：<span style="color:red"> ${monkName}</span></h4>
        </div> --%>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>寺院： <span style="color:red">${templeName}</span></h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>预参加人数：<span style="color:red">${buMonlam.sumNum}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>实际参加人数： <span style="color:red">${buMonlam.realNum}</span></h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>开始时间：<span style="color:red"><fmt:formatDate value="${buMonlam.monlamStartTime}" type="both" pattern="MM-dd HH:mm"/></span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>结束时间： <span style="color:red"><fmt:formatDate value="${buMonlam.monlamEndTime}" type="both" pattern="MM-dd HH:mm"/></span></h4>
        </div>
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>宗教类别：<span style="color:red"> 
		            <c:if test="${buMonlam.type==1}">活动</c:if> 
		            <c:if test="${buMonlam.type==2}">义工</c:if> 
		            </span></h4>
        </div>
    </div>
     <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
            <h4>法会内容：</h4>${buMonlam.content}
        </div>
     </div>     
    <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
	     ${buMonlam.banner}
     </div>
     <div class="row">
	     <div class="col-sm-12 text-center">
	             <a href="<st:BackURL/>" class="btn btn-success">
	                            <i class="ace-icon fa  fa-reply icon-only bigger-110"></i>
	                            返回
	                        </a>      
      </div>
     </div>
     
</div>
<!-- /.col -->
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>
