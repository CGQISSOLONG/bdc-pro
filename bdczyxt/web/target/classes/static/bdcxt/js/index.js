var tipCon = document.getElementById("tipCon");
var fullcontent = tipCon.innerHTML;
/*<![CDATA[*/
ctxPath = /*[[@{/}]]*/ '';

/*]]>*/
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    //return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
    return '/' + webName;
}

$(function () {
    //头部导航点击效果
    $('.nav li').click(function () {
        $(this).addClass('on').siblings().removeClass('on')
    });
    //		鼠标经过头像弹出菜单
    var timer;
    $('.head-right,.head-box').hover(function () {
        clearInterval(timer)
        $('.head-box').slideDown(150)
    }, function () {
        timer = setTimeout(function () {
            $('.head-box').slideUp(150)
        }, 500)
    });

    var timer2;
    $('.head-rightZ,.head-boxZ').hover(function () {
        clearInterval(timer2)
        $('.head-boxZ').slideDown(150)
    }, function () {
        timer2 = setTimeout(function () {
            $('.head-boxZ').slideUp(150)
        }, 500)
    });
    $('.skin-bg').hover(function () {
        $('.skin-list').fadeIn(150)
    }, function () {
        $('.skin-list').fadeOut(150)
    });

    //左侧菜单移入移出 伸缩

    /*$('.menu').hover(function () {
        $('.menu').removeClass('showmenu');
        $("#iframeContanier").stop(false, false).animate({left: "234px"}, 500);
        if ($('.f-menu li.on').length != 0) {
            $('.f-menu li.on').children("ul").slideDown(300);
        }
    },function () {
        $('.menu').addClass('showmenu');
        $('.f-menu').find("ul").slideUp(300);
        $("#iframeContanier").stop(false, false).animate({left: "49px"}, 500);
    });*/

    menu_block();

    function menu_block() {
        $('.menu').removeClass('showmenu');
        $("#iframeContanier").stop(false, false).animate({left: "234px"}, 500);
        if ($('.f-menu li.on').length != 0) {
            $('.f-menu li.on').children("ul").slideDown(300);
        }
    }

    $('.menu-item').each(function () {
        if ($(this).siblings('ul').length == 0) {
            $(this).children('.list-txt').css('background', 'none');
        }
    });

    //左侧菜单点击一些列效果
    var showMenu = true;
    $("body").on("click",".menu-item",function(){
        $('.menu-item').removeClass('on');
        $(this).addClass('on');
        if ($(this).siblings('ul').length == 0) {
            $(this).parent('li').addClass('on').siblings().removeClass('on');
            $(this).parent('li').siblings().find('ul').slideUp(300);
            return false;
        } else {
            if ($(this).parent('li').hasClass('on')) {
                $(this).parent('li').removeClass('on');
                $(this).siblings('ul').slideUp(300);
            } else {
                $(this).parent('li').addClass('on');
                $(this).siblings('ul').slideDown(300);
            }
            $(this).parent('li').siblings().removeClass('on').children('ul').slideUp(300)
        }
    });

    $(window).resize(function () {
        setWidth()
    });
    setWidth();

    function setWidth() {
        var width = ($('.leftinfos').width() - 12) / 2;
        $('.infoleft,.inforight').width(width);
        $('.inforight').css('margin-left', (width + 12) + 'px')
    }

    $(".tab-bar-home").click(function () {
        $(this).addClass("on");
        //$("#homePage").show();
        $("#div_pannel").css("display", "none");
        $(".tab-bar li").removeClass("on")
    });


    //分页显示内容
    $(".pagination li").click(function () {
        var _index = $(this).index();
        $(".pagination li").eq(_index).addClass("on").siblings().removeClass("on");
        $(".home-list").hide().eq(_index).fadeIn();
        sbslfx3.resize();
        cwlzb4.resize();
    })
});


//删除导航条中某一个tab项，同时删除其对应下面的ifrmame
function RemoveDiv(obj, objLi) {
    var ob = document.getElementById(obj);
    var obdiv = document.getElementById("div_" + obj);
    var tablist = document.getElementById("div_tab").getElementsByTagName('li');
    var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');
    var currentClass = objLi.parentElement.className;
    if (currentClass != "on") {
        ob.parentNode.removeChild(ob);
        obdiv.parentNode.removeChild(obdiv);
    } else {
        ob.parentNode.removeChild(ob);
        obdiv.parentNode.removeChild(obdiv);
        if (tablist.length > 0) {
            tablist[tablist.length - 1].className = 'on'
            pannellist[tablist.length - 1].style.display = 'block'
        } else {
            document.getElementById("div_pannel").style.display = "none";
            //document.getElementById("homePage").style.display="block";
        }
    }
    window.event ? window.event.cancelBubble = true : e.stopPropagation();
}

//关闭所有标签
function removeAllLiChild() {
    debugger;
    var div = document.getElementById("div_tab");
    var li_list = div.getElementsByTagName("li");
    for (var i = 0; i < li_list.length; i++) {
        var id = li_list[i].getAttribute("id");
        if (id != 0) {
            var ob_li = document.getElementById(id);
            var ob_div = document.getElementById("div_" + id);
            ob_li.parentNode.removeChild(ob_li);
            ob_div.parentNode.removeChild(ob_div);
            i = 0;
        }
    }
    document.getElementById("0").style.display = "block";
    document.getElementById("div_0").style.display = "block";
}

