<%@ page import="entity.Clothes" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: baobao
  Date: 2020/11/30
  Time: 10:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库存</title>
</head>
<body>
<div style="height:500px; width:500px; margin:0 auto;text-align: center;border: 2px solid; border-radius: 10px;background-color: wheat;">
    <h1 align="center">库存:</h1><br>
    <form action="/get" method="get">
        筛选大于该数量的服装库存：
        <input type="number" name="num">&nbsp;<input type="submit" value="搜索">
        <%List<Clothes> clothes = (List<Clothes>) session.getAttribute("clothes");%>
        <table border="1px" style="margin-top: 80px;margin-left: 130px;border: 2px solid;border-radius: 20px;">
            <th>服装品牌：</th>
            <th>服装数量：</th>
            <th>服装价格：</th>
            <%
                for (Clothes cloth : clothes) {
            %>
            <tr>
                <td><%=cloth.getBrand()%>
                </td>
                <td><%=cloth.getNumber()%>
                </td>
                <td>$<%=cloth.getPrice()%>.00
                </td>
                <%}%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
