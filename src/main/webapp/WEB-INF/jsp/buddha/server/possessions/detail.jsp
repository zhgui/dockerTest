<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="scriptureForm">
            <input name="id" value="${possessions.id}" type="hidden">
            <input id="hiddenSort" value="${sort}" type="hidden">
            <input id="opt" name="opt" value="${opt}" type="hidden">
            <!-- #section:elements.form -->
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                     <input type="text" id="name" name="name" value="${possessions.name}" placeholder="法物名称" class="col-xs-10 col-sm-5"/> 
                </div>
            </div>
            
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">封面图片<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="imageUrl" name="imageUrl">${possessions.imageUrl}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('imageUrl',{
                            initialFrameWidth :1000,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>
            
            <div id="divCover" class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="banner">摘要<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<textarea id="summary" name="summary"
						style="resize: none;width:41.666%;height:80px">${possessions.summary}</textarea>
						<span
					style="color: red">最多输入50个字</span>
				</div>
			</div>
			
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">市场价<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="price" name="price" value="${possessions.price}" placeholder="市场价" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">优惠价<span style="color: red">*</span></label>
                <div class="col-sm-9">
                         <input type="text" id="discountPrice" name="discountPrice" value="${possessions.discountPrice}" placeholder="优惠价" class="col-xs-10 col-sm-5"/> 元
                </div>
            </div>
           
            
             <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">内容<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="content" name="content">${possessions.content}</textarea>
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
                    <input type="text" id="sort" name="sort" value="${possessions.sort}" placeholder="排序键" class="col-xs-10 col-sm-5"/>
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
    var url = "${ctx}/buddha/server/possessions/insert";
	$(function(){
		if("add"==param){
	    	$("#sort").val($("#hiddenSort").val());
	    }
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#scriptureForm').valid()) {
                return false;
            }
        	if("edit"==param){
        		url = "${ctx}/buddha/server/possessions/update";
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
        
        $("#summary").keydown(function() {
    		if ($(this).val().length >= 50){
    			bootbox.alert("输入字数不得超过50");
    		}
    	});
    });
	// 页面项目验证
    $('#scriptureForm').validate(jValidateOption({
        rules: {
        	name: {required: true,maxlength:32},
        	summary: {required: true,maxlength:100},       	  
            sort:{required: true,number:true,min:0},
            price:{required: true,number:true,min:0}, 
            discountPrice:{required: true,number:true,min:0}, 
        },

        messages: {
        	name: {
                required: "请输入名字"
            },
            summary: {
                required: "请输入摘要"
            },
            sort:{
            	required: "请输入排序键"
            },
            price:{
            	required: "请输入市场价"
            },
            discountPrice:{
            	required: "请输入优惠价"
            }
            
            
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/possessions/list";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('imageUrl').setContent($("#imageUrl").html());
	    UE.getEditor('content').setContent($("#content").html());
	}
	
</script>
</body>
</html>
