<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
		<form class="form-horizontal" role="form" id="buMonlamFrom">
			<input name="id" value="${buMonlam.id}" type="hidden"> <input
				id="hiddentId" value="${buMonlam.tId}" type="hidden">
			<input id="hiddenType" value="${buMonlam.type}" type="hidden">
			<input id="param1" name="param1" value="${param1}" type="hidden">
			<div style="display: none" id="divContent">${buMonlam.content}</div>
			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="name">名称<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<input type="text" id="name" name="name" value="${buMonlam.name}"
						placeholder="名称" class="col-xs-10 col-sm-5" />
				</div>
			</div>
			<div id="divCover" class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="banner">封面<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<textarea id="banner" name="banner">${buMonlam.banner}</textarea>
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
			<%--<div id="divCover" class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="tId">寺院<span
					style="color: red">*</span></label>
				<div class="col-sm-4">
					<select id="tId" name="tId" style="width:97%">
					</select>
				</div>
			</div>--%>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="tId">寺院<span
						style="color: red">*</span></label>
				<div class="col-sm-4">
					<select class="chosen-select" id="tId" name="tId"
							data-placeholder="请选择" >
						<c:forEach items="${temples}" var="temple">
							<option value="${temple.id}">${temple.name}</option>
						</c:forEach>
					</select>

				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="sumNum"><nobr>预计参加人数</nobr><span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<input type="text" id="sumNum" name="sumNum"
						value="${buMonlam.sumNum}" placeholder="预计参加人数"
						class="col-xs-10 col-sm-5" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="realNum"><nobr>实际参加人数</nobr><span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<input type="text" id="realNum" name="realNum"
						value="${buMonlam.realNum}" placeholder="实际参加人数"
						class="col-xs-10 col-sm-5" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="address">地址<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<input type="text" id="address" name="address"
						value="${buMonlam.address}" placeholder="地址"
						class="col-xs-10 col-sm-5" />
					&nbsp;经度:<label id="lngLab" > ${buMonlam.lng}</label><input type="text" hidden="hidden" name="lng" id="lng"  value="${buMonlam.lng}">
					</br>
					&nbsp;纬度:<label id="latLab" >${buMonlam.lat} </label><input type="text" hidden="hidden"  name = "lat"  id="lat"  value="${buMonlam.lat}">
					<div id = 'message'></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="address">地图<span style="color: red">*</span></label>
				<div class="col-sm-9">
					<div id="container"></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="monlamStartTimeVo">开始时间<span style="color: red">*</span></label>
				<div class="col-sm-9">
					<input class="col-xs-10 col-sm-5" type="text"
						name="monlamStartTimeVo"
						onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="开始时间" id="monlamStartTimeVo"
						value="${buMonlam.monlamStartTime}" />

				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="monlamEndTimeVo">结束时间<span style="color: red">*</span></label>
				<div class="col-sm-9">
					<input class="col-xs-10 col-sm-5" type="text"
						name="monlamEndTimeVo"
						onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="结束时间" id="monlamEndTimeVo"
						value="${buMonlam.monlamEndTime}" />
				</div>
			</div>
			
			
			<div id="divCover" class="form-group">
				<label class="col-sm-1 control-label no-padding-right"
					for="description">描述<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<textarea id="description" name="description"
						style="resize: none;width:41.666%;height:80px">${buMonlam.description}</textarea>
						<span
					style="color: red">最多输入50个字</span>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="type">法会类别<span
					style="color: red">*</span></label>
				<div class="col-sm-4">
					<select class="form-control" id="type" name="type"
						style="width:97%">
						<option value="1"  ${buMonlam.type =="1"?'selected=selected':""}>活动</option>
						<option value="2" ${buMonlam.type =="2"?'selected=selected':""}>义工</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right">是否推荐</label>
				<div class="col-sm-4">
					<select class="form-control" id="isTj" name="isTj">
						<option value="1" ${buMonlam.isTj =="1"?'selected=selected':""}>推荐</option>
						<option value="0"  ${buMonlam.isTj =="0"?'selected=selected':""}>未推荐</option>
					</select>
				</div>
			</div>
 			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="mainName">主会人</label>
				<div class="col-sm-9">
					<input type="text" id="mainName" name="mainName"
						value="${buMonlam.mainName}" placeholder="主会人"
						class="col-xs-10 col-sm-5" />
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right" for="content">法会内容</label>
				<div class="col-sm-9">
					<textarea id="content" name="content">${buMonlam.content}</textarea>
					<script type="text/javascript">
						var ue = UE.getEditor('content', {
							initialFrameWidth : 900,//设置编辑器宽度
							initialFrameHeight : 450,//设置编辑器高度
							scaleEnabled : true,
							toolbars : [ [ 'fullscreen', 'source', '|', 'undo',
									'redo', '|', 'bold', 'italic', 'underline',
									'fontborder', 'strikethrough',
									'superscript', 'subscript', 'removeformat',
									'formatmatch', 'blockquote', 'pasteplain',
									'|', 'forecolor', 'backcolor',
									'insertorderedlist', 'insertunorderedlist',
									'selectall', 'cleardoc', '|',
									'rowspacingtop', 'rowspacingbottom',
									'lineheight', '|', 'customstyle',
									'paragraph', 'fontfamily', 'fontsize', '|',
									'directionalityltr', 'directionalityrtl',
									'indent', '|', 'justifyleft',
									'justifycenter', 'justifyright',
									'justifyjustify', '|', 'touppercase',
									'tolowercase', '|', 'link', 'unlink',
									'anchor', '|', 'imagenone', 'imageleft',
									'imageright', 'imagecenter', '|',
									'simpleupload', 'insertimage', 'pagebreak',
									'background', '|', 'insertvideo',
									'horizontal', 'date', 'time', 'spechars',
									'snapscreen', 'wordimage', '|',
									'inserttable', 'deletetable',
									'insertparagraphbeforetable', 'insertrow',
									'deleterow', 'insertcol', 'deletecol',
									'mergecells', 'mergeright', 'mergedown',
									'splittocells', 'splittorows',
									'splittocols', 'charts', '|', 'print',
									'preview' ] ]
						});
					</script>
				</div>
			</div>
			
			 <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" >状态</label>
                <div class="col-sm-4">
                	<select class="form-control" id="status" name="status">
                		<option value="1" <c:if test="${buMonlam.status=='1'}">selected="selected"</c:if>>启用</option>
                    	<option value="0" <c:if test="${buMonlam.status=='0'}">selected="selected"</c:if>>禁用</option>
                        
                    </select>
                </div>
            </div>

			<div class="clearfix form-actions">
				<div class="col-md-offset-4 col-md-9">
					<button class="btn btn-info saveBtn subm" type="button"
						onclick="cli()">
						<i class="ace-icon fa fa-check bigger-110"></i> 提交

					</button>
					&nbsp;
					<button class="btn" type="reset" onclick="clearUeditor1()">
						<i class="ace-icon fa fa-undo bigger-110"></i> 重置
					</button>
					<a href="<st:BackURL/>" class="btn btn-success"> <i
						class="ace-icon fa  fa-reply icon-only bigger-110"></i> 返回
					</a>
				</div>
			</div>

		</form>
	</div>
