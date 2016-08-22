<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="appVersionForForm" >
        	<input type="hidden" id="appVersionId" name="appVersionId" value="${atAppVersion.appVersionId}">
            <div style="display: none" id="hiddenDiv1">${atAppVersion.versionInformation}</div>
            <div class="space-1"></div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="appName">App名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <label>${atAppVersion.appName}</label>
                </div>
            </div>
             
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="appType">App类型<span style="color: red">*</span></label>
                <div class="col-sm-2">
                    <c:if test="${atAppVersion.appType == '2'}"><label>苹果IOS</label></c:if>
                    <c:if test="${atAppVersion.appType == '1'}"><label>安卓</label></c:if>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="businessType">应用类型<span style="color: red">*</span></label>
                <div class="col-sm-2">
                        <c:if test="${atAppVersion.businessType == '1'}"><label>佛子</label></c:if>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="versionNo">App版本<span style="color: red">*</span></label>
                <div class="col-sm-9">
                     <label>${atAppVersion.versionNo}</label>
                </div>
            </div>          
            
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="appStatus">状态</label>
                <div class="col-sm-9">
                    <span>
                        <input id="isUseNo" name="appStatus" type="radio" class="ace" value="1" />
                        <span class="lbl">有效</span>
                    </span>
                    <span>
                        <input id="isUseOk" name="appStatus" type="radio" class="ace" value="0" />
                        <span class="lbl">无效</span>
                    </span>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="updateMechanism">更新机制</label>
                <div class="col-sm-9">
                    <span>
                        <input id="noForceUpdate" name="updateMechanism" type="radio" class="ace" value="0" />
                        <span class="lbl">不强制更新</span>
                    </span>
                    <span>
                        <input id="forceUpdate" name="updateMechanism" type="radio" class="ace" value="1" />
                        <span class="lbl">强制更新</span>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="messageNotice">短信通知</label>
                <div class="col-sm-9">
                    <span>
                        <input id="noNotice" name="messageNotice" type="radio" class="ace" value="0"checked="checked"/>
                        <span class="lbl">不短信通知</span>
                    </span>
                    <span>
                        <input id="notice" name="messageNotice" type="radio" class="ace" value="1" />
                        <span class="lbl">短信通知<span style="color: red">*(因采用短信的形式通知用户,建议重大变更时使用)*</span></span>
                    </span>
                </div>
                <div class="col-sm-9" id="noticeContentDiv" style="display:none">
                    <textarea class="col-xs-10 col-sm-5" id="noticeContent" name="noticeContent" placeholder="短信通知内容" maxlength="70">${atAppVersion.noticeContent}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="versionInformation">版本变更信息<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="versionInformation" name="versionInformation">${atAppVersion.versionInformation}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('versionInformation',{
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
                                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',

                            ]]
                        });
                    </script>
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-9">
                    <button class="btn btn-info saveBtn subm"
                        type="button">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        提交
                        
                    </button>
                    &nbsp;
                    <button class="btn" type="reset" onclick="clearUeditorEditorForAPPVersion()">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        重置
                    </button>
                    &nbsp;
                    <a href="javascript:;" onClick="localhref()" class="btn btn-success">
                            <i class="ace-icon fa  fa-reply icon-only bigger-110"></i>
                            返回
                        </a>
                </div>
            </div>

        </form>
    </div>
</div>
<script type="text/javascript">

	$("input[name='appStatus'][value="+${atAppVersion.appStatus}+"]").attr("checked",true);
	$("input[name='updateMechanism'][value="+${atAppVersion.updateMechanism}+"]").attr("checked",true);
	$("input[name='messageNotice'][value="+${atAppVersion.messageNotice}+"]").attr("checked",true);
	if($("input[name='messageNotice']:checked").val()==1){
	    $("#noticeContentDiv").show();
	}
	$(function(){
        
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#appVersionForForm').valid()) {
                return false;
            }
            doPost("${ctx}/apptool/server/appversion/version/updateAppVersion", $("#appVersionForForm").serialize(), function (data) {
            	if (data.success) {
                    bootbox.alert(data.message, function (e) {
                    	localhref();
                    });
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
        });
    });
	
	// 页面项目验证
    $('#appVersionForForm').validate(jValidateOption({
        rules: {
        	appName: {
                required: true,
                maxlength:65
            },
            versionNo:{
            	required: true,
            	maxlength:65
            },
            appStatus:{
            	required:true,
            },
            versionInformation:{
                required:true
            }
        },

        messages: {
        	appName: {
                required: "请输入App名称"
            },
            versionNo:{
            	required: "请输入App版本"
            },
            appStatus:{
            	required:"请选择状态"
            },
            versionInformation:{
                required:"请输入版本变更信息"
            }
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/apptool/server/appversion/version/appVersionList";
	}
	// 不短信通知
	$("#noNotice").change(function() { 
		$("#noticeContent").val("");
		$("#noticeContentDiv").hide();
    });
	
	// 短信通知
	$("#notice").change(function() { 
		$("#noticeContent").val("");
		$("#noticeContentDiv").show();
    });
</script>
</body>
</html>