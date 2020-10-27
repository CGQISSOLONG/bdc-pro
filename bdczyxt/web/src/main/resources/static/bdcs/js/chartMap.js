// 各地区办件统计--地图
var myChart = echarts.init(document.getElementById('chartMap'));
//var uploadedDataURL = "https://geo.datav.aliyun.com/areas/bound/350800_full.json";
var uploadedDataURL = "ly/js/json.json";
// myChart.showLoading();
var data = [];

// myChart.showLoading();
//myChart.hideLoading();
var geoCoordMap = {
    '新罗区': [117.000,25.1518],
    '长汀县': [116.261007,25.682278],
    '漳平市': [117.42073,25.381597],
    '连城县': [116.816687,25.608506],
    '上杭县': [113.462158,25.050153],
    '武平县': [116.000928,25.08865],
    '永定区': [116.702691,24.720442]
}

var max = 200, min = 9; // todo
var maxSize4Pin = 48, minSize4Pin = 8;
var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};
$.getJSON(uploadedDataURL, function(geoJson) {
    echarts.registerMap('fujian', geoJson);
    var option = {
        // 标题信息
        title: {
            text: '',
            subtext: '',
            x: 'center',
            textStyle: {
                color: '#ccc'
            }
        },
        // 
        grid: {
            left: '0',
            top: '0',
            right: '0',
            bottom: '0',
            width: 'auto',
            height: 'auto'
        },
        // 提示框组件
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                if (typeof(params.value)[2] == "undefined") {
                    return params.name + params.seriesName+' : ' + params.value;
                } else {
                    return params.name +  params.seriesName+' : ' + params.value[2];
                }
            }
        },
        // 图例组件。
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x: 'right',
            data: ['credit'],
            textStyle: {
                color: '#fff'
            }
        },
        // 地理坐标系组件
        geo: {
            show: true,
            map: 'fujian',
            label: {
                normal: {
                    show: true,
                    color: '#1FEBF2', //标志颜色
                },
                emphasis: {
                    show: true,
                    color: '#1FEBF2', //标志颜色
                }
            },
            itemStyle: {
                normal: {
                    areaColor: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(31,239,242,0.3)'
                        },
                            {
                                offset: 1,
                                color: 'rgba(20,156,217,0)'
                            }
                        ]
                    ),
                    borderColor: '#20FFFF',
                    borderWidth: 1,
                    shadowColor: 'rgba(0,0,0,1)',
                    shadowBlur: 32,
                    label: {
                        show: true,
                        color: '#fff'
                    }
                },
                emphasis: {
                    areaColor: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(32,255,255,0.7)'
                        },
                            {
                                offset: 1,
                                color: 'rgba(46,131,253,0.7)'
                            }
                        ]
                    )
                }
            }
        },
        // 系列列表。每个系列通过 type 决定自己的图表类型
        series : [
            {
                name: '',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#05C3F9'
                    }
                }
            },
            {
                type: 'map',
                map: 'longyan',
                geoIndex: 0,
                aspectScale: 0.75, //长宽比
                showLegendSymbol:false,
                left: '10%',
                top: 10,
                width: '50%',
                height: '100%',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        areaColor: '#031525',
                        borderColor: '#3B5077'
                    },
                    emphasis: {
                        areaColor: '#2B91B7'
                    }
                },
                animation: true,
                data: data
            },
            {
                name: 'Top 7',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 7)),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                itemStyle: {
                    normal: {
                        color: '#1FEBF2',
                        shadowBlur: 10,
                        shadowColor: '#1FEBF2'
                    }
                },
                zlevel: 1
            }
        ]
    };
    myChart.setOption(option);
});

// 各地区办件排行榜--柱状图
var chartRank = echarts.init(document.getElementById('chartRank'));
function init(id) {
    chartRank = echarts.init(document.getElementById(id));
    chartRank.setOption(option);
}

