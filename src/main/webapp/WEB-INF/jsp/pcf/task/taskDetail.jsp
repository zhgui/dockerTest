<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="taskForForm" >
            <div class="space-1"></div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">任务类<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${pcfTask.name}"  disabled="disabled" placeholder="任务类" class="col-xs-10 col-sm-5"/>
                    <input type="hidden" id="id" name="id" value="${pcfTask.id}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="viewName">任务名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="viewName" name="viewName" disabled="disabled" value="${pcfTask.viewName}" placeholder="任务名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="groups">分组<span style="color: red">(非必须)</span></label>
                <div class="col-sm-9">
                    <input type="text" id="groups" name="groups" disabled="disabled" value="${pcfTask.groups}" placeholder="分组" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="type">类型<span style="color: red">(非必须)</span></label>
                <div class="col-sm-2">
                    <select class="form-control" id="type" name="type" disabled="disabled">
                        <option value="1">通用任务</option>
                    </select>
                </div>
            </div>
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="status">状态</label>
                <div class="col-sm-9">
                    <span>
                        <input id="isUseNo" name="status" type="radio" class="ace" value="1" disabled="disabled"/>
                        <span class="lbl">启用</span>
                    </span>
                    <span>
                        <input id="isUseOk" name="status" type="radio" class="ace" value="2" disabled="disabled"/>
                        <span class="lbl">停用</span>
                    </span>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="timeExpressionValue">任务规则<span style="color: red">*</span></label>
                <div class="col-sm-9">
                	<input type="button" id="timeExpression" name="timeExpression" value="设置任务规则">
                    <input type="hidden" id="timeExpressionValue" name="timeExpressionValue" value="${pcfTask.timeExpressionValue}" placeholder="执行时间段" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="timeExpressionName">规则说明<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="timeExpressionName" name="timeExpressionName" disabled="disabled" value="${pcfTask.timeExpressionName}" placeholder="规则说明" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="remark">任务说明<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="remark" name="remark" disabled="disabled">${pcfTask.remark}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('remark',{
                            initialFrameWidth :1200,//设置编辑器宽度
                            initialFrameHeight:250,//设置编辑器高度
                            scaleEnabled:true,
                            toolbars: [[
                                'fullscreen', 'source', '|', 'undo', 'redo', '|',
                                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch',  'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                                'directionalityltr', 'directionalityrtl', 'indent', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                                'link', 'unlink', 'anchor', '|', 'pagebreak',  'background', '|',
                                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|'
                            ]]
                        });
                    </script>
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
</div>
<script type="text/javascript">
function setCron(cron){
	 $("input[name='timeExpressionValue']") .val(cron);
	 bootbox.hideAll();
 }

$("input[name='status'][value="+${pcfTask.status}+"]").attr("checked",true);
$("#type option[value="+${pcfTask.type}+"]").attr("selected", "selected");

jQuery(function($){
    });
	
	$("#timeExpression").click(function () {
		var cron = $("#timeExpressionValue").val();
		$(this).bootboxNoCacheBrowser({
			title:"请设置任务执行规则:",
		    url:"${ctx}/pcf/task/toTimeExpressionDetail?timeExpressionValue="+$("#timeExpressionValue").val(),
			width:"100%",
			height:"540px",
			ok:"doOk",
			cancle: null,
			clear:null,
			size:"large",//size指的是宽度:默认600px；另外有两个可选属性  large：900px small：300px
			target:"" //top ：最顶层 。parent：父级window内。空字符串：当前window内
		}).showDialog();
    });

</script>
</body>
</html>