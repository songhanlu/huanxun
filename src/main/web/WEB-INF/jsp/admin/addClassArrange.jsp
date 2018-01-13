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
                    本次要排课：<input name="lessonNumber" class="easyui-numberbox" data-options="{min:1,value:1,required:true}"/>节
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    是否启用自动排课:<input id="autoArrangeButton" type="checkbox"/>
                    自动排课<input type="text" id="times" name="times" style="width: 50px;" class="easyui-textbox" readonly/>节
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

        <%--为课表上传教材窗口--%>
        <div id="uploadBookForArrange" class="easyui-window" title="上传教材" style="width: 700px;height: 400px;top:50px;left:50px;padding: 20px;" data-options="{closed:true}">
            <button id="addUploadBook" data-options="{iconCls:'icon-add'}">添加教材</button>
            <form style="display: inline" id="classBookForm">
                <input id="book" name="book.bookID" class="easyui-combobox"/>
                <input type="hidden" id="stuClassArrangeID" name="stuClassArrangeID"/>
            </form>
            <table id="haveBooksTable" border="1"></table>
        </div>
    </div>
</body>
<%--添加上传教材有关的事件--%>
<script type="text/javascript">
    $(function () {
        $("#addUploadBook").click(function () {
            var bookIDtest = $("#book").combobox("getValue");
            if(bookIDtest==-1){
                alert("请选择！");
                return;
            }
            if(confirm("确定上传该教材？")){


                var stuArrangeID = $("#stuClassArrangeID").val();
                var bookID = $("#book").combobox("getValue");
                var isError = false;
                //检查当前课程是否已上传
                $.get("${pageContext.request.contextPath}/classArrange/findClassBookByArrangeID.do",{"arrangeID":stuArrangeID},function (books) {
                    $.each(books,function (index, item) {
                        if(item.bookID==bookID){
                            alert("已添加过该教材，不能重复上传！");
                            isError = true;
                            return;
                        }
                    });
                    if(!isError){
                        var classBook = $("#classBookForm").serialize();
                        console.log(classBook);
                        $.post("${pageContext.request.contextPath}/classArrange/addClassBook.do",classBook,function (result) {
                            alert(result.msg);
                            //刷新表格
                            $("#haveBooksTable").text("");
                            $("#haveBooksTable").append("<tr><td>序号</td><td>教材名称</td><td>教材版本</td><td>下载附件</td><td>操作</td></tr>");
                            $.get("${pageContext.request.contextPath}/classArrange/findBooksByArrangeID.do",{"arrangeID":stuArrangeID},function (books) {
                                $.each(books,function (index, item) {
                                    $("#haveBooksTable").append("<tr><td>"+(index+1)+"</td>" +
                                        "<td>"+item.bookTitle+"</td>" +
                                        "<td>"+item.bookVersion+"</td>" +
                                        "<td><a href='${pageContext.request.contextPath}/"+item.bookAddress+"'>下载附件</a></td>"+
                                        "<td><button onclick='delBook("+item.bookID+","+stuArrangeID+")'>删除教材</button></td></tr>");
                                });
                            });
                        });
                    }
                });

            }
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        <c:choose>
            <c:when test="${!empty classArrangeTotal}">
                $("#haveArrangeNumber").textbox("setValue",${classArrangeTotal});
            </c:when>
            <c:otherwise>
                $("#haveArrangeNumber").textbox("setValue",0);
            </c:otherwise>
        </c:choose>
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
                        /*启用自动排课的javascript*/
                        $("#times").textbox("setValue", 1);
                        $("#autoArrangeButton").click(function () {
                            if($(this).prop("checked")){
                                $("#times").textbox({
                                    readonly:false,
                                });
                            }else{
                                $("#times").textbox({
                                    readonly:true,
                                });
                                $("#times").textbox("setValue", 1);
                            }
                        });
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
                            //更新当前课程的排课总数
                            $.get("${pageContext.request.contextPath}/classArrange/countClassArrange.do",{"stuCourseID":${studentCourse.stuCourseID}},function (data) {
                                var count = data.count;
                                $("#haveArrangeNumber").textbox("setValue", count);
                            });
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
                        "<button onclick='deleteStuClassArrange("+value+");'>删除</button>"+
                        "<button onclick='uploadBook("+value+");'>上传教材</button>";
                }}
            ]],
        });


    });
