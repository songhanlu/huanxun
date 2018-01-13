<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/11
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据统计模块</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
    <div id="stuCourseLessonType" style="width: 700px; height: 400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('stuCourseLessonType'));
    $(function () {
        $.get("${pageContext.request.contextPath}/data/countStuCourseNumberByLessonTypeID.do",function (data) {
            var option = {
                title: {
                    text: '课程分类统计（按课程种类分）'
                },
                series: [{
                    type: 'pie',
                    data:data,
                    label:{
                        normal:{
                            show:true,
                            formatter:'{b}/{c}/{d}%',
                        },
                    },
                }],

            };
            myChart.setOption(option);
        });
    });


</script>
</body>
</html>
