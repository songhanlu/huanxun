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
                <td>
                    <input class="easyui-textbox" readonly value="${studentCourse.student.stuName}"/>
                </td>
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
                    <input id="haveArrangeNumber" class="easyui-textbox" readonly/>
                </td>
            </tr>
        </table>

        <table id="classArrangeDatagrid"></table>

        <%--新增排课窗口--%>
        <div id="addClassArrangeWindow" class="easyui-window" title="开始排课" style="width: 700px;height: 400px;top:50px;left:50px;padding: 20px;" data-options="{closed:true}">
            <form id="addClassArrangeForm">
                选择全职/兼职：<input id="teacherCareer"/>&nbsp;&nbsp;&nbsp;
                选择教师：<input id="teacher" name="teacherID"/>
                <input name="stuCourseID" type="hidden"/>
                <br/>
                <h3 style="margin: 2px;">可排课时间段：</h3>
                <%--<ul id="partTeacherTimeListUl" style="list-style: none; margin: 0;padding: 0;"></ul>--%>
                <table border="1">
                    <tr id="partTeacherTimeListTR"></tr>
                </table>
                <br/>
                <div>
                    开始时间：<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="{required:true,showSeconds:false}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    结束时间：<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="{required:true,showSeconds:false}"/>
                </div>
                <div>
                    <br/>
                    本次排课：<input name="lessonNumber" class="easyui-numberbox" data-options="{min:1,value:1,required:true}"/>节
                    <input type="hidden" name="times" value="1"/>
                </div>
            </form>
            <button id="saveNewClassArrangeButton" class="easyui-linkbutton" data-options="{iconCls:'icon-save'}">确定排课</button>
        </div>

        <%--修改排课窗口--%>
        <div id="updateClassArrangeWindow" class="easyui-window" title="修改课表" style="width: 700px;height: 400px;top:50px;left:50px;padding: 20px;" data-options="{closed:true}">
            <form id="updateClassArrangeForm">
                <div>
                    开始时间：<input id="startTime_update" name="startTime" class="easyui-datetimebox" data-options="{required:true,showSeconds:false}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    结束时间：<input id="endTime_update" name="endTime" class="easyui-datetimebox" data-options="{required:true,showSeconds:false}"/>
                    <input type="hidden" id="stuClassArrangeID_update" name="stuClassArrangeID"/>
                </div>
                <div>
                    <br/>
                    本次排课：<input id="lessonNumber_update" name="lessonNumber" class="easyui-numberbox" data-options="{min:1,value:1,required:true}"/>节
                    <input type="hidden" name="times" value="1"/>
                </div>
            </form>
            <button id="updateNewClassArrangeButton" class="easyui-linkbutton" data-options="{iconCls:'icon-save'}">确定排课</button>
        </div>
    </div>
</body>
<script type="text/javascript">
    $(function () {
        /*表格*/
        $("#classArrangeDatagrid").datagrid({
            url:"${pageContext.request.contextPath}/classArrange/findArrangesByCourseID.do?stuCourseID=${studentCourse.stuCourseID}",
            pagination:true,
            striped:true,
            rownumbers:true,
            method:"get",
            onLoadSuccess:function () {
                $("button").linkbutton();
                $(this).datagrid("fixRowHeight");
                var total = $(this).get()
                <c:choose>
                    <c:when test="${!empty classArrangeTotal}">
                        $("#haveArrangeNumber").textbox("setValue",${classArrangeTotal});
                    </c:when>
                    <c:otherwise>
                        $("#haveArrangeNumber").textbox("setValue",0);
                    </c:otherwise>
                </c:choose>
            },
            toolbar:[
                {
                    text:"开始排课",
                    iconCls:"icon-add",
                    handler:function () {
                        $("#addClassArrangeForm").form("clear");
                        $("input[name=stuCourseID]").val(${studentCourse.stuCourseID});
                        $("input[name=times]").val(1);
                        $("#addClassArrangeWindow").window("open");
                        /*教师职业列表*/
                        $("#teacherCareer").combobox({
                            editable:false,
                            valueField:"careerTypeName",
                            textField:"careerTypeName",
                            data:[
                                {"careerTypeName":"全职"},
                                {"careerTypeName":"兼职"}
                            ],
                            onLoadSuccess:function () {
                                $(this).combobox("select", "${studentCourse.teacher.teacherCareerType}");
                            },
                            onSelect:function (careerType) {
                                var careerTypeName = careerType.careerTypeName;
                                $.get("${pageContext.request.contextPath}/teacher/findTeacherByCareerType",{"careerType":careerTypeName},function (teachers) {
                                    var tfo = $.parseJSON('{"teacherID":-1,"teacherName":"==请选择=="}');
                                    teachers.push(tfo);
                                    $("#teacher").combobox({
                                        valueField:"teacherID",
                                        textField:"teacherName",
                                        data:teachers,
                                        onLoadSuccess:function () {
                                            var career = $("#teacherCareer").val();
                                            var currentTeacherCareer = "${studentCourse.teacher.teacherCareerType}";
                                            if(career==currentTeacherCareer){
                                                $(this).combobox("select", ${studentCourse.teacher.teacherID});
                                            }else{
                                                $(this).combobox("select", -1);
                                            }

                                        },
                                        onSelect:function (teacher) {
                                            $("#partTeacherTimeListTR").text("");
                                            var careerType = $("#teacherCareer").combobox("getValue");
                                            if(careerType=="兼职"){
                                                var teacherID = teacher.teacherID;
                                                $.get("${pageContext.request.contextPath}/ptTime/findPTTimeByTeacherID",{"teacherID":teacherID},function (ptTimes) {
                                                    $.each(ptTimes,function (index,item) {
                                                        var sTime = new Date(item.ptStartTime);
                                                        var eTime = new Date(item.ptEndTime);
                                                        $("#partTeacherTimeListTR").append("<td>周"+item.weekDay+"，"+sTime.getHours()+":"+sTime.getMinutes()+"到"+eTime.getHours()+":"+eTime.getMinutes()+"</td>")
                                                    });
                                                });
                                            }else{
                                                $("#partTeacherTimeListTR").append("<td>全时段</td>");
                                            }


                                        }
                                    });
                                })
                            }
                        });
                    }
                },
                {
                    text:"批量删除",
                    iconCls:"icon-remove",
                    handler:function () {
                        var check = $("#classArrangeDatagrid").datagrid("getChecked");
                        var IDs = "";
                        if(check==null || check.length==0){
                            alert("请先勾选，再点删除");
                            return;
                        }
                        $.each(check,function (index, item) {
                            IDs = IDs + item.stuClassArrangeID+",";
                        });
                        $.post("${pageContext.request.contextPath}/classArrange/deleteArrange.do",{"IDs":IDs,"stuCourseID":${studentCourse.stuCourseID}},function (result) {
                            alert(result.msg);
                            $("#classArrangeDatagrid").datagrid("reload");
                        });

                    }
                }
            ],
            columns:[[
                {field:"",checkbox:true},
                {title:"授课教师",field:"teacher",formatter:function (value, row, index) {
                    return value.teacherName;
                }},
                {title:"开始时间",field:"startTime",formatter:function (value, row, index) {
                    var date = new Date(value);
                    var yyyy = date.getFullYear();
                    var MM = date.getMonth()+1;
                    var dd = date.getDate();
                    var HH = date.getHours();
                    var mm = date.getMinutes();
                    return yyyy+"年"+MM+"月"+dd+"日 "+HH+":"+mm;
                }},
                {title:"时间",field:"endTime",formatter:function (value, row, index) {
                    var date = new Date(value);
                    var yyyy = date.getFullYear();
                    var MM = date.getMonth()+1;
                    var dd = date.getDate();
                    var HH = date.getHours();
                    var mm = date.getMinutes();
                    return yyyy+"年"+MM+"月"+dd+"日 "+HH+":"+mm;
                }},
                {title:"排课节数",field:"lessonNumber"},
                {title:"操作",field:"stuClassArrangeID",formatter:function (value, row, index) {
                    return "<button onclick='updateStuClassArrange("+value+");'>修改</button>"+
                        "<button onclick='deleteStuClassArrange("+value+");'>删除</button>";
                }}
            ]],
        });


    });
