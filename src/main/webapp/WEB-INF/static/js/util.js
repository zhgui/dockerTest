
$.fn.serializeJson = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/* 得到日期年月日等加数字后的日期 */ 
Date.prototype.dateAdd = function(interval,number){ 
    var d = this; 
    var k={'y':'FullYear', 'q':'Month', 'm':'Month', 'w':'Date', 'd':'Date', 'h':'Hours', 'n':'Minutes', 's':'Seconds', 'ms':'MilliSeconds'}; 
    var n={'q':3, 'w':7}; 
    eval('d.set'+k[interval]+'(d.get'+k[interval]+'()+'+((n[interval]||1)*number)+')'); 
    return d; 
} 


/* 计算两日期相差的日期年月日等 */ 
Date.prototype.dateDiff = function(interval,objDate2) { 
    var d=this, i={}, t=d.getTime(), t2=objDate2.getTime(); 
    i['y']=objDate2.getFullYear()-d.getFullYear(); 
    i['q']=i['y']*4+Math.floor(objDate2.getMonth()/4)-Math.floor(d.getMonth()/4); 
    i['m']=i['y']*12+objDate2.getMonth()-d.getMonth(); 
    i['ms']=objDate2.getTime()-d.getTime(); 
    i['w']=Math.floor((t2+345600000)/(604800000))-Math.floor((t+345600000)/(604800000)); 
    i['d']=Math.floor(t2/86400000)-Math.floor(t/86400000); 
    i['h']=Math.floor(t2/3600000)-Math.floor(t/3600000); 
    i['n']=Math.floor(t2/60000)-Math.floor(t/60000); 
    i['s']=Math.floor(t2/1000)-Math.floor(t/1000); 
    return i[interval]; 
}

	
$(document).ready(function(){
	try{
		$(".hasover").hover(
			function(){
				var overClass=$(this).attr("overClass");
				$(this).addClass(overClass);
			},
			function(){
				var overClass=$(this).attr("overClass");
				$(this).removeClass(overClass);		
			}
		);
	} catch(e){}
	
	try{
		$(".openA").fancybox({
			'width'				:900,
			'height'			: 700,
			'transitionIn'	:	'elastic', 
			'speedIn'		:	600, 
			'showCloseButton':false,
			'overlayOpacity':9.0,
			'overlayColor':'#000',
			'type'				: 'iframe'
		});
	}catch(e){}
	
	
	try{
		$(".opaHover").addClass("hand").hover(
			function(){
				$(this).addClass("opacity_70");
			},function(){
				$(this).removeClass("opacity_70");
			}
		);
	}catch(e){}	
	
	
	try{
	  $("img.lazy").lazyload({
		event : "sporty",
		effect : "fadeIn"
	  });
	  $(window).bind("load", function() { 
	    var timeout = setTimeout(function() {$("img.lazy").trigger("sporty")}, 1000);
	 });
	} catch(e){}
	
	
	 
	

	
	try{
		$("label.overlabel").overlabel();
	} catch(e){}
	
}); 

var cuiDebug=true;

