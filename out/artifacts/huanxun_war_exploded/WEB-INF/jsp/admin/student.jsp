<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/5
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理界面</title>
    <%@include file="../common.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#studentDataGrid").datagrid({
                url:'${pageContext.request.contextPath}/student/queryStudent.do',
                method:'GET',
                pagination:true,
                rownumbers:true,
                striped:true,
                checkOnSelect:false,
                ctrlSelect:true,
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-save',
                        handler:function () {
                         /*   $("#addStudentForm").form('clear');*/
                            $("#addStudentName").textbox('setValue', "");
                            $("#addStuQQ").textbox('setValue', "");
                            $("#addStuAge").textbox('setValue',"");
                            $("#addStuEmail").textbox('setValue', "");
                            $("#addStuPhone").textbox('setValue', "");
                            $.get("${pageContext.request.contextPath}/studentGrade/queryStudentGrade",function (studentGrade) {
                                if(studentGrade!=null && studentGrade.length>0) {
                                    $("#addStudentGrade").combobox({
                                        valueField: 'stuGradeID',
                                        textField:'stuGradeName',
                                        data:studentGrade,
                                        onLoadSuccess:function () {
                                            $(this).combobox("select", studentGrade[0].stuGradeID);
                                        }
                                    });
                                }
                            })
                            $.get("${pageContext.request.contextPath}/agency/queryAgency",function (agency) {
                                if(agency!=null && agency.length>0) {
                                    $("#addAgency").combobox({
                                        valueField:'agencyID',
                                        textField:'agencyName',
                                        data:agency,
                                        onLoadSuccess:function () {
                                            $(this).combobox("select", agency[0].agencyID);
                                        }
                                    })
                                }
                            })
                            $("#addStudentWindow").window('open');

                        }
                    },
                    {
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            var checkStudent = $("#studentDataGrid").datagrid("getChecked");
                            if(checkStudent==null || checkStudent.length<=0) {
                                alert("请选择要删除的学生");
                                return;
                            }
                            var idStr = "";
                            $.each(checkStudent,function (index, item) {
                                idStr = idStr + item.stuID + ",";
                            })
                            if(confirm("确定要删除吗?")){
                                $.post("${pageContext.request.contextPath}/student/deleteStudentByList.do",{"stuIds":idStr},
                                function (data) {
                                    alert(data.msg);
                                    $("#studentDataGrid").datagrid('reload');
                                })
                            }

                        }
                    }
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'stuName',title:'姓名'},
                    {field:'stuAge',title:'年龄'},
                    {field:'stuGender',title:'性别'},
                 {field:'studentGrade',title:'年级',formatter:function (value, row, index) {
                        return value.stuGradeName;
                    }},
                    {field:'stuPhone',title:'电话'},
                    {field:'stuEmail',title:'邮箱'},
                    {field:'stuQQ',title:'QQ'},
                   {field:'agency',title:'所属机构',formatter:function (value, row, index) {
                        return value.agencyName;
                    }},
                    {field:'registerTime',title:'注册日期',formatter:function (value, row, index) {
                        var date = new Date(value);
                        var Y = date.getFullYear();
                        var M = date.getMonth() + 1;
                        var D = date.getDate();

                        return Y + "-" + M + "-" + D;
                    }},
                    {field:'stuID',title:'操作列',formatter:function (value, row, index) {
                        var queryStudent= "<button onclick='queryStudent("+value+")'>详情</button>";
                        var updateStudent = "<button onclick='updateStudent("+value+")'>修改</button>";
                        var deleteStudent = "<button onclick='deleteStudent("+value+")'>删除</button>";
                        return queryStudent + updateStudent + deleteStudent;
                    }}
                ]]
            })
            //查询
            $("#queryStudentButton").click(function () {
                var stuName = $("#queryStuName").val();
                var agencyName = $("#queryAgencyName").val();
                var stuAgeMin = $("#queryStuAgeMin").val();
                var stuAgeMax = $("#queryStuAgeMax").val();
                $("#studentDataGrid").datagrid('load',{
                    "stuName":stuName,
                    "agencyName":agencyName,
                    "stuAgeMin":stuAgeMin,
                    "stuAgeMax":stuAgeMax
                })
            })
            //添加
            $("#saveAddStudentButton").click(function () {
                var student = $("#addStudentForm").serialize();
                $.post("${pageContext.request.contextPath}/student/addStudent.do",student,
                function (data) {
                    alert(data.msg);
                    $("#studentDataGrid").datagrid('reload');
                    $("#addStudentWindow").window('close');
                })
            })
            //修改
            $("#saveUpdateStudentButton").click(function () {
                var student = $("#updateStudentForm").serialize();
                $.post("${pageContext.request.contextPath}/student/updateStudent.do",student,
                function (data) {
                    alert(data.msg);
                    $("#studentDataGrid").datagrid('reload');
                    $("#updateStudentWindow").window('close');
                })
            })

        })

        function queryStudent(id) {
            $.get("${pageContext.request.contextPath}/student/queryStudentByStuId.do",{"stuId":id},
                function (data) {
                    $("#queryStudentName").textbox('setValue', data.stuName);
                    $("#queryStudentId").val(data.stuID);
                    $("#queryStuQQ").textbox('setValue', data.stuQQ);
                    $("#queryStuAge").textbox('setValue',data.stuAge);
                    if( data.stuGender=='男'){
                        $("#queryStuGender1").attr("checked", true);
                    }else{
                        $("#queryStuGender2").attr("checked", true);
                    }
                    $("#queryStuEmail").textbox('setValue', data.stuEmail);
                    $("#queryStuPhone").textbox('setValue', data.stuPhone);
                    $("#queryStudentGrade").textbox('setValue', data.studentGrade.stuGradeName);
                    $("#queryAgency").textbox('setValue', data.agency.agencyName);
            })
            $("#queryStudentWindow").window('open');
        }
        function updateStudent(id) {
           $.get("${pageContext.request.contextPath}/student/queryStudentByStuId.do",{"stuId":id},
           function (data) {
               $("#updateStudentName").textbox('setValue', data.stuName);
               $("#updateStudentId").val(data.stuID);
               $("#updateStuQQ").textbox('setValue', data.stuQQ);
               $("#updateStuAge").textbox('setValue',data.stuAge);
               if( data.stuGender=='男'){
                   $("#updateStuGender1").attr("checked", true);
               }else{
                   $("#updateStuGender2").attr("checked", true);
               }
               $("#updateStuEmail").textbox('setValue', data.stuEmail);
               $("#updateStuPhone").textbox('setValue', data.stuPhone);
               $.get("${pageContext.request.contextPath}/studentGrade/queryStudentGrade",function (studentGrade) {

                   $("#updateStudentGrade").combobox({
                       valueField: 'stuGradeID',
                       textField: 'stuGradeName',
                       data: studentGrade,
                       onLoadSuccess: function () {
                           $(this).combobox("select", data.studentGrade.stuGradeID);
                       }
                   });
               })
               $.get("${pageContext.request.contextPath}/agency/queryAgency",function (agency) {

                       $("#updateAgency").combobox({
                           valueField:'agencyID',
                           textField:'agencyName',
                           data:agency,
                           onLoadSuccess:function () {
                               $(this).combobox("select",  data.agency.agencyID);
                           }
                       })
               })

           })
            $("#updateStudentWindow").window('open');

        }
        function deleteStudent(id) {
            if(confirm("确定要删除吗？")){
                $.post("${pageContext.request.contextPath}/student/deleteStudentById.do",{"stuId":id},
                    function (data) {
                        alert(data.msg);
                        $("#studentDataGrid").datagrid('reload');
                    })
            }

        }
    </script>

