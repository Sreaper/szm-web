//定义内部函数扩展对像
var ay = ay || {};

/*
*
*	ay.ns						增加命名空间
*	ay.serializeObject			将form表单元素的值序列化成对象
*	ay.isUnsignedInteger		校验是否是纯数字
*	ay.formatString				序列化表单数据
*	ay.stringToList				接收一个以逗号分割的字符串，返回List，
*								list里每一项都是一个字符串
* 	ay.autoIframeHeight			
*	ay.autoIframeWidth			设置iframe自适应高宽
*	ay.setIframeHeight			
*	ay.setIframeWidth			设置iframe高宽
*	ay.resetEditForm			重置表单
*	ay.resetFormError			清空表单错误校验
*	ay.ajaxGetData
*	ay.replaceHtmlTag		
*	ay.getType
*	ay.isString
*
*	内置对象拓展
*	Array.remove				删除数组中某项
*	Date.format 				格式化日期
*	String.replaceAll			全局替换字符
*/

//增加命名空间，使用方法: ay.ns('jQuery.aaa.bbb', 'jQuery.ccc.ddd')
ay.ns = function() {
    var o = {}, d;
    for (var i = 0; i < arguments.length; i++) {
        d = arguments[i].split('.');
        o = window[d[0]] = window[d[0]] || {};
        for (var k = 0; k < d.slice(1).length; k++){
            o = o[d[k + 1]] = o[d[k + 1]] || {};
        }
    }
    return o;
};

/**
 * 将form表单元素的值序列化成对象
 */
ay.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

ay.isUnsignedInteger = function(str) {
	var reg = /^\d+$/;
	return reg.test(str);
}

/**
 * @author yanhui.wang
 * 
 * 增加formatString功能
 * 使用方法：formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
ay.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * @returns list
 */
ay.stringToList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			/* 避免他将ID当成数字 */
			values.push('' + t[i]);
		}
		return values;
	} else {
		return [];
	}
};

//设置iframe自适应高度
ay.autoIframeHeight = function(iframeName) {
	$('#' + iframeName).height($(document).height() + 20);
}

ay.autoIframeWidth = function(iframeName) {
	$('#' + iframeName).width($(document).width() + 20);
}

//设置iframe高度
ay.setIframeHeight = function(iframeName, height) {
	$('#' + iframeName).height(height);
}

ay.setIframeWidth = function(iframeName, width) {
	$('#' + iframeName).width(width);
}

ay.resetEditForm = function(form){
	form.find("input,textarea,hidden,select,radio,checkbox").each(function(i){
	   $(this).val("");
	});	
}

ay.resetFormError = function(form) {
	form.find("span.error").each(function(i){
	   $(this).html("");
	});	
}

ay.replaceHtmlTag = function(value) {
    if (ay.isString(value)) {
        return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }
    return value;
}

ay.getType = function (object) {
    var _t;
    return ((_t = typeof (object)) == "object" ? object == null && "null" || Object.prototype.toString.call(object).slice(8, -1) : _t).toLowerCase();
}
ay.isString = function(o) {
    return ay.getType(o) == "string";
}


ay.ajaxGetData = function(path, param, method) {
	var result = null;
	$.ajax({
		url: path,
		data: param,
		type: method,
		success: function(data){
			result = data;
		},
		error:function(error){
			$.messager.alert('Error', error, 'error');
		}
	});
	return result;
}

//内置对象原型拓展
/*
*移除数组中key=s的项
*/
Array.prototype.remove = function(s) {     
    for (var i = 0; i < this.length; i++) {     
        if (s == this[i]){
        	this.splice(i,1);
        	return true;
        }     
    }
    return false;
}   
/*
*	格式化日期 (yyyy-MM-dd hh:mm:ss)
*/
Date.prototype.format = function(format) {

	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}

//时间转换  不知哪里会用到
function formatDate(date, fmt) {
    function pad(value) {
        return (value.toString().length < 2) ? '0' + value : value;
    }
    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
    	//1927-12-31 00:0:52（-1325664000000）后就没问题
    	if (date.getTime() < -1325664000000) {
            date = new Date(date.getTime() + (1325664352000 - 1325664000000));
        };
        switch (fmtCode) {
	        case 'Y':
	            return date.getFullYear();
	        case 'M':
	            return pad(date.getMonth() + 1);
	        case 'd':
	            return pad(date.getDate());
	        case 'H':
	            return pad(date.getHours());
	        case 'm':
	            return pad(date.getMinutes());
	        case 's':
	            return pad(date.getSeconds());
	        default:
	            throw new Error('Unsupported format code: ' + fmtCode);
        }
    });
}
// defined prototype function for class
function _extend(child, parent, proto) {
    if (!proto) {
        proto = parent;
        parent = null;
    }
    var childProto;
    if (parent) {
        var fn = function () { };
        fn.prototype = parent.prototype;
        childProto = new fn();
        _each(proto, function (key, val) {
            childProto[key] = val;
        });
    } else {
        childProto = proto;
    }
    childProto.constructor = child;
    child.prototype = childProto;
    child.parent = parent ? parent.prototype : null;
}
console.log("宋枝茂");