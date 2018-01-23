<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/9/009
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>外教管理界面</title>
    <jsp:include page="../common.jsp"/>
    <script type="text/javascript">
        $(function () {
            $("#teacherDg").datagrid({
               url:'${pageContext.request.contextPath}/teacher/queryAllTeacher',
               method:'GET',
               title:'外教基本信息',
                pagination:true,
                rownumbers:true,
                checkOnSelect:false,
                striped:true,
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
                        handler:function () {
                            $("#AddTeacherForm").form('clear');
                            $("#AddTeacherWindow").window('open');
                        }
                    },{
                   text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            var teacherCheck = $("#teacherDg").datagrid('getChecked');
                            if (teacherCheck==null||teacherCheck.length<=0) {
                                alert("请选择！");
                                return;
                            }
                            var teacherIDs = "";
                            var userIDs = "";
                            var timeIds = "";
                            $.each(teacherCheck,function (index,item) {
                                teacherIDs = teacherIDs + item.teacherID + ",";
                                userIDs = userIDs + item.loginUser.loginUserID + ",";
                                timeIds = timeIds + item.partTeacherTime.partTeacherTimeID + ",";
                            })
                            if (confirm("确定删除所选老师吗？")){
                                $.post("${pageContext.request.contextPath}/teacher/deleteTeacherByList",{"teacherIDs":teacherIDs},function (data) {
                                    alert(data.msg);
                                    $("#teacherDg").datagrid('reload');
                                })
                            }
                                $.post("${pageContext.request.contextPath}/common/deleteUserByList",{"UserIDs":userIDs},function (user) {
                                })
                                $.post("${pageContext.request.contextPath}/teacher/deleteTeacherTimeByList",{"partTeacherTimeIDs":timeIds},function (time) {
                                })
                        }
                    }
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'teacherName',title:'姓名',width:100},
                    {field:'teacherPhotoAddress',title:'头像',width:60,
                    formatter:function (value,row,index) {
                        var teacherPhotoAddress=row.teacherPhotoAddress;
                        alert(teacherPhotoAddress);
                        var teacherPhotoAddress="<img src='${pageContext.request.contextPath}/statics/upload/"+teacherPhotoAddress+" alt='' height='50px' width='50px'/>";
                        return teacherPhotoAddress;
                    }},
                    {field:'teacherAge',title:'年龄',width:50},
                    {field:'teacherGender',title:'性别',width:30},
                    {field:'teacherEmail',title:'邮箱',width:100},
                    {field:'teacherPhone',title:'手机号',width:100},
                    {field:'teacherQQ',title:'QQ',width:100},
                    {field:'teacherScore',title:'分数',width:50},
                    {field:'loginUser.loginName',title:'登录名',width:50,formatter:function (value,row,index) {
                        return row.loginUser.loginName;
                    }},
                    {field:'loginUser.loginPassword',title:'密码',width:100,formatter:function (value,row,index) {
                        return row.loginUser.loginPassword;
                    }},
                    {field:'teacherDesc',title:'描述',width:100},
                    {field:'teacherArea',title:'地区',width:50},
                    {field:'teacherCareerType',title:'兼职/全职',width:100},
                    {field:'teacherID',title:'操作列',width:400,formatter:function (value,row,index) {
                        var queryTeacherById="<button onclick='queryTeacherById("+value+")'>详情</button>";
                        var updateTeacher="<button onclick='updateTeacher("+value+")'>修改</button>";
                        var deleteTeacher="<button onclick='deleteTeacher("+value+")'>删除</button>";
                        return queryTeacherById+" "+updateTeacher+" "+deleteTeacher;
                    }}
                ]]
            })
            $("#saveUpdateTeacherButton").click(function () {
                var teacher=$("#updateTeacherForm");
               /* $.post("${pageContext.request.contextPath}/teacher/updateTeacher",teacher,function (data) {
                    alert(data.msg);
                    $("#updateTeacherWindow").window('close');
                    $("#teacherDg").datagrid('reload');
                })*/
                var formData = new FormData(teacher[0]);
                $.ajax({
                    url:"${pageContext.request.contextPath}/teacher/updateProduct",
                    data:formData,
                    catch:false,
                    contentType:false,
                    processData:false,
                    type:"post",
                    dataType:"JSON",
                    success:function (data,status) {
                        alert(data.msg);
                        $("#updateTeacherWindow").window("close");
                        $("#teacherDg").datagrid('reload');
                    }
                });
            })
            $("#saveAddTeacherButton").click(function () {
                var teacher = $("#AddTeacherForm");
                var teacherName = $("#addTeacherName").val();
                if (teacherName==""){
                    alert("外教名不能为空！");
                    return false;
                }
                var teacherPhotoAddress=$("#AddTeacherPhoto").val()
                if(teacherPhotoAddress==""){
                    alert("请上传头像！");
                    return false;
                }
                var teacherEmail = $("#addEmail").val();
                var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if (teacherEmail==""){
                    alert("Email不能为空！");
                    return false;
                }
                if(reg.test(teacherEmail)==false){
                    alert("邮箱格式不正确!");
                    return false;
                }
                var teacherScore = $("#addTeacherScore").val();
                if (teacherScore==""){
                    alert("请填写分数！");
                    return false;
                }
                if(isNaN(teacherScore)){
                    alert("请输入正确的分数！");
                    return false;
                }
                var teacherCareerType = $("#addTeacherCareerType").val();
                if (teacherCareerType==null){
                    alert("请选择类型！");
                    return false;
                }
                var teacherAge = $("#addAge").val();
                if (teacherAge==""){
                    alert("请选择年龄！");
                    return false;
                }
                if(isNaN(teacherAge)){
                    alert("请输入正确的年龄！");
                    return false;
                }
                var teacherGander = $("#addGender").val();
                if(teacherGander==null||teacherGander==""){
                    alert("请选择性别！");
                    return false;
                }
                var teacherLevel = $("#addTeacherLevel").val();
                if (teacherLevel==null||teacherLevel=="") {
                    alert("请选择等级！");
                    return false;
                }
                var teacherPhone = $("#addPhone").val();
                if(teacherPhone.length!=11||teacherPhone==""){
                    alert("请输入正确手机号");
                    return false;
                }
                var teacherQQ = $("#addQQ").val();
                if (teacherQQ==""||teacherQQ.length<6||teacherQQ.length>11){
                    alert("请输入正确的QQ号");
                    return false;
                }
                var teacherArea = $("#addTeacherArea").val();
                if (teacherArea==null){
                    alert("请选择地址！");
                    return false;
                }
                var loginName = $("#addUserName").val();
                if (loginName==""){
                    alert("请输入用户名！");
                    return false;
                }
                var password = $("#addPassWork").val();
                if (password==""){
                    alert("请输入密码！");
                    return false;
                }
                var teacherDesc = $("#addTestDesc").val();
                if(teacherDesc==""){
                    alert("请输入描述！");
                    return false;
                }
                var formData = new FormData(teacher[0]);
                $.ajax({
                    url:"${pageContext.request.contextPath}/teacher/AddProduct",
                    data:formData,
                    contentType:false,
                    processData:false,
                    type:"post",
                    dataType:"JSON",
                    success:function (data,status) {
                        alert(data.msg);
                        $("#AddTeacherWindow").window('close');
                        $("#teacherDg").datagrid('reload');
                    }
                })
            })
           $("#queryScoreButton").click(function () {
               var teacherName = $("#queryTeacherNameCombobox").val();
               var loginName = $("#queryLoginNameCombobox").val();
                   $("#teacherDg").datagrid('load',{
                       "teacherName":teacherName,
                       "loginName":loginName
             });
           })
        })
       function queryTeacherById(teacherID) {
         $("#haveBooksTable").text("");
         $("#haveBooksTable").append("<tr><td>工作日</td><td>上班</td><td>下班</td></tr>");
         $.get("${pageContext.request.contextPath}/teacherTime/queryByTeacherID",{"teacherID":teacherID},function (time) {
             $.each(time,function (index, item) {
                 var date=new Date(item.ptStartTime);
                 var H=date.getHours();//小时
                 var m=date.getMinutes();//分钟
                 var s=date.getSeconds();//秒
                 item.ptStartTime= H+":"+m+":"+s;
                 var date=new Date(item.ptEndTime);
                 var H=date.getHours();//小时
                 var m=date.getMinutes();//分钟
                 var s=date.getSeconds();//秒
                 item.ptEndTime= H+":"+m+":"+s;
                 $("#haveBooksTable").append("<tr><td>"+item.weekDay+"</td>" +
                     "<td>"+item.ptStartTime+"</td>" +
                     "<td>"+item.ptEndTime+"</td></tr>");
             });
         });
         $("#queryTeacherByIdWindow").window('open');
       }
       function deleteTeacher(teacherID) {
           if (confirm("确认删除吗？")){
               $.post("${pageContext.request.contextPath}/teacher/deleteTeacherById",{"teacherID":teacherID},function (data) {
                   alert(data.msg);
                   $("#teacherDg").datagrid('reload');
               })
           }
       }
       function updateTeacher(teacherID) {
           $.get("${pageContext.request.contextPath}/teacher/queryTeacherById",{"teacherID":teacherID},function (data) {
               //alert(data.teacherID);
               $("#updateTeacherCareerType").textbox('setValue', data.teacherCareerType);
               $("#updateGender").val( data.teacherGender);
               $("#updateTeacherScore").textbox('setValue', data.teacherScore);
               $("#updateTeacherLevel").textbox('setValue', data.teacherLevel);
               $("#updateQQ").textbox('setValue', data.teacherQQ);
               $("#updateAge").textbox('setValue', data.teacherAge);
               $("#updateEmail").textbox('setValue', data.teacherEmail);
               $("#updatePhone").textbox('setValue', data.teacherPhone);
               $("#updateTeacherArea").textbox('setValue', data.teacherArea);
               $("#updateTeacherName").textbox('setValue', data.teacherName);
               $("#updateTestDesc").textbox('setValue', data.teacherDesc);
               $("#updateUserName").textbox('setValue', data.loginUser.loginName);
               $("#updatePassWork").textbox('setValue', data.loginUser.loginPassword);
               $("#updateTeacherPhoto").val(data.teacherPhotoAddress);
               $("#updateTeacherId").val(data.teacherID);
               $("#updateUserId").val(data.loginUser.loginUserID);
           })
           $("#updateTeacherWindow").window('open');
       }
    </script>
