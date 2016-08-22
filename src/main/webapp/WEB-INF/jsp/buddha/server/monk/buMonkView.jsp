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
            <h4> ${monk.name}</h4>
        </div>
    </div>

    <div class="row" style="text-align: center;">
	     <span class="" style="color:#888f93;">
	     		<fmt:formatDate value="${monk.updateTime}" type="both" pattern="MM-dd HH:mm"/>
	     </span>
    </div>
    
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>法号：<span style="color:red"> ${monk.buddhistName}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>寺院： <span style="color:red">${templeName}</span></h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>职位：<span style="color:red"> ${monk.position}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>出生年月： <span style="color:red">${monk.born}</span></h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>祖籍：<span style="color:red"> ${monk.ancestralHome}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>学位： <span style="color:red">${monk.degree}</span></h4>
        </div>
    </div> 
    <div class="row">
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>戒腊：<span style="color:red"> ${monk.ringNa}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>皈依本师： <span style="color:red">${monk.convertTeacher}</span></h4>
        </div>
    </div>
    <div class="row">
    <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
                    <h4>戒师： <span style="color:red">${monk.acariya}</span></h4>
        </div>
        <div class="col-md-4 col-md-4 col-md-offset-2  text-left">
		            <h4>宗教类别：<span style="color:red"> 
		            <c:if test="${monk.religionType==1}">佛教</c:if> 
		            <c:if test="${monk.religionType==2}">道教</c:if> 
		            <c:if test="${monk.religionType==3}">基督教</c:if> 
		            </span></h4>
        </div>
    </div>
     <div class="row" style="text-align: center;">
     <h4>头像：<span style="color:red"> </h4>
	     ${monk.backgroundImage}
     </div>
     <div class="row" style="text-align: center;">
     <h4>背景图片：<span style="color:red"> </h4>
	     ${monk.banner}
     </div>
     
     <div class="row">
        <div class="col-sm- col-md-12">
            ${monk.detail}
        </div>
     </div>
     
     <div class="row">
        <div class="col-sm- col-md-12">
            ${monk.introduce}
        </div>
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
