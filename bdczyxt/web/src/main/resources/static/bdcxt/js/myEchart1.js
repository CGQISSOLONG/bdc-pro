option1 = {
    title: {
        text: '配号量',
        "x": '50%',
        "y": '45%',
        textAlign: "center",
        "textStyle": {
            "fontWeight": 'normal',
            "fontSize": 16,
            "color":"#00AEE9",
        },
        "subtextStyle": {
            "fontWeight": 'bold',
            "fontSize":22,
            "color": '#FEA36B'
        }
    },
    series: [ 
        {
            "name": ' ',
            "type": 'pie',
            "radius": ['85%', '95%'],
            "avoidLabelOverlap": false,
            "startAngle":225,
            "color": ["#0BBFFF", "transparent"],
            "hoverAnimation": false,
            "legendHoverLink": false,
            "label": {
                "normal": {
                    "show": false,
                    "position": 'center'
                },
                "emphasis": {
                    "show": true,
                    "textStyle": {
                        "fontSize": '12',
                        "fontWeight": 'bold'
                    }
                }
            },
            "labelLine": {
                "normal": {
                    "show": false
                }
            },
            "data": [{
                "value": 75,
                "name": '1'
            }, {
                "value": 25,
                "name": '2'
            }]
        }, 
      {
            "name": '',
            "type": 'pie',
            "radius": ['87%', '93%'],
            "avoidLabelOverlap": false,
            "startAngle": 317,
            "color": ["#fff", "transparent"],
            "hoverAnimation": false,
            "legendHoverLink": false,
            "clockwise": false,
            "itemStyle":{
                "normal":{
                    "borderColor":"transparent",
                    "borderWidth":"10"
                },
                "emphasis":{
                    "borderColor":"transparent",
                    "borderWidth":"10"
                }
            }
            ,
            "z":10,
            "label": {
                "normal": {
                    "show": false,
                    "position": 'center'
                },
                "emphasis": {
                    "show": true,
                    "textStyle": {
                        "fontSize": '12',
                        "fontWeight": 'bold'
                    }
                }
            },
            "labelLine": {
                "normal": {
                    "show": false
                }
            },
            "data": [{
//                 "value": (100 - value1) * 266 / 360,
                "name": ''
            }, {
                // "value": 100 - (100 - value1) * 266 / 360,
                "name": ''
            }
            ]
        }

    ]
};
option2= {

    title: {
        text: '配号量',
        "x": '50%',
        "y": '45%',
        textAlign: "center",
        "textStyle": {
            "fontWeight": 'normal',
            "fontSize": 16,
            color:"#00AEE9",
        },
        "subtextStyle": {
            "fontWeight": 'bold',
            "fontSize":22,
            "color": '#FEA36B'
        }
    },
    series: [ 
        {
            "name": ' ',
            "type": 'pie',
            "radius": ['85%', '95%'],
            "avoidLabelOverlap": false,
            "startAngle":225,
            "color": ["#0BBFFF", "transparent"],
            "hoverAnimation": false,
            "legendHoverLink": false,
            "label": {
                "normal": {
                    "show": false,
                    "position": 'center'
                },
                "emphasis": {
                    "show": true,
                    "textStyle": {
                        "fontSize": '18',
                        "fontWeight": 'bold'
                    }
                }
            },
            "labelLine": {
                "normal": {
                    "show": false
                }
            },
            "data": [{
                "value": 75,
                "name": '1'
            }, {
                "value": 25,
                "name": '2'
            }]
        }, 
      {
            "name": '',
            "type": 'pie',
            "radius": ['87%', '93%'],
            "avoidLabelOverlap": false,
            "startAngle": 317,
            "color": ["#fff", "transparent"],
            "hoverAnimation": false,
            "legendHoverLink": false,
            "clockwise": false,
            "itemStyle":{
                "normal":{
                    "borderColor":"transparent",
                    "borderWidth":"10"
                },
                "emphasis":{
                    "borderColor":"transparent",
                    "borderWidth":"10"
                }
            }
            ,
            "z":10,
            "label": {
                "normal": {
                    "show": false,
                    "position": 'center'
                },
                "emphasis": {
                    "show": true,
                    "textStyle": {
                        "fontSize": '18',
                        "fontWeight": 'bold'
                    }
                }
            },
            "labelLine": {
                "normal": {
                    "show": false
                }
            },
            "data": [{
//                 "value": (100 - value1) * 266 / 360,
                "name": ''
            }, {
                // "value": 100 - (100 - value1) * 266 / 360,
                "name": ''
            }
            ]
        }

    ]
};               
                    
var ringEcharts1=echarts.init(document.getElementById('ringEcharts1'));
var ringEcharts2=echarts.init(document.getElementById('ringEcharts2'));
ringEcharts1.setOption(option1);  
ringEcharts2.setOption(option2);  
ringEcharts1.timeTicket = setInterval(function() {
    var value = parseInt(Math.random() * 55) + 30,
    value_ = (100 - value) * 266 / 360;
	option1.title.subtext = value + "%";
	option1.series[1].data[0].value = value_;
	option1.series[1].data[1].value = 100 - value_;
	ringEcharts1.setOption(option1, true);
	option1.title.subtextStyle.fontSize+=6;
//	option1.title.subtextStyle.textShadow="-0 -0 0.2em #f87";
//  option1.title.subtextStyle.Animation='neon2 1.5s ease-in-out infinite alternate';
    option1.title.subtextStyle.fontColor='#ff0000'
	if(option1.title.subtextStyle.fontSize==34){
		option1.title.subtextStyle.fontSize=22;
	}
}, 1000);

ringEcharts2.timeTicket = setInterval(function() {
    var value = parseInt(Math.random() * 55) + 30,
    value_ = (100 - value) * 266 / 360;
	option2.title.subtext = value + "%";
	option2.series[1].data[0].value = value_;
	option2.series[1].data[1].value = 100 - value_;
	ringEcharts2.setOption(option2, true);
	option2.title.subtextStyle.fontSize+=6;
	if(option2.title.subtextStyle.fontSize==34){
		option2.title.subtextStyle.fontSize=22;
	}
}, 1000); 