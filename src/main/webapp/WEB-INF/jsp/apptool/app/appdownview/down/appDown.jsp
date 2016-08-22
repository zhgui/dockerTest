<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>佛子手机App下载</title>
    <meta name="description" content="佛子手机App下载"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@include file="/WEB-INF/jsp/common/import-acecss.jsp" %>
    <style>
	    html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline}
		article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block}
		html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}
		html,body{width:100%}
		body{font-family:Arial,Helvetica,sans-serif;line-height:1.6;background:#fff;font-size:14px;color:#333;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:100%;text-rendering:optimizeLegibility}
		img,a img,img:focus{border:0;outline:0}
		img{max-width:100%;height: auto;}
		textarea,input,a,textarea:focus,input:focus,a:focus{outline:none}
		h1,h2,h3,h4,h5,h6{font-weight:normal;margin-bottom:15px;line-height:1.4}
		h1 a,h2 a,h3 a,h4 a,h5 a,h6 a{font-weight:inherit;color:#444444}
		body{font-size: 62.5%; font-family: 'Microsoft Yahei','\5FAE\8F6F\96C5\9ED1',Arial,'Hiragino Sans GB','\5B8B\4F53'; line-height: 1.6}
		li{list-style: none;}
		.clearfix{*zoom:1}
		.clearfix:before,.clearfix:after{display:table;content:"";line-height:0}
		.clearfix:after{clear:both}
		.register{background: #62BDC5 url(../img/bg-480.jpg) no-repeat; background-size:cover;}
		.logo{height: 17.8%; text-align: center;}
		.logo img{display: inline-block; vertical-align: top; font-size: 0; width: 42.5%; margin: 0 auto;}
		.device{padding: 15.8% 0 2.18%; text-align: center;}
		.device img{width: 53.43%; margin: 0 auto;}
		.intro{color: #fff; line-height: 2; padding-bottom: 12.18%; text-align: center; font-size: 2em; text-align: center;}
		.intro p{font-size: 1.8em; border-bottom: 1px solid #fff; width: 80%; margin: 0 auto; display: inline-block; letter-spacing: .8em; white-space: nowrap; padding-left: .4em;}
		.intro h2{font-size: 2.6em; letter-spacing: .1em; font-weight: bold;}
		.intro img{width: 81%; margin: 0 auto;}
		.send{padding: 0 6.1% 23%;}
		.send-form input{float: left; padding: 15px 10px; width: 65.7%; background:#fff; border: 1px solid #fff; border-radius: 4px; font-size: 2em; -webkit-box-sizing:border-box; -moz-box-sizing:border-box; box-sizing:border-box;}
		.send-form button{float: right; padding: 15px 10px; white-space: nowrap; width: 31.7%;  background:#FF6F29; border: 1px solid #FF6F29; border-radius: 4px; font-size: 2em; color: #fff; -webkit-box-sizing:border-box; -moz-box-sizing:border-box; box-sizing:border-box;}
		/*.send-form .form-text{ float: left; background: #fff; border:0; border-radius: 4px; font-size: 2.4em;border-top: 10px; border-bottom: 10px;  padding: 10px 3.5%; line-height: 2.5em !important;  width: 59.5%; vertical-align: middle;}
		.send-form .form-submit{ float: right; min-width: 66px; background: #FF6F29; -webkit-appearance: none; white-space: nowrap; letter-spacing: -1px; color: #fff; font-size: 2.4em; padding: 2.4% 2%; line-height: 2.5em; border: 0; border-radius: 4px; width: 31.5%;}
		*/.wran{color: #f00; padding-top: 8px; font-size: 1.4em; visibility: hidden;}
		
		/*success*/
		.success{}
		.info{background: url(../img/success-bg-480.jpg) no-repeat center bottom; background-size: cover; color: #fff; padding: 13.75% 0 12.8%;}
		.info .ok-btn{width: 17.5%; margin: 0 auto; padding-bottom: 9.3%; text-align: center;}
		.entry-con{font-size: 2.4em; text-align: center;}
		.entry-hd{font-size: 4em; font-weight: bold; text-align: center;}
		.info .entry-hd{font-size: 6em;}
		.info .long-hd{font-size: 4.5em;}
		.info .entry-con{color: #FCFDFD;}
		.info-list{margin: 5.6% 10.6% 0; list-style: none; font-size: 1.8em; text-align: center; color: #F3F9FB;}
		.info-list li{padding-top: 10%; border-bottom: 1px solid #D1EAEE; padding-bottom: 3.4%;}
		.download{color: #4D4D4D; padding: 7.2% 6.8% 9.3%;}
		.download .entry-con{color: #8E8F90;}
		.download-btn{padding-top: 9%;}
		.download-btn a{width: 44.7%; display: inline-block; *display: inline; *zoom: 1; vertical-align: top;}
		.download-btn a:hover, .download-btn a:focus{opacity: .8;}
		.download-btn .android-btn{padding-left: 9%;}
		.footer-bg{background: #2D2D2D; color: #E4E4E4; padding: 3.4% 2%; text-align: center;}
		.footer-bg .entry-con{font-size: 1.6em;}
		.app img{width: 85.15%; margin: 0 auto; display: block; margin-bottom: 3.4%;}
		.app .entry-con{padding-bottom: 5.4%; color: #6B6B6B;}
		#weixin-tip{display:none; position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} 
		#weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%; position: relative;}
		#weixin-tip .close{
			color: #fff;
			padding: 5px;
			font: bold 20px/20px simsun;
			text-shadow: 0 1px 0 #ddd;
			position: absolute;
			top: 0; left: 5%;
		}
		@media screen and (min-width: 481px){
			.register{background-image: url(../img/bg.jpg);}
			.info{background-image: url(../img/success-bg.jpg);}
		}
		@media screen and (max-width: 480px){
			.intro{font-size: 1.6em;}
			.intro p{margin: 0 8% 0;}
			.send-form .form-text{font-size: 1.8em;}
			.send-form .form-submit{font-size: 1.8em;}
			.entry-hd{font-size:2.8em;}
			.info .entry-hd{font-size: 4em;}
			.info .long-hd{font-size: 3em;}
			.entry-con, .info-list{font-size: 1.8em;}
			.app .entry-con{font-size: 1.6em;}
			.footer-bg .entry-con{font-size: 1.2em;}
		}
		@media screen and (max-width:360px){
			.register{background-image: url(../img/bg-360.jpg);}
			.intro{font-size: 1.2em;}
			.intro p{letter-spacing: 1em;}
			.send-form .form-text{font-size: 1.4em; padding:10px 5px;}
			.send-form .form-submit{font-size: 1.4em; padding: 10px 5px;}
			.entry-hd{font-size: 2em;}
			.info .entry-hd{font-size: 3em;}
			.info .long-hd{font-size: 2.2em;}
			.entry-con, .info-list{font-size: 1.4em;}
			.app .entry-con{font-size: 1.4em;}
		}
	    </style>
</head>

<body class="no-skin" style="background-color: #fff" class="success">
 <div class="download" style="display: none;">
            <div class="download-btn" style="padding-top:0px;">
	            <a href="" style="display:none;">
	            </a>
	            <a href="" class="android-btn" id="J_weixin" style="display:none;">
	            </a>
	            <div class="col-xs-12 col-md-12  text-center">
  				</div>
	            
            </div>
        </div>
<div class="container">
<div class="row">
    <div class="space-20"></div>
    <div class="col-xs-12 col-md-12  text-center">
    	  
        <img src="${ctx}/static/img/fozi.png" /> 
        <div style="margin-top:10px;"></div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12  text-center">
        <div class="well">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12  text-center">
                    <img style="max-width:100%;height:auto;min-width:150px;
                        min-height:150px;" src="${androidQRCode}"/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12  text-center">
                    <br>
                    <a type="button" class="downBtn btn btn-xs btn-info" style="display: none" href="${androidPath}">
                        <i class="ace-icon fa fa-download bigger-125"></i>
                        直接下载安装
                    </a>
                    <br>
                    <br>
                    <label>Android客户端</label>
                    <br>
                    <label>版本号：${android.versionNo }</label>
                    <br>
                    <label>更新日期：<fmt:formatDate value="${android.createTime }" type="both"
                                                pattern="yyyy-MM-dd"/></label>
                    <br>
                </div>
            </div>
        </div>
        
        <div class="well">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12  text-center">
                    <img style="max-width:100%;height:auto;min-width:150px;
                        min-height:150px;" src="${iosQRCode}"/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12  text-center">
                    <br>
                    <a type="button" class="downBtn btn btn-xs btn-info" style="display: none" href="${iosPath}">
                        <i class="ace-icon fa fa-download bigger-125"></i>
                        直接下载安装
                    </a>
                    <br>
                    <br>
                    <label>iOS客户端</label>
                    <br>
                    <label>版本号：${ios.versionNo }</label>
                    <br>
                    <label>更新日期：${ios.noticeContent }</label>
                    <br>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
  <div id="weixin-tip"><p><img src="${ctx}/static/img/live_weixin.png" alt="微信打开" /><span id="close" title="关闭" class="close">×</span></p></div>
</body>
<%@include file="/WEB-INF/jsp/common/import-acejs.jsp" %>
<script>
    $(function () {
        var mobileAgent = new Array("iphone", "ipod", "ipad", "android", "mobile", "blackberry", "webos", "incognito", "webmate", "bada", "nokia", "lg", "ucweb", "skyfire");
        var browser = navigator.userAgent.toLowerCase();
        var isMobile = false;
        for (var i = 0; i < mobileAgent.length; i++) {
            if (browser.indexOf(mobileAgent[i]) != -1) {
                isMobile = true;
                $(".downBtn").show();
                break;
            }
        }
    });
	
        var is_weixin = (function () {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                return true;
            } else {
                return false;
            }
        })();
        window.onload = function () {
            var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
            var btn = document.getElementById('J_weixin');
            var tip = document.getElementById('weixin-tip');
            var close = document.getElementById('close');
            if (is_weixin) {
                btn.onclick = function (e) {
                    tip.style.height = winHeight + 'px';
                    tip.style.display = 'block';
                    return false;
                }
                close.onclick = function () {
                    tip.style.display = 'none';
                }
                init();
            }
        }
        function init() {
            var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
            var tip = document.getElementById('weixin-tip');
            tip.style.height = winHeight + 'px';
            tip.style.display = 'block';
        }
       

    </script>