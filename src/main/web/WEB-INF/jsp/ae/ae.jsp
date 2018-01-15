<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/15
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校区员工列表</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
    校区员工列表
    <div id="aeDataGrid"></div>
<script type="text/javascript">
    $(function () {
        $("#aeDataGrid").datagrid({
            url:"${pageContext.request.contextPath}/ae/aeList.do?agencyID=1",
            pagination:true,
            striped:true,
            method:"get",
            columns:[[
                {title:"校区名称",field:"agencyName",formatter:function (value, row, index) {
                    return row.agency.agencyName;
                }},
                {title:"员工姓名",field:"aeName"},
                {title:"登录名",field:"loginUserName",formatter:function (value, row, index) {
                    console.log(row);
                    return row.loginUser.loginName;
                }},
            ]],
        });
    });
</script>
</body>
</html>
