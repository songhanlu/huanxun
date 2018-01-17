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
    <%@include file="lesson.jsp"%>
    <script type="text/javascript">
        $(function () {

            $("#lessonTypeShow").datagrid({
                url:'${pageContext.request.contextPath}/lessonType/lessonTypeAll.do',
                method:'GET',
                rownumbers:true,
                striped:true,
                title:'课程列表',
                pagination:true,
                checkOnSelect:false,
                toolbar:[
                    {
                        text:'添加',
                        iconCls:'icon-add',
                        handler:function () {
                            $("#addlessonTypeForm").form("clear");
                            $("#addlessonTypeWindow").window('open');
                        }
                    },
                    {
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            var lessonTypeId=$("#lessonTypeShow").datagrid('getChecked');
                            if(null==lessonTypeId||lessonTypeId.length<=0){
                                alert("请选择要删除的项");
                                return;
                            }

                            if(confirm("确定要删除吗？")){
                                var IdList="";
                                $.each(lessonTypeId,function (index,item) {
                                    IdList=IdList+item.lessonTypeID+",";
                                })


                                $.post("${pageContext.request.contextPath}/lessonType/deletelessonTypeList.do",
                                    {"IdList":IdList},function (data) {
                                        alert(data.msg);
                                        $("#lessonTypeShow").datagrid('reload');
                                    })
                            }

                        }
                    }
                ],
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'timePerLesson',title:'课程时长(分)',width:150},
                    {field:'lessonArea',title:'地区(教师)',width:200},
                    {field:'lessonPrice',title:'单节费用(RMB)',width:200},
                    {field:'lessonDesc',title:'课程名称',width:250},
                    {field:'lessonTypeID',title:'操作列',width:300,formatter:function (value,row,index) {
                        var queryScoreById="<button onClick='queryScoreById("+value+")'>详情</button>";
                        var updateScore="<button onclick='updateScore("+value+")'>修改</button>";
                        var deleteScore="<button onclick='deleteScore("+value+")'>删除</button>";
                        return queryScoreById+" "+updateScore+" "+deleteScore;
                    }}

                ]]
            })
            /*修改课程*/
            $("#updatelessonTypeButton").click(function () {
                var lessonType = $("#updatelessonTypeForm").serialize();

                var timePerLesson = $("#updatekechengshichang").val();
                if(timePerLesson==null || timePerLesson==''){
                    alert("课时（分钟）不能为空");
                    return;
                }
                var lessonPrice=$("#updatedanjiefeiyong").val();
                if(lessonPrice==null || lessonPrice==''){
                    alert("资费金额（RMB）不能为空");
                    return;
                }
                var lessonDesc=$("#updatekechengmingcheng").val();
                if(lessonDesc==null || lessonDesc==''){
                    alert("课程名称不能为空");
                    return;
                }


                $.post("${pageContext.request.contextPath}/lessonType/updatelessonType.do",
                    lessonType,function (data) {
                        alert(data.msg);
                        $("#updatelessonTypeWindow").window('close');
                        $("#lessonTypeShow").datagrid('reload');
                });





            })
            /*添加课程*/
            $("#addlessonTypeButton").click(function () {
                var lessonType = $("#addlessonTypeForm").serialize();


                var timePerLesson = $("#addkechengshichang").val();
                if(timePerLesson==null || timePerLesson==''){
                    alert("课时（分钟）不能为空");
                    return;
                }
                var lessonPrice=$("#adddanjiefeiyong").val();
                if(lessonPrice==null || lessonPrice==''){
                    alert("资费金额（RMB）不能为空");
                    return;
                }
                var lessonDesc=$("#addkechengmingcheng").val();
                if(lessonDesc==null || lessonDesc==''){
                    alert("课程名称不能为空");
                    return;
                }
                var lessonArea=$("#addwaijiaodiqv").val();
                if(lessonArea==null || lessonArea==''){
                    alert("外教地区（机构）不能为空");
                    return;
                }

                $.ajax({
                    url:"${pageContext.request.contextPath}/lessonType/querylessonDescBylessonArea.do",
                    type:"GET",
                    data:{"lessonArea":lessonArea,"lessonDesc":lessonDesc},
                    dataType:"text",
                    success:function (data) {
                        if(data=="success"){
                            alert("该地区（机构）已经拥有该课程！");
                        }else{
                            $.post("${pageContext.request.contextPath}/lessonType/addlessonType.do",
                                lessonType,function (data) {
                                    alert(data.msg);
                                    $("#addlessonTypeWindow").window('close');
                                    $("#lessonTypeShow").datagrid('reload');
                                }
                            )
                        }
                    }
                });

            })
        })


        function queryScoreById(value) {
            $.get("${pageContext.request.contextPath}/lessonType/queryLessonTypeById",{"lessonTypeID":value},function (lessonType) {
                $("#kechengshichang").textbox('setValue',lessonType.timePerLesson);
                $("#waijiaodiqv").textbox('setValue',lessonType.lessonArea);
                $("#danjiefeiyong").textbox('setValue',lessonType.lessonPrice);
                $("#kechengmingcheng").textbox('setValue',lessonType.lessonDesc);
                if(lessonType.visible==1){
                    $("#kechengzhuangti").textbox('setValue',"在线课程")
                }
                if(lessonType.visible==0){
                    $("#kechengzhuangti").textbox('setValue',"下线课程")
                }

            })
            $("#lessonTypeWindow").window('open');
        }

        function updateScore(value) {
            $.get("${pageContext.request.contextPath}/lessonType/queryLessonTypeById",{"lessonTypeID":value},function (lessonType) {
                $("#updatekechengshichang").textbox('setValue',lessonType.timePerLesson);
                $("#updatewaijiaodiqv").textbox('setValue',lessonType.lessonArea);
                $("#updatedanjiefeiyong").textbox('setValue',lessonType.lessonPrice);
                $("#updatekechengmingcheng").textbox('setValue',lessonType.lessonDesc);
                $("#updateLessonTypeID").val(lessonType.lessonTypeID);
                if(lessonType.visible==1){
                    $("#updatekechengzhuangti").textbox('setValue',"在线课程")
                }
                if(lessonType.visible==0){
                    $("#updatekechengzhuangti").textbox('setValue',"下线课程")
                }

            })
            $("#updatelessonTypeWindow").window('open');
        }

        function deleteScore(value) {
                $.post("${pageContext.request.contextPath}/lessonType/deletelessonType.do",{"lessonTypeID":value},function (data) {
                    alert(data.msg);
                    $("#lessonTypeShow").datagrid('reload');


            })
        }

    </script>

