<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学生购买课程</title>
    <jsp:include page="../common.jsp"/>
    <script type="text/javascript">
        function returnUpMenu() {
            window.location.href = "${pageContext.request.contextPath}/classArrange/toClassArrange.do";
        }
    </script>
</head>
<body>
    <h3 align="center">
        新增学生购买课程
        <button type="button" class="easyui-linkbutton" data-options="{iconCls:'icon-undo'}" onclick="returnUpMenu();">返回上级菜单</button>
    </h3>
    <form>
        <table align="center" class="easyui-table">
            <tr>
                <td>选择学生年级：</td>
                <td>
                    <input id="gradeID" class="easyui-textbox" style="width: 120px;"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>选择学生：</td>
                <td>
                    <input id="studentID" name="student.stuID" class="easyui-textbox" style="width: 120px;"/>
                </td>
            </tr>
            <tr>
                <td>选择课程类型：</td>
                <td>
                    <input id="courseTypeID" name="courseType.courseTypeID" class="easyui-textbox" style="width: 120px;"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>选择教材类型：</td>
                <td>
                    <input id="lessonTypeID" name="lessonType.lessonTypeID" class="easyui-textbox" style="width: 120px;"/>
                </td>
            </tr>
            <tr>
                <td>购买总节数：</td>
                <td>
                    <input id="lessonTotalNumber" name="lessonTotalNumber" class="easyui-numberbox" style="width: 120px;"/>
                    <input type="hidden" id="lessonRestNumber" name="lessonRestNumber"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>折扣：</td>
                <td>
                    <input id="discount" name="discount" class="easyui-numberbox" style="width: 120px;" value="1.0" data-options="{precision:1}"/>
                </td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="保存" class="easyui-linkbutton"/>
                </td>
            </tr>
        </table>
    </form>
</body>
<script type="text/javascript">
    $(function () {
        //页面加载时显示学生年级及学生二级联动选项
        $.get("${pageContext.request.contextPath}/stuGrade/findAllStudentGrade.do",function (studentGrades) {
            var sgfo = $.parseJSON('{"stuGradeID":-1,"stuGradeName":"==请选择=="}');
            studentGrades.push(sgfo);
            //console.log(sgfo);
            $("#gradeID").combobox({
                valueField:"stuGradeID",
                textField:"stuGradeName",
                data:studentGrades,
                onLoadSuccess:function () {
                    $(this).combobox("select", -1);
                },
                onSelect:function (stuGrade) {
                    var stuGradeID = stuGrade.stuGradeID;
                    $.get("${pageContext.request.contextPath}/student/findStuByGradeID",{"stuGradeID":stuGradeID},function (students) {
                        var stufo = $.parseJSON('{"stuID":-1,"stuName":"==请选择=="}');
                        students.push(stufo);
                        $("#studentID").combobox({
                            valueField:"stuID",
                            textField:"stuName",
                            data:students,
                            onLoadSuccess:function () {
                                $(this).combobox("select", -1);
                            },
                        });
                    });
                }
            });
        })
    });
</script>
</html>
