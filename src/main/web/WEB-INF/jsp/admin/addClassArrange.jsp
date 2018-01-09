<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/7
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开始排课</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
    <a href="${pageContext.request.contextPath}/classArrange/toClassArrange.do"
            class="easyui-linkbutton" data-options="{iconCls:'icon-undo'}">返回上级</a>
    <div>
        <table>
            <tr>
                <td>学生姓名：</td>
                <td><input class="easyui-textbox" readonly value="${studentCourse.student.stuName}"/></td>
                <td>课程类型：</td>
                <td><input class="easyui-textbox" readonly
                           value="${studentCourse.courseType.courseTypeName}-${studentCourse.lessonType.lessonArea}${studentCourse.lessonType.timePerLesson}分钟"/></td>
                <td>试听</td>
                <td>
                    <c:if test="${studentCourse.lessonType.isTry eq 1}">
                        <input class="easyui-textbox" readonly value="是"/>
                    </c:if>
                    <c:if test="${studentCourse.lessonType.isTry ne 1}">
                        <input class="easyui-textbox" readonly value="否"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>需要排课节数：</td>
                <td>
                    <input class="easyui-textbox" readonly value="${studentCourse.lessonTotalNumber}"/>
                </td>
                <td>已排课节数：</td>
                <td>
                    <input class="easyui-textbox" readonly value=""/>
                </td>
            </tr>
        </table>
    </div>
</body>

</html>