</script>
<script type="text/javascript">
    $(function () {
        $("#saveNewClassArrangeButton").click(function () {
            var studentClassArrange = $("#addClassArrangeForm").serialize();
            console.log(studentClassArrange);
            $.post("${pageContext.request.contextPath}/classArrange/addArrange.do",studentClassArrange,function (result) {
                alert(result.msg);
                $("#classArrangeDatagrid").datagrid("reload");
                $("#addClassArrangeWindow").window("close");
            })

        });
        $("#updateNewClassArrangeButton").click(function () {
            var stuClassArrangeID = $("#stuClassArrangeID_update").val();
            var lesssonNumber = $("#lessonNumber_update").numberbox("getValue");
            var startTime = $("#startTime_update").datetimebox("getValue");
            var endTime = $("#endTime_update").datetimebox("getValue");
            $.post("${pageContext.request.contextPath}/classArrange/updateArrange.do",
                {
                    "stuClassArrangeID":stuClassArrangeID,
                    "lesssonNumber":lesssonNumber,
                    "startTime":startTime,
                    "endTime":endTime,

                },
                function (result) {
                    alert(result.msg);
                    $("#classArrangeDatagrid").datagrid("reload");
                });
        });
    });
</script>
<script type="text/javascript">
    function updateStuClassArrange(id) {
        $("#updateClassArrangeForm").form("clear");
        $.get("${pageContext.request.contextPath}/classArrange/findArrangeByID.do",{"id":id},function (arrage) {
            var startTime = new Date(arrage.startTime);
            var startTimeStr = startTime.getFullYear()+"-"+(startTime.getMonth()+1)+"-"+startTime.getDate()+" "+startTime.getHours()+":"+startTime.getMinutes();
            var endTime = new Date(arrage.endTime);
            var endTimeStr = endTime.getFullYear()+"-"+(endTime.getMonth()+1)+"-"+endTime.getDate()+" "+endTime.getHours()+":"+endTime.getMinutes();
            $("#startTime_update").datetimebox("setValue", startTimeStr);
            $("#endTime_update").datetimebox("setValue", endTimeStr);
            $("#lessonNumber_update").numberbox("setValue", arrage.lessonNumber);
            $("#stuClassArrangeID_update").val(arrage.stuClassArrangeID);
        });
        $("#updateClassArrangeWindow").window("open");
    }
    function deleteStuClassArrange(id) {
        if(confirm("确定删除？")){
            var IDs = id + ",";
            $.post("${pageContext.request.contextPath}/classArrange/deleteArrange.do",{"IDs":IDs,"stuCourseID":${studentCourse.stuCourseID}},function (result) {
                alert(result.msg);
                $("#classArrangeDatagrid").datagrid("reload");
                $("#updateClassArrangeWindow").window("close");
            });
        }
    }
</script>
</html>
