<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-My97DatePicker.jsp"%>
<link rel="stylesheet" href="${ctx}/static/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script src="${ctx}/static/plugin/zTree_v3/js/jquery.ztree.all-3.5.js"></script>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="practiceTypeInfoForm">
            <input name="id" id="id" value="${buPracticeType.id}" type="hidden">
            <input id="param1" name="param1" value="${param1}" type="hidden">
            <input id="statusTmp" name="statusTmp" value="${buPracticeType.status}" type="hidden">
            <input id="bTypeTmp" name="bTypeTmp" value="${buPracticeType.bType}" type="hidden">
            <div style="display: none" id="divImgUrl">${buPracticeType.imgUrl}</div>
           <%-- <div style="display: none" id="divBanner">${buPracticeType.banner}</div>--%>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="title">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="title" name="title" value="${buPracticeType.title}" placeholder="名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="bType">类型<span style="color: red">*</span></label>
                <div class="col-sm-4">
                    <select class="form-control" id="bType" name="bType" style="width:97%">
                        <%--<option value="2" selected="selected">经文</option>
                        <option value="3">梵音</option>
                        <option value="4">佛学院</option>
                        --%>
                            <c:forEach items="${bTypeList}" var="item" varStatus="status">
                        <option value="${item.value()}" <c:if test="${scripture.bType==item.value()}">selected="true"</c:if>>${item.getName()}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="imgUrl">图标<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="imgUrl" name="imgUrl">${buPracticeType.imgUrl}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('imgUrl',{
                            initialFrameWidth :900,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="remark">备注</label>
                <div class="col-sm-9">
                    <textarea id="remark" name="remark" cols="73" rows="6">${buPracticeType.remark}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="status">状态<span style="color: red">*</span></label>
                <div class="col-sm-4">
                	<select class="form-control" id="status" name="status" style="width:97%">
                    	<option value="1" selected="selected">启用</option>
                        <option value="0">停用</option>
                    </select>
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
   
    var url = "${ctx}/buddha/server/practiceType/saveBuPracticeType";
	$(function(){
		var param = $("#param1").val();
	    if("edit"==param){
	    	$("#status").val($("#statusTmp").val());
            $("#bType").val($("#bTypeTmp").val());
	    }

        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#practiceTypeInfoForm').valid()) {
                return false;
            }
        	if("edit"==param){
        		url = "${ctx}/buddha/server/practiceType/updateBuPracticeType";
        	}
            doPost(url, $("#practiceTypeInfoForm").serialize(), function (data) {
            	if (data.success) {
                    bootbox.alert(data.message, function (e) {
                    	localhref();
                    });
                } else {
                    addGritter("error", data.message);
                }
            }, "json");
        });
    });
	
	// 页面项目验证
    $('#practiceTypeInfoForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            },
            serviceCharge:{
            	required: true,
                maxlength:8
            }
        },

        messages: {
        	name: {
                required: "请输入名称"
            },
            serviceCharge:{
            	required: "请输入"
            }
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/practiceType/toList";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('imgUrl').setContent($("#divImgUrl").html());
		UE.getEditor('banner').setContent($("#divBanner").html());
	}
	

	function validateNameIsExist(){
		var id=$('#id').val();
		if(id==""){
			id=0;
		}
    	doPost("${ctx}/buddha/server/monk/validateNameIsExist", 
    		{
    	    	name:$('#name').val(),
    	    	id:id
    	  	}, 
    	  	function (data) {
        	if (data.success) {
                if(data.responseData==false){
                	addGritter("warning", "法师名称已存在，可以继续录入！");
                }
            }
        }, "json");
    }
</script>
</body>
</html>
