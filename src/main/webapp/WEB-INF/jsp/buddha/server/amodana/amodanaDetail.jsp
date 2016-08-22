<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="amodanaForm">
            <input name="id" id="id" type="hidden" value="${buAmodana.id}"/>
            <div class="space-1"></div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">随喜名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${buAmodana.name}" placeholder="随喜名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="money">随喜金额<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="money" name="money" value="${buAmodana.money}" placeholder="随喜金额" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="remark">备注</label>
                <div class="col-sm-9">
                     <textarea id="remark" name="remark"  placeholder="备注" style="resize: none;width:550px;height:150px">${buAmodana.remark}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="status">状态</label>
                <div class="col-sm-4">
                	<select class="form-control" id="status" name="status">
                    	<option value="1" ${buAmodana.status =="1"?'selected=selected':""}>启用</option>
                        <option value="0"  ${buAmodana.status =="0"?'selected=selected':""}>禁用</option>
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
                    <button class="btn" type="reset">
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
		initProvinces();
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#amodanaForm').valid()) {
                return false;
            }
            doPost("${ctx}/buddha/server/amodana/saveAmodanaOrEdit", $("#amodanaForm").serialize(), function (data) {
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
    $('#amodanaForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:100
            },
            money:{
            	required: true,
                number:true
            },
            remark:{
            	required: true
            }
        },

        messages: {
        	name: {
                required: "请输入随喜名称"
            },
            address:{
            	required: "请输入随喜金额"
            },
            remark:{
            	required: "请输入备注"
            }
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/amodana/toList";
	}
</script>
</body>
</html>
