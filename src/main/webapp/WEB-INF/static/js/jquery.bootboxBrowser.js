/**
 * bootboxBroser 弹出层
 * 
 * 
 * author peipeicai 2015.05.11
 * **/

+function ($) { "use strict";
	var BootboxBrowser = function (element,options) {
	   this.element = element;
	   this.options=options;
	   this.ifrm=null; 
	   this.size="";
	   this.init();
	}
	
	BootboxBrowser.prototype.init = function () {
		this.size=this.options.size;
		this.getIfrm();
		
	}
	
	BootboxBrowser.prototype.destroy = function () {
		//this.hide().$element.off('.' + this.type).removeData('bs.' + this.type)
	}
	BootboxBrowser.prototype.getIfrm = function () {
		if(this.ifrm==null) {
			
		    
		    
			var $ifrm=$("<iframe  border='0'  frameborder='no'  noresize='noresize'></iframe>")
			
			$ifrm.css({
				"border":"0px solid #fff",
				"margin-bottom":"-18px",
				"width": this.options.width,
				"height": this.options.height
			})
			
			$ifrm.attr({
				"src":this.options.url
			})
			
		
			
			this.ifrm=$ifrm[0];
		}
	},
	
	
	BootboxBrowser.prototype.getOkSetting=function(){
		var _this=this;
		return {
		      label: "确定",
		      className: "btn-success",
		      callback: function() {
		    	  if(_this.options.ok!='') {
	  		    	  //得到返回的数据
	  		    	 var windowIfrm=Util.getIfrmWindow(_this.ifrm);
			    	 var obj=windowIfrm.doOk();
			    	 if(obj==false){
			    		 return  false;
			    	 } else {
			    		//调用处理函数
					    _this.options.ok.call(_this.element, obj);
			    	 }
  		    	  }
		      }
		    };
	}
	
	BootboxBrowser.prototype.getCancelSetting=function(){
		var _this=this;
		return {
		      label: "取消",
  		      className: "btn-danger",
  		      callback: function() {
  		    	  if(_this.options.cancle!='') {
	  		    	  //得到返回的数据
	  		    	 var windowIfrm=Util.getIfrmWindow(_this.ifrm);
			    	 var obj=windowIfrm.doCancle();
				    
				     //调用处理函数
			    	_this.options.cancle.call(_this.element, [].concat(obj));
  		    	  }
  		      }
  		    };
	}
	
	
	BootboxBrowser.prototype.getClearSetting=function(){
		var _this=this;
		return {
			  label: "清除",
  		      className: "btn-primary",
  		      callback: function() {
  		    	 if(_this.options.clear!='') {
	  		    	  //得到返回的数据
	  		    	 var windowIfrm=Util.getIfrmWindow(_this.ifrm);
			    	 var obj=windowIfrm.doClear();
				    	
				     //调用处理函数
			    	_this.options.clear.call(_this.element, [].concat(obj));
 		    	  }
  		      }
		    };
	}
	
	BootboxBrowser.prototype.showDialog = function () {
		
			var  btns={};
			if(this.options.ok!=null) btns.ok=this.getOkSetting();
			if(this.options.cancel!=null) btns.cancel=this.getCancelSetting();
			if(this.options.clear!=null) btns.clear=this.getClearSetting();
			
		    var setting={
				  message: this.ifrm,
				  title: this.options.title,
				  size:this.size,
				  buttons:btns,
				  closeButton:true
			}
		    if(this.options.target=="parent"){
		    	parent.bootbox.dialog(setting);
		    } else if(this.options.target=="top"){
		    	top.bootbox.dialog(setting);
		    } else {
		    	bootbox.dialog(setting);
		    }
	}
	
	
	$.fn.bootboxBrowser = function (option, args) {
	  return this.each(function () {
		  
	    var $this   = $(this);
	    
	    var options = null;
	    
	    var param=$(this).metadata({ "type":"attr","name":"data-param" });
	    if(param!=null){
	    	options = $.extend(true,{}, $.fn.bootboxBrowser.defaults, $this.data(),param, typeof option == 'object' && option);
	    } else {
	    	options = $.extend(true,{}, $.fn.bootboxBrowser.defaults, $this.data(), typeof option == 'object' && option);
	    }
	    
    
	    var data    = $this.data('boot.bootboxBrowser')
	    if (!data) $this.data('boot.bootboxBrowser', (data = new BootboxBrowser(this, options)));
	    if (typeof option == 'string'){
	    	
	    	
	    	data[option].apply(data, [].concat(args));
	    }
	    
	    
	     
	    
	  })
	}
	//此方法用于，例如单一的一个按钮每次点击，但是需要传递不同的url时，不需要缓存对象的用途，用法，$(this).bootBooxNoCacheBrowser().showDialog();例如‘例如内容审核页面的点击查看功能’
	$.fn.bootboxNoCacheBrowser = function (option, args) {
		    var $this   = $(this);
		    var options = null;
		    var param=$(this).metadata({ "type":"attr","name":"data-param" });
		    if(param!=null){
		    	options = $.extend(true,{}, $.fn.bootboxBrowser.defaults, $this.data(),param, typeof option == 'object' && option);
		    } else {
		    	options = $.extend(true,{}, $.fn.bootboxBrowser.defaults, $this.data(), typeof option == 'object' && option);
		    }
		    var browser = new BootboxBrowser(this, options);
		    return browser;
		}
	
	//PLUGIN defaults
	//=====================
	$.fn.bootboxBrowser.defaults = {
		url:"",
		target:"cur",
		width:"100%",
		height:"470px",
		closeButton:true,
		ok:function(dataServer){},
		cancle:function(dataServer){},
		clear:function(dataServer){}
	};
	
	//PLUGIN DEFINITION
	// =====================
	var old = $.fn.bootboxBrowser;
	
	
	
	
	$.fn.bootboxBrowser.Constructor = BootboxBrowser;
	// NO CONFLICT
	// ===============
	
	$.fn.bootboxBrowser.noConflict = function () {
	  $.fn.bootboxBrowser = old;
	  return this;
	}
	
	//DATA-API
	// ============
	$(function () {
		$('[data-toggle="bootboxBrowser"]').on("click",function(e){
			e.preventDefault();
			$(this).bootboxBrowser("showDialog");
		});
	});
}(window.jQuery);
