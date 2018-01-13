<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工管理界面</title>
    <%@include file="../common.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#employeeDataGrid").datagrid({
                url:"${pageContext.request.contextPath}/employee/queryEmployee.do",
                method:'GET',
                pagination:true,
                rownumbers:true,
                striped:true,
                checkOnSelect:false,
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
                        handler:function () {

                            $("#addEmployeeWindow").window('open');
                        }
                    },
                    {
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            var checkEmployee = $("#employeeDataGrid").datagrid("getChecked");
                            if(checkEmployee==null||checkEmployee.length<=0){
                                alert("请选择要删除的员工");
                                return;
                            }
                            var idStr = "";
                            $.each(checkEmployee,function (index, item) {
                                idStr = idStr + item.employeeID + ",";
                            })
                            if(confirm("确定要删除吗？")){
                                $.post("${pageContext.request.contextPath}/employee/deleteEmployeeByIds.do",
                                    {"employeeIds":idStr},function (data) {
                                        alert(data.msg);
                                        $("#employeeDataGrid").datagrid('reload');
                                    })
                            }


                        }
                    }
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'employeeName',title:'姓名'},
                    {field:'employeeAge',title:'年龄'},
                    {field:'employeeGender',title:'性别'},
                    {field:'email',title:'邮箱'},
                    {field:'phone',title:'电话'},
                    {field:'employeeQQ',title:'QQ'},
                    {field:'loginUser',title:'登录名',formatter:function (value, row, index) {
                        return value.loginName;
                    }},
                    {field:'userRole',title:'角色',formatter:function (value, row, index) {
                        return row.loginUser.userRole.userRoleName;
                    }},
                    {field:'employeeID',title:'操作列',formatter:function (value, row, index) {
                        var queryEmployee = "<button onclick='queryEmployee("+value+")'>详情</button>";
                        var updateEmployee = "<button onclick='updateEmployee("+value+")'>修改</button>";
                        var deleteEmployee = "<button onclick='deleteEmployee("+value+")'>删除</button>";
                        return queryEmployee + updateEmployee + deleteEmployee;
                    }}
                ]]
            });
            //查询所有的用户角色信息
            $.get("${pageContext.request.contextPath}/userRole/queryUserRole.do",function (userRole) {
                var userRole1 = $.parseJSON('{"userRoleID":-1,"userRoleName":"--请选择--"}');
                userRole.push(userRole1);
                $("#queryUserRoleNameCombobx").combobox({
                    valueField:'userRoleID',
                    textField:'userRoleName',
                    data:userRole,
                    onLoadSuccess:function () {
                        $(this).combobox('select', -1);
                    },
                    onSelect:function (userRole) {
                        var userRoleId = userRole.userRoleID;
                        $.get("${pageContext.request.contextPath}/employee/queryEmployeeByUserRoleId.do",
                            {"userRoleId":userRoleId},function (employee) {
                                var employee1 = $.parseJSON('{"employeeID":-1,"employeeName":"--请选择--"}');
                                employee.push(employee1);
                                $("#queryEmployeeNameCombobx").combobox({
                                    valueField:'employeeID',
                                    textField:'employeeName',
                                    data:employee,
                                    onLoadSuccess:function () {
                                        $(this).combobox('select', -1);
                                    }
                                })
                            })
                    }
                })
            })
            $("#queryEmployeeButton").click(function () {
                var userRoleId = $("#queryUserRoleNameCombobx").val();
                var employeeId = $("#queryEmployeeNameCombobx").val();
                if(isNaN(userRoleId)){
                    alert("该角色不存在");
                    return;
                }

                if(isNaN(employeeId)){
                    alert("该姓名不存在");
                    return;
                }
                $("#employeeDataGrid").datagrid('load', {
                    "userRoleId": userRoleId,
                    "employeeId": employeeId
                });

            })
            //添加
            $("#saveAddEmployeeButton").click(function () {

                var employee = $("#addEmployeeForm").serialize();
                $.post("${pageContext.request.contextPath}/employee/addEmployee.do",employee,
                function (data) {
                    alert(data.msg);
                    $("#employeeDataGrid").datagrid('reload');
                    $("#addEmployeeWindow").window('close');
                })
            })
            //修改2
            $("#saveUpdateEmployeeButton").click(function () {
                var employee = $("#updateEmployeeForm").serialize();
                $.post("${pageContext.request.contextPath}/employee/updateEmployee.do",employee,
                function (data) {
                    alert(data.msg);
                    $("#employeeDataGrid").datagrid('reload');
                    $("#updateEmployeeWindow").window('close');
                })


            })
        });
        function queryEmployee(id) {
            $.get("${pageContext.request.contextPath}/employee/queryEmployeeByEmployeeId.do",
                {"employeeId":id},function (data) {
                    $("#queryEmployeeLoginName").textbox('setValue',data.loginUser.loginName);
                    $("#queryEmployeeName").textbox('setValue', data.employeeName);
                    $("#queryEmployeeAge").textbox('setValue', data.employeeAge);
                    $("#queryEmployeeGender").textbox('setValue', data.employeeGender);
                    $("#queryEmployeeEmail").textbox('setValue', data.email);
                    $("#queryEmployeeQQ").textbox('setValue', data.employeeQQ);
                    $("#queryEmployeePhone").textbox('setValue', data.phone);
                })
            $("#queryEmployeeByIdWindow").window('open');
        }
        function updateEmployee(id) {
            $.get("${pageContext.request.contextPath}/employee/queryEmployeeByEmployeeId.do",
                {"employeeId":id},function (data) {
                    $("#updateEmployeeLoginName").textbox('setValue',data.loginUser.loginName);
                    $("#updateEmployeeName").textbox('setValue', data.employeeName);
                    $("#updateEmployeeAge").textbox('setValue', data.employeeAge);
                    $("#updateEmployeeGender").textbox('setValue', data.employeeGender);
                    $("#updateEmployeeEmail").textbox('setValue', data.email);
                    $("#updateEmployeeQQ").textbox('setValue', data.employeeQQ);
                    $("#updateEmployeePhone").textbox('setValue', data.phone);
                    $("#updateEmployeeLoginPassword").textbox('setValue',data.loginUser.loginPassword)
                    $("#updateEmployeeId").val(data.employeeID);
                    $("#updateLoginUserId").val(data.loginUser.loginUserID);
                })
            $("#updateEmployeeWindow").window('open');
        }
        function deleteEmployee(id) {
            if(confirm("确定要删除吗？")){
                $.post("${pageContext.request.contextPath}/employee/deleteEmployeeById.do",
                    {"employeeId":id},function (data) {
                        alert(data.msg);
                        $("#employeeDataGrid").datagrid('reload');
                    })
            }

        }
    </script>