</head>
<body>
    <div>
        <form id="queryStudentForm"  method="get">
           姓名：<input type="text" name="stuName" id="queryStuName">
            所属机构：<input type="text" name="agency.agencyName" id="queryAgencyName">
            年龄：<input type="text" name="stuAgeMin" style="width: 50px;" id="queryStuAgeMin">-<input type="text" name="stuAgeMax" id="queryStuAgeMax" style="width: 50px;">
            <a id="queryStudentButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>
    </div>
    <table id="studentDataGrid"></table>

    <%--修改--%>
    <div class="easyui-window" id="queryStudentWindow"
         style="top: 10%;width: 600px;height: 500px; padding: 60px 120px;"
         title="学生详情" closed="true">
        <form>
            <table>
                <tr>
                    <td>
                        <h5 align="center">学生信息</h5>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="queryStudentName" />
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td>
                        <input class="easyui-textbox" id="queryStuAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <input type="radio"  name="stuGender" value="男" id="queryStuGender1"/>男
                        <input type="radio"  name="stuGender" value="女" id="queryStuGender2"/>女

                    </td>
                </tr>
                <tr>
                    <td>年级:</td>
                    <td>
                        <input id="queryStudentGrade"  class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td>
                        <input class="easyui-textbox" id="queryStuPhone" name="stuPhone"/>
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input class="easyui-textbox" id="queryStuEmail" name="stuEmail"/></td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="queryStuQQ" name="stuQQ"/>
                    </td>
                </tr>
                <tr>
                    <td>所属机构：</td>
                    <td>
                        <input id="queryAgency"  class="easyui-textbox"/>
                    </td>
                </tr>
            </table>
        </form>
        <button id="saveQueryStudentButton">保存</button>
    </div>

    <%--添加--%>
    <div class="easyui-window" id="addStudentWindow"
         style="top: 10%;width: 600px;height: 500px; padding: 60px 120px;"
         title="添加学生" closed="true">
        <form id="addStudentForm">
            <table>
                <tr>
                    <td>
                        <h5 align="center">添加学生信息</h5>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="addStudentName" name="stuName"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td>
                        <input class="easyui-textbox" id="addStuAge" name="stuAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <input type="radio"  name="stuGender" value="男" id="addStuGender1" checked/>男
                        <input type="radio"  name="stuGender" value="女" id="addStuGender2"/>女

                    </td>
                </tr>
                <tr>
                    <td>年级:</td>
                    <td>
                        <input id="addStudentGrade" name="studentGrade.stuGradeID"/>
                    </td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td>
                        <input class="easyui-textbox" id="addStuPhone" name="stuPhone"/>
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input class="easyui-textbox" id="addStuEmail" name="stuEmail"/></td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="addStuQQ" name="stuQQ"/>
                    </td>
                </tr>
                <tr>
                    <td>所属机构：</td>
                    <td>
                        <input id="addAgency" name="agency.agencyID"/>
                    </td>
                </tr>
            </table>
        </form>
        <button id="saveAddStudentButton">保存</button>
    </div>

    <%--修改--%>
    <div class="easyui-window" id="updateStudentWindow"
         style="top: 10%;width: 600px;height: 500px; padding: 60px 120px;"
         title="修改学生信息" closed="true">
        <form id="updateStudentForm">
            <table>
                <tr>
                    <td>
                        <h5 align="center">修改学生信息</h5>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="updateStudentName" name="stuName"/>
                        <input type="hidden" name="stuID" id="updateStudentId">
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td>
                        <input class="easyui-textbox" id="updateStuAge" name="stuAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <input type="radio" id="updateStuGender1" name="stuGender" value="男"/>男
                        <input type="radio" id="updateStuGender2" name="stuGender" value="女"/>女
                    </td>
                </tr>
                <tr>
                    <td>年级:</td>
                    <td>
                        <input id="updateStudentGrade"  name="studentGrade.stuGradeID"/>
                    </td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td>
                        <input class="easyui-textbox" id="updateStuPhone" name="stuPhone"/>
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input class="easyui-textbox" id="updateStuEmail" name="stuEmail"/></td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="updateStuQQ" name="stuQQ"/>
                    </td>
                </tr>
                <tr>
                    <td>所属机构：</td>
                    <td>
                        <input id="updateAgency" class="easyui-textbox" readonly/>
                    </td>
                </tr>
            </table>
        </form>
        <button id="saveUpdateStudentButton">保存</button>
    </div>
</body>
</html>