</head>
<body>
<div>
    搜索：<input id="lessonDesc" type="text" name="lessonDesc" prompt="课程名称搜索" class="easyui-textbox"/>
    <button  class="easyui-linkbutton" iconCls="icon-search" id="queryAllByLessonDescButton">搜索</button>
</div>
<br/>
<div>
    &nbsp;&nbsp;&nbsp; 教师地区：
    <a id="diqv0" value="" href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="diqv1" value="欧美" href="#" style="text-decoration:none">欧美</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="diqv2" value="菲律宾" href="#" style="text-decoration:none">菲律宾</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="diqv3" value="新东方英语" href="#" style="text-decoration:none">新东方英语</a>
</div>
<br/>
<div >
    &nbsp;&nbsp;&nbsp; 课程时间：
    <a id="fenzhong0" value="" href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="fenzhong1" value="25"  href="#" style="text-decoration:none">25分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="fenzhong2" value="45" href="#" style="text-decoration:none">45分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="fenzhong3" value="75" href="#" style="text-decoration:none">75分钟</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="fenzhong4" value="100" href="#" style="text-decoration:none">100分钟</a>

</div>
<br/>
<div>
    &nbsp;&nbsp;&nbsp; 单节价格：
    <a id="zifei0"  href="#" style="text-decoration:none">不限</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="zifei" value2="1" href="#" style="text-decoration:none">试听课程</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="zifei1" value1="100" value2="300" href="#" style="text-decoration:none">100~300 </a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="zifei2" value1="300" value2="500" href="#" style="text-decoration:none">300~500</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a id="zifei3" value1="500"  href="#" style="text-decoration:none">500+</a>
