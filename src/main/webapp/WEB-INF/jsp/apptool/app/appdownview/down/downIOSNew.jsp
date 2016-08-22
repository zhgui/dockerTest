<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>迎智 APP下载页</title>
</head>
<body>
<div style="text-align: center;margin-top: 100px;font-size: 50px;"><img style="height: 140px;"
                                                                        src="${ctx}/static/img/ctc.jpg">

</div>
<div style="text-align: center;margin-top: 10px;font-size: 50px;">
    <a href="itms-services://?action=download-manifest&url=${plistUrl}"
       style="font-size: 50px;">点击安装迎智APP</a>
</div>
<div style="text-align: left;margin-left:30px;margin-top: 100px;font-size: 35px;">
    如遇到无法正装安装情况，请先卸载旧版本后再次安装
</div>
</body>
</html>