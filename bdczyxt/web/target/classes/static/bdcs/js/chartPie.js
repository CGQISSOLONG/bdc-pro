// 各地区办件统计--饼状图
var chartPie = echarts.init(document.getElementById('pieChart'));
var legend_data = [];
var series_data = [];
option = {
    backgroundColor: "transparent",
    color: ["#2E83FD", "#20FFFF", "#FFC15B", "#FE7B96", "#9A7FDF", "#2CC26B", "#E9ECF0", "#656565", "#FF4545"],
    grid: {
        left: -100,
        top: 50,
        bottom: 10,
        right: 10,
        containLabel: true
    },
    tooltip: {
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)"
    },
    legend: {
        type: "scroll",
        orient: "vartical",
        // x: "right",
        top: "center",
        right: "15",
        // bottom: "0%",
        itemWidth: 16,
        itemHeight: 8,
        itemGap: 16,
        textStyle: {
            color: '#A3E2F4',
            fontSize: 12,
            fontWeight: 0
        },
        data: legend_data
    },
    polar: {},
    angleAxis: {
        interval: 1,
        type: 'category',
        data: [],
        z: 10,
        axisLine: {
            show: false,
            lineStyle: {
                color: "#0B4A6B",
                width: 1,
                type: "solid"
            },
        },
        axisLabel: {
            interval: 0,
            show: true,
            color: "#0B4A6B",
            margin: 8,
            fontSize: 16
        },
    },
    radiusAxis: {
        min: 40,
        max: 120,
        interval: 20,
        axisLine: {
            show: false,
            lineStyle: {
                color: "#0B3E5E",
                width: 1,
                type: "solid"
            },
        },
        axisLabel: {
            formatter: '{value} %',
            show: false,
            padding: [0, 0, 20, 0],
            color: "#0B3E5E",
            fontSize: 16
        },
        splitLine: {
            lineStyle: {
                color: "#0B3E5E",
                width: 2,
                type: "solid"
            }
        }
    },
    calculable: true,
    series: [
        {
            type: 'pie',
            radius: ["5%", "10%"],
            hoverAnimation: false,
            labelLine: {
                normal: {
                    show: false,
                    length: 30,
                    length2: 55
                },
                emphasis: {
                    show: false
                }
            },
            data: [{
                name: '',
                value: 0,
                itemStyle: {
                    normal: {
                        color: "#0B4A6B"
                    }
                }
            }]
        },
        {
            type: 'pie',
            radius: ["90%", "95%"],
            hoverAnimation: false,
            labelLine: {
                normal: {
                    show: false,
                    length: 30,
                    length2: 55
                },
                emphasis: {
                    show: false
                }
            },
            name: "",
            data: [{
                name: '',
                value: 0,
                itemStyle: {
                    normal: {
                        color: "#0B4A6B"
                    }
                }
            }]
        },
        {
            stack: 'a',
            type: 'pie',
            radius: ['20%', '80%'],
            roseType: 'area',
            zlevel: 10,
            label: {
                normal: {
                    show: true,
                    formatter: "{c}",
                    textStyle: {
                        fontSize: 12,
                    },
                    position: 'outside'
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    show: true,
                    length: 20,
                    length2: 55
                },
                emphasis: {
                    show: false
                }
            },
            data: series_data
        }
    ]
}

// 使用刚指定的配置项和数据显示图表。
chartPie.setOption(option);

function ajax_cakeshape_data_post(url, data) {
    chartPie.showLoading({
        text : '正在加载数据'
    });
    $.ajax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            //console.log(result);
            if (result.code=='200') {
                legend_data = [];
                series_data = [];
                for(var i=0;i<result.data.length;i++){
                    legend_data.push(result.data[i].name);//挨个取出类别并填入类别数组
                    series_data.push(result.data[i]);
                }
                chartPie.hideLoading();    //隐藏加载动画
                chartPie.setOption({        //加载数据图表
                    legend: {
                        data:legend_data
                    },
                    series:[{
                        stack: 'a',
                        type: 'pie',
                        radius: ['20%', '80%'],
                        roseType: 'area',
                        zlevel: 10,
                        label: {
                            normal: {
                                show: true,
                                formatter: "{c}",
                                textStyle: {
                                    fontSize: 12,
                                },
                                position: 'outside'
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        labelLine: {
                            normal: {
                                show: true,
                                length: 20,
                                length2: 55
                            },
                            emphasis: {
                                show: false
                            }
                        },
                        data: series_data
                    }]
                });

            }else {
                chartPie.hideLoading();
                alert("网络错误，请重试！！！");
            }

        },
        error: function (errorMsg) {
            chartPie.hideLoading();
            //请求失败时执行该函数
            alert("图表请求数据失败!");
        }
    });
}