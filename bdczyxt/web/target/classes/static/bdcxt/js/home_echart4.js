
option = {
    tooltip: {
        trigger: 'item',
        alwaysShowContent:false,
        formatter: "{b}<br/>{d}%"
    },
    grid: {
        left: '3%',    
    },
    legend: {
        orient:'vertical',
        top:20,
        right:20,
        width:100,
        height:180,
        itemGap:15,
        itemWidth:15,
        itemHeight:15,
        icon:'rect',
		data:['新罗区','漳平县','长汀县','永定县','连城县','武平县','上杭县']
    },
    color:['#60C9B7','#B9C88A','#FAC16D','#FF9A6A','#FF6870','#F05087','#A751B0'],
	series: [
		{
			type: 'pie',
			hoverAnimation:true,
			clockwise:false,
			radius: ['60%', '85%'],
			center: ['40%', '50%'],
			label:{
				normal:{
					show:false,
					position:'outside',
					color:'#616161',
					formatter:"{b}\n\n{d}%",
				},

			},
			labelLine :{
				normal:{
					show:true,
					lineStyle:{
						color:"#CDCDCD",
					}
				},
			},
			data:[
				{
					value:40,
					name:'新罗区',
					label:{
						normal:{
							show:true,
							color:'#616161',
						},
					},
					labelLine:{
						normal:{
							show:true,
							length:15,
							length2:25,
						},
						emphasis:{
//			           		show:true,
						},
					},
				},
				{
					value:20,
					name:'永定县',
					label:{
						normal:{
							show:true,
							color:'#616161',
						},

					},
					labelLine:{
						normal:{
							show:true,
							length:10,
							length2:25,
						}
					},
				},{
					value:20,
					name:'武平县',
					label:{
						normal:{
							show:true,
							color:'#616161',
						},

					},
					labelLine:{
						normal:{
							show:true,
							length:10,
							length2:25,
						}
					},
				},
				{
					value:12,
					name:'连城县',
				},
				{value:16, name:'上杭县'},
				{value:15, name:'漳平县'},
				{value:14, name:'长汀县'},

			]
		}
	]

};

var cwlzb4=echarts.init(document.getElementById('cwlzb4'));
cwlzb4.setOption(option);
