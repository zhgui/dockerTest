<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/import-ueditor.jsp" %>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<div class="row">
    <div class="col-sm-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="templeForm">
            <div class="space-1"></div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="name">名称<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="" placeholder="寺院名称" class="col-xs-10 col-sm-5" onBlur="validateNameIsExist()"/>
                </div>
            </div>
            
			<div id="divCover" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="banner">图片<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <textarea id="banner" name="banner"></textarea>
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
                <label class="col-sm-1 control-label no-padding-right" for="address">住持<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="buddhistName" name="buddhistName" value="" placeholder="住持" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="address">地址<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="address" name="address" value="" placeholder="寺院地址" class="col-xs-10 col-sm-5"/>
                    &nbsp;经度:<label id="lngLab" > </label><input type="text" hidden="hidden" name="lng" id="lng"  value="">
                    </br>
                    &nbsp;纬度:<label id="latLab" > </label><input type="text" hidden="hidden"  name = "lat"  id="lat"  value="">
                    <div id = 'message'></div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="address">地图<span style="color: red">*</span></label>
                <div class="col-sm-9">
                <div id="container"></div>
                    </div>
            </div>
            
            <div id="xx" class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="address">地区</label>
                <div class="col-sm-9">
                    <select id="provinceId" name="provinceId" onchange="getCity()" style="width:150px">
                            <option value=''>请选择</option>
                        </select>
                        <select id="cityId" name="cityId" onchange="getCounty()" style="width:150px">
                            <option value=''>请选择</option>
                        </select>
                        <select id="countyId" name="countyId" style="width:150px">
                            <option value=''>请选择</option>
                        </select>
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
                <label class="col-sm-1 control-label no-padding-right" for="sort">排序键<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="sort" name="sort" value="${sort}" placeholder="排序键" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="ticket">门票<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="ticket" name="ticket" value="${temple.ticket}" placeholder="门票" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="openTime">营业时间<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="openTime" name="openTime" value="${temple.openTime}" placeholder="营业时间" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="remark">简介</label>
                <div class="col-sm-9">
                    <textarea id="remark" name="remark"></textarea>
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
                	<select class="form-control" id="status" name="status">
                    	<option value="0" selected="selected">启用</option>
                        <option value="1">禁用</option>
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
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=40cd4fc824565b087b595d3312fb602a&plugin=AMap.Autocomplete"></script>
<style>
    #container {width:300px; height: 180px; }
</style>
<script type="text/javascript">
	
	$(function(){
		initProvinces();
        // 提交操作
        $(".subm").on("click", function () {
        	if (!$('#templeForm').valid()) {
                return false;
            }
            doPost("${ctx}/buddha/server/temple/insertTemple", $("#templeForm").serialize(), function (data) {
            	if (data.success) {
                    bootbox.alert(data.message, function (e) {
                    	localhref();
                    });
                } else {
                    addGritter("error", data.message);
                }
            }, "json");
        });





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
                        map.setCenter(marker.getPosition())
                        initPosition(LngLat);
                    }else{
                        $('#message').html('获取位置失败');
                    }
                })
            });
            //$("#address").change();
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

    function initPosition(LngLat){
        $("#lng").val(LngLat.getLng());
        $("#lat").val(LngLat.getLat());
        $("#lngLab").html(LngLat.getLng());
        $("#latLab").html(LngLat.getLat());
        $('#message').html('');
    }
	
	// 页面项目验证
    $('#templeForm').validate(jValidateOption({
        rules: {
            name: {
                required: true,
                maxlength:32
            },
            address:{
            	required: true
            },
            remark:{
            	required: true
            }
        },

        messages: {
        	name: {
                required: "请输入寺院名称"
            },
            address:{
            	required: "请输入寺院地址"
            },
            remark:{
            	required: "请输入寺院简介"
            }
        }
    }));
    function validateNameIsExist(){
    	doPost("${ctx}/buddha/server/temple/validateNameIsExist", 
    		{
    	    	name:$('#name').val(),
    	    	id:0
    	  	}, 
    	  	function (data) {
        	if (data.success) {
                if(data.responseData==false){
                	addGritter("warning", "寺院名称已存在，可以继续录入！");
                }
            }
        }, "json");
    }
	//跳转到列表页面 
	function localhref(){
		window.location.href="${ctx}/buddha/server/temple/templeList";
	}
</script>
</body>
</html>
