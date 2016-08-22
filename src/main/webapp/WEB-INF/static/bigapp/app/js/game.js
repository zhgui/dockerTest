var Config ={
    "sprite_len":8,
    "debug"     :false,
    "unit_h"    :153
};

var Game = (function($){

	var that = {};
	var resArr = [];
	var unit_h,first_end_pos,round_length;

    var time_gap = 300;

    var round = 4;
    var time_least = 6000;
    var time_duration = 1000;
    var lottery_delay = 3000;

    // 测试数据(抽奖转盘加快测试版)
    // var round = 1;
    // var time_least = 100;
    // var time_duration = 100;
    // var lottery_delay = 1000;
    
     
    var $btn_lottery,$btn_submit,$aniArr,$star,prize;

	that.init = function(){

        unit_h       = $("#game li").height();
        round_length = Config.sprite_len * unit_h;
        first_end_pos= round_length * 12;
        $aniArr      = $("#game").find("li");
        $btn_lottery = $(".gameWrapper"); 
        $btn_share   = $(".btn_share");
        // $star        = $(".lottery_remain>p");
        initScreen();
		initEvent();
	}
    function initScreen(){
        //  中奖记录 跑马灯
        // var $marquee = $(".marquee_winner");
        
        // $marquee.find("div").kxbdMarquee();

        //跑马灯 边框跑动效果
        // window.setInterval(function(){
        //     $marquee.hasClass("blink") ? $marquee.removeClass("blink") : $marquee.addClass("blink");
        // },200);
        
        //根据剩余抽奖机会 初始化按钮状态
        !hasLottery && $btn_lottery.addClass("disable");
        // hasLottery>=0 && hasLottery<=5 && $star.attr("class","star star"+hasLottery);
    }

	function initEvent(){

        $.fn.lockClick= function(fn){ 

            this.on("click", function(){
            
                if($(this).hasClass("disable")){
                    return false;
                }
                $(this).addClass("disable");

                fn.apply(this);
            }); 
            return this; 
        };

		$btn_lottery.lockClick(function(){

            // !hasLottery 
            // ? ( ( window.popUp = Popup({content: $(hasShare ? ".popup_noLottery_canShare" : ".popup_noLottery") }) ) && $(this).removeClass("disable") ) 
            // : gameControl();

            hasLottery  && gameControl();
		});
	}
    function gameControl(){
        
        hasLottery -= 1;    //抽奖次数减1
        // hasLottery>=0 && hasLottery<=5 && $star.attr("class","star star"+hasLottery);   //reset left opportunity
        $aniArr.each(function(index){

            var $that = $(this);

            $that.css('backgroundPositionY',$that.attr("pos")); //reset position 
            
            setTimeout(function(){
                $that.animate({'backgroundPositionY': first_end_pos + 'px'},10000);
            }, index * time_gap);
        });

        setTimeout(function(){
            gameLottery();
        },lottery_delay);

    }
    function gameLottery(){

        prize = null;
        var callback = function(data){
            
            if(data.success){

                var result = data.responseData;
                console.log(data);
                if(result.lotteryResult){   //中奖
                         resArr = Util.array.repeat3(7-result.prize.prize_code)
                         ,prize = result.prize;

                }else{
                    resArr = Util.array.random(3);
                   // console.log("resArr:",resArr)
                }


                
            }else{
                resArr = Util.array.random(3);
                alert(data.message);
            }
            gameAni();
        };
        Util.request({url:reqUrl},callback);
        
    }
	function gameAni(){
        console.log(resArr)
        var curr_pos;
       // return false;
        $aniArr.each(function(index){

            var $that = $(this);

            gameAni.animation || (gameAni.animation = function($that,index){

                console.log("before:",parseInt($that.css("backgroundPositionY")))
                $that.stop();
                curr_pos = parseInt($that.css("backgroundPositionY"));
                console.log("after:",parseInt($that.css("backgroundPositionY")),( curr_pos - curr_pos%round_length + (round*Config.sprite_len - resArr[index])*unit_h ))
                $that.animate({
                    'backgroundPositionY': ( curr_pos - curr_pos%round_length + (round*Config.sprite_len - resArr[index])*unit_h ) +'px'
                },{
                    duration: time_least+index*time_duration,
                    // easing: "Util.fx.easeOutSine",
                    // easing: "easeOutCirc",
                    easing: "easeOutQuad",
                    complete: function(){

                        index == 2 && (function(){
                            
                            // hasLottery && $btn_lottery.removeClass("disable"); //剩余抽奖次数为0，抽奖按钮状态为disable
                            $btn_lottery.removeClass("disable"); //enable btn_lottery

                            // 根据后台返回的数据生成不同的弹层
                            (prize && JSON.stringify(prize) != "{}") ? Game.Popup(prize) : Game.Popup("noprize");
                           
                        })();
                    }
                })
            });

            gameAni.animation($that,index);
        });
	}
	return that;

})(Zepto);

