var selectTab = 0;
var exists_select = false;
var isExpand = true;
var _hideTabid;

/**
 * 判断权限，如果有允许全照权利人查询的权限，则权利人文本框亮起来
 * @param userid
 * @param ctrName
 */
function getPriviledge(userid,ctrName){
    var url = "http://"+window.location.host+"/realestate/prestServlet?method=get&jsonData=null&urlPath=identity/users-roles/"+userid;
    $.get(url,function (resultData) {
        var arrSplit=resultData.split("♀");
        var code=parseInt(arrSplit[0]);
        var result;
        var isCanQuery=false;
        if(code>=200&& code <400) {
            var roles=$.parseJSON(arrSplit[1]).roles;
            for(var i=0;i<roles.length;i++){
                var name=roles[i].name;
                if(name=="允许综合查询按照权利人查询"&&ctrName=="qlr"){
                    isCanQuery=true;
                    $("#"+ctrName).textbox("enable")
                }
                if(name=="允许综合查询按照坐落查询"&&ctrName=="zl"){
                    isCanQuery=true;
                    $("#"+ctrName).textbox("enable")
                }
            }
        }
        if(!isCanQuery){
            $("#"+ctrName).textbox("disable")
        }
    });
}
/**
 * 展开重新设置高度
 */
function setExpandHeight() {
    var cHeight = document.body.scrollHeight-20;
    var c = $('#cc');
    var centen_p = c.layout('panel', 'center');
    centen_p.panel('resize', {height: parseInt((cHeight))});
    var south_p = c.layout('panel', 'south');
    south_p.panel('resize', {height: parseInt((cHeight) * 0.4)});
    c.layout('resize', {
        height: cHeight
    });
    $("#main_cqxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_ygxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_wqlxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_htxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_blzxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_ldxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_hyxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_jtxxifame").height(parseInt((cHeight) * 0.6));
    $("#main_nydxxifame").height(parseInt((cHeight) * 0.6));
    $("#cfxxifame").height(parseInt((cHeight) * 0.4 - 10));
    $("#dyxxifame").height(parseInt((cHeight) * 0.4 - 10));
    $("#yyxxifame").height(parseInt((cHeight) * 0.4 - 10));
    $("#xzxxifame").height(parseInt((cHeight) * 0.4 - 10));
    $("#dyiqxxifame").height(parseInt((cHeight) * 0.4 - 10));
    $("#main_tabls").tabs("resize");
    $("#xz_tabs").tabs("resize");


    resizeDataGrid();
    isExpand = true;
}

/**
 * 关闭重新设置高度
 */
function setCollapseHeight() {
    var cHeight = document.body.scrollHeight - 20;
    var c = $('#cc');
    var centen_p = c.layout('panel', 'center');
    centen_p.panel('resize', {height: parseInt(cHeight * 0.6)});
    var south_p = c.layout('panel', 'south');
    south_p.panel('resize', {height: parseInt(cHeight * 0.4)});
    c.layout('resize', {
        height: cHeight
    });
    $("#main_cqxxifame").height(parseInt(cHeight * 0.6));
    $("#main_ygxxifame").height(parseInt(cHeight * 0.6));
    $("#main_htxxifame").height(parseInt(cHeight * 0.6));
    $("#main_wqlxxifame").height(parseInt(cHeight * 0.6));
    $("#main_blzxxifame").height(parseInt(cHeight * 0.6));
    $("#main_ldxxifame").height(parseInt(cHeight * 0.6));
    $("#main_hyxxifame").height(parseInt(cHeight * 0.6));
    $("#main_jtxxifame").height(parseInt(cHeight * 0.6));
    $("#main_nydxxifame").height(parseInt(cHeight * 0.6));
    $("#cfxxifame").height(parseInt(cHeight * 0.4));
    $("#dyxxifame").height(parseInt(cHeight * 0.4));
    $("#yyxxifame").height(parseInt(cHeight * 0.4));
    $("#xzxxifame").height(parseInt(cHeight * 0.4));
    $("#dyiqxxifame").height(parseInt(cHeight * 0.4));
    $("#main_tabls").tabs("resize");
    $("#xz_tabs").tabs("resize");
    resizeDataGrid();
    isExpand = false;
}


function openPanel(){

}


/**
 * 隐藏tab,多个以逗号分割
 */
function hidetab(){
    if(_hideTabid != null && _hideTabid != ""){
        var splittabs = _hideTabid.split(",");
        if(splittabs.length > 0){
            for(i=0; i<splittabs.length ;i++){
                $('#main_tabls').tabs('getTab',parseInt(splittabs[i])).panel('options').tab.hide();
            }
        }
    }
}


/**
 * 重新设置grid大小
 */
function resizeDataGrid() {
    window["main_cqxxifame"].resizeGrid();
    window["main_ygxxifame"].resizeGrid();
    window["main_wqlxxifame"].resizeGrid();
    window["main_htxxifame"].resizeGrid();
    window["main_blzxxifame"].resizeGrid();
    window["main_ldxxifame"].resizeGrid();
    window["main_hyxxifame"].resizeGrid();
    window["main_jtxxifame"].resizeGrid();
    window["main_nydxxifame"].resizeGrid();
    window["cfxxifame"].resizeGrid();
    window["dyxxifame"].resizeGrid();
    window["yyxxifame"].resizeGrid();
    window["xzxxifame"].resizeGrid();
    window["dyiqxxifame"].resizeGrid();
    $("#xz_tabs").tabs("resize");
    $("#main_tabls").tabs("resize");
}

