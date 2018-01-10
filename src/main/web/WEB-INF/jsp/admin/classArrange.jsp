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
                    <td><h4 style="margin: 2px;">条件查询</h4></td>
                </tr>
                <tr>
                    <td>学生姓名：<input id="studentName" class="easyui-textbox" name="student.studentName"/></td>
                </tr>
            </table>
        </form>
        <p align="center">
            <button id="studentCourseSearchButton" class="easyui-linkbutton" data-options="{iconCls:'icon-search'}">搜索</button>
        </p>
        <h3 align="center" style="margin-bottom: 0;">所有学生列表</h3>
    </div>
    <table id="classArrangeDatagrid"></table>
<script type="text/javascript">
    $(function () {
        $("#studentCourseForm").form("clear");
        $("#studentCourseSearchButton").click(function () {
            var stuName = $("#studentName").textbox("getValue");
            $("#classArrangeDatagrid").datagrid("load", {
                "stuName":stuName,
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
            rownumbers:true,
            striped:true,
            onLoadSuccess:function () {
                $("button").linkbutton({iconCls:'icon-edit'});
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
                {field:"lessonRestNumber",title:"剩余节数"},
                {field:"isArranged",title:"是否已完成排课",formatter:function (value, row, index) {
                    var flag = "";
                    if(value==1){
                        flag="是";
                    }else{
                        flag="否";
                    }
                    return flag;
                }},
                {field:"stuCourseID",title:"排课",width:200,formatter:function (value, row, index) {
                    return "<button onclick='arrangeClass("+value+")'>排课</button>";
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
