<%--
  Created by IntelliJ IDEA.
  User: 大聪
  Date: 2018/1/9
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../common.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#dg").datagrid({
                url:'${pageContext.request.contextPath}/agency/findAll.do',
                method:'GET',
                rownumbers:true,
                striped:true,
                pagination:true,
                checkOnSelect:false,
                title:'校区',
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
                        handler:function () {
                            $("#addAgencyForm").form("clear");
                            $("#addAgencyWindow").window("open");
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
                    {field:'agencyName',title:'机构名称'},
                    {field:'contactName',title:'联系人姓名'},
                    {field:'contactPhone',title:'联系人电话'},
                    {field:'contactEmail',title:'联系人邮箱'},
                    {field:'stuNumber',title:'学生人数'},
                    {field:'contactQQ',title:'联系人QQ'},
                    {field:'loginUser',title:'登录名',formatter:function (value,row,index) {
                        return value.loginName;
                    }},
                    {field:'agencyID',title:'操作列',formatter:function (value,row,index) {
                        var queryAgencyById="<button onclick='queryAgencyById("+value+")'>详情</button>";
                        var updateAgency="<button onclick='updateAgency("+value+")'>修改</button>";
                        var deleteAgency="<button onclick='deleteAgency("+value+")'>删除</button>";
                        return queryAgencyById+" "+updateAgency+" "+deleteAgency;
                    }}
                ]]
            });
            $("#saveAddAgencyButton").click(function () {
                var agency = $("#addAgencyForm").serialize();
                $.post("${pageContext.request.contextPath}/agency/addAgency.do",
                    agency,function (data) {
                        alert(data.msg);
                        $("#addAgencyWindow").window("close");
                        $("#dg").datagrid("reload");
                    })
            })
            $("#saveUpdateAgencyButton").click(function () {
                var agency = $("#updateAgencyForm").serialize();
                $.post("${pageContext.request.contextPath}/agency/updateAgency.do",
                    agency,function (data) {
                        alert(data.msg);
                        $("#updateAgencyWindow").window("close");
                        $("#dg").datagrid("reload");
                    })
            })
            $("#queryAgencyButton").click(function () {
                var agencyName = $("#queryAgencyName").val();
                $("#dg").datagrid("load",{
                    "agencyName":agencyName
                })
            })
        })
        function queryAgencyById(id) {
            $.get("${pageContext.request.contextPath}/agency/queryAgencyById.do",
                {"id":id},function (data) {
                    $("#agencyName").textbox("setValue",data.agencyName);
                    $("#contactName").textbox("setValue",data.contactName);
                    $("#contactPhone").textbox("setValue",data.contactPhone);
                    $("#contactEmail").textbox("setValue",data.contactEmail);
                    $("#stuNumber").textbox("setValue",data.stuNumber);
                    $("#contactQQ").textbox("setValue",data.contactQQ);
                    $("#loginName").textbox("setValue",data.loginUser.loginName);
                    $("#loginPassword").textbox("setValue",data.loginUser.loginPassword);
                })
            $("#queryAgencyByIdWindow").window("open");

        }
        function updateAgency(id) {
            $.get("${pageContext.request.contextPath}/agency/queryAgencyById.do",
                {"id":id},function (data) {
                    $("#updateAgencyName").textbox("setValue",data.agencyName);
                    $("#updateAgencyId").val(data.agencyID);
                    $("#updateContactName").textbox("setValue",data.contactName);
                    $("#updateContactPhone").textbox("setValue",data.contactPhone);
                    $("#updateContactEmail").textbox("setValue",data.contactEmail);
                    $("#updateStuNumber").textbox("setValue",data.stuNumber);
                    $("#updateContactQQ").textbox("setValue",data.contactQQ);
                    $("#updateLoginName").textbox("setValue",data.loginUser.loginName);
                    $("#updateLoginUserId").val(data.loginUser.loginUserID)
                    $("#updateLoginPassword").textbox("setValue",data.loginUser.loginPassword);
                })
            $("#updateAgencyWindow").window("open");
        }
        function deleteAgency(id) {
            alert("删除 id=" + id);
        }
    </script>
