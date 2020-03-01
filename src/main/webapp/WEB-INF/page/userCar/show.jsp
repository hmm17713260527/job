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
        $.post("<%=request.getContextPath() %>/userCar/list",
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
                    html += "<td>"+data.data.carList[i].id+"</td>";
                    html += "<td>"+data.data.carList[i].brand+"</td>";
                    html += "<td>"+data.data.carList[i].carName+"</td>";
                    html += "<td><img src='<%=request.getContextPath()%>/car/toImg?fileName="+data.data.carList[i].carImg+"' width='80px' height='80px'></td>";
                    html += "<td>"+data.data.carList[i].price+"</td>";
                    html += "<td><input type='button' value='返还' onclick='toRetuen("+data.data.carList[i].id+")'/></td>";
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


    function toRetuen(id) {
        var index = layer.load(0, {shade:0.5});
        $.post("<%=request.getContextPath()%>/userCar/repay",
            {"id" : id, "_method" : "put"},
            function(data){
                layer.close(index);
                layer.msg(data.msg, function(){
                    if (data.code != 200) {
                        return;
                    }
                    window.location.href = "<%=request.getContextPath()%>/userCar/toShow";
                });

            })
    }




</script>
</head>
<body>

<form id = "fm">
    <input type = "hidden" name = "isDel" value = "1"/><br/>
    <input type="hidden" value="1" id="pageNo" name="pageNo">
    <table cellpadding='12px' cellspacing='0px' border='1px'  bordercolor='gray' bgcolor='pink'>
        <tr>
            <td>id</td>
            <td>品牌</td>
            <td>car名</td>
            <td>照片</td>
            <td>租金</td>
            <td>操作</td>
        </tr>

        <tbody id = "tbd">

        </tbody>

    </table>

</form>
<div id="pageInfo">

</div>

</body>
</html>
