option = {
    backgroundColor: '#2c343c',

    title: {
        text: '全省土地占用比例',
        left: 'center',
        top:12,
        textStyle: {
            color: '#099EDA'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {            //颜色条形
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:335, name:'集体'},
                {value:310, name:'宅基地'},
                {value:274, name:'林权'},
                {value:235, name:'其他'},
                {value:400, name:'国有'},
                {value:300, name:'国有'}
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 4,
                    length2: 6
                }
            },
            itemStyle: {
                normal: {
                    color: '#099EDA',
                    shadowBlur:100,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};
                    
                    
var item3Top2=echarts.init(document.getElementById('item3-top2'));
item3Top2.setOption(option);                