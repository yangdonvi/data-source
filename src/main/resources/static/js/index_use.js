//初始化方法
$(function () {
    var lentNum = new Vue({ //vue初始化
        el: "#total_lent_num",
        data: {
            total_lent_num: '',
            avg_lent_num: ''
        },
        methods: {
            lentNumInit: function (beginDate, endDate) {
                var json = {
                    "beginDate": beginDate,
                    "endDate": endDate
                }
                $.ajax({
                    url: "/baseData/getTotalLentNum", //请求地址
                    contentType: "application/json",
                    data: JSON.stringify(json),
                    type: "post", //请求类型
                    dataType: "json", //返回类型
                    async: false, //是否异步，默认为true
                    success: function (data) {
                        if (data.code == 200) {
                            lentNum.total_lent_num = data.data.totalOutBookNum;
                        }
                    }
                })
            },
            avgLentNumInit: function (beginDate, endDate) {
                var json = {
                    "beginDate": beginDate,
                    "endDate": endDate
                }
                $.ajax({
                    url: "/baseData/getAvgLentNum", //请求地址
                    contentType: "application/json",
                    data: JSON.stringify(json),
                    type: "post", //请求类型
                    dataType: "json", //返回类型
                    async: false, //是否异步，默认为true
                    success: function (data) {
                        if (data.code == 200) {
                            lentNum.avg_lent_num = data.data;
                        }
                    }
                })
            },
            toQueryLentNum: function () {
                var beginDate = $("#inpstart1").val();
                var endDate = $("#inpend1").val();
                lentNum.lentNumInit(beginDate, endDate);
                lentNum.avgLentNumInit(beginDate, endDate);
            }

        }
    });

    // 借阅总册数
    lentNum.lentNumInit("2018-01-01", "2018-12-31");
    // 人均借阅册数
    lentNum.avgLentNumInit("2018-01-01", "2018-12-31");
    // 初始化电子书部分
    getEBookInit();
    // 初始化各语种图书借阅册数
    getLanguageLentNumInit("2018-01-01", "2018-12-31");
    // 初始化各分馆外借册数
    getCampusLentNumInit("2018-01-01", "2018-12-31");
})

function getEBookInit() {
    $.ajax({
        url: "/baseData/getEBook", //请求地址
        contentType: "application/json",
        data: {},
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getEBook'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '电子书库访问量',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        right: 'right',
                        data: []
                    },
                    series: [{
                        name: '访问量',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }]
                };
                $.each(data.data, function (index, item) {
                    if (data.data[index].visitNum != null) {
                        option.legend.data.push(data.data[index].libName);
                        cell = {value: item.visitNum, name: item.libName};
                        option.series[0].data.push(cell);
                    }
                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

                // 电子书库下载量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart1 = echarts.init(document.getElementById('getEBook1'));

                // 指定图表的配置项和数据
                var option1 = {
                    title: {
                        text: '电子书库下载量',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        right: 'right',
                        data: []
                    },
                    series: [{
                        name: '下载量',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }]
                };
                $.each(data.data, function (index, item) {
                    if (data.data[index].downNum != null) {
                        option1.legend.data.push(data.data[index].libName);
                        cell = {value: item.downNum, name: item.libName};
                        option1.series[0].data.push(cell);
                    }
                });
                // 使用刚指定的配置项和数据显示图表。
                myChart1.setOption(option1);
            }
        }
    })
}


function getLanguageLentNumInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate
    };
    $.ajax({
        url: "/baseData/getLanguageLentNum", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getLanguageLentNum'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '各语种外借数量',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#cc6633'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'借出册数',
                            type:'bar',
                            barWidth: '60%',
                            data:[]
                        }
                    ]
                };



                $.each(data.data, function (index, item) {
                    option.xAxis[0].data.push(data.data[index].languageType);
                    option.series[0].data.push(data.data[index].languageNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetLanguageLentNum() {
    var beginDate = $("#inpstart2").val();
    var endDate = $("#inpend2").val();
    getLanguageLentNumInit(beginDate,endDate);
}

function getCampusLentNumInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate
    };
    $.ajax({
        url: "/baseData/getCampusLentNum", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getCampusLentNum'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '各分馆外借数量',
                        subtext: '2018年',
                        x: 'center'
                    },
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




                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[index].campusName);
                    option.series[0].data.push(data.data[index].campusOutBookNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetCampusLentNum() {
    var beginDate = $("#inpstart3").val();
    var endDate = $("#inpend3").val();
    getCampusLentNumInit(beginDate,endDate);
}
