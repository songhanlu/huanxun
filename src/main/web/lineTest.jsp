<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/12
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="common.jsp"/>
</head>
<body>
    <div id="lineTest" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript">
        var chart = echarts.init(document.getElementById('lineTest'));
        var option = {
            title:{
                text:"折线图测试",
            },
            xAxis: {
                type:"category",
                data: [{value:"ser"},{value:"df"},{value:"cc"},{value:"dd"}],
            },
            yAxis:{},
            series: [{
                type:"line",
                // 与 xAxis.data 一一对应。
                data: [23,  44,  55,  19],
                label:{
                    normal:{
                        show:true,
                        formatter:'{b},{c}',
                    },
                },
            }]
        };

        chart.setOption(option);
    </script>
</body>
</html>
