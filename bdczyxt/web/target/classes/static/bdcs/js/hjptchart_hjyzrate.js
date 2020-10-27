// 百度echart图表
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('HistChart')); // 指定图表的配置项和数据

var xData = [],
    yData = [];
var min=0;
option = {
    backgroundColor: "transparent",
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow',
          /*  lineStyle: {
                opacity: 0
            }*/
        },
        formatter: function(prams) {
            if (prams[0].data === min) {
                return "一致率：0"
            } else {
                return "一致率：" + prams[0].data + "%"
            }
        }
    },
    legend: {
        data: ['一致率', '背景'],
        show: false
    },
    grid: {
        left: '0%',
        right: '5%',
        bottom: '2%',
        top: '12%',
        height: '85%',
        containLabel: true,
        z: 22
    },
    xAxis: [{
        // 类型
        type: 'category',
        gridIndex: 0,
        data: [],
        axisTick: {
            alignWithLabel: true
        },
        // 轴线颜色
        axisLine: {
            lineStyle: {
                color: '#495499'
            }
        },
        // 刻度名称颜色
        axisLabel: {
            show: true,
            color: '#8C91B8',
            fontSize: 13,
            interval: 0
        },
        // X轴名称距离轴线距离
        offset: 10
    }],
    yAxis: [{
        // Y轴左上角名称
        name: '单位：%',
        nameTextStyle:{
            color: '#8C91B8',
            align: 'left'
        },
        type: 'value',
        gridIndex: 0,
        splitLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        // min: min,
        // max: 400,
        axisLine: {
            lineStyle: {
                color: '#495499'
            }
        },
        axisLabel: {
            color: '#8C91B8',
            fontSize: 12,
            formatter: '{value}'
        }
    }, {
        type: 'value',
        gridIndex: 0,
        min: min,
        max: 100,
        splitNumber: 12,
        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            show: false
        },
        splitArea: {
            show: true,
            areaStyle: {
                color: ['rgba(250,250,250,0.0)', 'rgba(250,250,250,0.05)']
            }
        }
    }],
    series: [{
        name: '一致率',
        type: 'bar',
        barWidth: '20px',
        xAxisIndex: 0,
        yAxisIndex: 0,
        itemStyle: {
            normal: {
                barBorderRadius: [30, 30, 0, 0],
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1, [{
                        offset: 0,
                        color: '#00feff'
                    }, {
                        offset: 0.5,
                        color: '#027eff'
                    }, {
                        offset: 1,
                        color: '#0286ff'
                    }]
                )
            }
        },
        data: [],
        zlevel: 11,
        label: {
            normal: {
                show: true,
                lineHeight: 26,
                width: 70,
                height: 23,
                backgroundColor: 'rgba(32,255,255,0.2)',
                borderRadius: 200,
                position: ['-8', '-36'],
                distance: 5,
                formatter: [
                    '   {d|◈}',
                    ' {a|{c}}  \n',
                    '    {b|}'
                ].join(','),
                rich: {
                    d: {
                        color: '#F1FF11',
                        fontSize: '16',
                        align: 'left',
                        textShadowColor: '#F1FF11',
                        textShadowBlur: '8'
                    },
                    a: {
                        color: '#fff',
                        align: 'right',
                        fontSize: '14',
                    },
                    b: {
                        width: 2,
                        height: 10,
                        borderWidth: 2,
                        borderColor: 'rgba(86,146,255,0.3)',
                        align: 'left',
                        verticalAlign: 'top'
                    },
                }
            }
        },
        //平均值的配置
        markLine: {
            type: 'line',
            data: [{
                type: 'average',
                name: '平均值',
                lineStyle: {
                    color: '#F1FF11'
                }
            }, ]
        }

    }, {
        name: '背景',
        type: 'bar',
        barWidth: '30px',
        xAxisIndex: 0,
        yAxisIndex: 1,
        barGap: '-128%',
        data: [100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100],
        itemStyle: {
            normal: {
                color: 'rgba(86,146,255,0.1)'
            }
        },
        zlevel: 9
    },

    ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

function ajax_post(url,data) {
    myChart.showLoading({
        text : '正在加载数据'
    });
    $.ajax({
        type : "post",
        url : url,
        data : data,
        dataType : "json",
        success : function(result) {
            myChart.hideLoading();
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result.code=='200') {
                if (result.data != null && result.data.length!=null) {
                    xData = [];
                    yData = [];
                    for (var i = 0; i < result.data.length; i++) {
                        xData.push(result.data[i].qxdm.length>5? result.data[i].qxdm.substring(0,5)+'...':result.data[i].qxdm);
                        yData.push(result.data[i].counts);
                    }
                    //console.log(xData);
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: xData
                        },
                        series: [{
                            data: yData
                        }]
                    });
                }

            }else {
                myChart.hideLoading();
                alert("网络错误，请重试！！！");
            }

        },
        error : function(errorMsg) {
            myChart.hideLoading();
            //请求失败时执行该函数
            alert("图表请求数据失败!");
        }
    });
}
//点击事件
myChart.on('click', function(params) {
    //console.log(params);
    //var name = parseInt(params.name);
    var sj = $(".time_ul .timeborder.active").attr("label");
    window.location.href=ctxPath+'hjyzrate?sj='+sj;
});
