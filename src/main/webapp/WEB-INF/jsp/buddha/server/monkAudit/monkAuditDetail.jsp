<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="monkInfoForm">
            <input name="id" id="id" value="${monk.id}" type="hidden">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="religionType" style="width:10%">审核类型</label>
                <div class="col-sm-4">
                	<select class="form-control" id="auditStatus" name="auditStatus" style="width:97%">
                    	<option value="0" selected="selected">未审核</option>
                        <option value="1">审核通过</option>
                        <option value="2">审核未通过</option>
                    </select>
                </div>
            </div>
            <div class="form-group" id="remark">
                <label class="col-sm-1 control-label no-padding-right" for="auditRemark" style="width:10%">审核未通过原因</label>
                <div class="col-sm-9">
                    <input type="text" id="auditRemark" name="auditRemark" value="${monk.auditRemark}" placeholder="审核未通过原因" class="col-xs-10 col-sm-5"/>
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
   
	$(function(){
    	$("#auditStatus").val(${monk.auditStatus});
    	
    	if($("#auditStatus").val()==2){
    		$("#remark").show();
    	}else{
    		$("#remark").hide();
    	}
    	
    	$('#auditStatus').change(function(){ 
    		var p1=$(this).children('option:selected').val();//这就是selected的值 
    		if(p1==2){
    			$("#remark").show();
    		}else{
    			$("#remark").hide();
    		}
   		});
    	
        // 提交操作
        $(".subm").on("click", function () {
        	doPost("${ctx}/buddha/server/monkAudit/updateMonkAudit", $("#monkInfoForm").serialize(), function (data) {
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
	
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/monkAudit/monkList";
	}
</script>
</body>
</html>
