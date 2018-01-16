<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/16
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>按年月季度统计新报名课程数</title>
    <jsp:include page="../common.jsp"/>
    <script type="text/javascript">
        $(function () {

        });
    </script>
</head>
<body>
<div>
    查询范围：
    <input id="selectTimeType"/>
</div>
<div id="stuCourseCourseType" style="width: 700px; height: 400px;"></div>
<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById('stuCourseCourseType'));
    $(function () {
        $("#selectTimeType").combobox({
            editable:false,
            valueField:"id",
            textField:"text",
            data:[

                {"id":"year","text":"按年统计"},
                {"id":"month","text":"按月统计"},
                {"id":"quaterYear","text":"按季度统计"},
            ],
            onLoadSuccess:function () {
                $(this).combobox("select", "year");
            },
            onSelect:function (item) {

                var timeType = item.id;
                console.log(timeType);
                $.get("${pageContext.request.contextPath}/data/countStuCourseNumberByTimeType.do?timeType="+timeType,function (data) {
                    myChart1.setOption({
                        title: {
                            text: '新报名课程数量统计'
                        },
                        xAxis: {
                            type:"category",
                            data: data.xArray,
                        },
                        yAxis:{},
                        series: [{
                            type:"line",
                            data: data.yArray,
                        }]
                    });
                })
            }
        });
    });


</script>
</body>
</html>
