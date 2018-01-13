<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>环迅后台系统-登录</title>
    <jsp:include page="common.jsp"/>
    <script type="text/javascript">
        $(function () {

        });
    </script>
</head>
<body>
    <div class="easyui-window" style="width: 400px;height: 280px;top: 100px;padding: 50px;" title="环迅后台系统登录" data-options="{closable:false,minimizable:false,maximizable:false}">
        <form action="/common/login" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;<b>用户名：</b>
            <input type="text" class="easyui-textbox" name="loginName"/>
            <br/><br/>
            <b>&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：</b>
            <input type="password" class="easyui-textbox" name="loginPassword"/>
            <br/><br/>
            <span id="loginMessage">${loginMessage}</span>
            <br/><br/>
            <div style="width: 200px;margin: 0 auto;">
                <input class="easyui-linkbutton" type="submit" value="登录" style="width: 100px;"/>
                <input class="easyui-linkbutton" type="reset" value="重置" style="width: 100px;"/>
            </div>
        </form>
    </div>
</body>
</html>
