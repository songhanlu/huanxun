<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排课功能</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>

    <div>
        <form id="studentCourseForm" style="margin:0;">
            <table border="1">
                <tr>
                    <td>
                        学生姓名：<input id="studentName" class="easyui-textbox"/>
                    </td>
                    <td>
                        课程类型：<input id="courseTypeCombobox"/>
                    </td>
                    <td>
                        教材类型：<input id="lessonTypeCombobox"/>
                    </td>
                    <td>
                        只查询未完成排课的课程：<input id="isArranged" type="checkbox"/>
                    </td>
                </tr>
            </table>
        </form>
        <p align="center">
            <button id="studentCourseSearchButton" class="easyui-linkbutton" data-options="{iconCls:'icon-add'}">搜索</button>
        </p>
        <h3 align="center" style="margin-bottom: 0;">所有学生列表</h3>
    </div>
    <table id="classArrangeDatagrid"></table>
<script type="text/javascript">
    $(function () {
        $("#studentCourseForm").form("clear");
        $.get("${pageContext.request.contextPath}/courseType/findAllCourseType",function (courses) {
            var cfo = $.parseJSON('{"courseTypeID":-1,"courseTypeName":"==请选择=="}');
            courses.push(cfo);
            //console.log(courses);
            $("#courseTypeCombobox").combobox({
                valueField:"courseTypeID",
                textField:"courseTypeName",
                editable:false,
                data:courses,
                onLoadSuccess:function () {
                    $(this).combobox("select",-1);
                }
            });
        });
        $.get("${pageContext.request.contextPath}/lessonType/findAllLessonType",function (lessons) {
            var lfo = $.parseJSON('{"lessonTypeID":-1,"lessonDesc":"==请选择=="}');
            lessons.push(lfo);
            $("#lessonTypeCombobox").combobox({
                valueField:"lessonTypeID",
                textField:"lessonDesc",
                editable:false,
                data:lessons,
                onLoadSuccess:function () {
                    $(this).combobox("select",-1);
                }
            });
        });

        $("#studentCourseSearchButton").click(function () {
            var stuName = $("#studentName").textbox("getValue");
            var isArranged = -1;
            var courseTypeID = $("#courseTypeCombobox").combobox("getValue");
            var lessonTypeID = $("#lessonTypeCombobox").combobox("getValue");
            var flag = $("#isArranged").prop("checked");
            if(flag){
                isArranged = 0;
            }
            $("#classArrangeDatagrid").datagrid("load", {
                "stuName":stuName,
                "isArranged":isArranged,
                "courseTypeID":courseTypeID,
                "lessonTypeID":lessonTypeID,
            });
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $("#classArrangeDatagrid").datagrid({
            url: "${pageContext.request.contextPath}/classArrange/findStudentCourseIsNotArrage.do",
            method:"get",
            pagination:true,
            /*pageSize:3,
            pageList:[3,5,7],*/
            rownumbers:true,
            striped:true,
            /*remoteSort:false,*/
            onLoadSuccess:function () {
                $("button").linkbutton({iconCls:'icon-edit'});
                $("#studentCourseSearchButton").linkbutton({iconCls:'icon-search'});
                $(this).datagrid("fixRowHeight");
            },
            toolbar:[
                {
                    text:"新增学生购买课程",
                    iconCls:"icon-add",
                    handler:function () {
                        window.location.href = "${pageContext.request.contextPath}/classArrange/toAddStuCourse.do";
                    }
                },
            ],
            columns:[[
                {field:"student",title:"学生姓名",formatter:function (value, row, index) {
                    return row.student.stuName;
                }},
                {field:"teacher",title:"教师姓名",formatter:function (value, row, index) {
                    var teacherName = "";
                    if(row.teacher!=null){
                        teacherName=row.teacher.teacherName;
                    }else{
                        teacherName = "无";
                    }
                    return teacherName;
                }},
                {field:"course",title:"课程类型",width:200,formatter:function (value, row, index) {
                    var course = row.courseType.courseTypeName+"/"+
                                row.lessonType.lessonArea+
                                row.lessonType.timePerLesson+"分钟";
                    return course;
                }},
                {field:"isTry",title:"试听",formatter:function (value, row, index) {
                    var isTry = "";
                    if(row.lessonType.isTry==1){
                        isTry = "是";
                    }else{
                        isTry = "否";
                    }
                    return isTry;
                }},
                {field:"lessonTotalNumber",title:"总节数",sortable:true},
                {field:"lessonRestNumber",title:"剩余节数",sortable:true},
                {field:"isArranged",title:"是否已完成排课",sortable:true,formatter:function (value, row, index) {
                    var flag = "";
                    if(value==1){
                        flag="是";
                    }else{
                        flag="否";
                    }
                    return flag;
                }},
                {field:"stuCourseID",title:"操作",width:200,formatter:function (value, row, index) {

                    return "<button onclick='arrangeClass("+value+")' <c:if test="${sessionScope.loginUser.userRole.userRoleID eq 5 ||}">disabled</c:if>>我要排课</button>";
                }},
            ]],
        });
    })
</script>
<script type="text/javascript">
    function arrangeClass(stuCourseID) {
        window.location.href = "${pageContext.request.contextPath}/classArrange/addClassArrange.do?stuCourseID="+stuCourseID;
    }
</script>
</body>
</html>
