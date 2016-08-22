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
        <form class="form-horizontal" role="form" id="buMusicInfoForm"enctype="multipart/form-data" method="post">
            <input name="id" value="${buMusic.id}" type="hidden">
            <input id="statusValue" value="${buMusic.status}" type="hidden">
            <input id="param1" name="param1" value="${param1}" type="hidden">
            <div style="display: none" id="divPicture">${buMusic.picture}</div>
            <!-- #section:elements.form -->
            <div class="form-group" id="musicName">
                <label class="col-sm-1 control-label no-padding-right" for="name">歌名</label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${buMusic.name}" placeholder="音乐地址" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group" id="authorName">
                <label class="col-sm-1 control-label no-padding-right" for="author">作者</label>
                <div class="col-sm-9">
                    <input type="text" id="author" name="author" value="${buMusic.author}" placeholder="作者" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="ptId">类型<span style="color: red">*</span></label>
                <div class="col-sm-4">
                    <select class="chosen-select" id="ptId" name="ptId" data-placeholder="请选择">
                        <c:forEach items="${practiceTypeList}" var="item" >
                            <option value="${item.id}">${item.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="picture">图片</label>
                <div class="col-sm-9">
                    <textarea id="picture" name="picture">${buMusic.picture}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('picture',{
                            initialFrameWidth :900,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>
		<div class="form-group" id="listen" style="display: none;">
				<label class="col-sm-1 control-label no-padding-right" for="name">试听音乐</label>
				<div class="col-sm-9" style="margin-top: 8px;">
					<audio controls="controls">
					<source src="http://music.fotuozi.com/BUDDHA_MUSIC/${buMusic.fileUrl}" />
				</audio>
				</div>
			</div>
            <div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="name">上传音乐<span
					style="color: red">*</span></label>
				<div class="col-sm-9" style="margin-top: 8px;">
					<input type="file" name="file">
				</div>
			</div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="url">音乐地址</label>
                <div class="col-sm-9">
                    <input type="text" id="url" name="url" value="${buMusic.url}" placeholder="音乐地址" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
 			<div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-9">
                        	<select  id="status" name="status">
		                		<option value="1">启用</option>
		                    	<option value="0">禁用</option>
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
   
    var url = "${ctx}/buddha/server/buMusic/saveBuMusic";
	$(function(){


        var ts = '${buMusic.ptId}';
        $("#ptId").find("option").each(function(idx,dom){
            if ($(this).val() == ts) {
                $(this).attr("selected","");
            }
        });

        $('.chosen-select').chosen({allow_single_deselect:true,no_results_text: "未找到匹配项！",search_contains: true});
        $(window).off('resize.chosen').on('resize.chosen', function() {
            $('.chosen-select').each(function() {
                var $this = $(this);
                $this.next().css({'width': $this.parent().width()});
            });
        }).trigger('resize.chosen');



		var param = $("#param1").val();
        // 提交操作
        if(param=="edit"){
        	getStatus();
        	$("#listen").css("display","block"); 
        	$("#musicName").css("display","block"); 
        }
        $(".subm").on("click", function () {
        	if("edit"==param){
        		url = "${ctx}/buddha/server/buMusic/updateBuMusic";
        	}
        	$("#buMusicInfoForm").ajaxSubmit({
                data: {},
                type: "post",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: url,
                success: function (data) {
                	if (data.success) {
                    	bootbox.alert(data.message, function (e) {
                        	localhref();
                        });
                    } else {
                        addGritter("error", data.message);
                    }
                }
            });

        });
    });
	
	function getStatus(){
		var statusValue=$("#statusValue").val();
		if(statusValue==0){
			$("#status option[ value='0']").attr("selected", true); 
		}else if(statusValue==1){
			$("#status option[ value='1']").attr("selected", true);
		}
	}
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/buMusic/buMusicList";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('picture').setContent($("#divPicture").html());
	}
	
</script>
</body>
</html>
