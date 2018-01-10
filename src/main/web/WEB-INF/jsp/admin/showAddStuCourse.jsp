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
    <form id="newStuCourseForm">
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
                    <input id="lessonTypeID" name="lessonType.lessonTypeID" class="easyui-textbox" style="width: 120px;"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>选择教材类型：</td>
                <td>
                    <input id="courseTypeID" name="courseType.courseTypeID" class="easyui-textbox" style="width: 120px;"/>
                </td>
            </tr>
            <tr>
                <td>教师职业类型：</td>
                <td>
                    <input id="teacherCareerType" name="teacherCareerType" style="width: 120px;"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>教师姓名：</td>
                <td>
                    <input id="teacherID" name="teacherID" style="width: 120px;"/>
                </td>
            </tr>
            <tr>
                <td>购买总节数：</td>
                <td>
                    <input id="lessonTotalNumber" name="lessonTotalNumber" class="easyui-numberbox" value="1" data-options="{min:1}" style="width: 120px;"/>
                    <input type="hidden" id="lessonRestNumber" name="lessonRestNumber"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>折扣：</td>
                <td>
                    <input id="discount" name="discount" class="easyui-numberbox" style="width: 120px;" value="1.0" data-options="{precision:1,max:1}"/>
                </td>
            </tr>
            <%--<tr>
                <td colspan="3" align="center">
                    <input type="submit" value="保存" class="easyui-linkbutton"/>
                </td>
            </tr>--%>
        </table>

    </form>
    <h3 align="center"><button id="" onclick="saveNewStuCourse();" class="easyui-linkbutton" data-options="{iconCls:'icon-save'}">保存新课程</button></h3>
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
                editable:false,
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
                            editable:false,
                            data:students,
                            onLoadSuccess:function () {
                                $(this).combobox("select", -1);
                            },
                        });
                    });
                }
            });
        });

        //加载页面时显示所有教材类型
        $.get("${pageContext.request.contextPath}/courseType/findAllCourseType",function (courseTypes) {
            var ctfo = $.parseJSON('{"courseTypeID":-1,"courseTypeName":"==请选择=="}');
            courseTypes.push(ctfo);
            $("#courseTypeID").combobox({
                valueField:"courseTypeID",
                textField:"courseTypeName",
                editable:false,
                data:courseTypes,
                onLoadSuccess:function () {
                    $(this).combobox("select", -1);
                },
            });
        });

        //加载页面时显示所有课程类型
        $.get("${pageContext.request.contextPath}/lessonType/findAllLessonType",function (lessonTypes) {
            var ptfo = $.parseJSON('{"lessonTypeID":-1,"lessonDesc":"==请选择=="}');
            lessonTypes.push(ptfo);
            $("#lessonTypeID").combobox({
                valueField:"lessonTypeID",
                textField:"lessonDesc",
                editable:false,
                data:lessonTypes,
                onLoadSuccess:function () {
                    $(this).combobox("select", -1);
                },
            });
        });

        //改变discount值
        $("#discount").numberbox({
            onChange:function (newValue,oldValue) {
                oldValue = newValue;
            }
        });

        //点击保存按钮事件

    });

</script>
<script type="text/javascript">
    function saveNewStuCourse() {
        //校验参数
        var stuID = $("#studentID").combobox("getValue");
        var courseTypeID = $("#courseTypeID").combobox("getValue");
        var lessonTypeID = $("#lessonTypeID").combobox("getValue");
        var teacherID = $("#teacherID").combobox("getValue");
        if(stuID==-1 || courseTypeID==-1 || lessonTypeID==-1 || teacherID==-1) {
            alert("请选择正确的参数！");
            return;
        }
        //将totalNumber的值赋给restNumber
        var number = $("#lessonTotalNumber").numberbox("getValue");
        $("#lessonRestNumber").val(number);
        var studentCourse = $("#newStuCourseForm").serialize();
        $.post("${pageContext.request.contextPath}/studentCourse/addStuCourse.do",studentCourse,function (result) {
            console.log(result);
            alert(result.msg);
            window.location.href = "${pageContext.request.contextPath}/classArrange/toClassArrange.do";
        });
    }
</script>
<script type="text/javascript">
    $(function () {
        /*教师职业列表*/
        $("#teacherCareerType").combobox({
            editable:false,
            valueField:"careerTypeName",
            textField:"careerTypeName",
            data:[
                {"careerTypeName":"全职"},
                {"careerTypeName":"兼职"}
            ],
            onLoadSuccess:function () {
                $(this).combobox("select", "全职");
            },
            onSelect:function (careerType) {
                var careerTypeName = careerType.careerTypeName;
                $.get("${pageContext.request.contextPath}/teacher/findTeacherByCareerType",{"careerType":careerTypeName},function (teachers) {
                    var tfo = $.parseJSON('{"teacherID":-1,"teacherName":"==请选择=="}');
                    teachers.push(tfo);
                    $("#teacherID").combobox({
                        valueField:"teacherID",
                        textField:"teacherName",
                        data:teachers,
                        onLoadSuccess:function () {
                            $(this).combobox("select", -1);
                        },
                    });
                })
            }
        });
    });
</script>
</html>
