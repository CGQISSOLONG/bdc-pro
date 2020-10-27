/**
 * @return {string}
 */
function RootPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    return pathName.substr(0, index + 1);
}

/**
 * 获取序列的下一位
 * @param name 序列名称
 * @private
 */
function _get_sequence(name) {
    var url = RootPath() + "/common/getSequence/" + name;
    _ajax_get(url, function (result) {
        if (result.ret == 1) {
            return result.data.seq;
        } else if (result.ret == 0) {
            _alert("错误:" + result.exception);
            return null;
        } else {
            _alert(result.msg);
            return null;
        }
    })
}


/**
 * ajax post 请求
 * @param url
 * @param data
 * @param callBack
 */
function _ajax_post(url, data, callBack) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: url,
        data: data,
        success: function (result) {
            callBack(result);
        },
        error: function (result) {
            alert("系统异常，请稍后再试");
        }
    });
}

/**
 * ajax post 请求 contentType:'application/json'
 * @param url
 * @param data
 * @param callBack
 */
function _ajax_json_post(url, data, callBack) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: url,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            callBack(result);
        },
        error: function (result) {
            alert("系统异常，请稍后再试");
        }

    });
}

/**
 * ajax delete 请求 contentType:'application/json'
 * @param url
 * @param data
 * @param callBack
 */
function _ajax_json_delete(url, data, callBack) {
    $.ajax({
        type: 'delete',
        dataType: 'json',
        url: url,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            callBack(result);
        },
        error: function (result) {
            alert("系统异常，请稍后再试");
        }

    });
}

/**
 * ajax get 请求
 * @param url
 * @param callBack
 */
function _ajax_get(url, callBack) {
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: url,
        success: function (result) {
            callBack(result);
        },
        error: function (result) {
            alert("系统异常，请稍后再试");
        }
    });
}

/**
 * ajax  delete请求
 * @param url
 * @param data
 * @param callBack
 */
function _ajax_delete(url, data, callBack) {
    $.ajax({
        type: 'delete',
        dataType: 'json',
        url: url,
        data: data,
        success: function (result) {
            callBack(result);
        },
        error: function (result) {
            alert("系统异常，请稍后再试");
        }
    });
}



Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 判断是否为空
 * @param data
 * @returns {boolean}
 */
function isEmpty(data) {
    return data === null || data === '' || typeof(data) === 'undefined';
}