<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-My97DatePicker.jsp"%>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="scriptureForm">
            <input name="id" value="${buProjectTemple.id}" type="hidden">
            <input name="pageviews" value="${buProjectTemple.pageviews}" type="hidden">
<%--              <input name="hiddentId" value="${buProjectTemple.tId}" type="hidden"> --%>
            <input id="opt" name="opt" value="${opt}" type="hidden">
            <!-- #section:elements.form -->
              <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">寺院<span style="color: red">*</span></label>
                <div class="col-sm-4">
                     <select class="chosen-select" id="tId" name="tId" data-placeholder="请选择">	                    
	                    <c:forEach items="${temple}" var="item" >
                             <option value="${item.id}">${item.name}</option>               
                         </c:forEach>
	                  </select>
                </div>
            </div>
            
             <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">善筹封面<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="url" name="url">${buProjectTemple.url}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('url',{
                            initialFrameWidth :1000,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">标题<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="title" name="title" value="${buProjectTemple.title}" placeholder="标题" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">筹款总额<span style="color: red">*</span></label>
                <div class="col-sm-9">
                         <input type="text" id="targetMoney" name="targetMoney" value="${buProjectTemple.targetMoney}" placeholder="筹款总额" class="col-xs-10 col-sm-5"/> 元
                </div>
            </div>
           
            
             <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">内容<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="content" name="content">${buProjectTemple.content}</textarea>
                    <script type="text/javascript"> 
				    	var ue = UE.getEditor('content',{
				 			initialFrameWidth :1000,//设置编辑器宽度
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
                <label class="col-sm-1 control-label no-padding-right" for="sort">排序键<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="sort" name="sort" value="${buProjectTemple.sort}" placeholder="排序键" class="col-xs-10 col-sm-5"/>
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
    var param = $("#opt").val();
    var url = "${ctx}/buddha/server/project/insertBuProjectTemple";
	$(function(){
		var ts = '${buProjectTemple.tId}';
		$("#tId").find("option").each(function(idx,dom){
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
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#scriptureForm').valid()) {
                return false;
            }
        	if("edit"==param){
        		url = "${ctx}/buddha/server/project/updateBuProjectTemple";
        	}
            doPost(url, $("#scriptureForm").serialize(), function (data) {
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
    $('#scriptureForm').validate(jValidateOption({
        rules: {
        	title: {required: true,maxlength:32},
        	tId: {required: true,maxlength:100},       	  
            sort:{required: true,number:true,min:0}           
        },

        messages: {
        	title: {
                required: "请输入标题"
            },
            tId: {
                required: "请选择寺院"
            },
            sort:{
            	required: "请输入排序键"
            }
            
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/project/projectList";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('banner').setContent($("#divBanner").html());
	    UE.getEditor('content').setContent($("#content").html());
	}
	
</script>
</body>
</html>