</div>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=40cd4fc824565b087b595d3312fb602a&plugin=AMap.Autocomplete"></script>
<style>
	#container {width:300px; height: 180px; }
</style>
<script type="text/javascript">

	$(function() {
		//获取状态
		//获取单位存入字段中
		var ts = '${buMonlam.tId}';
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


		$("#description").keydown(function() {
			if ($(this).val().length >= 50){
				bootbox.alert("输入字数不得超过50");
			}
		});

		//getBuMonkList();
		var param = $("#param1").val();
		if ("edit" == param) {
			$("#type").val(${buMonlam.type});
			$("#tId").val(${buMonlam.tId});
		}


		//高德地图
		var map = new AMap.Map('container',{
			resizeEnable: true,
			center: [116.397428, 39.90923],
			zoom: 13,
			isHotspot: true
		});
		AMap.plugin('AMap.Geocoder',function(){
			var geocoder = new AMap.Geocoder({
				//city: "010"//城市，默认：“全国”
			});
			var marker = new AMap.Marker({
				map:map,
				bubble:true
			})
			$("#address").bind("change",function(e){
				var address = $("#address").val();
				geocoder.getLocation(address,function(status,result){
					if(status=='complete'&&result.geocodes.length){
						var LngLat = result.geocodes[0].location;
						marker.setPosition(LngLat);
						map.setCenter(marker.getPosition());
						initPosition(LngLat);
					}else{
						$('#message').html('获取位置失败');
					}
				})
			});
			$("#address").change();
		});
		var auto = new AMap.Autocomplete({
			input: "address"
		});
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
		function select(e) {
			if (e.poi && e.poi.location) {
				var LngLat=e.poi.location;
				map.setZoom(15);
				var marker = new AMap.Marker({
					map:map,
					bubble:true
				});
				marker.setPosition(LngLat);
				map.setCenter(marker.getPosition());
				initPosition(LngLat)
			}
		}

		//为地图注册click事件获取鼠标点击出的经纬度坐标
		var clickEventListener = map.on('click', function(e) {
			var LngLat = e.lnglat;
			initPosition(LngLat);
			var lnglatXY=[LngLat.getLng(), LngLat.getLat()];//地图上所标点的坐标
			var geocoder = new AMap.Geocoder({
				//city: "010"//城市，默认：“全国”
			});
			geocoder.getAddress(lnglatXY, function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					//获得了有效的地址信息:
					$("#address").val(result.regeocode.formattedAddress);
				}else{
					//获取地址失败
				}
			});
		});


	});
