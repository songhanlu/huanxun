<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/1/17
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>为课程上传教材</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
    <%--批量上传教材--%>
    <a href="${pageContext.request.contextPath}/classArrange/toClassArrange.do" class="easyui-linkbutton" data-options="{iconCls:'icon-undo'}">返回上级</a>
    <form id="uploadBooksForm">
        <span style="color: red;">请选择批量上传的教材范围-->></span>
        从：<input id="startBook" name="startBookNo"/>
        到：<input id="endBook" name="endBookNo"/>
        <input type="hidden" name="courseTypeID" value="${stuCourse.courseType.courseTypeID}"/>
        <input type="hidden" name="stuCourseID" value="${stuCourse.stuCourseID}"/>
        <a href="#" id="uploadBooksButton" class="easyui-linkbutton" data-options="{iconCls:'icon-save'}">确定上传</a>
    </form>

    <%--上传教材列表--%>
    <div id="upLoadBookDatagrid"></div>


    <%--加载页面时，显示当前课程的教材列表--%>
    <script type="text/javascript">
        $(function () {
            $("#upLoadBookDatagrid").datagrid({
                url:"${pageContext.request.contextPath}/studentCourse/findBooksBystuCourseID.do?stuCourseID=${stuCourse.stuCourseID}",
                method:"get",
                pagination:true,
                rownumbers:true,
                striped:true,
                onLoadSuccess:function () {
                    $(".linkbutton").linkbutton();
                    $(this).datagrid("fixRowHeight");
                },
                toolbar:[
                    {
                        text:"批量删除",
                        iconCls:"icon-remove",
                        handler:function () {
                            var checked = $("#upLoadBookDatagrid").datagrid("getChecked");
                            if(null==checked || checked.length==0){
                                alert("请勾选");
                                return;
                            }
                            var stuCourseBookIDs = "";
                            $.each(checked,function (index, item) {
                                stuCourseBookIDs = stuCourseBookIDs+item.stuCourseBookID+",";
                            });
                            $.post("${pageContext.request.contextPath}/studentCourse/deleteUploadBooks.do",{"IDs":stuCourseBookIDs},function (result) {
                                alert(result.msg);
                                $("#upLoadBookDatagrid").datagrid("reload");
                            });
                        }
                    },
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {title:"教材名称",field:"book",formatter:function (value, row, index) {
                        return row.book.bookTitle;
                    }},
                    {title:"操作",width:120,field:"bookAddress",formatter:function (value,row,index) {
                        return "<a class='linkbutton' href='${pageContext.request.contextPath}/statics/upload/"+row.book.bookAddress+"'>下载附件</a>"+
                            "   <a class='linkbutton' href='#' onclick='deleteBook("+row.stuCourseBookID+")'>删除</a>";
                    }},
                ]],
            });
        });
    </script>

    <%--加载页面时，加载可选图书列表（开始教材）--%>
    <script type="text/javascript">
        $(function () {
            $.get("${pageContext.request.contextPath}/studentCourse/findBooksCanBeChose.do",{"courseTypeID":${stuCourse.courseType.courseTypeID}},function (books) {
                var bookfo = $.parseJSON('{"bookNoInHisType":-1,"bookTitle":"==请选择=="}');
                books.push(bookfo);
                $("#startBook").combobox({
                    editable:false,
                    valueField:"bookNoInHisType",
                    textField:"bookTitle",
                    data:books,
                    onLoadSuccess:function () {
                        $(this).combobox("select", -1);
                    }
                });
            });
        });
    </script>

    <%--加载页面时，加载可选图书列表（教材教材）--%>
    <script type="text/javascript">
        $(function () {
            $.get("${pageContext.request.contextPath}/studentCourse/findBooksCanBeChose.do",{"courseTypeID":${stuCourse.courseType.courseTypeID}},function (books) {
                var bookfo = $.parseJSON('{"bookNoInHisType":-1,"bookTitle":"==请选择=="}');
                books.push(bookfo);
                $("#endBook").combobox({
                    editable:false,
                    valueField:"bookNoInHisType",
                    textField:"bookTitle",
                    data:books,
                    onLoadSuccess:function () {
                        $(this).combobox("select", -1);
                    }
                });
            });
        });
    </script>
    <%--点击批量上传按钮--%>
    <script type="text/javascript">
        $(function () {
            $("#uploadBooksButton").click(function () {
                var uploadBooks = $("#uploadBooksForm").serialize();
                console.log(uploadBooks);
                $.post("${pageContext.request.contextPath}/studentCourse/doUploadBooks.do",uploadBooks,function (result) {
                    alert(result.msg);
                    $("#upLoadBookDatagrid").datagrid("reload");
                });
            });
        });
    </script>
    <script type="text/javascript">
        function deleteBook(id) {
            if(confirm("确认删除？")){
                var ids = id + ",";
                $.post("${pageContext.request.contextPath}/studentCourse/deleteUploadBooks.do",{"IDs":ids},function (result) {
                    alert(result.msg);
                    $("#upLoadBookDatagrid").datagrid("reload");
                });
            }

        }
    </script>
</body>
</html>
