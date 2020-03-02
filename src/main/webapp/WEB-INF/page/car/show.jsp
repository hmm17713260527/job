<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/29
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jsencrypt.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
<script type="text/javascript">


    var totalNum = 0;

    $(function() {
        search();
    })


    function search() {
        var index = layer.load(0, {shade:0.5});
        $.post("<%=request.getContextPath() %>/car/list",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                if (data.code != 200) {
                    layer.msg(data.msg);
                    return;
                }

                var html = "";
                for (var i = 0; i < data.data.carList.length; i++) {
                    html += "<tr>";
                    html += "<td><input type='checkbox' name = 'ids' value = '"+data.data.carList[i].id+"'/></td>";
                    html += "<td>"+data.data.carList[i].brand+"</td>";
                    html += "<td>"+data.data.carList[i].carName+"</td>";
                    html += "<td><img src='<%=request.getContextPath()%>/car/toImg?fileName="+data.data.carList[i].carImg+"' width='80px' height='80px'></td>";
                    html += "<td>"+data.data.carList[i].count+"</td>";
                    html += "<td>"+data.data.carList[i].price+"</td>";
                    <c:if test="${user.type == 1}">
                        html += "<td><input type='button' value='租用' onclick='toRent("+data.data.carList[i].id+")'/></td>";
                    </c:if>
                    html += "</tr>";
                }
                $("#tbd").html(html);
                totalNum = data.data.totalNum;
                var pageNo = $("#pageNo").val();
                var pageHtml = "<input type='button' value='上一页' onclick='page("+(parseInt(pageNo) - 1)+")'>";
                pageHtml += "<input type='button' value='下一页' onclick='page("+(parseInt(pageNo) + 1)+")')'>";
                $("#pageInfo").html(pageHtml);


            });
    }



    function page(page) {

        if (page < 1) {
            layer.msg('首页啦!', {icon:0});
            return;
        }
        if (page > totalNum) {
            layer.msg('已经到尾页啦!!', {icon:0});
            return;
        }
        $("#pageNo").val(page);
        search();

    }

    function find(){
        $("#pageNo").val(1);
        search();
    }

    function toRent(id) {
        var index = layer.load(0, {shade:0.5});
        $.post("<%=request.getContextPath()%>/car/rent",
            {"id" : id, "_method" : "put"},
            function(data){
                layer.close(index);
                layer.msg(data.msg, function(){
                    if (data.code != 200) {
                        return;
                    }
                    window.location.href = "<%=request.getContextPath()%>/car/toShow";
                });

            })
    }


    function toUpdate() {
        var chkValue = $('input[name="ids"]:checked');
        if (chkValue.length == 0) {
            layer.msg('未选中');
        } else if (chkValue.length > 1) {
            layer.msg('多选');
        } else {
            var id = chkValue.val();
            window.location.href = "<%=request.getContextPath()%>/car/toUpdate/"+id;
        }
    }


    function toAdd() {

        window.location.href = "<%=request.getContextPath()%>/car/toAdd";

        <%--layer.open({--%>
        <%--    type: 2,--%>
        <%--    title: '新增页面',--%>
        <%--    shadeClose: true,--%>
        <%--    shade: 0.8,--%>
        <%--    area: ['380px', '80%'],--%>
        <%--    content: '<%=request.getContextPath()%>/car/toAdd'--%>
        <%--});--%>
    }



    function del() {
        var chkValue = $('input[name="ids"]:checked');
        if (chkValue.length == 0) {
            layer.msg('未选中');
        } else if (chkValue.length > 1) {
            layer.msg('多选');
        } else {
            var id = chkValue.val();
            var index = layer.load(0, {shade:0.5});
            $.post("<%=request.getContextPath()%>/car/del",
                {"id" : id, "isDel" : 2, "_method" : "delete"},
                function(data){
                    layer.close(index);
                    layer.msg(data.msg, function(){
                        if (data.code != 200) {
                            return;
                        }
                        window.location.href = "<%=request.getContextPath()%>/car/toShow";
                    });

                })
        }
    }




</script>
</head>
<body>

<form id = "fm">
    <%--<input type="hidden" name="_method" value="get"/>--%>
    <input type="hidden" value="1" id="pageNo" name="pageNo">
    品牌:<input type = "text" name = "brand"/><br/>
    <input type = "hidden" name = "isDel" value = "1"/><br/>
    <input type = "button" value = "search" onclick = "find()"/><br/>
    <c:if test="${user.type == 2}">
        <input type="button" value="新增" onclick="toAdd()"/>
        <input type="button" value="删除" onclick="del()"/>
        <input type="button" value="修改" onclick="toUpdate()"/>
    </c:if>
    <table cellpadding='12px' cellspacing='0px' border='1px'  bordercolor='gray' bgcolor='pink'>
        <tr>
            <td>id</td>
            <td>品牌</td>
            <td>car名</td>
            <td>照片</td>
            <td>库存</td>
            <td>租金</td>
            <c:if test="${user.type == 1}">
                <td>操作</td>
            </c:if>
        </tr>

        <tbody id = "tbd">

        </tbody>

    </table>

</form>
<div id="pageInfo">

</div>

</body>
</html>
