<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校区员工列表</title>
    <jsp:include page="../common.jsp"/>
    <script type="text/javascript">
        function returnAgencyList() {
            window.location.href = "${pageContext.request.contextPath}/agency/toAgency";
        }
    </script>
</head>
<body>
    <button class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="returnAgencyList();">返回上一级</button>
    <h3 align="center">校区员工列表</h3>
    <div id="aeDataGrid"></div>

    <%--添加校区员工window--%>
    <div class="easyui-window" id="addAEWindow" title="添加校区员工" data-options="closed:true" style="width: 600px;height: 400px;top:10px; left: 20px;padding: 50px;">
        <form id="addAEForm">
            <table>
                <tr>
                    <td>登录名：</td>
                    <td><input name="loginUser.loginName" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input name="loginUser.loginPassword" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>身份：</td>
                    <td>
                        <input name="loginUser.userRole.userRoleID" id="loginUserInput" style="width: 140px;"/>
                        <input type="text" id="agency" name="agency.agencyID" value="${agency.agencyID}"/>
                    </td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td><input name="aeName" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>电话：</td>
                    <td><input name="aePhone" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>QQ号：</td>
                    <td><input name="aeQQ" class="easyui-textbox"/></td>
                </tr>
            </table>
        </form>
        <div align="center"><button id="addAEButton">保存</button></div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#addAEButton").click(function () {
                var ae = $("#addAEForm").serialize();
                $.post("${pageContext.request.contextPath}/ae/addAE.do",ae,function (result) {
                    alert(result.msg);
                    $("#addAEWindow").window("close");
                    $("#aeDataGrid").datagrid("reload");
                    $("#addAEForm").form("clear");
                    $("#agency").val(${agency.agencyID});
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(function () {
            $("#aeDataGrid").datagrid({
                url:"${pageContext.request.contextPath}/ae/aeList.do?agencyID=${agency.agencyID}",
                pagination:true,
                striped:true,
                method:"get",
                toolbar:[
                    {
                        text:"新增校区员工",
                        iconCls:"icon-add",
                        handler:function () {
                            $("#addAEForm").form("clear");
                            $("#agency").val(${agency.agencyID});
                            $("#loginUserInput").combobox({
                                valueField:"id",
                                textField:"text",
                                data:[
                                    {"id":-1,"text":"==请选择=="},
                                    {"id":6,"text":"校区经理"},
                                    {"id":7,"text":"校区员工"},
                                ],
                                onLoadSuccess:function () {
                                    $(this).combobox("select", -1);
                                }
                            });
                            $("#addAEWindow").window("open");
                        }
                    },
                ],
                onLoadSuccess:function () {
                    $(this).datagrid("fixRowHeight");
                    $("button[for=dg]").linkbutton();
                },
                columns:[[
                    {title:"校区名称",field:"agencyName",formatter:function (value, row, index) {
                        return row.agency.agencyName;
                    }},
                    {title:"员工姓名",field:"aeName"},
                    {title:"登录名",field:"loginUserName",formatter:function (value, row, index) {
                        return row.loginUser.loginName;
                    }},
                    {title:"登录名",field:"userRoleName",formatter:function (value, row, index) {
                        return row.loginUser.userRole.userRoleName;
                    }},
                ]],
            });
        });
    </script>
</body>
</html>
