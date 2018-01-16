<%--
  Created by IntelliJ IDEA.
  User: wangheng
  Date: 2018/1/9
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教材信息</title>
    <jsp:include page="../common.jsp"/>
    <script type="text/javascript">
        $(function () {
            $("#bookDatagrid").datagrid({
                url:'${pageContext.request.contextPath}/book/queryAll.do',
                method:"get",
                pagination:true,
                rownumbers:true,
                striped:true,
                checkOnSelect:false,
                onLoadSuccess:function () {
                    $(".linkbutton").linkbutton();
                    $(this).datagrid("fixRowHeight");
                },
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
                        handler:function () {

                           $("#addBookWindow").window('open');
                        }
                    },
                    {
                        text:'批量删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            var bookCheckBox=$("#bookDatagrid").datagrid('getChecked');
                            if (bookCheckBox==null||bookCheckBox.length<=0){
                                alert("请选择要删除的教材信息");
                                return;
                            }
                            var id="";

                            $.each(bookCheckBox,function (index, item) {

                                id=id+item.bookID+",";
                                alert(id);
                            });
                            if (confirm("确认要删除吗?")){
                                $.post("${pageContext.request.contextPath}/book/deleteBookByIdList.do",{"id":id},
                                function (data) {
                                    alert(data.msg);
                                    $("#bookDatagrid").datagrid('reload');
                                });
                            }
                        }
                    },
                ],
                columns:[[

                    {field:'ck',checkbox:true},
                    {field:'bookTitle',title:'教材名称',width:150},
                    {field:'bookAddress',title:'上传地址',width:150},
                    {field:'uploadTime',title:'上传时间',width:120,formatter:function (value, row, index) {
                        var date=new Date(value);
                        var Y=date.getFullYear();//年
                        var M=date.getMonth()+1;//月
                        var D=date.getDay();//天
                        var H=date.getHours();//小时
                        var m=date.getMinutes();//分
                        var s=date.getSeconds(); //秒
                        return Y+"-"+M+"-"+D+" "+H+":"+m+":"+s;


                    }},
                    {field:'bookVersion',title:'教材版本',width:130},
                    {field:'courseType',title:'教材类型',width:150,formatter:function (value, row, index) {
                        return value.courseTypeName;
                    }},
                    {field:'bookID',title:'操作列',width:400,formatter:function (value, row, index) {
                        var queryBook="<button class='linkbutton' onclick='queryBook("+value+")'>详情</button>";
                        var updateBook="<button class='linkbutton' onclick='updateBook("+value+")'>修改</button>";
                        var deleteBook="<button class='linkbutton' onclick='deleteBook("+value+")'>删除</button>";
                        return queryBook+" "+updateBook+" "+deleteBook;
                    }},

                ]],

            })//datagrid

        })
    </script>
    <script type="text/javascript">
        $(function () {
            //修改保存按钮单击事件
            $("#saveUpdateBookButton").click(function () {
                var book=$("#updateBookForm").serialize();
                alert(book);
                $.post("${pageContext.request.contextPath}/book/updateBook.do",book,function (data) {
                    alert("ddd");
                    alert(data.msg);
                    $("#updateBookWindow").window('close');
                    $("#bookDatagrid").datagrid('reload');
                })
            });
        })
    </script>
    <%--添加--%>

    <script type="text/javascript">
        $(function () {
            $("#saveAddBookButton").click(function () {
               var book=$("#addBookForm");
               //通过传统的from表单
               var formData=new  FormData(book[0]);
               //alert(formData);
               console.info(formData);
               $.ajax({
                   url:"${pageContext.request.contextPath}/book/addBook.do",
                   data:formData,
                   catch:false, //布尔值，表示浏览器是否缓存被请求页面 默认是true
                   contentType:false,//发送数据到服务器时所使用的内容类型
                   processData:false,//布尔值，规定通过请求发送的数据是否转换为查询字符串 默认是true
                   type:"post",
                   dataType:"JSON",
                   success:function (data,status) {
                       alert(data.msg);
                       $("#addBookWindow").window('close');
                       $("#bookDatagrid").datagrid('reload');
                   }
               })
            });
        })
    </script>
    <%--条件模糊查询--%>
    <script type="text/javascript">
        $(function () {
            $("#queryBookButton").click(function () {
                var bookTitle=$("#queryBookTitle").val();
                var courseTypeName=$("#queryCourseTypeName").val();
                $("#bookDatagrid").datagrid('load',{
                    "bookTitle":bookTitle,
                    "courseTypeName":courseTypeName
                });
            })
        })


    </script>
    <%--详情按钮单击事件--%>
    <script type="text/javascript">
        function queryBook(id) {
            $.get("${pageContext.request.contextPath}/book/queryBookById.do",{"id":id},
                function (data) {
                $("#queryBookByIdTitle").textbox('setValue',data.bookTitle);
                $("#queryBookVersion").textbox('setValue',data.bookVersion);
                $("#queryCourseName").textbox('setValue',data.courseType.courseTypeName);
                    var date=new Date(data.uploadTime);
                    var Y=date.getFullYear();
                    var M=date.getMonth()+1;
                    var D=date.getDate();
                    var h=date.getHours();
                    var m=date.getMinutes();
                    var s=date.getSeconds();
                    data.uploadTime=Y+"-"+M+"-"+D+" "+h+":"+m+":"+s;
                    $("#queryBookTime").textbox('setValue',data.uploadTime);
                })
            $("#queryBookByIdWindow").window('open');
        }
    </script>

    <%--修改按钮单击事件--%>
    <script type="text/javascript">
        function updateBook(id) {
            $.get("${pageContext.request.contextPath}/book/queryBookById.do",{"id":id},
                function (data) {
                    $("#updateBookByIdTitle").textbox('setValue',data.bookTitle);
                    $("#updateBookVersion").textbox('setValue',data.bookVersion);
                    $("#updateCourseName").textbox('setValue',data.courseType.courseTypeName);
                    var date=new Date(data.uploadTime);
                    var Y=date.getFullYear();
                    var M=date.getMonth()+1;
                    var D=date.getDate();
                    var h=date.getHours();
                    var m=date.getMinutes();
                    var s=date.getSeconds();
                    data.uploadTime=Y+"-"+M+"-"+D+" "+h+":"+m+":"+s;
                    $("#updateBookTime").textbox('setValue',data.uploadTime);
                    $("#updateBookID").val(data.bookID);
                })
            $("#updateBookWindow").window('open');
        }
    </script>

    <%--删除按钮单击事件--%>
    <script type="text/javascript">
        function deleteBook(id) {
            if (confirm("确认要删除吗?")){
                $.post("${pageContext.request.contextPath}/book/deleteBookById.do",{"id":id},function (data) {

                    alert(data.msg);
                    //刷新页面
                    $("#bookDatagrid").datagrid('reload');
                })
            }
        }
    </script>

