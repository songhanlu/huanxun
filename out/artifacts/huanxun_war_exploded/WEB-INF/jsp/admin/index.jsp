<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/4
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>环迅后台管理系统-主页</title>
    <jsp:include page="../common.jsp"/>
    <link href="${pageContext.request.contextPath}/statics/css/myCss.css" rel="stylesheet"/>
</head>
<body>
    <div class="easyui-layout" style="width: 100%;height: 100%;">
        <div region="north" style="height: 15%;">
            <h2 style="float: left;">
                环迅后台管理系统-主页,欢迎您:<span style="font-size: 16px;">${sessionScope.loginUser.userRole.userRoleName}</span>
            </h2>
            <c:if test="${sessionScope.loginUser.userRole.userRoleID eq 1 || sessionScope.loginUser.userRole.userRoleID eq 2 || sessionScope.loginUser.userRole.userRoleID eq 3 || sessionScope.loginUser.userRole.userRoleID eq 4}">
                <h2 style="float: right;">
                    用户名：${sessionScope.currentEmployee.employeeName}
                    <a href="${pageContext.request.contextPath}/common/logout">注销</a>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </h2>
            </c:if>
            <c:if test="${sessionScope.loginUser.userRole.userRoleID eq 6 || sessionScope.loginUser.userRole.userRoleID eq 7}">
                <h2 style="float: right;">
                    用户名：${sessionScope.currentAgencyEmployee.aeName}（${sessionScope.currentAgencyEmployee.agency.agencyName}）
                    <a href="${pageContext.request.contextPath}/common/logout">注销</a>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </h2>
            </c:if>
        </div>
        <div region="west" style="width: 15%;" title="菜单栏">
            <div class="easyui-accordion" style="width: 100%;height: 100%;" data-options="{fit:true,selected:-1}">
                <div title="用户模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/employee/toEmployee.do">员工信息管理</a></li>
                        <li>&nbsp;</li>
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/student/toStudent.do">学生信息管理</a></li>
                    </ul>
                </div>
                <div title="外教模块"></div>
                <div title="课程模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/lessonType/toLessonType.do">课程信息管理</a></li>
                        <li>&nbsp;</li>
                     </ul>
                </div>
                <div title="教材模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/book/tobook.do">教材信息管理</a></li>
                    </ul>
                </div>
                <div title="课表模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/classArrange/toClassArrange.do">进入课表管理</a></li>
                    </ul>
                </div>
                <div title="校区模块">
                    <ul myAttr="indexMenu">
                        <li><a class="easyui-linkbutton" url="${pageContext.request.contextPath}/agency/toAgency">查询校区</a></li>
                    </ul>
                </div>
                <div title="数据统计模块">
                    <div id="tree"></div>
                </div>
            </div>
        </div>
        <div region="center">
            <div id="indexTabs" class="easyui-tabs" style="width: 100%;height: 100%;">
                <%--<div title="欢迎界面">
                    欢迎
                </div>--%>
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
    <script type="text/javascript">
        $(function () {
            //加载数据统计菜单
            $("#tree").tree({
                data:[
                    {
                        id:1,
                        text:"员工数据统计",
                        children:[
                            {
                                id:11,
                                text:"员工二级菜单"
                            },
                        ],
                    },
                    {
                        id:2,
                        text:"课程数据统计",
                        children:[
                            {
                                id:21,
                                text:"课程分类统计(按种类)",
                                attribute:[
                                    {
                                        url:"${pageContext.request.contextPath}/data/toDataStuCourse.do",
                                        title:"课程分类统计(按种类)",
                                    }
                                ],
                            },
                            {
                                id:22,
                                text:"课程分类统计(按教材)",
                                attribute:[
                                    {
                                        url:"${pageContext.request.contextPath}/data/toDataStuCourse1.do",
                                        title:"课程分类统计(按教材)",
                                    }
                                ],
                            },
                            {
                                id:23,
                                text:"新报名课程统计",
                                attribute:[
                                    {
                                        url:"${pageContext.request.contextPath}/data/toDataStuCourse_YMQ.do",
                                        title:"新报名课程统计",
                                    }
                                ],
                            },
                        ],
                    },
                    {
                        id:3,
                        text:"学生数据统计",
                        children:[
                            {
                                id:31,
                                text:"学生按机构分类统计"
                            },
                            {
                                id:32,
                                text:"新增学生统计图（按月）"
                            },
                        ],
                    },
                ],
                lines:true,
                onSelect:function (node) {

                    if(null!=node.attribute && node.attribute.url != ''){
                        var url = node.attribute[0].url;
                        var title = node.attribute[0].title;
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
                    }
                }
            });
            $("#tree").tree("collapseAll");
        });
    </script>
</body>
</html>
