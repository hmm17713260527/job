<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/29
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jsencrypt.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\md5-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
<script type="text/javascript">

    if (window.top.document.URL != document.URL) {
        window.top.location = document.URL;
    }

    function inquire(name) {
        $.get(
            "<%=request.getContextPath()%>/user/findSalt",
            {"userName" : name.value, "_method" : "get"},
            function (data) {
                alert(data.msg);
                $("#salt").val(data.msg);
            })

    }

    function login() {
        var index = layer.load(0, {shade:0.5});

        var pwd = md5($("#password").val());
        var salt = $("#salt").val();
        var password = md5(pwd + salt);
        $("#password").val(password);

        $.get("<%=request.getContextPath()%>/user/login",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                layer.msg(data.msg, function(){
                    if (data.code != 200) {
                        return;
                    }
                    window.location.href = "<%=request.getContextPath()%>/index/toIndex";
                })

            })
    }


</script>
</head>
<body>

    <form id = "fm">
        <input type="hidden" name = "salt" id = "salt"/>
        <input type="hidden" name = "_method" value = "get"/>
        用户名/手机号/邮箱:<input type = "text" name = "userName" id = "userName" onblur="inquire(this)"/><br/>
        密    码:<input type = "text" name = "password" id = "password"/><br/>
        <a href="<%=request.getContextPath()%>/user/toAdd">还有没有账号?点我去注册</a>
        <input type = "button" value = "login" onclick = "login()"/>
    </form>

</body>
</html>