</div>
<br/>
<table id="lessonTypeShow"></table>
<div id="lessonTypeWindow" title="课程详情" class="easyui-window" style="width: 650px;height: 400px;padding: 60px 120px" closed="true">
    <table>
        <tr>
            <td>课程时长(分钟)</td>
            <td><input id="kechengshichang"  class="easyui-textbox"readonly/></td>
        </tr>
        <tr>
            <td>外教地区</td>
            <td><input id="waijiaodiqv"  class="easyui-textbox" readonly/></td>
        </tr>
        <tr>
            <td>单节费用（RMB）</td>
            <td><input id="danjiefeiyong"  class="easyui-textbox"readonly/></td>
        </tr>
        <tr>
            <td>课程名称</td>
            <td><input id="kechengmingcheng"  class="easyui-textbox"readonly/></td>
        </tr>
        <tr>
            <td>课程状态</td>
            <td><input id="kechengzhuangti"  class="easyui-textbox"readonly/></td>
        </tr>
    </table>
</div>
<div id="updatelessonTypeWindow" title="课程修改" class="easyui-window" style="width: 650px;height: 400px;padding: 60px 120px" closed="true">
    <form id="updatelessonTypeForm">
        <table>
            <tr>
                <td>课程时长（分钟）</td>
                <td><input id="updatekechengshichang" name="timePerLesson" class="easyui-textbox" />可修改
                    <input type="hidden" name="lessonTypeID" id="updateLessonTypeID"/></td>
            </tr>
            <tr>
                <td>单节费用（RMB）</td>
                <td><input id="updatedanjiefeiyong" name="lessonPrice"  class="easyui-textbox"/>可修改</td>
            </tr>
            <tr>
                <td>课程名称</td>
                <td><input id="updatekechengmingcheng" name="lessonDesc" class="easyui-textbox" />可修改</td>
            </tr>
            <tr>
                <td>外教地区（机构）</td>
                <td><input id="updatewaijiaodiqv" class="easyui-textbox"readonly /></td>
            </tr>
            <tr>
                <td>课程状态</td>
                <td><input id="updatekechengzhuangti"  class="easyui-textbox" readonly/></td>
            </tr>
        </table>
    </form>
    <div align="center">
        <button id="updatelessonTypeButton" class="easyui-linkButton">&nbsp;&nbsp; &nbsp;&nbsp;保 &nbsp;&nbsp; &nbsp;&nbsp;存&nbsp;&nbsp; &nbsp;&nbsp;</button>
    </div>
</div>

<div id="addlessonTypeWindow" title="课程添加" class="easyui-window" style="width: 650px;height: 400px;padding: 60px 120px" closed="true">
    <form id="addlessonTypeForm">
        <table>
            <tr>
                <td>课程时长(分钟)</td>
                <td><input id="addkechengshichang" name="timePerLesson" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>单节费用（RMB）</td>
                <td><input id="adddanjiefeiyong" name="lessonPrice"  class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>课程名称</td>
                <td><input id="addkechengmingcheng" name="lessonDesc" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>外教地区（机构）</td>
                <td><input id="addwaijiaodiqv" name="lessonArea" class="easyui-textbox" /></td>
            </tr>

        </table>
    </form>
   <div align="center">
       <button id="addlessonTypeButton" class="easyui-linkButton">&nbsp;&nbsp; &nbsp;&nbsp;保 &nbsp;&nbsp; &nbsp;&nbsp;存&nbsp;&nbsp; &nbsp;&nbsp;</button>
   </div>
</div>
</body>
</html>
