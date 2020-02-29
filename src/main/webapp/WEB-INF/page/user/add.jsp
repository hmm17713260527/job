<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\js\md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
    <script src="<%=request.getContextPath()%>\static\js\jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">

        jQuery.validator.addMethod("phone",
            function(value, element) {
                var tel = /^1[3456789]\d{9}$/;
                return tel.test(value)
            }, "请正确填写您的手机号");

        jQuery.validator.addMethod("email",
            function(value, element) {
                var tel = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
                return tel.test(value)
            }, "请正确填写您的邮箱编号");



        $(function() {
            $("#fm").validate({

                rules:{
                    userName:{
                        required:true,
                        minlength:2,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/findByName",
                            data:{
                                userName:function() {
                                    return $("#userName").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }

                            }
                        }
                    },
                    password:{
                        required:true,
                        minlength:5,
                        digits:true
                    },
                    password1:{
                        required:true,
                        minlength:5,
                        digits:true,
                        equalTo:"#password"
                    },
                    phone:{
                        required:true,
                        digits:true,
                        phone:true,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/findByPhone",
                            data:{
                                phone:function() {
                                    return $("#phone").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }

                            }
                        }
                    },
                    email:{
                        required:true,
                        email:true,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/findByEmail",
                            data:{
                                email:function() {
                                    return $("#email").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }

                            }
                        }
                    },
                    sex:{
                        required:true
                    },
                    age:{
                        required:true,
                        digits:true
                    }

                    
                    
                },
                messages:{
                    userName:{
                        required:"不能为空",
                        minlength:"最少两个字",
                        remote:"已注册"
                    },
                    password:{
                        required:"写密码啊",
                        minlength:"最少5个",
                        digits:"只能是数字"
                    },
                    password1:{
                        required:"写密码啊",
                        minlength:"最少5个字",
                        digits:"只能是数字",
                        equalTo:"两次不一致"
                    },
                    phone:{
                        required:"写手机号啊",
                        digits:"只能是数字",
                        phone:"格式不对",
                        remote:"已注册"
                    },
                    email:{
                        required:"填写你的邮箱",
                        email:"邮箱格式不对",
                        remote:"已注册"
                    },
                    sex:{
                        required:"性别！！！"
                    },
                    age:{
                        required:"年龄都不写！！",
                        digits:"只能是数字"
                    }

                }


            })
        })



        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(0, {shade:0.5});
                var pwd = md5($("#password").val());
                $("#password").val(pwd);
                $.post("<%=request.getContextPath() %>/user/addUser",
                    $("#fm").serialize(),
                    function(data){
                        layer.close(index);
                        layer.msg(data.msg, function(){
                            if (data.code != 200) {
                                return;
                            }
                            window.location.href = "<%=request.getContextPath()%>/user/toLogin";
                        });
                    })
            }
        });



    </script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>

<form id = "fm">
    <input type="hidden" name = "_method" value = "POST">
    用户名:<input type = "text" name = "userName" id = "userName"/><br/>
    密    码:<input type = "text" name = "password" id = "password"/><br/>
    重新输入密码:<input type = "text" name = "password1"/><br/>
    手机号:<input type = "text" name = "phone" id = "phone"/><br/>
    邮箱:<input type = "text" name = "email" id = "email"/><br/>
    性别:<input type = "radio" name = "sex" value="1" checked/>男<input type = "radio" name = "sex" value="2"/>女<br/>
    年龄:<input type = "text" name = "age" id = "age"/><br/>
    <input type = "hidden" name = "isDel" value = "1"/><br/>
    <input type = "submit"/>

</form>



</body>
</html>