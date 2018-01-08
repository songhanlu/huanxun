<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/4
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>环迅后台管理系统-主页</title>
    <jsp:include page="../common.jsp"/>
    <link href="${pageContext.request.contextPath}/statics/css/myCss.css" rel="stylesheet"/>
</head>
<body>
    <div class="easyui-layout" style="width: 100%;height: 100%;">
        <div region="north" style="height: 15%;">
            <h2>
                环迅后台管理系统-主页,欢迎你${sessionScope.currentEmployee.employeeName},身份:${sessionScope.loginUser.userRole.userRoleName}
            </h2>
        </div>
        <div region="west" style="width: 15%;" title="菜单栏">
            <div class="easyui-accordion" style="width: 100%;height: 100%;" data-options="{fit:true,selected:-1}">
                <div title="用户模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/employee/toEmployee.html">员工信息管理</a></li>
                        <li>&nbsp;</li>
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/student/toStudent.html">学生信息管理</a></li>
                    </ul>
                </div>
                <div title="外教模块"></div>
                <div title="课程模块"></div>
                <div title="教材模块"></div>
                <div title="课表模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="#">查询课表</a></li>
                        <li>&nbsp;</li>
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/classArrange/toClassArrange.html">进入排课功能</a></li>
                    </ul>
                </div>
                <div title="校区模块"></div>
                <div title="数据统计模块"></div>
            </div>
        </div>
        <div region="center">
            <div id="indexTabs" class="easyui-tabs" style="width: 100%;height: 100%;">
                <div title="欢迎界面">
                    欢迎
                </div>
            </div>
        </div>
    </div>

    <%--主页标签控制--%>
    <script type="text/javascript">
        $(function () {
            $("ul[myAttr]>li>a").click(function () {
                var url = $(this).attr("url");
                var title = $(this).text();
                var content = "<iframe style='height: 99%;width: 99%;' src='"+url+"'></iframe>";
                if(!$("#indexTabs").tabs("exists",title)){
                    $("#indexTabs").tabs("add",{
                        title:title,
                        closable:true,
                        content:content,
                    })
                }else{
                    $("#indexTabs").tabs("select", title);
                }
            });
        });
    </script>
</body>
</html>
