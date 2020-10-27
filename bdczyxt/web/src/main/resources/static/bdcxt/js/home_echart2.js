option = {
    tooltip: {
        trigger: 'item',
        alwaysShowContent: false,
        formatter: "{b}<br/>{d}%"
    },
    grid: {
        left: '3%',
    },
    legend: {
        orient: 'vertical',
        top: 20,
        right: 20,
        width: 100,
        height: 180,
        itemGap: 15,
        itemWidth: 15,
        itemHeight: 15,
        icon: 'rect',
        data: []
    },
    color: ['#60C9B7','#FF9A6A','#5561D8','#A751B0','#FF6870','#FAC16D','#2360D7'],
    series: [
        {
            type: 'pie',
            hoverAnimation: true,
            clockwise: false,
            radius: ['60%', '85%'],
            center: ['40%', '50%'],
            label: {
                normal: {
                    show: false,
                    position: 'outside',
                    color: '#616161',
                    formatter: "{b}\n\n{d}%",
                },

            },
            labelLine: {
                normal: {
                    show: true,
                    lineStyle: {
                        color: "#CDCDCD",
                    }
                },
            },
            data: [
                {
                    label: {
                        normal: {
                            show: true,
                            color: '#616161',
                        },
                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 15,
                            length2: 25,
                        },
                        emphasis: {
//			           		show:true,
                        },
                    },
                },
                {

                    label: {
                        normal: {
                            show: true,
                            color: '#616161',
                        },

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                },{

                    label: {
                        normal: {
                            show: true,
                            color: '#616161'
                        }

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                },
                {

                    label: {
                        normal: {
                            show: true,
                            color: '#616161'
                        }

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                },
                {

                    label: {
                        normal: {
                            show: true,
                            color: '#616161'
                        }

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                },
                {

                    label: {
                        normal: {
                            show: true,
                            color: '#616161'
                        }

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                },
                {

                    label: {
                        normal: {
                            show: true,
                            color: '#616161'
                        }

                    },
                    labelLine: {
                        normal: {
                            show: true,
                            length: 10,
                            length2: 25
                        }
                    }
                }

            ]
        }
    ]


};


var cwlzb = echarts.init(document.getElementById('cwlzb'));
cwlzb.setOption(option);

function setdata(data,legend) {

    cwlzb.setOption({
        series: [
            {
                data:data
            },
        ],
        legend:{
            data:legend
        }

    })
}