</script>
<script type="text/javascript">
    $(function () {
        $("#saveNewClassArrangeButton").click(function () {

            var studentClassArrange = $("#addClassArrangeForm").serialize();
            //console.log(studentClassArrange);
            $.post("${pageContext.request.contextPath}/classArrange/addArrange.do",studentClassArrange,function (result) {
                alert(result.msg);
                $("#classArrangeDatagrid").datagrid("reload");
                $("#addClassArrangeWindow").window("close");
                //更新当前课程的排课总数
                $.get("${pageContext.request.contextPath}/classArrange/countClassArrange.do",{"stuCourseID":${studentCourse.stuCourseID}},function (data) {
                    var count = data.count;
                    $("#haveArrangeNumber").textbox("setValue", count);
                });
            });


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
                    $("#updateClassArrangeWindow").window("close");
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
                //更新当前课程的排课总数
                $.get("${pageContext.request.contextPath}/classArrange/countClassArrange.do",{"stuCourseID":${studentCourse.stuCourseID}},function (data) {
                    var count = data.count;
                    $("#haveArrangeNumber").textbox("setValue", count);
                });
            });
        }
    }
    //点击上传教材按钮方法
    function uploadBook(id) {
        $("#stuClassArrangeID").val(id);
        $("#haveBooksTable").text("");
        $("#haveBooksTable").append("<tr><td>序号</td><td>教材名称</td><td>教材版本</td><td>下载附件</td><td>操作</td></tr>");
        $.get("${pageContext.request.contextPath}/classArrange/findBooksByArrangeID.do",{"arrangeID":id},function (books) {
            $.each(books,function (index, item) {
                $("#haveBooksTable").append("<tr><td>"+(index+1)+"</td>" +
                    "<td>"+item.bookTitle+"</td>" +
                    "<td>"+item.bookVersion+"</td>" +
                    "<td><a href='${pageContext.request.contextPath}/"+item.bookAddress+"'>下载附件</a></td>"+
                    "<td><button onclick='delBook("+item.bookID+","+id+")'>删除教材</button></td></tr>");
            });
            $.get("${pageContext.request.contextPath}/classArrange/findBooksByCourseTypeID.do",{"courseTypeID":${studentCourse.courseType.courseTypeID}},function (books) {
                var bfo = $.parseJSON('{"id":-1,"text":"==请选择=="}');
                books.push(bfo);
                $("#book").combobox({
                    editable:false,
                    valueField:"id",
                    textField:"text",
                    data:books,
                    onLoadSuccess:function () {
                        $(this).combobox("select", -1);
                    }
                });
            });
        });
        $("#uploadBookForArrange").window("open");
    }
    //删除教材
    function delBook(id,arrangeID) {
        if(confirm("确定删除？")){
            $.post("${pageContext.request.contextPath}/classArrange/deleteClassBookByArrangeID.do",{"arrangeID":id},function (result) {
                alert(result.msg);
                //刷新表格
                $("#haveBooksTable").text("");
                $("#haveBooksTable").append("<tr><td>序号</td><td>教材名称</td><td>教材版本</td><td>下载附件</td><td>操作</td></tr>");
                $.get("${pageContext.request.contextPath}/classArrange/findBooksByArrangeID.do",{"arrangeID":arrangeID},function (books) {
                    $.each(books,function (index, item) {
                        $("#haveBooksTable").append("<tr><td>"+(index+1)+"</td>" +
                            "<td>"+item.bookTitle+"</td>" +
                            "<td>"+item.bookVersion+"</td>" +
                            "<td><a href='${pageContext.request.contextPath}/"+item.bookAddress+"'>下载附件</a></td>"+
                            "<td><button onclick='delBook("+item.bookID+","+arrangeID+")'>删除教材</button></td></tr>");
                    });
                });
            });
        }
    }
</script>
</html>
