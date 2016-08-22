<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-My97DatePicker.jsp"%>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="merchantForm">
            <input name="merchId" value="${merchant.merchId}" type="hidden">
            <input id="opt" name="opt" value="${opt}" type="hidden">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="title">企业名字<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="merchantName" name="merchantName" value="${merchant.merchantName}" placeholder="企业名字" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
             <div class="form-group">
                 <label class="col-sm-1 control-label no-padding-right" for="licenseURL">企业执照<span style="color: red">*</span></label>
                 <div class="col-sm-4">
                    <img src="${merchant.licenseURL}" height="200" width="200" />
                 </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="legalPerson">企业法人</label>
                <div class="col-sm-9">
                   <input type="text" id="legalPerson" name="legalPerson" value="${merchant.legalPerson}" placeholder="企业法人" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div  class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">法人身份号码<span style="color: red">*</span></label>
                <div class="col-sm-9">
                     <input type="text" id="identityNumber" name="identityNumber" value="${merchant.identityNumber}" placeholder="法人身份号码" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="available"> 状态 </label>
                <div class="col-sm-9">
                    <span>
                     <input type="radio" id="available"  name="available" value="1" class="ace" <c:if test="${merchant.available==1}">checked</c:if> />
                        <span class="lbl"> 有效 </span>
                    </span>
                    <span>
                     <input type="radio" id="available"  name="available" value="0" class="ace" <c:if test="${merchant.available==0}">checked</c:if> />
                        <span class="lbl"> 无效 </span>
                    </span>
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
                    <button class="btn" type="reset" onclick="clearUeditor1()">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        重置
                    </button>
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
    var url = "${ctx}/shop/server/merchant/createOrUpdate";
	$(function(){
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#merchantForm').valid()) {
                return false;
            }
            doPost(url, $("#merchantForm").serialize(), function (data) {
            	if (data.success) {
                    bootbox.alert(data.message, function (e) {
                    	window.location.href="${ctx}/shop/server/merchant/list";
                    });
                } else {
                    addGritter("error", data.message);
                }
            }, "json");
        });
    });
	
	// 页面项目验证
    $('#scriptureForm').validate(jValidateOption({
        rules: {
        	merchantName: {required: true,maxlength:100},
        	legalPerson: {required: true,maxlength:32},
        	identityNumber:{required: true,maxlength:32}          
        },

        messages: {
        	merchantName: {
                required: "请插入企业名字"
            },
            legalPerson: {
                required: "请插入企业法人"
            },
            identityNumber:{
            	required: "请插入法人身份号码"
            }
            
        }
    }));
	
</script>
</body>
</html>
