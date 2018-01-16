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
                    }
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'agencyName',title:'机构名称'},
                    {field:'contactName',title:'联系人姓名'},
                    {field:'contactPhone',title:'联系人电话'},
                    {field:'contactEmail',title:'联系人邮箱'},
                    {field:'stuNumber',title:'学生人数'},
                    {field:'contactQQ',title:'联系人QQ'
                    },
                    {field:'agencyID',title:'操作列',formatter:function (value,row,index) {
                        var queryAgencyById="<button onclick='queryAgencyById("+value+")'>详情</button>";
                        var updateAgency="<button onclick='updateAgency("+value+")'>修改</button>";
                        return queryAgencyById+" "+updateAgency;
                    }}
                ]]
            });
            $("#saveAddAgencyButton").click(function () {
                //
                var agency1 = true;

                var agencyName = $("#addAgencyName").val();
                if(agencyName==null || agencyName==''){
                    alert("用户名不能为空");
                    agency1 = false;
                    return;
                }
                var addContactName=$("#addContactName").val();
                if(addContactName==null || addContactName==''){
                    alert("联系人姓名不能为空");
                    agency1 = false;
                    return;
                }
                var addContactPhone=$("#addContactPhone").val();
                if(addContactPhone==null || addContactPhone==''){
                    alert("联系人电话不能为空");
                    agency1 = false;
                    return;
                }
                var addContactEmail=$("#addContactEmail").val();
                if(addContactEmail==null || addContactEmail==''){
                    alert("联系人邮箱不能为空");
                    agency1 = false;
                    return;
                }
                var addStuNumber=$("#addStuNumber").val();
                if(addStuNumber==null || addStuNumber==''){
                    alert("学生人数不能为空");
                    agency1 = false;
                    return;
                }
                var addContactQQ=$("#addContactQQ").val();
                if(addContactQQ==null || addContactQQ==''){
                    alert("联系人QQ不能为空");
                    agency1 = false;
                    return;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/agency/deExists",
                    type:"GET",
                    data:{"agencyName":agencyName},
                    dataType:"text",
                    success:function (data) {
                        if(data=="success"){
                            alert("名称重复！");
                        }else{
                            if(agency1){
                                var agency = $("#addAgencyForm").serialize();
                                $.post("${pageContext.request.contextPath}/agency/addAgency.do",
                                    agency,function (data) {
                                        alert(data.msg);
                                        $("#addAgencyWindow").window("close");
                                        $("#dg").datagrid("reload");
                                    })
                            }
                        }
                    }
                });


                //

            })
            $("#saveUpdateAgencyButton").click(function () {

                var agency1 = true;

                var updateContactName=$("#updateContactName").val();
                if(updateContactName==null || updateContactName==''){
                    alert("联系人姓名不能为空");
                    agency1 = false;
                    return;
                }
                var updateContactPhone=$("#updateContactPhone").val();
                if(updateContactPhone==null || updateContactPhone==''){
                    alert("联系人电话不能为空");
                    agency1 = false;
                    return;
                }
                var updateContactEmail=$("#updateContactEmail").val();
                if(updateContactEmail==null || updateContactEmail==''){
                    alert("联系人邮箱不能为空");
                    agency1 = false;
                    return;
                }
                var updateStuNumber=$("#updateStuNumber").val();
                if(updateStuNumber==null || updateStuNumber==''){
                    alert("学生人数不能为空");
                    agency1 = false;
                    return;
                }
                var updateContactQQ=$("#updateContactQQ").val();
                if(updateContactQQ==null || updateContactQQ==''){
                    alert("联系人QQ不能为空");
                    agency1 = false;
                    return;
                }

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
                })
            $("#updateAgencyWindow").window("open");
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
            <table>
                <tr>
                    <td>
                        机构名称：
                    </td>
                    <td>
                        <input id="agencyName" class="easyui-textbox" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人姓名：
                    </td>
                    <td>
                        <input id="contactName" class="easyui-textbox" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人电话：
                    </td>
                    <td>
                        <input id="contactPhone" class="easyui-textbox" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人邮箱：
                    </td>
                    <td>
                        <input id="contactEmail" class="easyui-textbox" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                        学生人数：
                    </td>
                    <td>
                        <input id="stuNumber" class="easyui-textbox" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人&nbsp;QQ：
                    </td>
                    <td>
                        <input id="contactQQ" class="easyui-textbox" readonly/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--添加window框-->
    <div id="addAgencyWindow" class="easyui-window"
         style="width: 600px;height: 400px;padding: 60px 120px;top: 10%;left: 20%;"
         title="校区添加" closed="true">
        <form id="addAgencyForm">
            <table>
                <tr>
                    <td>
                        机构&nbsp;&nbsp;&nbsp;名称：
                    </td>
                    <td>
                        <input id="addAgencyName" name="agencyName" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人姓名：
                    </td>
                    <td>
                        <input id="addContactName" name="contactName" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人电话：
                    </td>
                    <td>
                        <input id="addContactPhone" name="contactPhone" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人邮箱：
                    </td>
                    <td>
                        <input id="addContactEmail" name="contactEmail" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        学生&nbsp;&nbsp;&nbsp;人数：
                    </td>
                    <td>
                        <input id="addStuNumber" name="stuNumber" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人&nbsp;QQ：
                    </td>
                    <td>
                        <input id="addContactQQ" name="contactQQ" class="easyui-textbox"/>
                    </td>
                </tr>
            </table>
        </form>
        <div align="center">
            <button id="saveAddAgencyButton">保存</button>
        </div>
    </div>
    <!--修改window框-->
    <div id="updateAgencyWindow" class="easyui-window"
         style="width: 600px;height: 400px;padding: 60px 120px;top: 10%;left: 20%;"
         title="校区修改" closed="true">
        <form id="updateAgencyForm">
            <table>
                <tr>
                    <td>
                        机构&nbsp;&nbsp;&nbsp;名称：
                    </td>
                    <td>
                        <input id="updateAgencyName" name="agencyName" class="easyui-textbox" readonly/>
                        <input type="hidden" name="agencyID" id="updateAgencyId" />
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人姓名：
                    </td>
                    <td>
                        <input id="updateContactName" name="contactName" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人电话：
                    </td>
                    <td>
                        <input id="updateContactPhone" name="contactPhone" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人邮箱：
                    </td>
                    <td>
                        <input id="updateContactEmail" name="contactEmail" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        学生&nbsp;&nbsp;&nbsp;人数：
                    </td>
                    <td>
                        <input id="updateStuNumber" name="stuNumber" class="easyui-textbox"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        联系人&nbsp;QQ：
                    </td>
                    <td>
                        <input id="updateContactQQ" name="contactQQ" class="easyui-textbox"/>
                    </td>
                </tr>
            </table>
        </form>
        <div align="center">
            <button id="saveUpdateAgencyButton">保存</button>
        </div>
    </div>
</body>
</html>
