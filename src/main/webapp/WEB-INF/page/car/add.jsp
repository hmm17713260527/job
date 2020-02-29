<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/29
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\md5-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
<script src="<%=request.getContextPath()%>\static\js\jquery.validate.js"></script>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">

    $(function() {
        $("#fm").validate({

            rules:{
                brand:{
                    required:true
                },
                carName:{
                    required:true
                },
                fileName:{
                    required:true
                },
                count:{
                    required:true,
                    digits:true,
                },
                price:{
                    required:true,
                    digits:true
                }



            },
            messages:{
                brand:{
                    required:"写品牌了！！"
                },
                carName:{
                    required:"写车名了！！"
                },
                fileName:{
                    required:"选择照片了！！"
                },
                count:{
                    required:"没有？？",
                    digits:"只能是数字"
                },
                price:{
                    required:"免费？？",
                    digits:"只能是数字"
                }

            }


        })
    })

</script>
<style>
    .error{
        color:red;
    }
</style>
</head>
<body>

<form action="<%=request.getContextPath()%>/car/add" method="post" enctype="multipart/form-data">
    <input type="hidden" name = "_method" value = "POST">
    品牌:<input type = "text" name = "brand" id = "brand"/><br/>
    车名:<input type = "text" name = "carName" id = "carName"/><br/>
    <%--照片:<input type = "text" name = "carImg"/><br/>--%>
    照片:<input type="file" name = "fileName"/><br/>
    库存:<input type = "text" name = "count"/><br/>
    租金:<input type = "text" name = "price"/><br/>
    <input type = "hidden" name = "isDel" value = "1"/><br/>
    <input type = "submit"/>

</form>

</body>
</html>
