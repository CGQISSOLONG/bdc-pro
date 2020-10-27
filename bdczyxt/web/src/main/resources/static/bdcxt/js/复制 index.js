
	$(function() {
		
		//		头部导航点击效果
		$('.nav li').click(function() {
			$(this).addClass('on').siblings().removeClass('on')
		})
		//		鼠标经过头像弹出菜单
		var timer;
		$('.head-right,.head-box').hover(function() {
			clearInterval(timer)
			$('.head-box').slideDown(150)
		}, function() {
			timer = setTimeout(function() {
				$('.head-box').slideUp(150)
			}, 500)
		})
		$('.skin-bg').hover(function() {
			$('.skin-list').fadeIn(150)
		}, function() {
			$('.skin-list').fadeOut(150)
		})

		//左侧菜单移入移出 伸缩
		
		$('.menu').hover(function() {
		   $('.menu').removeClass('showmenu');
           $("#iframeContanier").stop(false,false).animate({left:"234px"},500); 
           if($('.f-menu li.on').length !=0){
           	  $('.f-menu li.on').children("ul").slideDown(300);
           }
		},function(){
			$('.menu').addClass('showmenu');
			$('.f-menu').find("ul").slideUp(300);
			$("#iframeContanier").stop(false,false).animate({left:"49px"},500);
		})
		
		$('.menu-item').each(function() {
			if($(this).siblings('ul').length == 0) {
				$(this).children('.list-txt').css('background', 'none');
			}
		})
		
		//左侧菜单点击一些列效果
		var showMenu = true;
	    $('.menu-item').click(function() {
	    	
	    	$('.menu-item').removeClass('on');
	    	$(this).addClass('on');
	    	
			if($(this).siblings('ul').length == 0) {
				$(this).parent('li').addClass('on').siblings().removeClass('on');
				$(this).parent('li').siblings().find('ul').slideUp(300);
				return false;
			} else {
				if($(this).parent('li').hasClass('on')) {
					$(this).parent('li').removeClass('on')
					$(this).siblings('ul').slideUp(300);
				} else {
					$(this).parent('li').addClass('on')
					$(this).siblings('ul').slideDown(300);
				}
				$(this).parent('li').siblings().removeClass('on').children('ul').slideUp(300)
			}
		})

		$(window).resize(function() {
			setWidth()
		})
		setWidth()

		function setWidth() {
			var width = ($('.leftinfos').width() - 12) / 2;
			$('.infoleft,.inforight').width(width);
			$('.inforight').css('margin-left',(width+12)+'px')
		}
		
		$(".tab-bar-home").click(function(){
			$(this).addClass("on");
			$("#homePage").show();
			$("#div_pannel").css("display","none");
			$(".tab-bar li").removeClass("on")
		})
		
		
		//分页显示内容
        $(".pagination li").click(function(){
          var _index=$(this).index();
           $(".pagination li").eq(_index).addClass("on").siblings().removeClass("on");
           $(".home-list").hide().eq(_index).fadeIn();
           sbslfx3.resize();
			cwlzb4.resize();
        })
	})
	

    //删除导航条中某一个tab项，同时删除其对应下面的ifrmame
	function RemoveDiv(obj){  
        var ob = document.getElementById(obj);  
        ob.parentNode.removeChild(ob);  
        var obdiv = document.getElementById("div_" + obj);  
        obdiv.parentNode.removeChild(obdiv);  
        var tablist = document.getElementById("div_tab").getElementsByTagName('li');  
        var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');  
        if (tablist.length > 0){  
            tablist[tablist.length - 1].className = 'on'  
            pannellist[tablist.length - 1].style.display = 'block'  
        } else{
        	document.getElementById("div_pannel").style.display="none";
        	document.getElementById("homePage").style.display="block";
//      	document.getElementById("#homeBar").className += ' on';	
        }
        window.event? window.event.cancelBubble = true : e.stopPropagation();
    }
	 
	//删除所有子节点
	function removeAllChild(){       
	    var div = document.getElementById("div_tab");
	    while(div.hasChildNodes()){ //当div下还存在子节点时 循环继续
	        div.removeChild(div.firstChild);
	    }
	}
	
	//动态创建导航条		
	function CreateDiv(tabid, url, name){ 
		
        //如果当前tabid存在直接显示已经打开的tab，如果不存在则需要重新创建 
        if (document.getElementById("div_" + tabid) == null){ 
        	document.getElementById("homePage").style.display="none";
        	
            //创建iframe  
            var box = document.createElement("iframe");  
            box.id = "div_" + tabid;  
            box.src = url;  
            box.height = "100%";  
            box.frameBorder = 0;  
            box.width = "100%";  
            document.getElementById("div_pannel").appendChild(box);
            document.getElementById("div_pannel").style.display="block";
      
            //遍历并清除开始存在的tab当前效果并隐藏其显示的div  
            var tablist = document.getElementById("div_tab").getElementsByTagName('li');  
            var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');  
            if (tablist.length > 0){  
                for (i = 0; i < tablist.length; i++){  
                    tablist[i].className = "";  
                    pannellist[i].style.display = "none";  
                }  
            }  
      
            //创建li菜单  
            var tab = document.createElement("li");  
            tab.className = "on";  
            tab.id = tabid; 
            tab.onclick=function(){
            	CreateDiv(tabid,url,name);
            }
            var litxt = '<span>'+name+'</span><span class="tabclose" onclick="RemoveDiv(' + tabid + ')"><img src="img/tab-close.png"></span>';
            tab.innerHTML = litxt;  
            document.getElementById("div_tab").appendChild(tab); 
            
        }  
        else{ 	
        	document.getElementById("div_pannel").style.display="block";
        	document.getElementById("homePage").style.display="none";
            var tablist = document.getElementById("div_tab").getElementsByTagName('li');  
            var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');  
            //alert(tablist.length);  
            for (i = 0; i < tablist.length; i++){  
                tablist[i].className = "";  
                pannellist[i].style.display = "none";  
            }  
            document.getElementById(tabid).className = "on";  
            document.getElementById("div_" + tabid).style.display = "block";
        }  
    }
	
//	var tablist = document.getElementById("div_tab").getElementsByTagName('li'); 
//	var oLiW=0;
//	 for (i = 0; i < tablist.length; i++){  
//      oLiW+= parseInt(tablist[i].offsetWidth);
//  } 
//  var UlW=document.getElementById("div_tab").offsetWidth;
//  console.log(oLiW)
	






