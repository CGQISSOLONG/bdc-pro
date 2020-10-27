var myChart = echarts.init(document.getElementById('HistChart')); // 指定图表的配置项和数据
/*var posList = [
    'left', 'right', 'top', 'bottom',
    'inside',
    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
];

app.configParameters = {
    rotate: {
        min: -90,
        max: 90
    },
    align: {
        options: {
            left: 'left',
            center: 'center',
            right: 'right'
        }
    },
    verticalAlign: {
        options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
        }
    },
    position: {
        options: echarts.util.reduce(posList, function (map, pos) {
            map[pos] = pos;
            return map;
        }, {})
    },
    distance: {
        min: 0,
        max: 100
    }
};

app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
        var labelOption = {
            normal: {
                rotate: app.config.rotate,
                align: app.config.align,
                verticalAlign: app.config.verticalAlign,
                position: app.config.position,
                distance: app.config.distance
            }
        };
        myChart.setOption({
            series: [{
                label: labelOption
            }, {
                label: labelOption
            }, {
                label: labelOption
            }, {
                label: labelOption
            }]
        });
    }
};*/

var xData = [];
var zsData = [];
var stData = [];
var labelOption = {
    normal: {
        show: true,
        /*position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,*/
        rotate: 90,
        formatter: '{c}  {name|{a}}',
        fontSize: 16,
        rich: {
            name: {
                textBorderColor: '#fff'
            }
        }
    }
};

option = {
    color: ['#3398DB', '#006699'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['省厅', '臻善'],
        textStyle: {
            //图例文字的样式
            color: ['#3398DB', '#006699'],
            fontSize: 16
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: true,
    grid: {
        left: '0%',
        right: '3%',
        bottom: '2%',
        top: '12%',
        height: '85%',
        containLabel: true,
        z: 22
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {show: false},
            data: xData,
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
            offset: 5
        }
    ],
    yAxis: [
        {
            type: 'value',
            // Y轴左上角名称
            name: '单位：天',
            nameTextStyle: {
                color: '#8C91B8',
                align: 'left'
            },
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
            min: 0,
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
        }
    ],
    series: [
        {
            name: '省厅',
            type: 'bar',
            barGap: 0,
            barMaxWidth: 30,//最大宽度
            label: labelOption,
            data: stData
        },
        {
            name: '臻善',
            type: 'bar',
            barMaxWidth: 30,//最大宽度
            label: labelOption,
            data: zsData
        }
    ]
};
myChart.setOption(option);

function ajax_post(url, data) {
    myChart.showLoading({
        text: '正在加载数据'
    });
    $.ajax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            myChart.hideLoading();    //隐藏加载动画
            if (result.code == '200') {
                if (result.data != null && result.data.length != null) {
                    xData = [];
                    yData = [];
                    for (var i = 0; i < result.data.length; i++) {
                        xData.push(result.data[i].QXDM.length > 5 ? result.data[i].QXDM.substring(0, 5) + '...' : result.data[i].QXDM);
                        zsData.push(result.data[i].COUNTS);
                        stData.push(result.data[i].ORIGINALVALUE);
                    }

                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: xData
                        },
                        series: [{
                            name: '省厅',
                            data: stData
                        },
                            {
                                name: '臻善',
                                data: zsData
                            }
                        ]
                    });
                }

            } else {
                //myChart.hideLoading();
                alert("网络错误，请重试！！！");
            }

        },
        error: function (errorMsg) {
            myChart.hideLoading();
            //请求失败时执行该函数
            alert("图表请求数据失败!");
        }
    });
}

//点击事
myChart.on('click', function (params) {
    //console.log(params);
    //var name = parseInt(params.name);
    var sj = $(".time_ul .timeborder.active").attr("label");
    window.location.href = ctxPath + 'averageTime?sj=' + sj;
});