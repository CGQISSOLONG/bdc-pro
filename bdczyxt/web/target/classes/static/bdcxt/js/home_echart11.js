option8 = {
    title: {
        text: ''
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['2019年10月','2019年9月','2019年8月'],
        right:'10px'

    },
    color:['#F19149','#5BC6F9','#4CE7A0'],
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['新罗区','漳平县','长汀县','永定县','连城县','武平县','上杭县']
    },
    yAxis: {
        type: 'value',
        min:'50000',
        max:'150000',
        splitNumber:'6'
    },
    series: [
        {
            name:'2017年10月',
            type:'line',
            data:[150000, 130000, 128000, 108000,129500,110000,80000]
        },
        {
            name:'2017年9月',
            type:'line',
            data:[112000, 80000, 101000,111000,90000,70000,91000]
        },
        {
            name:'2017年8月',
            type:'line',
            data:[89000, 93000, 90000, 68000,70000,80000,88000]
        }
    ],
    itemStyle:{
    	normal:{
    		boderType:'dashed'
    	}
    },
   
};

var sbslfx=echarts.init(document.getElementById('sbslfx'));
sbslfx.setOption(option8);