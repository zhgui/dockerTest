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
        <form class="form-horizontal" role="form" id="serveInfoForm">
            <input name="id" id="id" value="${serve.id}" type="hidden">
            <input id="param1" name="param1" value="${param1}" type="hidden">
            <input id="statusTmp" name="statusTmp" value="${serve.status}" type="hidden">
            <div style="display: none" id="divImgUrl">${serve.imgUrl}</div>
            <div style="display: none" id="divBanner">${serve.banner}</div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${serve.name}" placeholder="名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="imgUrl">图标<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="imgUrl" name="imgUrl">${serve.imgUrl}</textarea>
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
            
            <div id="divBannerDiv" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">轮播图<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="banner" name="banner">${serve.banner}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('banner',{
                            initialFrameWidth :900,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="detail">服务内容</label>
                <div class="col-sm-9">
                    <textarea id="remark" name="remark">${serve.remark}</textarea>
                    <script type="text/javascript"> 
				    	var ue = UE.getEditor('remark',{
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
                <label class="col-sm-1 control-label no-padding-right" for="status">状态</label>
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
   
    var url = "${ctx}/buddha/server/serve/saveBuServe";
	$(function(){
	
            //获取状态
            //获取单位存入字段中
            var ts = '${monk.templeId}';
            $("#templeId").find("option").each(function(idx,dom){
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
		//getTempleList();
		var param = $("#param1").val();
	    if("edit"==param){
	    	//alert($("#statusTmp").val());
	    	$("#status").val($("#statusTmp").val());
	    }
	    //if("add"==param){
	    	
	   // }
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#serveInfoForm').valid()) {
                return false;
            }
        	if("edit"==param){
        		url = "${ctx}/buddha/server/serve/updateBuServe";
        	}
            doPost(url, $("#serveInfoForm").serialize(), function (data) {
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
    $('#serveInfoForm').validate(jValidateOption({
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
            	required: "请输入服务费"
            }
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/serve/buServeList";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('imgUrl').setContent($("#divImgUrl").html());
		UE.getEditor('banner').setContent($("#divBanner").html());
	}
	
	function getTempleList(){
	    $.ajax({
	        url : '${ctx}/buddha/server/temple/getTempleList',
	        cache : true,
	        async : false,
	        data:{},
	        type : "POST",
	        dataType : 'json',
	        success : function (result) {
	        	var obj = result.responseData;
	            $("select[name='templeId']").empty();
	            $("select[name='templeId']").append("<option value=-1>请选择</option>");
	            for (var i = 0; i < obj.length; i++) {
	                $("select[name='templeId']").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
	            }
	        },
	        error:function(result) {
	            alertError(result.message);
	        }
	    });
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
