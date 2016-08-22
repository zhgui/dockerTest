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
        <form class="form-horizontal" role="form" id="footmarkForm">
            <input id="id" name="id" value="${footmark.id}" type="hidden">
            <input id="param1" name="param1" value="${param1}" type="hidden">
            <div style="display: none" id="divBanner">${footmark.content}</div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">标题<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${footmark.name}" placeholder="标题" class="col-xs-10 col-sm-5"/>
                </div>
            </div>   
            
            <div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="monkId">法师<span
					style="color: red">*</span></label>
				<div class="col-sm-4">
					<select class="chosen-select" id="monkId" name="monkId"
						data-placeholder="请选择" >
						<c:forEach items="${buMonkList}" var="monk">
							<option value="${monk.id}">${monk.name}</option>
						</c:forEach>
					</select>

				</div>
			</div> 
            
           <%-- <div class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="monlamStartTimeVo">活动开始时间<span style="color: red">*</span></label>
				<div class="col-sm-9">
					<input class="col-xs-10 col-sm-5" type="text"
						name="beginTimeVo"
						onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="开始时间" id="beginTimeVo"
						value="${footmark.beginTime}" />

				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="">活动结束时间<span style="color: red">*</span></label>
				<div class="col-sm-9">
					<input class="col-xs-10 col-sm-5" type="text"
						name="endTimeVo"
						onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="结束时间" id="endTimeVo"
						value="${footmark.endTime}" />
				</div>
			</div>--%>
			
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="content">内容</label>
                <div class="col-sm-9">
                    <textarea id="content" name="content">${footmark.content}</textarea>
                    <script type="text/javascript"> 
				    	var ue = UE.getEditor('content',{
				 			initialFrameWidth :900,//设置编辑器宽度
				 			initialFrameHeight:450,//设置编辑器高度
				 			scaleEnabled:true,
                            toolbars: [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch',  'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                                'directionalityltr', 'directionalityrtl', 'indent', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                                'simpleupload', 'insertimage', 'pagebreak',  'background', '|','insertvideo',
                                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                                'print', 'preview']]
				 		});     
				    </script> 
                </div>
            </div>			      
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" >状态</label>
                <div class="col-sm-4">
                	<select class="form-control" id="status" name="status">
                		<option value="1" <c:if test="${footmark.status=='1'}">selected="selected"</c:if>>启用</option>
                    	<option value="0" <c:if test="${footmark.status=='0'}">selected="selected"</c:if>>禁用</option>
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

    

	$(document).ready(function () {
		 //获取状态
	    //获取单位存入字段中
		var ts = '${footmark.monkId}';
		$("#monkId").find("option").each(function(idx,dom){
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
	
	})


	$(function(){
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#footmarkForm').valid()) {
                return false;
            }
            doPost("${ctx}/buddha/server/footmark/saveOrUpdateFootmark", $("#footmarkForm").serialize(), function (data) {
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
    $('#footmarkForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            },
            tId:{
            	required: true
            },
            beginTimeVo:{
            	required: true
            },
            endTimeVo:{
            	required: true
            }
        },

        messages: {
        	name: {
                required: "请输入名称"
            },
            tId:{
            	required: "请选择法师"
            },
            beginTimeVo:{
            	required: "请选择活动开始时间"
            },
            endTimeVo:{
            	required: "请选择活动开始时间"
            }
        }
    }));
	
    function clearUeditor() {
		UE.getEditor('content').setContent($("#divBanner").html());
	}
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/footmark/footmarkList";
	}
</script>
</body>
</html>