/**
 * 加载中
 */
function showloading() {
    $("body").showLoading();
}

/**
 * 加载结束
 */
function hideloading() {
    $("body").hideLoading();
}


/**
 * 主的tabs切换事件
 * @param t
 * @param i
 */
function show_childrentalbs(t, i) {
    selectTab = i;
    var isR = true;
    if (i == 0 || i == 4 || i == 5|| i == 7 ||i == 8) {
        if (document.readyState == "complete") {
            try{
                $('#xz_tabs').tabs('enableTab', 1);
                $('#xz_tabs').tabs('enableTab', 2);
                $('#xz_tabs').tabs('enableTab', 3);
                $('#xz_tabs').tabs('select', 0);
                clearXzxxGridData();
            }
            catch(e){
                isR=false;
            }
        }
    } else if (i == 3) {
        if (document.readyState == "complete") {
            try{
                $('#xz_tabs').tabs('disableTab', 1);
                $('#xz_tabs').tabs('disableTab', 2);
                $('#xz_tabs').tabs('disableTab', 3);
                $('#xz_tabs').tabs('select', 0);
                clearXzxxGridData();
            }
            catch(e){}
        }
    } else if (i = 6){
        if (document.readyState == "complete") {
            try{
                $('#xz_tabs').tabs('enableTab', 1);
                $('#xz_tabs').tabs('enableTab', 2);
                $('#xz_tabs').tabs('enableTab', 3);
                $('#xz_tabs').tabs('select', 0);
                clearXzxxGridData();
            }
            catch(e){}
        }
    }

    if(i==0 && document.readyState!="complete"){
        gridRsize();
    }
    else if (i==0 && document.readyState=="complete" && !isR)
    {
        try{
            setExpandHeight();
        } catch(e){}
        //getPriviledge(request("userid"),"qlr");
        //getPriviledge(request("userid"),"zl");
    }
    else{
        resizeDataGrid();
    }

}


/**
 * 设置网格自适应
 */
function gridRsize() {
    if (document.readyState == "complete") {
        if(isExpand){
            resizeDataGrid();
            //setExpandHeight();
        }else{
            setCollapseHeight();
        }
    }
}

/**
 * 重置表单数据
 */
function reset() {
    $('#ff').form('clear');
    $("input[name='zt'][value='1']").prop("checked","true");
}

/**
 * 清除所有表格数据
 */
function clearAllGridData() {
    window["main_cqxxifame"].clearGridData();
    window["main_ygxxifame"].clearGridData();
    window["main_wqlxxifame"].clearGridData();
    window["main_htxxifame"].clearGridData();
    window["main_blzxxifame"].clearGridData();
    window["main_ldxxifame"].clearGridData();
    window["main_hyxxifame"].clearGridData();
    window["main_jtxxifame"].clearGridData();
    window["main_nydxxifame"].clearGridData();
    window["cfxxifame"].clearGridData();
    window["dyxxifame"].clearGridData();
    window["yyxxifame"].clearGridData();
    window["xzxxifame"].clearGridData();
    window["dyiqxxifame"].clearGridData();
}

/**
 * 清除限制信息表格数据
 */
function clearXzxxGridData() {
    window["cfxxifame"].clearGridData();
    window["dyxxifame"].clearGridData();
    window["yyxxifame"].clearGridData();
    window["xzxxifame"].clearGridData();
    window["dyiqxxifame"].clearGridData();
}

/**
 *添加tab图标
 */
function addTabIcon(i) {
    var pp = $("#xz_tabs").tabs("getTab", i);
    $('#xz_tabs').tabs('update', {
        tab: pp,
        options: {
            iconCls: 'icon-tip'
        }
    });
}

/**
 *删除tab图标
 */
function removeTabIcon(i) {
    var pp = $("#xz_tabs").tabs("getTab", i);
    $('#xz_tabs').tabs('update', {
        tab: pp,
        options: {
            iconCls: ''
        }
    });
}

/**
 * 选择tab
 */
function selectTabs(i) {
    $('#xz_tabs').tabs('select', i);
}




function readIdCard(){
    var plugin = document.getElementById("plugin");
    var temp = plugin.On_OpenDevice(1001); //设备端口(1-16,1001-1016)
    if(temp==null || temp ==""){
        layer.msg('未获取到正确的身份证信息，请重新读取！', {icon: 7});
        return;
    }
    var temp2 = $.parseJSON(temp);
    var objSfz =	{
        zjh:temp2['身份证号'],xm:temp2['姓名'],xb:temp2['性别'],sr:temp2['出生日期'],mz:temp2['名族'],zz:temp2['地址'],qfjg:temp2['发证机关'],yxqx:temp2['有效期开始']+'-'+temp2['有效期结束']
        , yxqx1:temp2['有效期开始'], yxqx2: temp2['有效期结束'],
        zpString:temp2['照片']
    }
    $('#ff').form('load',{
        qlr:objSfz.xm,
        zjh:objSfz.zjh
    });
}


function trimStr(str){
    return str.replace(/(^\s*)|(\s*$)/g,"");
}