</head>
<body>
<%--条件查询--%>
<div>
    <form id="queryBookForm">
        <div>
            教材名称:<input id="queryBookTitle" name="bookTitle">
            教材类型:<input id="queryCourseTypeName" name="courseType.courseTypeName">
            <a id="queryBookButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
            <input class="easyui-linkbutton" type="reset" value="重置" style="width: 50px;"/>
        </div>
    </form>
</div>
<table id="bookDatagrid"></table>
<%--详情--%>
<div id="queryBookByIdWindow" class="easyui-window" title="教材详情"
style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px" closed="true">
    <form id="queryBookByIdForm">
        <div>
            教材名称:<input class="easyui-textbox" id="queryBookByIdTitle">
        </div>
        <div>
            教材版本:<input class="easyui-textbox" id="queryBookVersion" >
        </div>
        <div>
            教材类型:<input class="easyui-textbox" id="queryCourseName">
        </div>
        <div>
            上传时间:<input class="easyui-textbox" id="queryBookTime" >
        </div>
    </form>
</div>
<%--修改--%>
<div id="updateBookWindow" class="easyui-window" title="修改教材"
     style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px" closed="true">
    <form id="updateBookForm">
        <div>
            教材名称:<input class="easyui-textbox" id="updateBookByIdTitle" name="bookTitle">
                  <input type="hidden" id="updateBookID" name="bookID">
        </div>
        <div>
            教材版本:<input class="easyui-textbox" id="updateBookVersion" name="bookVersion">
        </div>
        <div>
            教材类型:<input class="easyui-textbox" id="updateCourseName" name="courseType.courseTypeName">
        </div>
        <div>
            上传时间:<input class="easyui-textbox" id="updateBookTime" readonly>
        </div>
    </form>
    <button id="saveUpdateBookButton" class="easyui-linkbutton">保存</button>
</div>
<%--添加--%>
<div id="addBookWindow" class="easyui-window" title="添加教材"
     style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px" closed="true">
    <form id="addBookForm" enctype="multipart/form-data" method="post">
        <div>
            教材名称:<input class="easyui-textbox" id="addBookByIdTitle" name="bookTitle">

        </div>
        <div>
            教材版本:<input class="easyui-textbox" id="addBookVersion" name="bookVersion">
        </div>
        <div>
            教材类型:<input class="easyui-textbox" id="addCourseName" name="courseType.courseTypeName">
        </div>
        <div>
            上传教材: <input type="file" name="bookAddress_file" id="bookAddress_file"/>
        </div>
    </form>
    <button id="saveAddBookButton" class="easyui-linkbutton">保存</button>

</div>
</body>
</html>
