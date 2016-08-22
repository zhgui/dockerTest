<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- basic scripts -->

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

<!-- jqueryTimepickerAddon
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/smoothness/jquery-ui.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-addon.css"/>
<link rel="stylesheet" href="${ctx}/static/plugin/jqueryTimepickerAddon/myAddon.css"/>
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-addon.js"></script>
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery.ui.datepicker-zh-CN.js.js" ></script>
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-zh-CN.js"></script>
 -->

<link type="text/css" href="${ctx}/static/plugin/jqueryTimepickerAddon/smoothness/jquery-ui.css" rel="stylesheet" />
<link href="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-addon.css" type="text/css" />
<link href="${ctx}/static/plugin/jqueryTimepickerAddon/myAddon.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript" src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<!--中文-->
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery.ui.datepicker-zh-CN.js.js" type="text/javascript" charset="gb2312"></script>
<script src="${ctx}/static/plugin/jqueryTimepickerAddon/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
