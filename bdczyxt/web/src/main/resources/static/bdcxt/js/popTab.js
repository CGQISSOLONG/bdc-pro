
	    var popFlag=0;
	    var fullFlag=0;
	    var pageH=$(document).height();
		$(function(){
			
             //左侧列表tab切换
			$(".f-menu li").click(function(){       
				$(".f-menu li").removeClass("active");
				$(this).addClass("active");
			});
			
			//登录后 点击弹出退出登录
			$(".acount").click(function(){     
				$(".quit").slideDown();
				$(".quit").click(function(){
					$(".quit").slideUp();
					event.stopPropagation();
				});
				event.stopPropagation();
			})
			
			$(".pop-con .icon-home").click(function(){
				var popBottom=pageH-30;
				if(popFlag==2){
					$(".pop-con").animate({bottom:'-'+popBottom+'px'});
					$(".more-box").css({"top":"initial","bottom":"30px"});
					$(".more-box").show();
					popFlag=1;
				}else{
					$(".pop-con").animate({bottom:'0px'});
					$(".more-box").css({"top":"30px","bottom":"initial"});
					$(".more-box").show();
					popFlag=2;
				}		
			});
			
			//页面窗口变化时重新计算页脚离页面底部的距离
			$(window).resize(function(){
			   pageH=$(document).height();
			   var popBottom=pageH-30;
			   if(popFlag==0){
			   	   return false;	  
			   }if(popFlag==1){
			   	   $(".pop-con").animate({bottom:'-'+popBottom+'px'});
			   }
			   else{
			   	   $(".pop-con").animate({bottom:'0px'});
			   } 
			});  
			
			//右下角或者右上角的更多按钮点击
			$(".icon-more").click(function(){       
				if($(".more-box").css('display')=="none"){
					$(".more-box").fadeIn();
				    event.stopPropagation();
				}else{
					$(".more-box").fadeOut();
				}	
			});
			
			//点击全屏 显示
			$(".more-box ul li:first").click(function(){    
				if(popFlag==2){
					fullscreen();
				    $(".more-box").fadeOut();
				    $(".tab-menu").hide();
				}else{
                    fullscreen();
                    $("#sectionId").css({"left":"0px","top":"0px","bottom":"0px"});
                    $("#footerId").css({"bottom":"-30px"})
				    $(".more-box").fadeOut();
				    $(".tab-menu").hide();
				    $("#div_pannel").hide();
				}
			});
			
			//点击关闭全部
			$(".more-box ul li").eq(1).click(function(){    
                removeAllChild();
                $("#div_pannel").html("");

				$(".more-box").fadeOut();
				$(".pop-con").animate({bottom:'-1000px'});
                popFlag=0;
			});
			
			//点击关闭其他tab按钮
			$(".more-box ul li").eq(2).click(function(){    
				
				$(".tablist").find(".on").siblings().remove();
				$("iframe:hidden").remove();
				$(".more-box").fadeOut();
			});
			
			//点击更多弹出框以外的地方  隐藏更多弹出框
		   $(document).click(function(){     
			    $(".more-box").fadeOut();
			    $(".quit").slideUp();
			});
			
			//按下2次Esc按钮退出全屏
			$(document).keyup(function(event){    
				if(popFlag==2){
					switch(event.keyCode) {
				        case 27:
				           $(".tab-menu").show();
				        case 96:
                           $(".tab-menu").show();
				    }
				}else{
					$("#sectionId").css({"left":"230px","top":"62px","bottom":"30px"});
                    $("#footerId").css({"bottom":"0px"})
				    $(".tab-menu").show();
				    $("#div_pannel").show();
				}
			    
			});
			
			 //阻止冒泡事件
			$(".more-box").click(function(event){   
			    event.stopPropagation();
			});
			
             //监听浏览器全屏操作
			var fullscreen=function(){
			    elem=document.body;
			    if(elem.webkitRequestFullScreen){
			        elem.webkitRequestFullScreen();   
			    }else if(elem.mozRequestFullScreen){
			        elem.mozRequestFullScreen();
			    }else if(elem.requestFullScreen){
			        elem.requestFullscreen();
			    }else{
			        //浏览器不支持全屏API或已被禁用
			    }
			}

		});
			
	    //动态创建导航条		
		function CreateDiv(tabid, url, name){ 	
            //如果当前tabid存在直接显示已经打开的tab，如果不存在则需要重新创建 
            if (document.getElementById("div_" + tabid) == null){  
                //创建iframe  
                var box = document.createElement("iframe");  
                box.id = "div_" + tabid;  
                box.src = url;  
                box.height = "100%";  
                box.frameBorder = 0;  
                box.width = "100%";  
                document.getElementById("div_pannel").appendChild(box);
                document.getElementById("div_pannel").style.display="block";
                
                $(".pop-con").animate({bottom:'0px'});
                $(".more-box").css({"top":"30px","bottom":"initial"});
                popFlag=2;
          
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
                var litxt = '<span>'+name+'</span><span class="tabclose" onclick="RemoveDiv(' + tabid + ')"></span>';
                tab.innerHTML = litxt;  
                document.getElementById("div_tab").appendChild(tab); 
                
            }  
            else{  
                var tablist = document.getElementById("div_tab").getElementsByTagName('li');  
                var pannellist = document.getElementById("div_pannel").getElementsByTagName('iframe');  
                //alert(tablist.length);  
                for (i = 0; i < tablist.length; i++){  
                    tablist[i].className = "";  
                    pannellist[i].style.display = "none";  
                }  
                document.getElementById(tabid).className = "on";  
                document.getElementById("div_" + tabid).style.display = "block";
                
                $(".pop-con").animate({bottom:'0px'});
                $(".more-box").css({"top":"30px","bottom":"initial"});
                popFlag=2;
            }  
        }
		
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
            	$(".pop-con").animate({bottom:'-1000px'});
	            popFlag=0;
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
		
//		document.onreadystatechange = function(){
//			alert(document.readyState)
	       /* if(document.readyState == "complete"){
	            document.getElementById('load').style.display = 'none';
	        }*/
//	    }
		