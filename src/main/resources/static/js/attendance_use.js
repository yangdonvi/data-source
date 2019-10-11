//初始化方法
$(function () {
    var lentBigestReader = new Vue({ //vue初始化
        el: "#bigest_lent_reader",
        data: {
            reader_name: '',
            lent_num: ''
        },
        methods: {
            getLentNumBigestReaderInit: function (beginDate, endDate) {
                var json = {
                    "beginDate": beginDate,
                    "endDate": endDate
                }
                $.ajax({
                    url: "/attendance/getLentNumBigestReader", //请求地址
                    contentType: "application/json",
                    data: JSON.stringify(json),
                    type: "post", //请求类型
                    dataType: "json", //返回类型
                    async: false, //是否异步，默认为true
                    success: function (data) {
                        if (data.code == 200) {
                            lentBigestReader.reader_name = data.data.stuName;
                            lentBigestReader.lent_num = data.data.totalNum;
                        }
                    }
                })
            },
            toGetLentNumBigestReader: function () {
                var beginDate = $("#inpstart1").val();
                var endDate = $("#inpend1").val();
                lentBigestReader.getLentNumBigestReaderInit(beginDate, endDate);
            }

        }
    });


    lentBigestReader.getLentNumBigestReaderInit("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit1("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit2("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit3("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit4("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit5("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit6("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit7("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit8("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit9("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit10("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit11("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit12("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit13("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit14("2018-01-01", "2018-12-31");
    getDptmtAttendanceInit15("2018-01-01", "2018-12-31");

})

function getLentNumBigestReaderInit(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate
    };
    $.ajax({
        url: "/attendance/getLentNumBigestReader", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getLentNumBigestReader'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
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


                var flag = 4;
                $.each(data.data, function (index, item) {
                    /*option.yAxis.data.push(data.data[flag-index].stuName);
                    option.series[0].data.push(data.data[flag-index].totalNum);*/

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetLentNumBigestReader() {
    var beginDate = $("#inpstart1").val();
    var endDate = $("#inpend1").val();
    getLentNumBigestReaderInit(beginDate,endDate);
}


function getDptmtAttendanceInit1(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"全馆"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance1'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#D1D1D1'],
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


                var flag = 13;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].deptName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance1() {
    var beginDate = $("#inpstart2").val();
    var endDate = $("#inpend2").val();
    getDptmtAttendanceInit1(beginDate,endDate);
}


function getDptmtAttendanceInit2(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"水利学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance2'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#c8cc8d'],
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


                var flag = 6;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance2() {
    var beginDate = $("#inpstart3").val();
    var endDate = $("#inpend3").val();
    getDptmtAttendanceInit2(beginDate,endDate);
}


function getDptmtAttendanceInit3(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"材料学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance3'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#a3ccc5'],
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


                var flag = 1;
                $.each(data.data, function (index, item) {
                    option.xAxis[0].data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance3() {
    var beginDate = $("#inpstart4").val();
    var endDate = $("#inpend4").val();
    getDptmtAttendanceInit3(beginDate,endDate);
}


function getDptmtAttendanceInit4(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"土木与交通学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance4'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#5dc9cc'],
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


                var flag = 3;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance4() {
    var beginDate = $("#inpstart5").val();
    var endDate = $("#inpend5").val();
    getDptmtAttendanceInit4(beginDate,endDate);
}


function getDptmtAttendanceInit5(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"电力学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance5'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#cc97ba'],
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


                var flag = 6;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance5() {
    var beginDate = $("#inpstart6").val();
    var endDate = $("#inpend6").val();
    getDptmtAttendanceInit5(beginDate,endDate);
}


function getDptmtAttendanceInit6(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"机械学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance6'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#ccbf42'],
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


                var flag = 3;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance6() {
    var beginDate = $("#inpstart7").val();
    var endDate = $("#inpend7").val();
    getDptmtAttendanceInit6(beginDate,endDate);
}


function getDptmtAttendanceInit7(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"环境与市政工程学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance7'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#b3cca6'],
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
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance7() {
    var beginDate = $("#inpstart8").val();
    var endDate = $("#inpend8").val();
    getDptmtAttendanceInit7(beginDate,endDate);
}


function getDptmtAttendanceInit8(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"管理与经济学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance8'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#80cc97'],
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
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance8() {
    var beginDate = $("#inpstart9").val();
    var endDate = $("#inpend9").val();
    getDptmtAttendanceInit8(beginDate,endDate);
}


function getDptmtAttendanceInit9(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"数学与统计学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance9'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#82a6cc'],
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
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance9() {
    var beginDate = $("#inpstart10").val();
    var endDate = $("#inpend10").val();
    getDptmtAttendanceInit9(beginDate,endDate);
}


function getDptmtAttendanceInit10(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"建筑学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance10'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#cc9a38'],
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


                var flag = 5;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance10() {
    var beginDate = $("#inpstart11").val();
    var endDate = $("#inpend11").val();
    getDptmtAttendanceInit10(beginDate,endDate);
}


function getDptmtAttendanceInit11(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"信息工程学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance11'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#cc8442'],
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


                var flag = 3;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance11() {
    var beginDate = $("#inpstart12").val();
    var endDate = $("#inpend12").val();
    getDptmtAttendanceInit11(beginDate,endDate);
}


function getDptmtAttendanceInit12(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"物理与电子学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance12'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#a6aacc'],
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
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance12() {
    var beginDate = $("#inpstart13").val();
    var endDate = $("#inpend13").val();
    getDptmtAttendanceInit12(beginDate,endDate);
}


function getDptmtAttendanceInit13(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"外国语学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance13'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#ccbe36'],
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


                var flag = 1;
                $.each(data.data, function (index, item) {
                    option.xAxis[0].data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance13() {
    var beginDate = $("#inpstart14").val();
    var endDate = $("#inpend14").val();
    getDptmtAttendanceInit13(beginDate,endDate);
}


function getDptmtAttendanceInit14(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"法学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance14'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        subtext: '2018年',
                        x: 'center'
                    },
                    color: ['#4acca6'],
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


                var flag = 1;
                $.each(data.data, function (index, item) {
                    option.xAxis[0].data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance14() {
    var beginDate = $("#inpstart15").val();
    var endDate = $("#inpend15").val();
    getDptmtAttendanceInit14(beginDate,endDate);
}


function getDptmtAttendanceInit15(beginDate,endDate) {
    var json = {
        "beginDate": beginDate,
        "endDate": endDate,
        "dptmt":"艺术与设计学院"
    };
    $.ajax({
        url: "/attendance/getDptmtAttendance", //请求地址
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post", //请求类型
        dataType: "json", //返回类型
        async: false, //是否异步，默认为true
        success: function (data) {
            if (data.code == 200) {
                // 电子书库访问量部分
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('getDptmtAttendance15'));

                // 指定图表的配置项和数据

                option = {
                    title: {
                        text: '',
                        subtext: '2018年',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    color: ['#cc9cc0'],
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


                var flag = 3;
                $.each(data.data, function (index, item) {
                    option.yAxis.data.push(data.data[flag-index].majorName);
                    option.series[0].data.push(data.data[flag-index].totalNum);

                });
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
    })

}

function toGetDptmtAttendance15() {
    var beginDate = $("#inpstart16").val();
    var endDate = $("#inpend16").val();
    getDptmtAttendanceInit15(beginDate,endDate);
}
