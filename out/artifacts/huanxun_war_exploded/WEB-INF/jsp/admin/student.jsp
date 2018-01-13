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
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-save',
                        handler:function () {
                            alert("添加");
                        }
                    },
                    {
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            alert("删除");
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
                    {field:'stuID',title:'操作列',formatter:function (value, row, index) {
                        var queryStudent= "<button onclick='queryStudent("+value+")'>详情</button>";
                        var updateStudent = "<button onclick='updateStudent("+value+")'>修改</button>";
                        var deleteStudent = "<button onclick='deleteStudent("+value+")'>删除</button>";
                        return queryStudent + updateStudent + deleteStudent;
                    }}
                ]]
            })

        })

        function queryStudent(id) {
            alert(id);
        }
        function updateStudent(id) {
            alert(id);
        }
        function deleteStudent(id) {
            alert(id);
        }
    </script>
</head>
<body>
    <div>
        <form id="queryStudentForm"  method="get">
           姓名：<input type="text" name="stuName">
            所属机构：<input type="text" name="agency.agencyName">
            年龄：<input type="text" name="stuAge" style="width: 50px;">-<input type="text" name="stuAge1" style="width: 50px;">
            <input type="submit"id="queryEmployeeButton"></input>
        </form>
    </div>
    <table id="studentDataGrid"></table>
</body>
</html>