</head>
<body>
<!--条件查询的form-->
<div>
    <form id="queryScoreForm">
        外教名：<input id="queryTeacherNameCombobox" name="teacherName"/>
        用户名：<input id="queryLoginNameCombobox" name="loginName"/>
        <a id="queryScoreButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<table id="teacherDg"></table>
<!--Teacher详情window-->
<div  id="queryTeacherByIdWindow"
     style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px" class="easyui-window"
     title="外教详情" closed="true">
    <form id="queryTeacherByIdForm" style="display: inline"\>
    </form>
    <table id="haveBooksTable" class="table table-striped" border="1"></table>
</div>
<div class="easyui-window" title="修改成绩" id="updateTeacherWindow"
     style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px"
     closed="true">
    <form id="updateTeacherForm">
        <div>
            老师姓名：<input id="updateTeacherName" name="teacherName" class="easyui-textbox"/>
            <input type="hidden" id="updateTeacherId" name="teacherID"/>
            <input type="hidden" id="updateUserId" name="loginUserID">
        </div>
        <div>
            用&nbsp;&nbsp;&nbsp;&nbsp;户名：<input id="updateUserName" name="loginName" class="easyui-textbox"/>
        </div>
        <div>
            密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="updatePassWork" name="loginPassword" class="easyui-textbox"/>
        </div>
        <div>
           头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像：
            <div class="col-sm-9">
                <div class="col-sm-10">
                    <%--  <input type="hidden" id="errorinfo" value="${uploadFileError}">--%>
                    <input type="file" class="form-control" name="teacherPhotoUpdate" id="updateTeacherPhoto">
                    <img src="${pageContext.request.contextPath}/statics/upload/" alt="" height="50px" width="50px">
                </div>
            </div>
        </div>
     <%--   <td><img src="${pageContext.request.contextPath}/statics/upload/${product.fileName}" alt="" height="50px" width="50px"></td>--%>
        <div>
            Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input id="updateEmail" name="teacherEmail" class="easyui-textbox"/>
        </div>
        <div>
            分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：<input id="updateTeacherScore" name="teacherScore" class="easyui-textbox"/>
        </div>
        <div>
            类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：<input id="updateTeacherCareerType" name="teacherCareerType" class="easyui-textbox"/>
        </div>
        <div>
            年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：<input id="updateAge" name="teacherAge" class="easyui-textbox"/>
        </div>
        <div>
            <label class="col-sm-3 control-label">学生性别</label>
            <select class="form-control" name="teacherGender" id="updateGender">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div>
            教学等级：<input id="updateTeacherLevel" name="teacherLevel" class="easyui-textbox"/>
        </div>
        <div>
            手&nbsp;&nbsp;机&nbsp;号：<input id="updatePhone" name="teacherPhone" class="easyui-textbox"/>
        </div>
        <div>
            Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q：<input id="updateQQ" name="teacherQQ" class="easyui-textbox"/>
        </div>
        <div>
            地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<input id="updateTeacherArea" name="teacherArea" class="easyui-textbox"/>
        </div>
        <div>
            描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<input id="updateTestDesc" name="teacherDesc" class="easyui-textbox"/>
        </div>
    </form>
    <button class="easyui-linkbutton" id="saveUpdateTeacherButton">保存</button>
