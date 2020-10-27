/*$(function () {
    $.get('../xml/serviceXml.xml', function (data) {
        window.servicePort = $(data).find("FGservicePort").text();
    });
});*/
var pagesize = 25,pagenum=0;
var url = null;
var pageurl = null;
$(function () {
    var pager = $('#'+gridid).datagrid('getPager');	// get the pager of datagrid
    pager.pagination({
        displayMsg: "",
        beforePageText:"当前页",
        afterPageText:"共{pages}页"
       /* onSelectPage:function(pageNumber, pageSize) {
            url = url.replace("size="+pagesize,'size='+pageSize);
            pagesize = pageSize;
            pageurl = url +"￥start="+((pageNumber-1)*pagesize);
            //parent.showloading();
            ajaxQuery(pageurl);
        }*/
    });
});


/**
 * 设置行的样式
 * @param index
 * @param row
 * @returns {*}
 */
function rowstyle(index,row) {
    //设置行背景色
    if (row.zt == "历史" || row.zt == "已注销") {
        //设置已历史的数据行背景色
        return "color: #808A87;cursor:hand;";
    } else if (row.zszt != null && (row.zszt.indexOf("有查封") != -1
        || row.zszt.indexOf("有抵押") != -1 || row.zszt.indexOf("有限制") != -1 ||
        row.zszt.indexOf("有预告抵押") != -1|| row.zszt.indexOf("行政限制")!=-1
        || row.zszt.indexOf("办理中") != -1)) {
        //设置已经被限制的数据行背景色
        return "color: #ff0000;cursor:hand;";
    } else {
        return "cursor:hand;";
    }
}


var rowdata = null;
function showMenu(e,rowIndex, row){
    rowdata = row;
    e.preventDefault();
    $('#mm').menu('show', {
        left: e.pageX,
        top: e.pageY
    });
}
function openDetailsRow(type,datagridName,index){
    var rows = $('#'+datagridName).datagrid('getRows');
    var row = rows[index];
    rowdata = row;
    openDetails(type);
}

/**
 * 查看详细信息

 * @param type
 * @param rowData
 */
function openDetails(type) {
    var title = "";
    var url = "";
    if (type == "1") {
        title = "业务办理情况";
        url = "http://" + window.location.host + "/GisqRealEstate-Explorer/ArchiveBag.jsp?qlbm=" + rowdata.qlbm + "&qlbsm=" + rowdata.qlbsm
    } else if (type == "2") {
        title = "历史回溯";
        // url = "http://" + window.location.hostname + ":8034/GisqRealEstate-Explorer/hisRelation/relationTreeShow?qlbm=" + rowdata.qlbm + "&qlbsm=" + rowdata.qlbsm + "&portAndModuleName=8034,GisqRealEstate-Explorer"
        url = "http://" + window.location.host + "/GisqRealEstate-Explorer/HisRelation.html?qlbm=" + rowdata.qlbm + "&qlbsm=" + rowdata.qlbsm
    } else if (type == "3") {
        title = "查看登记簿";
        // url = "http://" + window.location.host + "/realestate/RegBook/index2.html?dybsm=" + rowdata.dybsm + "&zdbsm=" + rowdata.zdshiyqbsm + "&qlbsm=" + rowdata.qlbsm
        url = "http://" + window.location.host + "/realestate/ReportServer?reportlet=realestate/common/regbook_new_sb/regbook_tab.cpt&op=write&dybsm=" + rowdata.dybsm + "&qlbsm=" + rowdata.qlbsm + "&dybm=" + rowdata.dybm
    }else if (type == "4") {
        title = "查看合同";
        url = "http://"+servicePort+"/framework/app/rest/view/" + rowdata.bdcqzh
    }
    showPanel(url,title);
}


function showPanel( url,title) {
    window.open(url, title);
    return;
    try {
        top.updateContentFrame(url, title);
    }
    catch (e) {
        try {
            var data = "{\"event\":\"updateContentFrame\",\"url\":\"" + url + "\",\"title\":\"" + title + "\"}";
            top.postMessage(data, '*');
        }
        catch (e) {
            window.open(url, title);
        }
    }
}