</head>
<body>
    <div>
        <form>
            角色：<input id="queryUserRoleNameCombobx" name="userRoleID">
            姓名：<input id="queryEmployeeNameCombobx" name="employeeID">
            <a href="#" id="queryEmployeeButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>
    </div>
    <table id="employeeDataGrid"></table>
    <%--详情--%>
    <div class="easyui-window" id="queryEmployeeByIdWindow"
         style="width: 600px;height: 500px; padding: 60px 120px;top: 10px;left:20px;"
         title="员工详情" closed="true">
        <form>
            <table>
                <tr>
                    <td align="center">
                        <h3 align="center">员工信息详情</h3>
                    </td>
                </tr>
                <tr>
                    <td>登录名:</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeeLoginName"/>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeeName"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄：</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeeAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td><input class="easyui-textbox" id="queryEmployeeGender"/></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeeEmail"/>
                    </td>
                </tr>
                <tr>
                    <td>手机：</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeePhone"/>
                    </td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="queryEmployeeQQ"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <%--添加--%>
    <div class="easyui-window" id="addEmployeeWindow"
         style="width: 600px;height: 500px; padding: 60px 120px;"
         title="添加员工" closed="true">
        <form id="addEmployeeForm">
            <table>
                <tr>
                    <td>
                        <h5 align="center">添加员工信息</h5>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeName" name="employeeName"/>
                    </td>
                </tr>
                <tr>
                    <td>用户角色:</td>
                    <td>
                        <select  name="loginUser.userRole.userRoleID" style="width: 170px;">
                            <option value="1">系统管理员</option>
                            <option value="2">中国区经理</option>
                            <option value="3">外国区经理</option>
                            <option value="4">环迅员工</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>登录名:</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeLoginName" name="loginUserName"/>
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeLoginPassword" name="loginUserPassword"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄：</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeAge" name="employeeAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td><input class="easyui-textbox" id="addEmployeeGender" name="employeeGender"/></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeEmail" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>手机：</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeePhone" name="phone"/>
                    </td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="addEmployeeQQ" name="employeeQQ"/>
                    </td>
                </tr>
            </table>
        </form>
        <button id="saveAddEmployeeButton">保存</button>
    </div>
    <%--修改--%>
    <div class="easyui-window" id="updateEmployeeWindow"
         style="width: 600px;height: 500px; padding: 60px 120px;"
         title="添加员工" closed="true">
        <form id="updateEmployeeForm">
            <table>
                <tr>
                    <td>
                        <h5 align="center">修改员工信息</h5>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeName" name="employeeName"/>
                        <input type="hidden" id="updateEmployeeId" name="employeeID"/>
                    </td>
                </tr>
                <tr>
                    <td>用户角色:</td>
                    <td>
                        <select  name="loginUser.userRole.userRoleID" style="width: 170px;">
                            <option value="1">系统管理员</option>
                            <option value="2">中国区经理</option>
                            <option value="3">外国区经理</option>
                            <option value="4">环迅员工</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>登录名:</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeLoginName" name="loginUserName"/>
                        <input type="hidden" id="updateLoginUserId" name="loginUserId">
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeLoginPassword" name="loginUserPassword"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄：</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeAge" name="employeeAge"/>
                    </td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td><input class="easyui-textbox" id="updateEmployeeGender" name="employeeGender"/></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeEmail" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>手机：</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeePhone" name="phone"/>
                    </td>
                </tr>
                <tr>
                    <td>QQ：</td>
                    <td>
                        <input class="easyui-textbox" id="updateEmployeeQQ" name="employeeQQ"/>
                    </td>
                </tr>
            </table>
        </form>
        <button id="saveUpdateEmployeeButton">保存</button>
    </div>

</body>
</html>
