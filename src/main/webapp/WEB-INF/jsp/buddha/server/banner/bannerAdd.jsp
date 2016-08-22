<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="bannerForm">
            <div class="space-1"></div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="" placeholder="名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
			<div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="path">图片<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="path" name="path"></textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('path',{
                            initialFrameWidth :900,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="url">跳转地址<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="url" name="url" value="" placeholder="跳转地址" class="col-xs-10 col-sm-5"/>
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
                    <button class="btn" type="reset" onclick="clearUeditor()">
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
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#bannerForm').valid()) {
                return false;
            }
            doPost("${ctx}/buddha/server/banner/insertBanner", $("#bannerForm").serialize(), function (data) {
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
    $('#bannerForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            }
        },

        messages: {
        	name: {
                required: "请输入寺院名称"
            }
        }
    }));
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/banner/bannerList";
	}
</script>
</body>
</html>
