<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/29
  Time: 19:37
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


</script>
</head>
<body>

<form action="<%=request.getContextPath()%>/car/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name = "id" value="${car.id}"/>
    品牌:<input type = "text" name = "brand" value="${car.brand}"/><br/>
    车名:<input type = "text" name = "carName" value="${car.carName}"/><br/>
    照片:
<%--    <img src='<%=request.getContextPath()%>/car/toImg?fileName="+${car.carImg}+"' width='80px' height='80px'>--%>
    <img src="<%=request.getContextPath()%>/car/toImg?fileName=${car.carImg}" width="80px" height="80px">
    照片:<input type="file" name = "fileName"/><br/>
    库存:<input type = "text" name = "count" value="${car.count}"/><br/>
    租金:<input type = "text" name = "price" value="${car.price}"/><br/>
    <input type = "submit"/>
</form>

</body>
</html>
