// 各地区办件统计--折线图
var chartLine = echarts.init(document.getElementById('lineChart'));

var charts = {
    unit: '单位：件',
    names: ['办件量'],
    lineX: [],
    value: []

}
var color = ['rgba(23, 255, 243']
var lineY = ['red']

for (var i = 0; i < charts.names.length; i++) {
    var x = i
    if (x > color.length - 1) {
        x = color.length - 1
    }
    var data = {
        name: charts.names[i],
        type: 'line',
        color: color[x] + ')',
        smooth: true,
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: color[x] + ', 0.3)'
                }, {
                    offset: 0.8,
                    color: color[x] + ', 0)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        symbol: 'circle',
        symbolSize: 5,
        data: charts.value[i]
    }
    lineY.push(data)
}
 option = {
    backgroundColor:'transparent',
    tooltip: {
        trigger: 'axis',
        type: 'line',
        showAllSymbol: true //标注所有数据点
    },
    legend: {
       show:false
    },
    grid: {
        top: '15%',
        left: '3%',
        right: '4%',
        bottom: '10%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: charts.lineX,
        axisLine: {
            lineStyle: {
                color: '#495499'
            }
        },
        axisTick :{
            show: false
        },
        axisLabel: {
            margin: 16,
            textStyle: {
                color: '#89A4CC'
            },
            formatter: function(params) {
                return params.split(' ')[0] 
            }
        }
    },
    yAxis: {
        name: charts.unit,
        type: 'value',
        axisLabel: {
            margin: 16,
            formatter: '{value}',
            textStyle: {
                color: '#89A4CC'
            }
        },
        splitLine: {
            lineStyle: {
                color: 'rgba(46,131,253,0.2)'
            }
        },
        axisTick :{
            show: false
        },
        axisLine: {
            lineStyle: {
                color: '#495499'
            }
        }
    },
    series: lineY
}

chartLine.setOption(option);


function ajax_brokenLine_data_post(url,data) {
    chartLine.showLoading({
        text : '正在加载数据'
    });
    $.ajax({
        type : "post",
        url : url,
        data : data,
        dataType : "json",
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result.code=='200') {
                charts.lineX=[];
                charts.value=[];
                for(var i=0;i<result.data.length;i++){
                    charts.lineX.push(result.data[i].SJ);    //挨个取出类别并填入类别数组
                    charts.value.push(result.data[i].COUNTS);    //挨个取出销量并填入销量数组
                }

                chartLine.hideLoading();    //隐藏加载动画
                chartLine.setOption({        //加载数据图表
                    xAxis: {
                        data:  charts.lineX
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '办件量',
                        data:  charts.value
                    }]
                });

            }else {
                chartLine.hideLoading();
                alert("网络错误，请重试！！！");
            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            chartLine.hideLoading();
        }
    });
}