</div>
<!--添加成绩window-->
<div class="easyui-window" title="添加成绩" id="AddTeacherWindow"
     style="width: 500px;height: 300px;top: 30%;left: 30% ;padding: 60px 120px"
     closed="true">
    <form id="AddTeacherForm">
        <div>
            老师姓名：<input id="addTeacherName" name="teacherName" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            <label class="col-sm-3 control-label">头像：</label>
            <div class="col-sm-9">
                <div class="col-sm-10">
                    <%--  <input type="hidden" id="errorinfo" value="${uploadFileError}">--%>
                    <input type="file" class="form-control" name="teacherPhotoAdd" data-options="required:true" id="AddTeacherPhoto">
                </div>
            </div>
        </div>
        <div>
            Email&nbsp;&nbsp;&nbsp;：<input id="addEmail" name="teacherEmail"  class="easyui-validatebox" data-options="required:true,validType:'email'"/>
        </div>
        <div>
            分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：<input id="addTeacherScore" name="teacherScore" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：<%--<input id="addTeacherCareerType" name="teacherCareerType" class="easyui-textbox" data-options="required:true"/>--%>
            <select class="form-control" name="teacherCareerType" id="addTeacherCareerType">
                <option value="兼职">兼职</option>
                <option value="全职">全职</option>
            </select>
        </div>
        <div>
            年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：<input id="addAge" name="teacherAge" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别<%--：<input id="addGender" name="teacherGender" class="easyui-textbox" data-options="required:true"/>--%>
            <select class="form-control" name="teacherGender" id="addGender">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div>
            教学等级：<%--<input id="addTeacherLevel" name="teacherLevel" class="easyui-textbox" data-options="required:true"/>--%>
            <select class="form-control" name="teacherLevel" id="addTeacherLevel">
                <option value="初级">初级</option>
                <option value="中级">中级</option>
                <option value="高级">高级</option>
            </select>
        </div>
        <div>
            手机号&nbsp;&nbsp;&nbsp;：<input id="addPhone" name="teacherPhone" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q：<input id="addQQ" name="teacherQQ" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<%--<input id="addTeacherArea" name="teacherArea" class="easyui-textbox" data-options="required:true"/>--%>
            <select class="form-control" name="teacherArea" id="addTeacherArea">
                <option value="欧美">欧美</option>
                <option value="菲利宾">菲利宾</option>
            </select>
        </div>
        <div>
            用&nbsp;户&nbsp;名&nbsp;：<input id="addUserName" name="loginName" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="addPassWork" name="loginPassword" class="easyui-textbox" data-options="required:true"/>
        </div>
        <div>
            描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<input id="addTestDesc" name="teacherDesc" class="easyui-textbox" data-options="required:true,iconCls:'icon-search'" style="width:300px">
        </div>
    </form>
        <button class="easyui-linkbutton" id="saveAddTeacherButton">保存</button>
</div>
</div>
</body>
</html>
