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
        <form class="form-horizontal" role="form" id="monkInfoForm">
            <input name="id" id="id" value="${monk.id}" type="hidden">
            <input id="param1" name="param1" value="${param1}" type="hidden">
            <input id="hiddenSort" name="hiddenSort" value="${sort}" type="hidden">
            <div style="display: none" id="divBanner">${monk.banner}</div>
            <div style="display: none" id="divDetail">${monk.detail}</div>
            <div style="display: none" id="divIntroduce">${monk.introduce}</div>
            <input id="editIsRz" name="editIsRz" value="${monk.isRz}" type="hidden">
            <input id="editIsTj" name="editIsTj" value="${monk.isTj}" type="hidden">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${monk.name}" placeholder="名称" class="col-xs-10 col-sm-5" onBlur="validateNameIsExist()"/>
                </div>
            </div>
            
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">头像<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="banner" name="banner">${monk.banner}</textarea>
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
            
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="backgroundImage">背景图片</label>
                <div class="col-sm-9">
                    <textarea id="backgroundImage" name="backgroundImage">${monk.backgroundImage}</textarea>
                    <script type="text/javascript">
                        var ue = UE.getEditor('backgroundImage',{
                            initialFrameWidth :900,//设置编辑器宽度
                            initialFrameHeight:150,//设置编辑器高度
                            toolbars: [['source','simpleupload']],
                            scaleEnabled:true
                        });
                    </script>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="buddhistName">法号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="buddhistName" name="buddhistName" value="${monk.buddhistName}" placeholder="法号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <%--<div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="templeId">寺院<span style="color: red">*</span></label>
                <div class="col-sm-4">
                    <select id="templeId" name="templeId" style="width:97%">
                    </select>
                </div>
            </div>--%>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="templeId">寺院<span
                        style="color: red">*</span></label>
                <div class="col-sm-4">
                    <select class="chosen-select" id="templeId" name="templeId"
                            data-placeholder="请选择" >
                        <c:forEach items="${temples}" var="temple">
                            <option value="${temple.id}">${temple.name}</option>
                        </c:forEach>
                    </select>

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="religionType">宗教类别</label>
                <div class="col-sm-4">
                	<select class="form-control" id="religionType" name="religionType" style="width:97%">
                    	<option value="1" selected="selected">佛教</option>
                        <option value="2">道教</option>
                        <option value="3">基督教</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="mobileNumber">手机号码</label>
                <div class="col-sm-9">
                    <input type="text" id="mobileNumber" name="mobileNumber" value="${monk.mobileNumber}" placeholder="手机号码" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="position">职位</label>
                <div class="col-sm-9">
                    <input type="text" id="position" name="position" value="${monk.position}" placeholder="职位" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="ringNa">戒腊</label>
                <div class="col-sm-9">
                    <input type="text" id="ringNa" name="ringNa" value="${monk.ringNa}" placeholder="戒腊" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
                        
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="born">出生年月</label>
                <div class="col-sm-9">
                    <input type="text" id="born" name="born" value="${monk.born}" placeholder="出生年月" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="ancestralHome">籍贯</label>
                <div class="col-sm-9">
                    <input type="text" id="ancestralHome" name="ancestralHome" value="${monk.ancestralHome}" placeholder="籍贯" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="degree">学历</label>
                <div class="col-sm-9">
                    <input type="text" id="degree" name="degree" value="${monk.degree}" placeholder="学历" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
             <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="convertTeacher">皈依本师</label>
                <div class="col-sm-9">
                    <input type="text" id="convertTeacher" name="convertTeacher" value="${monk.convertTeacher}" placeholder="皈依本师" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="convertTeacher">戒师</label>
                <div class="col-sm-9">
                    <input type="text" id="acariya" name="acariya" value="${monk.acariya}" placeholder="戒师" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="sort">排序键<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="sort" name="sort" value="${monk.sort}" placeholder="排序键" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="sort">出家因缘</label>
                <div class="col-sm-9">
                    <input type="text" id="cause" name="cause" value="${monk.cause}" placeholder="出家因缘" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="sort">法师所在地</label>
                <div class="col-sm-9">
                    <input type="text" id="address" name="address" value="${monk.address}" placeholder="法师所在地" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="isRz">是否认证</label>
				<div class="col-sm-4">
					<select class="form-control" id="isRz" name="isRz">
						<option value="0" selected="selected">未认证</option>
						<option value="1">已认证</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="isTj">是否推荐</label>
				<div class="col-sm-4">
					<select class="form-control" id="isTj" name="isTj">
						<option value="0" selected="selected">未推荐</option>
						<option value="1">已推荐</option>
					</select>
				</div>
			</div>            

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="detail">主要成就</label>
                <div class="col-sm-9">
                    <textarea id="detail" name="detail">${monk.detail}</textarea>
                    <script type="text/javascript"> 
				    	var ue = UE.getEditor('detail',{
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
                <label class="col-sm-1 control-label no-padding-right" for="introduce">简介</label>
                <div class="col-sm-9">
                    <textarea id="introduce" name="introduce">${monk.introduce}</textarea>
                    <script type="text/javascript"> 
				    	var ue = UE.getEditor('introduce',{
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
   
    var url = "${ctx}/buddha/server/monk/insertMonk";
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
	    	$("#religionType").val(${monk.religionType});
	    	$("#templeId").val(${monk.templeId});
	    	$("#isRz").val($("#editIsRz").val());
			$("#isTj").val($("#editIsTj").val());
	    }
	    if("add"==param){
	    	$("#sort").val($("#hiddenSort").val());
	    }
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#monkInfoForm').valid()) {
                return false;
            }
        	if("edit"==param){
        		url = "${ctx}/buddha/server/monk/updateMonk";
        	}
            doPost(url, $("#monkInfoForm").serialize(), function (data) {
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
    $('#monkInfoForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            },
            buddhistName:{
            	required: true,
                maxlength:32
            },
            address:{
            	required: true,
                maxlength:500
            },
            sort:{
            	required: true,
                number:true,
                min:0
            }
        },

        messages: {
        	name: {
                required: "请输入名称"
            },
            buddhistName:{
            	required: "请插入法号"
            },
            address:{
            	required: "请输入法师所在地"
            },
            sort:{
            	required: "请输入排序键"
            }
        }
    }));
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/monk/buMonkList";
	}
	
	
	function clearUeditor1() {
		UE.getEditor('banner').setContent($("#divBanner").html());
	    UE.getEditor('detail').setContent($("#divDetail").html());
	    UE.getEditor('introduce').setContent($("#divIntroduce").html());
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
