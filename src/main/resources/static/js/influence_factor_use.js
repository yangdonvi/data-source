//初始化方法
$(function () {

    // 初始化各语种图书借阅册数
    getMonthNumInit("2018-01", "2018-12");
    // 初始化各分馆外借册数
    getGradeTopFiveInit("2018-01-01", "2018-12-31");
})


function getMonthNumInit(beginDate,endDate) {
    var json = {
        "begin": beginDate,
        "end": endDate
    };
    $.ajax({
        url: "/influenceFactor/getMonthNum", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getMonthNum'));

                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '各月借出总量',
                        subtext: '2018年',
                        x: 'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: [],
                        type: 'line'
                    }]
                };




                $.each(data.data, function (index, item) {
                    option.xAxis.data.push(data.data[index].month);
                    option.series[0].data.push(data.data[index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetMonthNum() {
    var beginDate = $("#inpstart1").val();
    var endDate = $("#inpend1").val();
    getMonthNumInit(beginDate,endDate);
}

function getGradeTopFiveInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate
    };
    $.ajax({
        url: "/influenceFactor/getGradeTopFive", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getGradeTopFive'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '各年级最受欢迎图书前五名',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#cc6633'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['2018年']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: []
                    },
                    series: [
                        {
                            type: 'bar',
                            data: []
                        }
                    ]
                };

                var flag = 19;
                $.each(data.data, function (index, item) {
                    index = flag-index;
                    if(data.data[index].grade == 0){
                        option.yAxis.data.push('（大一）'+data.data[index].bookName);
                    }
                    if(data.data[index].grade == 1){
                        option.yAxis.data.push('（大二）'+data.data[index].bookName);
                    }
                    if(data.data[index].grade == 2){
                        option.yAxis.data.push('（大三）'+data.data[index].bookName);
                    }
                    if(data.data[index].grade == 3){
                        option.yAxis.data.push('（大四）'+data.data[index].bookName);
                    }
                    option.series[0].data.push(data.data[index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetGradeTopFive() {
    var beginDate = $("#inpstart2").val();
    var endDate = $("#inpend2").val();
    getGradeTopFiveInit(beginDate,endDate);
}