</head>
<body>
    <div>
        <form id="queryAgencyForm">
            企业名称：<input id="queryAgencyName" name="agencyName" placeholder="输入企业名称搜索" />
            <a id="queryAgencyButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>
    </div>
    <table id="dg"></table>
    <!--详情window框-->
    <div id="queryAgencyByIdWindow" class="easyui-window"
         style="width: 600px;height: 400px;padding: 60px 120px;top: 10%;left: 20%;"
         title="校区详情" closed="true">
        <form id="queryAgencyByIdForm">
            <div>
                机构&nbsp;&nbsp;&nbsp;名称：<input id="agencyName" class="easyui-textbox" readonly/>
            </div>
            <div>
                联系人姓名：<input id="contactName" class="easyui-textbox" readonly/>
            </div>
            <div>
                联系人电话：<input id="contactPhone" class="easyui-textbox" readonly/>
            </div>
            <div>
                联系人邮箱：<input id="contactEmail" class="easyui-textbox" readonly/>
            </div>
            <div>
                学生&nbsp;&nbsp;&nbsp;人数：<input id="stuNumber" class="easyui-textbox" readonly/>
            </div>
            <div>
                联系人&nbsp;QQ：<input id="contactQQ" class="easyui-textbox" readonly/>
            </div>
            <div>
                登&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;名&nbsp;：<input id="loginName" class="easyui-textbox" readonly/>
            </div>
            <div>
                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="loginPassword" class="easyui-textbox" readonly/>
            </div>
        </form>
    </div>
    <!--添加window框-->
    <div id="addAgencyWindow" class="easyui-window"
         style="width: 600px;height: 400px;padding: 60px 120px;top: 10%;left: 20%;"
         title="校区添加" closed="true">
        <form id="addAgencyForm">
            <div>
                机构&nbsp;&nbsp;&nbsp;名称：<input id="addAgencyName" name="agencyName" class="easyui-textbox"/>
            </div>
            <tr>
                <td>用户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色:</td>
                <td>
                    <select  name="loginUser.userRole.userRoleID" style="width: 170px;">
                        <option value="1">校区经理</option>
                        <option value="2">校区员工</option>
                    </select>
                </td>
            </tr>
            <div>
                联系人姓名：<input id="addContactName" name="contactName" class="easyui-textbox"/>
            </div>
            <div>
                联系人电话：<input id="addContactPhone" name="contactPhone" class="easyui-textbox"/>
            </div>
            <div>
                联系人邮箱：<input id="addContactEmail" name="contactEmail" class="easyui-textbox"/>
            </div>
            <div>
                学生&nbsp;&nbsp;&nbsp;人数：<input id="addStuNumber" name="stuNumber" class="easyui-textbox"/>
            </div>
            <div>
                联系人&nbsp;QQ：<input id="addContactQQ" name="contactQQ" class="easyui-textbox"/>
            </div>
            <div>
                登&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;名&nbsp;：<input id="addLoginName" name="loginName" class="easyui-textbox"/>
            </div>
            <div>
                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="addLoginPassword" name="loginPassword" class="easyui-textbox"/>
            </div>
        </form>
        <button id="saveAddAgencyButton">保存</button>
    </div>
    <!--修改window框-->
    <div id="updateAgencyWindow" class="easyui-window"
         style="width: 600px;height: 400px;padding: 60px 120px;top: 10%;left: 20%;"
         title="校区修改" closed="true">
        <form id="updateAgencyForm">
            <div>
                机构&nbsp;&nbsp;&nbsp;名称：
                <input id="updateAgencyName" name="agencyName" class="easyui-textbox" readonly/>
                <input type="hidden" name="agencyID" id="updateAgencyId" />
            </div>
            <tr>
                <td>用户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色:</td>
                <td>
                    <select  name="loginUser.userRole.userRoleID" style="width: 170px;">
                        <option value="6">校区经理</option>
                        <option value="7">校区员工</option>
                    </select>
                </td>
            </tr>
            <div>
                联系人姓名：<input id="updateContactName" name="contactName" class="easyui-textbox"/>
            </div>
            <div>
                联系人电话：<input id="updateContactPhone" name="contactPhone" class="easyui-textbox"/>
            </div>
            <div>
                联系人邮箱：<input id="updateContactEmail" name="contactEmail" class="easyui-textbox"/>
            </div>
            <div>
                学生&nbsp;&nbsp;&nbsp;人数：<input id="updateStuNumber" name="stuNumber" class="easyui-textbox"/>
            </div>
            <div>
                联系人&nbsp;QQ：<input id="updateContactQQ" name="contactQQ" class="easyui-textbox"/>
            </div>
            <div>
                登&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;名&nbsp;：
                <input id="updateLoginName" name="loginName" class="easyui-textbox"/>
                <input type="hidden" name="loginUserId" id="updateLoginUserId" />
            </div>
            <div>
                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="updateLoginPassword" name="loginPassword" class="easyui-textbox"/>
            </div>
        </form>
        <button id="saveUpdateAgencyButton">保存</button>
    </div>
</body>
</html>
