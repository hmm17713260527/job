<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/1
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery.validate.js"></script>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\echarts.min.js"></script>
</head>
<script type="text/javascript">
    var v = [1,2,3,4];

    $(function() {
        search();
    })

    function search() {
        var index = layer.load(1,{shade:0.5});
        $.get("<%=request.getContextPath()%>/userCar/findTurnover",
            {"looks":v},
            function(data){
                layer.close(index);
                if (data.code != 200) {
                    return;
                }
                var x = ["今日营业额", "昨日营业额", "本周营业额", "本月营业额"];
                var y = [];
                for(var i = 0; i < data.data.length; i++){
                    y.push(data.data[i]);
                }
                var myChart = echarts.init(document.getElementById('chartmain'));
                myChart.clear();

                var yMax = 10000000000;
                option = {
                    title: {
                        text: '特性示例：渐变色 阴影 点击缩放',
                        subtext: '额度'
                    },
                    xAxis: {
                        data: x,
                        axisLabel: {
                            inside: true,
                            textStyle: {
                                color: '#fff'
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        z: 10
                    },
                    yAxis: {
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#999'
                            }
                        }
                    },
                    dataZoom: [
                        {
                            type: 'inside'
                        }
                    ],
                    series: [
                        { // For shadow
                            type: 'bar',
                            itemStyle: {
                                normal: {color: 'rgba(0,0,0,0.05)'}
                            },
                            barGap:'-100%',
                            barCategoryGap:'40%',
                            data: y,
                            animation: false
                        },
                        {
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#83bff6'},
                                            {offset: 0.5, color: '#188df0'},
                                            {offset: 1, color: '#188df0'}
                                        ]
                                    )
                                },
                                emphasis: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                    )
                                }
                            },
                            data: y
                        }
                    ]
                };

                // Enable data zoom when user click bar.
                var zoomSize = 6;
                myChart.on('click', function (params) {
                    console.log(x[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                    myChart.dispatchAction({
                        type: 'dataZoom',
                        startValue: x[Math.max(params.dataIndex - zoomSize / 2, 0)],
                        endValue: x[Math.min(params.dataIndex + zoomSize / 2, y.length - 1)]
                    });
                });

                myChart.setOption(option);

            })
    }
</script>
<body>
    <div style="border:2px solid #666;width:49%;height:450px;float:left" id="chartmain"></div>
</body>
</html>
