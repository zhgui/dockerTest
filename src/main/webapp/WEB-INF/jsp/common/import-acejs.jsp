<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- basic scripts -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${ctx}/static/plugin/ace_1.3.1/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/bootstrap.min.js"></script>

<!-- jqgrid -->
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>


<!--[if lte IE 8]>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.easypiechart.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.sparkline.min.js"></script>

<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/spin.min.js"></script>

<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/flot/jquery.flot.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/flot/jquery.flot.resize.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/chosen.jquery.min.js"></script>

<!-- jquery-form -->
<script src="${ctx}/static/plugin/jquery-form/jquery.form.js"></script>

<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/additional-methods.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/bootbox.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.maskedinput.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/select2.min.js"></script>

<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/jquery.gritter.min.js"></script>

<!-- ace scripts -->
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/static/plugin/ace_1.3.1/assets/js/ace.min.js"></script>
<script src="${ctx}/static/plugin/jquery.blockUI.js"></script>
<script src="${ctx}/static/js/jquery.metadata.js"></script>
<script src="${ctx}/static/js/util.js"></script>
<script src="${ctx}/static/js/jquery.bootboxBrowser.js"></script>
<script src="${ctx}/static/js/common.js"></script>
<c:if test="${fn:contains(header['User-Agent'],'MSIE')}">
    <script type="text/javascript">
        $(function () {
            $('<link rel="stylesheet" href="' + "${ctx}" + '/static/plugin/ace_1.3.1/assets/css/font-awesome.min.css"/>').prependTo("head");
        });
    </script>
</c:if> 
        


