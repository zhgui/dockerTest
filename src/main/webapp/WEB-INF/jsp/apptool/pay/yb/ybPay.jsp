<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK" />    
</head>
<body>
    <form action="${req.nodeAuthorizationURL}" method="post" id="form1" name="form1">
        <input type='hidden' name='p0_Cmd'   value='${req.p0_Cmd}'>
        <input type='hidden' name='p1_MerId' value='${req.p1_MerId}'>
        <input type='hidden' name='p2_Order' value='${req.p2_Order}'>
        <input type='hidden' name='p3_Amt'   value='${req.p3_Amt}'>
        <input type='hidden' name='p4_Cur'   value='${req.p4_Cur}'>
        <input type='hidden' name='p5_Pid'   value='${req.p5_Pid}'>
        <input type='hidden' name='p6_Pcat'  value='${req.p6_Pcat}'>
        <input type='hidden' name='p7_Pdesc' value='${req.p7_Pdesc}'>
        <input type='hidden' name='p8_Url'   value='${req.p8_Url}'>
        <input type='hidden' name='p9_SAF'   value='${req.p9_SAF}'>
        <input type='hidden' name='pa_MP'    value='${req.pa_MP}'>
        <input type='hidden' name='pd_FrpId' value='${req.pd_FrpId}'>
        <input type="hidden" name="pr_NeedResponse"  value="${req.pr_NeedResponse}">
        <input type='hidden' name='hmac'     value='${req.hmac}'>                              
    </form>
    <script type="text/javascript">
        document.getElementById("form1").submit();
    </script>
</body> 
</html>