<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件，主页面引入 -->
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main_e" style="width: 600px;height:400px;"></div>

<script type="text/javascript">

        var goEasy = new GoEasy({
            appkey: "BC-a36c38bc99ab4be39f74d7840201943e"
        });
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main_e'));
        var option = {
            title: {
                text: '持明法洲App活跃用户',
                subtext: '用户统计',
                left: 'center'
            },
            tooltip: {},
            legend: {
                type: "scroll",
                data: ['用户数量']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
        }
        myChart.setOption(option);

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/queryAllByDate",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '用户',
                        data: data.count,
                        type:"bar"
                    }],
                    xAxis: {
                        data: data.intervals
                    }
                })
            }
        });

        goEasy.subscribe({
            channel: "easy1",
            onMessage: function (message) {
                var mes = eval(message);
                console.log(mes.content);
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '用户',
                        data: mes.content.count,
                        type:"bar"
                    }],
                    xAxis: {
                        data: mes.content.intervals
                    }
                })
            }
        });




</script>
</body>
</html>