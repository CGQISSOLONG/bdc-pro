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
        data:[
	        {
	        	name:"2017年10月",
	        	textStyle:{
	        	 	color:'#616161',
	        	 	fontSize:12,
	        	},
//	        	icon:'image://img/Group 22.png',
	        },
	        {
	        	name:"2017年9月",
	        	textStyle:{
	        	 	color:'#616161',
	        	 	fontSize:12,
	        	},
//	        	icon:'image://img/Group 23.png',
	        },
	        {
	        	name:"2017年8月",
	        	textStyle:{
	        	 	color:'#616161',
	        	 	fontSize:12,
	        	},
//	        	icon:'image://img/Group 25.png',
	        }
		],
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: true,
        nameGap:20,
        axisLine: {
            lineStyle: {
                type: 'solid',
                color: '#616161',//左边线的颜色
                width:'1'//坐标线的宽度
            }
        },
        axisTick:{ 
			 show:false, 
			 alignWithLabel:true, 
        },
		data:['新罗区','漳平县','长汀县','永定县','连城县','武平县','上杭县']
    },
    yAxis: {
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
        min:'50000',
        max:'150000',
        interval:20000,
    },
    series: [
        {
            name:'2017年10月',
            type:'line',
            symbol: 'circle',
            symbolSize:8,
            data:[150000, 130000, 128000, 108000, 113000, 139000,129000],
        	itemStyle: {
		        normal: {	        	
		            color: "#F09048",
		            borderColor:"#DB762B",
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
	                color: "#EA7A7A"// 线条颜色
	            }
	        },
	    },
        {
            name:'2017年9月',
            type:'line',
            symbol: 'circle',
            symbolSize:8,
            data:[112000, 80000, 101000, 90000, 92000, 110000, 130000],
       		itemStyle: {
		        normal: {
		            color: "#50BEF1",
		            borderColor:"#178EC5",
		        }
   			},
   			lineStyle: {
	            normal: {
	            	type:"dashed",
	                color: "#5BC6F9"// 线条颜色
	            }
	       }
        },
        {
            name:'2017年8月',
            type:'line',
            symbol: 'circle',
            symbolSize:8,
          
            data:[89000, 93000, 90000, 68000, 71000, 89000,90000],
       		itemStyle: {
		        normal: {
		            color: "#4CE7A0",
		            borderColor:"#3FA577",
		        }
   			},
   			lineStyle: {
	            normal: {
	            	type:"dashed",
	                color: "#4CE7A0"// 线条颜色
	            }
	       }
        }
    ],
   
};


var sbslfx3=echarts.init(document.getElementById('sbslfx3'));
sbslfx3.setOption(option8);