//关闭其他标签
function removeotherAllLiChild() {
    var div = document.getElementById("div_tab");
    var li_list = div.getElementsByTagName("li");
    var temp = 0;
    for (var i = 0; i < li_list.length; i++) {
        var id = li_list[i].getAttribute("id");
        if (id != 0 && li_list[i].className != "on") {
            var ob_li = document.getElementById(id);
            var ob_div = document.getElementById("div_" + id);
            ob_li.parentNode.removeChild(ob_li);
            ob_div.parentNode.removeChild(ob_div);
            i = 0;
        }
        if (li_list[i].className == "on") {
            temp = id;
        }
    }
    document.getElementById(id + "").style.display = "block";
    document.getElementById("div_" + id).style.display = "block";
}


//删除所有子节点
function removeAllChild() {
    var div = document.getElementById("div_tab");
    while (div.hasChildNodes()) {
        //当div下还存在子节点时 循环继续
        div.removeChild(div.firstChild);
    }
}

function to_toggleClass() {
    $('.menu').toggleClass('showmenu');
    var className = document.getElementById("aside_menu").className;
    if (className == "menu") {
        $("#iframeContanier").stop(false, false).animate({left: "234px"}, 500);
        if ($('.f-menu li.on').length != 0) {
            $('.f-menu li.on').children("ul").slideDown(300);
        }
        $("#footerVal").css("display", "block");
    } else {
        $('.f-menu').find("ul").slideUp(300);
        $("#iframeContanier").stop(false, false).animate({left: "49px"}, 500);
        $("#footerVal").css("display", "none");
    }
}

var oLiW = 0;

function lay_win(msg) {
    layer.open({
        title:['温馨提示','text-align: center;padding:0;'],
        type: 0,
        closeBtn: 0,
        btn: [],
        shade:0.4,
        time: 1000,
        content: '<p style="text-align: center">'+msg+'</p>'
    });
}

//动态创建导航条
function CreateDiv(tabid, url, name) {
    if (url == 'jsz') {
        lay_win("功能正在建设中...");
        return;
    }
    //如果当前tabid存在直接显示已经打开的tab，如果不存在则需要重新创建
    if (document.getElementById("div_" + tabid) == null) {
        //document.getElementById("homePage").style.display="none";
        //创建iframe
        var box = document.createElement("iframe");
        box.id = "div_" + tabid;
        box.src = ctxPath + url;
        box.height = "100%";
        box.frameBorder = 0;
        box.width = "100%";
        document.getElementById("div_pannel").appendChild(box);
        document.getElementById("div_pannel").style.display = "block";

        //遍历并清除开始存在的tab当前效果并隐藏其显示的div
        var tablist = document.getElementById("div_tab").getElementsByTagName('li');
        var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');
        if (tablist.length > 0) {
            for (i = 0; i < tablist.length; i++) {
                tablist[i].className = "";
                pannellist[i].style.display = "none";
            }
        }
//          console.log(tablist.length)

        //创建li菜单
        var tab = document.createElement("li");
        tab.className = "on";
        tab.id = tabid;
        tab.onclick = function () {
            CreateDiv(tabid, url, name);
        }
        if (tabid == 0) {
            var litxt = '<span>' + name + '</span><span class="tabclose" onclick="RemoveDiv(' + tabid + ',this)">' +
                '</span>';

        } else {
            var litxt = '<span>' + name + '</span><span class="tabclose" onclick="RemoveDiv(' + tabid + ',this)">' +
                '<img src="' + getRootPath() + '/bdcs/img/tab-close.png"/></span>';
        }

        tab.innerHTML = litxt;
        document.getElementById("div_tab").appendChild(tab);
        //计算标签宽度
        calculateNav();
    } else {
        document.getElementById("div_pannel").style.display = "block";
        //document.getElementById("homePage").style.display="none";
        var tablist = document.getElementById("div_tab").getElementsByTagName('li');
        var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');
        //alert(tablist.length);
        for (i = 0; i < tablist.length; i++) {
            tablist[i].className = "";
            pannellist[i].style.display = "none";
        }
        document.getElementById(tabid).className = "on";
        document.getElementById("div_" + tabid).style.display = "block";
    }
}

//当导航栏菜单过多时,重新计算分配每个tab菜单的宽度,做到类似浏览窗口菜单的效果
function calculateNav() {
    var ScreenW = document.body.offsetWidth;
    var asideW = document.getElementById("aside").offsetWidth;
    var tabBarW = document.getElementById("div_tab").offsetWidth;
    // 修改导航条菜单长度
    if (ScreenW - asideW <= parseInt(tabBarW + 400)) {
        //删除第二个，保留首页
        $("#div_tab li:first-child+li").remove();
        $("#div_pannel iframe:first-child+iframe").remove();
    }
}

/*右下角推送提示框*/
function TipPopShow() {
    $(".tip-pop").slideDown(1000).show(500);
}

function TipPopHide() {
    $(".tip-pop").slideUp(1000).hide(500);
}

//TipPopShow();

/*右下角推送提示框文字内容超过处理*/
function subcontent(objstr, length) {
    var str = objstr.replace(/[\s]/g, "");
    str = str.substr(0, length);
    return str;
}

/*tipCon.innerHTML = subcontent(fullcontent, 80) + "...<span></span>";*/


    	
	