/*	$(function() {
		//getTempleList();

	});*/

	function initPosition(LngLat){
		$("#lng").val(LngLat.getLng());
		$("#lat").val(LngLat.getLat());
		$("#lngLab").html(LngLat.getLng());
		$("#latLab").html(LngLat.getLat());
		$('#message').html('');
	}

	// 页面项目验证
	$('#scriptureForm').validate(jValidateOption({
		rules : {
			description : {
				required : true,
				maxlength : 35
			}
		},

		messages : {
			description : {
				required : "输入文字必须小于35字"
			}

		}
	}));
	//跳转到列表页面 
	function localhref() {
		window.location.href = "${ctx}/buddha/server/buMonlam/buMonlamList";
	}

	function clearUeditor1() {
		UE.getEditor('content').setContent($("#content").html());
	}

	function getTempleList() {
		$
				.ajax({
					url : '${ctx}/buddha/server/temple/getTempleList',
					cache : true,
					async : false,
					data : {},
					type : "POST",
					dataType : 'json',
					success : function(result) {
						var obj = result.responseData;
						$("select[name='tId']").empty();
						$("select[name='tId']").append(
								"<option value=-1>请选择</option>");
						var hiddentId = $("#hiddentId").val();
						for (var i = 0; i < obj.length; i++) {
							$("select[name='tId']").append(
									"<option value=" + obj[i].id + ">"
											+ obj[i].name + "</option>");
							if (hiddentId == obj[i].id) {
								$("#tId option[value=" + obj[i].id + "]").attr(
										"selected", true);

							}
						}
					},
					error : function(result) {
						alertError(result.message);
					}
				});
	}
/*	$('.chosen-select').chosen({
		allow_single_deselect : true,
		no_results_text : "未找到匹配项！",
		search_contains : true
	});
	$(window).off('resize.chosen').on('resize.chosen', function() {
		$('.chosen-select').each(function() {
			var $this = $(this);
			$this.next().css({
				'width' : $this.parent().width()
			});
		});
	}).trigger('resize.chosen');*/

	/* var hiddentId = $("#hiddenMonkId").val();
	var hiddentIds = hiddentId.split(",");

	$("#monkId").find("option").each(function(idx, dom) {
		if (hiddentIds != null) {
			for (var i = 0; i < hiddentIds.length; i++) {
				if ($(this).val() == hiddentIds[i]) {
					$(this).attr("selected", true);
				}
			}
		}
	});
	$("#monkId").trigger("chosen:updated");
 */
	function cli() {
		var param = $("#param1").val();
		var url = "${ctx}/buddha/server/buMonlam/insertBuMonlam";
		if (!$('#buMonlamFrom').valid()) {
			return false;
		}
		if ("edit" == param) {
			url = "${ctx}/buddha/server/buMonlam/updateBuMonlam";
		}

		$.ajax({
			url : url,
			cache : true,
			async : false,
			data : $("#buMonlamFrom").serialize(),
			type : "POST",
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					bootbox.alert(data.message, function(e) {
						localhref();
					});
				} else {
					addGritter("error", data.message);
				}
			}
		});
	}
</script>
</body>
</html>
