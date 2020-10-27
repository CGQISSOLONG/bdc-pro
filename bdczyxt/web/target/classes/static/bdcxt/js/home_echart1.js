option8 = {
    title: {
        text: ''
    },
    tooltip: {
        trigger: 'axis'

    },
    legend: {
        right:'10px',
        itemGap:15,
        itemWidth:35,
    },
    grid: {
         left: '1%',
        right: '4%',
        bottom: '12%',
        containLabel: true,
        width: "auto"
    },
    xAxis: {
        type: 'category',
        boundaryGap: true,
        nameGap:20,

            axisLine: {
                lineStyle: {
                    type: 'solid',
                   // color: '#616161',//左边线的颜色
                    width:'1'//坐标线的宽度
            }
        },
        axisLabel: {
            interval:0,
            rotate:40,
            textStyle: {
               // color: 'red'//坐标值得具体的颜色
            }
        },
        axisTick:{
            show:false,
            alignWithLabel:true,
        },
           // data: []
        data: []
    },
    yAxis:
            {
        type: 'value',
        nameGap:20,
        axisLine:{
            show:false,
            lineStyle: {
                color: '#616161',
            }
        },
        axisTick :{
            show:false
        },
//      axisLabel:{
//			formatter : '{value}%'
//      },
        //interval:2000000,
        //interval: "auto"

    },
     series:[
    /*{
        type:'line',
        symbol: 'circle',
        symbolSize:0,
         itemStyle: {
            normal: {
                color: "#60C9B7",
                borderColor:"#60C9B7",
                label:{
                    show: true,
                    distance:10,
                    padding :3,
                    borderRadius:4,
                    backgroundColor:"#ECECEC",
                    color:'#1776AC',
                },
            }
        },
        lineStyle: {
            normal: {
                type:"dashed",
                color: "#FFFFFF"// 线条颜色
            }
        },
    },*/

        {
            name:'龙岩市',
            type:'line',
            symbol: 'circle',
            symbolSize:8,
            data:[],
            itemStyle: {
                normal: {
                    color: "#73BF00",
                    borderColor:"#D94600"
                }
            },
            lineStyle: {
                normal: {
                    type:"dashed",
                    color: "#977C00"// 线条颜色
                }
            }
        },]
};

var sbslfx=echarts.init(document.getElementById('sbslfx'));
sbslfx.setOption(option8);

function setechart(interval,getMin,getMax,series,xAxis) {
    sbslfx.setOption({
        series:{
            data:series
        },
        xAxis:{
            data:xAxis,
        },
        yAxis:{
            max:getMax,
            min:getMin,
            interval:interval
       }


    });
}