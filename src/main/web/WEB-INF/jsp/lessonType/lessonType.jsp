<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/1/9
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程</title>
    <%@include file="../common.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#lessonTypeShow").datagrid({
                url:'${pageContext.request.contextPath}/lessonType/lessonTypeAll.do',
                method:'GET',
                rownumbers:true,
                striped:true,
                queryParams: {
                    lessonPrice1: '',
                    lessonPrice2: '',
                },
                title:'课程列表',
                pagination:true,
                checkOnSelect:false,
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
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
                    {field:'timePerLesson',title:'课程时长(分)',width:200},
                    {field:'lessonArea',title:'地区(教师)',width:200},
                    {field:'lessonPrice',title:'单节费用(RMB)',width:200},
                    {field:'lessonDesc',title:'课程名称',width:200},
                    {field:'id',title:'操作列',width:400,formatter:function (value,row,index) {
                        var queryScoreById="<button onClick='queryScoreById("+value+")'>详情</button>";
                        var updateScore="<button onclick='updateScore("+value+")'>修改</button>";
                        var deleteScore="<button onclick='deleteScore("+value+")'>删除</button>";
                        return queryScoreById+" "+updateScore+" "+deleteScore;
                    }}

                ]]
            })
            $("#queryAllByLessonDescButton").click(function () {
                var lessonDesc = $("#lessonDesc").val();
                $("${pageContext.request.contextPath}/lessonType/lessonTypeAll.do",{"lessonDesc":lessonDesc},function (lessonType) {
                    $("#lessonTypeShow").datagrid("");
                })
            })
        })
    </script>
<%--    <style type=”text/javascript”>
        a:hover {
            color:#000000;
           ;
        }
        a{text-decoration:none} 无下划线
        a:link{color:red;background-color: #1f1f1f}
        a:visited{color:blue}
        a:active{color:yellow}
        a:hover{color:green;}
    </style>--%>
</head>
<body>
<div>
    搜索：<input id="lessonDesc" type="text" name="lessonDesc" class="easyui-textbox"/>
    <button  class="easyui-linkbutton" iconCls="icon-search" id="queryAllByLessonDescButton">搜索</button>
</div>
<br/>
<div>
    &nbsp;&nbsp;&nbsp; 教师地区：
    <a id="null" href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="欧美" href="#" style="text-decoration:none">欧美</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="菲律宾" href="#" style="text-decoration:none">菲律宾</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="新东方英语" href="#" style="text-decoration:none">新东方英语</a>
</div>
<br/>
<div>
    &nbsp;&nbsp;&nbsp; 课程时间：
    <a id="null2" href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="25分钟" href="#" style="text-decoration:none">25分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="45分钟" href="#" style="text-decoration:none">45分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="75分钟" href="#" style="text-decoration:none">75分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="100分钟" href="#" style="text-decoration:none">100分钟</a>

</div>
<br/>
<div>
    &nbsp;&nbsp;&nbsp; 单节价格：
    <a id="null3" href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="100~300 " href="#" style="text-decoration:none">100~300 </a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="300~500" href="#" style="text-decoration:none">300~500</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="500+" href="#" style="text-decoration:none">500+</a>
</div>
<br/>
<table id="lessonTypeShow"></table>
</body>
</html>
