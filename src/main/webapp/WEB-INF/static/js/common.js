$.devMode = true;
$.page_content_name = "body";

//法师预览连接
monkUrl="http://h5.fotuozi.com/monk/subtemple/index?id=";
templeUrl="http://h5.fotuozi.com/temple/subtemple/index?id=";
monlamActiveUrl="http://h5.fotuozi.com/monlam/subtemple/detail?id=";
monlamDutyUrl="http://h5.fotuozi.com/monlam/subtemple/detail?id=";
projectUrl="http://h5.fotuozi.com/projectTemple/subtemple/detail?id=";





$(function () {
    //修正控制台报错
    if ($(".navbar").length == 0) {
        $("body").append('<div style="display: none" class="navbar"></div>');
    }
    //自动隐藏提示信息
    //setTimeout(function () {
    //    $("button.close").trigger("click");
    //}, 3000);

    //if (top.location != location) {
    //    $("body").on("resize", function () {
    //         console.log("body resize @@@ document="+$(document).height()+",body="+$("body").height())
    //        $(document).height($(document).contents().height());
    //    })
    //}
    bootbox.setDefaults({
        locale: "zh_CN"
//show: true,
//backdrop: true,
//closeButton: true,
//animate: true,
//className: "my-modal"
    });
    $('.date-picker').datepicker({
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd',
        language: 'zh-CN'
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
});

var defaultSpinOpt = {
    lines: 13, // 画的线条数
    length: 6, // 每条线的长度
    width: 2, // 线宽
    radius: 12, // 线的圆角半径
    corners: 1, // Corner roundness (0..1)
    rotate: 50, // 旋转偏移量
    direction: 1, // 1: 顺时针, -1: 逆时针
    color: '#000', // #rgb or #rrggbb or array of colors
    speed: 1.4, // 转速/秒
    trail: 16, // Afterglow percentage
    shadow: false, // 是否显示阴影
    hwaccel: false, // 是否使用硬件加速
    className: 'spinner', // 绑定到spinner上的类名
    zIndex: 2e9, // 定位层 (默认 2000000000)
    top: '100%', // 相对父元素上定位，单位px
    left: 'auto' // 相对父元素左定位，单位px
};

$.fn.spin = function (opts) {
    this.each(function () {
        var $this = $(this),
            data = $this.data();

        if (data.spinner) {
            data.spinner.stop();
            delete data.spinner;
        }
        if (opts !== false) {
            data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
        }
    });
    return this;
};


/**
 * 公共的js,options等
 */


/*******************************异步加载一个页面到指定元素上************************************/
//打开内部页面,href表示内部页面的全限定路径
function openInnerPage(href, param) {
    href = setUrlParams(href, param);
    window.location.hash = href;
}

/*****************************jqgrid****************************/

//应用默认的jqgrid配置
var _defaultJqGridOptions = {
    datatype: "json",//从服务器端返回的数据类型
    mtype: 'get',//提交方式
    height: 350,//表格高度
    autowidth: true,//默认值为false。如果设为true，则Grid的宽度会根据父容器的宽度自动重算。重算仅发生在Grid初始化的阶段；
    multiselect: true, //定义是否可以多选
    multiboxonly: true,	//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
    rowNum: 10,//在grid上显示记录条数，这个参数是要被传递到后台
    rowList: [10, 20, 30],//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
    pager: '#grid-pager',//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
    viewrecords: true,//定义是否要显示总记录数
    caption: "查询表格",//表格名称

    jsonReader: {//用来设定如何解析从Server端发回来的json数据
        root: "rows", // json中代表实际模型数据的入口
        page: "page", // json中代表当前页码的数据
        total: "pages", // json中代表页码总数的数据
        records: "total", // json中代表数据行总数的数据
        repeatitems: false // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；
        //而所使用的name是来自于colModel中的name设定。
    },

    prmNames: {//用于设置jqGrid将要向Server传递的参数名称
        page: "page.pn", // 表示请求页码的参数名称
        rows: "page.size",  // 表示请求行数的参数名称
        sort: "sort" // 表示用于排序的列名的参数名称
    },
//    prmNames: {
//        page: "page", // 表示请求页码的参数名称
//        rows: "rows", // 表示请求行数的参数名称
//        sort: "sidx", // 表示用于排序的列名的参数名称
//        order: "sord", // 表示采用的排序方式的参数名称
//        search: "_search", // 表示是否是搜索请求的参数名称
//        nd: "nd", // 表示已经发送请求的次数的参数名称
//        id: "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
//        oper: "oper", // operation参数名称
//        editoper: "edit", // 当在edit模式中提交数据时，操作的名称
//        addoper: "add", // 当在add模式中提交数据时，操作的名称
//        deloper: "del", // 当在delete模式中提交数据时，操作的名称
//        subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
//        npage: null,
//        totalrows: "totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
//    },

    loadComplete: function () {//当从服务器返回响应时执行
        var table = this;
        setTimeout(function () {
            updateJqGridPagerIcons(table);
//            updateJqGridSubGridIcons(table);
            //enableTooltips(table);
        }, 0);
    },
    beforeRequest: function () {
        var table = this;
        var postData = $(table).jqGrid('getGridParam', "postData");
        var sortname = postData["sort"];
        if (sortname) {
            var sortorder = postData["sord"];
            delete postData[sortname];
            delete postData["sort"];
            delete postData["sord"];
            var delKey;
            for (var key in postData) {
                if (key.indexOf("sort.") == 0) {
                    delKey = key;
                }
            }
            delete postData[delKey];
            postData["sort." + sortname] = sortorder;
            $(table).jqGrid('setGridParam', {"postData": postData});
            //console.log(postData);
        }
    },
    gridComplete: function(){
    	var table = this;
        var colModel=$(this).jqGrid('getGridParam','colModel');
        var tableId = $(this).attr("id");
        $.each(colModel,function(index,item){
        	if(item.sortable==true){
        		$("#jqgh_"+tableId+"_"+item.index).find("span.s-ico").css("display","inline");
        	}
        	
        });
    }

};


//replace icons with FontAwesome icons like above
function updateJqGridPagerIcons(table) {

    var replacement =
    {
        'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
        'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
        'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
        'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    })
}

//replace icons with FontAwesome icons like above
function updateJqGridSubGridIcons(table) {

    var replacement =
    {
        'ui-icon-plus': 'icon-plus',
        'ui-icon-minus': 'icon-minus',
        'ui-icon-carat-1-sw': 'icon-level-down icon-flip-horizontal'
    };
    $('.ui-jqgrid-btable > tbody > tr > .ui-sgcollapsed > a > .ui-icon').each(function () {
        $(this).css("position", "relative");
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    });

    $('.ui-jqgrid-btable > tbody > tr.ui-subgrid > td.subgrid-cell > .ui-icon').each(function () {
        $(this).css("position", "relative");
        $(this).css("left", "8px");
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    });
}

//改变删除dialog的样式
function beforeDeleteCallback(e) {
    var form = $(e[0]);
    if (form.data('styled')) {
        return false;
    }
    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
    style_delete_form(form);
    form.data('styled', true);
}

//改变删除dialog的样式
function style_delete_form(form) {
    var buttons = form.next().find('.EditButton .fm-button');
    buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
    buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
    buttons.eq(1).prepend('<i class="icon-remove"></i>')
}


//Tooltip控件
function enableTooltips(table) {
    $('.navtable .ui-pg-button').tooltip({container: 'body'});
    $(table).find('.ui-pg-div').tooltip({container: 'body'});
}


//使返回的配置项继承于默认的jqgrid options
function jqGridOption(options) {
    return $.extend(true, {}, _defaultJqGridOptions, options);
}

//jqgrid查询方法
function jqGridQuery(tableId, postData) {

    $(tableId).jqGrid('setGridParam', {"postData": postData}).trigger("reloadGrid");
}

//jqgrid行按钮
function initCustomAct(gridTable, actClick, actHtmls) {
    if (!actHtmls) {
        actHtmls =
            "<div style='margin-left: 8px;'>" +
            "<div class='ui-pg-div' style='float:left; curosr: pointer;'><span class='ui-icon icon-pencil blue'></span></div>" +
            "<div class='ui-pg-div' style='float:left; curosr: pointer; margin-left: 5px;'><span class='ui-icon icon-trash red'></span></div>" +
            "</div>";
    }
    var ids = $(gridTable).jqGrid('getDataIDs');
    for (var i = 0; i < ids.length; i++) {
        var id = ids[i];//rowId
        $(gridTable).jqGrid('setRowData', ids[i], {'_act': actHtmls});
        actClick(i, id);
    }
}


/*****************************jquery-ui*************************************/

//jquery-ui-dialog默认配置参数
var _defaultDialogOptions = {
    autoOpen: false,
    width: 1200,
    height: 600,
    modal: true,
    title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i> " + "</h4></div>",
    title_html: true,
    beforeClose: function () {
        $(this).empty();
        $(this).dialog("destroy");
    },
    buttons: [
        {
            text: "关闭",
            "class": "btn btn-xs",
            click: function () {
                $(this).dialog("close");
            }
        }
    ]
}

//使返回的配置项继承于默认的jquery-ui-dialog options
function dialogOption(options) {

    return $.extend(true, {}, _defaultDialogOptions, options);
}

//override dialog's title function to allow for HTML titles
(function () {
    if ($.ui && $.ui.dialog) {
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));
    }

})();


/****************************gritter********************************/
if ($.gritter) {
    $.gritter.options = {
        fade_in_speed: 'fast',
        fade_out_speed: 400,
        time: 3000
    };
}

//jquery-gritter消息框
function addGritter(level, content, after_open, after_close) {

    var title =
    {
        "info": '提示',
        "success": '成功',
        "warning": '警告',
        "error": '错误'
    };

    var titleContent = "";
    if (level in title) {
        titleContent = title[level];
    }

    $.gritter.add({
        title: titleContent,
        text: content,
        class_name: "gritter-" + level,
        after_open: after_open,
        after_close: after_close
    });

}


/****************************overlay********************************/
//打开遮罩层
function showOverlay(selector, option) {
    if (!selector) {
        selector = $.page_content_name;
    }
    $(selector).block({
       // message: '<h5><img src="' + ctx + '/static/img/loading.gif"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理中，请稍候...</h5>',
        message: "<div class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width:100%;'><h2><span style='color: black;'>处理中，请稍候...</span></h2></div>",
        css: {
           // border: '3px solid #438eb9',
            border: '1px solid',
            width: "600px"
        }
    });

}

//关闭遮罩层
function closeOverlay(selector) {
    if (!selector) {
        selector = $.page_content_name;
    }
    $(selector).unblock();
}


/****************************jquery-ui-datepicker********************************/
var _defaultDatePickerOptions = {
    dateFormat: "yy-mm-dd",
    showOtherMonths: true,
    selectOtherMonths: true,
    beforeShow: function () {
        setTimeout(function () {
            $("#ui-datepicker-div").css("z-index", 9999);
        }, 100);
    },
    onClose: function () {
        $(this).blur();
    }
}

//使返回的配置项继承于默认的options
function datePickerOption(options) {
    return $.extend(true, {}, _defaultDatePickerOptions, options);
}

/****************************jquery-validate********************************/

if ($.validator) {
    $.validator.messages = {
        required: "此输入项为必填项",
        remote: "请检查此输入项",
        email: "请输入一个有效的邮件地址",
        url: "请输入一个有效的URL。",
        date: "请输入一个有效的日期",
        dateISO: "请输入一个有效的日期(ISO)",
        number: "请输入一个有效的数字",
        digits: "请输入一个有效的数字",
        creditcard: "请输入一个有效的身份证ID",
        equalTo: "请再次输入相同的内容",
        maxlength: $.validator.format("请输入一个不超过{0}位的字符"),
        minlength: $.validator.format("请输入一个不小于{0}位的字符"),
        rangelength: $.validator.format("请输入一个长度在 {0} 和 {1} 位之间的字符"),
        range: $.validator.format("请输入一个在 {0} 和 {1} 之间的字符"),
        max: $.validator.format("请输入一个小于等于 {0}的值"),
        min: $.validator.format("请输入一个大于等于 {0}的值")
    }
}

//默认的jquery-validate配置参数
var _defaultValidateOptions = {
    onsubmit: false,//默认不在submit()事件时触发验证
    errorElement: 'div',
    errorClass: 'help-block',
    ignore: ".ignore",
    //错误信息摆放的位置
    errorPlacement: function ($error, element) {
        var controls = element.closest('div[class*="col-"]');
        controls.append($error);
    },
    //验证非法后的处理
    invalidHandler: function (event, validator) {
        var errNum = validator.numberOfInvalids();
        if (errNum) {
            addGritter("warning", "此表单中有" + errNum + "项输入非法，无法提交，请检查");
        }
    },
    //控件验证错误时如何高亮
    highlight: function (element, errorClass, validClass) {
        $(element).closest(".form-group").removeClass("has-info").addClass("has-error");
    },
    //控件验证成功时的处理
    success: function ($error, element) {
        $(element).closest('.form-group').removeClass('has-error').addClass('has-info');
        $error.remove();
    }
}

//使返回的配置项继承于默认的jquery-validate options
function jValidateOption(options) {

    return $.extend(true, {}, _defaultValidateOptions, options);
}


/****************************mustache********************************/

/**
 * 解析mustache模版
 * 1.options: 参数
 * selector: 目标节点,
 * data: 模版数据(json对象),
 * url: 模版路径,
 * extend: 额外的数据扩展(json对象),
 * partial: 子模版参数{json对象}
 * 如下:
 * {
	 * 	"selector": ".tabbable",
	 * 	"data": data,
	 * 	"url": "pages/mst/travelMg/ticket/ticket_list/ticket_list.mst",
	 * 	"extend": extend,
	 * 	"partial": {
	 * 		"ticket_date": "pages/mst/travelMg/ticket/ticket_list/ticket_date.mst",
	 * 		"ticket_list_body": "pages/mst/travelMg/ticket/ticket_list/ticket_list_body.mst"
	 * 	}
	 * }
 * 2.callback: 回调函数
 *
 */
function mstRender(options, callback) {

    if (options && typeof options === 'object') {
        var selector = options.selector;
        var data = options.data;
        var templeteUrl = options.url;
        var extend = options.extend;
        var partial = options.partial;

        var d = new Date();

        var rendered = "";
        $.ajax({
            async: false,
            type: "GET",
            url: templeteUrl,
            data: "_t=" + d.getTime(),
            success: function (tpl) {
                $.extend(true, data, extend);//深度拷贝是否需要?
                data = data ? data : {};//return {} if data is invalid
                Mustache.parse(tpl);
                //partial options 子模版
                if (partial && typeof partial === 'object') {
                    $.each(partial, function (name, url) {
                        $.ajax({
                            async: false,//由于在循环里，这里使用同步请求
                            type: "GET",
                            url: url,
                            data: "_t=" + d.getTime(),
                            success: function (pTpl) {
                                partial[name] = pTpl;
                            }
                        });
                    });
                }
                rendered = Mustache.render(tpl, data, partial);
                if (selector) {
                    $(selector).html(rendered);
                }
                if (callback) {//回调
                    callback(rendered);
                }
            }
        });
        return rendered;

        /*
         $.get(templeteUrl, {"_t": d.getTime()}, function(tpl){
         $.extend(true, data, extend);//深度拷贝是否需要?
         Mustache.parse(tpl);
         //partial options 子模版
         if(partial && typeof partial === 'object'){
         $.each(partial, function(name, url){
         $.ajax({
         async: false,//由于在循环里，这里使用同步请求
         type: "GET",
         url: url,
         data: "_t=" + d.getTime(),
         success: function(pTpl){
         partial[name] = pTpl;
         }
         });
         });
         }
         var rendered = Mustache.render(tpl, data, partial);
         if(selector){
         $(selector).html(rendered);
         }
         if(callback){//回调
         callback(rendered);
         }
         });*/
    }
}


/****************************iframe相关********************************/

//iframe内部调用: iframe内部发生高度变化时(如form validate验证后),调用此方法来调整高度
function adjustIframeHeight() {
    var $iframe = $("#page-content-iframe", parent.document);
    var pageContentHeight = $(document).find("body").height();
    $iframe.height(pageContentHeight);
}

//iframe加载时,或parent发生resize事件时，调用此方法
function adaptIframeHeight() {
    var $iframe = $("#page-content-iframe");
    if ($iframe.size() > 0) {
        var pageContentHeight = $iframe.contents().find("body").height();
        $iframe.height(pageContentHeight);
    }
}


/****************************获取url后面拼接的参数********************************/

//获取url后面拼接的参数
function getUrlParams(key, url) {
    var params = {};
    var url = url ? url : window.location.href;
    var pos = url.indexOf("?");
    if (pos > 0) {
        var paraStr = url.substring(pos + 1);
        var params = _convertParaStrToObject(paraStr);
        if (params) {
            return key ? params[key] : params;
        }
    } else {
        return "";
    }
}

//设置url参数
function setUrlParams(url, params) {
    if (url && params) {
        var str = "";
        if (typeof params === 'string') {
            str = params;
        }
        if (typeof params === 'object') {
            $.each(params, function (key, value) {
                if (key && value) {
                    if (typeof value === 'object') {
                        value = JSON.stringify(value);
                    }
                    str += key + "=" + encodeURI(value) + "&";
                }
            });
        }
        if (url.indexOf("?") != -1) {
            url += "&" + str;
        } else {
            url += "?" + str;
        }
    }
    return url;
}

//将url参数(如：name=jack&age=18&sex=1)转换为json对象
function _convertParaStrToObject(paraStr) {
    if (paraStr) {
        var params = {};
        var arr = paraStr.split("&");
        for (var i = 0; i < arr.length; i++) {
            if (arr[i]) {
                var para = arr[i].split("=");
                if (para && para.length == 2) {
                    params[para[0]] = decodeURI(para[1]);
                }
            }
        }
        return params;
    }
}

//得到表单参数,利用formSerialize()方法序列化表单，然后再次解析，转换成json对象
function getFormParams($form) {
    if ($form) {
        var paraStr = $form.formSerialize();
        return _convertParaStrToObject(paraStr);
    }
}

//与在浏览器点击后退按钮相同
function goBack() {
    window.history.back();
}


/*************日志相关********************/
//function log(object) {
//    if ($.devMode) {
//        if (window.console && window.console.log) {
//            //console.trace();
//            console.log(object);
//        }
//    }
//}


/************doGet/doPost/doAjax/getJSON********************/
function doGet(url, data, callback, dataType) {
    return _doRequest(url, data, callback, dataType, "get");
}

function doPost(url, data, callback, dataType) {
    return _doRequest(url, data, callback, dataType, "post");
}

function doAjax(options) {
    return _doRequest(options);
}

function getJson(url, data, callback) {
    if ($.isFunction(data)) {
        callback = data;
        data = undefined;
    }
    var options = {"url": url, "data": data, "success": callback, "overlay": false, "dataType": "json"};
    return doAjax(options);
}

//do request
function _doRequest(url, data, callback, dataType, method) {
    //log("----------do request----------");
    var options = {};
    options["overlay"] = true;//遮罩开关，默认doGet/doPost请求都会开启、关闭遮罩
    options["checkError"] = true;//自动检查返回的错误信息，默认为true
    options["method"] = method ? method : "post";

    if (url && typeof url === 'object') {
        $.extend(options, url);
    } else if (jQuery.isFunction(data)) {
        // shift arguments if data argument was omitted
        options["url"] = url;
        options["dataType"] = dataType || callback;
        options["success"] = data;
        options["data"] = undefined;
    } else {
        options["url"] = url;
        options["data"] = data;
        options["success"] = callback;
        options["dataType"] = dataType;
    }

    //requesting
    var jqxhr = _reqing(options);

    //success
    _reqDone(options, jqxhr);

    //fail
    _reqFail(options, jqxhr);

    //finally
    _reqAlways(options, jqxhr);

    return jqxhr;
}

//发送请求
function _reqing(options) {
    //开启遮罩
    if (options["overlay"]) {
        showOverlay();
    }

    //log("url:" + options["url"]);
    //log("请求参数:");
    //log(options["data"]);

    //log("requesting...");
    return $.ajax({
        "url": options["url"],
        "type": options["method"],
        "dataType": options["dataType"],
        "data": options["data"]
    });
}

//请求执行success
function _reqDone(options, jqxhr) {
    jqxhr.done(function (data) {
        //checkError
        if (options["checkError"]) {
            //log("jqxhr.done, check if exists exception...");
            if (data) {
                //无法找到用户对象
                if (data.exceptionCode == "80") {
                    window.location.href = $("base").attr("href");
                    return;
                }
                //权限错误
                if (data.exceptionCode == "89") {
                    window.location.href = $("base").attr("href") + "pages/error/access_denied.jsp";
                    return;
                }
                if (data.exception) {
                    addGritter("error", data.exception);
                    return;
                }
            }
        }
        //log(">>>>>>>do callback<<<<<<<");
        options["success"](data);
        //log(">>>>>>>end callback<<<<<<<");
    });
}

//请求执行fail
function _reqFail(options, jqxhr) {
    jqxhr.fail(function () {
        addGritter("error", "系统发生异常，请联系管理员");
    });
}

//请求执行最后finally
function _reqAlways(options, jqxhr) {
    jqxhr.always(function () {
        if (options["overlay"]) {
            closeOverlay();
        }
        //log("---------end request-----------");
    });
}


//cookie相关
function setCookie(name, value, expireDays) {
    var exp = new Date();
    if (expireDays) {
        exp.setTime(exp.getTime() + expireDays * 24 * 60 * 60 * 1000);
    } else {
        exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);//缺省30天
    }
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]);
    return null;
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}


