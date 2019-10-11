//初始化方法
$(function () {

    // 初始化教职工前五名
    getWorkerTopFiveInit("2018-01-01", "2018-12-31");
    // 初始化男生前三名
    getBoyTopThreeInit("2018-01-01", "2018-12-31");
    // 初始化女生前三名
    getGirlTopThreeInit("2018-01-01", "2018-12-31");
    // 初始化全馆前十名
    getAllBookTopTenInit("2018-01-01", "2018-12-31");
    // 初始化各学院前五名
    getDptmtTopFiveInit("2018-01-01", "2018-12-31", "水利学院");

})

function getWorkerTopFiveInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"教职工"
    };
    $.ajax({
        url: "/bookWorth/getDepartmentTopFive", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getWorkerTopFive'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '教职工前五名',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#D1D1D1'],
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


                var flag = 4;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].bookName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetWorkerTopFive() {
    var beginDate = $("#inpstart1").val();
    var endDate = $("#inpend1").val();
    getWorkerTopFiveInit(beginDate,endDate);
}


function getBoyTopThreeInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"男"
    };
    $.ajax({
        url: "/bookWorth/getDepartmentTopFive", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getBoyTopThree'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '男生前三名',
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

                var flag = 2;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].bookName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetBoyTopThree() {
    var beginDate = $("#inpstart2").val();
    var endDate = $("#inpend2").val();
    getBoyTopThreeInit(beginDate,endDate);
}


function getGirlTopThreeInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"女"
    };
    $.ajax({
        url: "/bookWorth/getDepartmentTopFive", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getGirlTopThree'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '女生前三名',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#EEC900'],
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

                var flag = 2;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].bookName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetGirlTopThree() {
    var beginDate = $("#inpstart3").val();
    var endDate = $("#inpend3").val();
    getGirlTopThreeInit(beginDate,endDate);
}



function getAllBookTopTenInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate
    };
    $.ajax({
        url: "/bookWorth/getAllBookTopTen", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 全馆前十名
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getAllBookTopTen'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '全馆前十名',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#FFE4B5'],
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

                var flag = 9;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].bookName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetAllBookTopTen() {
    var beginDate = $("#inpstart4").val();
    var endDate = $("#inpend4").val();
    getAllBookTopTenInit(beginDate,endDate);
}

function getDptmtTopFiveInit(beginDate,endDate,dptmt) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt": dptmt
    };
    $.ajax({
        url: "/bookWorth/getDepartmentTopFive", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 全馆前十名
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtTopFive'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '各学院最受欢迎前五名',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#CD8162'],
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

                var flag = 4;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].bookName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtTopFive() {
    var beginDate = $("#inpstart5").val();
    var endDate = $("#inpend5").val();
    var dptmt = $("#mySelect").val();
    getDptmtTopFiveInit(beginDate,endDate,dptmt);
}
