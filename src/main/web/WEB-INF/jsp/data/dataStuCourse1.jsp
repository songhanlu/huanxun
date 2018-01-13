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
    <div id="stuCourseCourseType" style="width: 700px; height: 400px;"></div>
<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById('stuCourseCourseType'));
    $(function () {
        $.get("${pageContext.request.contextPath}/data/countStuCourseNumberByCourseTypeID.do",function (data) {
            var option1 = {
                title: {
                    text: '课程分类统计（按教材分类）'
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
            myChart1.setOption(option1);
        });
    });


</script>
</body>
</html>
