<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>left</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>\static\zTree\zTree_v3\css\zTreeStyle\zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>\static\zTree\zTree_v3\js\jquery.ztree.core.js"></script>
</head>
<body align="center">

<h2><a href="<%=request.getContextPath()%>/car/toShow" target="right">商品信息</a></h2>

<c:if test="${user.type == 1}">
	<h2><a href="<%=request.getContextPath()%>/car/toShow" target="right">我的订单</a></h2>
</c:if>

<%--<c:if test="${user.type == 2}">
	<h2><a href="<%=request.getContextPath()%>/commodity/toShow" target="right">销售额</a></h2>
</c:if>--%>


</body>
</html>