<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>fyAPP</title>

    <meta name="description" content="佛子APP"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
</head>
<body class="no-skin" style="background-color: #fff">
<div class="container">
    <form class="form-horizontal" role="form" id="courseInfoForm">
            <input name="id" value="${feedback.id}" type="hidden">
            <div class="space-1"></div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">反馈时间<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="control-label" for="name"><fmt:formatDate value="${feedback.createTime}" type="both" pattern="yyyy/MM/dd HH:mm"/></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="mobile">联系方式<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="control-label" >${feedback.mobile}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">反馈内容<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="" >${feedback.content}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">手机型号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="" >${feedback.mobileModel}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">系统型号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="" >${feedback.systemModel}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">系统类型<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="" ><c:if test="${feedback.mobileType=='0'}">Android</c:if></label>
                    <label class="" ><c:if test="${feedback.mobileType=='1'}">iOS</c:if></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">APP版本号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label class="" >${feedback.appNumber}</label>
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-5 col-md-9">
                    <a href="<st:BackURL/>" class="btn btn-success">
                            <i class="ace-icon fa  fa-reply icon-only bigger-110"></i>
                            返回
                        </a>
                </div>
            </div>
        </form>
     
</div>
<!-- /.col -->
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>
