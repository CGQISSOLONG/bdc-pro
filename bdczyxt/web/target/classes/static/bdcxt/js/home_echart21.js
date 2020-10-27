
option = {
    tooltip: {
        trigger: 'item',
        alwaysShowContent:false,
        formatter: "{b}<br/>{d}%"
    },
      grid: {
        left: '3%',    
        containLabel: true
    },
    legend: {
        orient: 'vertical',
        right:'4%',
        width:'100px',
        height:'160px',
        itemWidth:15,
        itemHeight:15,
        data:['新罗区','漳平县','长汀县','永定县','连城县','武平县','上杭县']
    },
    color:['#60C9B7','#FAC16D','#FF9A6A','#FF6870','#A751B0','#5561D8','#2360D7'],
    series: [
        {
            name: '访问来源',  
            type: 'pie',  
            radius: ['50%', '70%'], 
            center: ['40%', '60%'],
            itemStyle: {  
                emphasis: {  
                    shadowBlur: 10,  
                    shadowOffsetX: 0,  
                    shadowColor: 'rgba(0, 0, 0, 0.5)'  
                }  
            },
//          lable:{
//          	emphasis:{    
//                  show:true ,
//                  formatter: "{b}<br/>{d}%",
//                  color:'#ff0000'
//              } 
//          },
            labelLine:{    
                normal:{  
                	show: true, 
                	formatter: '{b}<br/>{d}%',  
                    length:10    
                } ,
                emphasis: {  
                    shadowBlur: 10,  
                    shadowOffsetX: 0,  
                    shadowColor: 'rgba(0, 0, 0, 0.5)'  
                } 
            }, 
            data:[
                {value:40, name:'新罗区'},
                {value:20, name:'漳平县'},
                {value:18, name:'永定县'},
                {value:17, name:'长汀县'},
                {value:16, name:'武平县'},
                {value:15, name:'连城县'},
                {value:14, name:'上杭县'},
            ]
        }  
    ]
    
    
};


var cwlzb=echarts.init(document.getElementById('cwlzb'));
cwlzb.setOption(option);
