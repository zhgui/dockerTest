var scratchCard = (function(){

	function init_screen(){

		_maskImg = new Image();
		_maskImg.src=$('#ctx').val()+"/static/bigapp/app/img/cove_lottery.jpg";
		_maskImg.onload=function(){
			var $area = $('.scratch_card .area');
			$area.wScratchPad({
				bg:"#fdfcbb",
				fg:$('#ctx').val()+'/static/bigapp/app/img/cove_lottery.jpg',
				size:20,
				scratchMove:function(e, percent){
					if(!lock){
						if(!scratchCard.hasfirstclick){
							$("img[crossorigin]").remove();
							scratchCard.hasfirstclick = true;
							ajaxlottery();
						}
						if(percent>10){
							$('.scratch_card .area').wScratchPad('clear');
							$('.scratch_card .area canvas').hide(100);
						}
					}
					return false;

				}
			});

		};
	}
	return {init:init_screen};
})();

scratchCard.hasfirstclick = false;


var pop = (function(){
	var that ={};
	that.show = function(layId){
		$(layId).lightbox_me({
			centered: true,
			overlayCSS:{background: 'black', opacity: .8}
		});
	}
	that.hide = function(layId){
		if(layId){
			$(layId).trigger('close').hide();
		}else{
			$(".pop").trigger('close').hide();
		}
	}
	return that;
})();
var lottery =(function(){
	var that ={};
	that.loading = function(){
		pop.show("#loadingimg");
	}
	that.endLoading = function(){
		pop.hide("#loadingimg");
		$("#loadingimg").hide();
	}
	return that;
})();
function Int(a){
	return parseInt(a);
}
function checkMobile(mobile){
	var pattern = /^(13|14|15|18)\d{9}$/;
	return pattern.test(mobile);
}
function postData(url,params,callback){
	$.ajax({
		url:url,
		type:'POST',
		data:params,
		dataType: "json",
		success:function(data) {
			callback(data);
		}
	});
}
function getDataByJsonP(url,params,callback){
	$.ajax({
		url:url,
		type:'get',
		data:params,
		dataType: "jsonp",
		success:function(data) {
			callback(data);
		}
	});
}
function RandomA(aArray){
	var aCopy = aArray.concat();
	var aRandomized = new Array();
	var nRandom;
	for (var i = 0; i < aCopy.length; i++) {
		nRandom = Math.floor(Math.random() * aCopy.length);
		aRandomized.push(aCopy[nRandom]);
		aCopy.splice(nRandom, 1);
		i--;
	}
	return aRandomized;
}


window.onload = function(){
	window.setTimeout(function(){pop.hide("#loadingimg");},1000);
}