//数据纯属虚构
var yData = [];
var barData = [];
var option = {
    title: [{
        show: false,
    }],
    grid: {
        left: '30%',
        right: '20%',
        top: '0',
    },
    tooltip: {
        show: true,
        formatter: function(params) {
            return params.name + '办件量：' + params.data['value']
        },
    },
    xAxis: {
        show: false
    },
    yAxis: {
        type: 'category',
        inverse: true,
        nameGap: 16,
        axisLine: {
            show: false,
            lineStyle: {
                color: '#ddd'
            }
        },
        axisTick: {
            show: false,
            lineStyle: {
                color: '#ddd'
            }
        },
        axisLabel: {
            interval: 0,
            margin: 85,
            textStyle: {
                color: '#fff',
                align: 'left',
                fontSize: 14
            },
            rich: {
                a: {
                    color: '#fff',
                    backgroundColor: '#F0515E',
                    width: 16,
                    height: 16,
                    align: 'center',
                    borderRadius: 2
                },
                b: {
                    color: '#fff',
                    backgroundColor: '#2E83FD',
                    width: 16,
                    height: 16,
                    align: 'center',
                    borderRadius: 2
                }
            },
            formatter: function(params) {
                if (parseInt(params.slice(0, 1)) < 3) {
                    return [
                        '{a|' + (parseInt(params.slice(0, 1)) + 1) + '}' + '  ' + params.slice(1)
                    ].join('\n')
                } else {
                    return [
                        '{b|' + (parseInt(params.slice(0, 1)) + 1) + '}' + '  ' + params.slice(1)
                    ].join('\n')
                }
            }
        },
        data: yData
    },
    series: [ {
        name: 'barSer',
        type: 'bar',
        roam: false,
        visualMap: false,
        zlevel: 2,
        barMaxWidth: 16,
        barGap: '-100%',

        label: {
            show:true,
            position: 'right',
            color: '#fff',
            fontsize: '14'
        },
        itemStyle: {
            normal: {
                color: function(params) {
                    // build a color map as your need.
                    var colorList = [{
                        colorStops: [{
                            offset: 0,
                            color: '#EF8455' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#FFB41F' // 100% 处的颜色
                        }]
                    },
                        {
                            colorStops: [{
                                offset: 0,
                                color: '#2E83FD' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#1FEEF2' // 100% 处的颜色
                            }]
                        }
                    ];
                    if (params.dataIndex < 3) {
                        return colorList[0]
                    } else {
                        return colorList[1]
                    }
                },
                barBorderRadius: [0, 8, 8, 0]
            }
        },
        data: barData
    }
    ]
};
chartRank.setOption(option);


function ajax_map_data_post(url, data_,zl,id) {
    init(id);
    myChart.showLoading({
        text : '正在加载数据'
    });
    $.ajax({
        type: "post",
        url: url,
        data: data_,
        dataType: "json",
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result.code == '200') {
                data = [];
                barData = [];
                yData=[];
                var type;
                if(zl==1){
                    type='办件量';
                }else if(zl==2){
                    type='证书量';
                }else{
                    type='证明量';
                }
                for (var i = 0; i < result.data.length; i++) {
                    data.push(result.data[i]);
                    barData.push(result.data[i]);
                    yData.push(i + result.data[i].name);
                }

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    series: [
                      /*  {
                            name: type,
                            type: 'scatter',
                            coordinateSystem: 'geo',
                            data: convertData(data),
                            symbolSize: function (val) {
                                return val[2] / 10;
                            },
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    position: 'right',
                                    show: true
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#05C3F9'
                                }
                            }
                        },*/
                        {
                            name:type,
                            type: 'map',
                            map: 'longyan',
                            geoIndex: 0,
                            aspectScale: 0.75, //长宽比
                            showLegendSymbol:false,
                            left: '10%',
                            top: 10,
                            width: '50%',
                            height: '100%',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    areaColor: '#031525',
                                    borderColor: '#3B5077'
                                },
                                emphasis: {
                                    areaColor: '#2B91B7'
                                }
                            },
                            animation: true,
                            data: data
                        },
                        /*{
                            name: type,
                            type: 'effectScatter',
                            coordinateSystem: 'geo',
                            data: convertData(data.sort(function (a, b) {
                                return b.value - a.value;
                            }).slice(0, 7)),
                            symbolSize: function (val) {
                                return val[2] / 10;
                            },
                            showEffectOn: 'render',
                            rippleEffect: {
                                brushType: 'stroke'
                            },
                            hoverAnimation: true,
                            itemStyle: {
                                normal: {
                                    color: '#1FEBF2',
                                    shadowBlur: 10,
                                    shadowColor: '#1FEBF2'
                                }
                            },
                            zlevel: 1
                        }*/
                    ]
                });

                chartRank.hideLoading();    //隐藏加载动画
                chartRank.setOption({
                    yAxis: {
                        data: yData
                    },
                    //加载数据图表
                    series: [{
                        // 根据名字对应到相应的系列
                        name: type,
                        data: barData
                    }]
                });


            }else{
                chartRank.hideLoading();    //隐藏加载动画
                myChart.hideLoading();
                alert("网络错误，请重试！！！");
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
            chartRank.hideLoading();    //隐藏加载动画
        }
    });
}