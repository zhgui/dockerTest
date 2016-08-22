<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-ctx.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>

<link rel="stylesheet" href="${ctx}/static/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script src="${ctx}/static/plugin/zTree_v3/js/jquery.ztree.all-3.5.js"></script>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="templeSupportForm">
            <input id="id" name="id" value="${templeSupport.id}" type="hidden">
            <input id="muom" name="muom" value="${templeSupport.muom}" type="hidden">
            <input id="sumMoney" name="sumMoney" value="${templeSupport.sumMoney}" type="hidden">
            <div style="display: none" id="divBanner">${templeSupport.banner}</div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${templeSupport.name}" placeholder="名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="money">金额<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="money" name="money" value="${templeSupport.money}" placeholder="金额" class="col-xs-10 col-sm-5"/>
                </div>
            </div>             
            
            <div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">图片<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="banner" name="banner">${templeSupport.banner}</textarea>
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
            
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="tId">寺院</label>
                <div class="col-sm-4">
                    <select class="chosen-select" id="tId" name="tId" data-placeholder="请选择">
                        <c:forEach items="${temples}" var="templec">
                            <option value="${templec.id}" >${templec.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="sId">供养类型</label>
                <div class="col-sm-4">
                    <select class="chosen-select" id="sId" name="sId" data-placeholder="请选择">
                        <c:forEach items="${supports}" var="supportp">
                            <option value="${supportp.id}" >${supportp.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>            
            

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="remark">简介</label>
                <div class="col-sm-9">
                     <textarea id="remark" name="remark" style="resize: none;width:365px;height:150px">${templeSupport.remark}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" >状态</label>
                <div class="col-sm-4">
                	<select class="form-control" id="status" name="status">
                    	<option value="0" <c:if test="${templeSupport.status=='0'}">selected="selected"</c:if>>禁用</option>
                        <option value="1" <c:if test="${templeSupport.status=='1'}">selected="selected"</c:if>>启用</option>
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
		 //var status = '${templeSupport.status}';
		 //$("#status").val(status);
	    //获取单位存入字段中
		var ts = '${templeSupport.tId}';
		var td = '${templeSupport.sId}'; 
		$("#tId").find("option").each(function(idx,dom){
		   		if ($(this).val() == ts) {
		   	         $(this).attr("selected","");
		   	     }
		});
		$("#sId").find("option").each(function(idx,dom){
	   		if ($(this).val() == td) {
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
        	if (!$('#templeSupportForm').valid()) {
                return false;
            }
            doPost("${ctx}/buddha/server/templesupport/updateTempleSupport", $("#templeSupportForm").serialize(), function (data) {
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
    $('#templeSupportForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            },
            money: {
            	number: true,
            	min:0.01
            },
            tId:{
            	required: true
            },
            sId:{
            	required: true
            },
            remark:{
            	required: true
            }
        },

        messages: {
        	name: {
                required: "请输入名称"
            },
            money: {
                required: "金额不能为空"
            },
            tId:{
            	required: "请选择寺院"
            },
            sId:{
            	required: "请选择供养类型"
            },
            remark:{
            	required: "请输入寺院简介"
            }
        }
    }));
	
    function clearUeditor() {
		UE.getEditor('banner').setContent($("#divBanner").html());
	}
	
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/templesupport/templeSupportList";
	}
</script>
</body>
</html>
