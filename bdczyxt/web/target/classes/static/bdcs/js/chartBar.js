// 百度echart图表
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('HistChart')); // 指定图表的配置项和数据
var data = [{
	"name": "2019.01",
	"value": 21
}, {
	"name": "2019.02",
	"value": 56
}, {
	"name": "2019.03",
	"value": 36
}, {
	"name": "2019.04",
	"value": 142
}, {
	"name": "2019.05",
	"value": 53
}, {
	"name": "2019.06",
	"value": 53
}, {
	"name": "2019.07",
	"value": 126
}, {
	"name": "2019.08",
	"value": 36
}, {
	"name": "2019.09",
	"value": 142
}, {
	"name": "2019.10",
	"value": 53
}, {
	"name": "2019.11",
	"value": 53
}, {
	"name": "2019.12",
	"value": 86
}];
var xData = [],
	yData = [];
var min = 50;
data.map(function(a, b) {
	xData.push(a.name);
	if (a.value === 0) {
		yData.push(a.value + min);
	} else {
		yData.push(a.value);
	}
});
option = {
	backgroundColor: "transparent",
	color: ['#3398DB'],
	tooltip: {
		trigger: 'axis',
		axisPointer: {
			type: 'line',
			lineStyle: {
				opacity: 0
			}
		},
		formatter: function(prams) {
			if (prams[0].data === min) {
				return "办件量：0"
			} else {
				return "办件量：" + prams[0].data + "件"
			}
		}
	},
	legend: {
		data: ['办件量', '背景'],
		show: false
	},
	grid: {
		left: '0%',
		right: '3%',
		bottom: '2%',
		top: '15%',
		height: '85%',
		containLabel: true,
		z: 22
	},
	xAxis: [{
		// 类型
		type: 'category',
		gridIndex: 0,
		data: xData,
		axisTick: {
			alignWithLabel: true
		},
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
			fontSize: 13
		},
		// X轴名称距离轴线距离
		offset: 5,
	}],
	yAxis: [{
		// Y轴左上角名称
        name: '单位：件',
        nameTextStyle:{
          color: '#8C91B8',
          align: 'left'
        },
		type: 'value',
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
		min: min,
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
	}],
	series: [{
			name: '办件量',
			type: 'bar',
			barWidth: '20px',
			xAxisIndex: 0,
			yAxisIndex: 0,
			itemStyle: {
				normal: {
					barBorderRadius: [30, 30, 0, 0],
					color: new echarts.graphic.LinearGradient(
						0, 0, 0, 1, [{
							offset: 0,
							color: '#00feff'
						}, {
							offset: 0.5,
							color: '#027eff'
						}, {
							offset: 1,
							color: '#0286ff'
						}]
					)
				}
			},
			data: yData,
			zlevel: 11,
			label: {
				normal: {
					show: true,
					lineHeight: 26,
					width: 70,
					height: 26,
					backgroundColor: 'rgba(32,255,255,0.2)',
					borderRadius: 200,
					position: ['-8', '-36'],
					distance: 5,
					formatter: [
						'   {d|◈}',
						' {a|{c}}  \n',
						'    {b|}'
					].join(','),
					rich: {
						d: {
							color: '#F1FF11',
							fontSize: '16',
							align: 'left',
							textShadowColor: '#F1FF11',
							textShadowBlur: '8'
						},
						a: {
							color: '#fff',
							align: 'right',
							fontSize: '8',
						},
						b: {
							width: 2,
							height: 10,
							borderWidth: 2,
							borderColor: 'rgba(86,146,255,0.3)',
							align: 'left',
							verticalAlign: 'top'
						},
					}
				}
			},
			//平均值的配置
			markLine: {
				type: 'line',
				data: [{
					type: 'average',
					name: '平均值',
					lineStyle: {
						color: '#F1FF11'
					}
				}, ]　　　
			}

		}, {
			name: '背景',
			type: 'bar',
			barWidth: '30px',
			xAxisIndex: 0,
			yAxisIndex: 1,
			barGap: '-128%',
			data: [100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100],
			itemStyle: {
				normal: {
					color: 'rgba(86,146,255,0.1)'
				}
			},
			zlevel: 9
		},

	]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);