(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

/**
 * 初始化游戏类型
 */
function initGamesType(value) {
    doPost(ctx + "/bigapp/server/games/gamesUrl/findGamesType", {}, function (data) {
        if (data.success) {
            var gamesTypeList = data.responseData;
            var str = "";
            $.each(gamesTypeList, function (i, j) {
                if (gamesTypeList[i].gameUrlId == value) {
                    str += "<option value='" + gamesTypeList[i].gameUrlId + "' selected>" + gamesTypeList[i].gameName + "</option>";
                } else {
                    str += "<option value='" + gamesTypeList[i].gameUrlId + "'>" + gamesTypeList[i].gameName + "</option>";
                }
            });
            $("#gamesType").append(str);
        } else {
            addGritter("error", "获取游戏列表出错");
            return;
        }
    });
}

//清空百度编辑器内容
function clearUeditor() {
    UE.getEditor('content').setContent('');
    UE.getEditor('cover').setContent('');
}

//AboutUs 关于我们的新增是百度编辑框重置
function cleartUeditorForAboutUs() {
    UE.getEditor('content').setContent('');
    UE.getEditor('logo').setContent('');
    //重置时,编辑框中的log图片都为空
    $("#imgShow").attr("src","");
    $("#imgShow1").attr("src","");
    $("#imgShow2").attr("src","");
}

//AboutUs 关于我们的编辑时百度编辑框重置
function cleartUeditorForAboutUsEditor() {
    UE.getEditor('content').setContent($("#hiddenDiv1").html());
    UE.getEditor('logo').setContent($("#hiddenDiv2").html());
    //重置时,编辑框中的log图片都为空
    $("#imgShow1").attr("src",$("#hiddenDiv3").html());
    $("#imgShow2").attr("src",$("#hiddenDiv3").html());
}

//EBook 新增电子杂志时百度编辑框重置
function cleartUeditorForEBook() {
    UE.getEditor('content').setContent('');
    UE.getEditor('frontCover').setContent('');
}

//Activity 活动编辑是百度编辑框重置
function clearUeditorForActivityEditor(){
    UE.getEditor('frontCover').setContent($("#hiddenDiv1").html());
    UE.getEditor('activityContent').setContent($("#hiddenDiv2").html());
}

//EBook 电子杂志编辑是百度编辑器重置
function cleartUeditorForEBookEditor() {
    UE.getEditor('content').setContent($("#hiddenDiv1").html());
    UE.getEditor('frontCover').setContent($("#hiddenDiv2").html());
}

//feedBack 意见反馈的编辑时百度编辑框重置
function cleartUeditorForFeedBackEditor() {
    UE.getEditor('answer').setContent($("#hiddenDiv1").html());
}

//Job 加入大大编辑时重置百度编辑框
function cleartUeditorForJobEditor() {
    UE.getEditor('jobDescription').setContent($("#hiddenDiv1").html());
    UE.getEditor('jobRequirement').setContent($("#hiddenDiv2").html());
}

function clearUeditorEditor() {
    UE.getEditor('content').setContent($("#hiddenDiv").html());
    UE.getEditor('cover').setContent($("#hiddenDiv2").html());
}

//产品编辑时重置百度编辑框
function clearUeditorEditorForProduct() {
    UE.getEditor('content1').setContent($("#hiddenDiv1").html());
    UE.getEditor('content2').setContent($("#hiddenDiv2").html());
    UE.getEditor('content3').setContent($("#hiddenDiv3").html());
}

//公告编辑是重置百度编辑框
function cleartUeditorForProclamationEditor() {
    UE.getEditor('content').setContent($("#hiddenDiv1").html());
}

function clearUeditorEditorForAPPVersion() {
    UE.getEditor('versionInformation').setContent($("#hiddenDiv1").html());
}

window.onload = function () {
    /****************************
     * 作者：q821424508@sina.com    *
     * 时间：2012-08-20            *
     * version：2.1                *
     *                            *
     ****************************/
    document.getElementsByTagName("body")[0].onkeydown = function () {

        //获取事件对象
        var elem = event.relatedTarget || event.srcElement || event.target || event.currentTarget;

        if (event.keyCode == 8) {//判断按键为backSpace键

            //获取按键按下时光标做指向的element
            var elem = event.srcElement || event.currentTarget;

            //判断是否需要阻止按下键盘的事件默认传递
            var name = elem.nodeName;

            if (name != 'INPUT' && name != 'TEXTAREA') {
                return _stopIt(event);
            }
            var type_e = elem.type.toUpperCase();
            if (name == 'INPUT' && (type_e != 'TEXT' && type_e != 'TEXTAREA' && type_e != 'PASSWORD' && type_e != 'FILE')) {
                return _stopIt(event);
            }
            if (name == 'INPUT' && (elem.readOnly == true || elem.disabled == true)) {
                return _stopIt(event);
            }
        }
    }
}
function _stopIt(e) {
    if (e.returnValue) {
        e.returnValue = false;
    }
    if (e.preventDefault) {
        e.preventDefault();
    }

    return false;
}

/**
 * 
 * @param url 请求路径
 * @param message 提示信息
 * @param tableId 存放jqGrid的table Id 
 */
function doDel(url,message,tableId){
	
//	bootbox.setDefaults({locale:"zh_CN"});
	bootbox.confirm(message, function(result) {
        if(result) {
            doPost(url, function (data) {
                if (data.success) {
                    addGritter("success", data.message);
                    jQuery(tableId).trigger("reloadGrid");
                } else {
                    addGritter("error", data.message);
                }
            }, "json");
        }
    });
}


function viewDetail(item){
	var itemJson = $.parseJSON(item);
	$(this).bootboxNoCacheBrowser({
		title:itemJson.title,
	    url:itemJson.url,
		width:"100%",
		height:itemJson.height,
		ok:null,
		cancle:null,
		clear:null,
		size:itemJson.size,//size指的是宽度:默认600px；另外有两个可选属性  large：900px small：300px
		target:itemJson.target //top ：最顶层 。parent：父级window内。空字符串：当前window内
	}).showDialog();
}

function jqGridAddButton(buttonJson){

	var $operate =$('<div class="hidden-sm hidden-xs action-buttons"></div>');
	$.each(buttonJson,function(index,item){
	switch(item.operateType){
	case 'edit':
	//修改：item 格式{"operateType":"edit","url":"传递的url"};
	$operate=$operate.append($('<a title="修改" class="green ui-state-hover" style="margin-right:5px;" href="'+item.url+'"><i class="ace-icon fa fa-pencil bigger-130"></i></a>'));
	break;
	case 'delete':
	//删除：item 格式{"operateType":"delete","url":"传递的url",message:""};
	$operate=$operate.append($('<a title="删除" class="red" style="margin-right:5px;"  href="javascript:void(0)" onclick=doDel('+"'"+item.url+"','"+item.message+"','"+item.tableId+"'"+')><i class="ace-icon fa fa-trash-o bigger-130"></i></a>'));
	break;
	case 'detail':
	//查看详细：item 格式{"operateType":"delete","url":"传递的url",title:"传递的title",height:"高度，单位px",size:"大小，参照注释",target:"iframe打开的位置，参照注释"};
	$operate=$operate.append($('<a title="查看" class="blue" style="margin-right:5px;"  href="javascript:void(0)" onclick=viewDetail('+"'"+JSON.stringify(item)+"'"+')><i class="ace-icon fa fa-info-circle bigger-130"></i></a>'));
	break;
	case 'view':
	//直接跳到详情页面的查看,不同于'detail',是直接跳转到详情页面,'detail'是使用dialog方式显示详情内容. 
	$operate=$operate.append($('<a title="查看" class="blue" style="margin-right:5px;" href="'+item.url+'"><i class="ace-icon fa fa-info-circle bigger-130"></i></a>'));
	break;
	default:
	
	}
	});
	return $operate.get(0).outerHTML;

}

function initProvinces(){
    $.ajax({
        url : 'buddha/server/position/getProvince',
        cache : true,
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (result) {
        	var obj = result.responseData;
            $("#provinceId").empty();
            $("#cityId").empty();
            $("#countyId").empty();
            $("#provinceId").append("<option value=''>请选择</option>");
            $("#cityId").append("<option value=''>请选择</option>");
            $("#countyId").append("<option value=''>请选择</option>");
            for (var i = 0; i < obj.length; i++) {
                $("#provinceId").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
            }
        },
        error:function(result) {
            //alertError(result.message);
        }
    });
}

function getCity(){
    var cityId = $("#provinceId").val();
    if(isEmpty(cityId)){
        return false;
    }
    if(cityId=='-1'){
        return false;
    }
    $.ajax({
        url : 'buddha/server/position/getCity',
        cache : true,
        async : false,
        data:{id:cityId},
        type : "POST",
        dataType : 'json',
        success : function (result) {
        	var obj = result.responseData;
            $("#cityId").empty();
            $("#countyId").empty();
            $("#cityId").append("<option value=''>请选择</option>");
            $("#countyId").append("<option value=''>请选择</option>");
            for (var i = 0; i < obj.length; i++) {
                $("#cityId").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
            }
        },
        error:function(result) {
            //alertError(result.message);
        }
    });
}

function getCounty(){
    var cId = $("#cityId").val();
    if(isEmpty(cId)){
        return false;
    }
    if(cId=='-1'){
        return false;
    }
    $.ajax({
        url : 'buddha/server/position/getCounty',
        cache : true,
        async : false,
        data:{id:cId},
        type : "POST",
        dataType : 'json',
        success : function (result) {
        	var obj = result.responseData;
            $("#countyId").empty();
            $("#countyId").append("<option value=''>请选择</option>");
            for (var i = 0; i < obj.length; i++) {
                $("#countyId").append("<option value=" + obj[i].id + ">" + obj[i].name + "</option>");
            }
        },
        error:function(result) {
            //alertError(result.message);
        }
    });
}

function isEmpty(fData) {

    return ((fData == null) || (fData.length == 0) || (trim(fData) == '') || fData == undefined);
}
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}