<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排课功能</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
    <div>
        <h3 align="center">
            学生已购课程列表
        </h3>
    </div>
    <table id="classArrangeDatagrid"></table>
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
                {field:"agency",title:"学生所属机构",formatter:function (value, row, index) {
                    var agencyName = "";
                    if(row.teacher!=null){
                        agencyName=row.agency.agencyName;
                    }else{
                        agencyName = "无";
                    }
                    return agencyName;
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
                {field:"lessonTotalNumber",title:"总节数"},
                {field:"lessonRestNumber",title:"剩余节数"},
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