var Util={
		doDel:function(fnSucess,fnCancel){
			bootbox.setDefaults({locale:"zh_CN"});
			bootbox.confirm("<h3 style='margin-top:10px!important;font-weight:600;color:#95a0bb;'>&nbsp;&nbsp删除确认<span style='color:#fa351f;'>!</span></h3> <h4 style='color:#95a0bb;margin-left:10px;'>( ^_^ )如果要删除，请点击'确认'；否则，请点击'取消'</h4>", function(result) {
				if (result) {
					if(fnSucess!=null) {
						fnSucess.call();
					}
			 	 }  else {
			 		if(fnCancel!=null) {
			 			fnCancel.call();
					}
			 	 }
			}); 
		},
		getIfrmWindow:function(ifrm){
			return ifrm.contentWindow||ifrm.contentDocument.parentWindow;
		},
		getIfrmDocument:function(ifrm){
			return ifrm.contentWindow.document|| ifrm.contentDocument;
		},
		log:function(obj){
			if(typeof cuiDebug != "undefined" && cuiDebug){
				try{
					console.log(obj);
				}catch(e){}	
			}
		},
		ueSync:function(){
			try{				
	    		for (var instance in UE.instants){
	    			UE.instants[instance].sync();
	    		}
	    	} catch(e){
	    		//alert(e);
	    	}
		},
		add0:function(str,len){
			var strLength=str.length;
			var dis=len-strLength;
			if(dis>0){
				for(var i=0;i<dis;i++){
					str="0"+str;
				}
			}
			
			return str;
		},
		getDateString:function(d){
			   var s="";
			   s += (d.getFullYear()+1900)+ "-";                         // 获取年份。
			   s += Util.add0(''+(d.getMonth() + 1),2) + "-";            // 获取月份。
			   s += Util.add0(''+d.getDate(),2) ;                   // 获取日。
			   return(s);                                // 返回日期。
	    },
		getTimeString:function(d){
			   var s="";
			   s += (d.getFullYear()+1900)+ "-";                         // 获取年份。
			   s += Util.add0(''+(d.getMonth() + 1),2) + "-";            // 获取月份。
			   s += Util.add0(''+d.getDate(),2) ;                   // 获取日。
			   s += Util.add0(''+d.getHours(),2) ;					//获取小时
			   s += Util.add0(''+d.getMinuts(),2) ;					//获取分钟
			   s += Util.add0(''+d.getSeconds(),2) ;				//获取秒
			   return(s);                                // 返回日期。
	    },
	    getDate:function(dateStr){
	    	var array=dateStr.split("-");
	    	var dateReturn=new Date();
	    	dateReturn.setYear(array[0]);
	    	dateReturn.setMonth(parseInt(array[1])-1,array[2]);
	    	
	    	return dateReturn;
	    },
	    getTime:function(timeStr){
	    	
	    	var array=timeStr.split(" ");
	    	var arrayDate=array[0].split("-");
	    	var arrayTime=array[1].split(":");
	    	
	    	var dateReturn=new Date();
	    	
	    	
	    	dateReturn.setYear(arrayDate[0]);
	    	dateReturn.setMonth(parseInt(arrayDate[1])-1,arrayDate[2]);
	    	//dateReturn.setDate(arrayDate[2]);
	    	
	    	dateReturn.setHours(arrayTime[0]);
	    	dateReturn.setMinutes(arrayTime[1]);
	    	dateReturn.setSeconds(arrayTime[2]);
	    	
	    	return dateReturn;
	    },
	    
		addParaToUrl:function(url,para,value){
			var baseUrl="";
			var pos=url.indexOf("&"+para+"=");
			if(pos==-1){
				pos=url.indexOf("?"+para+"=");
			}
			if(pos!=-1){
				var pos2=url.indexOf("=",pos+1);
				if(pos2!=-1){
					var pos3=url.indexOf("&",pos2+1);
					if(pos3!=-1){
						baseUrl=url.substring(0,pos)+url.substring(pos3);
					} else {
						baseUrl=url.substring(0,pos);
					}
				}
			} else {
				baseUrl=url;
			}
			//alert(baseUrl)
			if(baseUrl.indexOf("?")!=-1){
				return baseUrl+"&"+para+"="+value;
			}else{
				return baseUrl+"?"+para+"="+value;
			}
			
		},
		goPageAndAddPara:function(para,value,para2,value2){
			var url=Util.addParaToUrl(window.location.href,para,value);
			if(para2!=null){
				url=Util.addParaToUrl(url,para2,value2);
			}
			window.location.href=url;
		},
		doSearch:function(frmId,objBtn){
			objBtn.disabled=true;
			var $frm=$("#"+frmId);
			 
			var url=window.location.href;
			var fields = $frm.serializeArray();
		    $.each(fields, function(i, field){
		    	url=Util.addParaToUrl(url,field.name,encodeURI(field.value));
		    });
		    url=Util.addParaToUrl(url,"pageIndex","1"); 
		    window.location.href=url;
		},
		goPage:function(pageIndex){
			window.location.href=Util.addParaToUrl(window.location.href,"pageIndex",pageIndex);
		},
		goViewItemSource:function(itemId,resourceId){
			window.location.href="/page/"+itemId+"/"+resourceId+".html";
		},
		escapeFlag:function(remark){
			var reg = new RegExp("'","g"); //将单引号转义
			remark=remark.replace(reg,"&#39;");	       
			remark = remark.replace(/\n/g,"");
			remark = remark.replace(/\r/g,"");
			return remark;
		},
		div:function (exp1, exp2) { //整除
		    var n1 = Math.round(exp1); //四舍五入
		    var n2 = Math.round(exp2); //四舍五入
		    
		    var rslt = n1 / n2; //除
		    
		    if (rslt >= 0)
		    {
		        rslt = Math.floor(rslt); //返回值为小于等于其数值参数的最大整数值。
		    }
		    else
		    {
		        rslt = Math.ceil(rslt); //返回值为大于等于其数字参数的最小整数。
		    }
		    
		    return rslt;
		},
		getColCount:function(itemCount,rowCount){
			  if(itemCount%rowCount ==0) return itemCount/rowCount;
			  else return Util.div(itemCount,rowCount)+1;
		},
		goWebbyItemResource:function(itemId,resourceId,editType){
			window.location="/webby/page/"+itemId+"/"+resourceId+".html?editType="+editType;
		},
		commafy:function(num){ //数字格式转换成千分位
			   
			   if(isNaN(num)){
			      return"";
			   }
	
			   num = num+"";
			   if(/^.*\..*$/.test(num)){
			      varpointIndex =num.lastIndexOf(".");
			      varintPart = num.substring(0,pointIndex);
			      varpointPart =num.substring(pointIndex+1,num.length);
			      intPart = intPart +"";
			       var re =/(-?\d+)(\d{3})/
			       while(re.test(intPart)){
			          intPart =intPart.replace(re,"$1,$2")
			       }
			      num = intPart+"."+pointPart;
			   }else{
			      num = num +"";
			       var re =/(-?\d+)(\d{3})/
			       while(re.test(num)){
			          num =num.replace(re,"$1,$2")
			       }
			   }
			   return num;
		}
}