// 弹出层
Game.Popup = function(prize){

    if(typeof prize == "string" && prize == "noprize"){     //未中奖
        Popup({content: $(".popup_noprize")});
    }else{
        /*
            initialize UI（奖品大大豆）
                prize.prize_info[0].value (大大豆数量)
        */

        var cache_pop = $(".popup_coupon");
        
        cache_pop.find(".value em").html(prize.prize_info.value);
        if(prize.prize_info.gpType==2) {
            $("#unit").hide();
        }

        window.popUp = Popup({content: cache_pop});
    }

}
//可滚动弹出层
function ShowScrollPopup(popup) {
    var $popup = $(popup);

    if ($popup.data('scroll')) {
        Popup({content: $popup});
        $popup.data('scroll').scrollTo(0, 0);
        return;
    }
    var $con = $popup.find('.tipText');
    var popup = Popup({content: $popup});

    setTimeout(function(){

        $con.iScroll({
            hScroll: false,
            vScroll: true,
            hScrollbar: false,
            vScrollbar: true,
            hideScrollbar: false,
            momentum: true,
            snap: false,
            pager: false
        });
        $popup.data('scroll', $con.data('iScroll'));
        
    },0);
    
}
var Popup = function(options) {
    // 选项
    var options = $.extend({
        modal: true,
        content: '',
        button: [],
        open: null,
        close: null
    }, options);
    
    var $popup;
    var $con;

    // popup对象
    var popup = {
        ui: null,
        options: options,
        close: function() {
            if ($popup.data('static')) {
                $popup.hide();
            }
            else {
                $popup.remove();
            }
            
            setTimeout(function() {
                // 修正滚动条位置
                $(window).scrollTop($(window).scrollTop());
            }, 1);
            
            options.close && options.close(popup);
        }
    };

    if (typeof(options.content) == 'string') {
        // 创建ui
        $popup = $('<div class="popup"></div>');
        if (options.modal) {
            $popup.addClass('modal');
        }
        $con = $('<div class="tipContent" style="position:absolute; left:-9999px; top:-9999px;">').appendTo($popup);
        $con.append('<div class="tipText">' + options.content + '</div>');
        
        // 创建按钮
        var $btn = $([]);
        $.each(options.button, function(i, n) {
            if (!n) return;
            var $b = $('<button class="' + (n['class'] || n['cssClass']) + '">' + n.text + '</button>');
            if (n.click) {
                $b.click(function() {
                    n.click.call(this, popup);
                }); 
            }
            $btn = $btn.add($b);
        });
        if ($btn.length) {
            var $btc = $('<div class="btnContent"></div>');
            $btc.append($btn);
            $con.append($btc);
        }
        
        $popup.appendTo('body');
    
    }
    else {
        $popup = $(options.content);
        if ($popup.parent().hasClass('popup')) {
            $popup = $popup.parent();
        }
        else if (!$popup.hasClass('popup')) {
            $popup.wrap('<div class="popup"></div>');
            $popup = $popup.parent();
        }
        if (options.modal) {
            $popup.addClass('modal');
        }
        $con = $popup.find('.tipContent').first();
        $con.css({position: 'absolute', left: '-9999', top: '-9999'}).show();
        if (!$popup.data('static')) {
            $popup.data('static', true);
            $popup.find('[role=close], [role=cancel]').click(function() {
                popup.close();
                return false;
            });
        }
    }

    if (!$popup.data('handle')) {
        $popup.data('handle', true).bind('touchmove', function(event) {
            event.preventDefault();
        });
    }
    
    popup.ui = $popup;
    $popup.show();
    $con.css({left: '50%', top: '50%', margin: '-' + $con.height()/2 + 'px 0 0 -' + $con.width()/2 + 'px'});
    
    // 触发open事件
    options.open && options.open(popup);
    
    // 修正滚动条位置
    setTimeout(function() {
        $(window).scrollTop($(window).scrollTop());
    }, 1);
    
    // 返回popup对象，包含ui、options、close方法
    return popup;
};
//工具类

$.extend($.easing,{
    easeOutQuad: function (x, t, b, c, d) {
        return -c *(t/=d)*(t-2) + b;
    }
});
var Util = {
    "array":{
        "random":function(len){
            var _t,repeat,_a = [],len = len||3;

            while(_a.length<len){

                _t = parseInt(Math.random()*Config.sprite_len),repeat = 1;

                for(var i=0,l=_a.length;i<l;i++){
                    if(_a[i]== _t) {
                        if(++repeat==len){
                            break;
                        }
                    }
                }
                (i == l || _a.length == 0 )  && _a.push(_t);
               
            }
            return _a;
        },
        "repeat3":function(num){
            return [num,num,num];
        }
    },
    "validate":function(ele){

        if(!$.trim(ele.value)){
            return false;
        }

        var regex = $(ele).attr("regex");
        if(tag){
            if(!regex.test(ele.value)){ return false;}
        }
        return true;  
    },
    "request":function(requestData,callback){  //requestData:{url:,params:}

        $.ajax({
            type: requestData.type || "get",
            url:  requestData.url || "",
            data: requestData.params || {},
            timeout: 7000,
            cache: false,
            dataType: 'json',
            success: function(data){

                if(Config.debug){
                    data = {
                                "success":true,
                                "result":{ 
                                            "prize":{
                                                "prize_info":[
                                                    {
                                                        "value":10,
                                                        "min_cost":11
                                                    }
                                                ],
                                                "prize_code":1
                                            },
                                            "lotteryResult":false
                                        },
                                "message":""
                            }
                }
                callback(data);
            },
            complete: function() {}
        });
    } 
}
